package com.example.veeez.Activities.map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.veeez.Activities.map.search.SearchActivity;
import com.example.veeez.R;
import com.example.veeez.common.Base;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.UiSettings;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.mapbox.core.constants.Constants.PRECISION_6;
import static com.mapbox.mapboxsdk.style.layers.Property.NONE;
import static com.mapbox.mapboxsdk.style.layers.Property.VISIBLE;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineCap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineJoin;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineWidth;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.visibility;

public class DeliveryActivity extends AppCompatActivity implements PermissionsListener, OnMapReadyCallback {
    private static final String DROPPED_MARKER_LAYER_ID = "DROPPED_MARKER_LAYER_ID";
    private static final String TAG = "DeliveryActivity";
    private MapView mapView;
    private MapboxMap mapboxMap;
    private Button selectOriginLocationButton;
    private Button selectDestinationLocationButton;
    private PermissionsManager permissionsManager;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ImageView originMarker;

    private Layer droppedOriginMarkerLayer;

    private static final String ROUTE_LAYER_ID = "route-layer-id";
    private static final String ROUTE_SOURCE_ID = "route-source-id";
    private static final String ICON_LAYER_ID = "icon-layer-id";
    private static final String ICON_SOURCE_ID = "icon-source-id";
    private static final String RED_PIN_ICON_ID = "red-pin-icon-id";
    private DirectionsRoute currentRoute;
    private MapboxDirections client;


    private EditText deliverySearchEdt;
    private static final int REQUEST_CODE_SEARCH = 160;

    private Point origin = null;
    private Point destination = null;

    private LineLayer routeLayer;

    private Style loadedStyle;

    private FloatingActionButton findMyLocationBtn;

    private static final int ORIGIN_ID = 1;
    private static final int DESTINATION_ID = 2;

    private Bitmap originIconMarker;
    private Bitmap destinationMarker;

    private Layer droppedOriginLayer;

    private ImageView toolbarBackBtn;

    int a = 1;
    int b = 1;

