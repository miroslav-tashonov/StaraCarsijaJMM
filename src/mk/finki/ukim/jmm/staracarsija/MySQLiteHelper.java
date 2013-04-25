package mk.finki.ukim.jmm.staracarsija;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_COMMENTS = "objects";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_IME = "ime";
  public static final String COLUMN_TIP = "tip";
  public static final String COLUMN_RATING = "rating";
  public static final String COLUMN_COORDS = "coords";

  private static final String DATABASE_NAME = "scDB3.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_COMMENTS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_IME
      + " text not null, " + COLUMN_TIP
      + " integer not null , " + COLUMN_RATING
      + " real not null , " + COLUMN_COORDS
      + " text not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
    onCreate(db);
  }

} 