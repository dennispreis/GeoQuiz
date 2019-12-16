/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author User
 */
public interface RecordDaoInterface
{
    public void storeAnswer(int recordId,ArrayList<Integer> questionId,ArrayList<String> answers);
    
    public Map<Integer,String> getAnswerList(int recordId);
}
