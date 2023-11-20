package com.the.key.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.the.key.models.BlogPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BlogPostServiceTest {

    private SimpMessagingTemplate messagingTemplate;
    private WordCountProcessingService wordCountProcessingService;
    private DataFetchingService dataFetchingService;
    private BlogPostService blogPostService;

    @BeforeEach
    void setUp() {
        messagingTemplate = mock(SimpMessagingTemplate.class);
        wordCountProcessingService = mock(WordCountProcessingService.class);
        dataFetchingService = mock(DataFetchingService.class);
        blogPostService = new BlogPostService(messagingTemplate, wordCountProcessingService, dataFetchingService);
    }

    private BlogPost createMockPost(Long id, String date, String title, String content) {
        return new BlogPost(id, date, new BlogPost.RenderedContent(title), new BlogPost.RenderedContent(content));
    }

    @Test
    void fetchBlogPosts_ShouldProcessAndSendWordCounts() {
        BlogPost[] mockPosts = new BlogPost[] {
                createMockPost(1L, "2023-03-15", "Post Title 1", "Content of post 1 including various words and phrases."),
                createMockPost(2L, "2023-03-16", "Post Title 2", "Another post content with different words.")
        };

        when(dataFetchingService.fetchBlogPosts()).thenReturn(mockPosts);
        when(wordCountProcessingService.processWordCount(anyString())).thenReturn(Map.of("word", 1));

        blogPostService.fetchBlogPosts();

        verify(messagingTemplate, times(2)).convertAndSend(eq("/topic/wordcount"), anyMap());
    }
}

