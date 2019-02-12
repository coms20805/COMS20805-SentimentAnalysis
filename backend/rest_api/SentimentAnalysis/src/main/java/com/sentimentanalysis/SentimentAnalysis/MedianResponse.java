package com.sentimentanalysis.SentimentAnalysis;

import java.util.List;
import java.util.Map;

public class MedianResponse {

    private Map<String, Double> medians;

    public MedianResponse(Map<String, Double> medians) {
        this.medians = medians;
    }

    public Map<String, Double> getMedians() {
        return medians;
    }

    public void setMedians(Map<String, Double> medians) {
        this.medians = medians;
    }
}
