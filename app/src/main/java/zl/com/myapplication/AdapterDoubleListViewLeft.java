package zl.com.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mon on 2017/2/16.
 */

public class AdapterDoubleListViewLeft extends BaseAdapter {

    private ArrayList<String> data;
    private Map<Integer, Boolean> checkedMap = new HashMap<>();
    private ChangeRightMessage callBack;

    public AdapterDoubleListViewLeft(ArrayList<String> data) {
        this.data = data;
        setCheckedMap();
    }

    public void setCheckedMap() {
        for (int i = 0; i < data.size(); i++) {
            checkedMap.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.double_listview_item_left, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.cb = (RadioButton) view;
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.cb.setText(data.get(i));
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((RadioButton) view).isChecked()) {
                    for (int index = 0; index < checkedMap.size(); index++) {
                        checkedMap.put(index, false);
                    }
                }
                checkedMap.put(i, ((RadioButton) view).isChecked());
                if (callBack != null)
                    callBack.leftCallBack(i);
                notifyDataSetChanged();
            }
        });
        viewHolder.cb.setChecked(checkedMap.get(i));
        return view;
    }


    private class ViewHolder {
        public RadioButton cb;
    }

    public ChangeRightMessage getCallBack() {
        return callBack;
    }

    public void setCallBack(ChangeRightMessage callBack) {
        this.callBack = callBack;
    }

    public interface ChangeRightMessage {
        void leftCallBack(int position);
    }


}
