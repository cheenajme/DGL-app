package tw.dgl.com;

/**
 * Created by 3et on 2016/8/2.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */

    public HashMap<String, String> Search(String KeyWord) {

        HashMap result = new HashMap(); //  key=索引 val=內容
        Cursor cursor = database.rawQuery("SELECT Column1, Column2, Column3, Column4 FROM 'DGL-37' WHERE ( Column2 like '%" + KeyWord + "%' or  Column3 like '%" + KeyWord + "%' or Column4 like '%" + KeyWord + "%' )", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            result.put(cursor.getString(0), cursor.getString(1) + " " + cursor.getString(2) +" "+cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        return result;

    }
    public HashMap<String, String> Search2(String KeyWord) {

        HashMap result = new HashMap(); //  key=索引 val=內容
        Cursor cursor = database.rawQuery("SELECT Column1, Column2, Column3, Column4 FROM 'Policy'  WHERE  ( Column2 like '%" + KeyWord + "%' or  Column3 like '%" + KeyWord + "%' or Column4      like '%" + KeyWord + "%' )", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result.put(cursor.getString(0),cursor.getString(1) + " " + cursor.getString(2) +" "+cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        return result;

    }
    public HashMap<String, String> Search3(String KeyWord) {

        HashMap result = new HashMap(); //  key=索引 val=內容
        Cursor cursor = database.rawQuery("SELECT* FROM 'Segregation'  WHERE Column1='" + KeyWord + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result.put(cursor.getString(0),cursor.getString(1) + " " + cursor.getString(2) +" "+cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        return result;

    }




    //利用索引編號搜尋回傳陣列
    public String[] idGetAll(String id) {

        final int MAX_ROW_NUM = 31;
        String[] result = new String[MAX_ROW_NUM];

        Cursor cursor = database.rawQuery("SELECT * FROM 'DGL-37' WHERE Column1='" + id + "'", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            for (int i = 0; i < MAX_ROW_NUM; i++) {
                result[i] = cursor.getString(i+1);
            }
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
    public String [] Policyid(String policyid){
        final int MAX_ROW_NUM = 40;
        String[] result = new String[MAX_ROW_NUM];

        Cursor cursor = database.rawQuery("SELECT * FROM 'Policy' WHERE Column1='" + policyid + "'" , null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            for (int i = 0; i < MAX_ROW_NUM; i++) {
                result[i] = cursor.getString(i+1);
            }
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
    public String [] Segregation(String segregation){
        final int MAX_ROW_NUM = 15;
        String[] result = new String[MAX_ROW_NUM];

        Cursor cursor = database.rawQuery("SELECT * FROM 'Segregation' WHERE Column1='" + segregation + "'" , null);
        //Cursor cursor = database.rawQuery("SELECT * FROM 'Segregation' WHERE Column1='" + segregation + "' and " , null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            for (int i = 0; i < MAX_ROW_NUM; i++) {
                result[i] = cursor.getString(i+1);
            }
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
    public String[] getSP(String sp, String type) {

        Cursor cursor = database.rawQuery("SELECT * FROM 'additional' WHERE SPECIALPRO in( " + sp + " )", null);
        int x=cursor.getCount();
        String[] key = new String[x];
        String[] value = new String[x];
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            key[i] = cursor.getString(0);
            value[i] = cursor.getString(1);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        if (type.equals("key")) {
            return key;
        } else {

            return value;
        }

    }

    public String[] getibcp(String ibcp, String type) {


        Cursor cursor = database.rawQuery("SELECT * FROM IBC_P WHERE Column1 in( '" + ibcp + "')", null);
        int x=cursor.getCount();
        String[] key = new String[x];
        String[] value = new String[x];
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            key[i] = cursor.getString(0);
            value[i] = cursor.getString(1);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        if (type.equals("key")) {
            return key;
        } else {

            return value;
        }
    }

    public String[] getse(String se, String type) {



        Cursor cursor = database.rawQuery("SELECT * FROM SE WHERE Column1 in( '" + se + "')", null);
        int x=cursor.getCount();
        String[] key = new String[x];
        String[] value = new String[x];
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            key[i] = cursor.getString(0);
            value[i] = cursor.getString(1);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        if (type.equals("key")) {
            return key;
        } else {

            return value;
        }
    }

    public String[] getpp(String pp, String type) {



        Cursor cursor = database.rawQuery("SELECT * FROM pp WHERE Column1 in( '" + pp + "')", null);
        int x=cursor.getCount();
        String[] key = new String[x];
        String[] value = new String[x];
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            key[i] = cursor.getString(0);
            value[i] = cursor.getString(1);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        if (type.equals("key")) {
            return key;
        } else {

            return value;
        }
    }

    public String[] getTP(String TP, String type) {



        Cursor cursor = database.rawQuery("SELECT * FROM TP WHERE Column1 in( '" + TP + "')", null);
        int x=cursor.getCount();
        String[] key = new String[x];
        String[] value = new String[x];
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            key[i] = cursor.getString(0);
            value[i] = cursor.getString(1);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        if (type.equals("key")) {
            return key;
        } else {

            return value;
        }
    }

    public String[] getstc(String stc, String type) {


        Cursor cursor = database.rawQuery("SELECT * FROM ST_CODE WHERE Column1 in( '" + stc + "')", null);
        int x=cursor.getCount();
        String[] key = new String[x];
        String[] value = new String[x];
        cursor.moveToFirst();

        int i = 0;
        while (!cursor.isAfterLast()) {
            key[i] = cursor.getString(0);
            value[i] = cursor.getString(1);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        if (type.equals("key")) {
            return key;
        } else {

            return value;
        }
    }

   public String[] getsc(String sc, String type) {


        Cursor cursor = database.rawQuery("SELECT * FROM SC WHERE Column1 in('Stowagecategory " + sc + "')", null);
        int x=cursor.getCount();
        String[] key = new String[x];
        String[] value = new String[x];
        String[] value2 = new String[x];

//        Stowage category 01
        Log.d("測試", "SELECT * FROM SC WHERE Column1='Stowagecategory " + sc + "'");
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            key[i] = cursor.getString(0);
            value[i] = cursor.getString(1);
            value2[i] = cursor.getString(2);

            i++;
            cursor.moveToNext();
        }
        cursor.close();
        if (type.equals("key")) {
            return key;
        } else if (type.equals("value")) {

            return value;
        } else {
            return value2;
        }
    }





    /*public String[] getEQ(String EQ, String type) {


        Cursor cursor = database.rawQuery("SELECT * FROM EQ WHERE Code in('" + EQ + "')", null);
        int x=cursor.getCount();
        String[] key = new String[x];
        String[] value = new String[x];
        String[] value2 = new String[x];
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            key[i] = cursor.getString(0);
            value[i] = cursor.getString(1);
            value2[i] = cursor.getString(2);

            i++;
            cursor.moveToNext();
        }
        cursor.close();
        if (type.equals("key")) {
            return key;
        } else if (type.equals("value")) {

            return value;
        } else {
            return value2;
        }
    }
*/
    /*public String IBCI(String IBCI) {

        StringBuilder sb = new StringBuilder();

        String createTable="CREATE TABLE IF NOT EXISTS " +
                IBCI +
                "(name VARCHAR(32), " +
                "phone VARCHAR(16), " +
                "email VARCHAR(64))";
        database.execSQL(createTable);

        Cursor cursor = database.rawQuery("SELECT * FROM "+IBCI+"", null);

        if (cursor==null)
        {String ruby = "空的";}
        else if (cursor!=null){
            cursor.moveToFirst();
            int num=cursor.getColumnCount();

            while (!cursor.isAfterLast()) {
                for (int i=0;i<num;i++)
                    sb.append(cursor.getString(i)+"\n\n");

                cursor.moveToNext();
            }
        }

        sb.append("\n\n\n");
        cursor.close();

        return sb.toString();

    }
*/


}