package com.xhuyu.component.core.p035db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.xhuyu.component.mvp.model.HuYuUser;
import com.xhuyu.component.utils.AppUtil;
import com.xhuyu.component.utils.DESCUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xhuyu.component.core.db.DBExecutor */
public class DBExecutor {
    private Context mContext;
    private SQLiteOpenHelper mDbHelper;
    private String mTableName;

    public DBExecutor(Context context, SQLiteOpenHelper helper, String tableName) {
        this.mContext = context;
        this.mDbHelper = helper;
        this.mTableName = tableName;
    }

    private ContentValues createValues(HuYuUser user) {
        ContentValues values = new ContentValues();
        values.put("user_id", Long.valueOf(user.getUid()));
        values.put(DBTableRowItem.FIELD_LAST_TIME, user.getTime());
        values.put(DBTableRowItem.FIELD_USER_NAME, user.getUsername());
        values.put("password", DESCUtil.encryptByKey(user.getRSAPassword(), AppUtil.get16UniquId(this.mContext)));
        return values;
    }

    private HuYuUser createUserFromCursor(Cursor cursor) {
        String password;
        HuYuUser user = new HuYuUser();
        user.setUsername(cursor.getString(cursor.getColumnIndex(DBTableRowItem.FIELD_USER_NAME)));
        user.setUid(cursor.getLong(cursor.getColumnIndex("user_id")));
        try {
            password = DESCUtil.decrypt(cursor.getString(cursor.getColumnIndex("password")), AppUtil.get16UniquId(this.mContext));
        } catch (Exception e) {
            password = "";
        }
        user.setPassword(password);
        user.setTime(cursor.getString(cursor.getColumnIndex(DBTableRowItem.FIELD_LAST_TIME)));
        return user;
    }

    public boolean hasUser(String userName) {
        boolean result = true;
        Cursor cursor = this.mDbHelper.getWritableDatabase().query(this.mTableName, new String[]{DBTableRowItem.FIELD_USER_NAME}, "user_name = ?", new String[]{userName}, (String) null, (String) null, (String) null);
        if (cursor.getCount() <= 0) {
            result = false;
        }
        cursor.close();
        return result;
    }

    public boolean appendUser(HuYuUser user) {
        return this.mDbHelper.getWritableDatabase().insert(this.mTableName, AppEventsConstants.EVENT_PARAM_VALUE_NO, createValues(user)) != -1;
    }

    public boolean updateUser(HuYuUser user) {
        if (this.mDbHelper.getWritableDatabase().update(this.mTableName, createValues(user), "user_name = ?", new String[]{user.getUsername()}) > 0) {
            return true;
        }
        return false;
    }

    public boolean addUser(HuYuUser user) {
        if (hasUser(user.getUsername())) {
            return updateUser(user);
        }
        return appendUser(user);
    }

    public boolean appendUserList(List<HuYuUser> users) {
        SQLiteDatabase database = this.mDbHelper.getWritableDatabase();
        database.beginTransaction();
        boolean result = true;
        int i = 0;
        while (i < users.size()) {
            try {
                HuYuUser user = users.get(i);
                if (hasUser(user.getUsername())) {
                    result = updateUser(user);
                } else {
                    result = appendUser(user);
                }
                if (!result) {
                    break;
                }
                i++;
            } finally {
                database.endTransaction();
            }
        }
        if (result) {
            database.setTransactionSuccessful();
        }
        return result;
    }

    public boolean deleteUserList(List<String> accounts) {
        SQLiteDatabase database = this.mDbHelper.getWritableDatabase();
        database.beginTransaction();
        boolean result = true;
        int i = 0;
        while (i < accounts.size()) {
            try {
                result = database.delete(this.mTableName, "user_name = ?", new String[]{accounts.get(i)}) > 0;
                if (!result) {
                    break;
                }
                i++;
            } finally {
                database.endTransaction();
            }
        }
        if (result) {
            database.setTransactionSuccessful();
        }
        return result;
    }

    public boolean deleteUser(String userName) {
        if (this.mDbHelper.getWritableDatabase().delete(this.mTableName, "user_name = ?", new String[]{userName}) > 0) {
            return true;
        }
        return false;
    }

    public HuYuUser getUser(String account) {
        if (TextUtils.isEmpty(account)) {
            return null;
        }
        Cursor cursor = this.mDbHelper.getReadableDatabase().query(this.mTableName, (String[]) null, "user_name = ?", new String[]{account}, (String) null, (String) null, (String) null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        HuYuUser user = createUserFromCursor(cursor);
        cursor.close();
        return user;
    }

    public List<HuYuUser> getAllUser() {
        Cursor cursor = this.mDbHelper.getReadableDatabase().query(this.mTableName, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "last_time desc");
        if (cursor.getCount() == 0) {
            return new ArrayList();
        }
        List<HuYuUser> result = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            result.add(createUserFromCursor(cursor));
        }
        cursor.close();
        return result;
    }

    public boolean hasUser() {
        boolean result = false;
        Cursor cursor = this.mDbHelper.getReadableDatabase().rawQuery("select count(1) from ".concat(this.mTableName), (String[]) null);
        if (cursor.moveToFirst()) {
            if (cursor.getInt(0) > 0) {
                result = true;
            }
            cursor.close();
        }
        return result;
    }

    public void clearOutDataUserInfo() {
        deleteOutData(System.currentTimeMillis(), 7776000000L);
        deleteOutData(System.currentTimeMillis(), 2592000000L);
    }

    private boolean deleteOutData(long startTime, long outDataTime) {
        SQLiteDatabase database = this.mDbHelper.getWritableDatabase();
        long last_time = startTime - outDataTime;
        if (last_time > 0) {
            if (database.delete(this.mTableName, "last_time < ?", new String[]{last_time + ""}) > 0) {
                return true;
            }
        }
        return false;
    }
}
