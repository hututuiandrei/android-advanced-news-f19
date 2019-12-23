package ro.atelieruldigital.news.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.Article;

public class EverythingPageListAdapter extends
        RecyclerView.Adapter<EverythingPageListAdapter.EverythingPageViewHolder>{

    class EverythingPageViewHolder extends RecyclerView.ViewHolder {
        private final TextView articleTitleItemView;
        private final TextView articleAuthorItemView;

        private EverythingPageViewHolder(View itemView) {
            super(itemView);
            articleTitleItemView = itemView.findViewById(R.id.articletitle_every_textview);
            articleAuthorItemView = itemView.findViewById(R.id.articleauthor_every_textview);
        }
    }

    private final LayoutInflater mInflater;
    private List<Article> mArticles; // Cached copy of words

    public EverythingPageListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public EverythingPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_article, parent, false);
        return new EverythingPageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EverythingPageViewHolder holder, int position) {
        if (mArticles != null) {
            Article current = mArticles.get(position);

            holder.articleTitleItemView.setText(current.getTitle());
            holder.articleAuthorItemView.setText(current.getAuthor());
        }
    }

    public void setArticles(List<Article> articles){

        mArticles = articles;
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
