package ro.atelieruldigital.news.model.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ro.atelieruldigital.news.model.Article;

@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Article> articles);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Article article);

    @Query("DELETE FROM article_table WHERE type=:type AND page=:page")
    void deleteAll(String type, int page);

    @Query("DELETE FROM article_table WHERE type=:type")
    void deleteAll(String type);

    @Query("SELECT * FROM article_table WHERE type=:type")
    LiveData<List<Article>> getAllArticles(String type);

    @Query("SELECT * FROM article_table WHERE type=:type AND page=:page")
    List<Article> getArticles(String type, int page);
}
