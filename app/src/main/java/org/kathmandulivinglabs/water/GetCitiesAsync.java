package org.kathmandulivinglabs.water;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GetCitiesAsync extends AsyncTask<String, Integer, List<City>> {

    private String DEPLOYMENT = Utils.DEPLOYMENT_URL;
    private Context mContext;
    private Dialog mProgressDialog;


    public GetCitiesAsync(Context context) {
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
        mProgressDialog.setTitle("Getting the list of cities");
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(List<City> l) {
        mProgressDialog.dismiss();
        super.onPostExecute(l);
    }

    @Override
    protected List<City> doInBackground(String... strings) {
        List<City> cities = new ArrayList<City>();
        String country = strings[0];
        try {


            Log.i("ASyncTask", "Making HTTP GET Connection");

            URL url = new URL(DEPLOYMENT + "?function=getMapFeatures&type=cities&country=" + country);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            InputStream stream = conn.getInputStream();
            String data = convertStreamToString(stream);
            cities = readAndParseJSON(data);

            Log.i("JSON Reply", data);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return cities;
        }

        for (int i = 0; i < cities.size(); i++) {
            try {
                City city = cities.get(i);
                String cityName = city.getName();
                URL url = new URL(DEPLOYMENT + "?function=getSummary&type=city&city=" + cityName + "&country=" + country);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();
                InputStream stream = conn.getInputStream();
                String data = convertStreamToString(stream);
                Log.i("JSON Reply City", data);
                JSONObject jsonObject = new JSONObject(data);
                city.setpH(jsonObject.getDouble("ph"));
                city.setDissolved_oxygen(jsonObject.getDouble("dissolved_oxygen"));
                city.setTemperature(jsonObject.getDouble("temperature"));
                city.setTurbidity(jsonObject.getDouble("turbidity"));
                city.setBiochemical_oxygen_demand(jsonObject.getDouble("biochemical_oxygen_demand"));
                city.setNitrate(jsonObject.getDouble("nitrate"));
                city.setPhosphate(jsonObject.getDouble("phosphate"));
                city.setNumber_school(jsonObject.getInt("count_schools"));
                city.setNumber_testpoint(jsonObject.getInt("count_testPoints"));
                //city.setNumber_cities(jsonObject.getInt("count_cities"));
                JSONObject coliform = jsonObject.getJSONObject("coliform_bacteria");
                JSONObject macroinvertebrates = jsonObject.getJSONObject("benthic_macroinvertebrates");
                city.setColiform_absent(coliform.getInt("absent"));
                city.setColiform_present(coliform.getInt("present"));
                city.setMacroinvertebrates_absent(macroinvertebrates.getInt("absent"));
                city.setMacroinvertebrates_present(macroinvertebrates.getInt("present"));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return cities;

    }

    public List<City> readAndParseJSON(String in) {
        List<City> cities = new ArrayList<City>();
        try {
            JSONObject data = new JSONObject(in);

            JSONArray jCities = data.getJSONArray("features");
            for (int i = 0; i < jCities.length(); i++) {

                JSONObject object = jCities.getJSONObject(i);
                JSONObject properties = object.getJSONObject("properties");
                JSONObject geometry = object.getJSONObject("geometry");
                City city = new City(properties.getString("city"));
                JSONArray jCordinates = geometry.getJSONArray("coordinates");
                LatLng coordinates = new LatLng(jCordinates.getDouble(1), jCordinates.getDouble(0));
                city.setLocation(coordinates);

                cities.add(city);


                Log.i("CITY", properties.getString("city"));
                //JSONObject boundry = geometry.getJSONObject("coordinates");
                Log.i("CITY", geometry.getString("coordinates"));


            }

        } catch (Exception e) {
            e.printStackTrace();
            return cities;
        }

        return cities;


    }

}
