package com.thinkfly.plugins.coludladder.p034dk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.vending.expansion.zipfile.APEZProvider;
import com.eagle.mixsdk.sdk.verify.EagleInitConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.okgo.model.Progress;
import com.thinkfly.cloudlader.data.DBData;
import com.thinkfly.plugins.coludladder.config.Config;
import com.thinkfly.plugins.coludladder.log.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* renamed from: com.thinkfly.plugins.coludladder.dk.DatabaseHelper */
public class DatabaseHelper {
    /* access modifiers changed from: private */
    public String DB_NAME = "";
    /* access modifiers changed from: private */
    public String TABLE_TRACK = "";
    final String TAG = "DB";
    final int VERSION = 1;
    private List<DBData> lstQueryData = null;
    private Context mContext;
    private DBHelper mDBHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private AtomicInteger openCounter;

    /* renamed from: com.thinkfly.plugins.coludladder.dk.DatabaseHelper$QueryData */
    public class QueryData {
        public int _id = -1;
        public JSONObject json;
        public int priority;
        public String timestamp;

        public QueryData() {
        }

        public QueryData(JSONObject jSONObject, int i, int i2, String str) {
            this.json = jSONObject;
            this.priority = i;
            this.timestamp = str;
            this._id = i2;
        }
    }

    public DatabaseHelper() {
    }

    public DatabaseHelper(Context context, String daName, String tableName) {
        this.DB_NAME = daName;
        this.TABLE_TRACK = tableName;
        Log.m662d(Config.TAG, "DB_NAME : " + this.DB_NAME + "/TABLE_TRACK : " + this.TABLE_TRACK);
        this.mContext = context;
        this.mDBHelper = new DBHelper(context);
        this.openCounter = new AtomicInteger();
        this.lstQueryData = new ArrayList();
    }

    public synchronized void open() {
        if (this.mContext == null) {
            Log.m665w("DB", "mContext is Null when open database");
        } else if (this.mSQLiteDatabase != null && this.mSQLiteDatabase.isOpen()) {
            Log.m665w("DB", "Database was opened!");
        } else if (this.openCounter.incrementAndGet() == 1 || this.mSQLiteDatabase == null) {
            this.mSQLiteDatabase = this.mDBHelper.getWritableDatabase();
        } else {
            Log.m665w("DB", "Database was already opened!");
        }
    }

    public synchronized int getCount() {
        int count = 0;
        synchronized (this) {
            if (this.mContext == null) {
                Log.m665w("DB", "mContext is Null when query data from database");
            } else if (this.mSQLiteDatabase == null || !this.mSQLiteDatabase.isOpen()) {
                Log.m665w("DB", "Did you call method 'open' before you call query method?");
            } else if (this.mSQLiteDatabase.isReadOnly()) {
                Log.m665w("DB", "Your memory is not enough!");
            } else {
                Cursor mCursor = this.mSQLiteDatabase.rawQuery("select count(*) from " + this.TABLE_TRACK, (String[]) null);
                if (mCursor != null) {
                    count = 0;
                    while (mCursor.moveToNext()) {
                        count = mCursor.getInt(mCursor.getColumnIndex("count(*)"));
                        Log.m665w("DB", "count " + count);
                    }
                    mCursor.close();
                }
            }
        }
        return count;
    }

    public synchronized long insert(String table, ContentValues values) {
        long j = -1;
        synchronized (this) {
            if (this.mContext == null) {
                Log.m665w("DB", "mContext is Null when insert data to database");
            } else if (this.mSQLiteDatabase == null || !this.mSQLiteDatabase.isOpen()) {
                Log.m665w("DB", "Did you call method 'open' before you call insert method?");
            } else if (this.mSQLiteDatabase.isReadOnly()) {
                Log.m665w("DB", "Your memory is not enough!");
            } else {
                j = this.mSQLiteDatabase.insert(table, (String) null, values);
            }
        }
        return j;
    }

    public List<DBData> queryDataFromTrackWithLimit(int count) {
        return queryDataWithLimit(this.TABLE_TRACK, count);
    }

