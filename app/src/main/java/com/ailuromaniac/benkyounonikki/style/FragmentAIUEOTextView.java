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
public class FragmentAIUEOTextView extends TextView {

    private static final String TABLE_HEADER_TEXTVIEW = "TableHeaderTextView";
    private static final String TABLE_COLUMN_HEADER_TEXTVIEW = "TableColumnHeaderTextView";
    private static final String HIRAGANA_TEXTVIEW = "HiraganaTextView";
    private static final String KATAKANA_TEXTVIEW = "KatakanaTextView";
    private static final String ROMAJI_TEXTVIEW = "RomajiTextView";

    private static final int DRAWABLE_TABLE_HEADER_TEXTVIEW = R.drawable.table_header_textview;
    private static final int DRAWABLE_HIRAGANA_TEXTVIEW = R.drawable.hiragana_textview;
    private static final int DRAWABLE_KATAKANA_TEXTVIEW = R.drawable.katakana_textview;
    private static final int DRAWABLE_ROMAJI_TEXTVIEW = R.drawable.romaji_textview;

    public FragmentAIUEOTextView(Context context, Content content) {
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

        // set text size to 25dp
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25,
                            getResources().getDisplayMetrics());
        this.setTextSize(textSize);

        // align the text to center
        this.setGravity(Gravity.CENTER);

        // set padding to 3dp
        int padding = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,
                getResources().getDisplayMetrics()));
        this.setPadding(padding, padding, padding, padding);

        if(content.getStyle().equalsIgnoreCase(TABLE_COLUMN_HEADER_TEXTVIEW)) {
            this.setTableColumnHeaderTextview();
        } else {
            this.setFiveTextview(content.getStyle());
        }
    }

    private void setTableColumnHeaderTextview() {
        // set layout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.15f);

        this.setLayoutParams(layoutParams);

        this.setTableHeaderColorAndBackground();
    }

    private void setFiveTextview(String style) {

        // set layout
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.2f);

        this.setLayoutParams(layoutParams);

        // set the background shape
        if(style.equalsIgnoreCase(TABLE_HEADER_TEXTVIEW)) {
            this.setTableHeaderColorAndBackground();
        }
        else if(style.equalsIgnoreCase(HIRAGANA_TEXTVIEW)) {
            this.setBackgroundResource(DRAWABLE_HIRAGANA_TEXTVIEW);
        } else if(style.equalsIgnoreCase(KATAKANA_TEXTVIEW)) {
            this.setBackgroundResource(DRAWABLE_KATAKANA_TEXTVIEW);
        } else if(style.equalsIgnoreCase(ROMAJI_TEXTVIEW)){
            this.setBackgroundResource(DRAWABLE_ROMAJI_TEXTVIEW);
        }
    }

    private void setTableHeaderColorAndBackground(){
        // set text color to white
        this.setTextColor(Color.WHITE);

        // set the background shape
        this.setBackgroundResource(DRAWABLE_TABLE_HEADER_TEXTVIEW);
    }
}
