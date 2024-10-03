package utils;
import attributes.AttributesUniverse;
import attributes.AttributesUniverseValues;
import attributes.PoliciesAttributesValues;
import attributes.UserAttributes;
import com.google.gson.Gson;
import java.util.Arrays;
import policies.FilePolicy;

/**
 * Class used for displaying the contents of the attribute universe, users' attribute sets and access control policies.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-05-13
*/
public class StructuresDisplay {
    private static final String attributesPath="data/attributes/";
    private static final String policiesPath="data/policies/";
    private static final String attributesUniverseJSON="attributes.json";
    private static final String usersAttributesJSON="_attributes.json";
    public static String filePolicyJSON="_policy.json";

    /**
     * Display attributes universe data.
     * @param attributesArray Attributes array
     * @param type Array source
     */
    public static void displayAttributesUniverse(AttributesUniverse[] attributesArray,String type){
        System.out.println(type+"Attributes Universe".toUpperCase()+"\n");
        for (AttributesUniverse attribute:attributesArray){
            System.out.println("name: "+attribute.getName()+"\nvalue_type: "+attribute.getValueType()+"\ncategory: "+attribute.getCategory()+
                    "\nstatus: "+attribute.getStatus()+"\nvalues:");
            //Values array
            AttributesUniverseValues[] attrVals=attribute.getValues();
            for (AttributesUniverseValues value:attrVals)
                System.out.println("\tvalue: "+value.getValue()+"\n\tconstraints: "+Arrays.asList(value.getConstraints())+"\n\tstatus: "+value.getStatus());
        } //System.out.println("\n\n");
    }
    
    /**
     * Display attributes universe data (active attributes). 
     */
    public static void displayActiveAttributesUniverse(){
        //Read stored JSON
        String attributesJSON=ProcessJSON.readJSON(attributesPath+attributesUniverseJSON);
        AttributesUniverse[] attributesArray=new Gson().fromJson(attributesJSON, AttributesUniverse[].class);
        
        System.out.println("Attributes Universe (Active)".toUpperCase()+"\n");
        for (AttributesUniverse attribute:attributesArray)
            if(attribute.getStatus()){
                System.out.println("name: "+attribute.getName()+"\nvalue_type: "+attribute.getValueType()+"\ncategory: "+attribute.getCategory()+"\nvalues:");
                //Get attribute's values
                AttributesUniverseValues[] attrVals=attribute.getValues();
                for (AttributesUniverseValues value:attrVals)
                    System.out.println("\tvalue: "+value.getValue()+"\n\tconstraints: "+Arrays.asList(value.getConstraints())+"\n\tstatus: "+value.getStatus());
            }
    }
    
    /**
     * Display attributes universe data (active attributes & active values).
     */
    public static void displayActiveAttributesValues(){
        //Read stored JSON
        String attributesJSON=ProcessJSON.readJSON(attributesPath+attributesUniverseJSON);
        AttributesUniverse[] attributesArray=new Gson().fromJson(attributesJSON, AttributesUniverse[].class);
        
        System.out.println("Attributes Universe (Active)".toUpperCase()+"\n");
        for (AttributesUniverse attribute:attributesArray)
            if(attribute.getStatus()){
                System.out.println("name: "+attribute.getName()+"\nvalue_type: "+attribute.getValueType()+"\ncategory: "+attribute.getCategory()+"\nvalues:");
                //Get attribute's values
                AttributesUniverseValues[] attrVals=attribute.getValues();
                for (AttributesUniverseValues value:attrVals)
                    if(value.getStatus())
                        System.out.println("\tvalue: "+value.getValue()+"\n\tconstraints: "+Arrays.asList(value.getConstraints()));
            }
    }
    
    /**
     * Display user attributes set.
     * @param user
     * @param type Array source
     */
    public static void displayUserAttributes(String user,String type){
        //Read stored JSON
        String userAttributesJSON=ProcessJSON.readJSON(attributesPath+"users/"+user+usersAttributesJSON);
        UserAttributes[] attributesArray=new Gson().fromJson(userAttributesJSON,UserAttributes[].class);
            
        System.out.println(type+"User's Attributes".toUpperCase()+"\n");
        for (UserAttributes attribute:attributesArray)
            System.out.println("name: "+attribute.getAttribute()+"\nvalue: "+attribute.getValue()+"\nissuer_id: "+attribute.getIssuer_id()+
                    "\nissue_datetime:"+attribute.getIssue_datetime()+"\nstatus: "+attribute.getStatus()+"\n");
        System.out.println();
    }
    
