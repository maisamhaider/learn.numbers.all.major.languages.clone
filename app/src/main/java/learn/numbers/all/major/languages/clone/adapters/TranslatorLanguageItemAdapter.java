package learn.numbers.all.major.languages.clone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import learn.numbers.all.major.languages.clone.R;

public class TranslatorLanguageItemAdapter extends
        RecyclerView.Adapter<TranslatorLanguageItemAdapter.LanguageNameHolder> {

    Context context;
    boolean isDownlaod;
    ArrayList<String> languageNamesL;

    public TranslatorLanguageItemAdapter(Context context, boolean isDownlaod, ArrayList<String> languageNamesL) {
        this.context = context;
        this.isDownlaod = isDownlaod;
        this.languageNamesL = languageNamesL;
    }

    @NonNull
    @Override
    public LanguageNameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tranlator_language_items_layout,parent,false);
        return new LanguageNameHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageNameHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return languageNamesL.size();
    }

    class LanguageNameHolder extends RecyclerView.ViewHolder
    {
        public LanguageNameHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
