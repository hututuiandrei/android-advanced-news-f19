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

    public NewsViewModel(@NonNull Application application) {
        super(application);

        mRepository = this.<App>getApplication().getNewsRepository();
        newsObservableTop = mRepository.getNews("TopHeadlinesQuerry");
        newsObservableEvery = mRepository.getNews("EverythingQuerry");
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

        mRepository.syncNews(querry);
    }

    public void clearCache() {

        mRepository.clearCache();
    }
}
