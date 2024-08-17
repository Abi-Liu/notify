package com.abiliu.notify.services.impl;

import com.abiliu.notify.entities.Feed;
import com.abiliu.notify.entities.Post;
import com.abiliu.notify.repositories.FeedRepository;
import com.abiliu.notify.repositories.PostRepository;
import com.abiliu.notify.services.PostService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final ExecutorService taskExecutor;
    private final FeedRepository feedRepository;
    private final PostRepository postRepository;

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    private String fetchXML(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(HttpMethod.GET.name());

        InputStream stream = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            content.append(inputLine);
        }

        reader.close();
        con.disconnect();
        return content.toString();
    }

    private List<Post> parseRss(String xml)
            throws ParserConfigurationException, IOException, SAXException, ParseException {
        List<Post> items = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        doc.getDocumentElement().normalize();

        // date formatting
        SimpleDateFormat inputFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        NodeList nodeList = doc.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Post post = new Post();
                post.setTitle(getTagValue("title", element));
                post.setLink(getTagValue("link", element));

                Date date = inputFormat.parse(getTagValue("pubDate", element));
                String formattedDate = outputFormat.format(date);
                post.setPubDate(formattedDate);
                post.setGuid(getTagValue("guid", element));
                post.setDescription(getTagValue("description", element));

                items.add(post);
            }
        }

        return items;
    }

    private void insertPostIfUnique(Post post) {
        Optional<Post> optionalPost = postRepository.findByGuid(post.getGuid());
        if (optionalPost.isEmpty()) {
            postRepository.saveAndFlush(post);
        }
    }

    public void startScraping(int numPosts, Duration duration) throws InterruptedException {
        while (true) {
            List<Feed> feeds = feedRepository.fetchNextFeeds(numPosts);
            CountDownLatch latch = new CountDownLatch(feeds.size());

            // loop through each feed returned and fetch the xml on a new thread
            for (Feed feed : feeds) {
                taskExecutor.execute(() -> {
                    try {
                        String xml = fetchXML(feed.getUrl());
                        List<Post> posts = parseRss(xml);

                        for (Post post : posts) {
                            insertPostIfUnique(post);
                        }
                    } catch (Exception e) {
                        if (!(e.getCause() instanceof ConstraintViolationException)) {
                            System.err.println("Error: " + e.getMessage());
                            throw new RuntimeException();
                        }
                    } finally {
                        latch.countDown();
                    }
                });
            }
            System.out.println("Waiting for threads to complete");
            latch.await();
            System.out.println("All threads completed!");
            Thread.sleep(duration);
        }
    }
}
