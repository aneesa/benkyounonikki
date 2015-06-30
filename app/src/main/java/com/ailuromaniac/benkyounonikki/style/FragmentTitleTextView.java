package com.ailuromaniac.benkyounonikki.style;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.ailuromaniac.benkyounonikki.R;

/**
 * Created by Aneesa on 6/30/2015.
 */
public class FragmentTitleTextView extends TextView {


    public FragmentTitleTextView(Context context, String text) {
        super(context);

        // set layout
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);

        // 30dp
        int margins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                getResources().getDisplayMetrics()));

        layoutParams.setMargins(margins, margins, margins, margins);

        this.setLayoutParams(layoutParams);

        // set size to 40dp
        float testSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,
                getResources().getDisplayMetrics());
        this.setTextSize(testSize);

        // center the text
        this.setGravity(Gravity.CENTER);

        // set the background shape
        this.setBackgroundResource(R.drawable.fragment_textview_bordered);

        // set text
        this.setText(text);
    }
}
