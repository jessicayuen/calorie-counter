/**
 * @author	Copyright (c) 2013 Jessica Yuen <jyuen@ualberta.ca>
 * 
 * Permission to use, copy, modify, and distribute this software 
 * is granted, provided that the above copyright notice appear
 * in all copies.
 */

/**
 * Manages the loading and saving of the log entries onto file.
 */
package cmput301.as1.caloriecounter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class LogManager {

	private static final String LOG_PATH = "calorieslog.sav";
	private static List<LogEntry> logEntries;
	
	/** Loads the log entries **/
	@SuppressWarnings("unchecked")
	public static void loadCalorieLog(Context ctx) {
		logEntries = new ArrayList<LogEntry>();
		try {
			FileInputStream fis = ctx.openFileInput(LOG_PATH);
			ObjectInputStream in = new ObjectInputStream(fis);
			logEntries = (ArrayList<LogEntry>) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Appends the entry onto the existing list, and saves it onto file **/
	public static void writeToCalorieLog(LogEntry logEntry, Context ctx) {
		try {
			logEntries.add(logEntry);
			FileOutputStream fos = ctx.openFileOutput(LOG_PATH, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(logEntries);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Saves the provided list of log entries onto file, overwriting previous **/
	public static void writeToCalorieLog(List<LogEntry> le, Context ctx) {
		try {
			logEntries = le;
			FileOutputStream fos = ctx.openFileOutput(LOG_PATH, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(logEntries);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<LogEntry> getLogEntries() {
		return logEntries;
	}
}
