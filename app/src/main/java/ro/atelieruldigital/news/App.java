package ro.atelieruldigital.news;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;
import timber.log.Timber;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

        AndroidThreeTen.init(this);

        Timber.d("App has initialized...");
    }
    public static Context getContext(){
        return mContext;
    }
}
