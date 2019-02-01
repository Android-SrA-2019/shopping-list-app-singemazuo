package ca.nbcc.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onButtonClick(View view) {
        Intent replyIntent = new Intent();

        switch (view.getId()){
            case R.id.btnApple:
                replyIntent.putExtra(SELECTED_GOODS,"Apple");
                break;
            case R.id.btnCheese:
                replyIntent.putExtra(SELECTED_GOODS,"Cheese");
                break;
            case R.id.btnDumpling:
                replyIntent.putExtra(SELECTED_GOODS,"Dumpling");
                break;
            case R.id.btnPasta:
                replyIntent.putExtra(SELECTED_GOODS,"Pasta");
                break;
            case R.id.btnRice:
                replyIntent.putExtra(SELECTED_GOODS,"Rice");
                break;
            case R.id.btnSault:
                replyIntent.putExtra(SELECTED_GOODS,"Sault");
                break;
        }

        setResult(Activity.RESULT_OK,replyIntent);
        finish();
    }
}
