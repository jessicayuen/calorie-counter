/**
 * @author	Copyright (c) 2013 Jessica Yuen <jyuen@ualberta.ca>
 * 
 * Permission to use, copy, modify, and distribute this software 
 * is granted, provided that the above copyright notice appear
 * in all copies.
 */

/**
 * Contains the details of a calorie log entry with the date,
 * description, total calories, and optional calories per
 * weight/vol/serving, per weight/vol/serving, and total
 * weight/vol/serving consumption
 */

package cmput301.as1.caloriecounter;

import java.io.Serializable;
import java.util.Date;

public class LogEntry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date date;
	private String description;
	private int totalCalories;
	private float calPerWeight;
	private float perWeight;
	private float totalWeightCon;
	
	/** Constructor where total calories is provided **/
	public LogEntry(Date date, String description, int totalCalories) {
		this.date = date;
		this.description = description;
		this.totalCalories = totalCalories;
		calPerWeight = perWeight = totalWeightCon = -1;
	}
	
	/** Constructor where total calories is not provided **/
	public LogEntry(Date date, String description, float calPerWeight,
			float perWeight, float totalWeightCon) {
		this.date = date;
		this.description = description;
		this.calPerWeight = calPerWeight;
		this.perWeight = perWeight;
		this.totalWeightCon = totalWeightCon;
		if (perWeight != 0)
			totalCalories = (int) ( (totalWeightCon / perWeight) * calPerWeight);
		else
			totalCalories = 0;
	}

	/** Getters and Setters **/
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(int totalCalories) {
		this.totalCalories = totalCalories;
	}

	public float getCalPerWeight() {
		return calPerWeight;
	}

	public void setCalPerWeight(float calPerWeight) {
		this.calPerWeight = calPerWeight;
	}

	public float getPerWeight() {
		return perWeight;
	}

	public void setPerWeight(float perWeight) {
		this.perWeight = perWeight;
	}

	public float getTotalWeightCon() {
		return totalWeightCon;
	}

	public void setTotalWeightCon(float totalWeightCon) {
		this.totalWeightCon = totalWeightCon;
	}
	
}
