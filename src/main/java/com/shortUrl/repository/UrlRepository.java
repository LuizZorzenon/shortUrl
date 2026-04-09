package com.shortUrl.repository;

import com.shortUrl.dto.UrlResponse;
import com.shortUrl.entity.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlModel, Long> {


   Optional<UrlModel> findByShortUrl(String shortUrl);
}
