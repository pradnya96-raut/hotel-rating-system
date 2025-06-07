package com.example.MyUserService.Services.Impl;

import com.example.MyUserService.ExternalServices.HotelService;
import com.example.MyUserService.Services.UserService;
import com.example.MyUserService.entities.Hotel;
import com.example.MyUserService.entities.Rating;
import com.example.MyUserService.entities.User;
import com.example.MyUserService.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.MyUserService.repository.UserRepository;
import org.springframework.web.client.RestTemplate;
import com.example.MyUserService.Config.MyConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;

    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUser() {
        List<User> allUser = userRepository.findAll();
//        ArrayList<Rating>forObject = restTemplate.getForObject("http://localhost:9091/ratings/", ArrayList.class);
//        allUser.rating;

        return allUser;
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User id is not found"));
        //localhost:9091/ratings/users/af0029df-d888-48e2-bf41-5d4a5a2e24fe

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set hotel to rating
            rating.setHotel(hotel);
            return rating;

        }).toList();
        user.setRating(ratingList);
        return user;

    }
}
