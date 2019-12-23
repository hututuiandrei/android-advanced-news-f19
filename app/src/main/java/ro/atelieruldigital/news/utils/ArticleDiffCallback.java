package ro.atelieruldigital.news.utils;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import ro.atelieruldigital.news.model.Article;

public class ArticleDiffCallback extends DiffUtil.Callback {

    private List<Article> oldArticle;
    private List<Article> newArticle;

    public ArticleDiffCallback(List<Article> oldArticle, List<Article> newArticle) {
        this.oldArticle = oldArticle;
        this.newArticle = newArticle;
    }

    @Override
    public int getOldListSize() {
        return oldArticle.size();
    }

    @Override
    public int getNewListSize() {
        return newArticle.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldArticle.get(oldItemPosition).getTitle().equals(newArticle.get(newItemPosition).getTitle());

    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldArticle.get(oldItemPosition).getTitle().equals(newArticle.get(newItemPosition).getTitle()) &&
                oldArticle.get(oldItemPosition).getAuthor().equals(newArticle.get(newItemPosition).getAuthor());
    }
}
