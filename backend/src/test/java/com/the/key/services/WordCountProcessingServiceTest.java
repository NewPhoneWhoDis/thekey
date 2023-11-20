package com.the.key.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class WordCountProcessingServiceTest {

    private WordCountProcessingService wordCountProcessingService = new WordCountProcessingService();

    @Test
    void processWordCount_ShouldCountWordsCorrectly() {
        String sampleContent = "Content of post 1 including various words and phrases. Another post content with different words.";

        Map<String, Integer> wordCount = wordCountProcessingService.processWordCount(sampleContent);

        assertNotNull(wordCount);
        assertEquals(2, (int) wordCount.get("content"));
        assertEquals(1, (int) wordCount.get("including"));
        assertEquals(2, (int) wordCount.get("post"));
    }
}

