package ro.atelieruldigital.news.model.repository;

import android.app.Application;

import org.jetbrains.annotations.NotNull;

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

    private LiveData<List<Article>> articleListEvery;
    private LiveData<List<Article>> articleListTop;

    public NewsRepository(Application application) {

        newsWebService = new NewsWebService();

        ArticleRoomDatabase db = ArticleRoomDatabase.getDatabase(application);
        articleDao = db.wordDao();

        articleListTop = articleDao.getArticles("TopHeadlinesQuerry");
        articleListEvery = articleDao.getArticles("EverythingQuerry");
    }

    public LiveData<List<Article>> getTopNews() {

        return articleListTop;
    }

    public LiveData<List<Article>> getEveryNews() {

        return articleListEvery;
    }

    public void getCachedNews(NewsQuerry querry) {

        String type = querry.getClass().getSimpleName();
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {

            if(type.equals("EverythingQuerry")) {
                articleListEvery = articleDao.getArticles(type);
            } else {
                articleListTop = articleDao.getArticles(type);
            }
        });
    }

    public void getRemoteNews(NewsQuerry querry) {

        String type = querry.getClass().getSimpleName();

        newsWebService.queryArticles(querry).enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {

                ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
                    articleDao.deleteAll(type);

                    List<Article> articles = response.body().getArticleList();

                    for(Article article : articles) {

                        article.setType(type);
                    }

                    articleDao.insertAll(articles);

                    Timber.d("SUCC %s", articles.toString());
                });

            }

            @Override
            public void onFailure(@NotNull Call<News> call, @NotNull Throwable t) {

                Timber.d("FAIL");
            }
        });
    }

//    public void clearCache() {
//
//        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
//            articleDao.deleteAll("TopHeadlinesQuerry");
//        });
//    }
}
