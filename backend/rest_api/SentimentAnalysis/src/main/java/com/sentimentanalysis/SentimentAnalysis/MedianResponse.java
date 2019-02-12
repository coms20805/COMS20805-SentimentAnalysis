package com.sentimentanalysis.SentimentAnalysis;

import java.util.List;
import java.util.Map;

public class MedianResponse {

   private List<String> timestamps;
   private List<Double> medians;

    public MedianResponse(List<String> timestamps, List<Double> medians) {
        this.timestamps = timestamps;
        this.medians = medians;
    }

    public List<String> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<String> timestamps) {
        this.timestamps = timestamps;
    }

    public List<Double> getMedians() {
        return medians;
    }

    public void setMedians(List<Double> medians) {
        this.medians = medians;
    }
}
