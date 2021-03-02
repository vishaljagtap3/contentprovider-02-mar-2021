package in.bitcode.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver resolver = getContentResolver();
        Cursor c;

        c = resolver.query(
                Settings.System.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        for (String col : c.getColumnNames()) {
            mt("Col: " + col);
        }

        int colId = c.getColumnIndex(Settings.System._ID);
        int colName = c.getColumnIndex(Settings.System.NAME);
        int colVal = c.getColumnIndex(Settings.System.VALUE);

        while (c.moveToNext()) {
            mt(c.getInt(colId) + " " + c.getString(colName) + "\n"
                    + c.getString(colVal) + "\n" + "-------------------");
        }
        c.close();

        mt("Call log");

        c = resolver.query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        for (String col : c.getColumnNames()) {
            mt("Col: " + col);
        }

        int colNumber = c.getColumnIndex(CallLog.Calls.NUMBER);
        int colType = c.getColumnIndex(CallLog.Calls.TYPE);
        colName = c.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int colDate = c.getColumnIndex(CallLog.Calls.DATE);

        while (c.moveToNext()) {
            mt(c.getString(colNumber) + " " + c.getString(colName) + " " + c.getInt(colType) + " " + c.getString(colDate));
        }
        c.close();


        c = resolver.query(
                Uri.parse("content://sms"),
                null,
                null,
                null,
                null
        );
        for (String col : c.getColumnNames()) {
            mt("Col: " + col);
        }

        int colPerson = c.getColumnIndex("person");
        int colBody = c.getColumnIndex("body");
        colType = c.getColumnIndex("type");

        mt("************************");

        while(c.moveToNext()) {
            mt(c.getInt(colType) + " " + c.getString(colPerson)  + " " + c.getString(colBody));
        }
        c.close();





        /*
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        c = resolver.query(uri, null, null, null, null);
        for(String col : c.getColumnNames()) {
            mt("Col: " + col);
        }
        */


        //Telephony.MmsSms.CONTENT_URI

        /*
        Uri uri = Uri.parse("content://in.bitcode.products.provider");
        uri = Uri.withAppendedPath(uri, "products");

        Cursor c = resolver.query(
                uri,
                null,
                null,
                null,
                "price desc"
        );

        while(c.moveToNext()) {
            mt(c.getInt(0) +  " " + c.getString(1)  +" " + c.getInt(2));
        }

        mt("------------");

        uri = Uri.withAppendedPath(uri, "3");
        c = resolver.query(
                uri,
                null,
                null,
                null,
                "price desc"
        );

        while(c.moveToNext()) {
            mt(c.getInt(0) +  " " + c.getString(1)  +" " + c.getInt(2));
        }

        mt("-----------------------");
        ContentValues values = new ContentValues();
        values.put("name", "Smart Watch");
        values.put("price", 45000);

        int rownum = resolver.update(
                uri,
                values,
                null,
                null
        );

        mt("Update: " + rownum);

        uri = Uri.parse("content://in.bitcode.products.provider");
        uri = Uri.withAppendedPath(uri, "products");
        c = resolver.query(
                uri,
                null,
                null,
                null,
                "price desc"
        );
        while(c.moveToNext()) {
            mt(c.getInt(0) +  " " + c.getString(1)  +" " + c.getInt(2));
        }


        mt("----------------");
        uri = Uri.withAppendedPath(uri, "3");
        int rows = resolver.delete(
                uri,
                null,
                null
        );

        mt("-------------------");
        uri = Uri.parse("content://in.bitcode.products.provider");
        uri = Uri.withAppendedPath(uri, "products");
        c = resolver.query(
                uri,
                null,
                null,
                null,
                "price desc"
        );
        while(c.moveToNext()) {
            mt(c.getInt(0) +  " " + c.getString(1)  +" " + c.getInt(2));
        }

        //insert
        uri = Uri.parse("content://in.bitcode.products.provider");
        uri = Uri.withAppendedPath(uri, "products");

        ContentValues newValues = new ContentValues();
        newValues.put("id", 20);
        newValues.put("name", "Table");
        newValues.put("price", 5600);

        Uri newUri = resolver.insert(uri, newValues);
        mt(newUri.toString());

        c = resolver.query(
                newUri,
                null,
                null,
                null,
                "price desc"
        );
        while(c.moveToNext()) {
            mt(c.getInt(0) +  " " + c.getString(1)  +" " + c.getInt(2));
        }
        */


    }

    private void mt(String text) {
        //Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        Log.e("tag", text);
    }
}