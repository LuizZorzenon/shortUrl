package com.shortUrl.service;

import com.shortUrl.dto.UrlRequest;
import com.shortUrl.dto.UrlResponse;
import com.shortUrl.entity.UrlModel;
import com.shortUrl.entity.UserModel;
import com.shortUrl.mapper.UrlMapper;
import com.shortUrl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // Services used by controller
    public List<UrlResponse> getAllUrls(){

        UserModel user = getUserContext();

        return urlRepository.findByUser(user)
                .stream()
                .map(urlMapper::toResponse)
                .toList();
    }

    public UrlResponse getUrlByShortCode(String shortCode){
        UrlModel url = urlRepository.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL não encontrada!"));

        return urlMapper.toResponse(url);
    }

    public UserModel getUserContext(){
        UserModel user = (UserModel) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return user;
    }

    public UrlResponse urlUpdate(UrlRequest urlRequest, String shortCode){
        UrlModel url = urlRepository.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL não encontrada!"));

        url.setShortUrl(generateShortCode());

        UrlModel savedUrl = urlRepository.save(urlMapper.updateUrl(url, urlRequest));
        return urlMapper.toResponse(savedUrl);
    }

    public void deleteUrl(String shortCode){
        UrlModel url = urlRepository.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL não encontrada!"));

        urlRepository.delete(url);
    }

    // Services
    public String generateShortCode(){
        return UUID
                .randomUUID()
                .toString()
                .replace("-","")
                .substring(0,5)
                .toUpperCase();
    }

    public UrlResponse createShortUrl(UrlRequest request){

        UserModel user = getUserContext();

        UrlModel newUrl = urlMapper.toModel(request);

        String shortCode = generateShortCode();
        newUrl.setShortUrl(shortCode);
        newUrl.setUser(user);

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

    public String redirectToUrl(String shortCode){
        UrlModel url = urlRepository.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL não encontrada!"));

        url.setClicks(url.getClicks() + 1);
        urlRepository.save(url);

        return "URL ENCONTRADA!";
    }
}
