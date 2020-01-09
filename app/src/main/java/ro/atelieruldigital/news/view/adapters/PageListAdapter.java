package ro.atelieruldigital.news.view.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.Article;
import timber.log.Timber;

public class PageListAdapter extends
        RecyclerView.Adapter<PageListAdapter.PageViewHolder>{

    class PageViewHolder extends RecyclerView.ViewHolder {

//        private final TextView articleTopIdItemView;
        private final TextView articleTitleItemView;
        private final TextView articleDescriptionItemView;
        private final ImageView articleImageItemView;
        private final TextView articlePublishedAtItemView;
        private final TextView articleContentItemView;
        private final TextView readMoreItemView;

        private PageViewHolder(View itemView) {
            super(itemView);

//            articleTopIdItemView = itemView.findViewById(R.id.top_id);
            articleTitleItemView = itemView.findViewById(R.id.articletitle_textview);
            articleDescriptionItemView = itemView.findViewById(R.id.articledescription_textview);
            articleImageItemView = itemView.findViewById(R.id.articleimage_textview);
            articlePublishedAtItemView = itemView.findViewById(R.id.articlepublishedat_textview);
            articleContentItemView = itemView.findViewById(R.id.articlecontent_textview);
            readMoreItemView = itemView.findViewById(R.id.readmore_textview);

            readMoreItemView.setOnClickListener( v -> {

                Article clickedItem = mArticles.get(getAdapterPosition());
                String articleUrl = clickedItem.getUrl();
                if(!articleUrl.isEmpty()) {
                    Intent readMoreIntent = new Intent(Intent.ACTION_VIEW);
                    readMoreIntent.setData(Uri.parse(articleUrl));
                    v.getContext().startActivity(readMoreIntent);
                }
            });
        }
    }
//
//    private void onItemClicked(Article article, int position){
//    }

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
            holder.articleDescriptionItemView.setText(current.getDescription());
            holder.articlePublishedAtItemView.setText(current.getPublishedAt());
            holder.articleContentItemView.setText(current.getContent());

            String imageUrl = current.getUrlToImage();

            if (imageUrl != null && !imageUrl.isEmpty()) {

                Picasso.with(holder.itemView.getContext())
                        .load(imageUrl)
                        .fit()
                        .centerInside()
                        .into(holder.articleImageItemView);
            } else {

                Timber.d("IMAGE NONE");
                Picasso.with(holder.itemView.getContext())
                        .load(R.drawable.ic_launcher_background)
                        .fit()
                        .centerInside()
                        .into(holder.articleImageItemView);
            }
//
//            if(current.getTop_id() > 0) {
//
//                holder.articleTopIdItemView.setText(String.valueOf(current.getPage()));
//
//            } else {
//
//                holder.articleTopIdItemView.setText("");
//            }
        }
    }

    public void addArticles(List<Article> articles){

        mArticles.addAll(articles);
        notifyItemRangeInserted(mArticles.size(), articles.size() - 1);

    }

    public void replaceArticles(List<Article> articles){

        int size = articles.size();
        int size1 = mArticles.size();

        Timber.d("SIZE " + size + " " + size1);

        for(int i = 0; i < size; i++) {

            if(size1 - i - 1 >= 0) {
                mArticles.set(size1 - i - 1, articles.get(size - i - 1));
                notifyItemChanged(size1 - i - 1);
            }
        }

        for(int i = 0; i < mArticles.size(); i++) {

            if(mArticles.get(i).getPage() == 0) {

                Timber.d("REMOVED");
                notifyItemRemoved(i);
                mArticles.remove(i);
                i--;
            }
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
