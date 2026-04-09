package com.shortUrl.service;

import com.shortUrl.dto.UrlRequest;
import com.shortUrl.dto.UrlResponse;
import com.shortUrl.entity.UrlModel;
import com.shortUrl.mapper.UrlMapper;
import com.shortUrl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlMapper urlMapper;
    private final UrlRepository urlRepository;

    @Value("${app.base_url}")
    private String domain;

    public String generateShortCode(){
        return UUID
                .randomUUID()
                .toString()
                .replace("-","")
                .substring(0,5)
                .toUpperCase();
    }

    public UrlResponse createShortUrl(UrlRequest request){
        UrlModel newUrl = urlMapper.toModel(request);
        String shortCode = generateShortCode();
        newUrl.setShortUrl(shortCode);
        urlRepository.save(newUrl);
        UrlResponse response = urlMapper.toResponse(newUrl);

        return new UrlResponse(
                response.id(),
                response.urlBase(),
                domain + shortCode,
                response.clicks(),
                response.createdAt(),
                response.updatedAt()
        );
    }

    public List<UrlResponse> getAllUrls(){
        return urlRepository.findAll()
                .stream().map(urlMapper::toResponse)
                .toList();
    }

    public UrlResponse getUrlByShortCode(String shortCode){
        UrlModel url = urlRepository.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL não encontrada!"));

        return urlMapper.toResponse(url);
    }

    public UrlResponse urlUpdate(UrlRequest urlRequest, String shortCode){
        UrlModel url = urlRepository.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL não encontrada!"));
        UrlModel savedUrl = urlRepository.save(urlMapper.updateUrl(url, urlRequest));
        return urlMapper.toResponse(savedUrl);
    }

}
