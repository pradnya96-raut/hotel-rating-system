package com.example.RatingService.Services;

import com.example.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating CreateRating(Rating rating);
    List<Rating> getRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
