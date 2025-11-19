/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fns;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class StudentDBOP {
   public boolean isStudentTableCreated() {
    boolean flag = false;
    try {
        DBDriver dbd = new DBDriver();
        Statement st = dbd.getStatement();

        String query = "CREATE TABLE IF NOT EXISTS student_info (name VARCHAR(20), dob VARCHAR(20), mobile VARCHAR(15), email VARCHAR(60), department VARCHAR(50), college VARCHAR(100), username VARCHAR(20) PRIMARY KEY, pass VARCHAR(20), datet_ime VARCHAR(30))";
        st.execute(query);
        
        dbd.st.close();
        dbd.conn.close();
        flag = true;
    } 
    catch (Exception ex) {
        System.out.println("Exception in isStudentTableCreated(): " + ex);
        flag = false;
    }
    return flag;
}

 public boolean isStudentCreated(String name, String dob, String mobno, String email,String dept,String college, String username, String password, String date_time) {
    boolean flag = false;

    try {
        // 🧩 Step 1: Create table if not exists
        if (!isStudentTableCreated()) {
            System.out.println("Student table creation failed.");
            return false;
        }

        // 🧩 Step 2: Proceed with insertion
        DBDriver dbd = new DBDriver();
        Statement st = dbd.getStatement();
        
        String query = "INSERT INTO student_info VALUES ('" + name + "','" + dob + "','" + mobno + "','" + email + "','" + dept + "','" + college + "','" + username + "','" + password + "','" + date_time + "')";
        
        if (st.executeUpdate(query) > 0)
            flag = true;

        dbd.st.close();
        dbd.conn.close();

    } catch (Exception ex) {
        System.out.println("Exception at StudentDBOP in isStudentCreated(): " + ex);
        flag = false;
    }

    return flag;
}
   
 public boolean isStudentExisted(String username,String password)
{
    boolean flag=false;
    try
    {
        DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        String query="Select * from student_info where username='"+username+"'and pass='"+password+"'";
        System.out.println("Query is "+query);
        ResultSet rs=st.executeQuery(query);
        if(rs.next())
            flag=true;
    }
    catch(Exception e)
    {
        System.out.println("Exception at StudentDBOP with methods isStudentExisted()"+e);
        flag=false;
    }
    return flag;
}
    public ArrayList getStudentData(String studentname)
{
    ArrayList<String> data=new ArrayList<String>();
    try{
DBDriver dbd=new DBDriver();
 Statement st=dbd.getStatement();
 String query="select * from student_info where username = '"+studentname+"'";
    System.out.println("Query is");
    ResultSet rs=st.executeQuery(query);
    while(rs.next())
    {
    data.add(rs.getString(1));
    data.add(rs.getString(2));
    data.add(rs.getString(3));
    data.add(rs.getString(4));
    data.add(rs.getString(5));
    data.add(rs.getString(6));
     data.add(rs.getString(7));
    data.add(rs.getString(8));
    } 
    st.close();
    dbd.st.close();
    dbd.conn.close();
}
    catch(Exception ex)
{
    System.out.println("Exception at class StudentDBOP and method getStudentData()"+ex);   
}
return data;
}

public boolean isStudentEdited(String name,String dob,String mobno,String email,String dept,String college,String username,String password,String date_time)
{
boolean flag=false;
    try
    {
        DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        //username, name, dob, mobno, email, pass, date_time
        String query="Update student_info set name='"+name+"',dob='"+dob+"',mobile='"+mobno+"',email='"+email+"',department='"+dept+"',college='"+college+"',pass='"+password+"' where username='"+username+"'";
         int x=st.executeUpdate(query);
            if(x>0)
            flag=true;
            st.close();
            dbd.conn.close();
            dbd.st.close();
 
    }
    catch(Exception ex)
    {
        System.out.println("Exception at class StudentDBOP and at method isStudentEdited() is "+ex);
    }
return flag;
}
}

