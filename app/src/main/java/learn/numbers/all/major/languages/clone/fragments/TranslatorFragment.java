package learn.numbers.all.major.languages.clone.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.List;
import java.util.Locale;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.adapters.TranslatorLanguageItemAdapter;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.IsTargetLanguage;
import learn.numbers.all.major.languages.clone.preferences.Pref;

public class TranslatorFragment extends BaseFragment implements IsTargetLanguage {

    private View view;
    private View languagesList_ll;
    private TextView userLanguage_tv;
    private TextView targetLanguage_tv;
    private TextView languageResult_tv;
    private TranslatorLanguageItemAdapter adapter;
    private Pref pref;
    private AlertDialog dialog;

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

        // find views
        userLanguage_tv = view.findViewById(R.id.userLanguage_tv);
        targetLanguage_tv = view.findViewById(R.id.targetLanguage_tv);
        languageResult_tv = view.findViewById(R.id.languageResult_tv);
        languagesList_ll = view.findViewById(R.id.languagesList_ll);
        TextView translate_tv = view.findViewById(R.id.translate_tv);
        ImageView switchIv = view.findViewById(R.id.switchIv);
        ImageView back_iv = view.findViewById(R.id.back_iv);
        final EditText userLanguage_et = view.findViewById(R.id.userLanguage_et);

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
        translate_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE);
                String codeUser = pref.getStringData(MyAnno.USER_LANGUAGE);
                String intput = userLanguage_et.getText().toString();
                if (!intput.isEmpty()) {
                    Translate(intput, codeUser, codeTarget);
                }

            }
        });
        switchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeUser = pref.getStringData(MyAnno.USER_LANGUAGE);
                String codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE);
                pref.setData(codeTarget, MyAnno.USER_LANGUAGE);
                pref.setData(codeUser, MyAnno.TARGET_LANGUAGE);
                codeUser = pref.getStringData(MyAnno.USER_LANGUAGE);
                codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE);
                userLanguage_tv.setText(getLanguageName(codeUser));
                targetLanguage_tv.setText(getLanguageName(codeTarget));
            }
        });
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLanguagesListVisibility(false);
            }
        });
        String codeUser = pref.getStringData(MyAnno.USER_LANGUAGE);
        String codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE);

        userLanguage_tv.setText(getLanguageName(codeUser));
        targetLanguage_tv.setText(getLanguageName(codeTarget));

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

    }

    @Override
    public void language(boolean isTarget) {
        if (isTarget) {
            String codeTarget = pref.getStringData(MyAnno.TARGET_LANGUAGE);
            targetLanguage_tv.setText(getLanguageName(codeTarget));
        } else {
            String codeUser = pref.getStringData(MyAnno.USER_LANGUAGE);
            userLanguage_tv.setText(getLanguageName(codeUser));
        }

    }

    public void Translate(String input, String userLangCode, String targetLangCode) {
        TranslatorOptions tOption =
                new TranslatorOptions.Builder().setSourceLanguage(userLangCode)
                        .setTargetLanguage(targetLangCode).build();

        final Translator translator = Translation.getClient(tOption);

        // condition if wifi is required
        final DownloadConditions condition =
                new DownloadConditions.Builder().requireWifi().build();

        translator.downloadModelIfNeeded(condition).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                translator.downloadModelIfNeeded(condition)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                dialogFun("Download","Language model is downloading");
                                if (task.isSuccessful()) {
                                    dismissDialog(0);
                                    dialogFun("Info","Language model is downloaded successfully");
                                    dismissDialog(1000);
//                                    Log.i("Tag1",task.getResult().toString());

                                } else {
//
                                    Log.e("Tag12", task.getException().getMessage());
                                    dialogFun("Info",task.getException().getMessage());
                                    dismissDialog(1000);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Tag2", e.getMessage());
                        dialogFun("Info",e.getMessage());
                        dismissDialog(1000);
                    }
                });

            }
        });

        translator.translate(input).addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    languageResult_tv.setText(task.getResult());
                } else {
                    Toast.makeText(getActivity(), task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(),
                        Toast.LENGTH_SHORT).show();
                dialogFun("Info",e.getMessage());
                dismissDialog(1000);
            }
        });
    }

    public String getLanguageName(String languageCode) {
        Locale locale = new Locale(languageCode);

        return locale.getDisplayLanguage(locale);
    }

    public void dialogFun(String title, String message) {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setCancelable(true)
                .setMessage(message).setCancelable(true);

        dialog = builder.create();
        dialog.show();


    }

    public void dismissDialog(int timeDuration) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, timeDuration);
    }
}