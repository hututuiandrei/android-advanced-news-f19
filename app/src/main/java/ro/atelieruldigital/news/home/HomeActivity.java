package ro.atelieruldigital.news.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.viewmodel.NewsViewModel;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextView;
    private NewsViewModel mNewsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        NewsViewModel.Factory factory = new NewsViewModel.Factory(
                getApplication(), "Klaus Iohannis");

        mNewsViewModel = new ViewModelProvider(this, factory).get(NewsViewModel.class);

        mNewsViewModel.getnewsObservable().observe(this, news -> {

            mTextView.setText(news.getArticleList().get(0).getTitle());
        });
    }

    private void initView() {

        mTextView = findViewById(R.id.textView);
    }
}
