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
    private static final String FRAGMENT_BOXED_TEXTVIEW = "FragmentBoxedTextView";
    private static final String HIRAGANA_TEXTVIEW = "HiraganaTextView";
    private static final String KATAKANA_TEXTVIEW = "KatakanaTextView";
    private static final String ROMAJI_TEXTVIEW = "RomajiTextView";
    private static final String HIRAGANA_RIGHT_TEXTVIEW = "HiraganaRightTextView";
    private static final String HIRAGANA_LEFT_TEXTVIEW = "HiraganaLeftTextView";
    private static final String HIRAGANA_WRAPPED_TEXTVIEW = "HiraganaWrappedTextView";

    private static final int DRAWABLE_FRAGMENT_HEADER_TEXTVIEW = R.drawable.fragment_header_textview;
    private static final int DRAWABLE_FRAGMENT_BORDERED_TEXTVIEW = R.drawable.fragment_bordered_textview;
    private static final int DRAWABLE_FRAGMENT_BOXED_TEXTVIEW = R.drawable.fragment_boxed_textview;
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

        // set padding to 10dp
        int padding = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics()));
        this.setPadding(padding, padding, padding, padding);

        // set the text views ======================================================
        if(content.getStyle().equalsIgnoreCase(FRAGMENT_TITLE_TEXTVIEW)) {

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 30, Gravity.CENTER_HORIZONTAL,
                    34, Color.WHITE, Gravity.CENTER, DRAWABLE_FRAGMENT_HEADER_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_SECTION_TITLE_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 30, Gravity.CENTER_HORIZONTAL,
                    24, Color.WHITE, Gravity.CENTER, DRAWABLE_FRAGMENT_HEADER_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_CENTER_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.CENTER, 0);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_LEFT_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.LEFT, 0);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_BORDERED_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.CENTER, DRAWABLE_FRAGMENT_BORDERED_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(FRAGMENT_BOXED_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.CENTER, DRAWABLE_FRAGMENT_BOXED_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(HIRAGANA_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.CENTER, DRAWABLE_HIRAGANA_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(KATAKANA_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.CENTER, DRAWABLE_KATAKANA_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(ROMAJI_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.CENTER, DRAWABLE_ROMAJI_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(HIRAGANA_RIGHT_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.RIGHT, DRAWABLE_HIRAGANA_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(HIRAGANA_LEFT_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.LEFT, DRAWABLE_HIRAGANA_TEXTVIEW);
        }
        else if (content.getStyle().equalsIgnoreCase(HIRAGANA_WRAPPED_TEXTVIEW)){

            // layout width, layout height, marginDp, layoutGravity, textSizeDp, textColor, textGravity, backgroundResource
            this.setTextView(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 5, Gravity.CENTER_HORIZONTAL,
                    20, Color.BLACK, Gravity.CENTER, DRAWABLE_HIRAGANA_TEXTVIEW);
        }
    }

    private void setTextView(int width, int height, int marginDp, int layoutGravity,
                             int textSizeDp, int textColor, int textGravity, int backgroundResource) {

        // set the layout =====================================================
        LinearLayout.LayoutParams curLayoutParams =
                new LinearLayout.LayoutParams(width, height, 1.0f);

        // set default textview margins
        int margins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginDp,
                getResources().getDisplayMetrics()));
        curLayoutParams.setMargins(0, margins, 0, margins);
        curLayoutParams.gravity = layoutGravity;

        this.setLayoutParams(curLayoutParams);

        // set the texts =====================================================
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeDp);

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
