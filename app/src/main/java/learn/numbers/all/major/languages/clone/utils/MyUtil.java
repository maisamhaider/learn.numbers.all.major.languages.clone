package learn.numbers.all.major.languages.clone.utils;

import android.view.View;
import android.widget.TextView;

public class MyUtil {

    public MyUtil() {
    }

    public void showView(String text, TextView view)
    {
        view.setVisibility(View.VISIBLE);
        view.setText(text);
    }
    public void hideView(String text, TextView view)
    {
        view.setVisibility(View.GONE);
        view.setText(text);
    }
}
