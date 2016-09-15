package com.withhari.trafficfinesnepal.extras;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.withhari.trafficfinesnepal.R;

/**
 * A view holder
 * Created by Hari Lamichhane on 9/15/2016.
 */
public class TextHolder {
    public TextView mText;

    public TextHolder(View convertView) {
        mText = (TextView) convertView.findViewById(R.id.title);
        mText.setTypeface(Typeface.createFromAsset(
                convertView.getContext().getAssets(), "preeti.TTF"
        ));
    }

}
