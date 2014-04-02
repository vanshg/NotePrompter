package com.noteprompter;

import android.os.Bundle;
import java.util.UUID;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;


public class MainActivity extends Activity 
{
	private int numSlides = 0;
	private ArrayList <String> allTheText = new ArrayList <String>();
	//private ArrayAdapter <String> arrayAdapter = new ArrayAdapter <String> (context, android.R.layout.simple_list_item_1);
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		/*listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(arrayAdapter);
		arrayAdapter.add(allTheText.get(allTheText.size() - 1));*/
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_add)
		{
			addSlide();
			return true;
		}
		
		else if (id == R.id.action_send)
		{
			sendMessage();
			return true;
		}
		return false;
	}
	
	public void addSlide()
	{
		Intent intent = new Intent(this, AddNewSlide.class);
		intent.putExtra("com.noteprompter.numSlides", ++numSlides);
		startActivityForResult(intent, 0);
		
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		String result = data.getStringExtra("result");
		allTheText.add(result);
	}
	
	
	public void sendMessage()
	{
		if(!PebbleKit.isWatchConnected(getApplicationContext()))
		{
			Toast.makeText(getApplicationContext(), "Pebble is not connected. Please check connection.", Toast.LENGTH_SHORT).show();
		}
		else
		{
			UUID pebbleUUID = UUID.fromString("6a886a18-d9bd-4a1f-ac74-3e0bbdf65a78");
			PebbleDictionary dict = new PebbleDictionary();
			PebbleKit.startAppOnPebble(getApplicationContext(), pebbleUUID);
			for(String s : allTheText)
			{
				dict.addString(0, s);
				PebbleKit.sendDataToPebble(getApplicationContext(), pebbleUUID, dict);
				dict.remove(0);
				try
				{
					TimeUnit.MILLISECONDS.sleep(100);
				}
				catch(Exception e)
				{}
			}
		}
		
	}
	

}
