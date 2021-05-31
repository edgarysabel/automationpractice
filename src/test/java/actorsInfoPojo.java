import java.util.ArrayList;
import java.util.Arrays;

public class actorsInfoPojo {

    // private variables or data members of pojo class
    ArrayList<String> character = new ArrayList<>();
    ArrayList<String> actor = new ArrayList<>();

    // Getter and setter methods
    public String getCharacter(int position) {
        return character.get(position);
    }
    public void setCharacter(String character) {
        this.character.add(character);
    }
    public String getActor(int position) {
        return actor.get(position);
    }
    public void setActor(String actor) {
        this.actor.add(actor);
    }
}