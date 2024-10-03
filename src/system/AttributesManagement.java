package system;
import attributes.AttributesUniverse;
import attributes.AttributesUniverseValues;
import attributes.UserAttributes;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import org.json.JSONException;
import utils.ProcessJSON;
import utils.StructuresDisplay;

/**
 * Class used for attributes' management.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     2.0
 * @since       2022-03-28
*/
public class AttributesManagement {
    private static final String attributesPath="data/attributes/";
    private static final String attributesUniverseJSON="attributes.json";
    private static final String usersAttributesJSON="_attributes.json";
    
    /**
     * Example for attributes universe generation.
     * @param attributes
     * @param attrVals
     * @throws JSONException
     * @throws IOException 
     */
    public static void generateAttributesUniverse(String[][] attributes,Object[] attrVals) throws JSONException, IOException{
        File f = new File(attributesPath); 
        
        //Create directory, if not exists
        if (!f.exists())
            f.mkdirs();
        
        //Amount of attributes to be added
        int attrsLength=attributes.length;
        
        //Populate attributes universe
        AttributesUniverse[] attrU=new AttributesUniverse[attrsLength];
        for(int i=0;i<attrsLength;i++){
            //Get values for attribute in the i-th position
            AttributesUniverseValues[] values=(AttributesUniverseValues[])attrVals[i];
            //Add i-th attribute to attributes universe
            attrU[i]=new AttributesUniverse(attributes[i][0],attributes[i][1],attributes[i][2],values,true);
        }
        
        //Show attributes array
        StructuresDisplay.displayAttributesUniverse(attrU,"");
        
        //Write created JSON
        ProcessJSON.writeJSON(attrU,attributesPath+attributesUniverseJSON);
    }
    
    /**
     * Add attribute(s) to attributes universe.
     * @param attributes
     * @param attrVals
     */
    public static void addAttributesToUniverse(String[][] attributes,Object[] attrVals){
        //Amount of new attributes
        int attrLength=attributes.length;
        
        //Read stored JSON
        String attributesJSON=ProcessJSON.readJSON(attributesPath+attributesUniverseJSON);
        AttributesUniverse[] attributesArray=new Gson().fromJson(attributesJSON, AttributesUniverse[].class);
        //Amount of current attributes
        int attrUnivLength=attributesArray.length;
//        //Show current attributes array
//        StructuresDisplay.displayAttributesUniverse(attributesArray,"JSON ");
                
        //Populate attributes universe, including modifications
        AttributesUniverse[] attrU=new AttributesUniverse[attrUnivLength+attrLength];
        for(int i=0;i<attrU.length;i++)
            if(i<attrUnivLength) //Add current attributes
                attrU[i]=new AttributesUniverse(attributesArray[i].getName(),attributesArray[i].getValueType(),attributesArray[i].getCategory(),
                        attributesArray[i].getValues(),attributesArray[i].getStatus());
            else{ //Add new attributes
                int j=i-attrUnivLength; //Current position
                //Get values for attribute in the i-th position
                AttributesUniverseValues[] values=(AttributesUniverseValues[])attrVals[j];
                //Add i-th attribute to attributes universe
                attrU[i]=new AttributesUniverse(attributes[j][0],attributes[j][1],attributes[j][2],values,true);
            }
        
        //Show new attributes array
        StructuresDisplay.displayAttributesUniverse(attrU,"MODIFIED ");
        //Write created JSON
        ProcessJSON.writeJSON(attrU,attributesPath+attributesUniverseJSON);
    }
    
    /**
     * Modify attribute value's data (update constraints or status).
     * @param attribute
     * @param value
     * @param constraints
     * @param status 
     */
    public static void modifyAttributeValue(String attribute,String value,String[] constraints,boolean status){
        //Read stored JSON
        String attributesJSON=ProcessJSON.readJSON(attributesPath+attributesUniverseJSON);
        AttributesUniverse[] attributesArray=new Gson().fromJson(attributesJSON, AttributesUniverse[].class);
        //Amount of current attributes
        int attrUnivLength=attributesArray.length;
        //Required (attribute,value) indexes
        int idxAttr=-1; int idxVal=-1;
        
        //Get index of the requested attribute
        for(int i=0;i<attrUnivLength;i++)
            if(attributesArray[i].getName().equals(attribute)){
                idxAttr=i; break;
            }
                
        //Get attribute's values
        AttributesUniverseValues[] values=attributesArray[idxAttr].getValues();
        //Get index of the requested attribute value
        for(int i=0;i<values.length;i++)
            if(values[i].getValue().equals(value)){
                idxVal=i; break;
            }
        
        //Update value constraints/status
        if(constraints.length>0)
            values[idxVal].setConstraints(constraints);
        values[idxVal].setStatus(status);
        
        //Show new attributes array
        StructuresDisplay.displayAttributesUniverse(attributesArray,"MODIFIED ");
        //Write created JSON
        ProcessJSON.writeJSON(attributesArray,attributesPath+attributesUniverseJSON);
    }
    
