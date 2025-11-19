
package fns;
public class DigitValidator {
 public boolean inDigit(String str)
    {
        boolean flag =true;
        try
        {
            Long x=Long.parseLong(str);
            flag=true;
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Exception at Digit Validator is"+ex);
            flag=false;
        }
        return flag;
    }   

    boolean isDigit(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
