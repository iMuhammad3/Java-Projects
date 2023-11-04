import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start Quiz!");
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        Date startTime = new Date();
        QuizQuestion[] questions = getQuestions();

        for(int i = 0;i < questions.length;i++){
            System.out.println(questions[i].format());
            String choice = scanner.next();
            if(choice.equals(questions[i].getAnswer())) user.setScore(user.getScore() + 1);
        }

        long timeTaken = new Date().getTime() - startTime.getTime();

        System.out.println("You scored: " + user.getScore() + " out of " + questions.length + " 🥳");
        System.out.println("You failed " + user.wrongAnswers(questions.length) + " questions");
        System.out.println("It took you " + (timeTaken/1000) + " seconds!");
    }

    static QuizQuestion[] getQuestions(){
        QuizQuestion[] questions = new QuizQuestion[]{
                new QuizQuestion(1, "What is the most commonly used programming language for web development?", "c", new String[]{"Java", "Python", "JavaScript", "C++"}),
                new QuizQuestion(2, "Which programming language is known for its speed and performance and is often used for system-level programming?", "d", new String[]{"Java", "Python", "C", "C++"}),
                new QuizQuestion(3, "Which data structure follows the Last-In-First-Out (LIFO) principle?", "d", new String[]{"Queue", "Array", "Linked List", "Stack"}),
                new QuizQuestion(4, "Which data type is used to represent a single character in most programming languages?", "c", new String[]{"int", "float", "char", "string"}),
                new QuizQuestion(5, "What is the name of the process where a program converts high-level code into machine code?", "b", new String[]{"Interpretation", "Compilation", "Debugging", "Execution"}),
                new QuizQuestion(6, "In Java, which keyword is used to define a subclass that inherits properties and behaviors from a superclass?", "c", new String[]{"implements", "inherits", "extends", "inheritsFrom"})
        };
        return questions;
    }
}

// 1. start quiz!
// 2. *loads quiz questions*
// 3. *user enters an answer*
// 4. *save users answer and score*
// 5. *next question and repeat 3 & 4*
// 6. *if no more questions, end quiz and display details(user score, etc)*