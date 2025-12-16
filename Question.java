public abstract class Question {
    protected String id;
    protected int marks, penalty;
    
    public Question(String id, int marks, int penalty) {
        this.id = id; this.marks = marks; this.penalty = penalty;
    }
    
    public abstract boolean isCorrect(String response);
    public String getId() { return id; }
    public int getMarks() { return marks; }
    public int getPenalty() { return penalty; }
}
 