package org.kathmandulivinglabs.water;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Utils {

    public static final String DEPLOYMENT_URL = "http://www.kathmandulivinglabs.org/wash-mock/api.php";
    public static final String FORMHUB_IMAGE_URL = "https://ona.io/attachment/";
    public static final String NAME = "name", LOCATION = "location", PH = "ph", DISSOLVED_OXYGEN = "dissolved_oxygen", TEMPERATURE = "temperature",
            TURBIDITY = "turbidity", BIOCHEMICAL_OXYGEN_DEMAND = "biochemical_oxygen_demand", NITRATE = "nitrate", PHOSPHATE = "phosphate",
            NUMBER_SCHOOL = "school_number", NUMBER_CITIES = "cities_number", NUMBER_TESTPOINT = "testpoint_number", COLIFORM = "coliform",
            COLIFORM_ABSENT = "coliform_absent", MACROINVERTEBRATES = "macroinvertebrates", MACROINVERTEBRATES_ABSENT = "macroinvertebrates_absent", DESCRIPTION = "description", PHOTO = "photo_url", SCHOOL = "school_name";


    public Utils() {

    }

    public static List<Country> getCountries(Context context) {

        List<Country> countries = new ArrayList<Country>();
        Log.i("UTILS", "Calling Get Countries");

        GetCountriesAsync gcAsync = new GetCountriesAsync(context);

        try {
            countries = gcAsync.execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return countries;
    }

    public static List<City> getCities(Context context, String country) {
        List<City> cities = new ArrayList<City>();
        GetCitiesAsync gcAsync = new GetCitiesAsync(context);

        Log.i("UTILS", "Calling Get Cities");

        try {
            cities = gcAsync.execute(country).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return cities;
    }

    public static List<TestPoint> getTestPoints(Context context, String city) {
        List<TestPoint> testPoints = new ArrayList<TestPoint>();
        GetTestPointsAsync gtAsync = new GetTestPointsAsync(context);

        Log.i("UTILS", "Calling Get Test Points");

        try {
            testPoints = gtAsync.execute(city).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return testPoints;
    }

    public static String nameFormater(String s) {

        String[] words = s.split("_");
        StringBuilder sb = new StringBuilder();
        if (words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
            }
        }
        return sb.toString();

    }


}
