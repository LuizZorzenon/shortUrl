package com.shortUrl.repository;

import com.shortUrl.entity.UrlModel;
import com.shortUrl.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Long> {


   Optional<UrlModel> findByShortUrl(String shortUrl);

   List<UrlModel> findByUser(UserModel user);
}
