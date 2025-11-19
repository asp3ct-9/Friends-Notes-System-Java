/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fns;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import ui.operationsFrame;

/**
 *
 * @author HP
 */
public class Download_Data {
    public static ArrayList masterdata=new ArrayList();
    public boolean isFileDownloadedFromAWS(String filename)
    {
    boolean flag=true;
    
    return flag;
    }
    public boolean isfiledownloaded(String Filename)
{
    boolean flag = false;
    
    if (isFileDownloadedFromAWS(Filename))
    {
        String file_upload_user = null;
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY hh-mm-ss");
        String date_time = sdf.format(dt);

        // System.out.println("All data" + Download_Notes_Frame.masterdata);
        for (int i = 0; i < masterdata.size(); i++)
        {
            ArrayList row1 = (ArrayList) masterdata.get(i);

            if (Filename.equals(row1.get(2)))
            {
                // System.out.println("J = " + j);
                file_upload_user = row1.get(0).toString();
                // System.out.println("j" + j + "z" + file_upload_user);
                System.out.println("UP name: " + file_upload_user);
                break;
            }
        }

      if (isDownloadInfoInserted(GlobalUser.loggedInUsername, Filename, file_upload_user, date_time))

        {
            // example username
            String email_id = new EmailFetcher().getEmailAddress(file_upload_user);
            // System.out.println("Email in dd : " + email_id);

            String subject = "Vote of Thanks from Friends Notes System";
            String msg1 = "Dear, " + file_upload_user.toUpperCase() + "\nThe file \"" + Filename + "\" that you uploaded has been downloaded.";
            String msg2 = "\nThanks and Regards\nFriends Notes System";
            String finalmessage = msg1 + msg2;

            SendEmail sem = new SendEmail();
            sem.sendMailNow(finalmessage, subject, email_id);

            flag = true;
        }
    }

    return flag;
}
    public boolean isDownloadInfoInserted(String current_user, String filename, String uploaded_user_name, String date_time)
{
    try
    {
        DBDriver dbd = new DBDriver();
        Statement st = dbd.getStatement();

        String query = "CREATE TABLE IF NOT EXISTS download_info (" +
                       "username VARCHAR(255), filename VARCHAR(255), " +
                       "uploaded_by VARCHAR(255), download_time VARCHAR(255))";
        st.executeUpdate(query);

        String insertQuery = "INSERT INTO download_info VALUES('" + current_user + "', '" + filename + "', '" + uploaded_user_name + "', '" + date_time + "')";
        int result = st.executeUpdate(insertQuery);

        return result > 0;
    }
    catch (Exception ex)
    {
        System.out.println("Exception at Download_Data in method isDownloadInfo() : " + ex);
    }

    return false;
}
    public ArrayList getDownloadInfo(String current_user) {
    ArrayList data = new ArrayList();

    try {
        DBDriver dbd = new DBDriver();
        Statement st = dbd.getStatement();

        String query = "SELECT * FROM download_info WHERE username = '" + current_user + "'";
        System.out.println("query: " + query);

        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            ArrayList row = new ArrayList();
            row.add(rs.getString("filename"));
            row.add(rs.getString("uploaded_by"));
            row.add(rs.getString("download_time"));
            data.add(row);
        }
    } catch (Exception ex) {
        System.out.println("Exception at getDownloadInfo(): " + ex);
    }

    return data;
}

}
