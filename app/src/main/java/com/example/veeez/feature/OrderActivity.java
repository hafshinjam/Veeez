package com.example.veeez.feature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.veeez.R;
import com.example.veeez.data.UserManager;
import com.example.veeez.data.UserManagerProvider;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity {
    private OrderListAdapter adapter = new OrderListAdapter();
    private RecyclerView recyclerView;
    private ImageView emptyListImage;
    private OrderViewModel viewModel = new OrderViewModel(VeeezApiInterfaceProvider.getInstance());
    private UserManager userManager = UserManagerProvider.getInstance(this);
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        recyclerView = findViewById(R.id.addressRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        ic_back = findViewById(R.id.order_back_btn);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        emptyListImage = findViewById(R.id.emptyOrderListImage);
        viewModel.getOderResponse("ba53ab81-fbf3-40b1-aacc-6be48841264f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<OrderHistoryResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull OrderHistoryResponse orderHistoryResponse) {
                        if (orderHistoryResponse.getStatus() != 0) {
                            adapter.setObjects(orderHistoryResponse.getOrdersList());
                            adapter.notifyDataSetChanged();
                            emptyListImage.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                        else{
                            emptyListImage.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}