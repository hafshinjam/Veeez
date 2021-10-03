package com.example.veeez.feature;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veeez.R;
import com.example.veeez.feature.financial.FinancialListAdapter;
import com.example.veeez.feature.financial.FinancialResponse;
import com.example.veeez.feature.financial.FinancialViewModel;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FinanntialActivity extends AppCompatActivity {

    ImageView ic_back;
    private RecyclerView recyclerView;
    private FinancialListAdapter adapter;
    private FinancialViewModel viewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finanntial);
        viewModel = new FinancialViewModel(VeeezApiInterfaceProvider.getInstance());
        viewModel.getFinancialResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<FinancialResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull FinancialResponse response) {
                        adapter = new FinancialListAdapter(response);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("finanialError", e.getMessage());
                    }
                });
        recyclerView = findViewById(R.id.addressRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ic_back = findViewById(R.id.finanntial_back_btn);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }
}