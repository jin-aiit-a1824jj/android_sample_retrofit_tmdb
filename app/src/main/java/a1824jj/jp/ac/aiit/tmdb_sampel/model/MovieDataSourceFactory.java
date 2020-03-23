package a1824jj.jp.ac.aiit.tmdb_sampel.model;

import android.app.Application;

import a1824jj.jp.ac.aiit.tmdb_sampel.service.MovieDataService;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataService movieDataService;
    private MovieDataSource movieDataSource;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(movieDataService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
