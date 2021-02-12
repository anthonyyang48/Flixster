package com.example.flixster.modules;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// POJO class to encapsulate the idea of a movie
public class Movie {

    String posterPath;
    String title;
    String overview;

    // Constructor takes out the JSON object and extract the fields we want
    // If any of the get methods fails, then whoever is calling the method is responsible for
    // handling the JSON Exception
    public Movie(JSONObject jsonObject) throws JSONException {

        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");

    }

    // Method iterates through the JSON Array and constructing a movie for each element
    // inside the JSON Array
    public static List<Movie> fromJSONArray(JSONArray movieJsonArray) throws JSONException{
        List<Movie> movies = new ArrayList<>();

        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }

        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

}
