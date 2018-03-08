package com.demo.producer.controller;

import com.demo.producer.model.Quotes;
import com.demo.producer.service.MovieService;
import com.demo.producer.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProducerController {

    @Autowired
    private WelcomeService welcomeService;

    @Autowired
    private MovieService movieService;

    @RequestMapping("/quote")
    public String getRandomQuote() {
        return new Quotes().getRandomQuote();
    }

    @RequestMapping("/movies/{year}")
    public List<String> getMoviesByYear(@PathVariable("year") int year) {
        return movieService.getMovieNamesByYear(year);
    }

    @RequestMapping("/moviesWithId/{year}")
    public List<String[]> getMoviesByYearWithId(@PathVariable("year") int year) {
        return movieService.getMovieNamesByYearWithId(year);
    }

    @RequestMapping("/movieDetails/{imdbId}")
    public String getMoviesByYear(@PathVariable("imdbId") String imdbId) {
        return movieService.getMovieDetails(imdbId);
    }

    @RequestMapping("/movieDetails")
    public String getMoviesDetails(@RequestParam("imdbId") String imdbId) {
        return movieService.getMovieDetails(imdbId);
    }

    @RequestMapping("/visitorCount")
    public long getVisitorCount() {
        return welcomeService.getVisitorCount();
    }

}
