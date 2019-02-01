package ca.nbcc.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    private int[] ids = {R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,R.id.item7,R.id.item8,R.id.item9,R.id.item10};
    private HashMap<String,Integer> cartMap = new HashMap<String,Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("CartHistory")) {
                cartMap = (HashMap<String,Integer>)savedInstanceState.getSerializable("CartHistory");

                populateTexts();
            }
        }
    }

    private void populateTexts(){
        for (String key: cartMap.keySet()) {
            Integer count = cartMap.get(key);
            for (int id:ids) {
                TextView txtView = findViewById(id);
                if (txtView.getText().toString().contains(key)) {
                    txtView.setText(key + ":" + count.intValue());
                    break;
                }

                if(txtView.getText().length() <= 0) {
                    if (count.intValue() > 0){
                        txtView.setText(key + ":" + count.intValue());
                    }else{
                        txtView.setText(key);
                    }
                    break;
                }
            }
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
