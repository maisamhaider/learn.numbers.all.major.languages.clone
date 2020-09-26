package learn.numbers.all.major.languages.clone.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.preferences.Pref;


public class CustomAct extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initConverters();
       Pref preferences = new Pref(CustomAct.this);
        final EditText input_mEt = findViewById(R.id.inputNumber_mEt);
        TextView customSLang_mtv = findViewById(R.id.customActSLang_mtv);
        final TextView result_mTv = findViewById(R.id.result_mTv);
        final ImageView custom_iv = findViewById(R.id.custom_num_speak_iv);
        TextView convert_tv = findViewById(R.id.convertNumber_tv);

        String sLanguage = preferences.getStringData(MyAnno.S_LANGUAGE_KEY);
        customSLang_mtv.setText(sLanguage);

        convert_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input_mEt.getText().toString().matches("")) {
                    long num = Long.parseLong(input_mEt.getText().toString());
                    result_mTv.setText(converter.convertNumber(num));
                    String numWord = result_mTv.getText().toString();
                    custom_iv.setVisibility(View.VISIBLE);
                    callNumber(numWord);

                }
            }
        });
        custom_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nWord = result_mTv.getText().toString();
                callNumber(nWord);
            }
        });
        input_mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!input_mEt.getText().toString().matches("")) {
                    long num = Long.parseLong(input_mEt.getText().toString());
                    result_mTv.setText(converter.convertNumber(num));

                }
            }
        });

    }

    @Override
    protected void onPause() {
        shutDown();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        shutDown();
        super.onDestroy();
    }

}