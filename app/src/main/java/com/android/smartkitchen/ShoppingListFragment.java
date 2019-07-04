package com.android.smartkitchen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingListFragment extends Fragment {


    public ShoppingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View views = inflater.inflate(R.layout.fragment_shopping_list, container, false);



            String[] menuItem = {"Yogurt"};
            ListView listViews = (ListView) views.findViewById(R.id.shoppinglist);
            ArrayAdapter<String> listadapterview = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuItem);
            listViews.setAdapter(listadapterview);

            return views;

    }

}
