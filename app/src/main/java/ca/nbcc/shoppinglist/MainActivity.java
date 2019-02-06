package ca.nbcc.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

/**
 *
 * Main Activity
 * @author Yinbin Zuo
 * @date Feb 1st, 2019
 *
 **/
public class MainActivity extends AppCompatActivity {
    public static final int PICK_CONTACT_REQUEST = 1;

    public static final String EXTRA_MSG = MainActivity.class.getSimpleName();

    private LinearLayout svContent;

    private HashMap<String,Integer> cartMap = new HashMap<String,Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svContent = findViewById(R.id.MainActivity_Content);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("CartHistory")) {
                cartMap = (HashMap<String,Integer>)savedInstanceState.getSerializable("CartHistory");

                populateTexts();
            }
        }
    }

    private void populateTexts(){
        final int childCount = svContent.getChildCount();

        AAA:for (String key: cartMap.keySet()) {
            Integer count = cartMap.get(key);

            for (int i = 0; i < childCount; i++) {
                View v = svContent.getChildAt(i);
                if (v instanceof TextView) {
                    TextView temp = (TextView) v;

                    if (temp.getText().toString().contains(key)) {
                        temp.setText(key + ":" + count.intValue());
                        continue AAA;
                    }
                }
            }

            TextView txtView = new TextView(this);
            txtView.setTextSize(20);
            txtView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            txtView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            txtView.setText(key + ":" + count.intValue());
            svContent.addView(txtView);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("CartHistory",cartMap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String goods = data.getStringExtra(SecondActivity.SELECTED_GOODS);
                if (goods != null) {
                    Integer count = cartMap.get(goods);
                    if (count != null) {
                        count = new Integer(count.intValue() + 1);
                        cartMap.put(goods,count);
                    }else{
                        cartMap.put(goods,1);
                    }

                    populateTexts();
                }
            }
        }
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }
}
