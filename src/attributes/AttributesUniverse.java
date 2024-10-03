package attributes;

/**
 * Class used for attribute universe definition.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-03-28
*/
public class AttributesUniverse{
    private String name;
    private String value_type;
    private String category;
    private AttributesUniverseValues[] values;
    private boolean status;

    /**
     * Class Constructors
     */
    public AttributesUniverse(){ }
    
    public AttributesUniverse(String name,String valueType,String category,AttributesUniverseValues[] values,boolean status){ 
        this.name=name; this.value_type=valueType; this.category=category; 
        this.values=values; this.status=status;
    }
    
    /**
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * @param aName the name to set
     */
    public void setName(String aName){
        name=aName;
    }

    /**
     * @return the valueType
     */
    public String getValueType(){
        return value_type;
    }

    /**
     * @param aValueType the valueType to set
     */
    public void setValueType(String aValueType){
        value_type=aValueType;
    }

    /**
     * @return the category
     */
    public String getCategory(){
        return category;
    }

    /**
     * @param aCategory the category to set
     */
    public void setCategory(String aCategory){
        category=aCategory;
    }

    /**
     * @return the values
     */
    public AttributesUniverseValues[] getValues(){
        return values;
    }

    /**
     * @param aValues the values to set
     */
    public void setValues(AttributesUniverseValues[] aValues){
        values=aValues;
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
