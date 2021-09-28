package com.example.veeez.Activities.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veeez.R;
import com.example.veeez.common.Base;
import com.example.veeez.services.http.map.MapApiInterface;
import com.example.veeez.services.http.map.MapApiInterfaceProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.JsonObject;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.jetbrains.annotations.NotNull;

public class SubmitOrderBottomSheetFragment extends BottomSheetDialogFragment {

    private String originLat;
    private String originLon;
    private String destinationLat;
    private String destinationLon;
    private String originTitle;
    private String destinationTitle;

    private TextView originTv;
    private TextView destinationTv;

    private Button submitOrderBtn;

    private MapApiInterface mapApiInterface;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            destinationLat = getArguments().getString("ORIGIN_LAT");
            destinationLon = getArguments().getString("ORIGIN_LON");
            originLat = getArguments().getString("DESTINATION_LAT");
            originLon = getArguments().getString("DESTINATION_LON");

            originTitle = getArguments().getString(Base.EXTRA_KET_ORIGIN_TITLE);
            destinationTitle = getArguments().getString(Base.EXTRA_KET_DESTINATION_TITLE);
        }

        return inflater.inflate(R.layout.fragment_submit_order_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        originTv.setText(originTitle);
        destinationTv.setText(destinationTitle);

        submitOrderBtn.setOnClickListener(view1 -> {



            LatLng origin = new LatLng();
            origin.setLatitude(Double.parseDouble(originLat));
            origin.setLongitude(Double.parseDouble(originLon));

            LatLng destination = new LatLng();
            destination.setLatitude(Double.parseDouble(destinationLat));
            destination.setLongitude(Double.parseDouble(destinationLon));

            if (mapApiInterface == null){
                mapApiInterface = MapApiInterfaceProvider.getInstance();
            }

            // send submit request to server
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("origin_lat",origin.getLatitude());
            jsonObject.addProperty("origin_lon",origin.getLongitude());

            jsonObject.addProperty("destination_lat",destination.getLatitude());
            jsonObject.addProperty("destination_lon",destination.getLongitude());


            //apiInterface.submitOrder(jsonObject);
            // TODO: 9/26/2021 send request (set subscribeOn and observeOn)

            Bundle bundle = new Bundle();
            bundle.putString(Base.EXTRA_KEY_ORIGIN_LAT, String.valueOf(origin.getLatitude()));
            bundle.putString(Base.EXTRA_KEY_ORIGIN_LON, String.valueOf(origin.getLongitude()));
            bundle.putString(Base.EXTRA_KEY_DESTINATION_LAT, String.valueOf(destination.getLatitude()));
            bundle.putString(Base.EXTRA_KEY_DESTINATION_LON, String.valueOf(destination.getLongitude()));


            Intent intent = new Intent(requireActivity(), SubmitOrderService.class);
            intent.putExtras(bundle);

            requireActivity().startService(intent);
        });

    }

    private void initViews(View view) {
        originTv = view.findViewById(R.id.origin_tv);
        destinationTv = view.findViewById(R.id.destination_tv);
        submitOrderBtn = view.findViewById(R.id.submit_order_btn);
    }
}
