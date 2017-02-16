package zl.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> leftData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            leftData.add(i + "---->");
        }

        ArrayList<ArrayList<String>> rightData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<String> innerData = new ArrayList<>();
            rightData.add(innerData);
            for (int j = 0; j < 10; j++) {
                innerData.add(i + "////" + j);
            }
        }

        DoubleListViewHelper helper = new DoubleListViewHelper()
                .loadView(this)
                .loadLeftData(leftData, false)
                .loadAllRightData(rightData);
        helper.notifyData();


    }
}
