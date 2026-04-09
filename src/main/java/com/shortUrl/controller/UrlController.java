package com.shortUrl.controller;


import com.shortUrl.dto.UrlRequest;
import com.shortUrl.dto.UrlResponse;
import com.shortUrl.entity.UrlModel;
import com.shortUrl.repository.UrlRepository;
import com.shortUrl.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;
    private final UrlRepository urlRepository;

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

    @GetMapping("/r/{shortcode}")
    public ResponseEntity<Void> redirectToUrl(@PathVariable String shortcode){
        String originalUrl = urlService.redirectToUrl(shortcode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("location", originalUrl)
                .build();
    }

    @PostMapping("/create")
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody UrlRequest url){
        UrlResponse createdUrl = urlService.createShortUrl(url);
        return ResponseEntity.ok(createdUrl);
    }

    @PatchMapping("/{shortcode}")
    public ResponseEntity<UrlResponse> updateUrl(@RequestBody UrlRequest urlRequest,@PathVariable String shortcode){
        return ResponseEntity.ok(
                urlService.urlUpdate(urlRequest ,shortcode)
        );
    }

    @DeleteMapping("/{shortcode}")
    public ResponseEntity<String> deleteUrl(@PathVariable String shortcode){
        urlService.deleteUrl(shortcode);
        return ResponseEntity.ok("URL deletada com sucesso!");
    }

}
