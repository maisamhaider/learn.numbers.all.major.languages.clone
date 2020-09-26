package learn.numbers.all.major.languages.clone.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.fragments.AboutFragment;
import learn.numbers.all.major.languages.clone.fragments.CountingFragment;
import learn.numbers.all.major.languages.clone.fragments.HomeFragment;
import learn.numbers.all.major.languages.clone.fragments.TranslatorFragment;

public class MainAct extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bnvMain = findViewById(R.id.bnv_main);
         loadFragment(HomeFragment.newInstance());
         bnvMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_item:
                        loadFragment(HomeFragment.newInstance());
                        break;
                    case R.id.translator_item:
                        loadFragment(TranslatorFragment.newInstance());
                        break;
                    case R.id.counting_item:
                        loadFragment(CountingFragment.newInstance());
                        break;
                    case R.id.about_item:
                        sNFragmentAds(AboutFragment.newInstance()
                        );
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            exitt();
        }

    }

    public void exitt() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = getLayoutInflater();
            @SuppressLint("InflateParams") final View dialogView =
                    layoutInflater.inflate(R.layout.exit_layout, null);
            ConstraintLayout yes_cl = dialogView.findViewById(R.id.yes_cl);
            ConstraintLayout no_cl = dialogView.findViewById(R.id.no_cl);
            ConstraintLayout rateUs_cl = dialogView.findViewById(R.id.rateUs_cl);


            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();

            yes_cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainAct.this.moveTaskToBack(true);
                    alertDialog.cancel();

                    MainAct.this.finishAffinity();
                }
            });

            no_cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//
                    alertDialog.dismiss();

                }
            });
            rateUs_cl.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 MainAct.this.rate();
                                             }
                                         }
            );

        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    public void rate() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
    }

}