    /**
     * Modify attribute value's data (add value and its data).
     * @param attribute
     * @param values
     * @param constraints
     * @param statuses
     */
    public static void addAttributeValue(String attribute,String[] values,String[][] constraints,boolean[] statuses){
        //Amount of new values
        int valLength=values.length;
        
        //Read stored JSON
        String attributesJSON=ProcessJSON.readJSON(attributesPath+attributesUniverseJSON);
        AttributesUniverse[] attributesArray=new Gson().fromJson(attributesJSON, AttributesUniverse[].class);
        //Amount of current attributes
        int attrUnivLength=attributesArray.length;
        //Required attribute index
        int idxAttr=-1;
        
        //Get index of the requested attribute
        for(int i=0;i<attrUnivLength;i++)
            if(attributesArray[i].getName().equals(attribute)){
                idxAttr=i; break;
            }
                
        //Get attribute's values
        AttributesUniverseValues[] attributeValues=attributesArray[idxAttr].getValues();
        //Amount of current values
        int attrValLength=attributeValues.length;
        
        //Show current attributes array
        StructuresDisplay.displayAttributesUniverse(attributesArray,"CURRENT ");
        
        //Required (attribute,value) index
        int idxVal=-1;
        
        //Look after the requested attribute value(s) in current attributes universe
        for(int i=0;i<attrValLength;i++)
            for(int j=0;j<valLength;j++)
                //Verify if the requested value already exists
                if(attributeValues[i].getValue().equals(values[j])){
                    //Set index of the requested value and change its status to 'active'
                    idxVal=i; attributeValues[idxVal].setStatus(true);
                }
        
        if(idxVal==-1){ //Populate attribute's values, including modifications
            AttributesUniverseValues[] newValues=new AttributesUniverseValues[attrValLength+valLength];
            for(int i=0;i<newValues.length;i++)
                if(i<attrValLength) //Add current values
                    newValues[i]=new AttributesUniverseValues(attributeValues[i].getValue(),attributeValues[i].getConstraints(),attributeValues[i].getStatus());
                else{ //Add new values
                    int j=i-attrValLength; //Current position
                    newValues[i]=new AttributesUniverseValues(values[j],constraints[j],statuses[j]);              
                } //Update attribute data in attributes universe
            attributesArray[idxAttr].setValues(newValues);
        } else //Update attribute data in attributes universe
            attributesArray[idxAttr].setValues(attributeValues);
        
        //Show new attributes array
        StructuresDisplay.displayAttributesUniverse(attributesArray,"MODIFIED ");
        //Write created JSON
        ProcessJSON.writeJSON(attributesArray,attributesPath+attributesUniverseJSON);
    }
    
    /**
     * Remove attribute from Universe by disabling all its values.
     * @param attribute 
     */
    public static void removeAttribute(String attribute){
        //Read stored JSON
        String attributesJSON=ProcessJSON.readJSON(attributesPath+attributesUniverseJSON);
        AttributesUniverse[] attributesArray=new Gson().fromJson(attributesJSON, AttributesUniverse[].class);
        //Amount of current attributes
        int attrUnivLength=attributesArray.length;
        //Required attribute index
        int idxAttr=-1;
        
        //Get index of the requested attribute
        for(int i=0;i<attrUnivLength;i++)
            if(attributesArray[i].getName().equals(attribute)){
                idxAttr=i; break;
            }
                
        //Get attribute's values
        AttributesUniverseValues[] values=attributesArray[idxAttr].getValues();
        
        //Update attribute's values status to false
        for(AttributesUniverseValues value:values){
            value.setStatus(false);
        } //Update attribute's status to false
        attributesArray[idxAttr].setStatus(false);
        
        //Show new attributes array
        StructuresDisplay.displayAttributesUniverse(attributesArray,"MODIFIED ");
        //Write created JSON
        ProcessJSON.writeJSON(attributesArray,attributesPath+attributesUniverseJSON);
    }
    
