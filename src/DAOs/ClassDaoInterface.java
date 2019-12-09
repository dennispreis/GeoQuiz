/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.util.List;
import DTOs.Class ;
/**
 *
 * @author User
 */
public interface ClassDaoInterface
{
  //  calculateAverage
    public List<Class> getClassByTeacherId(int id);
}
