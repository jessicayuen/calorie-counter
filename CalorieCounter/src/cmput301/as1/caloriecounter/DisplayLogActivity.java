/**
 * @author	Copyright (c) 2013 Jessica Yuen <jyuen@ualberta.ca>
 * 
 * Permission to use, copy, modify, and distribute this software 
 * is granted, provided that the above copyright notice appear
 * in all copies.
 */

/**
 * Activity for displaying all the calorie logs along with 
 * their details:
 * date, description, total calories, and the calories per
 * weight/vol/serving, per weight/vol/serving, total 
 * weight/vol/serving if provided.
 */

package cmput301.as1.caloriecounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class DisplayLogActivity extends Activity {
	
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_log);
		lv = (ListView) findViewById(R.id.log_list);
	}

	protected void onResume() {
		super.onResume();
		displayLogEntries();
	}
	
	/** Displays the calorie log entry data **/
	private void displayLogEntries() {
		lv.setAdapter(new DisplayLogAdapter(this, LogManager.getLogEntries()));
		lv.setTextFilterEnabled(true);
	}
	
	/** Listener for add new entry button click **/
	public void addNewLogEntry(View view) {
		Intent intent = new Intent(this, AddNewEntryActivity.class);
		startActivity(intent);
	}
	
	/** Listener for back button click **/
	public void closeActivity(View view) {
		finish();
	}
}
