package com.example.hackathon.dto;

import com.example.hackathon.model.Media;
import com.example.hackathon.model.MediaSource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponseDto {

    private List<MediaResponseDto> mediaData = new ArrayList<>();
    @Data
    public static  class MediaResponseDto {
        private long userId;
        private long postId;
        private String sourceUrl;
        private String thumbnailUrl;
        private MediaSource mediaSource;
        private Creator creator;
        @Data
        public static class Creator {
            private String userName;
            private  long id;
        }
    }
}
