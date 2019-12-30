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
import timber.log.Timber;

public class NewsViewModel extends AndroidViewModel {

    private final LiveData<List<Article>> newsObservableTop;
    private final LiveData<List<Article>> newsObservableEvery;
    private final LiveData<NewsQuerry> querryObservable;
    private NewsRepository mRepository;

    public NewsViewModel(@NonNull Application application) {
        super(application);

        mRepository = this.<App>getApplication().getNewsRepository();
        newsObservableTop = mRepository.getTopNews();
        newsObservableEvery = mRepository.getEveryNews();
        querryObservable = mRepository.getCurrQuerry();
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

    public LiveData<NewsQuerry> getQuerryObservable() {return querryObservable; }

    public void syncNews(NewsQuerry querry) {

        getCachedNews(querry);
        getRemoteNews(querry);
    }

    public void getRemoteNews(NewsQuerry querry) {

        mRepository.getRemoteNews(querry);
    }

    public void getCachedNews(NewsQuerry querry) {

        mRepository.getCachedNews(querry);
    }

    public void clearCache(String type) {

        mRepository.clearCache(type);
    }

    public void setCurrentQuerry(NewsQuerry newsQuerry) {

        mRepository.setCurrQuerry(newsQuerry);
    }
}
