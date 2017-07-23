package game.programming.whileloop.canvas_game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by whileloop on 3/22/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "gh_db_db";
    public static final String TABLE_NAME_TAG = "Name_tag";
    public static final String TABLE_QUESTION= "Question";
    public static final String TABLE_LOG = "log";
    public static final String TABLE_TAG_QUE_ASSIST = "Tag_que_assist";


    //Tag name table
    public static final String COLUMN_TAG_ID = "Tag_ID";
    public static final String COLUMN_TAG_NAME = "Tag_Name";
    public static final String COLUMN_TAG_TYPE = "Tag_Type"; //keyword or variable
    public static final String COLUMN_TAG_DESC = "Tag_DESC";

    //Question table
    public static final String COLUMN_QUESTION_ID = "Question_ID";
    public static final String COLUMN_QUESTION_TOPIC = "Question_Topic";
    public static final String COLUMN_QUESTION_DESC = "Question_Desc";
    public static final String COLUMN_QUESTION_CLASS = "Question_class";
    public static final String COLUMN_ANSWER_SEQUENCE = "Answer_Sequence";
    public static final String COLUMN_START_NODE = "Start_Node";
    public static final String COLUMN_PROMOTION_NODE = "Promotion_Node";
    public static final String COLUMN_PUNISHMENT_NODE = "Punishment_Node";
    public static final String COLUMN_PROMOTION_CLASS = "Promotion_Class";
    public static final String COLUMN_PUNISHMENT_CLASS = "Punishment_Class";

    //Log table
    public static final String COLUMN_LOG_ID = "Log_ID";
    //public static final String COLUMN_QUESTION_ID = "Question_ID";
    public static final String COLUMN_STATUS = "Status";
    public static final String COLUMN_POSITION = "Position";

    //Tag que assist table
    //public static final String COLUMN_QUESTION_ID = "Question_ID";
    // public static final String COLUMN_TAG_ID = "Tag_ID";



    public DatabaseHelper(Context context, int version) {
        super(context, DATABASE_NAME, null,version);
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TAG + " ( Tag_ID INTEGER PRIMARY KEY AUTOINCREMENT, Tag_Name VARCHAR(20) , Tag_Type VARCHAR(20) , Tag_DESC VARCHAR(100) ); ");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION + " ( Question_ID  INTEGER PRIMARY KEY AUTOINCREMENT,Question_topic VARCHAR(30),Question_desc VARCHAR(1000) , Question_class VARCHAR(5) , Answer_Sequence VARCHAR(1000),Start_node INTEGER , Promotion_node INTEGER,Punishment_node INTEGER ,Promotion_class VARCHAR(5), punishment_class VARCHAR(5)); ");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LOG + " ( Log_ID INTEGER PRIMARY KEY AUTOINCREMENT, Question_ID INTEGER , Status VARCHAR(20) , Position VARCHAR(10) ); ");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TAG_QUE_ASSIST + " (Question_ID INTEGER , Tag_ID INTEGER, PRIMARY KEY(Question_ID,Tag_ID)); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG_QUE_ASSIST);
        Log.v("DATABASE","Dropped the database version "+oldVersion);
        onCreate(db);
        Log.v("DATABASE","Created the database version "+newVersion);
    }

    public boolean insert_Tag(String tag_name,String tag_type,String tag_desc ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TAG_NAME, tag_name);
        contentValues.put(COLUMN_TAG_TYPE, tag_type);
        contentValues.put(COLUMN_TAG_DESC, tag_desc);
        long result =  db.insert(TABLE_NAME_TAG,null,contentValues);
        if (result ==-1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean insert_Question(String question_topic,String question_desc,String question_class,String answer_sequence,String start_node,String promotion_node, String punishment_node, String promotion_class, String punishment_class){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUESTION_TOPIC,question_topic);
        contentValues.put(COLUMN_QUESTION_DESC,question_desc);
        contentValues.put(COLUMN_QUESTION_CLASS,question_class);
        contentValues.put(COLUMN_ANSWER_SEQUENCE,answer_sequence);
        contentValues.put(COLUMN_START_NODE,start_node);
        contentValues.put(COLUMN_PROMOTION_NODE,promotion_node);
        contentValues.put(COLUMN_PUNISHMENT_NODE,punishment_node);
        contentValues.put(COLUMN_PROMOTION_CLASS,promotion_class);
        contentValues.put(COLUMN_PUNISHMENT_CLASS,punishment_class);
        long result =  db.insert(TABLE_QUESTION,null,contentValues);
        if (result ==-1){
            return false;
        }
        else{
            return true;
        }

    }
    public boolean insert_Log(String question_id , String status,String position){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUESTION_ID,question_id);
        contentValues.put(COLUMN_STATUS,status);
        contentValues.put(COLUMN_POSITION,position);
        long result =  db.insert(TABLE_LOG,null,contentValues);
        if (result ==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean insert_tag_que_assist(int question_id , int tag_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUESTION_ID,question_id);
        contentValues.put(COLUMN_TAG_ID,tag_id);
        long result =  db.insert(TABLE_TAG_QUE_ASSIST,null,contentValues);
        if (result ==-1){
            return false;
        }
        else{
            return true;
        }
    }



    public Question_object get_data_object(String question_class){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql1="SELECT * FROM Question where Question_class='"+question_class+"';";
        Cursor res1= db.rawQuery(sql1, null);

        //select random question id from question class
        int question_id=-1; //default value
        String answer_sequence=null;
        String question_topic = null;
        String question_desc = null;
        String start_node = null,promotion_node=null,punishment_node=null;
        String promotion_class = null,punishment_class = null;
        if (res1.getCount()==0){
            //Toast.makeText(DatabaseHelper.this, "Nothing to show", Toast.LENGTH_LONG).show();
            //return null;
        }
        while (res1.moveToNext()){
            question_id=Integer.parseInt(res1.getString(0));
            question_topic = res1.getString(1);
            question_desc = res1.getString(2);// 3 is a class name
            answer_sequence=res1.getString(4);
            start_node=res1.getString(5);
            promotion_node=res1.getString(6);
            punishment_node=res1.getString(7);
            promotion_class=res1.getString(8);
            punishment_class=res1.getString(9);

        }

        //then use selected question id for queries..
        String sql2="SELECT Name_tag.* FROM Name_tag LEFT OUTER JOIN Tag_que_assist ON Name_tag.Tag_ID=Tag_que_assist.Tag_ID WHERE Name_tag.Tag_Type='MAIN' AND Tag_que_assist.Question_ID = "+ question_id +" ;";
        Cursor res2= db.rawQuery(sql2,null);
        String[] Keyword_array = null,Keyword_id_array=null;


        if (res2.getCount()!=0) {
            //Toast.makeText(DatabaseHelper.this, "Nothing to show", Toast.LENGTH_LONG).show();
            Keyword_array = new String[res2.getCount()];
            Keyword_id_array = new String[res2.getCount()];
            int i = 0;
            while (res2.moveToNext()) {
                Keyword_id_array[i]=String.valueOf(res2.getInt(0));
                Keyword_array[i] = res2.getString(1); //name of the tag
                i++;
            }
        }
        //create variable array
        String sql3="SELECT Name_tag.* FROM Name_tag LEFT OUTER JOIN Tag_que_assist ON Name_tag.Tag_ID=Tag_que_assist.Tag_ID WHERE Name_tag.Tag_Type='VARIABLE' AND Tag_que_assist.Question_ID = "+ question_id+" ;";
        Cursor res3= db.rawQuery(sql3,null);

        String[] Variable_array = null,variable_id_array=null;

        if (res3.getCount()!=0) {

            Variable_array = new String[res3.getCount()];
            variable_id_array = new String[res3.getCount()];
            int j = 0;
            while (res3.moveToNext()) {
                variable_id_array[j]=String.valueOf(res3.getInt(0));
                Variable_array[j] = res3.getString(1); //name of the tag
                j++;
            }
        }



        //String [] arr1 = {"4","5"};
        //String [] arr2 = {"if","else","is_prime","Yeah","done","x","Y","Z"};
        //Question_object question_object = new Question_object(arr1,arr2,"1,2,42,3,2,2",1);
        //return question_object;
        return new Question_object(Keyword_id_array,Keyword_array,variable_id_array,Variable_array,question_topic,question_desc,answer_sequence,question_id,start_node,promotion_node,punishment_node,promotion_class,punishment_class);

    }

}
