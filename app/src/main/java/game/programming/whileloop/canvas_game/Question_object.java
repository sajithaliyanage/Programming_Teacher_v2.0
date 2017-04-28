package game.programming.whileloop.canvas_game;

/**
 * Created by whileloop on 3/23/16.
 */
public class Question_object {
    String keywords_id [],keywords [],variable_id [],variable [],answer_sequence,question_topic,question_desc,start_node,promotion_node,punishment_node,promotion_class,punishment_class;
    int question_id;

    public Question_object(String[] keywords_id,String[] keyword_array,String[] variable_id,String[] variable_array,String question_topic,String question_desc,String answer_sequence, int question_id,
                           String start_node,String promotion_node,String punishment_node,String promotion_class, String punishment_class) {
        this.keywords_id= keywords_id;
        this.keywords = keyword_array;
        this.variable_id = variable_id;
        this.variable = variable_array;
        this.answer_sequence = answer_sequence;
        this.question_id = question_id;
        this.question_topic = question_topic;
        this.question_desc = question_desc;
        this.start_node = start_node;
        this.promotion_node = promotion_node;
        this.punishment_node = punishment_node;
        this.promotion_class = promotion_class;
        this.punishment_class = punishment_class;
    }
}
