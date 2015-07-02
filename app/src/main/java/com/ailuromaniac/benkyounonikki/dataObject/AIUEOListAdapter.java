package com.ailuromaniac.benkyounonikki.dataObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ailuromaniac.benkyounonikki.R;

import java.util.List;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class AIUEOListAdapter extends ArrayAdapter<AIUEO> {

    public AIUEOListAdapter(Context context, List<AIUEO> aiueos) {
        super(context, 0, aiueos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AIUEO aiueo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.aiueo_list, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.aiueo_group)).setText(aiueo.getGroup());

        // this can be make better by having AIUEO object to have arrays of Strings instead
        // but since we only have 5 sets of each, this will do for now

        // set A
        this.setListTextView(aiueo.getHiraganaA(), aiueo.getKatakanaA(), aiueo.getRomajiA(),
                ((TextView) convertView.findViewById(R.id.aiueo_hiragana_a)),
                ((TextView) convertView.findViewById(R.id.aiueo_katakana_a)),
                ((TextView) convertView.findViewById(R.id.aiueo_romaji_a)));

        // set I
        this.setListTextView(aiueo.getHiraganaI(), aiueo.getKatakanaI(), aiueo.getRomajiI(),
                ((TextView) convertView.findViewById(R.id.aiueo_hiragana_i)),
                ((TextView) convertView.findViewById(R.id.aiueo_katakana_i)),
                ((TextView) convertView.findViewById(R.id.aiueo_romaji_i)));

        // set U
        this.setListTextView(aiueo.getHiraganaU(), aiueo.getKatakanaU(), aiueo.getRomajiU(),
                ((TextView) convertView.findViewById(R.id.aiueo_hiragana_u)),
                ((TextView) convertView.findViewById(R.id.aiueo_katakana_u)),
                ((TextView) convertView.findViewById(R.id.aiueo_romaji_u)));

        // set E
        this.setListTextView(aiueo.getHiraganaE(), aiueo.getKatakanaE(), aiueo.getRomajiE(),
                ((TextView) convertView.findViewById(R.id.aiueo_hiragana_e)),
                ((TextView) convertView.findViewById(R.id.aiueo_katakana_e)),
                ((TextView) convertView.findViewById(R.id.aiueo_romaji_e)));

        // set O
        this.setListTextView(aiueo.getHiraganaO(), aiueo.getKatakanaO(), aiueo.getRomajiO(),
                ((TextView) convertView.findViewById(R.id.aiueo_hiragana_o)),
                ((TextView) convertView.findViewById(R.id.aiueo_katakana_o)),
                ((TextView) convertView.findViewById(R.id.aiueo_romaji_o)));

        return convertView;
    }

    private void setListTextView(String hiragana, String katakana, String romaji,
                                 TextView hiraganaTV, TextView katakanaTV, TextView romajiTV) {

        hiraganaTV.setText(hiragana);
        katakanaTV.setText(katakana);
        romajiTV.setText(romaji);

        if(hiragana.isEmpty()){
            hiraganaTV.setBackgroundResource(R.drawable.table_header_textview);
            katakanaTV.setBackgroundResource(R.drawable.table_header_textview);
            romajiTV.setBackgroundResource(R.drawable.table_header_textview);
        }else {
            hiraganaTV.setBackgroundResource(R.drawable.hiragana_textview);
            katakanaTV.setBackgroundResource(R.drawable.katakana_textview);
            romajiTV.setBackgroundResource(R.drawable.romaji_textview);
        }
    }
}
