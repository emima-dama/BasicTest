/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.util.ArrayList;

/**
 *
 * @author Damaris
 */
public class QuestionBank {
    
    /**
     * Attributes of class
     */
    public static int[] CHAPTERS = new int[]{8,9,10,11,13,14,15,16};
    private String MULTIPLE_CHOICE_FILE = "multiple-choice-questions.txt";
    private int MULTIPLE_CHOICE_FIELDS = 8;
    private int TRUE_FALSE_FIELDS = 4;
    private ArrayList<Question> questions;
    
    /**
     * Functions of class
     */
    
    /**
     * Constructor 
     */
    public QuestionBank(){
        
        //TODO
    }
    
    public int getLenght(){
        
        //I'm not sure
        return this.questions.size();
    }
    
    public Question getQuestion(int index){
        
        //I'm not sure
        return this.questions.get(index);
    }
    
    public void loadMultipleChoiceQuestions(){
        
        //TODO
    }
    
    public void loadTrueFalseQuestions(){
        
        //TODO
    }
    
    
}
