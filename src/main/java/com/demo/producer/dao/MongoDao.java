package com.demo.producer.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoDao {

	private @Value("${mongoHostt}") static String mongoHost;

	private @Value("${mongoUserName}") static String mongoUserName;

	// private String @Value("{}") host;

	final static MongoClientURI uri = new MongoClientURI(
			"mongodb://m001-student:m001-mongodb-basics@cluster0-shard-00-00-jxeqq.mongodb.net:27017,cluster0-shard-00-01-jxeqq.mongodb.net:27017,cluster0-shard-00-02-jxeqq.mongodb.net:27017/admin?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

	final static MongoClient client = new MongoClient(uri);
	
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
			Document document = new Document("time", LocalDateTime.now().format(time)).append("date",
					LocalDate.now().format(sdf));
			coll.insertOne(document);
			return coll.count();

		} catch (Throwable th) {
			throw new Exception(th);
		} finally {

		}
	}

	public List<String> getMoviesByYear(int year) {
		List<String> relasedMovies = new ArrayList<>();
		MongoDatabase db = client.getDatabase("video");
		MongoCollection<Document> movieCollection = db.getCollection("movies");

		Document searchCriteria = new Document().append("year", year);
		FindIterable<Document> foundMovies = movieCollection.find(searchCriteria);
		for (Document eachDocument : foundMovies) {
			String title = eachDocument.get("title").toString();
			relasedMovies.add(title);
		}
		return relasedMovies;
	}

	public List<String[]> getMoviesWithIdByYear(int year) {
		List<String[]> relasedMovies = new ArrayList<>();
		MongoDatabase db = client.getDatabase("video");
		MongoCollection<Document> movieCollection = db.getCollection("movies");

		Document searchCriteria = new Document().append("year", year);
		FindIterable<Document> foundMovies = movieCollection.find(searchCriteria);
		for (Document eachDocument : foundMovies) {
			String title = eachDocument.get("title").toString();
//			Document imdb = (Document) eachDocument.get("imdbId");
			relasedMovies.add(new String[] { eachDocument.get("imdbId").toString(), title });
		}
		return relasedMovies;
	}

	public List<String> getMoviesByCountry(String countryName) {
		List<String> moviesFromACountry = new ArrayList<>();
		MongoDatabase db = client.getDatabase("video");
		MongoCollection<Document> movieCollection = db.getCollection("movieDetails");

		Document searchCriteria = new Document().append("countries", countryName);
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
	public String getMoviesDetails(String imdbId) {
		
		MongoDatabase db = client.getDatabase("video");
		MongoCollection<Document> movieCollection = db.getCollection("movieDetails");

		Document searchCriteria = new Document().append("imdb.id", imdbId);

		FindIterable<Document> foundMovies = movieCollection.find(searchCriteria);

		if (foundMovies != null && foundMovies.first() != null) {
			return foundMovies.first().toJson();
		}
		return "";
	}
}
