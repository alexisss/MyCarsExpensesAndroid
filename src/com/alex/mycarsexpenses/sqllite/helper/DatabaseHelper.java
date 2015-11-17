package com.alex.mycarsexpenses.sqllite.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "carExpensesManager";

	// Table Names
	private static final String TABLE_CARS = "cars";
	private static final String TABLE_FILLUP_RECORDS = "fillup_records";
	private static final String TABLE_SERVICE_RECORDS = "service_records";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_CAR_ID = "carId";
	private static final String KEY_CREATED_AT = "created_at";
	private static final String KEY_ODOMETER = "odometer";
	private static final String KEY_TOTAL_COST = "total_cost";

	// CARS Table - column names
	private static final String KEY_MAKE = "make";
	private static final String KEY_MODEL = "model";
	private static final String KEY_YEAR = "year";
	private static final String KEY_REGNUMBER = "registrationNumber";
	private static final String KEY_VOLUME_TYPE = "volume_type";

	// FILLUP_RECORDS Table - column names
	private static final String KEY_VOLUME_COST = "volume_cost";
	private static final String KEY_VOLUME = "volume";
	private static final String KEY_FUEL_TYPE = "fuel_type";
	private static final String KEY_GAS_STATION = "gas_station";

	// SERVICE_RECORDS Table - column names
	private static final String KEY_SERVICE_COST = "service_cost";
	private static final String KEY_SERVICE_CENTER = "service_center";

	// Table Create Statements
	// Cars table create statement
	private static final String CREATE_TABLE_CARS = "CREATE TABLE "
			+ TABLE_CARS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MAKE
			+ " TEXT," + KEY_MODEL + " TEXT," + KEY_YEAR + " INTEGER,"
			+ KEY_REGNUMBER + " TEXT," + KEY_VOLUME_TYPE + " TEXT" + ")";

	// Fill-ups table create statement
	private static final String CREATE_TABLE_FILLUPS = "CREATE TABLE "
			+ TABLE_FILLUP_RECORDS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_CAR_ID + " INTEGER," + KEY_CREATED_AT + " INTEGER,"
			+ KEY_VOLUME_COST + " REAL," + KEY_VOLUME + " REAL,"
			+ KEY_FUEL_TYPE + " TEXT," + KEY_ODOMETER + " TEXT,"
			+ KEY_GAS_STATION + " TEXT," + KEY_TOTAL_COST + " REAL" + ")";

	// todo_tag table create statement
	private static final String CREATE_TABLE_SERVICES = "CREATE TABLE "
			+ TABLE_SERVICE_RECORDS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_CAR_ID + " INTEGER," + KEY_CREATED_AT + " INTEGER,"
			+ KEY_SERVICE_COST + " REAL," + KEY_ODOMETER + " TEXT,"
			+ KEY_SERVICE_CENTER + " TEXT" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		Log.d("test", CREATE_TABLE_CARS);
		db.execSQL(CREATE_TABLE_CARS);
		Log.d("test", CREATE_TABLE_FILLUPS);
		db.execSQL(CREATE_TABLE_FILLUPS);
		Log.d("test", CREATE_TABLE_SERVICES);
		db.execSQL(CREATE_TABLE_SERVICES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILLUP_RECORDS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_RECORDS);

		// create new tables
		onCreate(db);
	}

	// ///////////// CRUD OPERATIONS FOR CARS START HERE ///////////////

	/*
	 * Creating a car
	 */
	public long addCar(Car car) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_MAKE, car.getMake());
		values.put(KEY_MODEL, car.getModel());
		values.put(KEY_YEAR, car.getYear());
		values.put(KEY_REGNUMBER, car.getRegistrationNumber());

		// insert row
		long car_id = db.insert(TABLE_CARS, null, values);
		return car_id;
	}

	/*
	 * get single car
	 */
	public Car getCar(long car_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_CARS + " WHERE "
				+ KEY_ID + " = " + car_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Car car = new Car();
		car.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		car.setMake(c.getString(c.getColumnIndex(KEY_MAKE)));
		car.setModel(c.getString(c.getColumnIndex(KEY_MODEL)));
		car.setYear(c.getInt(c.getColumnIndex(KEY_YEAR)));
		car.setRegistrationNumber(c.getString(c.getColumnIndex(KEY_REGNUMBER)));
		return car;
	}

	/*
	 * getting all cars
	 */
	public List<Car> getAllCars() {
		List<Car> cars = new ArrayList<Car>();
		String selectQuery = "SELECT  * FROM " + TABLE_CARS;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Car car = new Car();
				car.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				car.setMake(c.getString(c.getColumnIndex(KEY_MAKE)));
				car.setModel(c.getString(c.getColumnIndex(KEY_MODEL)));
				car.setYear(c.getInt(c.getColumnIndex(KEY_YEAR)));
				car.setRegistrationNumber(c.getString(c
						.getColumnIndex(KEY_REGNUMBER)));

				// adding to cars list
				cars.add(car);
			} while (c.moveToNext());
		}

		return cars;
	}

	/*
	 * Updating a car
	 */
	public int updateCar(Car car) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_MAKE, car.getMake());
		values.put(KEY_MODEL, car.getModel());
		values.put(KEY_YEAR, car.getYear());
		values.put(KEY_REGNUMBER, car.getRegistrationNumber());

		// updating row
		return db.update(TABLE_CARS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(car.getId()) });
	}

	/*
	 * Deleting a car
	 */
	public void deleteCar(long car_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CARS, KEY_ID + " = ?",
				new String[] { String.valueOf(car_id) });
	}

	// ///////////// CRUD OPERATIONS FOR FILL-UPS START HERE ///////////////
	/*
	 * Creating a fill-up
	 */
	public long addFillUp(FillUpRecord fillUp) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CAR_ID, fillUp.getCarId());
		values.put(KEY_CREATED_AT, fillUp.getDate().getTime());
		values.put(KEY_VOLUME_COST, fillUp.getVolumeCost().doubleValue());
		values.put(KEY_VOLUME, fillUp.getVolume());
		values.put(KEY_ODOMETER, fillUp.getOdometer());
		values.put(KEY_GAS_STATION, fillUp.getGasStation());
		values.put(KEY_TOTAL_COST, fillUp.getTotalCost().doubleValue());

		// insert row
		long fillUp_id = db.insert(TABLE_FILLUP_RECORDS, null, values);
		return fillUp_id;
	}

	/*
	 * get single fill-up
	 */
	public FillUpRecord getFillUp(long fillUp_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_FILLUP_RECORDS
				+ " WHERE " + KEY_ID + " = " + fillUp_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		FillUpRecord fillUp = new FillUpRecord();
		fillUp.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		fillUp.setCarId(c.getInt(c.getColumnIndex(KEY_CAR_ID)));
		fillUp.setDate(new Date(c.getInt(c.getColumnIndex(KEY_CREATED_AT))));
		fillUp.setOdometer(c.getDouble(c.getColumnIndex(KEY_ODOMETER)));
		fillUp.setVolumeCost(new BigDecimal(c.getColumnIndex(KEY_VOLUME_COST)));
		fillUp.setVolume(c.getDouble(c.getColumnIndex(KEY_VOLUME)));
		fillUp.setTotalCost(new BigDecimal(c.getColumnIndex(KEY_TOTAL_COST)));
		return fillUp;
	}

	/*
	 * getting all fill-ups
	 */
	public List<FillUpRecord> getFillUps(int carId) {
		List<FillUpRecord> fillups = new ArrayList<FillUpRecord>();
		String selectQuery = "SELECT * FROM" + TABLE_FILLUP_RECORDS + "WHERE KEY_CAR_ID == carId";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				FillUpRecord fillUp = new FillUpRecord();
				fillUp.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				fillUp.setCarId(c.getInt(c.getColumnIndex(KEY_CAR_ID)));
				fillUp.setDate(new Date(c.getInt(c.getColumnIndex(KEY_CREATED_AT))));
				fillUp.setOdometer(c.getDouble(c.getColumnIndex(KEY_ODOMETER)));
				fillUp.setVolumeCost(new BigDecimal(c.getColumnIndex(KEY_VOLUME_COST)));
				fillUp.setVolume(c.getDouble(c.getColumnIndex(KEY_VOLUME)));
				fillUp.setTotalCost(new BigDecimal(c.getColumnIndex(KEY_TOTAL_COST)));

				// adding to fill-ups list
				fillups.add(fillUp);
			} while (c.moveToNext());
		}

		return fillups;
	}

	/*
	 * Updating a fill-up
	 */
	public int updateFillUp(FillUpRecord fillUp) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CAR_ID, fillUp.getCarId());
		values.put(KEY_CREATED_AT, fillUp.getDate().toString());
		values.put(KEY_VOLUME_COST, fillUp.getVolumeCost().toString());
		values.put(KEY_VOLUME, fillUp.getVolume());
		values.put(KEY_ODOMETER, fillUp.getOdometer());
		values.put(KEY_GAS_STATION, fillUp.getGasStation());
		values.put(KEY_TOTAL_COST, fillUp.getTotalCost().toString());

		// updating row
		return db.update(TABLE_FILLUP_RECORDS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(fillUp.getId()) });
	}

	/*
	 * Deleting a fill-up
	 */
	public void deleteFillUp(long fillUp_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FILLUP_RECORDS, KEY_ID + " = ?",
				new String[] { String.valueOf(fillUp_id) });
	}

	// ///////////// CRUD OPERATIONS FOR SERVICES START HERE ///////////////
	/*
	 * Creating a service
	 */
	public long addService(ServiceRecord service) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CAR_ID, service.getCarId());
		values.put(KEY_CREATED_AT, service.getDate().getTime());
		values.put(KEY_ODOMETER, service.getOdometer());
		values.put(KEY_SERVICE_CENTER, service.getServiceCenter());
		values.put(KEY_SERVICE_COST, service.getServiceCost().doubleValue());

		// insert row
		long service_id = db.insert(TABLE_SERVICE_RECORDS, null, values);
		return service_id;
	}

	/*
	 * get single service
	 */
	public ServiceRecord getService(long service_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_SERVICE_RECORDS
				+ " WHERE " + KEY_ID + " = " + service_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		ServiceRecord service = new ServiceRecord();
		service.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		service.setCarId(c.getInt(c.getColumnIndex(KEY_CAR_ID)));
		service.setDate(new Date(c.getInt(c.getColumnIndex(KEY_CREATED_AT))));
		service.setOdometer(c.getDouble(c.getColumnIndex(KEY_ODOMETER)));
		service.setServiceCenter(c.getString(c.getColumnIndex(KEY_SERVICE_CENTER)));
		service.setServiceCost(new BigDecimal(c.getString(c.getColumnIndex(KEY_SERVICE_COST))));		
		return service;
	}

	/*
	 * getting all service
	 */
	public List<ServiceRecord> getServices() {
		List<ServiceRecord> services = new ArrayList<ServiceRecord>();
		String selectQuery = "SELECT  * FROM " + TABLE_SERVICE_RECORDS;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				ServiceRecord service = new ServiceRecord();
				service.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				service.setCarId(c.getInt(c.getColumnIndex(KEY_CAR_ID)));
				service.setDate(new Date(c.getInt(c.getColumnIndex(KEY_CREATED_AT))));
				service.setOdometer(c.getDouble(c.getColumnIndex(KEY_ODOMETER)));
				service.setServiceCenter(c.getString(c.getColumnIndex(KEY_SERVICE_CENTER)));
				service.setServiceCost(new BigDecimal(c.getString(c.getColumnIndex(KEY_SERVICE_COST))));

				// adding to services list
				services.add(service);
			} while (c.moveToNext());
		}

		return services;
	}

	/*
	 * Updating a service
	 */
	public int updateFillUp(ServiceRecord service) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put(KEY_CAR_ID, service.getCarId());
		values.put(KEY_CREATED_AT, service.getDate().getTime());
		values.put(KEY_ODOMETER, service.getOdometer());
		values.put(KEY_SERVICE_CENTER, service.getServiceCenter());
		values.put(KEY_SERVICE_COST, service.getServiceCost().doubleValue());


		// updating row
		return db.update(TABLE_SERVICE_RECORDS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(service.getId()) });
	}

	/*
	 * Deleting a service
	 */
	public void deleteService(long service_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SERVICE_RECORDS, KEY_ID + " = ?",
				new String[] { String.valueOf(service_id) });
	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
}
