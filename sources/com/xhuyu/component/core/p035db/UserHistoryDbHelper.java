package com.xhuyu.component.core.p035db;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.xhuyu.component.core.db.UserHistoryDbHelper */
public class UserHistoryDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "user_history";
    private static final String TABLE_NAME_PHONE = "phone_history";
    private static final int VERSION = 1;
    private Context mContext;
    private final DBExecutor mPhoneExecutor;
    private final DBExecutor mUserExecutor;

    public UserHistoryDbHelper(Context context, String dbName) {
        super(context, dbName, (SQLiteDatabase.CursorFactory) null, 1);
        this.mContext = context;
        if (context instanceof Activity) {
            new RuntimeException("不要用Activity初始化UserHistoryDbHelper").printStackTrace();
        }
        this.mUserExecutor = new DBExecutor(this.mContext, this, TABLE_NAME);
        this.mPhoneExecutor = new DBExecutor(this.mContext, this, TABLE_NAME_PHONE);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(generateUserTableSql(TABLE_NAME));
        db.execSQL(generateUserTableSql(TABLE_NAME_PHONE));
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public DBExecutor getUserTable() {
        return this.mUserExecutor;
    }

    public DBExecutor getPhoneTable() {
        return this.mPhoneExecutor;
    }

    private String generateUserTableSql(String tableName) {
        return "create table if not exists " + tableName + " (" + "id" + " integer primary key," + "user_id" + " integer, " + DBTableRowItem.FIELD_USER_NAME + " text, " + "password" + " text, " + DBTableRowItem.FIELD_LAST_TIME + " integer)";
    }

    public void deleteDatabase() {
        try {
            this.mContext.deleteDatabase(getDatabaseName());
        } catch (Exception e) {
        }
    }

    public void clearOutDataUserInfo() {
        this.mUserExecutor.clearOutDataUserInfo();
        this.mPhoneExecutor.clearOutDataUserInfo();
    }
}
