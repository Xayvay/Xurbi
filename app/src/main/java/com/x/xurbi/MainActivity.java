package com.x.xurbi;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity  {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPageTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar bar = getActionBar();
      //for color
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0C090A")));
      //for image
      //bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.settings_icon));

        mTitle = mDrawerTitle = getTitle();
		mPageTitles = getResources().getStringArray(R.array.pages_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPageTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_websearch:
            // create intent to perform web search for this pages
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
       
        Fragment home = new Home();
        Fragment profile = new Profile();
        Fragment explore = new Explore();
        FragmentManager fragmentManager = getFragmentManager();
       
        switch (position) {
		case 0:
	        fragmentManager.beginTransaction().replace(R.id.content_frame, home).commit();
			break;
		case 1:
			fragmentManager.beginTransaction().replace(R.id.content_frame, profile).commit();
			break;
		case 2:
			fragmentManager.beginTransaction().replace(R.id.content_frame, explore).commit();
			break;
		case 3:
			
			break;
		}
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPageTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

   // Will try to put in own class later
    public static class Home extends Fragment {

    	public static Fragment newInstance(Context context){
    		Home h = new Home();
    		return h;
    	}
    	
    	public Home(){

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
    		final View rootView = inflater.inflate(R.layout.home, container, false);
    		Button button = (Button) rootView.findViewById(R.id.Button04);
             final TextView receive = (TextView) rootView.findViewById(R.id.getText);
             final EditText send = (EditText) rootView.findViewById(R.id.editText);
    		button.setOnClickListener(new OnClickListener(){
                String makeText;
    			 @Override
    				public void onClick(View v) {

    			    	makeText = receive.getText().toString() + "\n" + send.getText().toString();
                     
                        receive.setText(makeText);
                        send.setText("");


    			 }

    		});
    				return rootView;
    		
    	}
    	
    }
    
    public static class Profile extends Fragment {

    	public static Fragment newInstance(Context context){
    		Profile p = new Profile();
    		return p;
    	}
    	
    	public Profile(){
    		
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
    		View rootView = inflater.inflate(R.layout.profile, container, false);
    		
    				return rootView;
    		
    	}
    	
    }
    public static class Friends extends Fragment {

        public static Fragment newInstance(Context context){
            Friends f = new Friends();
            return f;
        }

        public Friends(){

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
            View rootView = inflater.inflate(R.layout.friends, container, false);

            return rootView;

        }

    }
    public static class Interest extends Fragment {

        public static Fragment newInstance(Context context){
            Interest i = new Interest();
            return i;
        }

        public Interest(){

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
            View rootView = inflater.inflate(R.layout.interest, container, false);

            return rootView;

        }

    }

    public static class Popular extends Fragment {

        public static Fragment newInstance(Context context){
            Popular p = new Popular();
            return p;
        }

        public Popular(){

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
            View rootView = inflater.inflate(R.layout.popular, container, false);

            return rootView;

        }

    }
    public static class Explore extends Fragment {
    	
    	
    	
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
    		home.setOnClickListener(new OnClickListener(){
    			 @Override
    				public void onClick(View v) {
    			    	Fragment home = new Home();
    			        FragmentManager fragmentManager = getFragmentManager();
    			 }
    			
    		});
            profile.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    Fragment profile = new Profile();
                    FragmentManager fragmentManager = getFragmentManager();
                }

            });
            explore.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    Fragment explore = new Explore();
                    FragmentManager fragmentManager = getFragmentManager();
                }

            });
            friends.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    Fragment friends = new Friends();
                    FragmentManager fragmentManager = getFragmentManager();
                }

            });
            interest.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    Fragment interest = new Interest();
                    FragmentManager fragmentManager = getFragmentManager();
                }

            });
            popular.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    Fragment popular = new Popular();
                    FragmentManager fragmentManager = getFragmentManager();
                }

            });
    				return rootView;
    		
    	}
    	
    }

}