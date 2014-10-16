package org.kathmandulivinglabs.water;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class SummaryFragment extends Fragment {

    private int num_cities, num_schools, num_testpoints;
    private double ph, dissolved_oxygen, temperature, turbidity, biochemical_oxygen_demand, nitrate, phosphate;
    private CustomSeekBar phSeekBar, doSeekBar, tempSeekBar, turbSeekBar, bodSeekBar, nitrateSeekBar, phospateSeekBar;
    private TextView txtCityNum, txtSchoolNum, txtPointsNum;
    private LinearLayout llCityNum, llSchoolNum, llPointsNum;
    private boolean isCompare;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle args = getArguments();
        getDataFromBundle(args);

        return inflater.inflate(R.layout.layout_summary, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        llCityNum = (LinearLayout) getActivity().findViewById(R.id.ll_no_of_cities);
        llPointsNum = (LinearLayout) getActivity().findViewById(R.id.ll_no_of_testpoint);
        llSchoolNum = (LinearLayout) getActivity().findViewById(R.id.ll_no_of_schools);

        if (isCompare) {
            llCityNum.setVisibility(View.GONE);
            llSchoolNum.setVisibility(View.GONE);
            llPointsNum.setVisibility(View.GONE);
        }


        phSeekBar = (CustomSeekBar) getActivity().findViewById(R.id.seek_bar_ph);
        initPhSeekBar();
        phSeekBar.setProgress((int) ((ph / 14) * 100));
        phSeekBar.setEnabled(false);

        doSeekBar = (CustomSeekBar) getActivity().findViewById(R.id.seek_bar_dissolved_oxygen);
        initDOSeekBar();
        doSeekBar.setProgress((int) ((dissolved_oxygen / 18) * 100));
        doSeekBar.setEnabled(false);

        tempSeekBar = (CustomSeekBar) getActivity().findViewById(R.id.seek_bar_temperature);
        initTempSeekBar();
        tempSeekBar.setProgress((int) temperature);
        tempSeekBar.setEnabled(false);

        turbSeekBar = (CustomSeekBar) getActivity().findViewById(R.id.seek_bar_turbidity);
        initTurbSeekBar();
        turbSeekBar.setProgress((int) turbidity * 10);
        turbSeekBar.setEnabled(false);

        bodSeekBar = (CustomSeekBar) getActivity().findViewById(R.id.seek_bar_bod);
        initBODSeekBar();
        bodSeekBar.setProgress((int) biochemical_oxygen_demand * 10);
        bodSeekBar.setEnabled(false);

        nitrateSeekBar = (CustomSeekBar) getActivity().findViewById(R.id.seek_bar_nitrate);
        initNitrateSeekBar();
        nitrateSeekBar.setProgress((int) nitrate * 10);
        nitrateSeekBar.setEnabled(false);

        phospateSeekBar = (CustomSeekBar) getActivity().findViewById(R.id.seek_bar_phosphate);
        initPhosphateSeekBar();
        phospateSeekBar.setProgress((int) phosphate * 10);
        phospateSeekBar.setEnabled(false);


        txtCityNum = (TextView) getActivity().findViewById(R.id.txt_no_of_cities);
        txtCityNum.setText(String.valueOf(num_cities));
        txtSchoolNum = (TextView) getActivity().findViewById(R.id.txt_no_of_schools);
        txtSchoolNum.setText(String.valueOf(num_schools));
        txtPointsNum = (TextView) getActivity().findViewById(R.id.txt_no_of_testpoint);
        txtPointsNum.setText(String.valueOf(num_testpoints));


    }

    private void initPhosphateSeekBar() {

        ArrayList<ProgressItem> progressItemList = new ArrayList<ProgressItem>();

        ProgressItem mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 10;
        mProgressItem.color = R.color.min;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 20;
        mProgressItem.color = R.color.good_min;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 20;
        mProgressItem.color = R.color.good_max;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 50;
        mProgressItem.color = R.color.max;
        progressItemList.add(mProgressItem);

        phospateSeekBar.initData(progressItemList);
        phospateSeekBar.invalidate();


    }

    private void initNitrateSeekBar() {

        ArrayList<ProgressItem> progressItemList = new ArrayList<ProgressItem>();

        ProgressItem mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 10;
        mProgressItem.color = R.color.min;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 20;
        mProgressItem.color = R.color.good_min;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 20;
        mProgressItem.color = R.color.good_max;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 50;
        mProgressItem.color = R.color.max;
        progressItemList.add(mProgressItem);

        nitrateSeekBar.initData(progressItemList);
        nitrateSeekBar.invalidate();


    }

    private void initBODSeekBar() {

        ArrayList<ProgressItem> progressItemList = new ArrayList<ProgressItem>();

        ProgressItem mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 10;
        mProgressItem.color = R.color.min;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 20;
        mProgressItem.color = R.color.good_min;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 20;
        mProgressItem.color = R.color.good_max;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 50;
        mProgressItem.color = R.color.max;
        progressItemList.add(mProgressItem);

        bodSeekBar.initData(progressItemList);
        bodSeekBar.invalidate();

    }

    private void initTurbSeekBar() {
        ArrayList<ProgressItem> progressItemList = new ArrayList<ProgressItem>();

        ProgressItem mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 5;
        mProgressItem.color = R.color.min;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 15;
        mProgressItem.color = R.color.good_min;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 20;
        mProgressItem.color = R.color.good_max;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 60;
        mProgressItem.color = R.color.max;
        progressItemList.add(mProgressItem);

        turbSeekBar.initData(progressItemList);
        turbSeekBar.invalidate();
    }

    private void initTempSeekBar() {

        ArrayList<ProgressItem> progressItemList = new ArrayList<ProgressItem>();

        ProgressItem mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 5;
        mProgressItem.color = R.color.cold;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 10;
        mProgressItem.color = R.color.cool;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 10;
        mProgressItem.color = R.color.avg_temp;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 10;
        mProgressItem.color = R.color.warm;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 65;
        mProgressItem.color = R.color.hot;
        progressItemList.add(mProgressItem);

        tempSeekBar.initData(progressItemList);
        tempSeekBar.invalidate();
    }

    private void initDOSeekBar() {

        ArrayList<ProgressItem> progressItemList = new ArrayList<ProgressItem>();

        ProgressItem mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 25;
        mProgressItem.color = R.color.low_oxygen;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 5;
        mProgressItem.color = R.color.goodmin_oxygen;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 5;
        mProgressItem.color = R.color.medium_oxygen;
        progressItemList.add(mProgressItem);

        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = 65;
        mProgressItem.color = R.color.high_oxygen;
        progressItemList.add(mProgressItem);

        doSeekBar.initData(progressItemList);
        doSeekBar.invalidate();

    }

    private void initPhSeekBar() {
        ArrayList<ProgressItem> progressItemList = new ArrayList<ProgressItem>();
        // red span
        ProgressItem mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (float) 14.2857;
        mProgressItem.color = R.color.red;
        progressItemList.add(mProgressItem);
        // orange
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (float) 14.2857;
        mProgressItem.color = R.color.organge;
        progressItemList.add(mProgressItem);
        // yellow
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (float) 14.2857;
        mProgressItem.color = R.color.yellow;
        progressItemList.add(mProgressItem);
        // green
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (float) 14.2857;
        mProgressItem.color = R.color.green;
        progressItemList.add(mProgressItem);
        // yellow
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (float) 14.2857;
        mProgressItem.color = R.color.yellow;
        progressItemList.add(mProgressItem);
        // orange
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (float) 14.2857;
        mProgressItem.color = R.color.organge;
        progressItemList.add(mProgressItem);
        // red span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (float) 14.2857;
        mProgressItem.color = R.color.red;
        progressItemList.add(mProgressItem);

        phSeekBar.initData(progressItemList);
        phSeekBar.invalidate();

    }

    public void setObject(Bundle args) {
        getDataFromBundle(args);

    }

    private void getDataFromBundle(Bundle args) {
        isCompare = args.getBoolean("ISCOMPARE");
        num_cities = args.getInt(Utils.NUMBER_CITIES);
        num_schools = args.getInt(Utils.NUMBER_SCHOOL);
        num_testpoints = args.getInt(Utils.NUMBER_TESTPOINT);
        ph = args.getDouble(Utils.PH);
        dissolved_oxygen = args.getDouble(Utils.DISSOLVED_OXYGEN);
        temperature = args.getDouble(Utils.TEMPERATURE);
        turbidity = args.getDouble(Utils.TURBIDITY);
        biochemical_oxygen_demand = args.getDouble(Utils.BIOCHEMICAL_OXYGEN_DEMAND);
        nitrate = args.getDouble(Utils.NITRATE);
        phosphate = args.getDouble(Utils.PHOSPHATE);

    }
}
