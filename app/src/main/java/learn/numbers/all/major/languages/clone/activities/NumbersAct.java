package learn.numbers.all.major.languages.clone.activities;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdView;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.preferences.Pref;


public class NumbersAct extends BaseAct implements View.OnClickListener {

    private String WHICH_NUMBERS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initConverters();
        WHICH_NUMBERS = getIntent().getStringExtra(MyAnno.Which_Number);
        AdView aView = findViewById(R.id.numberAct_adView);
        adView(aView);  
        TextView num_tv1 = findViewById(R.id.num_tv1);
        TextView num_tv2 = findViewById(R.id.num_tv2);
        TextView num_tv3 = findViewById(R.id.num_tv3);
        TextView num_tv4 = findViewById(R.id.num_tv4);
        TextView num_tv5 = findViewById(R.id.num_tv5);
        TextView num_tv6 = findViewById(R.id.num_tv6);
        TextView num_tv7 = findViewById(R.id.num_tv7);
        TextView num_tv8 = findViewById(R.id.um_tv8);
        TextView num_tv9 = findViewById(R.id.num_tv9);
        TextView thisToThat_tv = findViewById(R.id.t_To_t_tv);
        TextView numSLang_tv = findViewById(R.id.sLang_tv);
        String sLang = new Pref(this).getStringData(MyAnno.S_LANGUAGE_KEY);
        numSLang_tv.setText(sLang);
        ConstraintLayout clOne = findViewById(R.id.clOne);
        ConstraintLayout clTwo = findViewById(R.id.clTwo);
        ConstraintLayout clThree = findViewById(R.id.clThree);
        ConstraintLayout clFour = findViewById(R.id.clFour);
        ConstraintLayout clFive = findViewById(R.id.clFive);
        ConstraintLayout clSix = findViewById(R.id.clSix);
        ConstraintLayout clSeven = findViewById(R.id.clSeven);
        ConstraintLayout clEight = findViewById(R.id.clEight);
        ConstraintLayout clNine = findViewById(R.id.clNine);

        TextView tvOne = findViewById(R.id.tvOne);
        TextView tvTwo = findViewById(R.id.tvTwo);
        TextView tvThree = findViewById(R.id.tvThree);
        TextView tvFour = findViewById(R.id.tvFour);
        TextView tvFive = findViewById(R.id.tvFive);
        TextView tvSix = findViewById(R.id.tvSix);
        TextView tvSeven = findViewById(R.id.tvSeven);
        TextView tvEight = findViewById(R.id.tvEight);
        TextView tvNine = findViewById(R.id.tvNine);


        if (WHICH_NUMBERS.matches(MyAnno.One_tO_9)) {
            OneToNineFun(num_tv1);
            OneToNineFun(num_tv2);
            OneToNineFun(num_tv3);
            OneToNineFun(num_tv4);
            OneToNineFun(num_tv5);
            OneToNineFun(num_tv6);
            OneToNineFun(num_tv7);
            OneToNineFun(num_tv8);
            OneToNineFun(num_tv9);
            thisToThat_tv.setText("1 to 9");

        } else if (WHICH_NUMBERS.matches(MyAnno.Eleven_tO_19)) {
            ElevenToNineteenFun(num_tv1, tvOne);
            ElevenToNineteenFun(num_tv2, tvTwo);
            ElevenToNineteenFun(num_tv3, tvThree);
            ElevenToNineteenFun(num_tv4, tvFour);
            ElevenToNineteenFun(num_tv5, tvFive);
            ElevenToNineteenFun(num_tv6, tvSix);
            ElevenToNineteenFun(num_tv7, tvSeven);
            ElevenToNineteenFun(num_tv8, tvEight);
            ElevenToNineteenFun(num_tv9, tvNine);
            thisToThat_tv.setText("11 to 19");


        } else if (WHICH_NUMBERS.matches(MyAnno.Ten_tO_90)) {
            TenToNinety(num_tv1, tvOne);
            TenToNinety(num_tv2, tvTwo);
            TenToNinety(num_tv3, tvThree);
            TenToNinety(num_tv4, tvFour);
            TenToNinety(num_tv5, tvFive);
            TenToNinety(num_tv6, tvSix);
            TenToNinety(num_tv7, tvSeven);
            TenToNinety(num_tv8, tvEight);
            TenToNinety(num_tv9, tvNine);
            thisToThat_tv.setText("10 to 90");
        }

