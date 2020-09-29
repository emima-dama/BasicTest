/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

/**
 *
 * @author Damaris
 */
public class MulipleChoiceQuestion extends Question {
    
    /**
     * Attributes of class
     */
    private String[] answers = new String[4];
    private char correctAnswer;
    private char chosenAnswer;

    
    /**
     * Functions of class
     */
    
    /**
     * Constructor 
     */
    public MulipleChoiceQuestion(String questionID, int chapterNumber, String questionText, String answer1, String answer2, String answer3, String answer4, char correctAnswer) {
        
        super(questionID, chapterNumber, questionText);
        
        this.answers[0] = answer1;
        this.answers[1] = answer2;
        this.answers[2] = answer3;
        this.answers[3] = answer4;
        
        this.correctAnswer = correctAnswer;
    }

    @Override
    boolean isAnswerCorrect() {
        
        //I'm not sure ( TODO )
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
