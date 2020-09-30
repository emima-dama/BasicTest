/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.util.ArrayList;

public interface QuestionFinder {
    
    boolean containsQuestion(String questionID, ArrayList<Question> questions);
}