    public synchronized List<DBData> queryDataWithLimit(String table, int count) {
        List<DBData> list = null;
        synchronized (this) {
            if (this.mContext == null) {
                Log.m665w("DB", "mContext is Null when delete data from database");
            } else if (this.mSQLiteDatabase == null || !this.mSQLiteDatabase.isOpen()) {
                Log.m665w("DB", "Did you call method 'open' before you call queryDataWithLimit method?");
            } else if (this.mSQLiteDatabase.isReadOnly()) {
                Log.m665w("DB", "Your memory is not enough!");
            } else {
                Cursor mCursor = this.mSQLiteDatabase.rawQuery("select * from " + this.TABLE_TRACK + " order by _id limit 0," + count, (String[]) null);
                if (mCursor != null) {
                    if (this.lstQueryData != null) {
                        this.lstQueryData.clear();
                    }
                    while (mCursor.moveToNext()) {
                        byte[] content = mCursor.getBlob(mCursor.getColumnIndex(FirebaseAnalytics.Param.CONTENT));
                        int priority = mCursor.getInt(mCursor.getColumnIndex(Progress.PRIORITY));
                        String timestamp = mCursor.getString(mCursor.getColumnIndex("timestamp"));
                        this.lstQueryData.add(new DBData(byteArrayToJsonObj(content), priority, mCursor.getInt(mCursor.getColumnIndex(APEZProvider.FILEID)), timestamp));
                    }
                    mCursor.close();
                    list = this.lstQueryData;
                }
            }
        }
        return list;
    }

    public long insertToTrack(ContentValues values) {
        return insert(this.TABLE_TRACK, values);
    }

    public void deleteFromTrackById(long id) {
        delete(this.TABLE_TRACK, "_id=?", new String[]{String.valueOf(id)});
    }

    public void deleteMuchDataFromTrackById(long id) {
        delete(this.TABLE_TRACK, "_id<=?", new String[]{String.valueOf(id)});
    }

    public void deleteMuchDataFromTrackById(long startId, long endId) {
        if (startId > endId) {
            Log.m662d(Log.TAG, "startId > endId" + startId + EagleInitConfig.MSG_MARK + endId);
            long startId2 = startId ^ endId;
            endId ^= startId2;
            startId = startId2 ^ endId;
        }
        delete(this.TABLE_TRACK, "_id>=? and _id<=?", new String[]{String.valueOf(startId), String.valueOf(endId)});
    }

    public synchronized void delete(String table, String whereClause, String[] whereArgs) {
        if (this.mContext == null) {
            Log.m665w("DB", "mContext is Null when delete data from database");
        } else if (this.mSQLiteDatabase == null || !this.mSQLiteDatabase.isOpen()) {
            Log.m665w("DB", "Did you call method 'open' before you call delete method?");
        } else if (this.mSQLiteDatabase.isReadOnly()) {
            Log.m665w("DB", "Your memory is not enough!");
        } else {
            this.mSQLiteDatabase.delete(table, whereClause, whereArgs);
        }
    }

    public synchronized void close() {
        if (this.mSQLiteDatabase == null || !this.mSQLiteDatabase.isOpen()) {
            Log.m665w("DB", "Database was closed!");
        } else {
            if (this.lstQueryData != null) {
                this.lstQueryData.clear();
                this.lstQueryData = null;
            }
            if (this.openCounter.decrementAndGet() == 0) {
                this.mSQLiteDatabase.close();
            }
        }
    }

    private JSONObject byteArrayToJsonObj(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)), 8192);
        StringBuffer json_buffer = new StringBuffer();
        while (true) {
            try {
                String line = in.readLine();
                if (line == null) {
                    return new JSONObject(json_buffer.toString());
                }
                json_buffer.append(line);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* renamed from: com.thinkfly.plugins.coludladder.dk.DatabaseHelper$DBHelper */
    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DatabaseHelper.this.DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + DatabaseHelper.this.TABLE_TRACK + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, content BLOB, priority INTEGER, timestamp text);");
        }

        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }
}
