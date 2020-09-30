/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.util.ArrayList;

public class Test implements QuestionFinder{
    
    /**
     * Fields of class
     */
    private ArrayList<Question> questions;
 
    /**
     * Methods of class
     */
    
    /**
     * Constructor 
     */
    public Test(int numQuestions, QuestionBank questionBank){
        
        //TODO
    }
    
    public int getLenght(){
        
        //I'm not sure ( TODO )
        return this.questions.size();
    }
    
    //TODO : what's the type of return? In UML diagram doesn't occur
    private void selectQuestions(int numQuestions, QuestionBank questionBank){
        
    }

    public boolean runTest(){
        
        //TODO
        return false; //default
    }
    
    public void showTestSummary(){
        
        //TODO : print sth 
    }
    
    public void saveTestResult(){
        
        //TODO
    }

    @Override
    public boolean containsQuestion(String questionID, ArrayList<Question> questions) {
        
        for(Question q:questions){
            if(q.getQuestionID().equals(questionID))
                return true;
        }
        return false;
    }
}

