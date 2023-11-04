public class User {
    User() {
    }
    private int score = 0;

    public int wrongAnswers(int numOfQuestions) {
        return numOfQuestions - score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
