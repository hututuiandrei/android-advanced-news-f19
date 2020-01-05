package ro.atelieruldigital.news.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.model.Article;
import ro.atelieruldigital.news.model.repository.NewsRepository;
import ro.atelieruldigital.news.model.NewsQuerry;

public class NewsViewModel extends AndroidViewModel {

    private final LiveData<List<Article>> newsObservableTop;
    private final LiveData<List<Article>> newsObservableEvery;
    private final LiveData<NewsQuerry> querryObservableTop;
    private final LiveData<NewsQuerry> querryObservableEvery;
    private NewsRepository mRepository;

    public NewsViewModel(@NonNull Application application) {
        super(application);

        mRepository = this.<App>getApplication().getNewsRepository();
        newsObservableTop = mRepository.getTopNews();
        newsObservableEvery = mRepository.getEveryNews();
        querryObservableTop = mRepository.getCurrQuerryTop();
        querryObservableEvery = mRepository.getCurrQuerryEvery();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */

    public LiveData<List<Article>> getNewsObservable(String tag) {

        switch (tag) {

            case "EverythingQuerry": return newsObservableEvery;
            case "TopHeadlinesQuerry": return newsObservableTop;
            default: return null;
        }
    }

    public LiveData<NewsQuerry> getQuerryObservable(String tag) {

        switch (tag) {

            case "EverythingQuerry": return querryObservableEvery;
            case "TopHeadlinesQuerry": return querryObservableTop;
            default: return null;
        }
    }


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

    public void setCurrentQuerry(String tag, NewsQuerry newsQuerry) {

        switch (tag) {

            case "EverythingQuerry": mRepository.setCurrQuerryEvery(newsQuerry); break;
            case "TopHeadlinesQuerry": mRepository.setCurrQuerryTop(newsQuerry); break;
        }
    }

}