    /**
     * Users' attributes assignment.
     * @param user
     * @throws JSONException 
     * @throws java.net.UnknownHostException 
     */
    public static void generateUserAttributes(User user) throws JSONException, UnknownHostException{
        String usersPath=attributesPath+"users/";
        File f = new File(usersPath); 
        
        //Create directory, if not exists
        if (!f.exists())
            f.mkdirs();
        
        //User' attributes assignments
        UserAttributes[] attributes=user.getAttributes();

        //Write created JSON
        ProcessJSON.writeJSON(attributes,usersPath+user.getUserID()+usersAttributesJSON);
        //Show user's attributes
        StructuresDisplay.displayUserAttributes(user.getUserID(),"");
    }
    
    /**
     * Add attribute(s) to existing user.
     * @param userID
     * @param user_Su
     * @throws UnknownHostException 
     * @throws org.json.JSONException 
     */
    public static void addUserAttributes(String userID,UserAttributes[] user_Su) throws UnknownHostException, JSONException{
        //Amount of new attributes
        int attrLength=user_Su.length;        
        
        //Read stored JSON
        String userAttributesJSON=ProcessJSON.readJSON(attributesPath+"users/"+userID+usersAttributesJSON);
        UserAttributes[] attributesArray=new Gson().fromJson(userAttributesJSON,UserAttributes[].class);
        //Amount of current attributes
        int userAttrLength=attributesArray.length;
        
        //Required (attribute,value) index
        int idxAttr=-1; 
        
        //Look after the requested attribute in current S_u
        for(int i=0;i<userAttrLength;i++)
            for(int j=0;j<attrLength;j++)
                //Verify if the requested (attribute,value) already exists
                if(attributesArray[i].getAttribute().equals(user_Su[j].getAttribute()) && attributesArray[i].getValue().equals(user_Su[j].getValue())){
                    //Set index of the requested attribute and change its status to 'active'
                    idxAttr=i; attributesArray[idxAttr].setStatus(true);
                }
        
        //Add requested attribute to user's attributes set
        if(idxAttr==-1){ //Populate user attributes, including modifications
            UserAttributes[] userAttr=new UserAttributes[userAttrLength+attrLength];
            for(int i=0;i<userAttr.length;i++)
                if(i<userAttrLength) //Add current attributes
                    userAttr[i]=new UserAttributes(attributesArray[i].getAttribute(),attributesArray[i].getValue(),attributesArray[i].getStatus(),
                        attributesArray[i].getIssuer_id(),attributesArray[i].getIssue_datetime());
                else{ //Add new attributes
                    int j=i-userAttrLength; //Current position
                    //Add values for attribute in the i-th position
                    userAttr[i]=new UserAttributes(user_Su[j].getAttribute(),user_Su[j].getValue(),true);
                }

            //Write JSON with the changes made to S_u
            ProcessJSON.writeJSON(userAttr,attributesPath+"users/"+userID+usersAttributesJSON);
        } else //Write JSON with the changes made to S_u
            ProcessJSON.writeJSON(attributesArray,attributesPath+"users/"+userID+usersAttributesJSON);
        
        //Show user's attributes
        StructuresDisplay.displayUserAttributes(userID,"JSON ");
    }
    
    /**
     * Remove attribute from Universe by disabling all its values.
     * @param userID
     * @param attribute 
     * @param value 
     */
    public static void removeUserAttribute(String userID,String attribute,String value){
        ///Read stored JSON
        String userAttributesJSON=ProcessJSON.readJSON(attributesPath+"users/"+userID+usersAttributesJSON);
        UserAttributes[] attributesArray=new Gson().fromJson(userAttributesJSON,UserAttributes[].class);
        //Amount of current attributes
        int userAttrLength=attributesArray.length;
        //Required (attribute,value) index
        int idxAttr=-1;
        
        //Get index of the requested attribute
        for(int i=0;i<userAttrLength;i++)
            if(attributesArray[i].getAttribute().equals(attribute) && attributesArray[i].getValue().equals(value)){
                idxAttr=i; break;
            }
        
        //Update attribute status
        attributesArray[idxAttr].setStatus(false);
        
        //Write created JSON
        ProcessJSON.writeJSON(attributesArray,attributesPath+"users/"+userID+usersAttributesJSON);
        //Show user's attributes
        StructuresDisplay.displayUserAttributes(userID,"JSON ");
    }
    
    public static UserAttributes[] getUserAttributes(String userID){
        ///Read stored JSON
        String userAttributesJSON=ProcessJSON.readJSON(attributesPath+"users/"+userID+usersAttributesJSON);
        return new Gson().fromJson(userAttributesJSON,UserAttributes[].class);
    }
}
