/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author User
 */
public class Test
{
    private int test_id;
    private String test_name;
    private int paper_id;

    public Test(int test_id, String test_name, int paper_id)
    {
        this.test_id = test_id;
        this.test_name = test_name;
        this.paper_id = paper_id;
    }

    public int getTest_id()
    {
        return test_id;
    }

    public void setTest_id(int test_id)
    {
        this.test_id = test_id;
    }

    public String getTest_name()
    {
        return test_name;
    }

    public void setTest_name(String test_name)
    {
        this.test_name = test_name;
    }

    public int getPaper_id()
    {
        return paper_id;
    }

    public void setPaper_id(int paper_id)
    {
        this.paper_id = paper_id;
    }
    
}
