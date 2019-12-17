package ro.atelieruldigital.news.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.APIResponse;
import ro.atelieruldigital.news.viewmodel.ResponseViewModel;
import timber.log.Timber;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextView;
    private ResponseViewModel mResponseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        ResponseViewModel.Factory factory = new ResponseViewModel.Factory(
                getApplication(), "Klaus Iohannis");

        mResponseViewModel = new ViewModelProvider(this, factory).get(ResponseViewModel.class);

        mResponseViewModel.getresponseObservable().observe(this, apiResponse -> {

            Timber.d("UI : " + apiResponse.getTotalResults());

            mTextView.setText(apiResponse.getStatus());
        });
    }

    private void initView() {

        mTextView = findViewById(R.id.textView);
    }
}
