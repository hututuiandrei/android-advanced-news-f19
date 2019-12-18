package ro.atelieruldigital.news.model.webservice;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.News;

public class NewsWebService {

    private final String API_KEY = App.getContext().getResources().getString(R.string.news_api_key);
    private NewsApi newsApi;

    public NewsWebService() {

        newsApi = NewsApiClient.getClient().create(NewsApi.class);
    }

    public Call<News> queryArticles(String searchString) {
        return newsApi.queryArticles(searchString, API_KEY);
    }

    private interface NewsApi {

        @GET("/v2/everything")
        Call<News> queryArticles(@Query("q") String searchString,
                                 @Query("apiKey") String apiKey);
    }
}

class NewsApiClient {

    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        String BASE_URL = App.getContext().getResources().getString(R.string.base_url);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}