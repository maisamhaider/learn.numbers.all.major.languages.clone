package learn.numbers.all.major.languages.clone.fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.List;
import java.util.Locale;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.activities.MainAct;
import learn.numbers.all.major.languages.clone.adapters.TranslatorLanguageItemAdapter;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.IsTargetLanguage;
import learn.numbers.all.major.languages.clone.interfaces.TrueFalse;
import learn.numbers.all.major.languages.clone.preferences.Pref;

public class TranslatorFragment extends BaseFragment implements IsTargetLanguage, TrueFalse {

    private View view;
    private View languagesList_ll;
    private TextView userLanguage_tv;
    private TextView targetLanguage_tv;
    private TextView languageResult_tv;
    private TextView userInputLang_tv;
    private TextView resultTargetLang_tv;
    private TranslatorLanguageItemAdapter adapter;
    private Pref pref;
    private AlertDialog dialog;
    MainAct mainAct;
    EditText userLanguage_et;
    boolean fistTime = false;

    public TranslatorFragment() {
        // Required empty public constructor
    }


    public static TranslatorFragment newInstance() {


        return new TranslatorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_translator, container, false);

        // objects
        pref = new Pref(getActivity());
        mainAct = (MainAct) getActivity();

        // functions
        mainAct.setTrueFalse(this);

        // find views
        userLanguage_tv = view.findViewById(R.id.userLanguage_tv);
        targetLanguage_tv = view.findViewById(R.id.targetLanguage_tv);
        languageResult_tv = view.findViewById(R.id.languageResult_tv);
        userInputLang_tv = view.findViewById(R.id.userInputLang_tv);
        resultTargetLang_tv = view.findViewById(R.id.resultTargetLang_tv);
        languagesList_ll = view.findViewById(R.id.languagesList_ll);
        TextView translate_tv = view.findViewById(R.id.translate_tv);
        ImageView switchIv = view.findViewById(R.id.switchIv);
        userLanguage_et = view.findViewById(R.id.userLanguage_et);

        //click listeners
        userLanguage_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapter(false);
            }
        });
        targetLanguage_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapter(true);
            }
        });

        // FINAL CLICK TO START TRANSLATION
        translate_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE, true);
                String codeUser = pref.getStringData(MyAnno.USER_LANGUAGE, true);
                String input = userLanguage_et.getText().toString();
                if (!input.isEmpty()) {
                    translate(input, codeUser, codeTarget);
                }

            }
        });
        switchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeUser = pref.getStringData(MyAnno.USER_LANGUAGE, true);
                String codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE, true);
                pref.setData(codeTarget, MyAnno.USER_LANGUAGE);
                pref.setData(codeUser, MyAnno.TARGET_LANGUAGE);
                codeUser = pref.getStringData(MyAnno.USER_LANGUAGE, true);
                codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE, true);
                userLanguage_tv.setText(getLanguageName(codeUser));
                targetLanguage_tv.setText(getLanguageName(codeTarget));
                userInputLang_tv.setText(getLanguageName(codeUser));
                resultTargetLang_tv.setText(getLanguageName(codeTarget));

                // take last output as input and set to input editText and then convert it again
                String output = languageResult_tv.getText().toString();
                userLanguage_et.setText(output);
                String input = userLanguage_et.getText().toString();
                if (!input.isEmpty()) {
                    translate(input, codeUser, codeTarget);
                }
            }
        });

        userLanguage_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();

                if (!userLanguage_et.getText().toString().isEmpty()) {
                    pref.setData(string, MyAnno.LAST_TEXT_INPUT);
                } else {
                    pref.setData("", MyAnno.LAST_TEXT_INPUT);
                }

            }
        });

        // get Values
        String codeUser = pref.getStringData(MyAnno.USER_LANGUAGE, true);
        String codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE, true);


        // show values
        userLanguage_tv.setText(getLanguageName(codeUser));
        targetLanguage_tv.setText(getLanguageName(codeTarget));

        userInputLang_tv.setText(getLanguageName(codeUser));
        resultTargetLang_tv.setText(getLanguageName(codeTarget));

        fistTime = true;

        return view;
    }

    public void initAdapter(boolean isTergetlanuage) {
        RecyclerView translatorList_rv = view.findViewById(R.id.translatorList_rv);
        setLanguagesListVisibility(true);
        List<String> languages = TranslateLanguage.getAllLanguages();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        translatorList_rv.setLayoutManager(llm);
        adapter = new
                TranslatorLanguageItemAdapter(getActivity(), languages, isTergetlanuage, this);
        translatorList_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setLanguagesListVisibility(boolean setVisible) {
        if (setVisible) {
            languagesList_ll.setVisibility(View.VISIBLE);
        } else {
            languagesList_ll.setVisibility(View.GONE);
        }
        mainAct.isLanguagesList_llShowing = setVisible;
    }

    @Override
    public void language(boolean isTarget) {
        if (isTarget) {
            String codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE, true);
            targetLanguage_tv.setText(getLanguageName(codeTarget));
            resultTargetLang_tv.setText(getLanguageName(codeTarget));
            languageResult_tv.setText("");
            //            setLanguagesListVisibility(false);
        } else {
            String codeUser = pref.getStringData(MyAnno.USER_LANGUAGE, true);
            userLanguage_tv.setText(getLanguageName(codeUser));
            userInputLang_tv.setText(getLanguageName(codeUser));
            languageResult_tv.setText("");
            //            setLanguagesListVisibility(false);
        }
    }

    public void translate(final String input, final String userLangCode, final String targetLangCode) {
        TranslatorOptions tOption =
                new TranslatorOptions.Builder().setSourceLanguage(userLangCode)
                        .setTargetLanguage(targetLangCode).build();

        final Translator translator = Translation.getClient(tOption);
        translator.translate(input).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                languageResult_tv.setText(s);
                if (!languageResult_tv.getText().toString().isEmpty()) {
                    pref.setData(s, MyAnno.LAST_TEXT_RESULT);
                } else {
                    pref.setData("", MyAnno.LAST_TEXT_RESULT);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getActivity(), e.getMessage(),
//                        Toast.LENGTH_SHORT).show();
//                dialogFun("Wait", e.getMessage());
//                dismissDialog(8000);

                downloadLanguage("Language model size can be 30Mbs. Make sure you are connected to wifi.",
                        userLangCode, targetLangCode, input);

//                if (isConnected())
//                {
//                    dialogFun("Wait", "Selected language model will be downloaded. Please wait");
//                    dismissDialog(8000);
//                }
//                else
//                {
//                    dialogFun("Wait", "Selected language model will be downloaded. Make sure that you are connected to internet. Thank you");
//                    dismissDialog(10000);
//                }
            }
        });


    }

    public String getLanguageName(String languageCode) {
        Locale locale = new Locale(languageCode);

        return locale.getDisplayLanguage(locale);
    }

    public void dialogFun(String title, String message) {
        View view = getLayoutInflater().inflate(R.layout.dialog, null, false);
        TextView title_tv = view.findViewById(R.id.title_tv);
        TextView message_tv = view.findViewById(R.id.message_tv);
        TextView close_tv = view.findViewById(R.id.close_tv);
        TextView ok_tv = view.findViewById(R.id.ok_tv);

        AlertDialog.Builder builder = new
                AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setView(view);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        title_tv.setText(title);
        message_tv.setText(message);
        close_tv.setVisibility(View.GONE);
        ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void dismissDialog(int timeDuration) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, timeDuration);
    }

