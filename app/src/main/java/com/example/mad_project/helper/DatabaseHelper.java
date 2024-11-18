package com.example.mad_project.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mad_project.entity.Contact;

// For CRUD Activities
public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_NAME = "ContactsDatabase.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "tbl_contacts";

    public static final String COL_ID = "id";
    public static final String COL_FIRSTNAME = "first_name";
    public static final String COL_LASTNAME = "last_name";
    public static final String COL_CONTACTNUMBER = "contact_number";

    //Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    //OnCreate
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("+
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_FIRSTNAME + " TEXT, " +
                COL_LASTNAME + " TEXT, " +
                COL_CONTACTNUMBER + " TEXT);";

        db.execSQL(query);
    }

    //OnUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //CREATE
    public long createContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_FIRSTNAME, contact.getFirstName());
        cv.put(COL_LASTNAME, contact.getLastName());
        cv.put(COL_CONTACTNUMBER, contact.getContactNumber());

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "Failed to Insert", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    //READ
    public Cursor readAllContacts(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!= null) cursor = db.rawQuery(query, null);

        return cursor;
    }

    //SINGLE READ BY ID
    public Cursor readContactByID(String id){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!= null) cursor = db.rawQuery(query, new String[]{id});

        return cursor;
    }

    //SINGLE READ BY NAME
    public Cursor readContactsBySearch(String s){
        String name = s+"%";
        String contact = "%"+s+"%";
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE first_name LIKE ? OR last_name LIKE ? OR contact_number LIKE ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!= null) cursor = db.rawQuery(query, new String[]{name,name,contact});
        return cursor;
    }

    //UPDATE
    public long updateContactByID(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_FIRSTNAME, contact.getFirstName());
        cv.put(COL_LASTNAME, contact.getLastName());
        cv.put(COL_CONTACTNUMBER, contact.getContactNumber());

        return db.update(TABLE_NAME, cv, "id=?", new String[]{contact.getID()+""});
    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COL_ID + "=?", new String[] {row_id});

        if (result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Removed Successfully.", Toast.LENGTH_SHORT).show();
        }
    }




}
