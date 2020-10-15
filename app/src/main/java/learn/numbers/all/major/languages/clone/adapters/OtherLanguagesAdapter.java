package learn.numbers.all.major.languages.clone.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import learn.numbers.all.major.languages.clone.R;


public class OtherLanguagesAdapter extends RecyclerView.Adapter<OtherLanguagesAdapter.NumbsHolder> {

    private Context context;
    private ArrayList<String> numberArray;
    private String[] numberWordArray;
    private String[] numPronunciationArray;

    TextToSpeech tts;
    private int counter = 0;

    public OtherLanguagesAdapter(Context context, ArrayList<String> numberArray,
                                 String[] numberWordArray, String[] numPronunciationArray) {
        this.context = context;
        this.numberArray = numberArray;
        this.numberWordArray = numberWordArray;
        this.numPronunciationArray = numPronunciationArray;
        tts = new TextToSpeech(context,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                    }
                });

        tts.setLanguage(Locale.US);
        tts.setSpeechRate(1f);
        loadingDialog();
    }

    @NonNull
    @Override
    public NumbsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hundred_numbs_item,
                parent, false);
        return new NumbsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NumbsHolder holder, int position) {

        String langNum = numberArray.get(position);
        String mathNum = String.valueOf(position);
        String numWord = numberWordArray[position];
        String numPronounce = numPronunciationArray[position];

        holder.num_tv1.setText(langNum);
        holder.num_tv2.setText(mathNum);
        holder.numWord_tv.setText(numWord);
        holder.numPronounce_tv.setText(numPronounce);


        holder.hNum_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNumber(holder.numPronounce_tv.getText().toString());
            }
        });


    }

    public void callNumber(final String numString) {

        if (tts.isSpeaking()) {
            tts.stop();
            tts.speak(numString, TextToSpeech.QUEUE_FLUSH,
                    null, null);
            return;
        }
        tts.speak(numString, TextToSpeech.QUEUE_FLUSH,
                null, null);


    }
    @Override
    public int getItemCount() {
        return 101;
    }

    public void loadingDialog() {
        try {
            final ProgressDialog showDialog = ProgressDialog.show(context,
                    context.getString(R.string.app_name), "Please wait",
                    true);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    showDialog.dismiss();
                }
            }, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class NumbsHolder extends RecyclerView.ViewHolder {
        TextView num_tv1, num_tv2, numWord_tv, numPronounce_tv;
        ConstraintLayout hNum_cl;

        public NumbsHolder(@NonNull View itemView) {
            super(itemView);
            num_tv1 = itemView.findViewById(R.id.num_tv1);
            num_tv2 = itemView.findViewById(R.id.num_tv2);
            numWord_tv = itemView.findViewById(R.id.numWord_tv);
            numPronounce_tv = itemView.findViewById(R.id.numPronounce_tv);
            hNum_cl = itemView.findViewById(R.id.hNum_cl);
        }
    }

}
