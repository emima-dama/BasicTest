/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TestSimulator {

    public static int getSelection(){
        
        int selection = 0;
        boolean enterSuccess = false;
        
        while(!enterSuccess){
            
            System.out.println();
            System.out.println();
            System.out.print("Welcome to the Testsimulator program menu.");
            System.out.println();
            System.out.print("Select from one of the following options.");
            System.out.print("\n(1) New test. \n(2) Test summary. \n(3) Exit. \nEnter your selection: ");
            Scanner scanner = new Scanner(new InputStreamReader(System.in));    
        
            try{
                selection = scanner.nextInt();
                if(selection <1 || selection >3){
                    throw new Exception("The number isn't between 1 and 3");
                }
                enterSuccess = true; // the enter of user is accepted
            }catch(Exception e){
                System.out.print("\n! PLEASE ENTER A NUMBER BETWEEN  1 AND 3 !");
            }    
        }
        
        return selection;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        QuestionBank qb = new QuestionBank();
  
        while(true){
            
            int selection = getSelection();
            if(selection == 3){
                System.out.println();
                System.out.println("Thank you for using TestSimulator!");
                break;
            }
                
            if(selection == 1){
                //create a Test
                Test test = new Test(5,qb);
                if(test.runTest()){
                    test.showTestSummary();
                    test.saveTestResult();
                }
                
            }
            else{ //selection is 2
                TestSummary ts = new TestSummary();
                ts.summarisePerformance();
                ts.reportPerformance();
            }
            
        }
    }
    
}
