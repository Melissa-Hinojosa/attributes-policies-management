package attributes;

import java.util.LinkedList;
import policies.FilePolicy;

/**
 * Class used for attributes' values definition for the access policies.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-04-07
*/
public class PoliciesAttributesValues {
    private String name;
    private Object value;
    private boolean status;
    private FilePolicy attributes_set;
    private String group;

    /**
     * Class Constructors
     */
    public PoliciesAttributesValues(){ }
    
    //EXPERIMENTAL
    public PoliciesAttributesValues(FilePolicy filePolicy,boolean status){ 
//        super();
        this.attributes_set=filePolicy; this.status=status;
    }
    //EXPERIMENTAL
    
    public PoliciesAttributesValues(String name,Object value,boolean status){
//        super();
        this.name=name; this.value=value; 
        this.status=status;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
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
    
    /**
     * @return the attributes_set
     */
    public Object getAttributesSet() {
        return attributes_set;
    }

    /**
     * @param attributes_set the attributes_set to set
     */
    public void setAttributesSet(FilePolicy attributes_set) {
        this.attributes_set = attributes_set;
    }
}
