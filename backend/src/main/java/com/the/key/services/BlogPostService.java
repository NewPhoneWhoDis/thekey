package com.the.key.services;

import com.the.key.exceptions.GenericWebSocketException;
import com.the.key.exceptions.WebSocketProcessingException;
import com.the.key.models.BlogPost;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogPostService {

    private final SimpMessagingTemplate messagingTemplate;
    private final Set<Long> lastFetchedPostIds = new HashSet<>();
    private final WordCountProcessingService wordCountProcessingService;
    private final DataFetchingService dataFetchingService;

    public BlogPostService(SimpMessagingTemplate messagingTemplate,
                           WordCountProcessingService wordCountProcessingService,
                           DataFetchingService dataFetchingService) {
        this.messagingTemplate = messagingTemplate;
        this.wordCountProcessingService = wordCountProcessingService;
        this.dataFetchingService = dataFetchingService;
    }

    @Scheduled(fixedRate = 5000)
    public void fetchBlogPosts() {
        try {

        } catch (MessageDeliveryException | MessageHandlingException e) {
            throw new WebSocketProcessingException("Error handling WebSocket message", e);
        } catch (Exception e) {
            throw new GenericWebSocketException("Unexpected error in WebSocket service", e);
        }
        BlogPost[] posts = dataFetchingService.fetchBlogPosts();
        Set<Long> currentFetchedPostIds = new HashSet<>();
        boolean isDataNew = false;

        for (BlogPost post: posts) {
            currentFetchedPostIds.add(post.getId());
            if(!lastFetchedPostIds.contains(post.getId())) {
                isDataNew = true;
                break;
            }
        }

        if(isDataNew) {
            lastFetchedPostIds.clear();
            lastFetchedPostIds.addAll(currentFetchedPostIds);

            // Process posts
            for (BlogPost post : posts) {
                Map<String, Integer> wordCount = wordCountProcessingService.processWordCount(post.getContent().getRendered());
                messagingTemplate.convertAndSend("/topic/wordcount", wordCount);
            }
        }
    }
}
