package com.example.veeez.feature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.veeez.R;
import com.example.veeez.User;
import com.example.veeez.data.UserManager;
import com.example.veeez.data.UserManagerProvider;
import com.example.veeez.feature.address.AddressActivity;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.internal.ParcelableSparseIntArray;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Timer;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileActivity extends AppCompatActivity {

    ImageView ic_back;

    CardView cons_adress;
    CardView cons_message;
    CardView logOut;
    UserManager userManager = UserManagerProvider.getInstance(this);
    ImageView profileEditButton;
    ShapeableImageView profileImageView;
    ShapeableImageView editImageButton;
    private ConstraintLayout constraintLayout;
    private final int REQUEST_IMAGE_GET = 1;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ic_back = findViewById(R.id.profile_back_btn);
        imageUri = userManager.getUserImageUri();
        constraintLayout = findViewById(R.id.cons_address);

        profileEditButton = findViewById(R.id.profileEditButton);
        profileImageView = findViewById(R.id.profileIv);
        editImageButton = findViewById(R.id.editImageButton);
        if (imageUri.toString() != "") {
 //           File file = new File(imageUri.toString());
//            profileImageView.setImageURI(imageUri);
//            Picasso.get().load(file).into(profileImageView);
////            try {
////                ParcelFileDescriptor parcelFileDescriptor =
////                        getContentResolver().openFileDescriptor(imageUri, "r");
////                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
////                Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
////
////            } catch (IOException exception) {
////                Log.e("image", exception.toString());
////            }
        }

        constraintLayout.setOnClickListener(view -> {
            startActivity(new Intent(this, AddressActivity.class));
        });

        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, UserEditProfileActivity.class));
            }
        });
        editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
            }
        });


        cons_adress = findViewById(R.id.profileAddressLayout);
        cons_message = findViewById(R.id.profileMessageLayout);
        logOut = findViewById(R.id.profileLogoutLayout);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cons_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProfileActivity.this, AddressActivity.class));
            }
        });


        cons_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MessageActivity.class));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            assert data != null;
            Bitmap thumbnail = data.getParcelableExtra("data");
            //  profileImageView.setImageBitmap(thumbnail);
            Uri fullPhoto = data.getData();
            userManager.saveUserImage(fullPhoto);
            profileImageView.setImageURI(fullPhoto);
        }
    }
}