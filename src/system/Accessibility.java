package system;

import attributes.PoliciesAttributesValues;
import attributes.UserAttributes;
import java.util.Arrays;
import policies.FilePolicy;

/**
 * Class used for checking users' accessibility to sensitive files.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-05-16
*/
public class Accessibility {
    public static boolean verifyUserAccessibility(String userID,String file){
        //File's access policy
        FilePolicy policy=PoliciesManagement.getFilePolicy(file);
        //User's attributes
        UserAttributes[] userAttrs=AttributesManagement.getUserAttributes(userID);
        
        //Policy threshold and minimum required attributes
        String threshold=policy.getThreshold();
        int requiredAttrs=Integer.parseInt(threshold.split("of")[0]);
        //User found attributes which satisfy the policy
        int foundAttrs=0;
        
        //Verify if the user requesting access is not in the revocation list
        if(policy.getRevokedUsers().length>0){
            //Get file's list of revoked users
            String[] revokedUsers=policy.getRevokedUsers();
            
            //If user is already revoked, stop attributes' further verification
            for(int i=0;i<revokedUsers.length;i++)
                if(revokedUsers[i].equals(userID)) 
                    return false;
        }
        
        return true; 
    }
    
    public static boolean[] verifyUserPolicyMatch(FilePolicy policy,UserAttributes[] userAttrs,boolean access){
        //Policy threshold and minimum required attributes
        String threshold=policy.getThreshold();
        int requiredAttrs=Integer.parseInt(threshold.split("of")[0]);
        //User found attributes which satisfy the policy
        int foundAttrs=0;
        
        //Access policy attributes
        PoliciesAttributesValues[] acpAttributes=policy.getAttributes();
        //Verify if the user has the attributes required to access the requested file
        for(int i=0;i<acpAttributes.length;i++)
            if(acpAttributes[i].getAttributesSet()!=null){ //PoliciesAttributesValues contains a FilePolicy structure (another attributes set)
                FilePolicy fpAttrs_i=(FilePolicy)acpAttributes[i].getAttributesSet(); //FilePolicy_i (obj_i)
                verifyUserPolicyMatch(fpAttrs_i,userAttrs,access); //FilePolicy_i:attributes
            } else{
                for(int j=0;j<userAttrs.length;j++)
                    if(foundAttrs<requiredAttrs){ //Continue searching for attributes until the required attributes are found
                        if(acpAttributes[i].getName().equals(userAttrs[j].getAttribute()) && acpAttributes[i].getValue().equals(userAttrs[j].getValue()) &&
                                acpAttributes[i].getStatus() && userAttrs[j].getStatus())
                            foundAttrs++;
                    } else{ //Stop search when the minimum required attributes have been found
                        access=true; 
                        System.out.println("inside else");
                        return new boolean[]{true,access};
                    }
            }
        
        System.out.println("outside for");
        return new boolean[]{(foundAttrs==requiredAttrs),access};
    }

    public static boolean verifyUserAccessibility_(String userID,String file){
        //File's access policy
        FilePolicy policy=PoliciesManagement.getFilePolicy(file);
        PoliciesAttributesValues[] acpAttributes=policy.getAttributes();
        //Policy threshold and minimum required attributes
        String threshold=policy.getThreshold();
        int requiredAttrs=Integer.parseInt(threshold.split("of")[0]);
        
        //User's attributes
        UserAttributes[] userAttrs=AttributesManagement.getUserAttributes(userID);
        //User found attributes which satisfy the policy
        int foundAttrs=0;
        
        //Verify if the user requesting access is not in the revocation list
        if(policy.getRevokedUsers().length>0){
            //Get file's list of revoked users
            String[] revokedUsers=policy.getRevokedUsers();
            
            //If user is already revoked, stop attributes' further verification
            for(int i=0;i<revokedUsers.length;i++)
                if(revokedUsers[i].equals(userID)) 
                    return false;
        }
        
        //Verify if the user has the attributes required to access the requested file
        for(int i=0;i<acpAttributes.length;i++)
            for(int j=0;j<userAttrs.length;j++)
                if(foundAttrs<requiredAttrs){ //Continue searching for attributes until the required attributes are found
                    if(acpAttributes[i].getName().equals(userAttrs[j].getAttribute()) && acpAttributes[i].getValue().equals(userAttrs[j].getValue()) &&
                            acpAttributes[i].getStatus() && userAttrs[j].getStatus())
                        foundAttrs++;
                } else //Stop search when the minimum required attributes have been found
                    return true;
        
        return (foundAttrs==requiredAttrs);
    }
}
