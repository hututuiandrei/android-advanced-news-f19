package ro.atelieruldigital.news.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.APIResponse;
import ro.atelieruldigital.news.model.webservice.NewsWebService;

public class NewsRepository {

    private NewsWebService newsWebService;

    public NewsRepository(Application application) {

        newsWebService = new NewsWebService();
    }

    public LiveData<APIResponse> getResponse(String searchString) {

        final MutableLiveData<APIResponse> data = new MutableLiveData<>();

        newsWebService.queryArticles(searchString).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

                // TODO : error handling
            }
        });

        return data;
    }
}
