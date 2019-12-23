package ro.atelieruldigital.news.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.webservice.TopHeadlinesQuerry;
import ro.atelieruldigital.news.view.adapters.TopPageListAdapter;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopHeadlinesPageFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsViewModel mNewsViewModel;

    public TopHeadlinesPageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (ViewGroup) inflater.inflate(
                R.layout.fragment_top_headlines_page, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        mNewsViewModel = new NewsViewModel(getActivity().getApplication());
    }

    @Override
    public void onResume() {
        super.onResume();

        final TopPageListAdapter adapter = new TopPageListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mNewsViewModel.getnewsObservableTop().observe(getViewLifecycleOwner(), adapter::setArticles);

        mNewsViewModel.syncNews(new TopHeadlinesQuerry("ro", "", "", "",
                5, 1));
    }

    private void initView() {

        recyclerView = (RecyclerView) getView().findViewById(R.id.top_news_recycler_view);
    }
}
