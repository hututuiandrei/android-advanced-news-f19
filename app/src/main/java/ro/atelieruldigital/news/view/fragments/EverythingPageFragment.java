package ro.atelieruldigital.news.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.webservice.EverythingQuerry;
import ro.atelieruldigital.news.view.adapters.EverythingPageListAdapter;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class EverythingPageFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsViewModel mNewsViewModel;

    public EverythingPageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (ViewGroup) inflater.inflate(
                R.layout.fragment_everything_page, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Timber.d("CREATED");

        initView();
        mNewsViewModel = new NewsViewModel(getActivity().getApplication());
    }

    @Override
    public void onResume() {
        super.onResume();

        Timber.d("RESUMED");

        final EverythingPageListAdapter adapter = new EverythingPageListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mNewsViewModel.getNewsObservableEvery().observe(getViewLifecycleOwner(), adapter::setArticles);
    }

    private void initView() {

        recyclerView = (RecyclerView) getView().findViewById(R.id.every_news_recycler_view);
    }

}
