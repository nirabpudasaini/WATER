package org.kathmandulivinglabs.water;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements FragmentCommunicator {

    FragmentManager manager;
    MapFragment mapFragment;
    CountryList clFragment;
    SummaryFragment sumFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        setContentView(R.layout.activity_main);

        mapFragment = new MapFragment();
        clFragment = new CountryList();

        manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, clFragment, "CountryListFragment");
        transaction.commit();

        FragmentTransaction transaction1 = manager.beginTransaction();
        transaction1.replace(R.id.content_frame_another, new InfoFragment(), "InfoFragment");
        transaction1.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_compare) {
            Intent intent = new Intent(this, CompareActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMap(Object obj) {
        mapFragment = (MapFragment) manager.findFragmentByTag("MapFragment");
        Bundle args = new Bundle();
        if (obj instanceof Country) {
            Country country = (Country) obj;
            args = prepareCountryBundle(country);
        } else if (obj instanceof City) {
            City city = (City) obj;
            args = prepareCityBundle(city);
        }


        MapFragment newFragment = new MapFragment();

        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.content_frame_another, newFragment, "MapFragment");

        transaction.commit();

    }

    @Override
    public void showSummary(Object obj) {

        sumFragment = (SummaryFragment) manager.findFragmentByTag("SummaryFragment");
        Bundle args = new Bundle();
        if (obj instanceof Country) {
            Country country = (Country) obj;
            args = prepareCountryBundle(country);
        } else if (obj instanceof City) {
            City city = (City) obj;
            args = prepareCityBundle(city);
        } else if (obj instanceof TestPoint) {
            TestPoint testPoint = (TestPoint) obj;
            args = prepareTestPointBundle(testPoint);
        }

        SummaryFragment newFragment = new SummaryFragment();

        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.content_frame_another, newFragment, "SummaryFragment");

        transaction.commit();


    }


    @Override
    public void showCity(Object obj) {

        CityList newFragment = new CityList();

        Country country = (Country) obj;
        Bundle args = prepareCountryBundle(country);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.content_frame, newFragment, "CityFragment");
        transaction.addToBackStack(null);

        transaction.commit();

    }

    private Bundle prepareCountryBundle(Country country) {
        Bundle args = new Bundle();
        args.putString(Utils.NAME, country.getName());
        args.putDouble(Utils.PH, country.getpH());
        args.putDouble(Utils.TEMPERATURE, country.getTemperature());
        args.putInt(Utils.NUMBER_TESTPOINT, country.getNumber_testpoint());
        args.putDouble(Utils.DISSOLVED_OXYGEN, country.getDissolved_oxygen());
        args.putDouble(Utils.TURBIDITY, country.getTurbidity());
        args.putDouble(Utils.BIOCHEMICAL_OXYGEN_DEMAND, country.getBiochemical_oxygen_demand());
        args.putDouble(Utils.NITRATE, country.getNitrate());
        args.putDouble(Utils.PHOSPHATE, country.getPhosphate());
        args.putInt(Utils.NUMBER_SCHOOL, country.getNumber_school());
        args.putInt(Utils.NUMBER_CITIES, country.getNumber_cities());
        args.putInt(Utils.COLIFORM, country.getColiform_present());
        args.putInt(Utils.COLIFORM_ABSENT, country.getColiform_absent());
        args.putInt(Utils.MACROINVERTEBRATES, country.getMacroinvertebrates_present());
        args.putInt(Utils.MACROINVERTEBRATES_ABSENT, country.getMacroinvertebrates_absent());


        return args;

    }

    private Bundle prepareCityBundle(City city) {
        Bundle args = new Bundle();
        args.putString(Utils.NAME, city.getName());
        args.putString(Utils.LOCATION, city.getLocation().toString());
        args.putDouble(Utils.PH, city.getpH());
        args.putDouble(Utils.TEMPERATURE, city.getTemperature());
        args.putInt(Utils.NUMBER_TESTPOINT, city.getNumber_testpoint());
        args.putDouble(Utils.DISSOLVED_OXYGEN, city.getDissolved_oxygen());
        args.putDouble(Utils.TURBIDITY, city.getTurbidity());
        args.putDouble(Utils.BIOCHEMICAL_OXYGEN_DEMAND, city.getBiochemical_oxygen_demand());
        args.putDouble(Utils.NITRATE, city.getNitrate());
        args.putDouble(Utils.PHOSPHATE, city.getPhosphate());
        args.putInt(Utils.NUMBER_SCHOOL, city.getNumber_school());
        //args.putInt(Utils.NUMBER_CITIES, city.getNumber_cities());
        args.putInt(Utils.COLIFORM, city.getColiform_present());
        args.putInt(Utils.COLIFORM_ABSENT, city.getColiform_absent());
        args.putInt(Utils.MACROINVERTEBRATES, city.getMacroinvertebrates_present());
        args.putInt(Utils.MACROINVERTEBRATES_ABSENT, city.getMacroinvertebrates_absent());


        return args;

    }

    private Bundle prepareTestPointBundle(TestPoint tp) {

        Bundle args = new Bundle();
        args.putString(Utils.LOCATION, tp.getLocation().toString());
        args.putString(Utils.DESCRIPTION, tp.getDescription());
        args.putString(Utils.SCHOOL, tp.getSchool());
        args.putString(Utils.PHOTO, tp.getPhoto_url());
        args.putString(Utils.NAME, tp.getSurveyor_name());
        args.putDouble(Utils.PH, tp.getpH());
        args.putDouble(Utils.TEMPERATURE, tp.getTemperature());
        args.putDouble(Utils.DISSOLVED_OXYGEN, tp.getDissolved_oxygen());
        args.putDouble(Utils.TURBIDITY, tp.getTurbidity());
        args.putDouble(Utils.BIOCHEMICAL_OXYGEN_DEMAND, tp.getBiochemical_oxygen_demand());
        args.putDouble(Utils.NITRATE, tp.getNitrate());
        args.putDouble(Utils.PHOSPHATE, tp.getPhosphate());

        return args;
    }

}
