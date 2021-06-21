package com.awtwvo.services.youtube;

import com.awtwvo.data.dtos.SearchDTO;
import com.awtwvo.data.models.youtube.YoutubeVideo;
import com.awtwvo.data.models.youtube.YoutubeVideoSearch;
import com.awtwvo.data.models.youtube.YoutubeVideosSearchResult;
import com.awtwvo.exceptions.NullSearchException;

public interface VideoService {

    YoutubeVideosSearchResult<YoutubeVideoSearch> getVideos(SearchDTO searchDTO) throws NullSearchException;

    YoutubeVideosSearchResult<YoutubeVideo> getTrendingVideos(String regionCode);

    YoutubeVideo getVideoById(String videoId);

}
