package system;
import attributes.PoliciesAttributesValues;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import policies.FilePolicy;
import utils.ProcessJSON;

/**
 * Class used for ...
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-05-16
*/
public class PoliciesManagement {
    private static final String policiesPath="data/policies/";
    public static String filePolicyJSON="_policy.json";

    /**
     * Files' access control policies assignment.
     * @param file
     * @param policyAttributes
     * @param threshold
     * @param revokedUsers 
     */
    public static void generateFilePolicy(String file,PoliciesAttributesValues[] policyAttributes,String threshold,String[] revokedUsers,String note){
        File f = new File(policiesPath); 
        
        //Create directory, if not exists
        if (!f.exists())
            f.mkdirs();
        
        long startTime = System.nanoTime(); //Start timer
        //Create policy
        FilePolicy filePolicy=new FilePolicy(policyAttributes,threshold,new String[]{},revokedUsers);
//        //Show file policy
//        StructuresDisplay.displayFilePolicy(filePolicy,"");
            
        //Write created JSON
        ProcessJSON.writeJSON(filePolicy,policiesPath+file+filePolicyJSON);
        long endTime = System.nanoTime(); //End timer
        long durationMs = (endTime - startTime) / 1_000_000; //Convert to milliseconds
        
        //Write execution times
        if(!note.isEmpty())
            logPoliciesExecutionTime("ExecutionTimes_AccessPolicies.csv", note, durationMs);
    }
    
    /**
     * Log attribute sets generation response time
     * @param logFile
     * @param numAttributes
     * @param numValues
     * @param durationMs 
     */
    private static void logPoliciesExecutionTime(String logFile, String note, long durationMs) {
        File file = new File(policiesPath+logFile);
        boolean fileExists = file.exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            if (!fileExists)
                writer.println("#Attributes,ExecutionTime(ms)");
            writer.println(note+","+durationMs);
        } catch (IOException e) {
            System.err.println("Error writing execution time to CSV: "+logFile);
            e.printStackTrace();
        }
    }
    
    /**
     * Add attribute(s) to existing policy.
     * @param file
     * @param policyAttributes
     * @param threshold
     */
    public static void addAttributesToPolicy(String file,PoliciesAttributesValues[] policyAttributes,String threshold){
        //Amount of new attributes
        int attrLength=policyAttributes.length; 
        
        //Read stored JSON
        String policyJSON=ProcessJSON.readJSON(policiesPath+file+filePolicyJSON);
        FilePolicy policy=new Gson().fromJson(policyJSON, FilePolicy.class);
        PoliciesAttributesValues[] acpAttributes=policy.getAttributes();
        //Amount of current attributes
        int policyAttrLength=acpAttributes.length;
        
        //Threshold values
        String[] thresholdValues=policy.getThreshold().split("of");
        //Amount of active attributes in the policy
        int activeAttrs=Integer.parseInt(thresholdValues[1]);
        System.out.println("activeAttrs out: "+activeAttrs);

        //Required (attribute,value) index
        int idxAttr=-1;
        
        //Look after the requested attribute in current policy's attributes set
        for(int i=0;i<policyAttrLength;i++)
            for(int j=0;j<attrLength;j++)
                //Verify if the requested (attribute,value) already exists
                if(acpAttributes[i].getName().equals(policyAttributes[j].getName()) && acpAttributes[i].getValue().equals(policyAttributes[j].getValue())){
                    //Set index of the requested attribute and change its status to 'active'
                    idxAttr=i; acpAttributes[idxAttr].setStatus(true);
                    activeAttrs++; //<------ REVISAR: NO SIEMPRE SE ACTUALIZA, ES NECESARIO EJECUTAR 2 VECES
                    System.out.println("activeAttrs in: "+activeAttrs);
                }
        
        //Update threshold if it's not specified by user
        if(threshold.length()==0 || threshold.isEmpty()){
            threshold=thresholdValues[0]+"of"+activeAttrs;
            System.out.println("threshold if: "+threshold);
            policy.setThreshold(threshold);
        }
        
        //Add requested attribute to policy's attributes set
        if(idxAttr==-1){ //Populate policy attributes, including modifications
            PoliciesAttributesValues[] newPolicyAttributes=new PoliciesAttributesValues[policyAttrLength+attrLength];
            for(int i=0;i<newPolicyAttributes.length;i++)
                if(i<policyAttrLength) //Add current attributes
                    newPolicyAttributes[i]=new PoliciesAttributesValues(acpAttributes[i].getName(),acpAttributes[i].getValue(),acpAttributes[i].getStatus());
                else{ //Add new attributes
                    int j=i-policyAttrLength; //Current position
                    //Add values for attribute in the i-th position
                    newPolicyAttributes[i]=new PoliciesAttributesValues(policyAttributes[j].getName(),policyAttributes[j].getValue(),true);
                }

            //Update threshold if it's not specified by user
            if(threshold.length()==0)
                threshold=thresholdValues[0]+"of"+(policyAttrLength+attrLength);
            //Update policy
            policy=new FilePolicy(newPolicyAttributes,threshold,policy.getConstraints(),policy.getRevokedUsers());
        }

        //Write created JSON
        ProcessJSON.writeJSON(policy,policiesPath+file+filePolicyJSON);
    }
    
    public static void removePolicyAttribute(String file,String attribute,String value,String threshold){
        //Read stored JSON
        String policyJSON=ProcessJSON.readJSON(policiesPath+file+filePolicyJSON);
        FilePolicy policy=new Gson().fromJson(policyJSON, FilePolicy.class);
        PoliciesAttributesValues[] acpAttributes=policy.getAttributes();
        //Amount of current attributes
        int policyAttrLength=acpAttributes.length;
        //Required (attribute,value) index
        int idxAttr=-1;
        
        //Get index of the requested attribute
        for(int i=0;i<policyAttrLength;i++)
            if(acpAttributes[i].getName().equals(attribute) && acpAttributes[i].getValue().equals(value)){
                idxAttr=i; break;
            }
        
        //Update attribute status
        acpAttributes[idxAttr].setStatus(false);
        //Update threshold if it's not specified by user
        if(threshold.length()==0)
            threshold=policy.getThreshold().split("of")[0]+"of"+(policyAttrLength-1);
        policy.setThreshold(threshold);
        
        //Write created JSON
        ProcessJSON.writeJSON(policy,policiesPath+file+filePolicyJSON);
    }
    
    public static FilePolicy getFilePolicy(String file){
        //Read stored JSON
        String policyJSON=ProcessJSON.readJSON(policiesPath+file+filePolicyJSON);
        return new Gson().fromJson(policyJSON, FilePolicy.class);
    }
}
