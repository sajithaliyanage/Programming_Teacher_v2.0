package game.programming.whileloop.canvas_game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabushitha on 5/7/17.
 */

public class NormalQuestion {
    private String correctSequene;
    private String currentClass;
    private int currentPos;
    private String description;
    private String heading;

    private List<Tag> mainTags;
    private List<Tag> subTags;

    private String successClass;
    private int successPos;

    NormalQuestion(String cs, String cc, String cp, String d, String h, Tag[] mTag, Tag[] sTags){
        this.setCorrectSequene(cs);
        this.setCurrentClass(cc);
        this.setCurrentPos(cp);
        this.setDescription(d);
        this.setHeading(h);

        setMainTags(new ArrayList<Tag>());
        setSubTags(new ArrayList<Tag>());

        for(Tag x:mTag){
            getMainTags().add(x);
        }

        for(Tag x:sTags){
            getSubTags().add(x);
        }
    }


    NormalQuestion(){

    }
    public static class Tag{
        private int id;
        private String tag_name;
        Tag(){}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }
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

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(String currentPos) {
        this.currentPos = Integer.parseInt(currentPos);
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

    public List<Tag> getMainTags() {
        return mainTags;
    }

    public void setMainTags(List<Tag> mainTags) {
        this.mainTags = mainTags;
    }

    public List<Tag> getSubTags() {
        return subTags;
    }

    public void setSubTags(List<Tag> subTags) {
        this.subTags = subTags;
    }

    public String getSuccessClass() {
        return successClass;
    }

    public void setSuccessClass(String successClass) {
        this.successClass = successClass;
    }

    public int getSuccessPos() {
        return successPos;
    }

    public void setSuccessPos(String successPos) {
        this.successPos = Integer.parseInt(successPos);
    }
}
