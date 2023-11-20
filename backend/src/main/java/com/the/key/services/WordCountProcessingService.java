package com.the.key.services;

import com.the.key.exceptions.DataProcessingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WordCountProcessingService {

    public Map<String, Integer> processWordCount(String content) {
        try {
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
        } catch (Exception e) {
            throw new DataProcessingException("Error processing word count: " + e.getMessage());
        }
    }
}
