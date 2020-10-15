package learn.numbers.all.major.languages.clone.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import learn.numbers.all.major.languages.clone.R;


public class AboutFragment extends BaseFragment {


    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance() {

        return new AboutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        view.findViewById(R.id.settingsShare_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
                shareUs();
            }
        });
        view.findViewById(R.id.settingsRateUs_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Rate us", Toast.LENGTH_SHORT).show();
                rateUs();
            }
        });
        view.findViewById(R.id.settingsAbout_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
        view.findViewById(R.id.settingsMore_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=FreeBirdis")));
            }
        });


        return view;
    }

    public void dialog() {
        View view = getLayoutInflater().inflate(R.layout.about_dialog_layout, null, false);
        TextView aboutUsCloseApp_tv = view.findViewById(R.id.aboutUsCloseApp_tv);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true).setView(view);

        final AlertDialog dialog = builder.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView version_tv = view.findViewById(R.id.appVersion_tv);

        aboutUsCloseApp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        });

        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            String version = packageInfo.versionName;
            version_tv.setText(version);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void rateUs() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + new PackageInfo().packageName)));
    }

    public void shareUs() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + new PackageInfo().packageName);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


}