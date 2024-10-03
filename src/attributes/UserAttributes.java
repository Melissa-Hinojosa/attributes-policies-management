package attributes;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class used for users' attribute sets definition.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-03-29
*/
public class UserAttributes{
    private String attribute;
    private String value;
    private String issuer_id;
    private String issue_datetime;
    private boolean status;
    
    /**
     * Class Costructors
     */
    public UserAttributes(){ }
    
    public UserAttributes(String attribute,String value,boolean status) throws UnknownHostException{
        this.attribute=attribute; this.value=value; 
        this.issuer_id=InetAddress.getLocalHost().getHostName();
        this.status=status; 
        this.issue_datetime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
    
    public UserAttributes(String attribute,String value,boolean status,String issuer_id,String issue_datetime) throws UnknownHostException{
        this.attribute=attribute; this.value=value; this.issuer_id=issuer_id; 
        this.status=status; this.issue_datetime=issue_datetime;
    }

    /**
     * @return the attribute
     */
    public String getAttribute(){
        return attribute;
    }

    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(String attribute){
        this.attribute=attribute;
    }

    /**
     * @return the value
     */
    public String getValue(){
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value){
        this.value=value;
    }

    /**
     * @return the issuer_id
     */
    public String getIssuer_id(){
        return issuer_id;
    }

    /**
     * @param issuer_id the issuer_id to set
     */
    public void setIssuer_id(String issuer_id){
        this.issuer_id=issuer_id;
    }

    /**
     * @return the issue_datetime
     */
    public String getIssue_datetime(){
        return issue_datetime;
    }

    /**
     * @param issue_datetime the issue_datetime to set
     */
    public void setIssue_datetime(String issue_datetime){
        this.issue_datetime=issue_datetime;
    }

    /**
     * @return the status
     */
    public boolean getStatus(){
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status){
        this.status=status;
    }
}
