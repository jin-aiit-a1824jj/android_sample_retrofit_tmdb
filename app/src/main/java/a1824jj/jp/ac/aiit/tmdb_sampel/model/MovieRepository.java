package a1824jj.jp.ac.aiit.tmdb_sampel.model;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import a1824jj.jp.ac.aiit.tmdb_sampel.service.MovieDataService;
import a1824jj.jp.ac.aiit.tmdb_sampel.service.RetrofitInstance;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static a1824jj.jp.ac.aiit.tmdb_sampel.view.MainActivity.API_KEY;

public class MovieRepository {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();

    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {

        MovieDataService movieDataService = RetrofitInstance.getService();
        Call<MovieDBResponse> call = movieDataService.getPopularMovieQuery(API_KEY);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                if(movieDBResponse != null && movieDBResponse.getMovies() != null){
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });



        return mutableLiveData;
    }


}
