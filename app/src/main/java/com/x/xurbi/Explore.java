package com.x.xurbi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Xayvay on 5/22/17.
 */
public class Explore extends Fragment {



    public static Fragment newInstance(Context context){
        Explore I = new Explore();
        return I;
    }

    public Explore(){

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.explore, container, false);
        Button home = (Button) rootView.findViewById(R.id.home_e);
        Button profile = (Button) rootView.findViewById(R.id.profile_e);
        Button explore = (Button) rootView.findViewById(R.id.explore_e);
        Button friends = (Button) rootView.findViewById(R.id.friends_e);
        Button interest = (Button) rootView.findViewById(R.id.interest_e);
        Button popular = (Button) rootView.findViewById(R.id.popular_e);
        final ImageView docs =(ImageView)rootView.findViewById(R.id.imgz);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment home = new Home();
                FragmentManager fragmentManager = getFragmentManager();
            }

        });
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment profile = new Profile();
                FragmentManager fragmentManager = getFragmentManager();
            }

        });
        explore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment explore = new Explore();
                FragmentManager fragmentManager = getFragmentManager();
            }

        });
        friends.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment friends = new Friends();
                FragmentManager fragmentManager = getFragmentManager();
            }

        });
        interest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment interest = new Interest();
                FragmentManager fragmentManager = getFragmentManager();
            }

        });
        popular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment popular = new Popular();
                FragmentManager fragmentManager = getFragmentManager();
            }

        });
        return rootView;

    }

}