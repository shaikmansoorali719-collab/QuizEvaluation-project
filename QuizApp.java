import java.util.*;

public class QuizApp {
    public static void main(String[] args) {
        System.out.println("=== Quiz Evaluation & Analytics App ===");
        System.out.println("📥 Loading CSV inputs → 📊 Generating report.csv");
        
        try {
          
            System.out.println("1. Loading data...");
            Map<String, String> answers = CSVLoader.loadAnswers("answers.csv");
            List<MCQ> questions = CSVLoader.loadQuestions("questions.csv", answers);
            List<Student> students = CSVLoader.loadResponses("responces.csv");
            
            System.out.println("    Questions: " + questions.size());
            System.out.println("    Students: " + students.size());
            
            
            System.out.println("2. Evaluating...");
            Evaluator evaluator = new Evaluator(new StandardScoring());
            evaluator.evaluate(students, questions);
            
          
            System.out.println("3. Generating report.csv...");
            ReportGenerator.generateExcelCSV(students, questions);
            
         
            showResults(students, questions);
            
            
            
        } catch (Exception e) {
            System.err.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void showResults(List<Student> students, List<MCQ> questions) {
        int maxScore = questions.stream().mapToInt(MCQ::getMarks).sum();
        
        System.out.println("\n=== SUMMARY ===");
        System.out.println("📊 Total Questions: " + questions.size());
        System.out.println("🎯 Max Score: " + maxScore);
        
        System.out.println("\n🏆 TOP 5 PERFORMERS:");
        students.stream()
            .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
            .limit(5)
            .forEach(s -> {
                double pct = maxScore > 0 ? (double) s.getScore() / maxScore * 100 : 0;
                System.out.printf("%s: %d/%.0f (%.1f%%)\n", s.getId(), s.getScore(), 
                                (double)maxScore, pct);
            });
    }
}
