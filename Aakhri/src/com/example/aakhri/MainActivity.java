package com.example.aakhri;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	String desc,amount,people,date;
	SQLiteDatabase db;
	 TableRow tableRow;
	   TextView textView,textView1,textView2,textView3,textView4,textView5,textView6,textView7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();

	        db=openOrCreateDatabase("MyDB1",MODE_PRIVATE, null);
	        db.execSQL("CREATE TABLE IF NOT EXISTS Aakhri(desc VARCHAR,amount VARCHAR,people VARCHAR,date VARCHAR);");

		}
	}
	public void adddata(View view)
	{
	  EditText edittext1=(EditText )findViewById(R.id.deditview);
	  EditText edittext2=(EditText )findViewById(R.id.amounteditview);
	  EditText edittext3=(EditText )findViewById(R.id.peopleeditview);
	  EditText edittext4=(EditText )findViewById(R.id.dateeditview);
	  desc=edittext1.getText().toString();
	  amount=edittext2.getText().toString();
	  people=edittext3.getText().toString();
	  date=edittext4.getText().toString();
	  db.execSQL("INSERT INTO  Aakhri VALUES('"+desc+"','"+amount+"','"+people+"','"+date+"');");
		
		 
	}
	public void showdata(View view)
	{
	    Cursor c=db.rawQuery("SELECT * from Aakhri", null);
	     int count= c.getCount();
	    c.moveToFirst();
	    TableLayout tableLayout = new TableLayout(getApplicationContext());
	    tableLayout.setVerticalScrollBarEnabled(true);
	   TableRow tableRow;
	   TextView textView,textView1,textView2,textView3,textView4,textView5,textView6,textView7;
	   tableRow = new TableRow(getApplicationContext());
	   textView=new TextView(getApplicationContext());
	   textView.setText("Description");
	   textView.setTextColor(Color.RED);
	  	textView.setTypeface(null, Typeface.BOLD);
	  	 textView.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView);
	    textView4=new TextView(getApplicationContext());
	  	textView4.setText("Amount");
	  	textView4.setTextColor(Color.RED);
	  	textView4.setTypeface(null, Typeface.BOLD);
	  	 textView4.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView4);
	    textView5=new TextView(getApplicationContext());
	  	textView5.setText("People");
	  	textView5.setTextColor(Color.RED);
	  	textView5.setTypeface(null, Typeface.BOLD);
	  	textView5.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView5);
	  	textView6=new TextView(getApplicationContext());
	  	textView6.setText("Date");
	  	textView6.setTextColor(Color.RED);
	  	textView6.setTypeface(null, Typeface.BOLD);
	  	textView6.setPadding(20, 20, 20, 20);
	  	tableRow.addView(textView6);
	   tableLayout.addView(tableRow);
	     for (Integer j = 0; j < count; j++)
	     {
	         tableRow = new TableRow(getApplicationContext());
	         textView1 = new TextView(getApplicationContext());
	         textView1.setText(c.getString(c.getColumnIndex("desc")));
	         textView2 = new TextView(getApplicationContext());
	         textView2.setText(c.getString(c.getColumnIndex("amount")));
	         textView3 = new TextView(getApplicationContext());
	         textView3.setText(c.getString(c.getColumnIndex("people")));
	         textView7 = new TextView(getApplicationContext());
	         textView7.setText(c.getString(c.getColumnIndex("date")));
	        
	         textView1.setPadding(20, 20, 20, 20);
	         textView2.setPadding(20, 20, 20, 20);
	         textView3.setPadding(20, 20, 20, 20);
	         textView7.setPadding(20, 20, 20, 20);
	         tableRow.addView(textView1);
	         tableRow.addView(textView2);
	         tableRow.addView(textView3);
	         tableRow.addView(textView7);
	         tableLayout.addView(tableRow);
	         c.moveToNext() ;
	     }
	     setContentView(tableLayout);
	db.close();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
