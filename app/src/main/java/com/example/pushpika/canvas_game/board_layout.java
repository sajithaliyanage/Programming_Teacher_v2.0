package com.example.pushpika.canvas_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaActionSound;
import android.util.Log;
import android.view.View;

/**
 * Created by pushpika on 3/26/16.
 */
public class board_layout extends View {


    int target_height,target_width;
    Paint red_paint_brush_full,green_paint_brush_full,blue_paint_brush_full;
    Bitmap black_circle;
    int move=0;
    int black_circlex,black_circley,x_dir,y_dir;
    int move_forward,move_backword,move_upward,move_downward;
    String [] transition_sequence={"N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N"}; //20 none,F -forward,B -backward ,U -upward, D -downward
    int current_position,target_position;
    public board_layout(Context context) {
        super(context);

        //setBackgroundResource(R.drawable.boardnew);
        x_dir = 10;
        y_dir = 10;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        red_paint_brush_full = new Paint();
        red_paint_brush_full.setColor(Color.RED);
        red_paint_brush_full.setStyle(Paint.Style.FILL);

        green_paint_brush_full = new Paint();
        green_paint_brush_full.setColor(Color.GREEN);
        green_paint_brush_full.setStyle(Paint.Style.FILL);

        blue_paint_brush_full = new Paint();
        blue_paint_brush_full.setColor(Color.BLUE);
        blue_paint_brush_full.setStyle(Paint.Style.FILL);


        black_circle = BitmapFactory.decodeResource(getResources(), R.drawable.blackcircle);


        if (MainActivity.position_height==0 && MainActivity.position_width==0){ //if variable doesn't initalize
            int numbers[] ={0,1,2,3,4,5,6,7,8,9};
            int x,y;
            y=10 -(int) Math.ceil(MainActivity.current_pos/10.00);
            if(y%2!=0){ //10,30,50...
                x=(MainActivity.current_pos%10)-1;
            }
            else{
                x= 10 -(MainActivity.current_pos%10);
            }
            Log.v("happened x and y",String.valueOf(x)+" and "+ String.valueOf(y));
            MainActivity.position_height = (int) Math.ceil((canvas.getHeight()/10*y+(canvas.getHeight()/50)));   //
            MainActivity.position_width = (int) Math.ceil((canvas.getWidth())/10*x+(canvas.getWidth()/100)); //width ok
        }

        //create transition sequence
        if(MainActivity.is_increment==1) {
            //when increment
            Log.v("happened increment",String.valueOf(MainActivity.target_pos));
            int i = 0;
            while (MainActivity.current_pos + 1 <= MainActivity.target_pos) {
                if ((MainActivity.current_pos) % 10 != 0) {
                    Log.v("current pos is : ",String.valueOf(MainActivity.current_pos));
                    if ((int) Math.floor(MainActivity.current_pos / 10) % 2 == 0) { //move forward
                        move_forward += 1;
                        transition_sequence[i] = "F";
                        Log.v("happened move",  "1 forward");
                    } else if(MainActivity.current_pos!=MainActivity.target_pos) { //move backward
                        move_backword += 1;
                        transition_sequence[i] = "B";
                        Log.v("happened move",  "1 backward");
                    }
                } else if(MainActivity.current_pos!= MainActivity.target_pos) { //move upward
                    move_upward += 1;
                    transition_sequence[i] = "U";
                    Log.v("happened move",  "1 upward");
                }
                MainActivity.current_pos += 1;
                i++;
            }
        }
        else if(MainActivity.is_increment==0) {
            Log.v("happened decrement",String.valueOf(MainActivity.target_pos));
            //when decrement
            int j = 0;
            while (MainActivity.current_pos > MainActivity.target_pos) {
                if ((MainActivity.current_pos - 1) % 10 != 0) {
                    if ((int) Math.floor((MainActivity.current_pos-1)/ 10) % 2 != 0) { //move forward
                        move_forward += 1;
                        transition_sequence[j] = "F";
                        Log.v("happened move",  "1 forward");
                    } else if(MainActivity.current_pos!=MainActivity.target_pos){ //move backward
                        move_backword += 1;
                        transition_sequence[j] = "B";
                        Log.v("happened move",  "1 backward");
                        Log.v("happened cur",  "1 backward"+MainActivity.current_pos);
                        Log.v("happened tar",  "1 backward"+MainActivity.target_pos);
                    }
                } else if(MainActivity.current_pos!=MainActivity.target_pos) { //move upward
                    move_downward += 1;
                    transition_sequence[j] = "D";
                    Log.v("happened move",  "1 downward");
                    Log.v("happened cur",  "1 downward"+MainActivity.current_pos);
                    Log.v("happened tar",  "1 downward"+MainActivity.target_pos);
                }
                MainActivity.current_pos -= 1;
                j++;
            }
        }

        //canvas.drawBitmap(black_circle,MainActivity.position_width,MainActivity.position_height, null);

        //up _code
        if (transition_sequence[move].equals("U")) {
            MainActivity.position_height = MainActivity.position_height - (int) Math.ceil((canvas.getHeight() / 10 * 1));   //
        }

        // down _code
        else if (transition_sequence[move].equals("D")) {
            MainActivity.position_height = MainActivity.position_height + (int) Math.ceil((canvas.getHeight() / 10 * 1));   //
        }

        // move_forward _code
        else if (transition_sequence[move].equals("F")) {
            MainActivity.position_width = MainActivity.position_width + (int) Math.ceil((canvas.getWidth()) / 10 * 1);
        }

        //move_backward _code
        else if (transition_sequence[move].equals("B")) {
            MainActivity.position_width = MainActivity.position_width - (int) Math.ceil((canvas.getWidth()) / 10 * 1);
        }

        canvas.drawBitmap(black_circle, MainActivity.position_width, MainActivity.position_height, null);

        move=move+1;
        MainActivity.is_increment=-1; //loop closed
        if(!transition_sequence[move].equals("N")){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invalidate();
        }
    }

}
