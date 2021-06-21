package com.awtwvo.data.models.youtube;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Snippet {

    private LocalDateTime publishedAt;

    private String title;

    private String description;

    private Map<String, Thumbnail> thumbnails;

    private String channelTitle;

    private LocalDateTime publishTime;

}
