package ro.atelieruldigital.news.view.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.webservice.EverythingQuerry;
import ro.atelieruldigital.news.model.webservice.NewsQuerry;
import ro.atelieruldigital.news.model.webservice.TopHeadlinesQuerry;
import ro.atelieruldigital.news.view.adapters.ScreenSlidePagerAdapter;
import ro.atelieruldigital.news.view.fragments.EverythingPageFragment;
import ro.atelieruldigital.news.view.fragments.TopHeadlinesPageFragment;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;
import timber.log.Timber;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    private BottomNavigationView bottomNavigationView;

    private EditText mEditTextSearchBar;
    private TextView mTextViewTitleBar;
    private ImageButton mImageButtonSearch;
    private ImageButton mImageButtonClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);

        viewPager.setAdapter(pagerAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_top:

                            viewPager.setCurrentItem(0);
                            return true;
                        case R.id.action_everything:

                            viewPager.setCurrentItem(1);
                            return true;
                        case R.id.action_sources:

                            viewPager.setCurrentItem(2);
                            return true;
                    }
                    return false;
                });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                switch (position) {

                    case 0:

                        bottomNavigationView.setSelectedItemId(R.id.action_top);
                        mTextViewTitleBar.setText("Headlines");
                        break;
                    case 1:

                        bottomNavigationView.setSelectedItemId(R.id.action_everything);
                        mTextViewTitleBar.setText("Everything");
                        break;
                    case 2:

                        bottomNavigationView.setSelectedItemId(R.id.action_sources);
                        mTextViewTitleBar.setText("Sources");
                        break;
                }
            }
        });

        mEditTextSearchBar.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {

                String searchText = textView.getText().toString();

                NewsQuerry newsQuerry;

                switch (viewPager.getCurrentItem()) {

                    case 0:

                        newsQuerry = new TopHeadlinesQuerry("", "", "", searchText,
                                10, 1);
                        TopHeadlinesPageFragment.mNewsViewModel.setCurrentQuerry(newsQuerry);

                        break;
                    case 1:

                        newsQuerry = new EverythingQuerry(searchText, "", "",
                                "", "", "", "", "", "",
                                10, 1);
                        EverythingPageFragment.mNewsViewModel.setCurrentQuerry(newsQuerry);
                        break;
                    case 2:

                        break;
                }

                resetBar(textView);
                return true;
            }
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

            resetBar(mEditTextSearchBar);
        }
    }

    private void initView() {

        bottomNavigationView = findViewById(R.id.navigation_menu);
        mTextViewTitleBar = findViewById(R.id.title_bar);
        mEditTextSearchBar = findViewById(R.id.search_bar);
        mImageButtonSearch = findViewById(R.id.search_button);
        mImageButtonClose = findViewById(R.id.close_button);
    }

    public void searchArticle(View view) {

        mTextViewTitleBar.setVisibility(View.GONE);
        mEditTextSearchBar.setVisibility(View.VISIBLE);
        mImageButtonSearch.setVisibility(View.GONE);
        mImageButtonClose.setVisibility(View.VISIBLE);

        mEditTextSearchBar.requestFocus();
        mEditTextSearchBar.setFocusableInTouchMode(true);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditTextSearchBar, InputMethodManager.SHOW_FORCED);
    }

    public void stopSearchingArticle(View view) {

        resetBar(view);
    }

    private void resetBar(View view) {

        mTextViewTitleBar.setVisibility(View.VISIBLE);
        mEditTextSearchBar.setVisibility(View.GONE);
        mEditTextSearchBar.setText("");
        mImageButtonSearch.setVisibility(View.VISIBLE);
        mImageButtonClose.setVisibility(View.GONE);

        InputMethodManager imm = (InputMethodManager)view.getContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

