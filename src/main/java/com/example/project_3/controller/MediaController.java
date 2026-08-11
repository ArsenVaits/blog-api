package com.example.project_3.controller;

import com.example.project_3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

    private final S3Service s3Service;


    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file) {
        return s3Service.uploadFile(file);
    }
}
