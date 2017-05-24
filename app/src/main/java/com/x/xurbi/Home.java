package com.x.xurbi;

import com.x.xurbi.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// Will try to put in own class later
public class Home extends android.app.Fragment {

	public static android.app.Fragment newInstance(Context context){
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
		button.setOnClickListener(new View.OnClickListener(){
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
