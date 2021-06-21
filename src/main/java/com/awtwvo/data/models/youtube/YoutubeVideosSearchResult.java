package com.awtwvo.data.models.youtube;

import lombok.Data;

import java.util.List;

@Data
public final class YoutubeVideosSearchResult<T> {

    private String etag;

    private String nextPageToken;

    private PageInfo pageInfo;

    private List<T> items;

}