package com.the.key.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.the.key.models.BlogPost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DataFetchingService {

    private final RestTemplate restTemplate;
    @Value("${internate.base.api}")
    private String internetBaseApi;

    public DataFetchingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BlogPost[] fetchBlogPosts() {
        try {
            String rawResponse = restTemplate.getForObject(internetBaseApi + "/posts", String.class);
            if (rawResponse != null) {
                Pattern pattern = Pattern.compile("\\[\\s*\\{");
                Matcher matcher = pattern.matcher(rawResponse);

                if (matcher.find()) {
                    String jsonResponse = rawResponse.substring(matcher.start());
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(jsonResponse, BlogPost[].class);
                } else {
                    System.out.println("JSON content start pattern not found in the response.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching blog posts: " + e.getMessage());
        }
        return new BlogPost[0];
    }
}
