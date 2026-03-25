package com.example.subscriptionapp.controller;

import com.example.subscriptionapp.entity.Video;
import com.example.subscriptionapp.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        Video saved = videoRepository.save(video);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
