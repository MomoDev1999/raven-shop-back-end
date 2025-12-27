package com.example.service;

import com.example.model.Rating;
import com.example.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating findById(long id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.orElse(null);
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long id, Rating rating) {
        if (ratingRepository.existsById(id)) {
            rating.setId(id);
            return ratingRepository.save(rating);
        } else {
            throw new IllegalArgumentException("El rating con el ID especificado no existe.");
        }
    }

    @Override
    public void deleteById(long id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El rating con el ID especificado no existe.");
        }
    }
}
