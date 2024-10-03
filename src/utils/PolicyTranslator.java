package utils;
import attributes.PoliciesAttributesValues;
import java.util.LinkedList;
import policies.FilePolicy;

/**
 * Class used for converting JSON contents into ABE policies.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-03-28
*/
public class PolicyTranslator{
    
    /**
     * Generate BSW07 access policy from JSON specification.
     * @param policy
     * @param str
     * @return policyStr
     */
    public static String getBSW07PolicyAttributesValues(FilePolicy policy,String str){
        PoliciesAttributesValues[] fpAttrs=policy.getAttributes(); //policy:attributes
        String policyStr="";
        
        for(int i=0;i<fpAttrs.length;i++)
            if(fpAttrs[i].getAttributesSet()!=null && fpAttrs[i].getStatus()){ //PoliciesAttributesValues contains a FilePolicy structure (another attributes set)
                FilePolicy fpAttrs_i=(FilePolicy)fpAttrs[i].getAttributesSet(); //FilePolicy_i (obj_i)
                
                policyStr+=getBSW07PolicyAttributesValues(fpAttrs_i,str)+fpAttrs_i.getThreshold()+" "; //FilePolicy_i:attributes, FilePolicy_i:threshold
            } else //PoliciesAttributesValues contains just the attributes data
                if(fpAttrs[i].getStatus())
                    policyStr+=fpAttrs[i].getValue()+" ";
        
        return policyStr;
    }
    
    /**
     * Generate W11 access policy from JSON specification.
     * @param policy
     * @param str
     * @return policyStr
     */
    public static Object getW11PolicyAttributesValues(FilePolicy policy,Object str){
        PoliciesAttributesValues[] fpAttrs=policy.getAttributes(); //policy:attributes
        LinkedList<Object> fbf = new LinkedList<Object>();
//        FBF fbf=new FBF();
        
        for(int i=0;i<fpAttrs.length;i++)
            if(fpAttrs[i].getAttributesSet()!=null && fpAttrs[i].getStatus()){ //PoliciesAttributesValues contains a FilePolicy structure (another attributes set)
                FilePolicy fpAttrs_i=(FilePolicy)fpAttrs[i].getAttributesSet(); //FilePolicy_i (obj_i)
                
                fbf.add(getW11PolicyAttributesValues(fpAttrs_i,str)); //FilePolicy_i:attributes
                if(i==fpAttrs.length-1)
                    fbf.add(Integer.parseInt(policy.getThreshold().split("of")[0])); //FilePolicy_i:threshold
            } else //PoliciesAttributesValues contains just the attributes data
                if(fpAttrs[i].getStatus()){
                    fbf.add(fpAttrs[i].getValue());
                    if(i==fpAttrs.length-1)
                        fbf.add(Integer.parseInt(policy.getThreshold().split("of")[0])); //FilePolicy_i:threshold
                }
        return fbf;
    }   
}
