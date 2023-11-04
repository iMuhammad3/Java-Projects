public class QuizQuestion {
    private int id;
    private String question;
    private String answer;
    private String[] options;

    QuizQuestion(int id, String question, String answer, String[] options){
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.options = options;
    }
    public int getId() {
        return id;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
    public String[] getOptions() {
        return options;
    }

    public String format(){
        StringBuilder optionsStr = new StringBuilder();
        String letters = "abcd";
        for(int i = 0;i < this.options.length;i++){
            optionsStr.append(letters.charAt(i)).append(". ").append(this.options[i]).append("\n");
        }
        return this.id + ". " + this.question + "\n" + optionsStr;
    }

}
