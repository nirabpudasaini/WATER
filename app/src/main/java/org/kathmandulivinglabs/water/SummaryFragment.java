package org.kathmandulivinglabs.water;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class SummaryFragment extends Fragment {

    private int num_cities, num_schools, num_testpoints;
    private double ph, dissolved_oxygen, temperature, turbidity, biochemical_oxygen_demand, nitrate, phosphate;
    private CustomSeekBar phSeekBar, doSeekBar, tempSeekBar, turbSeekBar, bodSeekBar, nitrateSeekBar, phospateSeekBar;
    private String name, description, school_name, photo_id;
    private boolean isCompare;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle args = getArguments();
        getDataFromBundle(args);

        return inflater.inflate(R.layout.layout_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        LinearLayout llCityNum, llSchoolNum, llPointsNum, llNameBlock, llDescription, llSchoolName, llSurveyorName, llPhoto;
        llDescription = (LinearLayout) view.findViewById(R.id.ll_description);
/*        llSchoolName = (LinearLayout) view.findViewById(R.id.ll_school_name);
        llSurveyorName = (LinearLayout) view.findViewById(R.id.ll_surveyor_name);*/
        llNameBlock = (LinearLayout) view.findViewById(R.id.ll_name_summary);
        llCityNum = (LinearLayout) view.findViewById(R.id.ll_no_of_cities);
        llPointsNum = (LinearLayout) view.findViewById(R.id.ll_no_of_testpoint);
        llSchoolNum = (LinearLayout) view.findViewById(R.id.ll_no_of_schools);
        llPhoto = (LinearLayout) view.findViewById(R.id.ll_photo);

        if (isCompare || photo_id != null) {
            llCityNum.setVisibility(View.GONE);
            llSchoolNum.setVisibility(View.GONE);
            llPointsNum.setVisibility(View.GONE);
        }

        if (photo_id != null) {
            llNameBlock.setVisibility(View.GONE);
            llDescription.setVisibility(View.VISIBLE);
/*            llSchoolName.setVisibility(View.VISIBLE);
            llSurveyorName.setVisibility(View.VISIBLE);*/
            llPhoto.setVisibility(View.VISIBLE);
            ImageView imageView = (ImageView) view.findViewById(R.id.photo);
            new GetImageFromUrlAsync(imageView, "medium").execute(photo_id);
        }

        phSeekBar = (CustomSeekBar) view.findViewById(R.id.seek_bar_ph);
        initPhSeekBar();
        phSeekBar.setProgress((int) ((ph / 14) * 100));
        phSeekBar.setEnabled(false);

        doSeekBar = (CustomSeekBar) view.findViewById(R.id.seek_bar_dissolved_oxygen);
        initDOSeekBar();
        doSeekBar.setProgress((int) ((dissolved_oxygen / 18) * 100));
        doSeekBar.setEnabled(false);

/*        tempSeekBar = (CustomSeekBar) view.findViewById(R.id.seek_bar_temperature);
        initTempSeekBar();
        tempSeekBar.setProgress((int) temperature);
        tempSeekBar.setEnabled(false);*/

        turbSeekBar = (CustomSeekBar) view.findViewById(R.id.seek_bar_turbidity);
        initTurbSeekBar();
        turbSeekBar.setProgress((int) turbidity * 10);
        turbSeekBar.setEnabled(false);

        bodSeekBar = (CustomSeekBar) view.findViewById(R.id.seek_bar_bod);
        initBODSeekBar();
        bodSeekBar.setProgress((int) biochemical_oxygen_demand * 10);
        bodSeekBar.setEnabled(false);

        nitrateSeekBar = (CustomSeekBar) view.findViewById(R.id.seek_bar_nitrate);
        initNitrateSeekBar();
        nitrateSeekBar.setProgress((int) nitrate * 10);
        nitrateSeekBar.setEnabled(false);

        phospateSeekBar = (CustomSeekBar) view.findViewById(R.id.seek_bar_phosphate);
        initPhosphateSeekBar();
        phospateSeekBar.setProgress((int) phosphate * 10);
        phospateSeekBar.setEnabled(false);

        TextView txtCityNum, txtSchoolNum, txtPointsNum, txtName, txtDescription, txtSchoolName, txtSurveyorName;
        txtDescription = (TextView) view.findViewById(R.id.txt_description);
        txtDescription.setText(description);
/*        txtSchoolName = (TextView) view.findViewById(R.id.txt_school_name);
        txtSchoolName.setText(school_name);
        txtSurveyorName = (TextView) view.findViewById(R.id.txt_surveyor_name);
        txtSurveyorName.setText(name);*/
        txtName = (TextView) view.findViewById(R.id.txt_name_summary);
        txtName.setText(name);
        txtCityNum = (TextView) view.findViewById(R.id.txt_no_of_cities);
        txtCityNum.setText(String.valueOf(num_cities));
        txtSchoolNum = (TextView) view.findViewById(R.id.txt_no_of_schools);
        txtSchoolNum.setText(String.valueOf(num_schools));
        txtPointsNum = (TextView) view.findViewById(R.id.txt_no_of_testpoint);
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
        name = args.getString(Utils.NAME);
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
        photo_id = args.getString(Utils.PHOTO);
        school_name = args.getString(Utils.SCHOOL);
        description = args.getString(Utils.DESCRIPTION);

    }
}
