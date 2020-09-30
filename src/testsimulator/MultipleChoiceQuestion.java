/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

public class MultipleChoiceQuestion extends Question {
    
    /**
     * Fields of class
     */
    private String[] answers = new String[4];  
    private char correctAnswer; // a/b/c/d
    private char chosenAnswer;  
    
    /**
     * Methods of class
     */
    
    /**
     * Constructor 
     */
    public MultipleChoiceQuestion(String questionID, int chapterNumber, String questionText, String answer1, String answer2, String answer3, String answer4, char correctAnswer) {
        
        super(questionID, chapterNumber, questionText);
        
        this.answers[0] = answer1;
        this.answers[1] = answer2;
        this.answers[2] = answer3;
        this.answers[3] = answer4;
        
        this.correctAnswer = correctAnswer;
        this.chosenAnswer = 'a'; //default value
    }

    @Override
    boolean isAnswerCorrect() {
        
        return this.chosenAnswer == this.correctAnswer;
    }
    
    public String[] getAnswers(){
        
        return this.answers;
    }
    
    public char getCorrectAnswer(){
        
        return this.correctAnswer;
    }
    
    public char getChosenAnswer(){
        
        return this.chosenAnswer;
    }
    
    public void setChosenAnswer(char chosenAnswer){
        
        this.chosenAnswer = chosenAnswer;
    }
    
}
