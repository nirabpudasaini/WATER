package org.kathmandulivinglabs.water;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GetCountriesAsync extends AsyncTask<String, Integer, List<Country>> {

    private String DEPLOYMENT = Utils.DEPLOYMENT_URL;
    private Context mContext;
    private Dialog mProgressDialog;


    public GetCountriesAsync(Context context) {
        mContext = context;

    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new Dialog(mContext);
        mProgressDialog.setTitle("Getting the list of countries");
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(List<Country> l) {
        mProgressDialog.dismiss();
        super.onPostExecute(l);
    }

    @Override
    protected List<Country> doInBackground(String... strings) {
        List<Country> countries = new ArrayList<Country>();
        try {

            Log.i("ASyncTask", "Making HTTP GET Connection");

            URL url = new URL(DEPLOYMENT + "?function=getMapFeatures&type=countries");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            InputStream stream = conn.getInputStream();
            String data = convertStreamToString(stream);
            countries = readAndParseJSON(data);

            Log.i("JSON Reply", data);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return countries;
        }

        for (int i = 0; i < countries.size(); i++) {
            try {
                Country country = countries.get(i);
                String countryName = country.getName();
                URL url = new URL(DEPLOYMENT + "?function=getSummary&type=country&city=all&country=" + countryName);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();
                InputStream stream = conn.getInputStream();
                String data = convertStreamToString(stream);
                Log.i("JSON Reply", data);
                JSONObject jsonObject = new JSONObject(data);
                country.setpH(jsonObject.getDouble("ph"));
                country.setDissolved_oxygen(jsonObject.getDouble("dissolved_oxygen"));
                country.setTemperature(jsonObject.getDouble("temperature"));
                country.setTurbidity(jsonObject.getDouble("turbidity"));
                country.setBiochemical_oxygen_demand(jsonObject.getDouble("biochemical_oxygen_demand"));
                country.setNitrate(jsonObject.getDouble("nitrate"));
                country.setPhosphate(jsonObject.getDouble("phosphate"));
                country.setNumber_school(jsonObject.getInt("count_schools"));
                country.setNumber_testpoint(jsonObject.getInt("count_testPoints"));
                country.setNumber_cities(jsonObject.getInt("count_cities"));
                JSONObject coliform = jsonObject.getJSONObject("coliform_bacteria");
                JSONObject macroinvertebrates = jsonObject.getJSONObject("benthic_macroinvertebrates");
                country.setColiform_absent(coliform.getInt("absent"));
                country.setColiform_present(coliform.getInt("present"));
                country.setMacroinvertebrates_absent(macroinvertebrates.getInt("absent"));
                country.setMacroinvertebrates_present(macroinvertebrates.getInt("present"));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return countries;

    }

    public List<Country> readAndParseJSON(String in) {
        List<Country> countries = new ArrayList<Country>();
        try {
            JSONObject data = new JSONObject(in);

            JSONArray jCountries = data.getJSONArray("features");
            for (int i = 0; i < jCountries.length(); i++) {

                JSONObject object = jCountries.getJSONObject(i);
                JSONObject properties = object.getJSONObject("properties");
                JSONObject geometry = object.getJSONObject("geometry");
                Country country = new Country(properties.getString("country"));

                countries.add(country);

                Log.i("COUNTRY", properties.getString("country"));
                //JSONObject boundry = geometry.getJSONObject("coordinates");
                Log.i("COUNTRY", geometry.getString("coordinates"));


            }

        } catch (Exception e) {
            e.printStackTrace();
            return countries;
        }

        return countries;


    }

}
