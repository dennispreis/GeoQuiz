/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Question;
import java.util.List;

/**
 *
 * @author Tunjing
 */
public interface QuestionDaoInterface
{
    public List<Question> getPractice();
    public List<Question> getPracticeByType(String type);
    public List<Question> getPracticeByRegion(String region);
    public List<Question> getPracticeByLevel();
    public boolean addQuestion(Question q);
    public boolean updateQuestion(int id,String field,String value);
    public boolean deleteQuestion(int id);
}
