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
public class TrueFalseQuestion extends Question {

    /**
     * Attributes of class
     */
    private boolean correctAnswer;
    private boolean chosenAnswer;
    
    /**
     * Functions of class
     */
    
    /**
     * Constructor 
     */
    
    public TrueFalseQuestion(String questionID, int chapterNumber, String questionText, boolean correctAnswer) {
        
        super(questionID, chapterNumber, questionText);
        this.correctAnswer = correctAnswer;
    }
  
    @Override
    public boolean isAnswerCorrect() {
        
        //I'm not sure ( TODO )
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
