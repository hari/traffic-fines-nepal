package com.withhari.trafficfinesnepal;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.withhari.trafficfinesnepal.extras.Helper;
import com.withhari.trafficfinesnepal.extras.TextAdapter;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mListView = (ListView) findViewById(R.id.list);
        mListView.setDivider(null);

        TextView btnOne, btnTwo, btnThree, btnFour;
        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);
        btnThree = (TextView) findViewById(R.id.btnThree);
        btnFour = (TextView) findViewById(R.id.btnFour);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnThree.setOnClickListener(this);

        btnOne.setText(Html.fromHtml("<small>Rs</small><br /><b>500</b>"));
        btnTwo.setText(Html.fromHtml("<small>Rs</small><br /><b>1000</b>"));
        btnThree.setText(Html.fromHtml("<small>Rs</small><br /><b>1500</b>"));
        btnFour.setText(Html.fromHtml("<small>+</small><br /><b>Other</b>"));

        showList("500.json");
    }

    private void showList(String name) {
        try {
            JSONObject item = new JSONObject(Helper.loadData(this, name));
            ListView mListView = (ListView) findViewById(R.id.list);
            mListView.setAdapter(new TextAdapter(this, item.getJSONArray("values")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                showList("500.json");
                break;
            case R.id.btnTwo:
                showList("1000.json");
                break;
            case R.id.btnThree:
                showList("1500.json");
                break;
            case R.id.btnFour:
                showList("other.json");
                break;
        }
    }
}
