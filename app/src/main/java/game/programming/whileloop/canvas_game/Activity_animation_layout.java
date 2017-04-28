package game.programming.whileloop.canvas_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.example.pushpika.canvas_game.R;
import com.google.example.games.*;

/**
 * Created by whileloop on 3/19/16.
 */
public class Activity_animation_layout extends View {
    int target_height,target_width;
    Paint red_paint_brush_full,green_paint_brush_full,blue_paint_brush_full;
    Bitmap black_circle;
    int black_circlex,black_circley,x_dir,y_dir;

    public Activity_animation_layout(Context context) {
        super(context);
        setBackgroundResource(R.drawable.board);
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

//        Rect rect = new Rect();
//        rect.set(120, 145, 178, 170);
//        canvas.drawRect(rect, green_paint_brush_full);

        black_circle = BitmapFactory.decodeResource(getResources(),R.drawable.blackcircle);


        if (MainActivity.position_height==0 && MainActivity.position_width==0){ //if variable doesn't initalize
            MainActivity.position_height = (int) Math.ceil((canvas.getHeight()/10*9+(canvas.getHeight()/50)));   //
            MainActivity.position_width = (int) Math.ceil((canvas.getWidth())/10*0+(canvas.getWidth()/100)); //width ok


        }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////        //moving conditions



        if(MainActivity.is_increment==1){ //need increment
            MainActivity.is_increment=-1;
            if((MainActivity.cur_position)%10==0){ //1,2,3
                Log.v("happened width",  "up and 3 forward");
                target_height=MainActivity.position_height-((int) Math.ceil((canvas.getHeight())/10*1));

                if((int) Math.ceil((MainActivity.cur_position)/10)%2!=0) { //go up and move right
                    target_width = MainActivity.position_width - ((int) Math.ceil((canvas.getWidth()) / 10 * 3));
                }
                else { //go up and move left
                    target_width = MainActivity.position_width + ((int) Math.ceil((canvas.getWidth()) / 10 * 3));
                }

            }

            else if((MainActivity.cur_position)%10==9){ //1,2 //one forward and up then 2 forward    incorrect
                Log.v("happened width",  "1 forward up 1 forward");
                target_height=MainActivity.position_height-((int) Math.ceil((canvas.getHeight())/10*1));

                if((int) Math.ceil((MainActivity.cur_position)/10)%2!=0) { //go up and move right
                    target_width = MainActivity.position_width - ((int) Math.ceil((canvas.getWidth()) / 10 *2));
                }
                else { //go up and move left
                    target_width = MainActivity.position_width + ((int) Math.ceil((canvas.getWidth()) / 10 * 2));
                }

            }

            else if((MainActivity.cur_position)%10==8){ //1, //two forward and up then 2 forward in correct
                Log.v("happened width",  "2 forward up forward");
                target_height=MainActivity.position_height-((int) Math.ceil((canvas.getHeight())/10*1));

                if((int) Math.ceil((MainActivity.cur_position)/10)%2!=0) { //go up and move right
                    target_width = MainActivity.position_width - ((int) Math.ceil((canvas.getWidth()) / 10 *2));
                }
                else { //go up and move left
                    target_width = MainActivity.position_width + ((int) Math.ceil((canvas.getWidth()) / 10 * 2));
                }

            }

            else {//three forward
                Log.v("happened width",  "3 forward");
                target_height=MainActivity.position_height;

                if((int) Math.ceil((MainActivity.cur_position)/10)%2!=0) { //go up and move right
                    target_width = MainActivity.position_width - ((int) Math.ceil((canvas.getWidth()) / 10 *3));
                }
                else { //go up and move left
                    target_width = MainActivity.position_width + ((int) Math.ceil((canvas.getWidth()) / 10 * 3));
                }

            }

            MainActivity.cur_position+=3;

        }

        else if (MainActivity.is_increment==0){ //to decrement
            MainActivity.is_increment=-1;
            if((MainActivity.cur_position)%10==1){ //1,2 //one down then one backward    incorrect
                target_height=MainActivity.position_height+((int) Math.ceil((canvas.getHeight())/10*1));

                if((int) Math.ceil((MainActivity.cur_position)/10)%2==0) { //go up and move right
                    target_width = MainActivity.position_width - ((int) Math.ceil((canvas.getWidth()) / 10 *1));
                }
                else { //go up and move left
                    target_width = MainActivity.position_width + ((int) Math.ceil((canvas.getWidth()) / 10 *1));
                }

            }

            else if((MainActivity.cur_position)%10==2){ //1, //one backward and down then backward one  in correct
                target_height=MainActivity.position_height+((int) Math.ceil((canvas.getHeight())/10*1));

                if((int) Math.ceil((MainActivity.cur_position)/10)%2==0) { //go up and move right
                    target_width = MainActivity.position_width - ((int) Math.ceil((canvas.getWidth()) / 10 *1));
                }
                else { //go up and move left
                    target_width = MainActivity.position_width + ((int) Math.ceil((canvas.getWidth()) / 10 * 1));
                }

            }

            else {//two backward
                target_height=MainActivity.position_height;

                if((int) Math.ceil((MainActivity.cur_position)/10)%2==0) { //go up and move right
                    target_width = MainActivity.position_width - ((int) Math.ceil((canvas.getWidth()) / 10 *2));
                }
                else { //go up and move left
                    target_width = MainActivity.position_width + ((int) Math.ceil((canvas.getWidth()) / 10 * 2));
                }

            }

            MainActivity.cur_position-=3;


        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Log.v("target width",  String.valueOf(target_width));
        Log.v("target height",  String.valueOf(target_height));

        Log.v("current width",  String.valueOf(MainActivity.position_width));
        Log.v("current height",  String.valueOf(MainActivity.position_height));

        if(MainActivity.position_height<(target_height-50) || MainActivity.position_width<(target_width-50)) {
            if(MainActivity.position_height<(target_height-50)){MainActivity.position_height +=x_dir;}
            if(MainActivity.position_width<(target_width-50)){MainActivity.position_width += y_dir;}
            canvas.drawBitmap(black_circle,MainActivity.position_width,MainActivity.position_height, null);
            invalidate();
        }
        else{
            MainActivity.position_height=target_height;
            MainActivity.position_width=target_width;
            canvas.drawBitmap(black_circle,  target_width,target_height, null);
        }

        //path sequence=hw

    }
}

