package a1824jj.jp.ac.aiit.tmdb_sampel.service;

import a1824jj.jp.ac.aiit.tmdb_sampel.model.MovieDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovieQuery(@Query("api_key")String apiKey);


}
