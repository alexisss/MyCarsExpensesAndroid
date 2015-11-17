package com.alex.mycarsexpenses.data;

import com.alex.mycarsexpenses.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class SpinnerActivity extends Activity
{
private Spinner spinner_volume;
private Button btnSubmit;

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	addListenerOnButton();
	addListenerOnSpinnerItemSelection();
}

// add items into spinner dynamically

public void addListenerOnSpinnerItemSelection() {
	
	spinner_volume.setOnItemSelectedListener(new CustomOnItemSelectedListener());
}

public void addListenerOnButton() {
	 
	btnSubmit = (Button) findViewById(R.id.btnSave);
 
	btnSubmit.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
 
	    
	  }
 
	});
  }
	
}