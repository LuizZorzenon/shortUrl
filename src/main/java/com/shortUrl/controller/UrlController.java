package com.shortUrl.controller;


import com.shortUrl.dto.UrlRequest;
import com.shortUrl.dto.UrlResponse;
import com.shortUrl.entity.UrlModel;
import com.shortUrl.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping
    public ResponseEntity<List<UrlResponse>> getAllUrl(){
        List<UrlResponse> urls = urlService.getAllUrls();
        return ResponseEntity.ok(urls);
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<UrlResponse> getUrlByShortCode(@PathVariable String shortcode){
        UrlResponse url = urlService.getUrlByShortCode(shortcode);
        return ResponseEntity.ok(url);

    }

    @PostMapping("/create")
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody UrlRequest url){
        UrlResponse createdUrl = urlService.createShortUrl(url);

        return ResponseEntity.ok(createdUrl);
    }

    @PatchMapping("/{id}")
    public String updateUrl(@RequestBody UrlModel urlModel, Long id){
        return "Url atualizada";
    }

    @DeleteMapping("/{id}")
    public String deleteUrl(Long id){
        return "Url deletada!";
    }

}
