package ro.atelieruldigital.news.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.model.Article;
import ro.atelieruldigital.news.model.repository.NewsRepository;
import ro.atelieruldigital.news.model.webservice.NewsQuerry;

public class NewsViewModel extends AndroidViewModel {

    private final LiveData<List<Article>> newsObservableTop;
    private final LiveData<List<Article>> newsObservableEvery;
    private NewsRepository mRepository;

    private boolean firstSync;

    public NewsViewModel(@NonNull Application application) {
        super(application);

        mRepository = this.<App>getApplication().getNewsRepository();
        newsObservableTop = mRepository.getTopNews();
        newsObservableEvery = mRepository.getEveryNews();
        firstSync = true;
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Article>> getnewsObservableTop() {
        return newsObservableTop;
    }

    public LiveData<List<Article>> getNewsObservableEvery() {
        return newsObservableEvery;
    }

    public void syncNews(NewsQuerry querry) {

        if (firstSync) {
            getCachedNews(querry);
            getRemoteNews(querry);
        } else {
            getCachedNews(querry);
        }
        firstSync = false;
    }

    public void getRemoteNews(NewsQuerry querry) {

        mRepository.getRemoteNews(querry);
    }

    public void getCachedNews(NewsQuerry querry) {

        mRepository.getCachedNews(querry);
    }

//    public void clearCache() {
//
//        mRepository.clearCache();
//    }
}
