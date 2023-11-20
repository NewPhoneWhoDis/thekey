package com.the.key.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.the.key.models.BlogPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class DataFetchingServiceTest {

    private RestTemplate restTemplate;
    private DataFetchingService dataFetchingService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        dataFetchingService = new DataFetchingService(restTemplate);
    }

    @Test
    void fetchBlogPosts_ShouldReturnPosts() {
        String jsonResponse = "[{\"id\":1,\"date\":\"2023-03-15\",\"title\":{\"rendered\":\"Post Title 1\"},\"content\":{\"rendered\":\"Content of post 1.\"}},{\"id\":2,\"date\":\"2023-03-16\",\"title\":{\"rendered\":\"Post Title 2\"},\"content\":{\"rendered\":\"Another post content.\"}}]";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        BlogPost[] posts = dataFetchingService.fetchBlogPosts();

        assertNotNull(posts);
        assertEquals(2, posts.length);
        assertEquals(1L, posts[0].getId());
        assertEquals("Post Title 1", posts[0].getTitle().getRendered());
    }
}

