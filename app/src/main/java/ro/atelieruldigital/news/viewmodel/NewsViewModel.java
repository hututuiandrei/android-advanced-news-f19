package ro.atelieruldigital.news.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import ro.atelieruldigital.news.model.News;
import ro.atelieruldigital.news.model.repository.NewsRepository;

public class NewsViewModel extends AndroidViewModel {

    private final LiveData<News> newsObservable;
    private NewsRepository mRepository;

    public NewsViewModel(@NonNull Application application, final String searchString) {
        super(application);

        mRepository = new NewsRepository(application);
        newsObservable = mRepository.getNews(searchString);
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<News> getnewsObservable() {
        return newsObservable;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final String searchString;

        public Factory(@NonNull Application application, String searchString) {
            this.application = application;
            this.searchString = searchString;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new NewsViewModel(application, searchString);
        }
    }
}
