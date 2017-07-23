package game.programming.whileloop.canvas_game;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.pushpika.canvas_game.R;

public class database_data_entry_initial extends Activity implements CallBack{

    DatabaseHelper mydb;
    FirebaseData fd;

    Button btn;
    boolean isRetry = true;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_database_data_entry_initial);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        btn = (Button) findViewById(R.id.go_firstpage);
        btn.setVisibility(View.GONE);
        mydb = new DatabaseHelper(this);

        //show loading
        loading = new ProgressDialog(this);
        loading.setTitle("Questions are downloading...");
        loading.setMessage("Please wait... \nConnect to the internet if not connected");
        loading.setCancelable(false);
        loading.show();

        fd = new FirebaseData(this,this);

    }

    public void onBtnClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onComplete(){
        loading.dismiss();

        //false the 1st run
        SharedPreferences settings1 = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings1.edit();
        editor.putBoolean("firstRun", false);
        editor.commit();

        //appear goto 1st page button
        isRetry = false;
        btn.setText("Get Started");
        btn.setVisibility(View.VISIBLE);

    }

    @Override
    public void onError(){
        //loading.dismiss();
        Toast.makeText(this,"Error connecting internet",Toast.LENGTH_SHORT);

        //Exit the application

    }





/*
    public void insertData_tag_ques_assist() {

        int z1 = 1;int z2 = 2;int z3 = 3;int z4 = 4;int z5 = 5;
        int z6 = 6;int z7 = 7;int z8 = 8; int z9 = 9; int z10 = 10;
        int z11 = 11; int z12 =12;int z13 =13;int z14 =14;int z15 =15;int z16=16;


        //Z
        //question 22 class - Z1
        mydb.insert_tag_que_assist(z1,208);
        mydb.insert_tag_que_assist(z1,209);
        mydb.insert_tag_que_assist(z1,210);
        mydb.insert_tag_que_assist(z1,211);

        //question 23 class - Z2
        mydb.insert_tag_que_assist(z2,212);
        mydb.insert_tag_que_assist(z2,213);
        mydb.insert_tag_que_assist(z2,214);

        //question 24 class - Z3
        mydb.insert_tag_que_assist(z3,215);
        mydb.insert_tag_que_assist(z3,216);
        mydb.insert_tag_que_assist(z3,217);

        //question 25 class - Z4
        mydb.insert_tag_que_assist(z4,218);
        mydb.insert_tag_que_assist(z4,219);
        mydb.insert_tag_que_assist(z4,220);
        mydb.insert_tag_que_assist(z4,221);
        mydb.insert_tag_que_assist(z4,222);

        //question 26 class - Z5
        mydb.insert_tag_que_assist(z5,223);
        mydb.insert_tag_que_assist(z5,224);
        mydb.insert_tag_que_assist(z5,225);
        mydb.insert_tag_que_assist(z5,226);
        mydb.insert_tag_que_assist(z5,227);
        mydb.insert_tag_que_assist(z5,228);
        mydb.insert_tag_que_assist(z5,229);

        //question 27 class - Z6
        mydb.insert_tag_que_assist(z6,230);
        mydb.insert_tag_que_assist(z6,231);
        mydb.insert_tag_que_assist(z6,232);
        mydb.insert_tag_que_assist(z6,233);
        mydb.insert_tag_que_assist(z6,234);
        mydb.insert_tag_que_assist(z6,235);
        mydb.insert_tag_que_assist(z6,236);
        mydb.insert_tag_que_assist(z6,237);

        //question 28 class - Z7
        mydb.insert_tag_que_assist(z7,238);
        mydb.insert_tag_que_assist(z7,239);
        mydb.insert_tag_que_assist(z7,240);
        mydb.insert_tag_que_assist(z7,241);
        mydb.insert_tag_que_assist(z7,242);
        mydb.insert_tag_que_assist(z7,243);
        mydb.insert_tag_que_assist(z7,244);
        mydb.insert_tag_que_assist(z7,245);
        mydb.insert_tag_que_assist(z7,246);
        mydb.insert_tag_que_assist(z7,247);
        mydb.insert_tag_que_assist(z7,248);
        mydb.insert_tag_que_assist(z7,249);
        mydb.insert_tag_que_assist(z7,250);
        mydb.insert_tag_que_assist(z7,251);
        mydb.insert_tag_que_assist(z7,252);
        mydb.insert_tag_que_assist(z7,253);
        mydb.insert_tag_que_assist(z7,254);

        //question 29 class - Z8
        mydb.insert_tag_que_assist(z8,255);
        mydb.insert_tag_que_assist(z8,256);
        mydb.insert_tag_que_assist(z8,257);
        mydb.insert_tag_que_assist(z8,258);
        mydb.insert_tag_que_assist(z8,259);
        mydb.insert_tag_que_assist(z8,260);
        mydb.insert_tag_que_assist(z8,261);
        mydb.insert_tag_que_assist(z8,262);
        mydb.insert_tag_que_assist(z8,263);

        //question 30 class - Z9
        mydb.insert_tag_que_assist(z9,264);
        mydb.insert_tag_que_assist(z9,265);
        mydb.insert_tag_que_assist(z9,266);
        mydb.insert_tag_que_assist(z9,267);
        mydb.insert_tag_que_assist(z9,268);
        mydb.insert_tag_que_assist(z9,269);
        mydb.insert_tag_que_assist(z9,270);
        mydb.insert_tag_que_assist(z9,271);
        mydb.insert_tag_que_assist(z9,272);
        mydb.insert_tag_que_assist(z9,273);
        mydb.insert_tag_que_assist(z9,274);

        //question 31 class - Z10
        mydb.insert_tag_que_assist(z10,275);
        mydb.insert_tag_que_assist(z10,276);
        mydb.insert_tag_que_assist(z10,277);
        mydb.insert_tag_que_assist(z10,278);
        mydb.insert_tag_que_assist(z10,279);
        mydb.insert_tag_que_assist(z10,280);
        mydb.insert_tag_que_assist(z10,281);
        mydb.insert_tag_que_assist(z10,282);
        mydb.insert_tag_que_assist(z10,283);

        //question 32 class - Z11
        mydb.insert_tag_que_assist(z11,284);
        mydb.insert_tag_que_assist(z11,285);
        mydb.insert_tag_que_assist(z11,286);
        mydb.insert_tag_que_assist(z11,287);
        mydb.insert_tag_que_assist(z11,288);
        mydb.insert_tag_que_assist(z11,289);
        mydb.insert_tag_que_assist(z11,290);
        mydb.insert_tag_que_assist(z11,291);
        mydb.insert_tag_que_assist(z11,292);

        //question 33 class - Z12
        mydb.insert_tag_que_assist(z12,293);
        mydb.insert_tag_que_assist(z12,294);
        mydb.insert_tag_que_assist(z12,295);
        mydb.insert_tag_que_assist(z12,296);
        mydb.insert_tag_que_assist(z12,297);
        mydb.insert_tag_que_assist(z12,298);
        mydb.insert_tag_que_assist(z12,299);
        mydb.insert_tag_que_assist(z12,300);


        //question 34 class - Z13
        mydb.insert_tag_que_assist(z13,301);
        mydb.insert_tag_que_assist(z13,302);
        mydb.insert_tag_que_assist(z13,303);
        mydb.insert_tag_que_assist(z13,304);
        mydb.insert_tag_que_assist(z13,305);
        mydb.insert_tag_que_assist(z13,306);
        mydb.insert_tag_que_assist(z13,307);
        mydb.insert_tag_que_assist(z13,308);
        mydb.insert_tag_que_assist(z13,309);
        mydb.insert_tag_que_assist(z13,310);

        //question 35 class - Z14
        mydb.insert_tag_que_assist(z14,311);
        mydb.insert_tag_que_assist(z14,312);
        mydb.insert_tag_que_assist(z14,313);
        mydb.insert_tag_que_assist(z14,314);
        mydb.insert_tag_que_assist(z14,315);
        mydb.insert_tag_que_assist(z14,316);
        mydb.insert_tag_que_assist(z14,317);
        mydb.insert_tag_que_assist(z14,318);
        mydb.insert_tag_que_assist(z14,319);
        mydb.insert_tag_que_assist(z14,320);

        //question 36 class - Z15
        mydb.insert_tag_que_assist(z15,321);
        mydb.insert_tag_que_assist(z15,322);
        mydb.insert_tag_que_assist(z15,323);
        mydb.insert_tag_que_assist(z15,324);
        mydb.insert_tag_que_assist(z15,325);

        //question 37 class - Z16
        mydb.insert_tag_que_assist(z16,326);
        mydb.insert_tag_que_assist(z16,327);
        mydb.insert_tag_que_assist(z16,328);
        mydb.insert_tag_que_assist(z16,329);
        mydb.insert_tag_que_assist(z16,330);
        mydb.insert_tag_que_assist(z16,331);





//        -----------------------------
        //after all data stored
        btn.setVisibility(View.VISIBLE);
    }

*/
/*
    public void insertData_Question() {
        //(String question_topic,String question_desc,
        // String question_class,String answer_sequence,
        //String start_node,String promotion_node, String punishment_node, String promotion_class, String punishment_class){

        //        Q22
        mydb.insert_Question(
                "Follow “GoTo” statement",
                "Instruct to jump to the position 3",
                "Z1","208,209","1","3","1","Z2","Z1");

//        Q23
        mydb.insert_Question(
                "Follow “Go” statement",
                "Instruct to go 2 steps forward",
                "Z2","212,213","3","5","3","Z3","Z2");
//        Q24
        mydb.insert_Question(
                "Follow “backward” statement",
                "Instruct to go 1 step backward",
                "Z3","215,216","5","4","5","Z4","Z3");
//        Q25
        mydb.insert_Question(
                "Follow “Go” statement  and assigning value to a variable ",
                "Instruct to go 2 steps forward",
                "Z4","221,218,219","4","6","4","Z5","Z4");

//        Q26
        mydb.insert_Question(
                "Calculate the total sum of two small numbers",
                "Instruct to go 3 steps forward",
                "Z5","227,228,225,226,229,223,224","6","9","6","Z6","Z5");
//        Q27
        mydb.insert_Question(
                "Calculate the total sum of two small numbers",
                "Instruct to jump to the position 14",
                "Z6","234,233,235,232,230,231|233,234,235,232,230,231|234,235,233,232,230,231|233,235,234,232,230,231","9","14","9","Z7","Z6");
//        Q28
        mydb.insert_Question(
                "Calculate the arithmetics values (and precedence of operands)",
                "Instruct to jump to the position 20",
                "Z7","242,239,248,249,246,252,240,238,239|242,239,248,252,246,249,240,238,239","14","20","14","Z8","Z7");
//        Q29
        mydb.insert_Question(
                "The knowledge of conditional statements",
                "Instruct to go 2 steps forward if current position is 20",
                "Z8","257,258,259,260,261,262,263,255,256","20","22","20","Z9","Z8");
//        Q30
        mydb.insert_Question(
                "The knowledge of conditional statements",
                "Generate a random integer Y. Instruct to go 2 steps forward if Y is a multiple of 2",
                "Z9","267,266,269,271,270,273,264,265","22","24","22","Z10","Z9");
//        Q31
        mydb.insert_Question(
                "Knowledge of modular arithmetic",
                "Instruct to go 6 steps forward",
                "Z10","277,283,278,281,280,282,275,276","24","30","24","Z11","Z10");
//        Q32
        mydb.insert_Question(
                "Knowledge of modular arithmetic",
                "Instruct to go 2 steps forward",
                "Z11","286,292,287,290,288,289,284,285","30","32","30","Z12","Z11");
//        Q33
        mydb.insert_Question(
                "Get the corresponding value of a given index of an array. (ordering and indexing mechanism of an array)",
                "Instruct to jump to position 50",
                "Z12","294,293,300","32","50","32","Z13","Z12");
        //SET 02

//        Q34
        mydb.insert_Question(
                "Once a value is given, identify the corresponding position in the array",
                "Instruct to goto position 53",
                "Z13","302,303,308,306,309,304,307,305,310,301|302,303,308,306,309,304,307,305,301,310","50","53","50","Z14","Z13");


//        Q35
        mydb.insert_Question(
                "Count the total value of every item in an array and the length of an array",
                "Instruct to goto 2 steps forward",
                "Z14",
                "312,315,316,313,320,314,318,311|312,316,315,313,320,314,318,311|315,312,316,313,320,314,318,311|315,316,312,313,320,314,318,311|316,312,315,313,320,314,318,311|316,315,312,313,320,314,318,311",
                "53","55","53","Z15","Z14");

//        Q36
        mydb.insert_Question(
                "Sorting and searching",
                "Instruct to goto 4 steps forward",
                "Z15",
                "323,325,322,321",
                "55","59","55","Z16","Z15");

//        Q37
        mydb.insert_Question(
                "Sorting and searching-having an idea about efficiency of sorting algorithms.\n",
                "Instruct to sort using most efficient sorting algorithm and go 4 steps forward",
                "Z16",
                "328,329,327,326",
                "59","63","59","Z17","V");

    }
*/
/*
    public void insertData_tags(){

        //keyword is used for mainly use variables

        //question 01 class - A
        mydb.insert_Tag("x=y;\n","MAIN","None"); //id=1
        mydb.insert_Tag("temp=x;\n","MAIN","None"); //id=2
        mydb.insert_Tag("y=temp;\n","MAIN","None"); //id=3
        mydb.insert_Tag("print(x,y)\n","VARIABLE","None"); //id=4
        mydb.insert_Tag("x=10;y=20;\n","VARIABLE","None"); //id=5

        //question 02 class - B
        mydb.insert_Tag("else\n","MAIN","None"); //id=6
        mydb.insert_Tag(" return false\n","VARIABLE","None"); //id=7
        mydb.insert_Tag(" return true\n","VARIABLE","None"); //id=8
        mydb.insert_Tag("(isPrime(myScore))\n","MAIN","None"); //id=9
        mydb.insert_Tag("if","MAIN","None");//id=10

        //question 03 class - C
        //mydb.insert_Tag("if","MAIN","None"); //id=10
        //mydb.insert_Tag("else","MAIN","None"); //id=6

        mydb.insert_Tag("Elseif","MAIN","None"); //id=11
        mydb.insert_Tag(" print(\"x is less than 20\");\n","VARIABLE","None"); //id=12
        mydb.insert_Tag(" print(\"x>20 or x=20\");\n","VARIABLE","None"); //id=13
        mydb.insert_Tag("(x<20)\n","VARIABLE","None"); //id=14
        mydb.insert_Tag(" print(\"x is less than 10\");\n","VARIABLE","None"); //id=15
        mydb.insert_Tag("(x<10)\n","VARIABLE","None"); //id=16


        //question 04 class - D

        mydb.insert_Tag("break\n","MAIN","None");//id=17
        mydb.insert_Tag("(month)\n","VARIABLE","None");//id=18
        mydb.insert_Tag("print('January')\n","VARIABLE","None");//id=19
        mydb.insert_Tag("switch","MAIN","None"); //id=20
        mydb.insert_Tag("default:","MAIN","None");//id=21
        mydb.insert_Tag("case 2:","MAIN","None");//id=22
        mydb.insert_Tag("case 1:","MAIN","None");//id=23
        mydb.insert_Tag("case 3:","MAIN","None");//id=24
        mydb.insert_Tag("print('February')\n","VARIABLE","None"); //id=25
        mydb.insert_Tag("print('March')\n","VARIABLE","None"); //id=26


        //question 05 class - E


        mydb.insert_Tag("i<=myScore;","VARIABLE","None"); //id =27
        mydb.insert_Tag("i%2== 0) \n","VARIABLE","None"); //id=28
        mydb.insert_Tag("i++) { \n","MAIN","None");  //id=29
        mydb.insert_Tag("for ( ","MAIN","None"); //id=30
        mydb.insert_Tag("i>=myScore;","VARIABLE","None"); //id =31
        mydb.insert_Tag("print(i); }\n","VARIABLE","None"); //id=32
        mydb.insert_Tag("if( ","MAIN","None"); //id=33
        mydb.insert_Tag("i=0; ","VARIABLE","None");//id=34

        //question 06 class - F

        mydb.insert_Tag(" item in data ) { \n","VARIABLE","None");//id=35
        mydb.insert_Tag("sum=+data; }\n","VARIABLE","None");//id=36
        mydb.insert_Tag("print(sum); \n","VARIABLE","None");//id=37
        mydb.insert_Tag("print(item) \n","VARIABLE","None");//id=38
        mydb.insert_Tag("for-each( ","MAIN","None");//id=39
        mydb.insert_Tag("sum =+item; } \n","VARIABLE","None");//id=40
        mydb.insert_Tag("data =[20,30,40,50,60]; sum=0; \n","VARIABLE","None");//id=41
        mydb.insert_Tag(" data in item ) { \n","VARIABLE","None");//id=42
        mydb.insert_Tag("sum = sum+item; }\n","VARIABLE","None");//id=43

        //41,39,35,43,37

        //question 07 class - G

        mydb.insert_Tag("print(x); \n","VARIABLE","None"); //id= 44
        mydb.insert_Tag("} while ","MAIN","None"); //id= 45
        mydb.insert_Tag("x = x-1; \n","VARIABLE","None"); //id=46
        mydb.insert_Tag("x = 10; \n","VARIABLE","None"); //id= 47
        mydb.insert_Tag("do{ \n","MAIN","None"); //id= 48
        mydb.insert_Tag("(x < 0); \n","VARIABLE","None"); //id= 49
        mydb.insert_Tag("x = x+1; \n","VARIABLE","None"); //id= 50
        mydb.insert_Tag("x = 9; \n","VARIABLE","None"); //id= 51
        mydb.insert_Tag("(x >= 0); \n","VARIABLE","None"); //id= 52

        //question 08 class - H

        mydb.insert_Tag("while (a<=myScore) \n","MAIN","None");//id=53
        mydb.insert_Tag("temp =b; \n","VARIABLE","None");//id=54
        mydb.insert_Tag("a = temp; \n","VARIABLE","None");//id=55
        mydb.insert_Tag("print(a); \n","VARIABLE","None");//id=56
        mydb.insert_Tag("b =a+b; \n","VARIABLE","None");//id=57
        mydb.insert_Tag("a=0;b=1; \n","VARIABLE","None");//id=58




        //question 09 class - I

        mydb.insert_Tag("letter == ","VARIABLE","None");//id=59
        mydb.insert_Tag("letter in 'ALGORITHM' )\n","VARIABLE","None");//id=60
        mydb.insert_Tag(" r ) \n","VARIABLE","None");//id=61
        mydb.insert_Tag("print(letter); \n","VARIABLE","None");//id=62
        mydb.insert_Tag(" for( \n","MAIN","None");//id=63
        mydb.insert_Tag("if (","MAIN","None");//id=64
        mydb.insert_Tag("for-each( ","MAIN","None");//id=65
        mydb.insert_Tag("break; \n","MAIN","None");//id=66
        //65 60 64 59 61 66 62

        //question 10 class - J

        mydb.insert_Tag(" letter == ","VARIABLE","None");//id=67
        mydb.insert_Tag(" 'i' ) \n","VARIABLE","None");//id=68
        mydb.insert_Tag(" continue; \n","MAIN","None");//id=69
        mydb.insert_Tag(" 'a') \n","VARIABLE","None");//id=70
        mydb.insert_Tag("if ( ","MAIN","None");//id=71
        mydb.insert_Tag("for-each ( ","MAIN","None");//id=72
        mydb.insert_Tag(" print(letter) \n","VARIABLE","None");//id=73
        mydb.insert_Tag("letter in 'grain' )\n","VARIABLE","None");//id=74
        mydb.insert_Tag(" 'r') \n","VARIABLE","None");//id=75

        //72 74 71 67 75 69 73

        //question 11 class - k

        mydb.insert_Tag("j <= i/j;","VARIABLE","None");//id= 76
        mydb.insert_Tag("if","MAIN","None");//id= 77
        mydb.insert_Tag("i--){ \n","VARIABLE","None");//id= 78
        mydb.insert_Tag("(!(i % j)){ \n","VARIABLE","None");//id= 79
        mydb.insert_Tag("i <= end;","VARIABLE","None");//id= 80
        mydb.insert_Tag("for(","MAIN","None");//id= 81
        mydb.insert_Tag("start = 2; end = myScore; \n","VARIABLE","None");//id= 82
        mydb.insert_Tag("i++){ \n","VARIABLE","None");//id= 83
        mydb.insert_Tag("start = myScore; end =2; \n","VARIABLE","None");//id= 84
        mydb.insert_Tag("j = start;","VARIABLE","None");//id= 85
        mydb.insert_Tag("break;}} \n","VARIABLE","None");//id= 86
        mydb.insert_Tag("i = start;","VARIABLE","None");//id= 87
        mydb.insert_Tag("(j>(i/j)){ \n","VARIABLE","None");//id= 88
        mydb.insert_Tag("j++){ \n","VARIABLE","None");//id= 89
        mydb.insert_Tag("print(i);} } \n","MAIN","None");//id= 90

        // 82,81,87,80,83,81,85,76,89,77,79,86,77,88,90



        //question 12 class - L

        mydb.insert_Tag("else {\n","MAIN","None"); //id=91
        mydb.insert_Tag("if","MAIN","None"); //id=92
        mydb.insert_Tag("(i=1;i<=5;i++) { \n","VARIABLE","None"); //id=93
        mydb.insert_Tag("for","MAIN","None"); //id=94
//        mydb.insert_Tag("for","MAIN","None"); //id=94
        mydb.insert_Tag("print(' '); }\n","VARIABLE","None"); //id=95
        mydb.insert_Tag("} println(); } \n","VARIABLE","None"); //id=96
        mydb.insert_Tag("(j==4 || j==1 || i==1 || i==5){ \n","VARIABLE","None"); //id=97
        mydb.insert_Tag("(j=1;j<=4;j++){ \n","VARIABLE","None"); //id=98
        mydb.insert_Tag("print(1); }\n","VARIABLE","None"); //id=99

        //94 93 94 98 92 97 99 91 95 96

        //question 13 class - M

        mydb.insert_Tag("(a > 0){ \n","VARIABLE","None");//id=100
        mydb.insert_Tag("s = s + (a % 10); \n","VARIABLE","None");//id=101
        mydb.insert_Tag("}if","MAIN","None");//id=102
        mydb.insert_Tag("print('Palindrome'); \n","VARIABLE","None");//id=103
        mydb.insert_Tag("t = a;s = len(a) \n","VARIABLE","None");//id=104
        mydb.insert_Tag("s = s * 10; \n","VARIABLE","None");//id=105
        mydb.insert_Tag("a = input(); \n","VARIABLE","None");//id=106
        mydb.insert_Tag("(t == s){ \n","VARIABLE","None");//id=107
        mydb.insert_Tag("}else{ \n","MAIN","None");//id=108
        mydb.insert_Tag("a = a / 10; \n","VARIABLE","None");//id=109
        mydb.insert_Tag("s = 0;t = a; \n","MAIN","None");//id=110
        mydb.insert_Tag("print('Not a palindrome');} \n","VARIABLE","None");//id=111
        mydb.insert_Tag("while","MAIN","None");//id=112
        mydb.insert_Tag("(t == a){ \n","VARIABLE","None");//id=113
        mydb.insert_Tag("(a > 9){ \n","VARIABLE","None");//id=114

        //106,110,112,100,105,101,109,102,107,103,108,111


        //question 14 class - N

        mydb.insert_Tag("if","VARIABLE","None");//id=115
        mydb.insert_Tag("array.","VARIABLE","None");//id=116
        mydb.insert_Tag("(s); \n","VARIABLE","None");//id=117
        mydb.insert_Tag("break; \n","MAIN","None");//id=118
        mydb.insert_Tag("(s): \n","VARIABLE","None");//id=119
        mydb.insert_Tag("array = []; \n","MAIN","None");//id=120
        mydb.insert_Tag("else: \n","MAIN","None");//id=121
        mydb.insert_Tag("print(sentence); \n","VARIABLE","None");//id=122
        mydb.insert_Tag("append","MAIN","None");//id=123
        mydb.insert_Tag("True: \n","VARIABLE","None");//id=124
        mydb.insert_Tag("(s.upper()); \n","VARIABLE","None");//id=125
        mydb.insert_Tag("sentence in array: \n","VARIABLE","None");//id=126
        mydb.insert_Tag("while","MAIN","None");//id=127
        mydb.insert_Tag("for","MAIN","None");//id=128
        mydb.insert_Tag("s = input(); \n","MAIN","None");//id=129

        //120,127,124,129,115,119,116,123,125,121,118,128,126,122

        //question 15 class - O


        mydb.insert_Tag("(j,j+1); \n","VARIABLE","None");//id=130
        mydb.insert_Tag("(j=0;j<=array.length-1;j++): \n","VARIABLE","None");//id=131
        mydb.insert_Tag("if","VARIABLE","None");//id=132
        mydb.insert_Tag("bubble_sort","MAIN","None");//id=133
        mydb.insert_Tag("for","MAIN","None");//id=134
        mydb.insert_Tag("swap","MAIN","None");//id=135
        mydb.insert_Tag("(array[j]>array[j+1]) \n","VARIABLE","None");//id=136
        mydb.insert_Tag("(i,i+1); \n","VARIABLE","None");//id=137
        mydb.insert_Tag("(array): \n","MAIN","None");//id=138
        mydb.insert_Tag("function","MAIN","None");//id=139
        mydb.insert_Tag("(j=0;j<=array.length-1-i;j++): \n","VARIABLE","None");//id=140
        mydb.insert_Tag("(i=0;i<=array.length;i++): \n","VARIABLE","None");//id=141



        //139,133,138,134,141,134,140,132,136,135,130

        //--------------------

        //question 16 class - p

        mydb.insert_Tag("-2;\n","VARIABLE","None");//id=142
        mydb.insert_Tag("print(","MAIN","None");//id=143
        mydb.insert_Tag("+2;\n","VARIABLE","None");//id=144
        mydb.insert_Tag("myScore","MAIN","None");//id=145
        mydb.insert_Tag(");\n","VARIABLE","None");//id=146
        mydb.insert_Tag("=","VARIABLE","None");//id=147

        //145,147,145,144,143,145,146



        //question 17 class - q

        mydb.insert_Tag("modulo","MAIN","None");//id=148
        mydb.insert_Tag("myScore","MAIN","None");//id=149
        mydb.insert_Tag("return","VARIABLE","None");//id=150
        mydb.insert_Tag(");\n","VARIABLE","None");//id=151
        mydb.insert_Tag("getModular(","MAIN","None");//id=152
        mydb.insert_Tag("function","MAIN","None");//id=153
        mydb.insert_Tag("=","VARIABLE","None");//id=154
        mydb.insert_Tag("4;\n","VARIABLE","None");//id=155
        mydb.insert_Tag("value","VARIABLE","None");//id=156
        mydb.insert_Tag("a","VARIABLE","None");//id=157
        mydb.insert_Tag("print(","VARIABLE","None");//id=158
        mydb.insert_Tag("main():\n","VARIABLE","None");//id=159
        mydb.insert_Tag("):\n","VARIABLE","None");//id=160

        //153,152,157,160,150,157,148,155,153,159,156,154,152,149,151,158,156,151


        //question 18 class - r


        mydb.insert_Tag("return","MAIN","None");//id=161
        mydb.insert_Tag("b /","VARIABLE","None");//id=162
        mydb.insert_Tag(";\n","VARIABLE","None");//id=163
        mydb.insert_Tag("function","MAIN","None");//id=164
        mydb.insert_Tag(":\n","VARIABLE","None");//id=165
        mydb.insert_Tag("(b==0)","VARIABLE","None");//id=166
        mydb.insert_Tag("a *","VARIABLE","None");//id=167
        mydb.insert_Tag("( a, b-1 )","VARIABLE","None");//id=168
        mydb.insert_Tag("else","MAIN","None");//id=169
        mydb.insert_Tag("if","MAIN","None");//id=170
        mydb.insert_Tag("powerFunc","MAIN","None");//id=171
        mydb.insert_Tag("b *","VARIABLE","None");//id=172
        mydb.insert_Tag("a /","VARIABLE","None");//id=173
        mydb.insert_Tag("(a==0)","VARIABLE","None");//id=174
        mydb.insert_Tag("(a,b)","VARIABLE","None");//id=175
        mydb.insert_Tag("1","VARIABLE","None");//id=176

        //164,171,175,165,170,174,165,161,176,163,169,165,161,167,171,168,163


        //question 19 class - s


        mydb.insert_Tag("myCar","MAIN","None");//id=177
        mydb.insert_Tag("()","VARIABLE","None");//id=178
        mydb.insert_Tag("Red","VARIABLE","None");//id=179
        mydb.insert_Tag(".getColor","MAIN","None");//id=180
        mydb.insert_Tag("Car","MAIN","None");//id=181
        mydb.insert_Tag("Print","VARIABLE","None");//id=182
        mydb.insert_Tag("=","VARIABLE","None");//id=183
        mydb.insert_Tag(".setColor","MAIN","None");//id=184
        mydb.insert_Tag("new","VARIABLE","None");//id=185
        mydb.insert_Tag(";\n","VARIABLE","None");//id=186


        //181,177,183,185,181,178,186,177,184,183,179,186,182,177,180,178,186

        //question 20 class - t

        mydb.insert_Tag(",","VARIABLE","None");//id=187
        mydb.insert_Tag("10","VARIABLE","None");//id=188
        mydb.insert_Tag("increase","MAIN","None");//id=189
        mydb.insert_Tag("5","VARIABLE","None");//id=190
        mydb.insert_Tag("divide","MAIN","None");//id=191
        mydb.insert_Tag(")","VARIABLE","None");//id=192
        mydb.insert_Tag("(","VARIABLE","None");//id=193
        mydb.insert_Tag(";\n","VARIABLE","None");//id=194
        mydb.insert_Tag("2","VARIABLE","None");//id=195
        mydb.insert_Tag("12","VARIABLE","None");//id=196


        //189,193,191,193,196,187,195,192,192,194

        //question 21 class - u

        mydb.insert_Tag("1","VARIABLE","None");//id=197
        mydb.insert_Tag("=","VARIABLE","None");//id=198
        mydb.insert_Tag(";\n","VARIABLE","None");//id=199
        mydb.insert_Tag("(","VARIABLE","None");//id=200
        mydb.insert_Tag("value","MAIN","None");//id=201
        mydb.insert_Tag(")","VARIABLE","None");//id=202
        mydb.insert_Tag("myScore + ","MAIN","None");//id=203
        mydb.insert_Tag("setMyScore","MAIN","None");//id=204
        mydb.insert_Tag(">","VARIABLE","None");//id=205
        mydb.insert_Tag("myScore , ","MAIN","None");//id=206
        mydb.insert_Tag("add","MAIN","None");//id=207



        //201,198,197,199,204,200,207,200,206,201,202,202,199
        //204,200,207,200,206,197,202,202,199

        //question 22 class - Z1
        mydb.insert_Tag("GOTO","MAIN","None");//id=208
        mydb.insert_Tag("(3);\n","VARIABLE","None");//id=209
        mydb.insert_Tag("(5);\n","VARIABLE","None");//id=210
        mydb.insert_Tag("(7);\n","VARIABLE","None");//id=211

        //question 23 class - Z2
        mydb.insert_Tag("GO","MAIN","None");//id=212
        mydb.insert_Tag("(2_steps _forward);\n","VARIABLE","None");//id=213
        mydb.insert_Tag("(3_steps_forward);\n","VARIABLE","None");//id=214

        //question 24 class - Z3
        mydb.insert_Tag("GO","MAIN","None");//id=215
        mydb.insert_Tag("(1_steps_backward);\n","VARIABLE","None");//id=216
        mydb.insert_Tag("(2_steps_backward);\n","VARIABLE","None");//id=217

        //question 25 class - Z4
        mydb.insert_Tag("GO","MAIN","None");//id=218
        mydb.insert_Tag("(X_steps_forward);\n","MAIN","None");//id=219
        mydb.insert_Tag("X=1;\n","VARIABLE","None");//id=220
        mydb.insert_Tag("X=2;\n","VARIABLE","None");//id=221
        mydb.insert_Tag("X=3;\n","VARIABLE","None");//id=222

        //question 26 class - Z5
        mydb.insert_Tag("GO","MAIN","None");//id=223
        mydb.insert_Tag("(Y_steps_forward);\n","MAIN","None");//id=224
        mydb.insert_Tag("Y","VARIABLE","None");//id=225
        mydb.insert_Tag("=","VARIABLE","None");//id=226
        mydb.insert_Tag("A=2;\n","VARIABLE","None");//id=227
        mydb.insert_Tag("B=1;\n","VARIABLE","None");//id=228
        mydb.insert_Tag("A+B;\n","VARIABLE","None");//id=229

        //question 27 class - Z6
        mydb.insert_Tag("GOTO","MAIN","None");//id=230
        mydb.insert_Tag("(Y);\n","MAIN","None");//id=231
        mydb.insert_Tag("Y = A+B+C;\n","VARIABLE","None");//id=232
        mydb.insert_Tag("B=7;\n","VARIABLE","None");//id=233
        mydb.insert_Tag("A=3;\n","VARIABLE","None");//id=234
        mydb.insert_Tag("C=4;\n","VARIABLE","None");//id=235
        mydb.insert_Tag("D=5;\n","VARIABLE","None");//id=236
        mydb.insert_Tag("Y = A+D+B;\n","VARIABLE","None");//id=237
        //234,233,235,232,230,231

        //242,239,248,249,246,252,240,238,239|242,239,248,252,246,249,240,238,239
        //question 28 class - Z7
        mydb.insert_Tag("GOTO","MAIN","None");//id=238
        mydb.insert_Tag("Y","MAIN","None");//id=239
        mydb.insert_Tag(";\n","VARIABLE","None");//id=240
        mydb.insert_Tag("a=1;\n","VARIABLE","None");//id=241
        mydb.insert_Tag("a=myPosition;\nb=1;\nc=4;\nd=3;\n","VARIABLE","None");//id=242
        mydb.insert_Tag("c=1;\n","VARIABLE","None");//id=243
        mydb.insert_Tag("d=1;\n","VARIABLE","None");//id=244
        mydb.insert_Tag("+","VARIABLE","None");//id=245
        mydb.insert_Tag("*","VARIABLE","None");//id=246
        mydb.insert_Tag("/","VARIABLE","None");//id=247
        mydb.insert_Tag("=","VARIABLE","None");//id=248
        mydb.insert_Tag("(a+b)","VARIABLE","None");//id=249
        mydb.insert_Tag("b","VARIABLE","None");//id=250
        mydb.insert_Tag("c","VARIABLE","None");//id=251
        mydb.insert_Tag("c/d","VARIABLE","None");//id=252
        mydb.insert_Tag("(","VARIABLE","None");//id=253
        mydb.insert_Tag(")","VARIABLE","None");//id=254

        //question 29 class - Z8
        mydb.insert_Tag("GO","MAIN","None");//id=255
        mydb.insert_Tag("(X_steps_forward);\n","MAIN","None");//id=256
        mydb.insert_Tag("A = 20;\n","VARIABLE","None");//id=257
        mydb.insert_Tag("B=myPosition;\n","VARIABLE","None");//id=258
        mydb.insert_Tag("if","MAIN","None");//id=259
        mydb.insert_Tag("(A==B)","VARIABLE","None");//id=260
        mydb.insert_Tag("then\n","VARIABLE","None");//id=261
        mydb.insert_Tag("Y=8;\n","VARIABLE","None");//id=262
        mydb.insert_Tag("X=Y/4;\n","VARIABLE","None");//id=263

        //question 30 class - Z9
        //267,266,269,271,270,273,264,265
        mydb.insert_Tag("GO","MAIN","None");//id=264
        mydb.insert_Tag("(X_steps_forward)\n","MAIN","None");//id=265
        mydb.insert_Tag("if","MAIN","None");//id=266
        mydb.insert_Tag("X=2\nY=RANDOM_INTEGER\n","MAIN","None");//id=267
        mydb.insert_Tag("Y/2","VARIABLE","None");//id=268
        mydb.insert_Tag("Y mod 2","VARIABLE","None");//id=269
        mydb.insert_Tag("0","VARIABLE","None");//id=270
        mydb.insert_Tag("==","VARIABLE","None");//id=271
        mydb.insert_Tag("2","VARIABLE","None");//id=272
        mydb.insert_Tag("then\n","VARIABLE","None");//id=273
        mydb.insert_Tag("=","VARIABLE","None");//id=274

        //question 31 class - Z10
        //277,283,278,281,280,282,275,276
        mydb.insert_Tag("GO","MAIN","None");//id=275
        mydb.insert_Tag("(X_steps_forward);\n","MAIN","None");//id=276
        mydb.insert_Tag("X","VARIABLE","None");//id=277
        mydb.insert_Tag("51","VARIABLE","None");//id=278
        mydb.insert_Tag("7","VARIABLE","None");//id=279
        mydb.insert_Tag("15","VARIABLE","None");//id=280
        mydb.insert_Tag("mod","VARIABLE","None");//id=281
        mydb.insert_Tag(";\n","VARIABLE","None");//id=282
        mydb.insert_Tag("=","VARIABLE","None");//id=283

        //question 32 class - Z11
        //286,292,287,290,288,289,284,285
        mydb.insert_Tag("GO","MAIN","None");//id=284
        mydb.insert_Tag("(X_steps_forward);","MAIN","None");//id=285
        mydb.insert_Tag("X","VARIABLE","None");//id=286
        mydb.insert_Tag("51","VARIABLE","None");//id=287
        mydb.insert_Tag("7","VARIABLE","None");//id=288
        mydb.insert_Tag(";\n","VARIABLE","None");//id=289
        mydb.insert_Tag("mod","VARIABLE","None");//id=290
        mydb.insert_Tag("div","VARIABLE","None");//id=291
        mydb.insert_Tag("=","VARIABLE","None");//id=292

        //question 33 class - Z12
        mydb.insert_Tag("GOTO","MAIN","None");//id=293
        mydb.insert_Tag("ArrayA ={35,50,60,23,45};\n","VARIABLE","None");//id=294
        mydb.insert_Tag("49","VARIABLE","None");//id=295
        mydb.insert_Tag("GO","VARIABLE","None");//id=296
        mydb.insert_Tag("1_step_back","VARIABLE","None");//id=297
        mydb.insert_Tag("ArrayB={35,52,60,23,45};\n","VARIABLE","None");//id=298
        mydb.insert_Tag("(ArrayA[0]);\n","VARIABLE","None");//id=299
        mydb.insert_Tag("(ArrayA[1]);\n","VARIABLE","None");//id=300


        //question 34 class - Z13

        //302,303,308,306,309,304,307,305,310,301|302,303,308,306,309,304,307,305,301,310
        mydb.insert_Tag("Goto(K);\n","MAIN","None");//id=301
        mydb.insert_Tag("ArrayC={32,50,53,30}\n","MAIN","None");//id=302
        mydb.insert_Tag("For (i=0;i++, i<4){\n","MAIN","None");//id=303
        mydb.insert_Tag("K","MAIN","None");//id=304
        mydb.insert_Tag("ArrayC(i);\n","MAIN","None");//id=305
        mydb.insert_Tag("i==2 ","VARIABLE","None");//id=306
        mydb.insert_Tag("=","VARIABLE","None");//id=307
        mydb.insert_Tag("If ","VARIABLE","None");//id=308
        mydb.insert_Tag("then\n ","VARIABLE","None");//id=309
        mydb.insert_Tag("\n}\n","VARIABLE","None");//id=310

        //question 35 class - Z14
        mydb.insert_Tag("Go X steps forward\n","MAIN","None");//id=311
        mydb.insert_Tag("ArrayA ={0,1,2,3,4}\n","MAIN","None");//id=312
        mydb.insert_Tag("For (i=0,i<k-1,i++){\n","MAIN","None");//id=313
        mydb.insert_Tag("\n}\n","MAIN","None");//id=314
        mydb.insert_Tag("sum=0\n","VARIABLE","None");//id=315
        mydb.insert_Tag("k=5\n","VARIABLE","None");//id=316
        mydb.insert_Tag("k=4\n ","VARIABLE","None");//id=317
        mydb.insert_Tag("X = sum mod 4\n ","VARIABLE","None");//id=318
        mydb.insert_Tag("X = sum\n","VARIABLE","None");//id=319
        mydb.insert_Tag("sum = sum + ArrayA[i]\n","VARIABLE","None");//id=320

        //Question 36 - class Z15

        mydb.insert_Tag("Go X steps forward;\n","MAIN","None");//id=321
        mydb.insert_Tag("X = ArrayA[0];\n","MAIN","None");//id=322
        mydb.insert_Tag("ArrayA = [3,4,1,0];\n","MAIN","None");//id=323
        mydb.insert_Tag("sort ArrayA in accending;\n","VARIABLE","None");//id=324
        mydb.insert_Tag("sort ArrayA in decending;\n","VARIABLE","None");//id=325

        //Question 37 - class Z16

        mydb.insert_Tag("Go X steps forward;\n","MAIN","None");//id=326
        mydb.insert_Tag("X= ArrayA[2];\n","MAIN","None");//id=327
        mydb.insert_Tag("ArrayA =[2,4,6,3,9];\n","MAIN","None");//id=328
        mydb.insert_Tag("sort array using selection sort;\n","VARIABLE","None");//id=329
        mydb.insert_Tag("sort array using bubble sort;\n","VARIABLE","None");//id=330
        mydb.insert_Tag("X= ArrayA[1];\n","VARIABLE","None");//id=331


    }
*/
}