package com.awtwvo.web.controllers;

import com.awtwvo.data.dtos.SearchDTO;
import com.awtwvo.data.models.youtube.YoutubeVideo;
import com.awtwvo.data.models.youtube.YoutubeVideoSearch;
import com.awtwvo.data.models.youtube.YoutubeVideosSearchResult;
import com.awtwvo.exceptions.NullSearchException;
import com.awtwvo.services.youtube.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping
    public String index(Model model)
    {
        String regionCode = "NG";

        YoutubeVideosSearchResult<YoutubeVideo> results = videoService.getTrendingVideos(regionCode);

        for(YoutubeVideo video : results.getItems()) {
            video.initializeVideoId();
        }

        model.addAttribute("videos", results.getItems());
        model.addAttribute("pageInfo", results.getPageInfo());
        model.addAttribute("nextPageToken", results.getNextPageToken());

        return "videos/index";
    }

    @PostMapping
    public String processVideoSearchForm(@ModelAttribute("searchDTO") @Valid SearchDTO searchDTO, Model model)
    {
        try {
            YoutubeVideosSearchResult<YoutubeVideoSearch> results = videoService.getVideos(searchDTO);

            for(YoutubeVideoSearch video : results.getItems()) {
                video.initializeVideoId();
            }

            model.addAttribute("videos", results.getItems());
            model.addAttribute("pageInfo", results.getPageInfo());
            model.addAttribute("nextPageToken", results.getNextPageToken());
        }
        catch (NullSearchException exception) {
            exception.printStackTrace();
        }

        return "videos/index";
    }

    @ModelAttribute
    public void createSearchDTO(Model model)
    {
        model.addAttribute("searchDTO", new SearchDTO());
    }

}
