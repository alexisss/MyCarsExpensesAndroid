package com.alex.mycarsexpenses;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alex.mycarsexpenses.sqllite.helper.Car;
import com.alex.mycarsexpenses.sqllite.helper.DatabaseHelper;

public class CarSettings extends Activity {
	DatabaseHelper databaseHelper;
	Car chosenCar;
	TextView text;
	AlertDialog.Builder alertDialogBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_fill_ups);
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
						CarSettings.this.finish();
					}
				  });						
				
		chosenCar = (Car) intent.getSerializableExtra("updateCar");
		if (chosenCar != null) {
			EditText editMake = (EditText) findViewById(R.id.editMake);
			editMake.setText(chosenCar.getMake());
			EditText editModel = (EditText) findViewById(R.id.editModel);
			editModel.setText(chosenCar.getModel());
			EditText editYear = (EditText) findViewById(R.id.editYear);
			editYear.setText(Integer.toString(chosenCar.getYear()));
			EditText editReg = (EditText) findViewById(R.id.editReg);
			editReg.setText(chosenCar.getRegistrationNumber());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_fill_ups, menu);
		return true;
	}
	
	private boolean validate(EditText e) {
		if (e.getText() == null || e.getText().toString() == null
				|| "".equals(e.getText().toString())) {
			return false;
		}

		return true;
	}

	public void saveCar(View view) {
		if (chosenCar == null) {
			Car car = new Car();
			
			if (validate((EditText) findViewById(R.id.editMake)) ||
				validate((EditText) findViewById(R.id.editModel)) ||
				validate((EditText) findViewById(R.id.editYear)) ||
				validate((EditText) findViewById(R.id.editReg))) {
				
				car.setMake(((EditText) findViewById(R.id.editMake)).getText()
						.toString());
				car.setModel(((EditText) findViewById(R.id.editModel)).getText()
						.toString());			
				car.setRegistrationNumber(((EditText) findViewById(R.id.editReg))
						.getText().toString());
				car.setYear(Integer
						.parseInt(((EditText) findViewById(R.id.editYear))
								.getText().toString()));
				databaseHelper.addCar(car);
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
				
				chosenCar.setMake(((EditText) findViewById(R.id.editMake))
						.getText().toString());
				chosenCar.setModel(((EditText) findViewById(R.id.editModel))
						.getText().toString());
				chosenCar.setYear(Integer
						.parseInt(((EditText) findViewById(R.id.editYear))
								.getText().toString()));
				chosenCar
						.setRegistrationNumber(((EditText) findViewById(R.id.editReg))
								.getText().toString());

				databaseHelper.updateCar(chosenCar);
			} else {			 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
					}				
		}
		databaseHelper.closeDB();
		

		Intent intent = new Intent(this, AllCars.class);
		startActivity(intent);		
	}

	public void cancelChanges(View view) {
		CarSettings.this.finish();
	}
}
