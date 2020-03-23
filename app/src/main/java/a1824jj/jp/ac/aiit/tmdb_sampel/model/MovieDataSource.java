package a1824jj.jp.ac.aiit.tmdb_sampel.model;

import android.app.Application;

import java.util.ArrayList;

import a1824jj.jp.ac.aiit.tmdb_sampel.service.MovieDataService;
import a1824jj.jp.ac.aiit.tmdb_sampel.service.RetrofitInstance;
import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static a1824jj.jp.ac.aiit.tmdb_sampel.view.MainActivity.API_KEY;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private MovieDataService movieDataService;
    private Application application;

    public MovieDataSource(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {

        movieDataService = RetrofitInstance.getService();
        Call<MovieDBResponse> call = movieDataService.getPopularMovieQueryWithPaging(API_KEY, 1);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();
                if(movieDBResponse != null && movieDBResponse.getMovies() != null){
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    callback.onResult(movies, null, (long)2);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {

        movieDataService = RetrofitInstance.getService();
        Call<MovieDBResponse> call = movieDataService.getPopularMovieQueryWithPaging(API_KEY, params.key);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();

                if(movieDBResponse != null && movieDBResponse.getMovies() != null){
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    callback.onResult(movies, params.key+1);
                }

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }
}
