package com.example.veeez.feature.map.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;
import com.example.veeez.common.Base;
import com.example.veeez.services.http.map.MapApiInterface;
import com.example.veeez.services.http.map.MapApiInterfaceProvider;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnSearchEventListener {

    private EditText searchEdt;
    private SearchAdapter searchAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView searchResultRv;
    private ImageView backBtn;

    private FrameLayout progressFrameLayout;
    private ProgressBar progressBar;

    private TextView searchErrorTv;

    private Button retryBtn;

    private int titleId;

    private MapApiInterface mapApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       titleId = getIntent().getIntExtra(Base.EXTRA_KEY_ID,0);

        initViews();

        backBtn.setOnClickListener(view->{
            finish();
        });

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>1){
                    search(String.valueOf(charSequence));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initViews() {
        searchEdt = findViewById(R.id.address_search_edt);
        searchAdapter = new SearchAdapter();
        searchResultRv = findViewById(R.id.search_result_rv);

        searchResultRv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        searchResultRv.setAdapter(searchAdapter);
        searchAdapter.setOnSearchEventListener(this::onSearchClick);

        backBtn = findViewById(R.id.search_toolbar_back_btn);

        progressFrameLayout = findViewById(R.id.progress_frame_layout);
        progressBar = findViewById(R.id.progress_indicator);

        searchErrorTv = findViewById(R.id.search_error_tv);

        retryBtn = findViewById(R.id.retry_button);
    }


    private void search(String q){
        if (mapApiInterface ==null){
            mapApiInterface = MapApiInterfaceProvider.getInstance();
        }

         mapApiInterface.search(q,"ir",20,"json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchModel>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        compositeDisposable.add(d);
                        progressFrameLayout.setVisibility(View.VISIBLE);
                        progressBar.setIndeterminate(true);
                        retryBtn.setVisibility(View.GONE);
                        searchErrorTv.setVisibility(View.GONE);
                    }

                    @Override
                    public void onSuccess(@NotNull List<SearchModel> searches) {
                        searchAdapter.setSearches(searches);
                        progressFrameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("TAG", "onError: " +e.getMessage());
                        searchErrorTv.setVisibility(View.VISIBLE);
                        progressBar.setIndeterminate(false);
                        retryBtn.setVisibility(View.VISIBLE);
                        retryBtn.setOnClickListener(view->{
                            search(q);
                        });
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void onSearchClick(SearchModel search) {
        Log.i("TAG", "onSearchClick: "+search.getDisplayName());

        LatLng latLng = new LatLng(Double.parseDouble(search.getLat()),Double.parseDouble(search.getLon()));

        Intent returnResult = new Intent();
        returnResult.putExtra(Base.EXTRA_KEY_DATA, latLng);

        setResult(Activity.RESULT_OK,returnResult);
        finish();
    }


}