package sg.edu.np.mad.p02.activity1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "userDB.db";
    private static final String TABLE_USER = "users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FOLLOWED = "followed";

    @Override
    public void onCreate(SQLiteDatabase db){

        //Prevent stacking of data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        String CREATE_USER_TABLE = "CREATE TABLE " +
                TABLE_USER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_FOLLOWED + " BOOL" +
                ");";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    //Constructor
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,DB_NAME,factory,DB_VERSION);
    }

    //Adding data
    public void addUser(User u){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,u.getName());
        values.put(COLUMN_DESCRIPTION, u.getDescription());
        values.put(COLUMN_FOLLOWED, u.getFollowed());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USER, null, values);
        db.close();;
    }

    //Get all user records
    public ArrayList<User> getUsers(){
        String query = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        ArrayList<User> userList = new ArrayList<>();

        while(cursor.moveToNext()){
            userList.add(new User(
                    cursor.getString(1),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(0)),
                    Boolean.parseBoolean(cursor.getString(3))
            ));
        }

        return userList;
    }

    public void updateUser(User user){
        String query = "SELECT " + COLUMN_FOLLOWED +" FROM " + TABLE_USER
                + " WHERE " + COLUMN_ID + " = " + Integer.toString(user.getId());
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, user.followed);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            db.update(TABLE_USER,values, Integer.toString(user.getId()),null);
        }

    }

}
