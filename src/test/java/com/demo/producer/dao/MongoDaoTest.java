package com.demo.producer.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MongoDaoTest {

    MongoDao dao = new MongoDao();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMoviesByYear() throws Exception {
        List<String> movieList= dao.getMoviesByYear(2013);

        Assert.assertNotNull("Retrieved movie list for 2013 cannot be null",movieList);
        Assert.assertEquals("Retrieved movie list for 2013 will have 52074 entries",52074,movieList.size());

    }

   

    @Test
    public void getMoviesByYearWithId() throws Exception {
        List<String[]> movieList= dao.getMoviesWithIdByYear(2013);

        Assert.assertNotNull("Retrieved movie list for 2013 cannot be null",movieList);
        Assert.assertEquals("Retrieved movie list for 2013 cannot be null",52074,movieList.size());
        Assert.assertEquals("Each movie should have id and title",2,movieList.get(0).length);

    }

    @Test
    public void getMovieDetails() throws Exception{
        String movieDetails = dao.getMoviesDetails("tt0475179");
        Assert.assertNotNull(movieDetails);

    }

}