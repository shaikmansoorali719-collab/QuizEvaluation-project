import java.io.*;
import java.util.*;

public class ReportGenerator {
    public static void generateExcelCSV(List<Student> students, List<MCQ> questions) 
            throws IOException {
        
        try (PrintWriter pw = new PrintWriter(new FileWriter("report.csv"))) {
            
       
            pw.println("QUIZ EVALUATION REPORT");
            pw.println("Generated: " + new Date());
            pw.println();
            
            pw.println("STUDENT SCORES");
            pw.println("StudentID,Total Score,Percentage,Grade,Status");
            pw.println("========================================");
            
            int maxScore = questions.stream().mapToInt(MCQ::getMarks).sum();
            
            students.stream()
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .forEach(s -> {
                    double percent = maxScore > 0 ? (double) s.getScore() / maxScore * 100 : 0;
                    String grade = percent >= 80 ? "A+" : percent >= 70 ? "A" : 
                                  percent >= 60 ? "B" : percent >= 50 ? "C" : "F";
                    String status = percent >= 50 ? "PASS" : "FAIL";
                    pw.println(String.format("%s,%.0f,%.1f%%,%s,%s", 
                        s.getId(), (double)s.getScore(), percent, grade, status));
                });
            
            pw.println();
            
         
            pw.println("QUESTION DIFFICULTY ANALYSIS");
            pw.println("QID,Marks,Correct Answer,Resp Count,%Correct,Difficulty,Recommendation");
            pw.println("=====================================================");
            
            for (MCQ q : questions) {
                long totalResp = students.stream()
                    .filter(s -> s.getResponses().containsKey(q.getId())).count();
                long correct = students.stream()
                    .filter(s -> s.getResponses().containsKey(q.getId()))
                    .filter(s -> q.isCorrect(s.getResponses().get(q.getId()))).count();
                
                double pctCorrect = totalResp > 0 ? (double) correct / totalResp * 100 : 0;
                String difficulty = pctCorrect < 40 ? "VERY HARD" : 
                                   pctCorrect < 60 ? "HARD" :
                                   pctCorrect < 80 ? "MEDIUM" : "EASY";
                String recommendation = pctCorrect < 50 ? "REVISE" : 
                                       pctCorrect > 90 ? "SIMPLIFY" : "GOOD";
                
                pw.println(String.format("%s,%d,%s,%d,%.1f%%,%s,%s",
                    q.getId(), q.getMarks(), q.getCorrectAnswer(),
                    (int)totalResp, pctCorrect, difficulty, recommendation));
            }
            
            pw.println();
            pw.println("Total Questions: " + questions.size());
            pw.println("Average Difficulty: Analysis Complete");
        }
    }
}
