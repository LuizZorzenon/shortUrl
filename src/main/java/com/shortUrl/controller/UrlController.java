package com.shortUrl.controller;


import com.shortUrl.entity.UrlModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {


    @GetMapping("")
    public String getAllUrl(){
        return "url!";
    }

    @GetMapping("/{id}")
    public String getUrlById(Long id){
        return "url com id";
    }

    @PostMapping("/create")
    public String createShortUrl(@RequestBody UrlModel urlModel){
        return "Url criada";
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
