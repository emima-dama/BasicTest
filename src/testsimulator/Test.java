/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Test implements QuestionFinder{
    
    /**
     * Fields of the class
     */
    private ArrayList<Question> questions;
 
    /**
     * Methods of the class
     */
    
    /**
     * Constructor of the class
     */
    public Test(int numQuestions, QuestionBank questionBank){
        
        this.selectQuestions(numQuestions, questionBank);
    }
    
    public int getLenght(){
        
        return this.questions.size();
    }
    
    //TODO : what's the type of return? In UML diagram doesn't occur
    private void selectQuestions(int numQuestions, QuestionBank questionBank){
        
        int sizeOfSelection = 0;
        int nrOfQuestions = questionBank.getLenght();
        List<Question> questionsCopy = questionBank.getQuestions().stream().collect(Collectors.toList());
        
        if(numQuestions > nrOfQuestions){
            sizeOfSelection = nrOfQuestions;
            this.questions = new ArrayList<Question>(questionsCopy);
            System.out.printf("The question bank does not have %d questions.",numQuestions);
            System.out.printf("The test will have %d questions instead.",nrOfQuestions);
            return;
        }
        //else
        sizeOfSelection = numQuestions;
        this.questions = new ArrayList<>();
        
        //shuffle the list of questions
        Collections.shuffle(questionsCopy);
        for(Question q: questionsCopy){
            if(sizeOfSelection == 0){ //can add just "numQuestions" questions
                break;
            }
            this.questions.add(q);
            sizeOfSelection--; // decrease with 1 after a Question is added
        }
    }

    public boolean runTest(){
        
        int currentQuestion = 1;
        
        System.out.println("Welcome to your test.\n" +
                "The test will have 5 questions.\n"+
                "You may hit 'q' to quit the test any time, but progress or results will not be saved.\n" +
                "Starting your test now ...");   
        try
        {
            Thread.sleep(1000);  //delay of 1 secounds
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        
        for(Question q:this.questions){
            
            while(true){ //repeate the question while the answer is not allowed
                System.out.println("\nQuestion "+currentQuestion+":");
                System.out.println(q.getQuestionText());
                System.out.println("Answers are:");
                
                if(q instanceof MultipleChoiceQuestion){
                    MultipleChoiceQuestion mcq = (MultipleChoiceQuestion)q;
                    String[] answers = mcq.getAnswers();
                    System.out.println(" (a) " + answers[0] + "\n"+
                                        " (b) " + answers[1] + "\n"+
                                        " (c) " + answers[2] + "\n"+
                                        " (d) " + answers[3] + "\n");
                    System.out.print("\nEnter your answer: ");
                    
                    Scanner scanner = new Scanner(new InputStreamReader(System.in));
                    char answer = scanner.next().charAt(0);
                    if (answer == 'q'){
                        this.questions.clear(); //remove the test !TODO! I don't know if is correct
                        return false;
                    }
                    if(answer == 'a' || answer == 'b' || answer == 'c' || answer == 'd'){
                        //save the answer 
                        mcq.setChosenAnswer(answer);
                        //Give the feedback
                        if(mcq.isAnswerCorrect()){
                            System.out.println("Feedback: Correct!");
                        }
                        else{
                            System.out.println("Feedback: Incorrect. Correct answer was ("+mcq.getCorrectAnswer()+")");
                        }
                        currentQuestion++; //next question
                        break; //exit from while loop
                    }
                    System.out.print("\n! PLEASE ENTER ONE OF THE CHARS a, b, c, d, q !");
                    
                }else{ // q is an instance of TrueFalseQuestion
                    TrueFalseQuestion tfq = (TrueFalseQuestion)q;
                    System.out.println("  (a) True\n  (b) False");
                    System.out.print("\nEnter your answer: ");
                    
                    Scanner scanner = new Scanner(new InputStreamReader(System.in));
                    String answer = scanner.next().toLowerCase();
                    if (answer.equals("q")){
                        this.questions.clear(); //remove the test
                        return false;
                    }
                    boolean allowed = false;
                    boolean answerTF = false;
                    if(answer.equals("a")){
                        answerTF = true;
                        allowed = true; //the user type allowed answer
                    }else if(answer.equals("b")){
                        allowed = true; //the user type allowed answer
                    }
                    
                    if(!allowed){
                        System.out.print("\n! PLEASE ENTER ONE OF THE ANSWERS a, b, q !");
                    }else{ 
                        //save the answer
                        tfq.setChosenAnswer(answerTF);
                        //Give the feedback
                        if(tfq.isAnswerCorrect()){
                            System.out.println("Feedback: Correct!");
                        }
                        else{
                            System.out.println("Feedback: Incorrect. Correct answer was ("+tfq.getCorrectAnswer()+")");
                        }
                        currentQuestion++; //next question
                        break; //exit from while loop
                    }

                }
            }    
        }
        
        System.out.println("\nTest is finished");
        return true; //the Test is finished
    }
    
    public void showTestSummary(){
        
        int correctQuestions = 0;
        for(Question q:this.questions){
            if(q.isAnswerCorrect()){
                correctQuestions++;
            }
        }
        
        System.out.printf("\nYou answered %d questions correctly ot of %d",correctQuestions,this.questions.size());
        float score = (correctQuestions*100)/this.questions.size();
        System.out.println("\nYour score was " + score + "%");
    }
    
    public void saveTestResult(){
        
        //Test result file
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd-HHmmss");
        Date now = new Date();
        String resultFileName = "test-"+formatter.format(now)+".txt";
        //create the file

        //write to the file
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(resultFileName))){
            for(Question q: this.questions){
                if(q instanceof MultipleChoiceQuestion){
                    MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) q; //cast action
                    bw.write(mcq.getQuestionID()+","+mcq.getChapterNumber()+","+mcq.getCorrectAnswer()+","+mcq.getChosenAnswer());    
                }
                else{//q is an instance of TrueFalseQuestion class 
                    TrueFalseQuestion mcq = (TrueFalseQuestion) q; //cast action
                    bw.write(mcq.getQuestionID()+","+mcq.getChapterNumber()+","+mcq.getCorrectAnswer()+","+mcq.getChosenAnswer());    
                } 
                bw.newLine();
            }
            System.out.println("\nTest result saved to "+resultFileName);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //TODO
        //the summary file
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("test-summary.txt",true))){ //If the file exists, append to it
            
            bw.newLine();
            bw.write(resultFileName);
            System.out.println("Test record added to test-summary.txt.");
        }catch(IOException e){
            e.printStackTrace();
        }
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

