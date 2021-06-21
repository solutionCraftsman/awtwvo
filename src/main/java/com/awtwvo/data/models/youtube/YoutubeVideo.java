package com.awtwvo.data.models.youtube;

import lombok.Data;

@Data
public final class YoutubeVideo {

    private String etag;

    private String id;

    private Snippet snippet;

    private Statistics statistics;

    private String videoId;

    public void initializeVideoId() {
        this.videoId = id;
    }

}