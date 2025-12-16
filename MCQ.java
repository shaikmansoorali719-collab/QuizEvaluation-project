public class MCQ extends Question {
    private String correctAnswer;
    
    public MCQ(String id, int marks, int penalty, String correctAnswer) {
        super(id, marks, penalty);
        this.correctAnswer = correctAnswer;
    }
    
    @Override
    public boolean isCorrect(String response) {
        return correctAnswer != null && 
               correctAnswer.equalsIgnoreCase(response.trim());
    }
    
    public String getCorrectAnswer() { return correctAnswer; }
} 

