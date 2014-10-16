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


public class GetTestPointsAsync extends AsyncTask<String, Integer, List<TestPoint>> {

    private String DEPLOYMENT = Utils.DEPLOYMENT_URL;
    private Context mContext;
    private Dialog mProgressDialog;


    public GetTestPointsAsync(Context context) {
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
        mProgressDialog.setTitle("Getting the list of test points");
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(List<TestPoint> l) {
        mProgressDialog.dismiss();
        super.onPostExecute(l);
    }

    @Override
    protected List<TestPoint> doInBackground(String... strings) {
        List<TestPoint> testPoints = new ArrayList<TestPoint>();
        try {

            Log.i("ASyncTask", "Making HTTP GET Connection");
            String city = strings[0];

            URL url = new URL(DEPLOYMENT + "?function=getByLocation&type=city&location=" + city);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            InputStream stream = conn.getInputStream();
            String data = convertStreamToString(stream);
            testPoints = readAndParseJSON(data);

            Log.i("JSON Reply", data);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return testPoints;
        }

        return testPoints;

    }

    public List<TestPoint> readAndParseJSON(String in) {
        List<TestPoint> testPoints = new ArrayList<TestPoint>();
        try {
            JSONObject mainObject = new JSONObject(in);
            JSONArray data = mainObject.getJSONArray("jsonObject");
            for (int i = 0; i < data.length(); i++) {

                JSONObject object = data.getJSONObject(i);
                LatLng location = new LatLng(object.getDouble("lat"), object.getDouble("lon"));
                TestPoint testPoint = new TestPoint(object.getLong("id"), location);
                testPoints.add(testPoint);

                testPoint.setSchool(object.getString("school"));
                testPoint.setSurveyor_name(object.getString("name"));
                testPoint.setCity(object.getString("city"));
                testPoint.setCountry(object.getString("country"));
                testPoint.setPhoto_url(object.getString("photo"));
                testPoint.setDescription(object.getString("description"));
                testPoint.setColor(object.getString("color"));
                testPoint.setpH(object.getDouble("ph"));
                testPoint.setDissolved_oxygen(object.getDouble("dissolved_oxygen"));
                testPoint.setTemperature(object.getDouble("temperature"));
                testPoint.setTurbidity(object.getDouble("turbidity"));
                testPoint.setBiochemical_oxygen_demand(object.getDouble("biochemical_oxygen_demand"));
                testPoint.setNitrate(object.getDouble("nitrate"));
                testPoint.setPhosphate(object.getDouble("phosphate"));
                String coliform = object.getString("coliform_bacteria");
                String macro = object.getString("benthic_macroinvertebrates");
                if (coliform.equals("1")) {
                    testPoint.setColiform(true);
                } else {
                    testPoint.setColiform(false);
                }
                if (macro.equals("1")) {
                    testPoint.setMacroinvertebrates(true);
                } else {
                    testPoint.setMacroinvertebrates(false);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return testPoints;
        }

        return testPoints;


    }

}
