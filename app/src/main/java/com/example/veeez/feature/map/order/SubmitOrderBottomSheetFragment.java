package com.example.veeez.feature.map.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veeez.R;
import com.example.veeez.common.Utils;
import com.example.veeez.data.ApplyVoucherResponse;
import com.example.veeez.data.CalculatePriceResponse;
import com.example.veeez.data.UserManager;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.veeez.common.Base.EXTRA_KEY_BOX;
import static com.example.veeez.common.Base.EXTRA_KEY_DESTINATION_TITLE;
import static com.example.veeez.common.Base.EXTRA_KEY_ENVELOPE;
import static com.example.veeez.common.Base.EXTRA_KEY_INSURANCE;
import static com.example.veeez.common.Base.EXTRA_KEY_ORIGIN_TITLE;
import static com.example.veeez.common.Base.EXTRA_KEY_DESTINATION_LAT;
import static com.example.veeez.common.Base.EXTRA_KEY_DESTINATION_LON;
import static com.example.veeez.common.Base.EXTRA_KEY_ORIGIN_LAT;
import static com.example.veeez.common.Base.EXTRA_KEY_ORIGIN_LON;
import static com.example.veeez.common.Base.EXTRA_KEY_PRODUCT_COST;
import static com.example.veeez.common.Base.EXTRA_KEY_PRODUCT_SIZE;
import static com.example.veeez.common.Base.EXTRA_KEY_TYPE;
import static com.example.veeez.common.Base.PAYMENT_METHOD_DESTINATION;
import static com.example.veeez.common.Base.PAYMENT_METHOD_ORIGIN;
import static com.example.veeez.common.Base.TYPE_NORMAL_DELIVERY;

public class SubmitOrderBottomSheetFragment extends BottomSheetDialogFragment {

    private String originLat;
    private String originLon;
    private String destinationLat;
    private String destinationLon;
    private String originTitle;
    private String destinationTitle;

    private String envelope;
    private String box;
    private String productSize;
    private String productCost;
    private String insurance;

    private TextView envelopeTv;
    private TextView boxTv;
    private TextView costTv;
    private TextView sizeTv;
    private TextView insuranceTv;

    private TextView originTv;
    private TextView destinationTv;
    private Button submitOrderBtn;
    private TextView priceTv;
    private EditText voucherEdt;

    private Button applyDiscount;
    private Button removeDiscount;
    private RadioGroup paymentMethodRg;

    private int userPaymentMethod;

    private CalculatePriceResponse mCalculatePriceResponse;

    private UserManager userManager;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private SubmitOrderViewModel viewModel = new SubmitOrderViewModel(VeeezApiInterfaceProvider.getInstance());

    private int orderType;

    private ConstraintLayout postalInfo;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {

            if (getArguments().getInt(EXTRA_KEY_TYPE) == TYPE_NORMAL_DELIVERY) {
                destinationLat = getArguments().getString(EXTRA_KEY_DESTINATION_LAT);
                destinationLon = getArguments().getString(EXTRA_KEY_DESTINATION_LON);
                originLat = getArguments().getString(EXTRA_KEY_ORIGIN_LAT);
                originLon = getArguments().getString(EXTRA_KEY_ORIGIN_LON);
                originTitle = getArguments().getString(EXTRA_KEY_ORIGIN_TITLE);
                destinationTitle = getArguments().getString(EXTRA_KEY_DESTINATION_TITLE);

                orderType = 1;


            } else {
                originTitle = getArguments().getString(EXTRA_KEY_ORIGIN_TITLE);
                originLat = getArguments().getString(EXTRA_KEY_ORIGIN_LAT);
                originLon = getArguments().getString(EXTRA_KEY_ORIGIN_LON);
                envelope = getArguments().getString(EXTRA_KEY_ENVELOPE);
                productSize = getArguments().getString(EXTRA_KEY_PRODUCT_SIZE);
                productCost = getArguments().getString(EXTRA_KEY_PRODUCT_COST);
                box = getArguments().getString(EXTRA_KEY_BOX);
                insurance = getArguments().getString(EXTRA_KEY_INSURANCE);

                orderType = 2;
            }


        }

