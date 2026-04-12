package com.shortUrl.mapper;

import com.shortUrl.dto.UrlRequest;
import com.shortUrl.dto.UrlResponse;
import com.shortUrl.entity.UrlModel;
import com.shortUrl.repository.UrlRepository;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {

    public UrlModel toModel(UrlRequest urlRequest){
        return UrlModel.builder()
                .urlBase(urlRequest.urlBase())
                .build();
    }

    public UrlResponse toResponse(UrlModel urlModel){
        return UrlResponse.builder()
                .id(urlModel.getId())
                .urlBase(urlModel.getUrlBase())
                .shortUrl(urlModel.getShortUrl())
                .clicks(urlModel.getClicks())
                .createdAt(urlModel.getCreatedAt())
                .updatedAt(urlModel.getUpdatedAt())
                .build();
    }

    public UrlModel updateUrl(UrlModel urlModel, UrlRequest urlRequest){
        if(urlRequest.urlBase() != null) urlModel.setUrlBase(urlRequest.urlBase());
        if(urlRequest.urlBase() != null) urlModel.setShortUrl(urlRequest.shortUrl());

        return urlModel;
    }

}
