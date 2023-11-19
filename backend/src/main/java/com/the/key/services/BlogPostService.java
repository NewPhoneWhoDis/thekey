package com.the.key.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.the.key.models.BlogPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BlogPostService {

    private final RestTemplate restTemplate;
    private final SimpMessagingTemplate messagingTemplate;
    @Value("${internate.base.api}")
    private String internetBaseApi;

    public BlogPostService(RestTemplate restTemplate, SimpMessagingTemplate messagingTemplate) {
        this.restTemplate = restTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void fetchBlogPosts() {
        try {
            String rawResponse = restTemplate.getForObject(internetBaseApi + "/posts", String.class);
            if (rawResponse != null) {
                Pattern pattern = Pattern.compile("\\[\\s*\\{");
                Matcher matcher = pattern.matcher(rawResponse);

                if (matcher.find()) {
                    String jsonResponse = rawResponse.substring(matcher.start());
                    ObjectMapper objectMapper = new ObjectMapper();
                    BlogPost[] posts = objectMapper.readValue(jsonResponse, BlogPost[].class);

                    // Process posts
                    for (BlogPost post : posts) {
                        Map<String, Integer> wordCount = createWordCountMap(post.getContent().getRendered());
                        messagingTemplate.convertAndSend("/topic/wordcount", wordCount);
                    }
                } else {
                    System.out.println("JSON content start pattern not found in the response.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error processing blog posts: " + e.getMessage());
        }
    }

    private Map<String, Integer> createWordCountMap(String content) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        // String HTML and convert to text
        Document doc = Jsoup.parse(content);
        String text = doc.text();

        // Using Regex to match words and then matcher to find the words
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        return wordCountMap;
    }
}
