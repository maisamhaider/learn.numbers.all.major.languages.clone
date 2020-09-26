package learn.numbers.all.major.languages.clone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import learn.numbers.all.major.languages.clone.R;

public class TranslatorListAdapter extends
        RecyclerView.Adapter<TranslatorListAdapter.SectionHolder> {

    Context context;
    ArrayList<String> sectionNameL;

    public TranslatorListAdapter(Context context, ArrayList<String> sectionNameL) {
        this.context = context;
        this.sectionNameL = sectionNameL;
    }

    @NonNull
    @Override
    public SectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.translator_list_adapter_layout,parent,false);
        return new SectionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return sectionNameL.size();
    }

    class SectionHolder extends RecyclerView.ViewHolder
    {


        TextView title_tv;
        RecyclerView  languageList_rv;
        public SectionHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.title_tv);
            languageList_rv = itemView.findViewById(R.id.languageList_rv);
        }
        public void setAdapterView(ArrayList<String> list,boolean isDownload)
        {
            TranslatorLanguageItemAdapter adapter =
                    new TranslatorLanguageItemAdapter(context,isDownload,list);
                    languageList_rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

    }

}
