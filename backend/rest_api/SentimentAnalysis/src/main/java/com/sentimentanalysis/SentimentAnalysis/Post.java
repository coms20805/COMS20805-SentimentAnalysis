package com.sentimentanalysis.SentimentAnalysis;

import java.util.Date;

import lombok.Value;

@Value
public class Post {
    private String content;
    private int timestamp;
    private String url;
    private double score;
}
