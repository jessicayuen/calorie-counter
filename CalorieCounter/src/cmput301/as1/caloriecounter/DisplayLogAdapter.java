/**
 * @author	Copyright (c) 2013 Jessica Yuen <jyuen@ualberta.ca>
 * 
 * Permission to use, copy, modify, and distribute this software 
 * is granted, provided that the above copyright notice appear
 * in all copies.
 */

/**
 * Adapter for displaying the log entries, and removal of calorie 
 * log entries.
 */

package cmput301.as1.caloriecounter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class DisplayLogAdapter extends BaseAdapter implements OnClickListener {

    private Context context;
    private List<LogEntry> logEntries;

    public DisplayLogAdapter(Context context, List<LogEntry> logEntries) {
        this.context = context;
        this.logEntries = logEntries;
    }

    /** return the number of log entries **/
    public int getCount() {
        return logEntries.size();
    }

    /** return the item at @param position **/
    public Object getItem(int position) {
        return logEntries.get(position);
    }

    /** get the id of item at @param position **/
    public long getItemId(int position) {
        return position;
    }

    /** set up the contents of the ListView **/
    @SuppressLint("SimpleDateFormat")
	public View getView(int position, View convertView, ViewGroup viewGroup) {
        LogEntry entry = logEntries.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_entry, null);
        }
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        tvDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(entry.getDate()));
        
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        tvDescription.setText(entry.getDescription());

        TextView tvTotalCalories = (TextView) convertView.findViewById(R.id.tvTotalCalories);
        tvTotalCalories.setText(Integer.toString(entry.getTotalCalories()));

        if (entry.getCalPerWeight() > 0) {
	        TextView tvCalPerWeight = (TextView) convertView.findViewById(R.id.tvCalPerWeight);
	        tvCalPerWeight.setText(String.format("%.1f", entry.getCalPerWeight()));
	        
	        TextView tvPerWeight = (TextView) convertView.findViewById(R.id.tvPerWeight);
	        tvPerWeight.setText(String.format("%.1f", entry.getPerWeight()));
	        
	        TextView tvTotalWeightCon = (TextView) convertView.findViewById(R.id.tvTotalWeightCon);
	        tvTotalWeightCon.setText(String.format("%.1f", entry.getTotalWeightCon()));
        }
        
        // Set up remove button listener
        Button btnRemove = (Button) convertView.findViewById(R.id.btnRemove);
        btnRemove.setFocusableInTouchMode(false);
        btnRemove.setFocusable(false);
        btnRemove.setOnClickListener(this);
        btnRemove.setTag(entry);

        return convertView;
    }

    /** removal button listener **/
    @Override
    public void onClick(View view) {
        LogEntry entry = (LogEntry) view.getTag();
        logEntries.remove(entry);
        LogManager.writeToCalorieLog(logEntries, context);
        notifyDataSetChanged();
    }
}
