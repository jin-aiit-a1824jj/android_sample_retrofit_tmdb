package a1824jj.jp.ac.aiit.tmdb_sampel.viewmodel;

import android.app.Application;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import a1824jj.jp.ac.aiit.tmdb_sampel.model.Movie;
import a1824jj.jp.ac.aiit.tmdb_sampel.model.MovieDataSource;
import a1824jj.jp.ac.aiit.tmdb_sampel.model.MovieDataSourceFactory;
import a1824jj.jp.ac.aiit.tmdb_sampel.model.MovieRepository;
import a1824jj.jp.ac.aiit.tmdb_sampel.service.MovieDataService;
import a1824jj.jp.ac.aiit.tmdb_sampel.service.RetrofitInstance;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviesPagedList;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);

        MovieDataService movieDataService = RetrofitInstance.getService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService, application);
        movieDataSourceLiveData = factory.getMutableLiveData();


        PagedList.Config config
                = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        moviesPagedList = (new LivePagedListBuilder<Long, Movie>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<List<Movie>> getAllMovie(){
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
