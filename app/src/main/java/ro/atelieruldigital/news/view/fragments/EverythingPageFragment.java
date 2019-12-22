package ro.atelieruldigital.news.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.webservice.EverythingQuerry;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EverythingPageFragment extends Fragment {


    private NewsViewModel mNewsViewModel;
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (ViewGroup) inflater.inflate(
                R.layout.fragment_everything_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        mNewsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        mNewsViewModel.getNewsObservableEvery().observe(getViewLifecycleOwner(), articles -> {


            mTextView.setText(articles.toString());
        });

        mNewsViewModel.syncNews(new EverythingQuerry("Pizza Hut", "", "",
                "", "", "", "", "", "", 10,
                1));
    }

    private void initView() {

        mTextView = getView().findViewById(R.id.textviewevery);
    }

}
