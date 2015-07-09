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

    private static final String FRAGMENT_TITLE_TEXTVIEW = "FragmentTitleTextView";
    private static final String FRAGMENT_SECTION_TITLE_TEXTVIEW = "FragmentSectionTitleTextView";
    private static final String FRAGMENT_CENTER_TEXTVIEW = "FragmentCenterTextView";
    private static final String FRAGMENT_LEFT_TEXTVIEW = "FragmentLeftTextView";
    private static final String FRAGMENT_BORDERED_TEXTVIEW = "FragmentBorderedTextView";
    private static final String HIRAGANA_TEXTVIEW = "HiraganaTextView";
    private static final String KATAKANA_TEXTVIEW = "KatakanaTextView";
    private static final String ROMAJI_TEXTVIEW = "RomajiTextView";

    private static final int DRAWABLE_FRAGMENT_HEADER_TEXTVIEW = R.drawable.fragment_header_textview;
    private static final int DRAWABLE_FRAGMENT_BORDERED_TEXTVIEW = R.drawable.fragment_bordered_textview;
    private static final int DRAWABLE_HIRAGANA_TEXTVIEW = R.drawable.hiragana_textview;
    private static final int DRAWABLE_KATAKANA_TEXTVIEW = R.drawable.katakana_textview;
    private static final int DRAWABLE_ROMAJI_TEXTVIEW = R.drawable.romaji_textview;

    public FragmentTextView(Context context, Content content) {
        super(context);

        // set the defaults ======================================================

        // this will not be a conflict because the ids will be searched by views,
        // and each textview will not have the same position in a view
        this.setId(content.getId());

        // set this textview to focusable
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);

        // set text
        this.setText(content.getContent());

        // align the text to center
        this.setGravity(Gravity.CENTER);

        // set padding to 10dp
        int padding = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics()));
        this.setPadding(padding, padding, padding, padding);

        // set the defaults ======================================================

        // set layout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        if(content.getStyle().equalsIgnoreCase(FRAGMENT_TITLE_TEXTVIEW) ||
                content.getStyle().equalsIgnoreCase(FRAGMENT_SECTION_TITLE_TEXTVIEW)) {
            this.setFragmentTitleTextview(layoutParams, content.getStyle());
        } else {
            this.setFragmentTextview(layoutParams, content.getStyle());
        }
    }

    private void setFragmentTitleTextview (LinearLayout.LayoutParams layoutParams, String style) {

        // set default textview margins to 30dp
        int margins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                getResources().getDisplayMetrics()));
        layoutParams.setMargins(0, margins, 0, margins);
        this.setLayoutParams(layoutParams);

        // set default text size to 40dp
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,
                getResources().getDisplayMetrics());

        if(style.equalsIgnoreCase(FRAGMENT_SECTION_TITLE_TEXTVIEW)) {
            // set default text size to 30dp
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                    getResources().getDisplayMetrics());
        }
        this.setTextSize(textSize);

        // set text color to white
        this.setTextColor(Color.WHITE);

        // set the background shape
        this.setBackgroundResource(DRAWABLE_FRAGMENT_HEADER_TEXTVIEW);
    }

    private void setFragmentTextview(LinearLayout.LayoutParams layoutParams, String style) {

        // set default textview margins to 5dp
        int margins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                getResources().getDisplayMetrics()));
        layoutParams.setMargins(0, margins, 0, margins);
        this.setLayoutParams(layoutParams);

        // set text size to 25dp
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25,
                getResources().getDisplayMetrics());
        this.setTextSize(textSize);

        if (style.equalsIgnoreCase(FRAGMENT_LEFT_TEXTVIEW)) {
            // align the text to left
            this.setGravity(Gravity.LEFT);
        } else if (style.equalsIgnoreCase(FRAGMENT_BORDERED_TEXTVIEW)) {
            this.setFragmentBorderedTextview(margins, textSize);
        } else if (style.equalsIgnoreCase(HIRAGANA_TEXTVIEW) ||
                    style.equalsIgnoreCase(KATAKANA_TEXTVIEW) ||
                    style.equalsIgnoreCase(ROMAJI_TEXTVIEW)) {
            this.setJapaneseTextview(style, margins, textSize);
        }
    }

    private void setFragmentBorderedTextview(int margins, float textSize){
        // set layout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        // set default textview margins to 5dp
        layoutParams.setMargins(0, margins, 0, margins);
        layoutParams.gravity = Gravity.CENTER;
        this.setLayoutParams(layoutParams);

        // set text size to 25dp
        this.setTextSize(textSize);

        // set the background shape
        this.setBackgroundResource(DRAWABLE_FRAGMENT_BORDERED_TEXTVIEW);
    }

    private void setJapaneseTextview(String style, int margins, float textSize) {

        // set layout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        // set default textview margins to 5dp
        layoutParams.setMargins(0, margins, 0, margins);
        this.setLayoutParams(layoutParams);

        // set text size to 25dp
        this.setTextSize(textSize);

        // set the background shape
        if(style.equalsIgnoreCase(HIRAGANA_TEXTVIEW)) {
            this.setBackgroundResource(DRAWABLE_HIRAGANA_TEXTVIEW);
        } else if(style.equalsIgnoreCase(KATAKANA_TEXTVIEW)) {
            this.setBackgroundResource(DRAWABLE_KATAKANA_TEXTVIEW);
        } else if(style.equalsIgnoreCase(ROMAJI_TEXTVIEW)){
            this.setBackgroundResource(DRAWABLE_ROMAJI_TEXTVIEW);
        }
    }
}
