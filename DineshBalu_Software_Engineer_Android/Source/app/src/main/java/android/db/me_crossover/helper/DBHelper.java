package android.db.me_crossover.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.db.me_crossover.model.Conference;
import android.db.me_crossover.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by dineshbalu on 27/11/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "conference_db.sqlite";
    private static final String TABLE_USER = "user";
    private static final String TABLE_CONFERENCE = "conference";
    private static String DB_PATH = "";

    private static DBHelper mHelper; //Singleton instance

    private Context mContext;
    private SQLiteDatabase mDataBase;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);

        mContext = context;

        ContextWrapper cw =new ContextWrapper( mContext.getApplicationContext());
        DB_PATH =cw.getDatabasePath(DB_NAME).getAbsolutePath(); //edited to databases

        File file = new File(DB_PATH);
        //if( file.exists() )
            file.mkdirs();

        boolean dbexist = checkdatabase();
        if ( !dbexist ) {
            System.out.println("Database doesn't exist");
            try {
                createdatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Database exists");
        opendatabase();

    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            System.out.println(" Database exists.");
        } else {
                copydatabase();

        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        File file = new File( outfilename );

        if( file.exists() )
            file.createNewFile();
        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if(mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }

    public User getUser( String email , String password ) {

        Cursor c = mDataBase.query( TABLE_USER , null , "email = ? and password = ?" , new String[]{email,password} , null , null , null );

        if( c != null && c.getCount() > 0 ) {
            c.moveToFirst();
            return new User(c);
        }
        else
            return null;

    }

    public ArrayList<Conference> getConferences() {

        Cursor c = mDataBase.query( TABLE_CONFERENCE , null , null , null , null , null , null );

        ArrayList<Conference> items = new ArrayList<>();

        if( c != null && c.getCount() > 0 ) {

            c.moveToFirst();

            while( !c.isClosed() ){
                items.add( new Conference(c) );

                if( c.isLast() )
                    c.close();
                else
                    c.moveToNext();

            }

        }

        return items;
    }

    public static DBHelper getInstance(Context context) {

        if( mHelper == null )
            mHelper = new DBHelper(context);

        return mHelper;
    }

    public void scheduleConference(String title, String detail, String startDate, String endDate, String venue, int manager) {

        ContentValues values = new ContentValues();
        values.put( "conference" , title );
        values.put( "description" , detail );
        values.put( "from_date" , startDate );
        values.put( "to_date" , endDate );
        values.put( "venue" , venue );
        values.put( "manager" , manager );

            mDataBase.insert( TABLE_CONFERENCE , null , values );

    }
}
