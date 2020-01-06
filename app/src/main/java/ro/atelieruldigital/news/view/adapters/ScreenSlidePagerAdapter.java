package ro.atelieruldigital.news.view.adapters;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import ro.atelieruldigital.news.model.EverythingQuerry;
import ro.atelieruldigital.news.model.NewsQuerry;
import ro.atelieruldigital.news.model.TopHeadlinesQuerry;
import ro.atelieruldigital.news.view.fragments.ArticlePageFragment;
import ro.atelieruldigital.news.view.fragments.SourcesPageFragment;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;
    public static final String TOPTAG = "TopHeadlinesQuerry";
    public static final String EVERYTAG = "EverythingQuerry";
    public static final String SOURCETAG = "SourcesQuerry";
    public static final String KEY = "key";

    public ScreenSlidePagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {

            case 0: {

                Bundle bundle = new Bundle();
                bundle.putString(KEY, TOPTAG);
                ArticlePageFragment articlePageFragment = new ArticlePageFragment();
                articlePageFragment.setArguments(bundle);
                return articlePageFragment;
            }
            case 1: {

                Bundle bundle = new Bundle();
                bundle.putString(KEY, EVERYTAG);
                ArticlePageFragment articlePageFragment = new ArticlePageFragment();
                articlePageFragment.setArguments(bundle);
                return articlePageFragment;
            }
            case 2:
                return new SourcesPageFragment();
            default:
                return null;
        }
    }

    public static NewsQuerry getInitialQuerry(String tag) {

        switch (tag) {

            case TOPTAG: return new TopHeadlinesQuerry(null, null, null,
                    null, 10, 1);


            case EVERYTAG: return new EverythingQuerry(null, null, null,
                    null, null, null, null, null, null,
                    10, 1);

            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
