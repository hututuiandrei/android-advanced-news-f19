package ro.atelieruldigital.news.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.News;
import ro.atelieruldigital.news.model.webservice.NewsWebService;

public class NewsRepository {

    private NewsWebService newsWebService;

    public NewsRepository(Application application) {

        newsWebService = new NewsWebService();
    }

    public LiveData<News> getNews(String searchString) {

        final MutableLiveData<News> data = new MutableLiveData<>();

        newsWebService.queryArticles(searchString).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                // TODO : error handling
            }
        });

        return data;
    }
}
