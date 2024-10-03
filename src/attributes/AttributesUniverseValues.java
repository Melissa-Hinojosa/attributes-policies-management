package attributes;

/**
 * Class used for values' definition for the attribute universe.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-03-28
*/
public class AttributesUniverseValues{
    private Object value;
    private String[] constraints;
    private boolean status;

    /**
     * Class Constructors
     */
    public AttributesUniverseValues(){ }
    
    public AttributesUniverseValues(Object value,String[] constraints,boolean status){
        this.value=value; this.constraints=constraints;
        this.status=status;
    }
    
    /**
     * @return the value
     */
    public Object getValue(){
        return value;
    }

    /**
     * @param aValue the value to set
     */
    public void setValue(Object aValue){
        value=aValue;
    }

    /**
     * @return the constraints
     */
    public String[] getConstraints(){
        return constraints;
    }

    /**
     * @param aConstraints the constraints to set
     */
    public void setConstraints(String[] aConstraints){
        constraints=aConstraints;
    }

    /**
     * @return the status
     */
    public boolean getStatus(){
        return status;
    }

    /**
     * @param aStatus the status to set
     */
    public void setStatus(boolean aStatus){
        status=aStatus;
    }    
}
