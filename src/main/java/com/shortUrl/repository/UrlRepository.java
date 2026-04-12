package com.shortUrl.repository;

import com.shortUrl.entity.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Long> {


   Optional<UrlModel> findByShortUrl(String shortUrl);
}
