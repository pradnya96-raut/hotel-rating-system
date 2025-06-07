package com.example.RatingService.Controller;

import com.example.RatingService.Services.RatingService;
import com.example.RatingService.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return new ResponseEntity<>(ratingService.CreateRating(rating), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getRating(){
        return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return new ResponseEntity<>(ratingService.getRatingByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
        return new ResponseEntity<>(ratingService.getRatingByHotelId(hotelId), HttpStatus.OK);
    }
}
