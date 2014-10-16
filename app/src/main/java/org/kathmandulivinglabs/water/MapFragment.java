package org.kathmandulivinglabs.water;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mapbox.mapboxsdk.api.ILatLng;
import com.mapbox.mapboxsdk.geometry.BoundingBox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.ItemizedIconOverlay;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.tileprovider.tilesource.WebSourceTileLayer;
import com.mapbox.mapboxsdk.views.MapView;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment {


    LatLng northEast, southWest;
    ILatLng mapCenter;
    MapView mapView;
    FragmentCommunicator communicator;
    List<City> cities;
    List<TestPoint> testPoints;
    ArrayList<Marker> markers = new ArrayList<Marker>();
    private String strName, strCityLocation;
    private int intCityNumber;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle args = getArguments();
        getDataFromBundle(args);

        return inflater.inflate(R.layout.map_fragment, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        communicator = (FragmentCommunicator) getActivity();

        mapView = (MapView) getActivity().findViewById(R.id.mapview);
        Log.e("NOTIFY", "MapView Created");
        mapView.setTileSource(new WebSourceTileLayer("openstreetmap",
                "http://tile.openstreetmap.org/{z}/{x}/{y}.png").setName("OpenStreetMap")
                .setAttribution("© OpenStreetMap Contributors")
                .setMinimumZoomLevel(4)
                .setMaximumZoomLevel(18));
        Log.e("NOTIFY", "MapView TileSource and ZoomLevelSet");

        mapCenter = new ILatLng() {
            @Override
            public double getLatitude() {
                return 19.953;
            }

            @Override
            public double getLongitude() {
                return 83.320;
            }

            @Override
            public double getAltitude() {
                return 0;
            }
        };

        northEast = new LatLng(41.87, 129.20);
        southWest = new LatLng(0.57, 26.98);

        mapView.setCenter(mapCenter);
        Log.e("NOTIFY", "MapView Center Set");

        mapView.setZoom(5);
        Log.e("NOTIFY", "MapView Zoom Set");

        mapView.setScrollableAreaLimit(new BoundingBox(northEast, southWest));
        Log.e("NOTIFY", "MapView Scroll Limit Set");


        if (intCityNumber > 0) {

            preformCountryAction();

        } else if (strCityLocation != null) {
            preformCityAction();
        }

        mapView.addItemizedOverlay(new ItemizedIconOverlay(getActivity(), markers, new ItemizedIconOverlay.OnItemGestureListener<Marker>() {
            @Override
            public boolean onItemSingleTapUp(int i, Marker marker) {
                if (cities != null) {
                    communicator.showSummary(cities.get(Integer.parseInt(marker.getTitle())));
                } else if (testPoints != null) {
                    communicator.showSummary(testPoints.get(Integer.parseInt(marker.getTitle())));
                }
                return true;
            }

            @Override
            public boolean onItemLongPress(int i, Marker marker) {
                Toast.makeText(getActivity(), "Marker Selected: " + marker.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        }));

    }

    private void centerMapAndZoom(LatLng coordinates, float zoom) {

        mapView.setZoom(zoom);
        mapView.setCenter(coordinates);


    }

    public void preformCountryAction() {

        cities = Utils.getCities(getActivity(), strName);
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            Log.i("CityCoodrinates", city.getLocation().toString());
            LatLng cityLatLng = city.getLocation();
            addMarker(cityLatLng, i);
            centerMapAndZoom(cityLatLng, 6);
        }


    }

    public void preformCityAction() {
        testPoints = Utils.getTestPoints(getActivity(), strName);
        LatLng cityLatLng = stringToLatLng(strCityLocation);
        centerMapAndZoom(cityLatLng, 12);
        for (int i = 0; i < testPoints.size(); i++) {
            TestPoint testPoint = testPoints.get(i);
            Log.i("TestPointCoodrinates", testPoint.getLocation().toString());
            LatLng testPointLatLng = testPoint.getLocation();
            addMarker(testPointLatLng, i);
        }

    }

    public LatLng stringToLatLng(String string) {

        String[] coordinates = string.split(",");
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
        LatLng location = new LatLng(lat, lng);
        return location;

    }

    public void addMarker(LatLng latlng, int index) {


        Log.i("Add Marker", String.valueOf(latlng));
        markers.add(new Marker(mapView, String.valueOf(index), null, latlng));

    }


    private void getDataFromBundle(Bundle args) {

        strName = args.getString(Utils.NAME);
        intCityNumber = args.getInt(Utils.NUMBER_CITIES);
        strCityLocation = args.getString(Utils.LOCATION);

    }


}
