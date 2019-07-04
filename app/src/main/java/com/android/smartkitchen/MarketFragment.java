package com.android.smartkitchen;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarketFragment extends Fragment implements View.OnClickListener{

    View view;
    public MarketFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_market, container, false);

        Button btn1 = (Button) view.findViewById(R.id.prisma);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) view.findViewById(R.id.tokmanni);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) view.findViewById(R.id.sale);
        btn3.setOnClickListener(this);


      return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prisma:
                Intent onClick1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.prisma.fi/fi/prisma"));
                startActivity(onClick1);
                break;
            case R.id.tokmanni:
                Intent onClick2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tokmanni.fi"));
                startActivity(onClick2);
                break;
            case R.id.sale:
                Intent onClick3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.s-kanava.fi/ketju/sale/103"));
                startActivity(onClick3);
                break;
    }
    }
}
