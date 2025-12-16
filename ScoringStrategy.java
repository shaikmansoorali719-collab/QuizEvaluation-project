public interface ScoringStrategy {
    int calculateScore(Question q, boolean isCorrect);
}

class StandardScoring implements ScoringStrategy {
    public int calculateScore(Question q, boolean isCorrect) {
        return isCorrect ? q.getMarks() : -q.getPenalty();
    }
} 
