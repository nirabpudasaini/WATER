package org.kathmandulivinglabs.water;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


public class CountryList extends Fragment {

    FragmentCommunicator communicator;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_countrylist, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator = (FragmentCommunicator) getActivity();
        ExpandableListView elv = (ExpandableListView) getActivity().findViewById(R.id.expandableListView_country);
        elv.setAdapter(new CountryAdapter(getActivity()));
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long l) {


                switch (i2) {
                    case 0:
                        communicator.showMap(CountryAdapter.countries.get(i));
                        break;
                    case 1:
                        communicator.showSummary(CountryAdapter.countries.get(i));
                        break;
                    case 2:
                        communicator.showCity(CountryAdapter.countries.get(i));
                }


                return false;
            }
        });
    }
}
