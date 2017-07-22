package game.programming.whileloop.canvas_game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabushitha on 5/7/17.
 */

public class NormalQuestion {
    private String correctSequene;
    private String currentClass;
    private String currentPos;
    private String description;
    private String heading;

    private List<Object> mainTags;
    private List<Object> subTags;

    private String successClass;
    private String successPos;

    NormalQuestion(String cs, String cc, String cp, String d, String h, Object[] mTag, Object[] sTags){
        this.setCorrectSequene(cs);
        this.setCurrentClass(cc);
        this.setCurrentPos(cp);
        this.setDescription(d);
        this.setHeading(h);

        setMainTags(new ArrayList<Object>());
        setSubTags(new ArrayList<Object>());

        for(Object x:mTag){
            getMainTags().add(x);
        }

        for(Object x:sTags){
            getSubTags().add(x);
        }
    }


    NormalQuestion(){

    }


    public String getCorrectSequene() {
        return correctSequene;
    }

    public void setCorrectSequene(String correctSequene) {
        this.correctSequene = correctSequene;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public String getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(String currentPos) {
        this.currentPos = currentPos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Object> getMainTags() {
        return mainTags;
    }

    public void setMainTags(List<Object> mainTags) {
        this.mainTags = mainTags;
    }

    public List<Object> getSubTags() {
        return subTags;
    }

    public void setSubTags(List<Object> subTags) {
        this.subTags = subTags;
    }

    public String getSuccessClass() {
        return successClass;
    }

    public void setSuccessClass(String successClass) {
        this.successClass = successClass;
    }

    public String getSuccessPos() {
        return successPos;
    }

    public void setSuccessPos(String successPos) {
        this.successPos = successPos;
    }
}