    /**
     * Display user attributes set (active attributes).
     * @param user
     */
    public static void displayUserActiveAttributes(String user){
        //Read stored JSON
        String userAttributesJSON=ProcessJSON.readJSON(attributesPath+"users/"+user+usersAttributesJSON);
        UserAttributes[] attributesArray=new Gson().fromJson(userAttributesJSON,UserAttributes[].class);
            
        System.out.println("User's Attributes (Active)".toUpperCase()+"\n");
        for (UserAttributes attribute:attributesArray)
            if(attribute.getStatus())
                System.out.println("name: "+attribute.getAttribute()+"\nvalue: "+attribute.getValue()+"\nissuer_id: "+attribute.getIssuer_id()+
                        "\nissue_datetime:"+attribute.getIssue_datetime()+"\n");
        System.out.println();
    }
    
    /**
     * Display policy attributes set.
     * @param file 
     * @param type Policy source
     */
        public static void displayFilePolicy(String file,String type){
        //Read stored JSON
        String policyJSON=ProcessJSON.readJSON(policiesPath+file+filePolicyJSON);
        FilePolicy policy=new Gson().fromJson(policyJSON, FilePolicy.class);
            
        System.out.println(type+"File Policy".toUpperCase()+"\n");
        PoliciesAttributesValues[] attributesValues=policy.getAttributes();
        
        System.out.println("attributes: ");
        for(PoliciesAttributesValues attribute:attributesValues)
            System.out.println("\tname: "+attribute.getName()+"\n\tvalue: "+attribute.getValue()+"\n\tstatus: "+attribute.getStatus()+"\n");
        
        System.out.println("\nthreshold: "+policy.getThreshold()+"\nconstraints: "+
                Arrays.asList(policy.getConstraints())+"\nrevokedUsers: "+Arrays.asList(policy.getRevokedUsers())+"\n");
        //System.out.println("\n\n");
    }
    
    /**
     * Display policy attributes set (recursive method).
     * @param policy 
     */
    public static void displayPoliciesAttributesValues(FilePolicy policy){
        PoliciesAttributesValues[] fpAttrs=policy.getAttributes(); //policy:attributes
        
        for(int i=0;i<fpAttrs.length;i++)
            if(fpAttrs[i].getAttributesSet()!=null){ //PoliciesAttributesValues contains a FilePolicy structure (another attributes set)
                FilePolicy fpAttrs_i=(FilePolicy)fpAttrs[i].getAttributesSet(); //FilePolicy_i (obj_i)
                
                displayPoliciesAttributesValues(fpAttrs_i); //FilePolicy_i:attributes
                System.out.println(fpAttrs_i.getThreshold()+"\n"); //FilePolicy_i:threshold
            } else //PoliciesAttributesValues contains just the attributes data
                System.out.println(fpAttrs[i].getName()+", "+fpAttrs[i].getValue()+", "+fpAttrs[i].getStatus()); //attributes data
    }
        
    /**
     * Display policy attributes set.
     * @param file 
     * @param type Policy source
     */
    public static void displayComplexFilePolicy(String file,String type){
        //Read stored JSON
        String policyJSON=ProcessJSON.readJSON(policiesPath+file+filePolicyJSON);
        FilePolicy policy=new Gson().fromJson(policyJSON, FilePolicy.class); //policy

        //File ID
        System.out.println((file+" Policy ("+type+")").toUpperCase());
        //policy:attributes
        displayPoliciesAttributesValues(policy);
        //policy:threshold, policy:constraints, policy:revokedUsers
        System.out.println(policy.getThreshold()+"\n"+Arrays.asList(policy.getConstraints())+"\n"+Arrays.asList(policy.getRevokedUsers())+"\n");
    }
    
    /**
     * Display policy attributes set (active attributes).
     * @param file
     */
    public static void displayPolicyActiveAttributes(String file){
        //Read stored JSON
        String policyJSON=ProcessJSON.readJSON(policiesPath+file+filePolicyJSON);
        FilePolicy policy=new Gson().fromJson(policyJSON, FilePolicy.class);
        
        System.out.println((file+" Policy").toUpperCase()+"\n");
        PoliciesAttributesValues[] attributesValues=policy.getAttributes();
        
        System.out.println("attributes: ");
        for(PoliciesAttributesValues attribute:attributesValues)
            if(attribute.getStatus())
                System.out.println("\tname: "+attribute.getName()+"\n\tvalue: "+attribute.getValue()+"\n");
        
        System.out.println("\nthreshold: "+policy.getThreshold()+"\nconstraints: "+
                Arrays.asList(policy.getConstraints())+"\nrevokedUsers: "+Arrays.asList(policy.getRevokedUsers())+"\n");
        //System.out.println("\n\n");
    }
}
