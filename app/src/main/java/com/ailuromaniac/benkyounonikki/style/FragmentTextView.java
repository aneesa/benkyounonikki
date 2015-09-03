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
    private static final String BOXED_TEXTVIEW = "BoxedTextView";

    private static final int DRAWABLE_FRAGMENT_HEADER_TEXTVIEW = R.drawable.fragment_header_textview;
    private static final int DRAWABLE_FRAGMENT_BORDERED_TEXTVIEW = R.drawable.fragment_bordered_textview;
    private static final int DRAWABLE_HIRAGANA_TEXTVIEW = R.drawable.hiragana_textview;
    private static final int DRAWABLE_KATAKANA_TEXTVIEW = R.drawable.katakana_textview;
    private static final int DRAWABLE_ROMAJI_TEXTVIEW = R.drawable.romaji_textview;
    private static final int DRAWABLE_BOXED_TEXTVIEW = R.drawable.boxed_textview;

    // layouts
    private static final LinearLayout.LayoutParams LAYOUTPARAMS_MATCH_WRAP =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
    private static final LinearLayout.LayoutParams LAYOUTPARAMS_WRAP =
            new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
    private static final LinearLayout.LayoutParams LAYOUTPARAMS_MATCH =
            new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

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

        // set padding to 10dp
        int padding = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics()));
        this.setPadding(padding, padding, padding, padding);

        // set the text views ======================================================
        // layoutParams, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
        if(content.getStyle().equalsIgnoreCase(FRAGMENT_TITLE_TEXTVIEW)) {
            this.setTextView(LAYOUTPARAMS_MATCH_WRAP, 30, Gravity.NO_GRAVITY,
                    40, Color.WHITE, Gravity.CENTER, DRAWABLE_FRAGMENT_HEADER_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_SECTION_TITLE_TEXTVIEW)){
            this.setTextView(LAYOUTPARAMS_MATCH_WRAP, 30, Gravity.NO_GRAVITY,
                    30, Color.WHITE, Gravity.CENTER, DRAWABLE_FRAGMENT_HEADER_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_CENTER_TEXTVIEW)){
            this.setTextView(LAYOUTPARAMS_MATCH_WRAP, 5, Gravity.NO_GRAVITY,
                    25, Color.BLACK, Gravity.CENTER, 0);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_LEFT_TEXTVIEW)){
            this.setTextView(LAYOUTPARAMS_MATCH_WRAP, 5, Gravity.NO_GRAVITY,
                    25, Color.BLACK, Gravity.LEFT, 0);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_BORDERED_TEXTVIEW)){
            this.setTextView(LAYOUTPARAMS_WRAP, 5, Gravity.CENTER_HORIZONTAL,
                    25, Color.BLACK, Gravity.CENTER, DRAWABLE_FRAGMENT_BORDERED_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(HIRAGANA_TEXTVIEW)){
            this.setTextView(LAYOUTPARAMS_MATCH, 5, Gravity.NO_GRAVITY,
                    25, Color.BLACK, Gravity.CENTER, DRAWABLE_HIRAGANA_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(KATAKANA_TEXTVIEW)){
            this.setTextView(LAYOUTPARAMS_MATCH, 5, Gravity.NO_GRAVITY,
                    25, Color.BLACK, Gravity.CENTER, DRAWABLE_KATAKANA_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(ROMAJI_TEXTVIEW)){
            this.setTextView(LAYOUTPARAMS_MATCH, 5, Gravity.NO_GRAVITY,
                    25, Color.BLACK, Gravity.CENTER, DRAWABLE_ROMAJI_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(BOXED_TEXTVIEW)){
            this.setTextView(LAYOUTPARAMS_MATCH, 5, Gravity.NO_GRAVITY,
                    25, Color.BLACK, Gravity.CENTER, DRAWABLE_BOXED_TEXTVIEW);
        }
    }

    private void setTextView(LinearLayout.LayoutParams layoutParams, int marginDp, int layoutGravity,
                             int textSizeDp, int textColor, int textGravity, int backgroundResource) {

        // set the layout =====================================================

        // set default textview margins
        int margins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginDp,
                getResources().getDisplayMetrics()));
        layoutParams.setMargins(0, margins, 0, margins);
        layoutParams.gravity = layoutGravity;

        this.setLayoutParams(layoutParams);

        // set the texts =====================================================
        // set default text size to 25dp
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, textSizeDp,
                getResources().getDisplayMetrics());
        this.setTextSize(textSize);

        // set the text color
        this.setTextColor(textColor);

        // align the text
        this.setGravity(textGravity);

        // set the background shape
        if (backgroundResource!=0) {
            this.setBackgroundResource(backgroundResource);
        }
    }
}
