package ro.atelieruldigital.news.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("articles")
    List<Article> articleList;

    public APIResponse(String status, int totalResults, List<Article> articleList) {
        this.status = status;
        this.totalResults = totalResults;
        this.articleList = articleList;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
