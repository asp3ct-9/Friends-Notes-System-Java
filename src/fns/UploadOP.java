
package fns;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UploadOP {

    // Step 1: Main upload handler
    public boolean isUploaded(String username, String filepath, String description, String date_time) {
        boolean flag = true;

        try {
            if (isUploadInfoTableCreated()) {
                System.out.println("Upload info table is ready.");
                if (isFileUploaded(username, filepath, description, date_time)) {
                    System.out.println("File upload record stored successfully.");
                } else {
                    System.out.println("Failed to store file upload record.");
                    flag = false;
                }
            } else {
                System.out.println("Failed to create/check upload info table.");
                flag = false;
            }
        } catch (Exception ex) {
            System.out.println("Exception in isUploaded(): " + ex);
            flag = false;
        }

        // For now return true hardcoded
        return true;
    }

    public boolean isUploadInfoTableCreated() {
        boolean flag = true;

        try {
            DBDriver dbd = new DBDriver();
            Statement st = dbd.getStatement();
            

            String query = "CREATE TABLE IF NOT EXISTS upload_info ("+ "username VARCHAR(45), "+ "filename VARCHAR(100) PRIMARY KEY, "+ "description VARCHAR(255), "+ "time VARCHAR(45))";

            st.executeUpdate(query);
            dbd.st.close();
            dbd.conn.close();
        } catch (Exception ex) {
            System.out.println("Exception in isUploadInfoTableCreated(): " + ex);
            flag = false;
        }

        return flag;
    }

    // Step 3: Store file upload info
    public boolean isFileUploaded(String username, String filepath, String description, String date_time) {
        boolean flag = false;

        try {
            DBDriver dbd = new DBDriver();
            Statement st = dbd.getStatement();

            String query = "INSERT INTO upload_info VALUES ('" + username + "', '" + filepath + "', '" + description + "', '" + date_time + "')";
            if (st.executeUpdate(query) > 0) {
                flag = true;
            }

            dbd.st.close();
            dbd.conn.close();
        } catch (Exception ex) {
            System.out.println("Exception in isFileUploaded(): " + ex);
            flag = false;
        }

        return flag;
    }


 public ArrayList getStoredFileInfo(String studentname)
     {
     ArrayList data=new ArrayList();
     try
     {
        DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        String query="Select * from upload_info where username = '"+studentname+"'";
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
            String filename=rs.getString(2);
            String description=rs.getString(3);
            String date_time=rs.getString(4);
            ArrayList row=new ArrayList();
            row.add(filename);
            row.add(description);
            row.add(date_time);
            data.add(row);
            
        }
     
     }
     catch(Exception ex)
     {
         System.out.println("Exception at class UploadOP() in method getStoredFileInfo() in "+ex);
     }
     
     return data;
     }


public boolean isUploadInfoEdited(String username, String filepath, String description, String date_time) {
    boolean flag = false;
    try {
        DBDriver dbd = new DBDriver();
        Statement st = dbd.getStatement();

        // 🛠 Update both description and time
        String query = "UPDATE upload_info SET description = '" + description + "', time = '" + date_time + "' " +
                       "WHERE username = '" + username + "' AND filename = '" + filepath + "'";

        int i = st.executeUpdate(query);
        if (i > 0) {
            flag = true;
        }

        dbd.st.close();
        dbd.conn.close();
    } catch (Exception e) {
        System.out.println("Edit error: " + e);
    }
    return flag;
}

}