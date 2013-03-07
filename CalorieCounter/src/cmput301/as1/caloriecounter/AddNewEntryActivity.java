/**
 * @author	Copyright (c) 2013 Jessica Yuen <jyuen@ualberta.ca>
 * 
 * Permission to use, copy, modify, and distribute this software 
 * is granted, provided that the above copyright notice appear
 * in all copies.
 */

/**
 * Activity allowing for the addition of a new calorie entry
 * into the existing calorie log.
 */

package cmput301.as1.caloriecounter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddNewEntryActivity extends Activity {
	
	private EditText calPerWeight;
	private EditText perWeight;
	private EditText totalWeight;
	private EditText totalCalories;
	private EditText date;
	private EditText description;
	private Button submitButton;
	private TextWatcher watcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_entry);
		
		calPerWeight = (EditText) findViewById(R.id.calories_per_weight_text);
		perWeight = (EditText) findViewById(R.id.per_weight_text);
		totalWeight = (EditText) findViewById(R.id.total_weight_text);
		totalCalories = (EditText) findViewById(R.id.total_calories_text);
		date = (EditText) findViewById(R.id.date_text);
		description = (EditText) findViewById(R.id.description_text);
		submitButton = (Button) findViewById(R.id.submit_button);
		
		watcher = new LocalTextWatcher();
		calPerWeight.addTextChangedListener(watcher);
		perWeight.addTextChangedListener(watcher);
		totalWeight.addTextChangedListener(watcher);
		totalCalories.addTextChangedListener(watcher);
		updateSubmitButton();
		
		automateDate();
	}
	
	/** Sets the default day to today's date **/
	@SuppressLint("SimpleDateFormat")
	private void automateDate() {
		Date today = new Date();
        TextView tv = (TextView) findViewById(R.id.date_text);
        tv.setText(new SimpleDateFormat("yyyy-MM-dd").format(today));
	}
	
	/** Creates a new log entry on submit button click **/
	public void createEntry(View view) {
		Date d;
		String de;
		int totalCal = 0;
		float calPerWeightf, perWeightf, totalWeightConf = 0;
		LogEntry l;
		
		d = convertToDate(date.getText().toString());
		de = description.getText().toString();

		/* Calls the correct constructor depending on whether total calories was provided. */
		if (!totalCalories.getText().toString().equals("")) {
			totalCal = Integer.parseInt(totalCalories.getText().toString());
			l = new LogEntry(d, de, totalCal);
		} else {
			calPerWeightf = Float.parseFloat(calPerWeight.getText().toString());
			perWeightf = Float.parseFloat(perWeight.getText().toString());
			totalWeightConf = Float.parseFloat(totalWeight.getText().toString());
			l = new LogEntry(d, de, calPerWeightf, perWeightf, totalWeightConf);
		}
		LogManager.writeToCalorieLog(l, this);
		finish();
	}
	
	public void returnToPrevActivity(View view) {
		finish();
	}
	
	/** Converts a String input in the form "yyyy-MM-dd" to a Date Object **/
	@SuppressLint("SimpleDateFormat")
	private Date convertToDate(String s) {
		Date date = new Date();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.parse(s);
		} catch (ParseException e) { e.printStackTrace(); }
		return date;
	}
	
	/** Determine whether to enable submit button based on whether
	 * the correct fields are entered.
	 */
	private void updateSubmitButton() {
		boolean enabled = (checkEditText(calPerWeight) &&
				checkEditText(perWeight) &&
				checkEditText(totalWeight)) || (checkEditText(totalCalories));
		submitButton.setEnabled(enabled);		
	}
	
	/** Disables Total Calories field if the Calories per weight/vol/serving,
	 * per weight/vol/serving or total weight/volume/serving is enabled. Vice versa.
	 */
	private void updateTextFields() {
		boolean totalCalEnabled = checkEditText(calPerWeight) ||
				checkEditText(perWeight) || 
				checkEditText(totalWeight);
		boolean othersEnabled = checkEditText(totalCalories);
		totalCalories.setEnabled(!totalCalEnabled);
		calPerWeight.setEnabled(!othersEnabled);
		perWeight.setEnabled(!othersEnabled);
		totalWeight.setEnabled(!othersEnabled);
	}
	
	/** Check if @param edit is empty **/
	private boolean checkEditText(EditText edit) {
		return !edit.getText().toString().equals("");
	}
	
	/** Watcher for text changes **/
	private class LocalTextWatcher implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) {
			updateSubmitButton();
			updateTextFields();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int count, int after) {
		}	
	}
}
