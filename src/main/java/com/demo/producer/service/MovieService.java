package com.demo.producer.service;

import java.util.List;

public interface MovieService {


    /**
     * Retrieve all the movies by the specified actor.
     * @param actorName  - actor whose movie names will be retrieved.
     * @return list of movies of that actor.
     */
    public List<String> movieNamesForTheActors(String actorName);

    /**
     *
     * @return - list of all actors.
     */
    public List<String> getAllActors();

    /**
     * Get list of movies in the passed year.
     * @param year for which all the movies will be retrieved.
     * @return list of movies released in that year.
     */
    public List<String> getMovieNamesByYear(int year);

    /**
     * Get list of movies in the passed year.
     * @param year for which all the movies will be retrieved.
     * @return list of movies released in that year.
     */
    public List<String[]> getMovieNamesByYearWithId(int year);


    /**
     * Get movies made from the specified country
     * @param countryName
     * @return - List of movies from that country
     */
    public List<String> getMoviesByCountry(String countryName);

    /**
     *
     * @param imdbId imdb.id of the movie.
     * @return movie details information in JSON format.
     */
    public String getMovieDetails(String imdbId);
}
