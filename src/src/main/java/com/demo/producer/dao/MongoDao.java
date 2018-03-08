package com.demo.producer.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class MongoDao {

    final static MongoClient client = new MongoClient();
    final static MongoDatabase db = client.getDatabase("click");
    static long hitCount = 0;

    /**
     * To format date in ddMMMyyyy (e.g. 19Jan2018).
     */
    DateTimeFormatter sdf = DateTimeFormatter.ofPattern("ddMMMyyyy");
    DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");



    public long eachClick() throws Exception {
        try {

            MongoCollection<Document> coll = db.getCollection("Clicks");
            Document document = new Document("time", LocalDateTime.now().format(time)).append("date", LocalDate.now().format(sdf));
            coll.insertOne(document);
            return coll.count();

        } catch (Throwable th) {
            throw new Exception(th);
        } finally {

        }
    }


    public List<String> getMoviesByYear(int year){
        List<String> relasedMovies = new ArrayList<>();
        MongoDatabase db = client.getDatabase("video");
        MongoCollection<Document> movieCollection = db.getCollection("movieDetails");

        Document searchCriteria = new Document().append("year",year);
        FindIterable<Document> foundMovies = movieCollection.find(searchCriteria);
        for (Document eachDocument : foundMovies) {
            String title = eachDocument.getString("title");
            relasedMovies.add(title);
        }
        return relasedMovies;
    }


    public List<String[]> getMoviesWithIdByYear(int year){
        List<String[]> relasedMovies = new ArrayList<>();
        MongoDatabase db = client.getDatabase("video");
        MongoCollection<Document> movieCollection = db.getCollection("movieDetails");

        Document searchCriteria = new Document().append("year",year);
        FindIterable<Document> foundMovies = movieCollection.find(searchCriteria);
        for (Document eachDocument : foundMovies) {
            String title = eachDocument.getString("title");
            Document imdb = (Document)eachDocument.get("imdb");
            relasedMovies.add(new String[]{imdb.getString("id"),title});
        }
        return relasedMovies;
    }


    public List<String> getMoviesByCountry(String countryName) {
        List<String> moviesFromACountry = new ArrayList<>();
        MongoDatabase db = client.getDatabase("video");
        MongoCollection<Document> movieCollection = db.getCollection("movieDetails");

        Document searchCriteria = new Document().append("countries",countryName);
        FindIterable<Document> foundMovies = movieCollection.find(searchCriteria);
        for (Document eachDocument : foundMovies) {
            String title = eachDocument.getString("title");
            moviesFromACountry.add(title);
        }
        return moviesFromACountry;
    }

    /**
     *
     * @param imdbId
     * @return JSON document about the film if exists , null otherwise.
     */
    public String getMoviesDetails(String imdbId){
        List<String> relasedMovies = new ArrayList<>();
        MongoDatabase db = client.getDatabase("video");
        MongoCollection<Document> movieCollection = db.getCollection("movieDetails");

        Document searchCriteria = new Document().append("imdb.id",imdbId);

        FindIterable<Document> foundMovies = movieCollection.find(searchCriteria);


        if (foundMovies != null && foundMovies.first() != null){
            return foundMovies.first().toJson();
        }
        return "";
    }
}
