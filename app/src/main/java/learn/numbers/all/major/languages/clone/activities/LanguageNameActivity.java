package learn.numbers.all.major.languages.clone.activities;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.adapters.LangAdapter;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.TextChangedInterface;

public class LanguageNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_name);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initAdapter(this, new TextChangedInterface() {
            @Override
            public boolean changed(String val) {
                 return false;
            }
        });
    }

    public void initAdapter(Context context, TextChangedInterface textChanged) {
        ArrayList<String> languages = new ArrayList<>();
        languages.add(MyAnno.Afrikaan);
        languages.add(MyAnno.Chinese);
        languages.add(MyAnno.English);
        languages.add(MyAnno.Estonian);
        languages.add(MyAnno.Finnsh);
        languages.add(MyAnno.French);
//        languages.add(MyAnno.Georgian);
        languages.add(MyAnno.German);
        languages.add(MyAnno.Greek);
        languages.add(MyAnno.Hebrew);
        languages.add(MyAnno.Indonesian);
        languages.add(MyAnno.Irish);
        languages.add(MyAnno.Italian);
        languages.add(MyAnno.Japanese);
        languages.add(MyAnno.Korean);
        languages.add(MyAnno.Latin);
        languages.add(MyAnno.Luxembourgish);
        languages.add(MyAnno.Portuguese);
        languages.add(MyAnno.Russian);
        languages.add(MyAnno.Spanish);
        languages.add(MyAnno.Thai);
        languages.add(MyAnno.Turkish);
        RecyclerView lang_rv = findViewById(R.id.lang_rv);
        TextView done_tv = findViewById(R.id.done_tv);
        LangAdapter adapter = new LangAdapter(context, languages);
        adapter.init(textChanged);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lang_rv.setLayoutManager(layoutManager);
        lang_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        done_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}