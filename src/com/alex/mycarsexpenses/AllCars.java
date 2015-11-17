package com.alex.mycarsexpenses;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alex.mycarsexpenses.data.IntentActivity;
import com.alex.mycarsexpenses.sqllite.helper.Car;
import com.alex.mycarsexpenses.sqllite.helper.DatabaseHelper;



	public class AllCars extends Activity {
        ListView listView ;
        DatabaseHelper databaseHelper;  
        TextView text; 
        String activity;
     
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all_cars);
            text = (TextView) findViewById(R.id.noAddedRecords);
            final Intent recievedIntent = getIntent();
            activity = recievedIntent.getSerializableExtra("showActivity").toString();
            if(activity != "ALLCARS"){
            	Button button = (Button) findViewById(R.id.btnAddCar);
            	button.setVisibility(View.GONE);
            }
            databaseHelper = new DatabaseHelper(getApplicationContext());
            List<Car> allCars = databaseHelper.getAllCars();
            // Get ListView object from xml
            listView = (ListView) findViewById(R.id.cars_list);
            
            if(allCars == null || allCars.isEmpty()){             
            text.setVisibility(View.VISIBLE);
            }
            else{
            	text.setVisibility(View.GONE);
            // Assign adapter to ListView
            listView.setAdapter(new ArrayAdapter<Car>(this,
            		                                  android.R.layout.simple_list_item_1,
            		                                  android.R.id.text1,
            		                                  allCars) {

				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {
					View view = convertView;
			        if (view == null) {
			            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			            view = inflater.inflate(android.R.layout.simple_list_item_1, null);
			        }

			        Car item = getItem(position);
			        if (item!= null) {
			            // My layout has only one TextView
			            TextView itemView = (TextView) view.findViewById(android.R.id.text1);
			            if (itemView != null) {
			                // do whatever you want with your string and long
			                itemView.setText(item.getMake() + " " 
			                + item.getModel() + " " 
			                + item.getRegistrationNumber());
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
                   Car  item    = (Car) listView.getItemAtPosition(position);
                     
                      Intent intent =  new Intent(AllCars.this, CarSettings.class);
                      
                      switch (IntentActivity.valueOf(activity)) {
                      case ALLCARS:  intent = new Intent(AllCars.this, CarSettings.class);
                               break;
                      case FILLUPS:  intent = new Intent(AllCars.this, ShowFillUps.class);
                               break;
                      case SERVICES: intent = new Intent(AllCars.this, ShowServices.class);
                               break;
                      case STATISTICS: intent = new Intent(AllCars.this, ShowStats.class);
                               break;                      
                      default: intent = new Intent(AllCars.this, CarSettings.class);
                               break;
                  }
                   
                   intent.putExtra("updateCar", item);                  
                   startActivity(intent);
                  }
    
             }); 
            }
        }
    
        public void addCar(View view){
    		Intent intent = new Intent(this, CarSettings.class);    	
        	startActivity(intent);
    	}
        
        @Override
    	public boolean onCreateOptionsMenu(Menu menu) {
    		// Inflate the menu; this adds items to the action bar if it is present.
    		getMenuInflater().inflate(R.menu.all_cars, menu);
    		return true;
    	}
    	
    }

