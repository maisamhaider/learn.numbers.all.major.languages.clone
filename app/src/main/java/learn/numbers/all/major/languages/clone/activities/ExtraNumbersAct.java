package learn.numbers.all.major.languages.clone.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.preferences.Pref;


public class ExtraNumbersAct extends BaseAct implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_numbers);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        try {


        initConverters();
        loadingDialog();
        ConstraintLayout cl0 = findViewById(R.id.zero_cl);
        ConstraintLayout cl100 = findViewById(R.id.hundred_cl);
        ConstraintLayout cl1000 = findViewById(R.id.thousand_cl);
        ConstraintLayout million_cl = findViewById(R.id.million_cl);
        ConstraintLayout billion_cl = findViewById(R.id.billion_cl);
        ConstraintLayout trillion_cl = findViewById(R.id.trillion_cl);

        TextView targetWordNum_tv0 = findViewById(R.id.targetWordNum_tvZero);
        TextView targetWordNum_tv100 = findViewById(R.id.targetWordNum_tvHundred);
        TextView targetWordNum_tv1000 = findViewById(R.id.targetWordNum_tvThousand);
        TextView millionTarget_tv = findViewById(R.id.million_tv);
        TextView billionTarget_tv = findViewById(R.id.billion_tv);
        TextView trillionTarget_tv = findViewById(R.id.trillion_tv);
        TextView sNumSLang_tv = findViewById(R.id.sNumSLang_tv);
        String sLang = new Pref(this).getStringData(MyAnno.S_LANGUAGE_KEY,false);
        sNumSLang_tv.setText(sLang);
        setNumWordInTvFun(targetWordNum_tv0);
        setNumWordInTvFun(targetWordNum_tv100);
        setNumWordInTvFun(targetWordNum_tv1000);
        setNumWordInTvFun(millionTarget_tv);
        setNumWordInTvFun(billionTarget_tv);
        setNumWordInTvFun(trillionTarget_tv);

        cl0.setOnClickListener(this);
        cl100.setOnClickListener(this);
        cl1000.setOnClickListener(this);
        million_cl.setOnClickListener(this);
        billion_cl.setOnClickListener(this);
        trillion_cl.setOnClickListener(this);
        }
        catch (Exception e)
        {
            Log.e("error_three",e.getMessage());
        }
    }
    public void setNumWordInTvFun(TextView view) {
        switch (view.getId()) {
            case R.id.targetWordNum_tvZero:
                view.setText(converter.selectedNumber(MyAnno.Zero));
                break;
            case R.id.targetWordNum_tvHundred:
                view.setText(converter.selectedNumber(MyAnno.Hundred));
                break;
            case R.id.targetWordNum_tvThousand:
                view.setText(converter.selectedNumber(MyAnno.Thousand));
                break;
            case R.id.million_tv:
                view.setText(converter.selectedNumber(MyAnno.Million));
                break;
            case R.id.billion_tv:
                view.setText(converter.selectedNumber(MyAnno.Billion));
                break;
            case R.id.trillion_tv:
                view.setText(converter.selectedNumber(MyAnno.Trillion));
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zero_cl:
                callNumber(converter.selectedNumber(MyAnno.Zero));
                break;
            case R.id.hundred_cl:
                callNumber(converter.selectedNumber(MyAnno.Hundred));
                break;
            case R.id.thousand_cl:
                callNumber(converter.selectedNumber(MyAnno.Thousand));
                break;
            case R.id.million_cl:
                callNumber(converter.selectedNumber(MyAnno.Million));
                break;
            case R.id.billion_cl:
                callNumber(converter.selectedNumber(MyAnno.Billion));
                break;
            case R.id.trillion_cl:
                callNumber(converter.selectedNumber(MyAnno.Trillion));
                break;
        }
    }




}