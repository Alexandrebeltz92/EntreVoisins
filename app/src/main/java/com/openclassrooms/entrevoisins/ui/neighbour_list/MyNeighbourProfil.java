package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.OnClick;

public class MyNeighbourProfil extends AppCompatActivity {

    private ImageView mProfilPitcure;
    private TextView mContactProfil;
    private TextView mAboutProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_neighbour_profil);

        mProfilPitcure = (ImageView) findViewById(R.id.profil_picture);
        mContactProfil = (TextView) findViewById(R.id.name_profil);
        mAboutProfil = (TextView) findViewById(R.id.about_profil);

        Neighbour neighbour = getIntent().getExtras().getParcelable("myNeighbour");

        mContactProfil.setText(neighbour.getName());
    }

}