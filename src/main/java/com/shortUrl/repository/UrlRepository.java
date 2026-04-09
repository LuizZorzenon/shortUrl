package com.shortUrl.repository;

import com.shortUrl.entity.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlModel, Long> {


}
