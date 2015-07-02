package com.ailuromaniac.benkyounonikki.style;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ailuromaniac.benkyounonikki.R;
import com.ailuromaniac.benkyounonikki.dataObject.Content;

/**
 * Created by Aneesa on 7/2/2015.
 */
public class FragmentTextView extends TextView {

    private static final String FRAGMENT_LEFT_TEXTVIEW = "FragmentLeftTextView";

    public FragmentTextView(Context context, Content content) {
        super(context);

        // set layout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        // set textview margins to 5dp
        int margins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                getResources().getDisplayMetrics()));
        layoutParams.setMargins(0, margins, 0, margins);
        this.setLayoutParams(layoutParams);

        // set text size to 25dp
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25,
                getResources().getDisplayMetrics());
        this.setTextSize(textSize);

        // set text color to white
//        this.setTextColor(Color.WHITE);

        // align the text
        if (content.getStyle().equalsIgnoreCase(FRAGMENT_LEFT_TEXTVIEW)) {
            this.setGravity(Gravity.LEFT);
        }

        // set text
        this.setText(content.getContent());
    }
}
