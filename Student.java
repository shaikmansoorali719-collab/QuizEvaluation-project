import java.util.*;

public class Student {
    private String id;
    private Map<String, String> responses = new HashMap<>();
    private int score;
    
    public Student(String id) { this.id = id; }
    
    public void addResponse(String qid, String response) {
        responses.put(qid, response);
    }
    
    public String getId() { return id; }
    public Map<String, String> getResponses() { return responses; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
} 
