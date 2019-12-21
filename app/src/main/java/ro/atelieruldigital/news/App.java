package ro.atelieruldigital.news;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;

import ro.atelieruldigital.news.model.repository.NewsRepository;
import ro.atelieruldigital.news.model.webservice.NewsQuerry;
import timber.log.Timber;

public class App extends Application {

    private static Context mContext;
    private NewsRepository newsRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

        AndroidThreeTen.init(this);

        newsRepository = new NewsRepository(this);

        //NewsQuerry.TopHeadlines newsQuerry = new NewsQuerry.TopHeadlines("", "", "", "", 0, 0, "");


        //newsRepository.syncNews("Pizza Hut");

        Timber.d("App has initialized...");
    }
    public static Context getContext(){
        return mContext;
    }

    public NewsRepository getNewsRepository() {
        return newsRepository;
    }
}
