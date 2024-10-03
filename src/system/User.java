package system;
import attributes.UserAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class used for setting users' attribute sets.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-03-29
*/
public class User {
    private String userID;
    private String registration_datetime;
    private UserAttributes[] attributes;
    private boolean status;

    /**
     * Class Costructors
     */
    public User(){ }
    
    public User(String userID,UserAttributes[] attributes,boolean status){
        this.userID=userID; this.attributes=attributes;this.status=status; 
        this.registration_datetime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
    
    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the registration_datetime
     */
    public String getRegistration_datetime() {
        return registration_datetime;
    }

    /**
     * @param registration_datetime the registration_datetime to set
     */
    public void setRegistration_datetime(String registration_datetime) {
        this.registration_datetime = registration_datetime;
    }

    /**
     * @return the attributes
     */
    public UserAttributes[] getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(UserAttributes[] attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
