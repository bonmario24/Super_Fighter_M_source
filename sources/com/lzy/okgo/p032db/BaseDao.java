package com.lzy.okgo.p032db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import com.facebook.appevents.AppEventsConstants;
import com.lzy.okgo.utils.OkLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/* renamed from: com.lzy.okgo.db.BaseDao */
public abstract class BaseDao<T> {
    protected static String TAG;
    protected SQLiteDatabase database;
    protected SQLiteOpenHelper helper;
    protected Lock lock = DBHelper.lock;

    /* renamed from: com.lzy.okgo.db.BaseDao$Action */
    public interface Action {
        void call(SQLiteDatabase sQLiteDatabase);
    }

    public abstract ContentValues getContentValues(T t);

    public abstract String getTableName();

    public abstract T parseCursorToBean(Cursor cursor);

    public abstract void unInit();

    public BaseDao(SQLiteOpenHelper helper2) {
        TAG = getClass().getSimpleName();
        this.helper = helper2;
        this.database = openWriter();
    }

    public SQLiteDatabase openReader() {
        return this.helper.getReadableDatabase();
    }

    public SQLiteDatabase openWriter() {
        return this.helper.getWritableDatabase();
    }

    /* access modifiers changed from: protected */
    public final void closeDatabase(SQLiteDatabase database2, Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (database2 != null && database2.isOpen()) {
            database2.close();
        }
    }

