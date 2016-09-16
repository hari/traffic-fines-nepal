package com.withhari.trafficfinesnepal;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.withhari.trafficfinesnepal.extras.Helper;
import com.withhari.trafficfinesnepal.extras.TextAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnClickListener {

    int x = 0;
    private TextView btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mListView = (ListView) findViewById(R.id.list);
        mListView.setDivider(null);

        Typeface tf = Typeface.createFromAsset(getAssets(), "preeti.TTF");

        TextView mTitle = (TextView) findViewById(R.id.page_title);
        mTitle.setTypeface(tf);

        final TextView btnOne, btnTwo, btnThree, btnFour;

        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);
        btnThree = (TextView) findViewById(R.id.btnThree);
        btnFour = (TextView) findViewById(R.id.btnFour);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnThree.setOnClickListener(this);

        btnOne.setTypeface(tf);
        btnTwo.setTypeface(tf);
        btnFour.setTypeface(tf);
        btnThree.setTypeface(tf);

        btnOne.setText(Html.fromHtml("<small>?</small><br />%))"));
        btnTwo.setText(Html.fromHtml("<small>?</small><br />!)))"));
        btnThree.setText(Html.fromHtml("<small>?</small><br />!%))"));
        btnFour.setText("cGo");

        btnSelect = (TextView) findViewById(R.id.btnSelector);
        btnSelect.setTypeface(tf);

        GradientDrawable gdb = new GradientDrawable();
        gdb.setCornerRadius(72);
        gdb.setColor(Color.argb(200, 255, 255, 255));
        if (Build.VERSION.SDK_INT >= 16) {
            btnSelect.setBackground(gdb);
        } else {
            btnSelect.setBackgroundDrawable(gdb);
        }

        showList("500.json");
        btnSelect.setText(btnOne.getText());
        select(0, btnOne);
        if (Helper.isFirstRun(this)) {
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Helper.setFirstRun(getApplicationContext(), false);
                            showList("other.json");
                            select(3, btnFour);
                        }
                    });
                }
            }, 1000);
        }
    }

    private void showList(String name) {
        try {
            JSONObject item = new JSONObject(Helper.loadData(this, name));
            ListView mListView = (ListView) findViewById(R.id.list);
            TextView mTitle = (TextView) findViewById(R.id.page_title);
            mTitle.setText(item.getString("title"));
            mListView.setAdapter(new TextAdapter(this, item.getJSONArray("values")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void select(int i, final View btn) {
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) btnSelect.getLayoutParams();
        final int FinalPos = i * params.width;
        if (FinalPos == x) {
            return;
        }
        final Timer t = new Timer();
        btnSelect.setText("");
        final boolean up = (FinalPos > x);
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (up) {
                    if (x < FinalPos) {
                        x++;
                    } else {
                        x = FinalPos;
                        t.cancel();
                    }
                } else {
                    if (x > FinalPos) {
                        x--;
                    } else {
                        x = FinalPos;
                        t.cancel();
                    }
                }
                params.setMargins(x, 0, 0, 0);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (FinalPos == x) {
                            btnSelect.setText(((TextView) btn).getText());
                        }
                        btnSelect.setLayoutParams(params);
                    }
                });
            }
        }, 0, 1);
    }

    @Override
    public void onClick(View v) {
        int i = 0;
        switch (v.getId()) {
            case R.id.btnOne:
                i = 0;
                showList("500.json");
                break;
            case R.id.btnTwo:
                i = 1;
                showList("1000.json");
                break;
            case R.id.btnThree:
                i = 2;
                showList("1500.json");
                break;
            case R.id.btnFour:
                i = 3;
                showList("other.json");
                break;
        }
        select(i, v);
    }
}
