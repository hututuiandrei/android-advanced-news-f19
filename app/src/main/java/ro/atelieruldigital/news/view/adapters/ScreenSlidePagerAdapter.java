package ro.atelieruldigital.news.view.adapters;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import ro.atelieruldigital.news.view.fragments.EverythingPageFragment;
import ro.atelieruldigital.news.view.fragments.TopHeadlinesPageFragment;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 2;

    public ScreenSlidePagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {

            case 0:
                return new TopHeadlinesPageFragment();
            case 1:
                return new EverythingPageFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
