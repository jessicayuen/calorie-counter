/**
 * @author	Copyright (c) 2013 Jessica Yuen <jyuen@ualberta.ca>
 * 
 * Permission to use, copy, modify, and distribute this software 
 * is granted, provided that the above copyright notice appear
 * in all copies.
 */

/**
 * Calculates the total calories consumed, the total overall 
 * consumption time, and the average calories consumed per day
 * for a given list of calorie log entries.
 */

package cmput301.as1.caloriecounter;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.widget.TextView;

public class CalorieSummary {

	private int totalCalories;
	private float totalDays;
	private float averageCalories;
	
	public CalorieSummary(List<LogEntry> logEntries) {
		calculateSummary(logEntries);
	}
	
	/** Calculates the total calories, overall consumption time, and
	 * average calories consumed per day.
	 * @param logEntries The list of calorie entries.
	 */
	public void calculateSummary(List<LogEntry> logEntries) {
		totalCalories = 0;
	    totalDays = averageCalories = 0;
		
		for (LogEntry l : logEntries)
			totalCalories += l.getTotalCalories();
		
		if (logEntries.size() > 1) {
			Date max = getMaxDate(logEntries);
			Date min = getMinDate(logEntries);
			totalDays = ( max.getTime() - min.getTime() ) / ( 1000 * 60 * 60 * 24 );
		}
		
		if (logEntries.size() != 0)
			averageCalories = totalCalories / logEntries.size();
	}
	
	/** Displays the information calculated on the provided Activity **/
	public void display(Activity activity) {
		TextView textView = (TextView) activity.findViewById(R.id.total_cals);
		textView.setText("Total calorie consumption: " 
				+ Integer.toString(totalCalories) + " cal");
	
		textView = (TextView) activity.findViewById(R.id.total_time);
		textView.setText("Total overall consumption time: " 
				+ String.format("%.1f", totalDays) + " days");
		
		textView = (TextView) activity.findViewById(R.id.average_cals);
		textView.setText("Average calories consumed per day: " 
				+ String.format("%.1f", averageCalories) + " cal");
	}
	
	/** Retrieve the latest date in a given list of log entries **/
	public Date getMaxDate(List<LogEntry> logEntries) {
		Date max;
		if (logEntries.size() < 1)
			return null;
		
		max = logEntries.get(0).getDate();
		for (int i = 1; i < logEntries.size(); i++) {
			if (logEntries.get(i).getDate().compareTo(max) > 0)
				max = logEntries.get(i).getDate();
		}
		return max;
	}
	
	/** Retrieve the earliest date in a given list of log entries **/
	public Date getMinDate(List<LogEntry> logEntries) {
		Date min;
		if (logEntries.size() < 1)
			return null;
		
		min = logEntries.get(0).getDate();
		for (int i = 1; i < logEntries.size(); i++) {
			if (logEntries.get(i).getDate().compareTo(min) < 0)
				min = logEntries.get(i).getDate();
		}
		return min;
	}

	/** Getters and Setters **/

	public float getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(int totalCalories) {
		this.totalCalories = totalCalories;
	}

	public float getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(float totalDays) {
		this.totalDays = totalDays;
	}

	public float getAverageCalories() {
		return averageCalories;
	}

	public void setAverageCalories(float averageCalories) {
		this.averageCalories = averageCalories;
	}	
}