//    public boolean isConnected() {
//        final boolean[] isConnected = {false};
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//
//            try {
//                ConnectivityManager connectivityManager = (ConnectivityManager)
//                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//
//                NetworkRequest.Builder builder = new NetworkRequest.Builder();
//
//                connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
//                                                                               @Override
//                                                                               public void onAvailable(Network network) {
//
//                                                                                   isConnected[0] = true; // Global Static Variable
//                                                                               }
//
//                                                                               @Override
//                                                                               public void onLost(Network network) {
//                                                                                   isConnected[0] = false; // Global Static Variable
//                                                                               }
//                                                                           }
//                                                                           );
//                isConnected[0] = false;
//            } catch (Exception e) {
//                isConnected[0] = false;
//            }
//        } else {
//            ConnectivityManager cm =
//                    (ConnectivityManager)
//                            getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//
//            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//            isConnected[0] = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
//        }
//        return isConnected[0];
//    }

    public void downloadLanguage(String message,
                                 final String userCode,
                                 final String targetCode,
                                 final String input) {
        View view = getLayoutInflater().inflate(R.layout.dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true).setView(view);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView title_tv = view.findViewById(R.id.title_tv);
        TextView message_tv = view.findViewById(R.id.message_tv);
        TextView close_tv = view.findViewById(R.id.close_tv);
        TextView ok_tv = view.findViewById(R.id.ok_tv);
        ok_tv.setText("Download");

        title_tv.setText("Download");
        message_tv.setText(message);

        close_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslatorOptions tOption =
                        new TranslatorOptions.Builder().setSourceLanguage(userCode)
                                .setTargetLanguage(targetCode).build();

                final Translator translator = Translation.getClient(tOption);

                // condition if wifi is required
                final DownloadConditions condition =
                        new DownloadConditions.Builder().requireWifi().build();

                translator.downloadModelIfNeeded(condition)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                translator.translate(input).addOnSuccessListener(new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        languageResult_tv.setText(s);
                                        if (!languageResult_tv.getText().toString().isEmpty()) {
                                            pref.setData(s, MyAnno.LAST_TEXT_RESULT);
                                        } else {
                                            pref.setData("", MyAnno.LAST_TEXT_RESULT);

                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(getActivity(), e.getMessage(),
//                                                Toast.LENGTH_SHORT).show();
//                                        dialogFun("error", "Unknown error. Please try again");
                                        dismissDialog(12000);
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialogFun("error", "Language is not downloaded due to internet problem or other unknown issue. Please try again");
                        dismissDialog(12000);
                    }
                });
                dialog.dismiss();
            }
        });


        dialog.show();


    }

    @Override
    public void isTrue(boolean isTrue) {
        if (isTrue) {
            setLanguagesListVisibility(false);
        } else {
            setLanguagesListVisibility(true);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (fistTime) {
            userLanguage_et.setText("");
            languageResult_tv.setText("");
            fistTime = false;
        } else {
            String input = pref.getStringData(MyAnno.LAST_TEXT_INPUT);
            String output = pref.getStringData(MyAnno.LAST_TEXT_RESULT);
            userLanguage_et.setText(input);
            languageResult_tv.setText(output);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pref.setData("", MyAnno.LAST_TEXT_INPUT);
        pref.setData("", MyAnno.LAST_TEXT_RESULT);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        pref.setData("", MyAnno.LAST_TEXT_INPUT);
        pref.setData("", MyAnno.LAST_TEXT_RESULT);

    }
}