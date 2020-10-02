/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class QuestionBank implements QuestionFinder{
    
    /**
     * Fields of the class
     */
    public static int[] CHAPTERS = new int[]{8,9,10,11,13,14,15,16};
    private String MULTIPLE_CHOICE_FILE = "multiple-choice-questions.txt";
    private String TRUE_FALSE_FILE = "true-false-questions.txt";
    private int MULTIPLE_CHOICE_FIELDS = 8;
    private int TRUE_FALSE_FIELDS = 4;
    private ArrayList<Question> questions;
    
    /**
     * Methods of the class
     */
    
    /**
     * Constructor
     * 
     * Calls loadMultipleChoiceQuestions() and loadTrueFalseQuestions() helper methods.
     * Prints a message in the form: Loaded all N questions from the question bank.
     */
    public QuestionBank(){
        
        questions = new ArrayList<Question>();
        
        this.loadMultipleChoiceQuestions();
        this.loadTrueFalseQuestions();
        
        System.out.print("Loaded all "+this.getLenght()+" questions from the question bank.");
    }
    
    /**
     * 
     * @return  int:  the length of the “questions” member
     */
    public int getLenght(){
        
        return this.questions.size();
    }
    
    /**
     * 
     * @param index
     * @return Question:  at the given index
     */
    public Question getQuestion(int index){
        
        return this.questions.get(index);
    }
    
    //A function that isn't in UML diagram, but I need it for random selection
    public ArrayList<Question> getQuestions(){
        
        return this.questions;
    }
    
    /**
     * Loads the multiple-choice questions from the data file into the “questions” member
     */
    public void loadMultipleChoiceQuestions(){
        
        try(BufferedReader br = new BufferedReader(new FileReader(this.MULTIPLE_CHOICE_FILE))){
            
            String line = br.readLine();
            while(line != null){ // loop until all lines are read
                
                String [] fields = line.split(",");
                if(fields.length != this.MULTIPLE_CHOICE_FIELDS)
                    throw new Exception("One of the lines from "+this.MULTIPLE_CHOICE_FILE+" file has incorrect number of fields (need to be 8)");
                
                //convert fields to correct type:
                //convert "cn" variable to int
                int cn;
                cn = Integer.parseInt(fields[1]); //chapterNumber field was not numeric throws NumberFormatError

                //verify if cn is in CHAPTERS 
                boolean exists = false;
                for(int c: CHAPTERS){
                    if(c == cn){
                        exists = true;
                        break; //since it found, the search can be ended
                    }
                }
                if (!exists){ //if chapterNumber field was not one of the allowed chapter numbers
                    throw new Exception("The chapterNumber field need to be one of numbers: 8,9,10,11,13,14,15,16\nNOT: "+cn+" that is in file "+this.MULTIPLE_CHOICE_FILE);
                }                    
                
                //change to lowercase for consistency and convert "ca" variable to char
                char ca = fields[7].toLowerCase().charAt(0); //change to lowercase for consistency
                if(!(ca == 'a') && !(ca  == 'b') && !(ca == 'c') && !(ca == 'd')){
                    throw new Exception("The correctAnswer field need to be one of 'a' to 'd'\nNOT: "+ ca);
                }
                
                //verify if quiestionID is duplicate
                if(this.containsQuestion(fields[0], this.questions)){
                    throw new Exception("The questionID "+fields[0]+" is already exists in list of questions");
                }
                
                //MultipleChoiceQuestion(String questionID, int chapterNumber, String questionText, String answer1, String answer2, String answer3, String answer4, char correctAnswer)
                MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(fields[0],cn,fields[2],fields[3],fields[4],fields[5],fields[6],ca);
                this.questions.add(mcq);
                
                line = br.readLine();
            }
        }catch(IOException e){ //when the file cannot be open 
            System.out.println(">>>>> ERROR <<<<<");
            System.out.println("The file "+this.MULTIPLE_CHOICE_FILE+" cannot be open\n"+e.getMessage());
            System.exit(0); //exit the program
        }catch(NumberFormatException e){ // when chapterNumber field is not numeric
            System.out.println(">>>>> ERROR <<<<<");
            System.out.println("One of the chapter numbers from "+this.TRUE_FALSE_FILE+" file is not numeric");
            System.out.println("("+e.getMessage()+")");
            System.exit(0); //exit the program
        }catch(Exception e){
            System.out.println(">>>>> ERROR <<<<<");
            System.out.println(e.getMessage());
            System.exit(0); //exit the program
        }
    }
    
    /**
     * Loads the true/false questions from the data file into the “questions” member.
     */
    public void loadTrueFalseQuestions(){
        
        try(BufferedReader br = new BufferedReader(new FileReader(this.TRUE_FALSE_FILE))){
            
            String line = br.readLine();
            while(line != null){ // loop until all lines are read
                
                String [] fields = line.split(",");
                if(fields.length != this.TRUE_FALSE_FIELDS)
                    throw new Exception("One of the lines from "+this.TRUE_FALSE_FILE+" file has incorrect number of fields (need to be 4)");
                
                //convert "cn" variable to int
                int cn;
                cn = Integer.parseInt(fields[1]); //chapterNumber field was not numeric

                //verify if cn is in CHAPTERS 
                boolean exists = false;
                for(int c: CHAPTERS){
                    if(c == cn){
                        exists = true;
                        break; //since it found, the search can be ended
                    }
                }
                if (!exists){ //if chapterNumber field was not one of the allowed chapter numbers
                    throw new Exception("The chapterNumber field need to be one of numbers: 8,9,10,11,13,14,15,16\nNOT: "+cn+" that is in file "+this.TRUE_FALSE_FILE);
                }                    
                
                if(!(fields[3].toLowerCase().equals("true") || fields[3].toLowerCase().equals("false"))){
                        throw new Exception("The correctAnswer field need to be \"true\" or \"false\"\n NOT: "+fields[3]);
                }
                //change to lowercase for consistency and convert "ca" variable to boolean
                boolean ca = Boolean.parseBoolean(fields[3].toLowerCase());
                //TODO check if throw error if is not true/false
                
                //verify if quiestionID is duplicate
                if(this.containsQuestion(fields[0], this.questions)){
                    throw new Exception("The questionID "+fields[0]+" is already exists in list of questions");
                }
                
                //MultipleChoiceQuestion(String questionID, int chapterNumber, String questionText, String answer1, String answer2, String answer3, String answer4, char correctAnswer)
                TrueFalseQuestion tfq = new TrueFalseQuestion(fields[0],cn,fields[2],ca);
                this.questions.add(tfq);
                
                line = br.readLine();
            }
        }catch(IOException e){
            System.out.println(">>>>> ERROR <<<<<");
            System.out.println("The file "+this.TRUE_FALSE_FILE+" cannot be open\n"+e.getMessage());
            System.exit(0); //exit the program
        }catch(NumberFormatException e){
            System.out.println(">>>>> ERROR <<<<<");
            System.out.println("One of the chapter numbers from "+this.TRUE_FALSE_FILE+" file is not numeric");
            System.out.println("("+e.getMessage()+")");
            System.exit(0); //exit the program
        }catch(Exception e){
            System.out.println(">>>>> ERROR <<<<<");
            System.out.println(e.getMessage());
            System.exit(0); //exit the program
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
