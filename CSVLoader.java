import java.io.*;
import java.util.*;

public class CSVLoader {
    
    public static Map<String, String> loadAnswers(String filePath) throws IOException {
        Map<String, String> answers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); 
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    answers.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
        return answers;
    }
    
    public static List<MCQ> loadQuestions(String filePath, Map<String, String> answers) throws IOException {
        List<MCQ> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); 
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length >= 3) {
                    String qid = parts[0].trim();
                    int marks = Integer.parseInt(parts[1].trim());
                    int penalty = Integer.parseInt(parts[2].trim());
                    String correctAns = answers.get(qid);
                    if (correctAns != null) {
                        questions.add(new MCQ(qid, marks, penalty, correctAns));
                    }
                }
            }
        }
        return questions;
    }
    
    public static List<Student> loadResponses(String filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); 
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length >= 3) {
                    String studentId = parts[0].trim();
                    String qid = parts[1].trim();
                    String response = parts[2].trim();
                    
                    Student student = students.stream()
                        .filter(s -> s.getId().equals(studentId))
                        .findFirst().orElse(null);
                    if (student == null) {
                        student = new Student(studentId);
                        students.add(student);
                    }
                    student.addResponse(qid, response);
                }
            }
        }
        return students;
    }
}

