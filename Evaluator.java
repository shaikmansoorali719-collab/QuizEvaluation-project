import java.util.*;

public class Evaluator {
    private ScoringStrategy strategy;
    
    public Evaluator(ScoringStrategy strategy) {
        this.strategy = strategy;
    } 
    
    public void evaluate(List<Student> students, List<MCQ> questions) {
        for (Student student : students) {
            int score = 0;
            
            for (Map.Entry<String, String> response : student.getResponses().entrySet()) {
                String qid = response.getKey();
                String answer = response.getValue();
                
                Optional<MCQ> question = questions.stream()
                    .filter(q -> q.getId().equals(qid))
                    .findFirst();
                
                if (question.isPresent()) {
                    MCQ q = question.get();
                    boolean isCorrect = q.isCorrect(answer);
                    score += strategy.calculateScore(q, isCorrect);
                }
            }
            student.setScore(score);
        }
        System.out.println("✅ Evaluation completed!");
    }
}
