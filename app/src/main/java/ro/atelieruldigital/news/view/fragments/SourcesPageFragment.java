package ro.atelieruldigital.news.view.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ro.atelieruldigital.news.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesPageFragment extends Fragment {


    public SourcesPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sources_page, container, false);
    }

}