        clOne.setOnClickListener(this);
        clTwo.setOnClickListener(this);
        clThree.setOnClickListener(this);
        clFour.setOnClickListener(this);
        clFive.setOnClickListener(this);
        clSix.setOnClickListener(this);
        clSeven.setOnClickListener(this);
        clEight.setOnClickListener(this);
        clNine.setOnClickListener(this);
    }

    public void OneToNineFun(TextView view) {
        switch (view.getId()) {
            case R.id.num_tv1:
                view.setText(converter.convertNumber(1));
                break;
            case R.id.num_tv2:
                view.setText(converter.convertNumber(2));
                break;
            case R.id.num_tv3:
                view.setText(converter.convertNumber(3));
                break;
            case R.id.num_tv4:
                view.setText(converter.convertNumber(4));
                break;
            case R.id.num_tv5:
                view.setText(converter.convertNumber(5));
                break;
            case R.id.num_tv6:
                view.setText(converter.convertNumber(6));
                break;
            case R.id.num_tv7:
                view.setText(converter.convertNumber(7));
                break;
            case R.id.um_tv8:
                view.setText(converter.convertNumber(8));
                break;
            case R.id.num_tv9:
                view.setText(converter.convertNumber(9));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void ElevenToNineteenFun(TextView view, TextView textView) {
        switch (view.getId()) {
            case R.id.num_tv1:
                view.setText(converter.convertNumber(11));
                textView.setText("11");
                break;
            case R.id.num_tv2:
                view.setText(converter.convertNumber(12));
                textView.setText("12");
                break;
            case R.id.num_tv3:
                view.setText(converter.convertNumber(13));
                textView.setText("13");
                break;
            case R.id.num_tv4:
                view.setText(converter.convertNumber(14));
                textView.setText("14");
                break;
            case R.id.num_tv5:
                view.setText(converter.convertNumber(15));
                textView.setText("15");
                break;
            case R.id.num_tv6:
                view.setText(converter.convertNumber(16));
                textView.setText("16");
                break;
            case R.id.num_tv7:
                view.setText(converter.convertNumber(17));
                textView.setText("17");
                break;
            case R.id.um_tv8:
                view.setText(converter.convertNumber(18));
                textView.setText("18");
                break;
            case R.id.num_tv9:
                view.setText(converter.convertNumber(19));
                textView.setText("19");
                break;

        }
    }

    @SuppressLint("SetTextI18n")
    public void TenToNinety(TextView viewNumberWords, TextView viewNumber) {
        switch (viewNumberWords.getId()) {
            case R.id.num_tv1:
                viewNumberWords.setText(converter.convertNumber(10));
                viewNumber.setText("10");
                break;
            case R.id.num_tv2:
                viewNumberWords.setText(converter.convertNumber(20));
                viewNumber.setText("20");
                break;
            case R.id.num_tv3:
                viewNumberWords.setText(converter.convertNumber(30));
                viewNumber.setText("30");
                break;
            case R.id.num_tv4:
                viewNumberWords.setText(converter.convertNumber(40));
                viewNumber.setText("40");
                break;
            case R.id.num_tv5:
                viewNumberWords.setText(converter.convertNumber(50));
                viewNumber.setText("50");
                break;
            case R.id.num_tv6:
                viewNumberWords.setText(converter.convertNumber(60));
                viewNumber.setText("60");
                break;
            case R.id.num_tv7:
                viewNumberWords.setText(converter.convertNumber(70));
                viewNumber.setText("70");
                break;
            case R.id.um_tv8:
                viewNumberWords.setText(converter.convertNumber(80));
                viewNumber.setText("80");
                break;
            case R.id.num_tv9:
                viewNumberWords.setText(converter.convertNumber(90));
                viewNumber.setText("90");
                break;

        }
    }


    @Override
    public void onClick(View view) {
        if (WHICH_NUMBERS.matches(MyAnno.One_tO_9)) {
            switch (view.getId()) {
                case R.id.clOne:
                    callNumber(converter.convertNumber(1));
                    break;
                case R.id.clTwo:
                    callNumber(converter.convertNumber(2));
                    break;
                case R.id.clThree:
                    callNumber(converter.convertNumber(3));
                    break;
                case R.id.clFour:
                    callNumber(converter.convertNumber(4));
                    break;
                case R.id.clFive:
                    callNumber(converter.convertNumber(5));
                    break;
                case R.id.clSix:
                    callNumber(converter.convertNumber(6));
                    break;
                case R.id.clSeven:
                    callNumber(converter.convertNumber(7));
                    break;
                case R.id.clEight:
                    callNumber(converter.convertNumber(8));
                    break;
                case R.id.clNine:
                    callNumber(converter.convertNumber(9));
                    break;
            }

        } else if (WHICH_NUMBERS.matches(MyAnno.Eleven_tO_19)) {
            switch (view.getId()) {
                case R.id.clOne:
                    callNumber(converter.convertNumber(11));
                    break;
                case R.id.clTwo:
                    callNumber(converter.convertNumber(12));
                    break;
                case R.id.clThree:
                    callNumber(converter.convertNumber(13));
                    break;
                case R.id.clFour:
                    callNumber(converter.convertNumber(14));
                    break;
                case R.id.clFive:
                    callNumber(converter.convertNumber(15));
                    break;
                case R.id.clSix:
                    callNumber(converter.convertNumber(16));
                    break;
                case R.id.clSeven:
                    callNumber(converter.convertNumber(17));
                    break;
                case R.id.clEight:
                    callNumber(converter.convertNumber(18));
                    break;
                case R.id.clNine:
                    callNumber(converter.convertNumber(19));
                    break;
            }
        } else if (WHICH_NUMBERS.matches(MyAnno.Ten_tO_90)) {
            switch (view.getId()) {
                case R.id.clOne:
                    callNumber(converter.convertNumber(10));
                    break;
                case R.id.clTwo:
                    callNumber(converter.convertNumber(20));
                    break;
                case R.id.clThree:
                    callNumber(converter.convertNumber(30));
                    break;
                case R.id.clFour:
                    callNumber(converter.convertNumber(40));
                    break;
                case R.id.clFive:
                    callNumber(converter.convertNumber(50));
                    break;
                case R.id.clSix:
                    callNumber(converter.convertNumber(60));
                    break;
                case R.id.clSeven:
                    callNumber(converter.convertNumber(70));
                    break;
                case R.id.clEight:
                    callNumber(converter.convertNumber(80));
                    break;
                case R.id.clNine:
                    callNumber(converter.convertNumber(90));
                    break;
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        shutDown();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shutDown();
    }


}