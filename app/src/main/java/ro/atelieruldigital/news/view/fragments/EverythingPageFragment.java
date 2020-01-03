package ro.atelieruldigital.news.view.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.Article;
import ro.atelieruldigital.news.model.webservice.EverythingQuerry;
import ro.atelieruldigital.news.model.webservice.NewsQuerry;
import ro.atelieruldigital.news.view.adapters.EverythingPageListAdapter;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class EverythingPageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    public static NewsViewModel mNewsViewModel;
    private EverythingPageListAdapter adapter;

    private int currentPage = 1;
    private int lastPage = 10;
    private boolean isLoading = false;
    private SwipeRefreshLayout swipeRefresh;

    private Article emptyArticle = new Article(0, "", 0, null, "", "",
            "", "", "", "", "");

    private List<Article> emptyArticleList = new ArrayList<>();

    private NewsQuerry currentQuerry = null;

    public EverythingPageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (ViewGroup) inflater.inflate(
                R.layout.fragment_everything_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i = 0; i < 10; i++) {

            emptyArticleList.add(emptyArticle);
        }

        Timber.d("CREATED");

        initView();

        swipeRefresh.setOnRefreshListener(this);

        mNewsViewModel = new NewsViewModel(getActivity().getApplication());

        currentQuerry = new EverythingQuerry("Romania", "", "",
                "", "", "", "", "", "",
                10, currentPage);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EverythingPageListAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                int lastItem = layoutManager.getItemCount();

                int offset = 0;

                Timber.d(lastVisibleItem + " " + lastItem);

                if(lastVisibleItem + 1 == lastItem - offset) {

                    if(!isLoading && currentPage < lastPage) {

                        Timber.d("BOTTOM");

                        recyclerView.post(() -> { adapter.addArticles(emptyArticleList); });
                        currentPage++;
                        startQuerry(currentPage);
                        isLoading = true;
                    }
                }
            }
        });

        // Observe changes in article list
        mNewsViewModel.getNewsObservableEvery().observe(getViewLifecycleOwner(), articles -> {

            Timber.d("CHANGED");

            if(!articles.isEmpty()) {
                recyclerView.post(() -> { adapter.replaceArticles(articles); });
                isLoading = false;
            }
            swipeRefresh.setRefreshing(false);
        });

        // Observe changes in current querry. This is triggered by searching for new articles in
        // Home Activity
        mNewsViewModel.getQuerryObservable().observe(getViewLifecycleOwner(), querry -> {

            currentQuerry = querry;
            onRefresh();
        });

        recyclerView.post(() -> adapter.addArticles(emptyArticleList));
        startQuerry(currentPage);
    }

    private void startQuerry(int page) {

        NewsQuerry newsQuerry = currentQuerry;
        ((EverythingQuerry) newsQuerry).setPage(page);

        mNewsViewModel.syncNews(newsQuerry);
    }

    private void initView() {

        recyclerView = getView().findViewById(R.id.every_news_recycler_view);
        swipeRefresh = getView().findViewById(R.id.swipe_refresh);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onRefresh() {

        if(isNetworkConnected()) {
            isLoading = true;
            adapter.clear();
            mNewsViewModel.clearCache("EverythingQuerry");
            currentPage = 1;
            recyclerView.post(() -> adapter.addArticles(emptyArticleList));
            startQuerry(currentPage);
        } else {

            swipeRefresh.setRefreshing(false);
        }
    }
}