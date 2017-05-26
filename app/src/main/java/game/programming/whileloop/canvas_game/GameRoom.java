package game.programming.whileloop.canvas_game;

import java.util.List;
import java.util.Map;

/**
 * Created by prabushitha on 5/6/17.
 */

public class GameRoom {
    private int opponentid;
    private int playerid;
    private List<Integer> questions;
    private String status;
    private int roomid;
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }






    public int getOpponentid() {
        return opponentid;
    }

    public void setOpponentid(int opponentid) {
        this.opponentid = opponentid;
    }

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public List<Integer> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Integer> questions) {
        this.questions = questions;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
