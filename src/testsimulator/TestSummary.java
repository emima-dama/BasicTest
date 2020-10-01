/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestSummary {
    
    /**
     * Fields of class
     */
    public static String FILENAME_SUMMARY = "test-summary.txt";
    private int TEST_REPORT_FIELDS = 4;
    private int[] chapterQuestionsAnswered = new int[8];
    private int[] chapterQuestionsCorrect = new int[8];
 
    /**
     * Methods of class
     */
    
    /**
     * Constructor 
     */
    public TestSummary(){
        //TODO
    }
    
    public void summarisePerformance(){
        
        //open "test-summary.txt" file 
        try(BufferedReader br = new BufferedReader(new FileReader(FILENAME_SUMMARY))){
            
            String line = br.readLine();
            while(line != null){//open each file
                                
                if(line.equals("")){ //blank row
                    line = br.readLine();
                    continue; //back to while loop
                }
                     
                try(BufferedReader current_br = new BufferedReader(new FileReader(line))){
                    
                    String reportLine = current_br.readLine();
                    while(reportLine != null){
                        //0 - questionID ; 1 - chapterNumber ; 2 - correctAnswer ; 3 - chosenAnswer
                        String[] reports = reportLine.split(",");
                        
                        if(reports.length != this.TEST_REPORT_FIELDS){//File row has incorrect number of fields.
                            throw new Exception("The file " + line + " has a row with incorrect numberr of fields !");
                        }

                        //Chapters equivalent with vector's indexes:
                        //index 0 - > chapter 8 ; index 1 - > chapter 9 ; index 2 - > chapter 10 ; index 3 - > chapter 11 ; index 4 - > chapter 13 ; index 4 - > chapter 14 ; ... ; index 7 - > chapter 16 
                        //increse with 1 the number of questions answered for the chapter reports[1]
                        Integer currentChapter = Integer.parseInt(reports[1]);
                        if(currentChapter < 12 ){
                            int index = currentChapter - 8;  // 8,9,10,11 -> 0,1,2,3
                            if(index < 0 || index > 3){
                                throw new Exception("Number "+currentChapter+" is not one of the allowed as chapter numbers");
                            }
                            
                            this.chapterQuestionsAnswered[index] ++;
                            if(reports[2].equals(reports[3])){
                                //increse with 1 the number of correct questions for the chapter reports[1]
                                this.chapterQuestionsCorrect[index] ++;
                            }
                        }else if(currentChapter > 12){
                            int index = currentChapter - 9;  // 13,14,15,16 -> 4,5,6,7
                            if(index > 7){
                                throw new Exception("Number "+currentChapter+" is not one of the allowed as chapter numbers");
                            }
                            
                            this.chapterQuestionsAnswered[index] ++;
                            if(reports[2].equals(reports[3])){
                                //increse with 1 the number of correct questions for the chapter reports[1]
                                this.chapterQuestionsCorrect[index] ++;
                            }
                        }
                                       
                        reportLine = current_br.readLine();
                    }
                }catch(IOException e){//when file cannot be open 
                    System.out.println(">>>>> ERROR <<<<<");
                    System.out.println("The file "+line+" cannot be open\n"+e.getMessage());
                    System.exit(0); //exit the program
                }catch(NumberFormatException e){//when chapterNumber field is not numeric
                    System.out.println(">>>>> ERROR <<<<<");
                    System.out.println("One of the chapter numbers from "+line+" file is not numeric");
                    System.out.println("("+e.getMessage()+")");
                    System.exit(0); //exit the program
                }catch(Exception e){
                    System.out.println(">>>>> ERROR <<<<<");
                    System.out.println(e.getMessage());
                    System.exit(0); //exit the program
                }
                line = br.readLine();
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void reportPerformance(){
        
        System.out.println();
        System.out.println("Performace report ...");
        System.out.println();
        System.out.printf("%8s%9s%9s%9s", "Chapter","Correct","Answered","Percent");
        System.out.println();
        System.out.printf("%1$-9s%1$-9s%1$-9s%1$-9s","--------");
        
        int sumCorrect = 0;
        int sumAnswered = 0;
        for(int i=0;i<8;i++){
            
            int correctAnswers = this.chapterQuestionsCorrect[i];
            int answers = this.chapterQuestionsAnswered[i];
            double percent = 0.00;
            if(answers !=0 ){ //if exists answers
                percent = (correctAnswers*100)/answers;
            }
            int chapter = (i < 4) ? i+8 : i+9;
            
            sumCorrect += correctAnswers;
            sumAnswered += answers;
            
            System.out.println();
            System.out.printf("%8d%9d%9d%9.2f", chapter,correctAnswers,answers,percent);
        }
        
        double percent = 0;
        if(sumAnswered != 0){
            percent = (sumCorrect*100)/sumAnswered;
        }
        System.out.println();
        System.out.printf("%1$-9s%1$-9s%1$-9s%1$-9s","--------");
        System.out.println();
        System.out.printf("%8s%9d%9d%9.2f","Total",sumCorrect,sumAnswered,percent);
        System.out.println();
        
    }
}
