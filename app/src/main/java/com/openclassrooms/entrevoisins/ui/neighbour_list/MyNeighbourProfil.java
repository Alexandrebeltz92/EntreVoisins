package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.OnClick;

public class MyNeighbourProfil extends AppCompatActivity {

    private ImageView mProfilPitcure;
    private Button mButtonPreview;
    private TextView mProfilName;
    private TextView mProfilName2;
    private TextView mLocalisationContact;
    private TextView mNumberContact;
    private TextView mSocialMedia;
    private TextView mAboutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_neighbour_profil);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mProfilPitcure = (ImageView) findViewById(R.id.profil_picture);
        mButtonPreview = (Button) findViewById(R.id.button_preview);
        mProfilName = (TextView) findViewById(R.id.profil_name);
        mProfilName2 = (TextView) findViewById(R.id.profil_name_2);
        mLocalisationContact = (TextView) findViewById(R.id.localisation_contact);
        mNumberContact = (TextView) findViewById(R.id.number_contact);
        mSocialMedia = (TextView) findViewById(R.id.social_media);
        mAboutInfo = (TextView) findViewById(R.id.about_info);

        Neighbour neighbour = getIntent().getExtras().getParcelable("myNeighbour");

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mProfilPitcure);

        mProfilName.setText(neighbour.getName());
        mProfilName2.setText(neighbour.getName());
        mLocalisationContact.setText(neighbour.getAddress());
        mNumberContact.setText(neighbour.getPhoneNumber());
        mAboutInfo.setText(neighbour.getAboutMe());
    }

}