        return inflater.inflate(R.layout.fragment_submit_order_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        originTv.setText(originTitle);

        if (orderType == TYPE_NORMAL_DELIVERY){

            paymentMethodRg.setVisibility(View.VISIBLE);
            postalInfo.setVisibility(View.GONE);

            viewModel.getPrice(Double.parseDouble(originLat), Double.parseDouble(originLon), Double.parseDouble(destinationLat), Double.parseDouble(destinationLon), "sanandaj", TYPE_NORMAL_DELIVERY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<CalculatePriceResponse>() {
                        @Override
                        public void onSubscribe(@NotNull Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(@NotNull CalculatePriceResponse calculatePriceResponse) {
                            if (calculatePriceResponse.getStatus() == 0) {
                                Toast.makeText(requireActivity(), calculatePriceResponse.getText(), Toast.LENGTH_SHORT).show();
                            }
                            if (calculatePriceResponse.getStatus() == 1) {
                                priceTv.setText(Utils.formatPrice(Double.parseDouble(calculatePriceResponse.getPrice().toString())));
                            }
                            mCalculatePriceResponse = calculatePriceResponse;

                        }

                        @Override
                        public void onError(@NotNull Throwable e) {
                            Toast.makeText(requireActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

            destinationTv.setText(destinationTitle);

        }else {
            postalInfo.setVisibility(View.VISIBLE);
            paymentMethodRg.setVisibility(View.GONE);

            envelopeTv.setText(envelope);
            boxTv.setText(box);
            costTv.setText(productCost);
            sizeTv.setText(productSize);
            insuranceTv.setText(insurance);

        }

        userManager = new UserManager(requireContext());


        paymentMethodRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.payment_at_origin_rb) {
                    userPaymentMethod = PAYMENT_METHOD_ORIGIN;
                } else {
                    userPaymentMethod = PAYMENT_METHOD_DESTINATION;
                }
            }
        });


        applyDiscount.setOnClickListener(view1 -> {
            viewModel.setVoucher(
                    voucherEdt.getText().toString(),
                    userManager.getUserId(),
                    75730000,
                    75730000
            )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<ApplyVoucherResponse>() {
                        @Override
                        public void onSubscribe(@NotNull Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(@NotNull ApplyVoucherResponse applyVoucherResponse) {
                            if (applyVoucherResponse.getStatus() == 0) {
                                Toast.makeText(requireActivity(), applyVoucherResponse.getText(), Toast.LENGTH_SHORT).show();
                            }
                            if (applyVoucherResponse.getStatus() == 1) {
                                priceTv.setText(Utils.formatPrice(Double.parseDouble(applyVoucherResponse.getFinalCost().toString())));
                                applyDiscount.setVisibility(View.GONE);
                                removeDiscount.setVisibility(View.VISIBLE);

                            }
                        }

                        @Override
                        public void onError(@NotNull Throwable e) {
                            Toast.makeText(requireActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });


        removeDiscount.setOnClickListener(view1 -> {
            priceTv.setText(Utils.formatPrice(Double.parseDouble(mCalculatePriceResponse.getPrice().toString())));
            removeDiscount.setVisibility(View.GONE);
        });

        submitOrderBtn.setOnClickListener(view1 -> {

//            Bundle bundle = new Bundle();
//            bundle.putString(Base.EXTRA_KEY_ORIGIN_LAT, String.valueOf(origin.getLatitude()));
//            bundle.putString(Base.EXTRA_KEY_ORIGIN_LON, String.valueOf(origin.getLongitude()));
//            bundle.putString(Base.EXTRA_KEY_DESTINATION_LAT, String.valueOf(destination.getLatitude()));
//            bundle.putString(Base.EXTRA_KEY_DESTINATION_LON, String.valueOf(destination.getLongitude()));
//
//
//            Intent intent = new Intent(requireActivity(), SubmitOrderService.class);
//            intent.putExtras(bundle);
//
//            requireActivity().startService(intent);

        });

    }

    private void initViews(View view) {
        originTv = view.findViewById(R.id.origin_tv);
        destinationTv = view.findViewById(R.id.destination_tv);
        submitOrderBtn = view.findViewById(R.id.submit_order_btn);
        priceTv = view.findViewById(R.id.price_tv);
        applyDiscount = view.findViewById(R.id.apply_voucher_btn);
        voucherEdt = view.findViewById(R.id.voucher_edt);
        removeDiscount = view.findViewById(R.id.remove_voucher_btn);

        paymentMethodRg = view.findViewById(R.id.payment_method_rg);

        postalInfo = view.findViewById(R.id.postal_information);

        envelopeTv = view.findViewById(R.id.envelop_tv);
        boxTv = view.findViewById(R.id.box_tv);
        costTv = view.findViewById(R.id.cost_tv);
        sizeTv = view.findViewById(R.id.size_tv);
        insuranceTv = view.findViewById(R.id.insurance_tv);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable.size() > 1) {
            compositeDisposable.clear();
        }
    }
}
