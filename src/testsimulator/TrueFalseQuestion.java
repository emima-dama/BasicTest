/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

public class TrueFalseQuestion extends Question {

    /**
     * Fields of class
     */
    private boolean correctAnswer; // TRUE / FALSE
    private boolean chosenAnswer;
    
    /**
     * Methods of class
     */
    
    /**
     * Constructor 
     */
    
    public TrueFalseQuestion(String questionID, int chapterNumber, String questionText, boolean correctAnswer) {
        
        super(questionID, chapterNumber, questionText);
        this.correctAnswer = correctAnswer;
        this.chosenAnswer = true; //default value
    }
  
    @Override
    public boolean isAnswerCorrect() {
        
        return this.chosenAnswer == this.correctAnswer;
    }
  
    public boolean getCorrectAnswer(){
        
        return this.correctAnswer;
    }
    
    public boolean getChosenAnswer(){
        
        return this.chosenAnswer;
    }
    
    public void setChosenAnswer(boolean chosenAnswer){
        
        this.chosenAnswer = chosenAnswer;
    }
}
