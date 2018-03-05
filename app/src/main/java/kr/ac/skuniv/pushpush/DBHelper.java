package kr.ac.skuniv.pushpush;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    /*********************************************************************************************/
    // DBHelper 생성자
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /*********************************************************************************************/
    // onCreate()
    @Override
    public void onCreate(SQLiteDatabase db) {
        /* 새로운 테이블 생성
           이름은 RANKING이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
           nickname 문자열 컬럼, moves 정수형 컬럼, time 문자열 컬럼, grade 문자열 컬럼으로 구성된 테이블을 생성.
        */
        db.execSQL("CREATE TABLE RANKING (_id INTEGER PRIMARY KEY AUTOINCREMENT, nickname TEXT, moves INTEGER, time INTEGER, grade TEXT);");
        db.execSQL("CREATE TABLE RANKING2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, nickname TEXT, moves INTEGER, time INTEGER, grade TEXT);");
        db.execSQL("CREATE TABLE RANKING3 (_id INTEGER PRIMARY KEY AUTOINCREMENT, nickname TEXT, moves INTEGER, time INTEGER, grade TEXT);");
    }
    /*********************************************************************************************/
    // onUpgrade()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /*********************************************************************************************/
    // 테이블에 값을 저장하는 메소드
    public void insert(int stage_num, String nickname, int moves, int time, char grade) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        if(stage_num == 0)
            db.execSQL("INSERT INTO RANKING VALUES(null, '" + nickname + "', " + moves + ", '" + time + "', '" + grade + "');");
        if(stage_num == 1)
            db.execSQL("INSERT INTO RANKING2 VALUES(null, '" + nickname + "', " + moves + ", '" + time + "', '" + grade + "');");
        if(stage_num == 2)
            db.execSQL("INSERT INTO RANKING3 VALUES(null, '" + nickname + "', " + moves + ", '" + time + "', '" + grade + "');");
        db.close();
    }
    /*********************************************************************************************/
    // DB를 초기화하는 메소드
    public void deleteAll(int num) {
        SQLiteDatabase db = getWritableDatabase();
        if(num == 0)
            db.execSQL("DELETE FROM RANKING");
        if(num == 1)
            db.execSQL("DELETE FROM RANKING2");
        if(num == 2)
            db.execSQL("DELETE FROM RANKING3");
        db.close();
    }
    /*********************************************************************************************/
    // 일치하는 grade db를 삭제하는 메소드
    public void delete(char grade) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM RANKING WHERE grade = '" + grade + "';");
        db.execSQL("DELETE FROM RANKING2 WHERE grade = '" + grade + "';");
        db.execSQL("DELETE FROM RANKING3 WHERE grade = '" + grade + "';");
        db.close();
    }
    /*********************************************************************************************/
    // DB에 저장된 nickname을 반환하는 메소드
    public String getNickname(int stage_num, int select_num) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        if(stage_num == 0) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for (int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(1);
            }
        }
        if(stage_num == 1) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING2 ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for (int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(1);
            }
        }
        if(stage_num == 2) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING3 ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for (int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(1);
            }
        }
        return result;
    }
    /*********************************************************************************************/
    // DB에 저장된 moves를 반환하는 메소드
    public String getMoves(int stage_num, int select_num) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        if(stage_num == 0) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for (int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(2);
            }
        }
        if(stage_num == 1) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING2 ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for (int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(2);
            }
        }
        if(stage_num == 2) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING3 ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for (int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(2);
            }
        }
        return result;
    }
    /*********************************************************************************************/
    // DB에 저장된 time을 반환하는 메소드
    public String getTime(int stage_num, int select_num) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        if(stage_num == 0) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for(int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1) {
                    if (cursor.getInt(3) < 10)
                        result = ("00:0" + cursor.getInt(3));
                    else if (cursor.getInt(3) >= 10 && cursor.getInt(3) < 60)
                        result = ("00:" + cursor.getInt(3));
                    else if (cursor.getInt(3) == 60)
                        result = ("01:00");
                    else if (cursor.getInt(3) > 60 && cursor.getInt(3) < 70)
                        result = ("01:0" + cursor.getInt(3) % 60);
                    else if (cursor.getInt(3) >= 70 && cursor.getInt(3) < 120)
                        result = ("01:" + cursor.getInt(3) % 60);
                    else if (cursor.getInt(3) == 120)
                        result = ("02:00");
                }
            }
        }
        if(stage_num == 1) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING2 ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for(int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1) {
                    if (cursor.getInt(3) < 10)
                        result = ("00:0" + cursor.getInt(3));
                    else if (cursor.getInt(3) >= 10 && cursor.getInt(3) < 60)
                        result = ("00:" + cursor.getInt(3));
                    else if (cursor.getInt(3) == 60)
                        result = ("01:00");
                    else if (cursor.getInt(3) > 60 && cursor.getInt(3) < 70)
                        result = ("01:0" + cursor.getInt(3) % 60);
                    else if (cursor.getInt(3) >= 70 && cursor.getInt(3) < 120)
                        result = ("01:" + cursor.getInt(3) % 60);
                    else if (cursor.getInt(3) == 120)
                        result = ("02:00");
                }
            }
        }
        if(stage_num == 2) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING3 ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for(int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1) {
                    if (cursor.getInt(3) < 10)
                        result = ("00:0" + cursor.getInt(3));
                    else if (cursor.getInt(3) >= 10 && cursor.getInt(3) < 60)
                        result = ("00:" + cursor.getInt(3));
                    else if (cursor.getInt(3) == 60)
                        result = ("01:00");
                    else if (cursor.getInt(3) > 60 && cursor.getInt(3) < 70)
                        result = ("01:0" + cursor.getInt(3) % 60);
                    else if (cursor.getInt(3) >= 70 && cursor.getInt(3) < 120)
                        result = ("01:" + cursor.getInt(3) % 60);
                    else if (cursor.getInt(3) == 120)
                        result = ("02:00");
                }
            }
        }
        return result;
    }
    /*********************************************************************************************/
    // DB에 저장된 grade를 반환하는 메소드
    public String getGrade(int stage_num, int select_num) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        if(stage_num == 0) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for(int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(4);
            }
        }
        if(stage_num == 1) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING2 ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for(int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(4);
            }
        }
        if(stage_num == 2) {
            Cursor cursor = db.rawQuery("SELECT * FROM RANKING3 ORDER BY moves ASC, time ASC, grade ASC limit 9", null);
            for(int i = 0; i < select_num; i++) {
                cursor.moveToNext();
                if(i == select_num - 1)
                    result = cursor.getString(4);
            }
        }
        return result;
    }
}
