package org.kathmandulivinglabs.water;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CityAdapter extends BaseExpandableListAdapter {

    public static List<City> cities = new ArrayList<City>();
    private Context mContext;
    private List<String> parentList = new ArrayList<String>();
    private List<List<String>> childList = new ArrayList<List<String>>();

    public CityAdapter(Context context, String country) {
        mContext = context;
        cities = Utils.getCities(mContext, country);
        Log.i("CITY ADAPTER", "Cities Size :" + String.valueOf(cities.size()));
        for (City city : cities) {
            String cityName = city.getName();


            parentList.add(cityName);

            List<String> options = new ArrayList<String>();
            options.add("Maps");
            options.add("Summary");
            childList.add(options);

        }
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return parentList.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return childList.get(i).get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        TextView tv = new TextView(mContext);
        tv.setText(parentList.get(i));
        tv.setPadding(50, 10, 50, 5);
        tv.setTextSize(25);
        return tv;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {
        TextView tv = new TextView(mContext);
        tv.setText(childList.get(i).get(i2));
        tv.setPadding(65, 5, 50, 5);
        tv.setTextSize(20);

        return tv;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
