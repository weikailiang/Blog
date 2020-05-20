package com.base.common.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.base.common.BaseApp;

import java.util.ArrayList;

/**
 * 工程数据库助手类, 我們在自己封裝一些東西的時候可以以Manager類的型式，也可以以Helper的形式
 * Created by weikailiang on 2020/5/9.
 */

public final class DBHelper extends SQLiteOpenHelper{


    private static DBHelper helper = null;
    private static final String DATABASE_NAME = "blog.db";
    private static final int DATABASE_VERSION = 1;


    /**
     * 字段公共类型
     */
    public static final String INTEGER_TYPE = " integer";
    public static final String TEXT_TYPE = " TEXT";
    public static final String DESC = " DESC";
    /**
     * 表公共字段
     */
    public static final String ID = "_id";
    public static final String TIME = "time";




    private DBHelper() {
        super(BaseApp.sAppContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHelper getInstance() {
        if (helper == null) {
            /**
             * 这里用全局Application即可
             */
            helper = new DBHelper();
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();
        try {
            createAllTables(sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //根据老的数据库版本名来升级数据库,增量升级
        switch (oldVersion) {
            case 1:
                //此处不应该有break; 保证版本跳跃时也可以全部更新，例如:由oldVersion为1直接到version 5
            case 2:
                //老版本为2时,说明一定执行过了老版本为1时的更新,再执行以后所有版本的更新即可,不需要使用网上的那种循环的方式。
            case 3:

            default:
                break;
        }
    }








    private void createAllTables(SQLiteDatabase db) {
        /**
         * 创建用户表
         */
        String[] cloumns = new String[]{DbField.token +
                TEXT_TYPE, DbField.deviceType + INTEGER_TYPE,
                DbField.userId + TEXT_TYPE,DbField.userName + TEXT_TYPE,DbField.accountId+TEXT_TYPE,
                DbField.realName + TEXT_TYPE,DbField.phone+TEXT_TYPE,DbField.address+TEXT_TYPE};
        createTable(db, DbField.USER_TABLE_NAME, cloumns);
    }
    /**
     * @param sqliteDatabase
     * @param table          要创建的数据表名
     * @param columns        列名
     */
    private void createTable(SQLiteDatabase sqliteDatabase, String table, String[] columns) {
        String createTable = "create table if not exists ";
        String primaryKey = " Integer primary key autoincrement";
        String text = " item_ad_above_catelist_layout";
        char leftBracket = '(';
        char rightBracket = ')';
        char comma = ',';
        int stringBufferSize = 170;
        StringBuffer sql = new StringBuffer(stringBufferSize); // StringBuffer的效率会更高一些
        sql.append(createTable).append(table).append(leftBracket).append(ID).append(primaryKey).append(comma);
        for (String column : columns) {
            sql.append(column);
            sql.append(comma);
        }
        sql.append(TIME).append(text).append(rightBracket);
        try {
            sqliteDatabase.execSQL(sql.toString());
        } catch (Exception e) {
            e.getMessage();
        }
    }



    /**
     * 关闭数据库
     */
    public void closeDatabase(SQLiteDatabase db) {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    /**
     * 插入数据
     *
     * @param table
     * @param nullColumnHack
     * @param values
     * @return
     */
    public synchronized long insert(final String table, final String nullColumnHack, final ContentValues values) {
        SQLiteDatabase database = null;
        try {
            database = getWritableDatabase();
            return database.insert(table, nullColumnHack, values);
        } catch (Exception e) {
            return -1;
        } finally {
            closeDatabase(database);
        }
    }

    /**
     * 批量插入数据，显示使用事物,事物的效率在批量插入时非常的明显
     */
    public synchronized boolean insert(final String table, final String nullColumnHack,
                                       final ArrayList<ContentValues> values) {
        boolean result = true;
        SQLiteDatabase database = null;
        try {
            database = getWritableDatabase();
            database.beginTransaction();
            for (ContentValues value : values) {
                if (database.insert(table, nullColumnHack, value) < 0) {
                    result = false;
                    break;
                }
            }
            if (result) {
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            return false;
        } finally {
            database.endTransaction();
            closeDatabase(database);
        }
        return result;
    }

    /**
     * 删除数据
     *
     * @param table
     * @param whereClause //删除条件
     * @param whereArgs   //删除条件值
     * @return
     */
    public int delete(final String table, final String whereClause, final String[] whereArgs) {
        SQLiteDatabase database = null;
        try {
            database = getWritableDatabase();
            return database.delete(table, whereClause, whereArgs);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeDatabase(database);
        }
    }

    /**
     * 更新数据
     *
     * @param table
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public int update(final String table, final ContentValues values, final String whereClause,
                      final String[] whereArgs) {
        SQLiteDatabase database = null;
        try {
            database = getWritableDatabase();
            return database.update(table, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeDatabase(database);
        }
    }

}
