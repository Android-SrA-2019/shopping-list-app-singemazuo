package ca.nbcc.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Second Activity
 * @author Yinbin Zuo
 * @date Feb 1st, 2019
 *
 **/
public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_ITEMS = "com.nbcc.android.exercise.shoppinglist.secondactivity.extraitem";

    public static final String SELECTED_GOODS = "SelectedGoods";

    private LinearLayout svContent;

    private String[] resources = {"watermelon",
        "banana",
        "grape",
        "blueberry",
        "peach",
        "tangerine",
        "mango",
        "dragonfruit",
        "coconut",
        "pineapple",
        "star fruit",
        "avocado",
        "tomato",
        "cherry",
        "lime",
        "raspberry",
        "kiwi",
        "boysenberry",
        "apricot",
        "satsuma"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        svContent = findViewById(R.id.content);
        for (String fruit : resources){
            Button button = new Button(this);
            button.setText(fruit);
            button.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent replyIntent = new Intent();
                    replyIntent.putExtra(SELECTED_GOODS,((Button)v).getText());
                    setResult(Activity.RESULT_OK,replyIntent);
                    finish();
                }
            });
            svContent.addView(button);
        }
    }
}
