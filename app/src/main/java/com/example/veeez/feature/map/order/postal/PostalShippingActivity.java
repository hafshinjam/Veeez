package com.example.veeez.feature.map.order.postal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.veeez.R;
import com.example.veeez.feature.map.order.SubmitOrderBottomSheetFragment;

import static com.example.veeez.common.Base.EXTRA_KEY_BOX;
import static com.example.veeez.common.Base.EXTRA_KEY_ENVELOPE;
import static com.example.veeez.common.Base.EXTRA_KEY_INSURANCE;
import static com.example.veeez.common.Base.EXTRA_KEY_ORIGIN_TITLE;
import static com.example.veeez.common.Base.EXTRA_KEY_DATA;
import static com.example.veeez.common.Base.EXTRA_KEY_ORIGIN_LAT;
import static com.example.veeez.common.Base.EXTRA_KEY_ORIGIN_LON;
import static com.example.veeez.common.Base.EXTRA_KEY_PRODUCT_COST;
import static com.example.veeez.common.Base.EXTRA_KEY_PRODUCT_SIZE;
import static com.example.veeez.common.Base.EXTRA_KEY_TYPE;
import static com.example.veeez.common.Base.TYPE_POSTAL_DELIVERY;

public class PostalShippingActivity extends AppCompatActivity {

    private String originLat;
    private String originLon;

    private String originTitle;

    private TextView originTitleTv;

    private EditText productSizeEdt;
    private EditText productCostEdt;
    private TextView envelopeEdt;
    private TextView boxEdt;
    private TextView insuranceEdt;

    private ImageView backBtn;

    private Button continuePostalOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postal_shipping);

        initViews();

        Bundle bundle = getIntent().getBundleExtra(EXTRA_KEY_DATA);

        originLat = bundle.getString(EXTRA_KEY_ORIGIN_LAT);
        originLon = bundle.getString(EXTRA_KEY_ORIGIN_LON);

        originTitle = bundle.getString(EXTRA_KEY_ORIGIN_TITLE);


        originTitleTv.setText(originTitle);
        backBtn.setOnClickListener(view -> {
            finish();
        });

        continuePostalOrderBtn.setOnClickListener(view -> {

            Bundle userData = new Bundle();
            userData.putString(EXTRA_KEY_ORIGIN_LAT, String.valueOf(originLat));
            userData.putString(EXTRA_KEY_ORIGIN_LON, String.valueOf(originLon));

            userData.putString(EXTRA_KEY_ORIGIN_TITLE, originTitle);

            userData.putString(EXTRA_KEY_ENVELOPE, envelopeEdt.getText().toString());

            userData.putString(EXTRA_KEY_PRODUCT_SIZE, productSizeEdt.getText().toString());
            userData.putString(EXTRA_KEY_PRODUCT_COST, productCostEdt.getText().toString());

            userData.putString(EXTRA_KEY_BOX, boxEdt.getText().toString());
            userData.putString(EXTRA_KEY_INSURANCE, insuranceEdt.getText().toString());

            userData.putInt(EXTRA_KEY_TYPE, TYPE_POSTAL_DELIVERY);

            SubmitOrderBottomSheetFragment bottomSheetFragment = new SubmitOrderBottomSheetFragment();
            bottomSheetFragment.setArguments(userData);
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

        });


    }

    private void initViews() {
        originTitleTv = findViewById(R.id.origin_title_tv);

        productSizeEdt = findViewById(R.id.product_size_edt);
        productCostEdt = findViewById(R.id.product_cost_edt);

        envelopeEdt = findViewById(R.id.item_dropdown_envelope);
        boxEdt = findViewById(R.id.item_dropdown_box);
        insuranceEdt = findViewById(R.id.item_dropdown_insurance);

        backBtn = findViewById(R.id.postal_toolbar_back_btn);

        continuePostalOrderBtn = findViewById(R.id.continue_postal_order);
    }
}