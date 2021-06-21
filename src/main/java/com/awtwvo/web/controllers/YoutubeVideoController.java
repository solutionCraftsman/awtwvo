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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/youtube/")
public class YoutubeVideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("{videoId}")
    public String watchYoutubeVideo(Model model, @PathVariable("videoId") String videoId)
    {
        YoutubeVideo video = videoService.getVideoById(videoId);
        model.addAttribute("video", video);

        return "videos/video";
    }

    @ModelAttribute
    public void createSearchDTO(Model model)
    {
        model.addAttribute("searchDTO", new SearchDTO());
    }

}
