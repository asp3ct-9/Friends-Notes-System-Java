package fns;

import java.sql.ResultSet;
import java.sql.Statement;
import fns.GlobalUser; // ✅ import your global class

public class EmailFetcher {

    public String getEmailAddress(String up_name) {
        String email_id = "";
        

    try {
        DBDriver dbd = new DBDriver();
        Statement st = dbd.getStatement();
        String query = "SELECT * FROM student_info WHERE username = '" + up_name + "'";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            email_id = rs.getString("email");
        }
    } catch (Exception ex) {
        System.out.println("Exception at EmailFetcher.getEmailAddress: " + ex);
    }
    return email_id;
}
}

