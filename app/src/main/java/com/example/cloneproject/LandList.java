package com.example.cloneproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LandList extends ArrayAdapter<Lands> {

    private Activity context;
    List <Lands> lands ;

    public LandList(Activity context, ArrayList<Lands> lands) {
        super(context, R.layout.list_lands_layout, lands);
        this.context = context;
        this.lands = lands;

    }

  /*  @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_lands_layout,null,true);

        TextView  textViewLandName = (TextView) listViewItem.findViewById(R.id.textViewLandName);
        TextView  textViewLandArea = (TextView) listViewItem.findViewById(R.id.textViewLandArea);

        Lands lands = Lands.get(position);
        textViewLandName.setText(lands.getLandPlace());
        textViewLandArea.setText(lands.getLandArea());

        return listViewItem;



    }*/





}