    private String originTitle = null;
    private String destinationTitle = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_delivery);
        initViews();


        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        deliverySearchEdt.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(Base.EXTRA_KEY_ID, ORIGIN_ID);

            startActivityForResult(intent, REQUEST_CODE_SEARCH);

        });


        findMyLocationBtn.setOnClickListener(view -> {
            enableLocationPlugin(loadedStyle);
        });

        toolbarBackBtn.setOnClickListener(view->{
            finish();
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SEARCH && resultCode == Activity.RESULT_OK) {
            LatLng latLng = data.getParcelableExtra(Base.EXTRA_KEY_DATA);

            if (latLng != null) {
                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(latLng.getLatitude(), latLng.getLongitude()))
                        .zoom(14)
                        .tilt(20)
                        .build();

                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
            }
        }
    }

    private void initViews() {
        mapView = findViewById(R.id.mapView);
        deliverySearchEdt = findViewById(R.id.delivery_search_edt);
        findMyLocationBtn = findViewById(R.id.find_my_location_btn);
        selectOriginLocationButton = findViewById(R.id.delivery_confirm_origin_btn);
        selectDestinationLocationButton = findViewById(R.id.delivery_confirm_destination_btn);

        toolbarBackBtn = findViewById(R.id.toolbar_back_btn);

    }

    @Override
    public void onMapReady(@NonNull @NotNull MapboxMap mapboxMap) {
        DeliveryActivity.this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull @NotNull Style style) {
                enableLocationPlugin(style);

                loadedStyle = style;

                initOriginSource(style);

                originIconMarker = BitmapUtils.getBitmapFromDrawable(
                        getResources().getDrawable(R.drawable.red_marker));

                destinationMarker = BitmapUtils.getBitmapFromDrawable(
                        getResources().getDrawable(R.drawable.blue_marker));

                initLayers(style, originIconMarker);

                // When user is still picking a location, we hover a marker above the mapboxMap in the center.
                // This is done by using an image view with the default marker found in the SDK. You can
                // swap out for your own marker image, just make sure it matches up with the dropped marker.

                originMarker = new ImageView(DeliveryActivity.this);
                originMarker.setImageResource(R.drawable.red_marker);
                FrameLayout.LayoutParams originParams = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
                originMarker.setLayoutParams(originParams);
                mapView.addView(originMarker);


                UiSettings uiSettings = mapboxMap.getUiSettings();
                uiSettings.setCompassEnabled(false);


                // Initialize, but don't show, a SymbolLayer for the marker icon which will represent a selected location.
                initDroppedMarker(style);


                selectOriginLocationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (a == 1) {
                            // Use the map target's coordinates to make a reverse geocoding search
                            final LatLng mapTargetLatLng = mapboxMap.getCameraPosition().target;

                            origin = Point.fromLngLat(mapTargetLatLng.getLongitude(), mapTargetLatLng.getLatitude());

                            // Use the map camera target's coordinates to make a reverse geocoding search
                            getReverseAddress(Point.fromLngLat(mapTargetLatLng.getLongitude(), mapTargetLatLng.getLatitude()), new VolleyCallback() {
                                @Override
                                public void onSuccess(String address) {
                                    originTitle = address;

                                    if (destination != null){
                                        getRoute(mapboxMap,origin,destination);
                                        loadedStyle.addLayer(routeLayer);

                                        if (originTitle != ""){
                                            SubmitOrderBottomSheetFragment bottomSheetFragment = new SubmitOrderBottomSheetFragment();
                                            Bundle bundle = new Bundle();

                                            bundle.putString(Base.EXTRA_KEY_ORIGIN_LAT, String.valueOf(origin.latitude()) );
                                            bundle.putString(Base.EXTRA_KEY_ORIGIN_LON ,String.valueOf(origin.longitude()));
                                            bundle.putString(Base.EXTRA_KEY_DESTINATION_LAT,String.valueOf(destination.latitude()) );
                                            bundle.putString(Base.EXTRA_KEY_DESTINATION_LON,String.valueOf(destination.longitude()));
                                            bundle.putString(Base.EXTRA_KET_ORIGIN_TITLE,originTitle);
                                            bundle.putString(Base.EXTRA_KET_DESTINATION_TITLE,destinationTitle);

                                            bottomSheetFragment.setArguments(bundle);
                                            bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());
                                        }


                                    }else {
                                        Toast.makeText(DeliveryActivity.this, "لطفا مقصد را انتخاب کنید", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                            // Hide the hovering red hovering ImageView marker
                            a = 2;

                            // Transform the appearance of the button to become the cancel button
                            selectOriginLocationButton.setText(getString(R.string.select_origin));


                            // Show the SymbolLayer icon to represent the selected map location
                            if (style.getLayer(DROPPED_MARKER_LAYER_ID) != null) {
                                GeoJsonSource source = style.getSourceAs("dropped-marker-source-id");
                                if (source != null) {
                                    source.setGeoJson(Point.fromLngLat(mapTargetLatLng.getLongitude(), mapTargetLatLng.getLatitude()));
                                }
                                droppedOriginMarkerLayer = style.getLayer(DROPPED_MARKER_LAYER_ID);
                                if (droppedOriginMarkerLayer != null) {
                                    droppedOriginMarkerLayer.setProperties(visibility(VISIBLE));
                                }
                            }


                            Log.i(TAG, "onClick: " + mapTargetLatLng.toString());

                        } else {

                            loadedStyle.removeLayer(routeLayer);
                            origin = null;

                            // Switch the button appearance back to select a location.

                            selectOriginLocationButton.setText(getString(R.string.confirm_origin));

                            // Show the red hovering ImageView marker
                            a = 1;

                            // Hide the selected location SymbolLayer
                            droppedOriginMarkerLayer = style.getLayer(DROPPED_MARKER_LAYER_ID);

                            if (droppedOriginMarkerLayer != null) {
                                droppedOriginMarkerLayer.setProperties(visibility(NONE));

                            }
                        }
                        Log.i(TAG, " onStyleLoaded:ORIGIN=> " + origin);
                        Log.i(TAG, " onStyleLoaded:DESTINATION=> " + destination);
                    }
                });


                selectDestinationLocationButton.setOnClickListener(view -> {
                    if (b == 1) {
                        // Use the map target's coordinates to make a reverse geocoding search
                        final LatLng mapTargetLatLng = mapboxMap.getCameraPosition().target;

                        destination = Point.fromLngLat(mapTargetLatLng.getLongitude(), mapTargetLatLng.getLatitude());

                        getReverseAddress(Point.fromLngLat(mapTargetLatLng.getLongitude(), mapTargetLatLng.getLatitude()), new VolleyCallback() {
                            @Override
                            public void onSuccess(String address) {
                                destinationTitle = address;

                                if (origin != null){
                                    getRoute(mapboxMap,origin,destination);
                                    loadedStyle.addLayer(routeLayer);

                                    if (destinationTitle != null){
                                        SubmitOrderBottomSheetFragment bottomSheetFragment = new SubmitOrderBottomSheetFragment();
                                        Bundle bundle = new Bundle();

                                        bundle.putString(Base.EXTRA_KEY_ORIGIN_LAT, String.valueOf(origin.latitude()) );
                                        bundle.putString(Base.EXTRA_KEY_ORIGIN_LON ,String.valueOf(origin.longitude()));
                                        bundle.putString(Base.EXTRA_KEY_DESTINATION_LAT,String.valueOf(destination.latitude()) );
                                        bundle.putString(Base.EXTRA_KEY_DESTINATION_LON,String.valueOf(destination.longitude()));
                                        bundle.putString(Base.EXTRA_KET_ORIGIN_TITLE,originTitle);
                                        bundle.putString(Base.EXTRA_KET_DESTINATION_TITLE,destinationTitle);

                                        bottomSheetFragment.setArguments(bundle);
                                        bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());
                                    }


                                }else {
                                    Toast.makeText(DeliveryActivity.this, "لطفا مبدا را انتخاب کنید", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });





                        // Hide the hovering red hovering ImageView marker
                        b = 2;

                        // Transform the appearance of the button to become the cancel button

                        selectDestinationLocationButton.setText(getString(R.string.select_destination));


                        // Show the SymbolLayer icon to represent the selected map location
                        if (style.getLayer(DROPPED_MARKER_LAYER_ID) != null) {
                            GeoJsonSource source = style.getSourceAs("dropped-destination-marker-source-id");
                            if (source != null) {
                                source.setGeoJson(Point.fromLngLat(mapTargetLatLng.getLongitude(), mapTargetLatLng.getLatitude()));
                            }
                            droppedOriginMarkerLayer = style.getLayer(DROPPED_MARKER_LAYER_ID);
                            if (droppedOriginMarkerLayer != null) {
                                droppedOriginMarkerLayer.setProperties(visibility(VISIBLE));
                            }
                        }


                        // Use the map camera target's coordinates to make a reverse geocoding search



                        Log.i(TAG, "onClick: " + mapTargetLatLng.toString());

                    } else {

                        loadedStyle.removeLayer(routeLayer);

                        destination = null;

                        // Switch the button appearance back to select a location.
                        selectDestinationLocationButton.setText(getString(R.string.confirm_destination));

                        // Show the red hovering ImageView marker
                        b = 1;

                        // Hide the selected location SymbolLayer
                        droppedOriginMarkerLayer = style.getLayer(DROPPED_MARKER_LAYER_ID);

                        if (droppedOriginMarkerLayer != null) {
                            droppedOriginMarkerLayer.setProperties(visibility(NONE));

                        }
                    }
                    Log.i(TAG, "onStyleLoaded: " + origin);
                    Log.i(TAG, "onStyleLoaded: " + destination);
                });


            }
        });
    }





    private void initDroppedMarker(@NonNull Style loadedMapStyle) {
// Add the marker image to map
        loadedMapStyle.addImage("dropped-icon-image", AppCompatResources.getDrawable(DeliveryActivity.this, R.drawable.blue_marker));
        loadedMapStyle.addSource(new GeoJsonSource("dropped-marker-source-id"));

        droppedOriginLayer = new SymbolLayer(DROPPED_MARKER_LAYER_ID,
                "dropped-marker-source-id").withProperties(
                iconImage("dropped-icon-image"),
                visibility(NONE),
                iconAllowOverlap(true),
                iconIgnorePlacement(true)
        );

        loadedMapStyle.addLayer(droppedOriginLayer);
    }


    //for route
    private void initOriginSource(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource(ROUTE_SOURCE_ID));

        if (destination != null) {
            GeoJsonSource iconGeoJsonSource = new GeoJsonSource(ICON_SOURCE_ID, FeatureCollection.fromFeatures(new Feature[]{
                    Feature.fromGeometry(Point.fromLngLat(origin.longitude(), origin.latitude())),
                    Feature.fromGeometry(Point.fromLngLat(destination.longitude(), destination.latitude()))}));
            loadedMapStyle.addSource(iconGeoJsonSource);
        }

    }

    private void initLayers(@NonNull Style loadedMapStyle, Bitmap markerIcon) {
        routeLayer = new LineLayer(ROUTE_LAYER_ID, ROUTE_SOURCE_ID);

// Add the LineLayer to the map. This layer will display the directions route.
        routeLayer.setProperties(
                lineCap(Property.LINE_CAP_ROUND),
                lineJoin(Property.LINE_JOIN_ROUND),
                lineWidth(5f),
                lineColor(Color.parseColor("#009688"))
        );
        // Add the red marker icon image to the map
        loadedMapStyle.addImage(RED_PIN_ICON_ID, markerIcon);

// Add the red marker icon SymbolLayer to the map
        loadedMapStyle.addLayer(new SymbolLayer(ICON_LAYER_ID, ICON_SOURCE_ID).withProperties(
                iconImage(RED_PIN_ICON_ID),
                iconIgnorePlacement(true),
                iconAllowOverlap(true),
                iconOffset(new Float[]{0f, -9f})));
    }


    private void getRoute(MapboxMap mapboxMap, Point origin, Point destination) {
        client = MapboxDirections.builder()
                .origin(origin)
                .destination(destination)
                .overview(DirectionsCriteria.OVERVIEW_FULL)
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .accessToken(getString(R.string.mapbox_access_token))
                .build();

        client.enqueueCall(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
// You can get the generic HTTP info about the response
                Timber.d("Response code: " + response.code());
                if (response.body() == null) {
                    Timber.e("No routes found, make sure you set the right user and access token.");
                    return;
                } else if (response.body().routes().size() < 1) {
                    Timber.e("No routes found");
                    return;
                }

// Get the directions route
                currentRoute = response.body().routes().get(0);

// Make a toast which displays the route's distance
//                Toast.makeText(DirectionsActivity.this, String.format(
//                        getString(R.string.directions_activity_toast_message),
//                        currentRoute.distance()), Toast.LENGTH_SHORT).show();

                if (mapboxMap != null) {
                    mapboxMap.getStyle(new Style.OnStyleLoaded() {
                        @Override
                        public void onStyleLoaded(@NonNull Style style) {

// Retrieve and update the source designated for showing the directions route
                            GeoJsonSource source = style.getSourceAs(ROUTE_SOURCE_ID);

// Create a LineString with the directions route's geometry and
// reset the GeoJSON source for the route LineLayer source
                            if (source != null) {
                                source.setGeoJson(LineString.fromPolyline(currentRoute.geometry(), PRECISION_6));
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                Timber.e("Error: " + throwable.getMessage());
                Toast.makeText(DeliveryActivity.this, "Error: " + throwable.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }






    @Override
    public void onExplanationNeeded(List<String> list) {
        Toast.makeText(this, "user_location_permission_explanation", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted && mapboxMap != null) {
            Style style = mapboxMap.getStyle();
            if (style != null) {
                enableLocationPlugin(style);
            }
        } else {
            Toast.makeText(this, "user_location_permission_not_granted", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @SuppressWarnings({"MissingPermission"})
    private void enableLocationPlugin(@NonNull Style loadedMapStyle) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

// Get an instance of the component. Adding in LocationComponentOptions is also an optional
// parameter
            LocationComponent locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(
                    this, loadedMapStyle).build());
            locationComponent.setLocationComponentEnabled(true);

// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.NORMAL);

        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    private void getReverseAddress(Point point, VolleyCallback callback) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=" + point.latitude() + "&lon=" + point.longitude(),
                null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        try {
                            String address = response.getString("display_name");
                           // Toast.makeText(DeliveryActivity.this, address, Toast.LENGTH_LONG).show();
                            callback.onSuccess(address);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onResponse: " + error.toString());

            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public interface VolleyCallback {
        void onSuccess(String address);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the Directions API request
        if (client != null) {
            client.cancelCall();
        }
        compositeDisposable.clear();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}