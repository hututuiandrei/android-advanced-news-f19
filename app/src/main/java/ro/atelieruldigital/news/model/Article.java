package ro.atelieruldigital.news.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
public class Article {

    @PrimaryKey(autoGenerate = true)
    private int top_id;

    @Embedded
    @SerializedName("source")
    private Source source;
    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("content")
    private String content;

    private String type;

    private int page;

    public static class Source {

        @SerializedName("id")
        String id;
        @SerializedName("name")
        String name;

        public Source(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public Article(int top_id, String type, int page, Source source, String author, String title,
                   String description, String url, String urlToImage, String publishedAt,
                   String content) {

        this.top_id = top_id;
        this.type = type;
        this.page = page;
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public int getTop_id() { return top_id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Source getSource() {
        return source;
    }

    @NotNull
    public String getAuthor() {
        return author;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    @NotNull
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                '}';
    }
}