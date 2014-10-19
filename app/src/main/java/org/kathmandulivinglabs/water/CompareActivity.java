package org.kathmandulivinglabs.water;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class CompareActivity extends ActionBarActivity {

    private Spinner country1, country2, city1, city2;
    private List<Country> countries;
    private List<City> cities1, cities2;
    private List<String> country_names, city_names1, city_names2;
    private Boolean isCompare = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        setContentView(R.layout.activity_compare);
        country1 = (Spinner) findViewById(R.id.spinner_country1);
        country2 = (Spinner) findViewById(R.id.spinner_country2);
        city1 = (Spinner) findViewById(R.id.spinner_city1);
        city2 = (Spinner) findViewById(R.id.spinner_city2);
        countries = Utils.getCountries(this);
        country_names = new ArrayList<String>();
        city_names1 = new ArrayList<String>();
        city_names2 = new ArrayList<String>();
        for (Country country : countries) {
            country_names.add(country.getName());
        }
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country_names);



        country1.setAdapter(countryAdapter);
        country2.setAdapter(countryAdapter);

        country1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                SummaryFragment summaryFragment1 = new SummaryFragment();
                Bundle args = prepareCountryBundle(countries.get(pos));
                summaryFragment1.setArguments(args);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_compare_summary, summaryFragment1, "Summary Fragment 1");
                fragmentTransaction.commit();

                cities1 = Utils.getCities(CompareActivity.this,countries.get(pos).getName());
                city_names1.clear();
                city_names1.add("All");
                for (City city : cities1){
                    city_names1.add(city.getName());
                }
                ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(CompareActivity.this,android.R.layout.simple_spinner_item, city_names1);
                city1.setAdapter(cityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        country2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                SummaryFragment summaryFragment2 = new SummaryFragment();
                Bundle args = prepareCountryBundle(countries.get(pos));
                summaryFragment2.setArguments(args);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_compare_summary2, summaryFragment2, "Summary Fragment 2");
                fragmentTransaction.commit();

                cities2 = Utils.getCities(CompareActivity.this,countries.get(pos).getName());
                city_names2.clear();
                city_names2.add("All");
                for (City city : cities2){
                    city_names2.add(city.getName());
                }
                ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(CompareActivity.this,android.R.layout.simple_spinner_item, city_names2);
                city2.setAdapter(cityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        city1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {

                if (pos > 0) {
                    SummaryFragment summaryFragment1 = new SummaryFragment();
                    Bundle args = prepareCityBundle(cities1.get(pos - 1));
                    summaryFragment1.setArguments(args);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_compare_summary, summaryFragment1, "Summary Fragment 1");
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        city2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {

                if (pos > 0) {
                    SummaryFragment summaryFragment1 = new SummaryFragment();
                    Bundle args = prepareCityBundle(cities2.get(pos - 1));
                    summaryFragment1.setArguments(args);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_compare_summary2, summaryFragment1, "Summary Fragment 1");
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_main) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Bundle prepareCountryBundle(Country country) {
        Bundle args = new Bundle();
        args.putBoolean("ISCOMPARE", isCompare);
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
        args.putBoolean("ISCOMPARE", isCompare);
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
}