    public boolean insert(T t) {
        String str;
        if (t == null) {
            return false;
        }
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            this.database.insert(getTableName(), (String) null, getContentValues(t));
            this.database.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        } finally {
            this.database.endTransaction();
            this.lock.unlock();
            str = " insertT";
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + str);
        }
    }

    public long insert(SQLiteDatabase database2, T t) {
        return database2.insert(getTableName(), (String) null, getContentValues(t));
    }

    public boolean insert(List<T> ts) {
        String str;
        if (ts == null) {
            return false;
        }
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            for (T t : ts) {
                this.database.insert(getTableName(), (String) null, getContentValues(t));
            }
            this.database.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        } finally {
            this.database.endTransaction();
            this.lock.unlock();
            str = " insertList";
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + str);
        }
    }

    public boolean insert(SQLiteDatabase database2, List<T> ts) {
        try {
            for (T t : ts) {
                database2.insert(getTableName(), (String) null, getContentValues(t));
            }
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        }
    }

    public boolean deleteAll() {
        return delete((String) null, (String[]) null);
    }

    public long deleteAll(SQLiteDatabase database2) {
        return delete(database2, (String) null, (String[]) null);
    }

    /* JADX INFO: finally extract failed */
    public boolean delete(String whereClause, String[] whereArgs) {
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            this.database.delete(getTableName(), whereClause, whereArgs);
            this.database.setTransactionSuccessful();
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " delete");
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " delete");
            return false;
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " delete");
            throw th;
        }
    }

    public long delete(SQLiteDatabase database2, String whereClause, String[] whereArgs) {
        return (long) database2.delete(getTableName(), whereClause, whereArgs);
    }

    /* JADX INFO: finally extract failed */
    public boolean deleteList(List<Pair<String, String[]>> where) {
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            for (Pair<String, String[]> pair : where) {
                this.database.delete(getTableName(), (String) pair.first, (String[]) pair.second);
            }
            this.database.setTransactionSuccessful();
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " deleteList");
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " deleteList");
            return false;
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " deleteList");
            throw th;
        }
    }

    public boolean replace(T t) {
        String str;
        if (t == null) {
            return false;
        }
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            this.database.replace(getTableName(), (String) null, getContentValues(t));
            this.database.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        } finally {
            this.database.endTransaction();
            this.lock.unlock();
            str = " replaceT";
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + str);
        }
    }

    public long replace(SQLiteDatabase database2, T t) {
        return database2.replace(getTableName(), (String) null, getContentValues(t));
    }

    /* JADX INFO: finally extract failed */
    public boolean replace(ContentValues contentValues) {
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            this.database.replace(getTableName(), (String) null, contentValues);
            this.database.setTransactionSuccessful();
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " replaceContentValues");
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " replaceContentValues");
            return false;
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " replaceContentValues");
            throw th;
        }
    }

    public long replace(SQLiteDatabase database2, ContentValues contentValues) {
        return database2.replace(getTableName(), (String) null, contentValues);
    }

    public boolean replace(List<T> ts) {
        String str;
        if (ts == null) {
            return false;
        }
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            for (T t : ts) {
                this.database.replace(getTableName(), (String) null, getContentValues(t));
            }
            this.database.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        } finally {
            this.database.endTransaction();
            this.lock.unlock();
            str = " replaceList";
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + str);
        }
    }

    public boolean replace(SQLiteDatabase database2, List<T> ts) {
        try {
            for (T t : ts) {
                database2.replace(getTableName(), (String) null, getContentValues(t));
            }
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        }
    }

    public boolean update(T t, String whereClause, String[] whereArgs) {
        String str;
        if (t == null) {
            return false;
        }
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            this.database.update(getTableName(), getContentValues(t), whereClause, whereArgs);
            this.database.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return false;
        } finally {
            this.database.endTransaction();
            this.lock.unlock();
            str = " updateT";
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + str);
        }
    }

    public long update(SQLiteDatabase database2, T t, String whereClause, String[] whereArgs) {
        return (long) database2.update(getTableName(), getContentValues(t), whereClause, whereArgs);
    }

    /* JADX INFO: finally extract failed */
    public boolean update(ContentValues contentValues, String whereClause, String[] whereArgs) {
        long start = System.currentTimeMillis();
        this.lock.lock();
        try {
            this.database.beginTransaction();
            this.database.update(getTableName(), contentValues, whereClause, whereArgs);
            this.database.setTransactionSuccessful();
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " updateContentValues");
            return true;
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " updateContentValues");
            return false;
        } catch (Throwable th) {
            this.database.endTransaction();
            this.lock.unlock();
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " updateContentValues");
            throw th;
        }
    }

    public long update(SQLiteDatabase database2, ContentValues contentValues, String whereClause, String[] whereArgs) {
        return (long) database2.update(getTableName(), contentValues, whereClause, whereArgs);
    }

    public List<T> queryAll(SQLiteDatabase database2) {
        return query(database2, (String) null, (String[]) null);
    }

    public List<T> query(SQLiteDatabase database2, String selection, String[] selectionArgs) {
        return query(database2, (String[]) null, selection, selectionArgs, (String) null, (String) null, (String) null, (String) null);
    }

    public T queryOne(SQLiteDatabase database2, String selection, String[] selectionArgs) {
        List<T> query = query(database2, (String[]) null, selection, selectionArgs, (String) null, (String) null, (String) null, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    public List<T> query(SQLiteDatabase database2, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        List<T> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = database2.query(getTableName(), columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            while (!cursor.isClosed() && cursor.moveToNext()) {
                list.add(parseCursorToBean(cursor));
            }
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        } finally {
            closeDatabase((SQLiteDatabase) null, cursor);
        }
        return list;
    }

    public List<T> queryAll() {
        return query((String) null, (String[]) null);
    }

    public List<T> query(String selection, String[] selectionArgs) {
        return query((String[]) null, selection, selectionArgs, (String) null, (String) null, (String) null, (String) null);
    }

    public T queryOne(String selection, String[] selectionArgs) {
        long start = System.currentTimeMillis();
        List<T> query = query((String[]) null, selection, selectionArgs, (String) null, (String) null, (String) null, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + " queryOne");
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    public List<T> query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        String str;
        long start = System.currentTimeMillis();
        this.lock.lock();
        List<T> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            this.database.beginTransaction();
            cursor = this.database.query(getTableName(), columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            while (!cursor.isClosed() && cursor.moveToNext()) {
                list.add(parseCursorToBean(cursor));
            }
            this.database.setTransactionSuccessful();
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        } finally {
            closeDatabase((SQLiteDatabase) null, cursor);
            this.database.endTransaction();
            this.lock.unlock();
            str = " query";
            OkLogger.m641v(TAG, (System.currentTimeMillis() - start) + str);
        }
        return list;
    }

    public void startTransaction(Action action) {
        this.lock.lock();
        try {
            this.database.beginTransaction();
            action.call(this.database);
            this.database.setTransactionSuccessful();
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        } finally {
            this.database.endTransaction();
            this.lock.unlock();
        }
    }
}
