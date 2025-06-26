import attributes.AttributesUniverseValues;
import attributes.PoliciesAttributesValues;
import attributes.UserAttributes;
import java.io.IOException;
import java.net.UnknownHostException;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import policies.FilePolicy;
import src.cpabe.waters.policy.FBF;
import system.AttributesManagement;
import system.PoliciesManagement;
import system.User;
import system.UsersManagement;
import utils.PolicyTranslator;
import utils.StructuresDisplay;

/**
 * Class used for testing.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-03-28
*/
public class MainCaseStudy {
    public static String filePolicyJSON="_policy.json";
    
    /****************************************
     * ATTRIBUTES UNIVERSE MANAGEMENT
     ****************************************/
    /**
     * Generate attribute universe (\mathbb{U})
     * @throws JSONException
     * @throws IOException 
     */
    public static void generateAttributesUniverse_eHealth() throws JSONException, IOException{
        /**
         * BEGIN generateAttributesUniverse EXAMPLE DATA
         */
        //Attributes to be added
        String[][] attributes={{"occupation","string","staff"},{"specialty","string","medical_specialty"},{"sub-specialty","string","medical_sub-specialties"},
            {"department","string","infrastructure"},{"institution","string","infrastructure"}};
        
        //Occupation attributes
        AttributesUniverseValues[] attrValsOccupation=new AttributesUniverseValues[5]; 
        attrValsOccupation[0]=new AttributesUniverseValues("physician",new String[]{},true);
        attrValsOccupation[1]=new AttributesUniverseValues("resident",new String[]{},true);
        attrValsOccupation[2]=new AttributesUniverseValues("nurse",new String[]{},true);
        attrValsOccupation[3]=new AttributesUniverseValues("medical_assistant",new String[]{},true);
        attrValsOccupation[4]=new AttributesUniverseValues("medical_researcher",new String[]{},true);
        //Specialty attributes
        AttributesUniverseValues[] attrValsSpecialty=new AttributesUniverseValues[6]; 
        attrValsSpecialty[0]=new AttributesUniverseValues("radiology",new String[]{},true);
        attrValsSpecialty[1]=new AttributesUniverseValues("oncology",new String[]{},true);
        attrValsSpecialty[2]=new AttributesUniverseValues("gynecology",new String[]{},true);
        attrValsSpecialty[3]=new AttributesUniverseValues("orthopedics",new String[]{},true);
        attrValsSpecialty[4]=new AttributesUniverseValues("internal_medicine",new String[]{},true);
        attrValsSpecialty[5]=new AttributesUniverseValues("pathology",new String[]{},true);
        //Sub-specialty attributes
        AttributesUniverseValues[] attrValsSubSpecialty=new AttributesUniverseValues[8]; 
        attrValsSubSpecialty[0]=new AttributesUniverseValues("nuclear_medicine",new String[]{},true);
        attrValsSubSpecialty[1]=new AttributesUniverseValues("interventional_radiology",new String[]{},true);
        attrValsSubSpecialty[2]=new AttributesUniverseValues("thoracic_oncology",new String[]{},true);
        attrValsSubSpecialty[3]=new AttributesUniverseValues("breast_oncology",new String[]{},true);
        attrValsSubSpecialty[4]=new AttributesUniverseValues("musculoskeletal_oncology",new String[]{},true);
        attrValsSubSpecialty[5]=new AttributesUniverseValues("rheumatology",new String[]{},true);
        attrValsSubSpecialty[6]=new AttributesUniverseValues("anatomical_pathology",new String[]{},true);
        attrValsSubSpecialty[7]=new AttributesUniverseValues("clinical_pathology",new String[]{},true);
        //Department attributes
        AttributesUniverseValues[] attrValsDepartment=new AttributesUniverseValues[7]; 
        attrValsDepartment[0]=new AttributesUniverseValues("medical_imaging",new String[]{},true);
        attrValsDepartment[1]=new AttributesUniverseValues("oncology",new String[]{},true);
        attrValsDepartment[2]=new AttributesUniverseValues("gynecology",new String[]{},true);
        attrValsDepartment[3]=new AttributesUniverseValues("orthopedics",new String[]{},true);
        attrValsDepartment[4]=new AttributesUniverseValues("pathology",new String[]{},true);
        attrValsDepartment[5]=new AttributesUniverseValues("clinical_trials",new String[]{},true);
        attrValsDepartment[6]=new AttributesUniverseValues("emergency_medicine",new String[]{},true);
        //Institution attributes
        AttributesUniverseValues[] attrValsInstitution=new AttributesUniverseValues[5]; 
        attrValsInstitution[0]=new AttributesUniverseValues("INR",new String[]{},true);
        attrValsInstitution[1]=new AttributesUniverseValues("IMSS",new String[]{},true);
        attrValsInstitution[2]=new AttributesUniverseValues("ISSSTE",new String[]{},true);
        attrValsInstitution[3]=new AttributesUniverseValues("OCA",new String[]{},true);
        attrValsInstitution[4]=new AttributesUniverseValues("HU",new String[]{},true);
        
        //Array of attributes values arrays
        Object[] attrVals={attrValsOccupation,attrValsSpecialty,attrValsSubSpecialty,attrValsDepartment,attrValsInstitution};
        /**
         * END generateAttributesUniverse EXAMPLE DATA
         */
        AttributesManagement.generateAttributesUniverse(attributes,attrVals,"");
    }
    
    
    /****************************************
     * USERS' ATTRIBUTES MANAGEMENT
     ****************************************/
    /**
     * Generate user's attribute sets (\mathbf{S}_u)
     * @throws JSONException
     * @throws UnknownHostException 
     */
    public static void generateUserAttributes_eHealth() throws JSONException, UnknownHostException{
        /**
         * BEGIN generateUserAttributes EXAMPLE DATA
         */
        //User 1
        UserAttributes[] userMD1_Su=new UserAttributes[5]; 
        userMD1_Su[0]=new UserAttributes("occupation","physician",true);
        userMD1_Su[1]=new UserAttributes("specialty","oncology",true);
        userMD1_Su[2]=new UserAttributes("sub-specialty","thoracic_oncology",true);
        userMD1_Su[3]=new UserAttributes("department","oncology",true);
        userMD1_Su[4]=new UserAttributes("institution","IMSS",true);
        //User 2
        UserAttributes[] userMD2_Su=new UserAttributes[5];
        userMD2_Su[0]=new UserAttributes("occupation","physician",true);
        userMD2_Su[1]=new UserAttributes("specialty","oncology",true);
        userMD2_Su[2]=new UserAttributes("sub-specialty","musculoskeletal_oncology",true);
        userMD2_Su[3]=new UserAttributes("department","orthopedics",true);
        userMD2_Su[4]=new UserAttributes("institution","ISSSTE",true);
        //User 3
        UserAttributes[] userMD3_Su=new UserAttributes[3];
        userMD3_Su[0]=new UserAttributes("occupation","physician",true);
        userMD3_Su[1]=new UserAttributes("specialty","internal_medicine",true);
        userMD3_Su[2]=new UserAttributes("department","emergency_medicine",true);
        //User 4
        UserAttributes[] userNU1_Su=new UserAttributes[5];
        userNU1_Su[0]=new UserAttributes("occupation","nurse",true);
        userNU1_Su[1]=new UserAttributes("specialty","oncology",true);
        userNU1_Su[2]=new UserAttributes("sub-specialty","thoracic_oncology",true);
        userNU1_Su[3]=new UserAttributes("department","oncology",true);
        userNU1_Su[4]=new UserAttributes("institution","IMSS",true);
        //User 5
        UserAttributes[] userNU2_Su=new UserAttributes[4];
        userNU2_Su[0]=new UserAttributes("occupation","nurse",true);
        userNU2_Su[1]=new UserAttributes("specialty","oncology",true);
        userNU2_Su[2]=new UserAttributes("sub-specialty","breast_oncology",true);
        userNU2_Su[3]=new UserAttributes("department","gynecology",true);
        //User 6
        UserAttributes[] userNU3_Su=new UserAttributes[4];
        userNU3_Su[0]=new UserAttributes("occupation","nurse",true);
        userNU3_Su[1]=new UserAttributes("specialty","oncology",true);
        userNU3_Su[2]=new UserAttributes("sub-specialty","breast_oncology",true);
        userNU3_Su[3]=new UserAttributes("department","oncology",true);
        //User 7
        UserAttributes[] userNU4_Su=new UserAttributes[5];
        userNU4_Su[0]=new UserAttributes("occupation","nurse",true);
        userNU4_Su[1]=new UserAttributes("specialty","pathology",true);
        userNU4_Su[2]=new UserAttributes("sub-specialty","clinical_pathology",true);
        userNU4_Su[3]=new UserAttributes("department","pathology",true);
        userNU4_Su[4]=new UserAttributes("institution","OCA",true);
        //User 8
        UserAttributes[] userMR1_Su=new UserAttributes[4];
        userMR1_Su[0]=new UserAttributes("occupation","medical_researcher",true);
        userMR1_Su[1]=new UserAttributes("specialty","pathology",true);
        userMR1_Su[2]=new UserAttributes("sub-specialty","anatomical_pathology",true);
        userMR1_Su[3]=new UserAttributes("department","clinical_trials",true);
        //User 9
        UserAttributes[] userMR2_Su=new UserAttributes[4];
        userMR2_Su[0]=new UserAttributes("occupation","medical_researcher",true);
        userMR2_Su[1]=new UserAttributes("specialty","radiology",true);
        userMR2_Su[2]=new UserAttributes("sub-specialty","nuclear_medicine",true);
        userMR2_Su[3]=new UserAttributes("department","medical_imaging",true);
        //User 10
        UserAttributes[] userMA1_Su=new UserAttributes[3];
        userMA1_Su[0]=new UserAttributes("occupation","medical_assistant",true);
        userMA1_Su[1]=new UserAttributes("specialty","radiology",true);
        userMA1_Su[2]=new UserAttributes("sub-specialty","interventional_radiology",true);
        
        
        String[] usersID={"userMD1_Su","userMD2_Su","userMD3_Su","userNU1_Su","userNU2_Su","userNU3_Su","userNU4_Su","userMR1_Su","userMR2_Su","userMA1_Su"};
        UserAttributes[][] usersAttributes={userMD1_Su,userMD2_Su,userMD3_Su,userNU1_Su,userNU2_Su,userNU3_Su,userNU4_Su,userMR1_Su,userMR2_Su,userMA1_Su};
        User[] users=UsersManagement.createUsers(usersID,usersAttributes);
        /**
         * END generateUserAttributes EXAMPLE DATA
         */
        
        //Users' attributes assignments
        for(User user:users)
            AttributesManagement.generateUserAttributes(user,"");
    }
    
    
    /****************************************
     * ACCESS CONTROL POLICIES MANAGEMENT
     ****************************************/
    public static void generateFilePolicy_eHealth(){
        /**
         * BEGIN generateFilePolicy EXAMPLE DATA
         */
        //Files specifications
        String[] files=new String[]{"file1.dcm","file2.dcm","file3.dcm","file4.dcm","file5.dcm","file6.dcm","file7.dcm","file8.dcm","file9.dcm","file10.dcm"};
        String[] thresholds=new String[]{"2of3","2of5","1of2","2of2","1of3","1of2","3of3","3of4","5of5","2of4"};
        String[] revokedUsers=new String[]{};
        
        //File 1
        PoliciesAttributesValues[] policyAttributes_File1=new PoliciesAttributesValues[]{new PoliciesAttributesValues("occupation","physician",true),
            new PoliciesAttributesValues("specialty","radiology",true),new PoliciesAttributesValues("specialty","orthopedics",true)};
        //File 2
        PoliciesAttributesValues[] policyAttributes_File2=new PoliciesAttributesValues[]{new PoliciesAttributesValues("occupation","physician",true),
            new PoliciesAttributesValues("occupation","resident",true),new PoliciesAttributesValues("specialty","radiology",true),
            new PoliciesAttributesValues("specialty","orthopedics",true),new PoliciesAttributesValues("department","medical_imaging",true)};
        //File 3
        PoliciesAttributesValues[] policyAttributes1=new PoliciesAttributesValues[]{
            new PoliciesAttributesValues("occupation","physician",true),new PoliciesAttributesValues("specialty","orthopedics",true)
        }; FilePolicy policy1=new FilePolicy(policyAttributes1,"2of2");
        PoliciesAttributesValues[] policyAttributes2=new PoliciesAttributesValues[]{
            new PoliciesAttributesValues("occupation","physician",true),new PoliciesAttributesValues("specialty","radiology",true)
        }; FilePolicy policy2=new FilePolicy(policyAttributes2,"2of2");    
        PoliciesAttributesValues[] policyAttributes_File3=new PoliciesAttributesValues[]{
            new PoliciesAttributesValues(policy1,true),new PoliciesAttributesValues(policy2,true)
        };
        //File 4
        PoliciesAttributesValues[] policyAttributes_File4=new PoliciesAttributesValues[]{
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                    new PoliciesAttributesValues("occupation","physician",true),new PoliciesAttributesValues("occupation","nurse",true)
            },"1of2"),true),
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                new PoliciesAttributesValues("specialty","orthopedics",true),new PoliciesAttributesValues("specialty","radiology",true)
            },"1of2"),true)
        };
        //File 5
        PoliciesAttributesValues[] policyAttributes_File5=new PoliciesAttributesValues[]{
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                    new PoliciesAttributesValues("occupation","physician",true),new PoliciesAttributesValues("occupation","nurse",true)
            },"1of2"),true),
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                new PoliciesAttributesValues("specialty","orthopedics",true),new PoliciesAttributesValues("specialty","radiology",true)
            },"1of2"),true),
            new PoliciesAttributesValues("department","emergency_medicine",true)
        };
        //File 6
        PoliciesAttributesValues[] policyAttributes_File6=new PoliciesAttributesValues[]{
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                        new PoliciesAttributesValues("occupation","physician",true),new PoliciesAttributesValues("occupation","nurse",true)
                },"1of2"),true),
                new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                    new PoliciesAttributesValues("specialty","gynecology",true),new PoliciesAttributesValues("specialty","oncology",true)
                },"1of2"),true)
            },"2of2"),true),
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                        new PoliciesAttributesValues("occupation","physician",true),new PoliciesAttributesValues("specialty","radiology",true)
                },"2of2"),true),
                new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                    new PoliciesAttributesValues("occupation","resident",true),new PoliciesAttributesValues("department","medical_imaging",true)
                },"2of2"),true)
            },"1of2"),true)
        };
        //File 7
        PoliciesAttributesValues[] policyAttributes_File7=new PoliciesAttributesValues[]{new PoliciesAttributesValues("occupation","medical_researcher",true),
            new PoliciesAttributesValues("specialty","pathology",true),new PoliciesAttributesValues("department","clinical_trials",true)};
        //File 8
        PoliciesAttributesValues[] policyAttributes_File8=new PoliciesAttributesValues[]{
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                    new PoliciesAttributesValues("occupation","physician",true),new PoliciesAttributesValues("occupation","medical_researcher",true)
            },"1of2"),true),
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                new PoliciesAttributesValues("specialty","radiology",true),new PoliciesAttributesValues("specialty","internal_medicine",true),
                new PoliciesAttributesValues("specialty","pathology",true)
            },"1of3"),true),
            new PoliciesAttributesValues(new FilePolicy(new PoliciesAttributesValues[]{
                    new PoliciesAttributesValues("sub-specialty","interventional_radiology",true),new PoliciesAttributesValues("sub-specialty","clinical_pathology",true)
            },"1of2"),true),
            new PoliciesAttributesValues("institution","IMSS",true)
        };
        //File 9
        PoliciesAttributesValues[] policyAttributes_File9=new PoliciesAttributesValues[]{new PoliciesAttributesValues("occupation","medical_researcher",true),
            new PoliciesAttributesValues("specialty","radiology",true),new PoliciesAttributesValues("sub-specialty","nuclear_medicine",true),
            new PoliciesAttributesValues("department","medical_imaging",true),new PoliciesAttributesValues("institution","INR",true)
        };
        //File 10
        PoliciesAttributesValues[] policyAttributes_File10=new PoliciesAttributesValues[]{new PoliciesAttributesValues("occupation","medical_researcher",true),
            new PoliciesAttributesValues("occupation","medical_assistant",true),new PoliciesAttributesValues("specialty","internal_medicine",true),
            new PoliciesAttributesValues("department","emergency_medicine",true)
        };
        
        //Array of policies values arrays
        Object[] policiesVals={policyAttributes_File1,policyAttributes_File2,policyAttributes_File3,policyAttributes_File4,policyAttributes_File5,
            policyAttributes_File6,policyAttributes_File7,policyAttributes_File8,policyAttributes_File9,policyAttributes_File10};
        
        //Write created policies
        for(int i=0;i<files.length;i++){
            PoliciesManagement.generateFilePolicy(files[i],(PoliciesAttributesValues[])policiesVals[i],thresholds[i],revokedUsers,"");
            StructuresDisplay.displayComplexFilePolicy(files[i],"JSON ");
        }
    }
    
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args) throws JSONException, IOException, ParseException{
        /**
         * Case study
         */
        //Attributes Universe management
        generateAttributesUniverse_eHealth();
        //Display
        StructuresDisplay.displayActiveAttributesUniverse();
        StructuresDisplay.displayActiveAttributesValues();

        //User attributes management
        generateUserAttributes_eHealth(); 
        //Display
        MainExample.displayUserActiveAttributes("userMR1_Su");

        //Access control policies management
        generateFilePolicy_eHealth();
        //Display
        MainExample.displayComplexFilePolicy(new String[]{"file1.dcm","file2.dcm","file3.dcm","file4.dcm","file5.dcm"});
        
        testABEtranslator("file6.dcm","BSW07"); //Available constructions: BSW07, W11
        translatePolicies();
    }
    
    /**
     * Simulation policy translator 
     */
    public static void translatePolicies(){
        String[] files=new String[]{"file1.dcm","file2.dcm","file3.dcm","file4.dcm","file5.dcm","file6.dcm","file7.dcm","file8.dcm","file9.dcm","file10.dcm"};
        String[] constructions={"BSW07","W11"};
        
        for(String file:files){
                System.out.println(file);
            for(String construction:constructions)
                testABEtranslator(file,construction);
        }
    }
    
    /**
     * Convert JSON policy structures into CP-ABE required formats
     * @param file
     * @param construction 
     */
    public static void testABEtranslator(String file,String construction){
        FilePolicy filePolicy=PoliciesManagement.getFilePolicy(file);
        
        if(construction.equals("BSW07")){ //CP-ABE tree-based construction
            //Get and display access control policy
            String policyStr=PolicyTranslator.getBSW07PolicyAttributesValues(filePolicy,"")+filePolicy.getThreshold();
            System.out.println("BSW07 policy: \n"+policyStr+"\n");
        }else{ //CP-ABE matrix-based construction
            //Get and display access control policy
            FBF fbf=PolicyTranslator.getW11PolicyAttributesValues(filePolicy,new Object()); //FilePolicy_i:attributes
            System.out.println("W11 policy: \n"+fbf+"\n");
        }
    }
}
