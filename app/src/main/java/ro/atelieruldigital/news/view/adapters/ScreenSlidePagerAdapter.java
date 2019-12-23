package ro.atelieruldigital.news.view.adapters;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import ro.atelieruldigital.news.view.fragments.EverythingPageFragment;
import ro.atelieruldigital.news.view.fragments.TopHeadlinesPageFragment;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {

    private NewsViewModel mNewsViewModel;
    private static final int NUM_PAGES = 3;

    public ScreenSlidePagerAdapter(FragmentActivity fa, NewsViewModel mNewsViewModel) {
        super(fa);

        this.mNewsViewModel = mNewsViewModel;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {

            case 0:
                return new TopHeadlinesPageFragment();
            case 1:
                return new EverythingPageFragment();
            case 2:
                return new TopHeadlinesPageFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
