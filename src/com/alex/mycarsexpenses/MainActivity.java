package com.alex.mycarsexpenses;

import com.alex.mycarsexpenses.R;
import com.alex.mycarsexpenses.data.IntentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        intent  = new Intent(this, AllCars.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void showFillUps(View view){    	
    	intent.putExtra("showActivity", IntentActivity.FILLUPS);        
    	startActivity(intent);
    }
    
    public void showServices(View view){    	
    	intent.putExtra("showActivity", IntentActivity.SERVICES);
    	startActivity(intent);
    }    

	public void showStats(View view){		
		intent.putExtra("showActivity", IntentActivity.STATISTICS);
    	startActivity(intent);
	}
	
	public void allCars(View view){		
		intent.putExtra("showActivity", IntentActivity.ALLCARS);
    	startActivity(intent);
	}
	
}
