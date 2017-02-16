package zl.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mon on 2017/2/16.
 */

public class DoubleListViewHelper implements AdapterDoubleListViewLeft.ChangeRightMessage, AdapterDoubleListViewRight.ChangeRightMessage {

    private ListView listViewLeft;
    private ListView listViewRight;
    private ArrayList<String> leftData;
    private ArrayList<String> rightData;
    private ArrayList<ArrayList<String>> AllRightData;
    private AdapterDoubleListViewLeft adapterLeft;
    private AdapterDoubleListViewRight adapterRight;
    private int leftIndex;
    private Map<Integer, Integer> rightIndexMap;

    public DoubleListViewHelper() {
        leftData = new ArrayList<>();
        rightData = new ArrayList<>();
        AllRightData = new ArrayList<>();
        rightIndexMap = new HashMap<>();
    }

    public DoubleListViewHelper loadView(Activity aty) {
        listViewLeft = (ListView) aty.findViewById(R.id.list_left);
        listViewRight = (ListView) aty.findViewById(R.id.list_right);
        adapterLeft = new AdapterDoubleListViewLeft(leftData);
        adapterRight = new AdapterDoubleListViewRight(rightData);
        listViewLeft.setAdapter(adapterLeft);
        listViewRight.setAdapter(adapterRight);
        adapterLeft.setCallBack(this);
        adapterRight.setCallBack(this);
        return this;
    }

    public DoubleListViewHelper loadLeftData(ArrayList<String> leftData, boolean ifClear) {
        if (ifClear && this.leftData != null)
            this.leftData.clear();

        this.leftData.addAll(leftData);
        return this;
    }

    public DoubleListViewHelper loadRightData(ArrayList<String> rightData, boolean ifClear) {
        if (ifClear && this.rightData != null)
            this.rightData.clear();

        this.rightData.addAll(rightData);
        return this;
    }

    public DoubleListViewHelper loadAllRightData(ArrayList<ArrayList<String>> AllRightData) {
        this.AllRightData.addAll(AllRightData);
        loadRightData(AllRightData.get(0), false);
        return this;
    }

    public void notifyData() {
        adapterLeft.setCheckedMap();
        adapterRight.setCheckedMap(-1);
        adapterLeft.notifyDataSetChanged();
        adapterRight.notifyDataSetChanged();
    }

    @Override
    public void leftCallBack(int position) {
        leftIndex = position;
        loadRightData(AllRightData.get(position), true);
        adapterRight.notifyDataSetChanged();
        adapterRight.setCheckedMap(rightIndexMap.containsKey(position) ? rightIndexMap.get(position) : -1);
        adapterRight.notifyDataSetChanged();
    }

    @Override
    public void RightCallBack(int position) {
        rightIndexMap.put(leftIndex, position);
    }
}
