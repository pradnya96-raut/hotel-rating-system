package com.example.HotelService.Services;

import com.example.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);
    List<Hotel> getAllHotel();
    Hotel getHotel(String hotelId);

}
