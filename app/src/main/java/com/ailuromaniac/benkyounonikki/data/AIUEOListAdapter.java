package com.ailuromaniac.benkyounonikki.data;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_aiueorow, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.aiueo_group)).setText(aiueo.getGroup());

        ((TextView)convertView.findViewById(R.id.aiueo_hiragana_a)).setText(aiueo.getHiraganaA());
        ((TextView)convertView.findViewById(R.id.aiueo_katakana_a)).setText(aiueo.getKatakanaA());
        ((TextView)convertView.findViewById(R.id.aiueo_romaji_a)).setText(aiueo.getRomajiA());

        ((TextView) convertView.findViewById(R.id.aiueo_hiragana_i)).setText(aiueo.getHiraganaI());
        ((TextView)convertView.findViewById(R.id.aiueo_katakana_i)).setText(aiueo.getKatakanaI());
        ((TextView) convertView.findViewById(R.id.aiueo_romaji_i)).setText(aiueo.getRomajiI());

        ((TextView)convertView.findViewById(R.id.aiueo_hiragana_u)).setText(aiueo.getHiraganaU());
        ((TextView)convertView.findViewById(R.id.aiueo_katakana_u)).setText(aiueo.getKatakanaU());
        ((TextView)convertView.findViewById(R.id.aiueo_romaji_u)).setText(aiueo.getRomajiU());

        ((TextView) convertView.findViewById(R.id.aiueo_hiragana_e)).setText(aiueo.getHiraganaE());
        ((TextView)convertView.findViewById(R.id.aiueo_katakana_e)).setText(aiueo.getKatakanaE());
        ((TextView)convertView.findViewById(R.id.aiueo_romaji_e)).setText(aiueo.getRomajiE());

        ((TextView)convertView.findViewById(R.id.aiueo_hiragana_o)).setText(aiueo.getHiraganaO());
        ((TextView)convertView.findViewById(R.id.aiueo_katakana_o)).setText(aiueo.getKatakanaO());
        ((TextView)convertView.findViewById(R.id.aiueo_romaji_o)).setText(aiueo.getRomajiO());

        return convertView;
    }
}
