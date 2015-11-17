package com.alex.mycarsexpenses;

import java.math.BigDecimal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.alex.mycarsexpenses.sqllite.helper.Car;
import com.alex.mycarsexpenses.sqllite.helper.DatabaseHelper;
import com.alex.mycarsexpenses.sqllite.helper.FillUpRecord;

public class FillUpSettings extends Activity {
	DatabaseHelper databaseHelper;
	FillUpRecord chosenFillUp;
	TextView text;
	AlertDialog.Builder alertDialogBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fill_up_settings);
		text = (TextView) findViewById(R.id.notCorrectInfo);
		databaseHelper = new DatabaseHelper(getApplicationContext());
		Intent intent = getIntent();
		alertDialogBuilder = new AlertDialog.Builder(
				this);
		 
			// set title
			alertDialogBuilder.setTitle("The fields are not correctly filled");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Click 'OK' to exit!")
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						FillUpSettings.this.finish();
					}
				  });						
				
		chosenFillUp = (FillUpRecord) intent.getSerializableExtra("updateFillUp");
		if (chosenFillUp != null) {
						
			DatePicker datePicker = (DatePicker) findViewById(R.id.editDate);
			 int day = datePicker.getDayOfMonth();
			 int month = datePicker.getMonth() + 1;
			 int year = datePicker.getYear();
			 
			 EditText editVolumeCosts = (EditText) findViewById(R.id.editVolumeCost);
			 editVolumeCosts.setText(chosenFillUp.getVolumeCost().toString());
			 EditText editVolume = (EditText) findViewById(R.id.editVolume);
			 editVolume.setText(chosenFillUp.getVolume().toString());
			 EditText editOdometer = (EditText) findViewById(R.id.editOdometer);
			 editOdometer.setText(chosenFillUp.getOdometer().toString());
			 TextView totalCost = (TextView) findViewById(R.id.totalCost);
			 //totalCost.setText(new String(chosenFillUp.getVolume() * chosenFillUp.getVolume()).toString());
			 EditText editGasStation = (EditText) findViewById(R.id.editGasStation);
			 editOdometer.setText(chosenFillUp.getGasStation().toString());
			 
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fill_up_settings, menu);
		return true;
	}

	
	private boolean validate(EditText e) {
		if (e.getText() == null || e.getText().toString() == null
				|| "".equals(e.getText().toString())) {
			return false;
		}

		return true;
	}

	public void saveFillUp(View view) {
		if (chosenFillUp == null) {
			FillUpRecord fillUp = new FillUpRecord();
			
			if (validate((EditText) findViewById(R.id.editDate)) ||
				validate((EditText) findViewById(R.id.editVolumeCost)) ||
				validate((EditText) findViewById(R.id.editVolume)) ||
				validate((EditText) findViewById(R.id.editOdometer))||
				validate((EditText) findViewById(R.id.editGasStation))) {
				
				fillUp.setVolumeCost(new BigDecimal((EditText) findViewById(R.id.editVolumeCost)).getText()
						.toString());
				fillUp.setVolume(((EditText) findViewById(R.id.editVolume)).getText()
						.toString());			
				fillUp.setOdometer(((EditText) findViewById(R.id.editOdometer))
						.getText().toString());
				fillUp.setGasStation(Integer
						.parseInt(((EditText) findViewById(R.id.editGasStation))
								.getText().toString()));
				databaseHelper.addFillUp(fillUp);
			} else {
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
			}
		} else {

			if (validate((EditText) findViewById(R.id.editMake)) ||
				validate((EditText) findViewById(R.id.editModel)) ||
				validate((EditText) findViewById(R.id.editYear)) ||
				validate((EditText) findViewById(R.id.editReg))) {
				
				chosenFillUp.setVolumeCost(new BigDecimal((EditText) findViewById(R.id.editVolumeCost)).getText()
						.toString());
				chosenFillUp.setVolume(((EditText) findViewById(R.id.editVolume)).getText()
						.toString());			
				chosenFillUp.setOdometer(((EditText) findViewById(R.id.editOdometer))
						.getText().toString());
				chosenFillUp.setGasStation(Integer
						.parseInt(((EditText) findViewById(R.id.editGasStation))
								.getText().toString()));

				databaseHelper.updateFillUp(chosenFillUp);
			} else {			 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
					}				
		}
		databaseHelper.closeDB();
		

		Intent intent = new Intent(this, ShowFillUps.class);
		startActivity(intent);		
	}

	public void cancelChanges(View view) {
		finish();
	}
}
