package com.alex.mycarsexpenses;

import java.text.SimpleDateFormat;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alex.mycarsexpenses.data.IntentActivity;
import com.alex.mycarsexpenses.sqllite.helper.Car;
import com.alex.mycarsexpenses.sqllite.helper.DatabaseHelper;
import com.alex.mycarsexpenses.sqllite.helper.FillUpRecord;

public class ShowFillUps extends Activity {

	ListView listView ;
    DatabaseHelper databaseHelper;  
    TextView text; 
    SimpleDateFormat formatter;
    Car car;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fill_ups);
		 text = (TextView) findViewById(R.id.noAddedRecords);
         final Intent recievedIntent = getIntent();         
         formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
         databaseHelper = new DatabaseHelper(getApplicationContext());
         car = (Car) recievedIntent.getSerializableExtra("updateCar");
         List<FillUpRecord> allFillUps = databaseHelper.getFillUps(car.getId());
         
         // Get ListView object from xml
         listView = (ListView) findViewById(R.id.fillups_list);
         
         if(allFillUps == null || allFillUps.isEmpty()){             
         text.setVisibility(View.VISIBLE);
         }
         else{
         	text.setVisibility(View.GONE);
         // Assign adapter to ListView
         listView.setAdapter(new ArrayAdapter<FillUpRecord>(this,
         		                                  android.R.layout.simple_list_item_1,
         		                                  android.R.id.text1,
         		                                  allFillUps) {

				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {
					View view = convertView;
			        if (view == null) {
			            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			            view = inflater.inflate(android.R.layout.simple_list_item_1, null);
			        }

			        FillUpRecord item = getItem(position);
			        if (item!= null) {
			            // My layout has only one TextView
			            TextView itemView = (TextView) view.findViewById(android.R.id.text1);
			            
			            if (itemView != null) {
			                // do whatever you want with your string and long
			                itemView.setText(			               
			               formatter.format(item.getDate()) + " " 
			                + item.getOdometer() + " km " + " ()"  
			                + item.getVolume() + "litres ");
			            }
			         }

			        return view;
				}
         });  
         
        
         
         // ListView Item Click Listener
         listView.setOnItemClickListener(new OnItemClickListener() {
        		 
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                  
                  // ListView Clicked item value
                  FillUpRecord  item    = (FillUpRecord) listView.getItemAtPosition(position);
                    
                  Intent intent =  new Intent(ShowFillUps.this, FillUpSettings.class);
                     
                  intent.putExtra("updateFillUp", item);                  
                  startActivity(intent);
                 }
   
            }); 
         }
     }
 
     public void addFillUpRecord(View view){
 		Intent intent = new Intent(this, FillUpSettings.class);    	
     	startActivity(intent);
 	}
     
   

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fill_ups, menu);
		return true;
	}

}
