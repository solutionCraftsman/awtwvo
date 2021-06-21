package com.awtwvo.services.youtube;

import com.awtwvo.data.dtos.SearchDTO;
import com.awtwvo.data.models.youtube.YoutubeVideo;
import com.awtwvo.data.models.youtube.YoutubeVideoSearch;
import com.awtwvo.data.models.youtube.YoutubeVideosSearchResult;
import com.awtwvo.exceptions.NullSearchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${YOUTUBE_API_KEY}")
    private String API_KEY;

    @Override
    public YoutubeVideosSearchResult<YoutubeVideoSearch> getVideos(SearchDTO searchDTO) throws NullSearchException {

        if(searchDTO == null){
            throw new NullSearchException("Search DTO cannot be null");
        }

        if(searchDTO.getQuery().isEmpty()){
            throw new NullSearchException("Search Query cannot be null");
        }

        final String URL_END_POINT = "https://youtube.googleapis.com/youtube/v3/search" +
                "?part=snippet" +
                "&maxResults=50" +
                "&type=video" +
                "&q=" + searchDTO.getQuery() +
                "&key=" + API_KEY;

        YoutubeVideosSearchResult<YoutubeVideoSearch> results = restTemplate.exchange(
                URL_END_POINT,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<YoutubeVideosSearchResult<YoutubeVideoSearch>>() {})
                .getBody();

        log.info("API Response --> {}", results);

        return results;
    }

    @Override
    public YoutubeVideosSearchResult<YoutubeVideo> getTrendingVideos(String regionCode)
    {
        final String URL_END_POINT = "https://youtube.googleapis.com/youtube/v3/videos" +
                "?part=snippet,statistics" +
                "&chart=mostPopular" +
                "&maxResults=50" +
                "&regionCode=" + regionCode +
                "&key=" + API_KEY;

        YoutubeVideosSearchResult<YoutubeVideo> results = restTemplate.exchange(
                URL_END_POINT,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<YoutubeVideosSearchResult<YoutubeVideo>>() {})
                .getBody();

        log.info("API Response --> {}", results);

        return results;
    }

    @Override
    public YoutubeVideo getVideoById(String videoId) {
        return null;
    }

}
