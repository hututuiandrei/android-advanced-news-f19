package ro.atelieruldigital.news.view.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.lang.reflect.Field;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.NewsQuerry;
import ro.atelieruldigital.news.utils.DataSingleton;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment{


    public static NewsViewModel mNewsViewModel;

    private MaterialSpinner dropdownCountries;
    private MaterialSpinner dropdownCategories;
    private EditText editTextSources;

    private String selectedCountry = "";
    private String selectedCategory = "";

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_filter, container, false);

        initView(v);

        mNewsViewModel = new NewsViewModel(getActivity().getApplication());

        String[] items;

        items = DataSingleton.getInstance().getCountryData();
        ArrayAdapter<String> adapterCountries = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdownCountries.setAdapter(adapterCountries);

        dropdownCountries.setOnItemSelectedListener(
                (MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> {

                selectedCountry = item;
        });

        items = DataSingleton.getInstance().getCategoryData();
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdownCategories.setAdapter(adapterCategories);

        dropdownCategories.setOnItemSelectedListener(
                (MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> {

                    selectedCategory = item;
                });

        return v;

    }

    private void initView(View view) {

        dropdownCountries = view.findViewById(R.id.spinner_country);
        dropdownCategories = view.findViewById(R.id.spinner_category);
        editTextSources = view.findViewById(R.id.edit_text_sources);
    }


    public void closeFilterArticle(View view) {
    }

    public void applyFilters(View view) {

        NewsQuerry newsQuerry = mNewsViewModel.getQuerryObservable("TopHeadlinesQuerry").getValue();

        newsQuerry.setNewCategory(selectedCategory);
        newsQuerry.setNewCountry(selectedCountry);
        newsQuerry.setNewSources(editTextSources.getText().toString());

        mNewsViewModel.setCurrentQuerry("TopHeadlinesQuerry", newsQuerry);
    }
}
