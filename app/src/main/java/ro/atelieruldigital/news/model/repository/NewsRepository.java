package ro.atelieruldigital.news.model.repository;

import android.app.Application;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.Article;
import ro.atelieruldigital.news.model.News;
import ro.atelieruldigital.news.model.database.ArticleDao;
import ro.atelieruldigital.news.model.database.ArticleRoomDatabase;
import ro.atelieruldigital.news.model.webservice.EverythingQuerry;
import ro.atelieruldigital.news.model.webservice.NewsQuerry;
import ro.atelieruldigital.news.model.webservice.NewsWebService;
import ro.atelieruldigital.news.model.webservice.TopHeadlinesQuerry;
import timber.log.Timber;

public class NewsRepository {

    private NewsWebService newsWebService;
    private ArticleDao articleDao;

    private MutableLiveData<List<Article>> articleListEvery;
    private MutableLiveData<List<Article>> articleListTop;

    private MutableLiveData<NewsQuerry> currentQuerry;

    public final static String EVTAG = "EverythingQuerry";
    public final static String TTAG = "TopHeadlinesQuerry";

    public NewsRepository(Application application) {

        newsWebService = new NewsWebService();

        ArticleRoomDatabase db = ArticleRoomDatabase.getDatabase(application);
        articleDao = db.wordDao();

        articleListEvery = new MutableLiveData<>();
        articleListTop = new MutableLiveData<>();

        currentQuerry = new MutableLiveData<>();
    }

    public void setCurrQuerry(NewsQuerry newsQuerry) {

        currentQuerry.setValue(newsQuerry);
    }

    public LiveData<NewsQuerry> getCurrQuerry() {

        return currentQuerry;
    }

    public LiveData<List<Article>> getTopNews() {

        return articleListTop;
    }

    public LiveData<List<Article>> getEveryNews() {

        return articleListEvery;
    }

    public void getCachedNews(NewsQuerry querry) {

        String type = querry.getClass().getSimpleName();
        int page = getPageFromQuerry(querry);

        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {

            List<Article> articles = articleDao.getArticles(type, page);

            switch (type) {

                case EVTAG : {

                    articleListEvery.postValue(articles);
                    break;
                }

                case TTAG : {

                    articleListTop.postValue(articles);
                    break;
                }
            }
        });
    }

    public void getRemoteNews(NewsQuerry querry) {

        String type = querry.getClass().getSimpleName();
        int page = getPageFromQuerry(querry);

        newsWebService.queryArticles(querry).enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {

                if(response.isSuccessful()) {

                    Timber.d("SUCC");

                    List<Article> articles = response.body().getArticleList();

                    ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {

                        articleDao.deleteAll(type, page);

                        for (Article article : articles) {

                            article.setType(type);
                            article.setPage(page);
                        }
                        articleDao.insertAll(articles);

                        switch (type) {

                            case EVTAG : {

                                articleListEvery.postValue(articleDao.getArticles(type, page));
                                //articleListEvery.postValue(articles);
                                break;
                            }

                            case TTAG : {

                                articleListTop.postValue(articleDao.getArticles(type, page));
                                //articleListTop.postValue(articles);
                                break;
                            }
                        }
                    });

                } else {

                    Timber.d("NO SUCCESSFUL");
                }

            }

            @Override
            public void onFailure(@NotNull Call<News> call, @NotNull Throwable t) {

                Timber.d("FAIL");
            }
        });
    }

    private int getPageFromQuerry(NewsQuerry querry) {

        String type = querry.getClass().getSimpleName();
        int page = 0;

        switch (type) {

            case EVTAG : {

                page = ((EverythingQuerry) querry).getPage();
                break;
            }

            case TTAG : {

                page =((TopHeadlinesQuerry) querry).getPage();
                break;
            }
        }
        return page;
    }

    public void clearCache(String type) {

        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {

            articleDao.deleteAll(type);
        });
    }
}
