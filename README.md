
# Quiz Evaluation & Analytics

A Java command-line application that evaluates multiple-choice quiz responses using CSV files and generates student performance reports.

## Features

- Loads questions, answer keys and student responses from CSV files.
- Calculates student scores with configurable marks and penalties.
- Displays the top five performers.
- Generates student performance reports.
- Analyzes question difficulty.
- Assigns grades and pass/fail status.

## Technologies

- Java
- CSV File Handling
- Object-Oriented Programming
- Java Collections and Streams

## Project Workflow

1. Load answers from `answers.csv`.
2. Load questions from `questions.csv`.
3. Load student responses from `responces.csv`.
4. Evaluate student answers.
5. Generate `report.csv`.

## Input File Formats

**answers.csv**
```csv
questionID,correctAnswer
Q1,A
Q2,Y
```

**questions.csv**
```csv
questionID,marks,penalty
Q1,4,1
Q2,3,1
```

**responces.csv**
```csv
studentID,questionID,response
S001,Q1,A
S001,Q2,X
```

## How to Run

Make sure Java JDK is installed.

Compile:
```bash
javac *.java
```

Run:
```bash
java QuizApp
```

## Generated Report

The application generates `report.csv`, containing:

- Student scores and percentages
- Grades and pass/fail results
- Question difficulty analysis
- Performance recommendations

## Main Java Files

| File | Purpose |
|---|---|
| QuizApp.java | Main application |
| CSVLoader.java | CSV input handling |
| Question.java | Question abstraction |
| MCQ.java | Multiple-choice questions |
| Student.java | Student information |
| Evaluator.java | Score calculation |
| ReportGenerator.java | Report generation |
| ScoringStrategy.java | Scoring rules |

