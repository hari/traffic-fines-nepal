package com.withhari.trafficfinesnepal.extras;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.withhari.trafficfinesnepal.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * The adapter for text
 * Created by Hari Lamichhane on 9/15/2016.
 */
public class TextAdapter extends ArrayAdapter<String> {

    private JSONArray items;
    private Context mContext;

    public TextAdapter(Context context, JSONArray list) {
        super(context, R.layout.text_view);
        mContext = context;
        items = list;
    }

    @Override
    public int getCount() {
        return items.length();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public String getItem(int position) {
        try {
            return items.getString(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextHolder mHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.text_view, parent, false);
            mHolder = new TextHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (TextHolder) convertView.getTag();
        }
        mHolder.mText.setText(Html.fromHtml(getItem(position)));
        return convertView;
    }
}
