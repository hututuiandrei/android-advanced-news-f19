package ro.atelieruldigital.news.model.repository;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.Article;
import ro.atelieruldigital.news.model.News;
import ro.atelieruldigital.news.model.database.ArticleDao;
import ro.atelieruldigital.news.model.database.ArticleRoomDatabase;
import ro.atelieruldigital.news.model.webservice.NewsQuerry;
import ro.atelieruldigital.news.model.webservice.NewsWebService;
import ro.atelieruldigital.news.model.webservice.TopHeadlinesQuerry;
import timber.log.Timber;

public class NewsRepository {

    private NewsWebService newsWebService;
    private ArticleDao articleDao;

    private LiveData<List<Article>> articleList;

    public NewsRepository(Application application) {

        newsWebService = new NewsWebService();

        ArticleRoomDatabase db = ArticleRoomDatabase.getDatabase(application);
        articleDao = db.wordDao();

        articleList = articleDao.getArticles();
    }

    public LiveData<List<Article>> getNews() {

        return articleList;
    }

    public void syncNews(NewsQuerry querry) {

        newsWebService.queryArticles(querry).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                insertAll(response.body().getArticleList());
                articleList = articleDao.getArticles();

                Timber.d("SUCC %s", response.body().getArticleList().toString());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                Timber.d("FAIL");
                // TODO : error handling
            }
        });
    }

    private void insertAll(List<Article> articles) {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
            articleDao.deleteAll();
            articleDao.insertAll(articles);
        });
    }
}
