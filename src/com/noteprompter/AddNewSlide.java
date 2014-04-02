package com.noteprompter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.getpebble.*;

public class AddNewSlide extends Activity 
{
	
	private int numSlides;
	Button button;
	EditText editText;
	String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Intent intent = getIntent();
		numSlides = intent.getIntExtra("com.noteprompter.numSlides", 0);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_slide);
		button = (Button)findViewById(R.id.button);
		editText = (EditText)findViewById(R.id.editText);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() 
	{

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_slide, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void finishedTyping(View view)
	{
		text = this.editText.getText().toString();
		Log.v("EditText", text);
		Intent data = new Intent();
		data.putExtra("result", text);
		setResult(RESULT_OK, data);
		finish();
		
	}

}
