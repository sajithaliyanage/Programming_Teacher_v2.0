package game.programming.whileloop.canvas_game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabushitha on 5/7/17.
 */

public class MultiplayerQuestion {
    String title;
    String question;
    List<String> options;
    int answer; //1,2,3,4
    MultiplayerQuestion(String t, String q, int answer, String...a){
        this.title =  t;
        this.question = q;
        this.answer = answer;
        options = new ArrayList<String>();
        for(String x:a){
            options.add(x);
        }
    }
    MultiplayerQuestion(){

    }
    //[{"title":"adsadsad", "question":"dhasbhd dhasd asdsad", "options":["x","y","z","r"]}]
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }



}
