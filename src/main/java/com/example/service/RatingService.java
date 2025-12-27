package com.example.service;

import com.example.model.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> findAll();

    Rating findById(long id);

    Rating createRating(Rating rating);

    Rating updateRating(Long id, Rating rating);

    void deleteById(long id);
}
