package ro.atelieruldigital.news.view.fragments;


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
    private boolean isLoading = false;
    private SwipeRefreshLayout swipeRefresh;

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

        Timber.d("CREATED");

        initView();

        swipeRefresh.setOnRefreshListener(this);

        mNewsViewModel = new NewsViewModel(getActivity().getApplication());
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

                int offset = 1;

                if(lastVisibleItem + 1 == lastItem - offset) {

                    if(!isLoading) {

                        Timber.d("BOTTOM");
                        currentPage++;
                        startQuerry(currentPage);
                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Timber.d("RESUMED");

        mNewsViewModel.getNewsObservableEvery().observe(getViewLifecycleOwner(), articles -> {

            if(!articles.isEmpty()) {
                adapter.addArticles(articles);
                isLoading = false;
                swipeRefresh.setRefreshing(false);
            }
        });

        startQuerry(currentPage);
    }

    private void startQuerry(int page) {

        NewsQuerry newsQuerry = new EverythingQuerry("Romania", "", "",
                "", "", "", "", "", "",
                10, page);

        mNewsViewModel.syncNews(newsQuerry);
    }

    private void initView() {

        recyclerView = getView().findViewById(R.id.every_news_recycler_view);
        swipeRefresh = getView().findViewById(R.id.swipe_refresh);
    }

    @Override
    public void onRefresh() {

        adapter.clear();
        mNewsViewModel.clearCache("EverythingQuerry");
        currentPage = 1;
        startQuerry(currentPage);
    }
}