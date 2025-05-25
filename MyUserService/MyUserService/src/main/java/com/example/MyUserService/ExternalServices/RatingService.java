package com.example.MyUserService.ExternalServices;

import com.example.MyUserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RatingService")
public interface RatingService {

    @PostMapping("/ratings")
    Rating CreateRating();

    @PutMapping("/")
    Rating updateRating();
}
