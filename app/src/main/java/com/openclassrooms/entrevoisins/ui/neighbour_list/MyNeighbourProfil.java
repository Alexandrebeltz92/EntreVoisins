package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class MyNeighbourProfil extends AppCompatActivity {

    private ImageView mProfilPitcure;
    private TextView mProfilName2;
    private TextView mLocalisationContact;
    private TextView mNumberContact;
    private TextView mSocialMedia;
    private TextView mAboutInfo;
    private TextView mAboutTitle;
    private FloatingActionButton mFavButton;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_neighbour_profil);
        mApiService = DI.getNeighbourApiService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbarlayout);


        mProfilPitcure = (ImageView) findViewById(R.id.profil_picture);
        mProfilName2 = (TextView) findViewById(R.id.profil_name_2);
        mLocalisationContact = (TextView) findViewById(R.id.localisation_contact);
        mNumberContact = (TextView) findViewById(R.id.number_contact);
        mSocialMedia = (TextView) findViewById(R.id.social_media);
        mAboutInfo = (TextView) findViewById(R.id.about_info);
        mAboutTitle = (TextView) findViewById(R.id.about_title);
        mFavButton = findViewById(R.id.fav_button);

        Neighbour neighbour;
        if (getIntent().getExtras() != null) {
            neighbour = getIntent().getExtras().getParcelable("myNeighbour");

            Glide.with(this)
                    .load(neighbour.getAvatarUrl())
                    .into(mProfilPitcure);

            toolBarLayout.setTitle(neighbour.getName());
            mProfilName2.setText(neighbour.getName());
            mLocalisationContact.setText(neighbour.getAddress());
            mNumberContact.setText(neighbour.getPhoneNumber());
            mAboutInfo.setText(neighbour.getAboutMe());
            mSocialMedia.setText("www.facebook/" + neighbour.getName());
            mAboutTitle.setText("A propos de moi");

            if (neighbour.isFavorite()) {
                mFavButton.setImageResource(R.drawable.ic_star_white_24dp);
            } else {
                mFavButton.setImageResource(R.drawable.ic_star_border_white_24dp);
            }

            mFavButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (neighbour.isFavorite()) {
                        mFavButton.setImageResource(R.drawable.ic_star_border_white_24dp);
                    } else {
                        mFavButton.setImageResource(R.drawable.ic_star_white_24dp);

                    }
                    neighbour.setFavorite(!neighbour.isFavorite());
                    mApiService.modifyNeighbour(neighbour);
                }
            });
        }
    }

}