package com.awtwvo.data.models.youtube;

import lombok.Data;

@Data
public final class YoutubeVideoSearch {

    private String etag;

    private ItemId id;

    private Snippet snippet;

    private Statistics statistics;

    private String videoId;

    public void initializeVideoId() {
        this.videoId = id.getVideoId();
    }
}