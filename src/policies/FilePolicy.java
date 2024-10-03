package policies;
import attributes.PoliciesAttributesValues;
import java.util.Arrays;

/**
 * Class used for setting files' access policies.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-04-07
*/
public class FilePolicy {
    private PoliciesAttributesValues[] attributes;
    private String threshold;
    private String[] constraints;
    private String[] revokedUsers;

    /**
     * Class Constructors
     */
    public FilePolicy(){ }
    
    //EXPERIMENTAL
    public FilePolicy(PoliciesAttributesValues[] attributes,String threshold){
        this.attributes=attributes; this.threshold=threshold;
    }
    //EXPERIMENTAL
    
    public FilePolicy(PoliciesAttributesValues[] attributes,String threshold,String[] constraints,String[] revokedUsers){
        this.attributes=attributes; this.threshold=threshold;
        this.constraints=constraints; this.revokedUsers=revokedUsers;
    }
        
    /**
     * @return the attributes
     */
    public PoliciesAttributesValues[] getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(PoliciesAttributesValues[] attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the threshold
     */
    public String getThreshold() {
        return threshold;
    }

    /**
     * @param threshold the threshold to set
     */
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    /**
     * @return the constraints
     */
    public String[] getConstraints() {
        return constraints;
    }

    /**
     * @param constraints the constraints to set
     */
    public void setConstraints(String[] constraints) {
        this.constraints = constraints;
    }

    /**
     * @return the revokedUsers
     */
    public String[] getRevokedUsers() {
        return revokedUsers;
    }

    /**
     * @param revokedUsers the revokedUsers to set
     */
    public void setRevokedUsers(String[] revokedUsers) {
        this.revokedUsers = revokedUsers;
    }
}
