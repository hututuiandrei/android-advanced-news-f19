package ro.atelieruldigital.news.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import androidx.fragment.app.Fragment;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.NewsQuerry;
import ro.atelieruldigital.news.utils.DataSingleton;
import ro.atelieruldigital.news.view.adapters.ScreenSlidePagerAdapter;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;

import static ro.atelieruldigital.news.view.adapters.ScreenSlidePagerAdapter.EVERYTAG;
import static ro.atelieruldigital.news.view.adapters.ScreenSlidePagerAdapter.KEY;
import static ro.atelieruldigital.news.view.adapters.ScreenSlidePagerAdapter.TOPTAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment{


    private static NewsViewModel mNewsViewModel;

    private MaterialSpinner dropdownCountries;
    private MaterialSpinner dropdownCategories;
    private MaterialSpinner dropdownLanguages;
    private MaterialSpinner dropdownSortBy;

    private EditText editTextSources;
    private EditText editTextInTitle;
    private EditText editTextDomains;
    private EditText editTextExcludeDomains;
    private EditText editTextFrom;
    private EditText editTextTo;

    private TextView textViewCountry;
    private TextView textViewCategory;
    private TextView textViewLanguage;
    private TextView textViewSortBy;

    private String TAG = null;

    public FilterFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_filter, container, false);

        initView(v);

        if (getArguments() != null) {
            TAG = getArguments().getString(KEY);
        }

        mNewsViewModel = new NewsViewModel(getActivity().getApplication());

        String[] items;

        items = DataSingleton.getInstance().getCountryData();
        initMaterialSpinner(dropdownCountries, items);
        items = DataSingleton.getInstance().getCategoryData();
        initMaterialSpinner(dropdownCategories, items);
        items = DataSingleton.getInstance().getLanguageData();
        initMaterialSpinner(dropdownLanguages, items);
        items = DataSingleton.getInstance().getSortByData();
        initMaterialSpinner(dropdownSortBy, items);

        switch (TAG) {

            case EVERYTAG: {

                dropdownCategories.setVisibility(View.GONE);
                textViewCategory.setVisibility(View.GONE);
                dropdownCountries.setVisibility(View.GONE);
                textViewCountry.setVisibility(View.GONE);
                break;
            }

            case TOPTAG: {

                dropdownLanguages.setVisibility(View.GONE);
                textViewLanguage.setVisibility(View.GONE);
                dropdownSortBy.setVisibility(View.GONE);
                textViewSortBy.setVisibility(View.GONE);
                editTextInTitle.setVisibility(View.GONE);
                editTextDomains.setVisibility(View.GONE);
                editTextExcludeDomains.setVisibility(View.GONE);
                editTextFrom.setVisibility(View.GONE);
                editTextTo.setVisibility(View.GONE);
                break;
            }
        }

        return v;

    }

    private void initMaterialSpinner(MaterialSpinner spinner, String[] items) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    private Object getSelectedItem(MaterialSpinner spinner) {

        return spinner.getItems().get(spinner.getSelectedIndex());
    }

    private void initView(View view) {

        dropdownCountries = view.findViewById(R.id.spinner_country);
        dropdownCategories = view.findViewById(R.id.spinner_category);
        dropdownLanguages = view.findViewById(R.id.spinner_language);
        dropdownSortBy = view.findViewById(R.id.spinner_sort_by);

        editTextSources = view.findViewById(R.id.edit_text_sources);
        editTextInTitle = view.findViewById(R.id.edit_text_q_in_title);
        editTextDomains = view.findViewById(R.id.edit_text_domains);
        editTextExcludeDomains = view.findViewById(R.id.edit_text_exclude_domains);
        editTextFrom = view.findViewById(R.id.edit_text_from);
        editTextTo = view.findViewById(R.id.edit_text_to);

        textViewCountry = view.findViewById(R.id.text_view_country);
        textViewCategory = view.findViewById(R.id.text_view_category);
        textViewLanguage = view.findViewById(R.id.text_view_language);
        textViewSortBy = view.findViewById(R.id.text_view_sort_by);
    }


    public void closeFilterArticle(View view) {
    }

    public void applyFilters(View view) {

        NewsQuerry newsQuerry = mNewsViewModel.getQuerryObservable(TAG).getValue();

        if(newsQuerry == null) {

            newsQuerry = ScreenSlidePagerAdapter.getInitialQuerry(TAG);
        }

        String searchSources = editTextSources.getText().toString();
        String searchInTitle = editTextInTitle.getText().toString();
        String searchDomains = editTextDomains.getText().toString();
        String excludeDomains = editTextExcludeDomains.getText().toString();
        String from = editTextFrom.getText().toString();
        String to = editTextTo.getText().toString();

        String selectedCountry = (String) getSelectedItem(dropdownCountries);
        String selectedCategory = (String) getSelectedItem(dropdownCategories);
        String selectedLanguage = (String) getSelectedItem(dropdownLanguages);
        String selectedSortBy = (String) getSelectedItem(dropdownSortBy);

        newsQuerry.setNewCategory(selectedCategory.equals("None") ? null : selectedCategory);
        newsQuerry.setNewCountry(selectedCountry.equals("None") ? null : selectedCountry);
        newsQuerry.setNewLanguage(selectedLanguage.equals("None") ? null : selectedLanguage);
        newsQuerry.setNewSortBy(selectedSortBy.equals("None") ? null : selectedSortBy);

        newsQuerry.setNewSources(searchSources.isEmpty() ? null : searchSources);
        newsQuerry.setNewQInTitle(searchInTitle.isEmpty() ? null : searchInTitle);
        newsQuerry.setNewDomains(searchDomains.isEmpty() ? null : searchDomains);
        newsQuerry.setNewExcludeDomains(excludeDomains.isEmpty() ? null : excludeDomains);
        newsQuerry.setNewFrom(from.isEmpty() ? null : from);
        newsQuerry.setNewTo(to.isEmpty() ? null : to);

        mNewsViewModel.setCurrentQuerry(TAG, newsQuerry);
    }
}
