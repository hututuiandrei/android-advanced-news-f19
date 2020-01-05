package ro.atelieruldigital.news.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.Article;

public class PageListAdapter extends
        RecyclerView.Adapter<PageListAdapter.PageViewHolder>{

    class PageViewHolder extends RecyclerView.ViewHolder {

        private final TextView articleTopIdItemView;
        private final TextView articleTitleItemView;
        private final TextView articleAuthorItemView;

        private PageViewHolder(View itemView) {
            super(itemView);

            articleTopIdItemView = itemView.findViewById(R.id.top_id);
            articleTitleItemView = itemView.findViewById(R.id.articletitle_every_textview);
            articleAuthorItemView = itemView.findViewById(R.id.articleauthor_every_textview);
        }
    }

    private List<Article> mArticles; // Cached copy of words

    public PageListAdapter(List<Article> mArticles) {
        this.mArticles = mArticles;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recyclerview_article, parent, false);
        return new PageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {
        if (mArticles != null) {
            Article current = mArticles.get(position);

            holder.articleTitleItemView.setText(current.getTitle());
            holder.articleAuthorItemView.setText(current.getAuthor());
            holder.articleTopIdItemView.setText(String.valueOf(current.getPage()));
        }
    }

    public void addArticles(List<Article> articles){

        mArticles.addAll(articles);
        notifyItemRangeInserted(mArticles.size(), articles.size() - 1);

    }

    public void replaceArticles(List<Article> articles){

        int size = articles.size();
        int size1 = mArticles.size();

        for(int i = 0; i < size; i++) {

            mArticles.set(size1 - i - 1, articles.get(size - i - 1));
            notifyItemChanged(size1 - i - 1);
        }
    }

    public void clear(){

        mArticles.clear();
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mArticles != null)
            return mArticles.size();
        else return 0;
    }
}
