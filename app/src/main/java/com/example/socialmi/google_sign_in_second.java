package com.example.socialmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class google_sign_in_second extends AppCompatActivity {

    TextView name, mail, id;
    Button logout;
    ImageView google_image;
    private String gname, gemail, gid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in_second);

        logout = findViewById(R.id.sign_out);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        mail = findViewById(R.id.email);
        google_image = findViewById(R.id.google_photo);

        gname = getIntent().getExtras().getString("personName");
        gemail = getIntent().getExtras().getString("personEmail");
        gid = getIntent().getExtras().getString("personId");

        if (gname == null) {
            name.setVisibility(View.GONE);
        }
        if (gemail == null) {
            mail.setVisibility(View.GONE);
        }
        if (gid == null) {
            id.setVisibility(View.GONE);
        }
        if (!getIntent().getExtras().getString("personPhoto").equals("noPhoto")) {
            Picasso.get().load(getIntent().getExtras().getString("personPhoto")).into(google_image);
        } else {
            google_image.setImageResource(R.drawable.images);
        }


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            name.setText("Name : " + signInAccount.getDisplayName());
            mail.setText("Email : " + signInAccount.getEmail());
            id.setText("ID : " + signInAccount.getId());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}