package com.demo.producer.service.impl;


import com.demo.producer.dao.MongoDao;
import com.demo.producer.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MongoDao mongoDao;

    @Override
    public List<String> movieNamesForTheActors(String actorName) {
        return null;
    }

    @Override
    public List<String> getAllActors() {
        return null;
    }

    @Override
    public List<String> getMovieNamesByYear(int year) {
        return mongoDao.getMoviesByYear(year);
    }

    @Override
    public List<String[]> getMovieNamesByYearWithId(int year) {
        return mongoDao.getMoviesWithIdByYear(year);
    }

    @Override
    public List<String> getMoviesByCountry(String countryName) {
        return mongoDao.getMoviesByCountry(countryName);
    }

    @Override
    public String getMovieDetails(String imdbId) {
        return mongoDao.getMoviesDetails(imdbId);
    }
}
