package org.kathmandulivinglabs.water;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


public class CityList extends Fragment {

    FragmentCommunicator communicator;
    String strName;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Bundle args = getArguments();
        strName = args.getString(Utils.NAME);
        return inflater.inflate(R.layout.layout_citylist, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator = (FragmentCommunicator) getActivity();
        ExpandableListView elv = (ExpandableListView) getActivity().findViewById(R.id.expandableListView_city);
        elv.setAdapter(new CityAdapter(getActivity(), strName));
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long l) {


                switch (i2) {
                    case 0:
                        communicator.showMap(CityAdapter.cities.get(i));
                        break;
                    case 1:
                        communicator.showSummary(CityAdapter.cities.get(i));
                        break;

                }


                return false;
            }
        });
    }

}
