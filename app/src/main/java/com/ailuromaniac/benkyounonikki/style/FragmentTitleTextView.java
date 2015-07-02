package com.ailuromaniac.benkyounonikki.style;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.ailuromaniac.benkyounonikki.R;
import com.ailuromaniac.benkyounonikki.dataObject.Content;
import com.ailuromaniac.benkyounonikki.dataObject.Fragment;

/**
 * Created by Aneesa on 6/30/2015.
 */
public class FragmentTitleTextView extends TextView {

    private static final int DRAWABLE_FRAGMENT_HEADER_TEXTVIEW = R.drawable.fragment_header_textview;

    public FragmentTitleTextView(Context context, Fragment fragment) {
        super(context);

        // set layout
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);

        // set textview top and bottom margins to 30dp
        int margins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                getResources().getDisplayMetrics()));
        layoutParams.setMargins(0, margins, 0, margins);
        this.setLayoutParams(layoutParams);

        // set text size to 40dp
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,
                    getResources().getDisplayMetrics());
        this.setTextSize(textSize);

        // set text color to white
        this.setTextColor(Color.WHITE);

        // center the text
        this.setGravity(Gravity.CENTER);

        // set the background shape
        this.setBackgroundResource(DRAWABLE_FRAGMENT_HEADER_TEXTVIEW);

        // set text
        this.setText(fragment.getName());
    }
}
