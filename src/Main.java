import attributes.AttributesUniverseValues;
import attributes.PoliciesAttributesValues;
import attributes.UserAttributes;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;
//import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import policies.FilePolicy;
import system.Accessibility;
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
public class Main {
    public static String filePolicyJSON="_policy.json";
    
    /**
     * ATTRIBUTES UNIVERSE MANAGEMENT
     */
    public static void generateAttributesUniverse_example() throws JSONException, IOException{
        /**
         * BEGIN generateAttributesUniverse EXAMPLE DATA
         */
        //Attributes to be added
        String[][] attributes={{"occupation","string","faculty_staff"},{"career","string","education_offer"}};
        
        //Occupation attributes
        AttributesUniverseValues[] attrValsOccupation=new AttributesUniverseValues[3]; 
        attrValsOccupation[0]=new AttributesUniverseValues("student",new String[]{"weekdays","currentTime>=10:00:00","currentTime<=18:00:00"},true);
        attrValsOccupation[1]=new AttributesUniverseValues("professor",new String[]{"weekdays","currentTime>=08:00:00","currentTime<=18:00:00"},true);
        attrValsOccupation[2]=new AttributesUniverseValues("assistant",new String[]{"weekdays","currentTime>=09:00:00","currentTime<=18:00:00"},true);
        //Careers attributes
        AttributesUniverseValues[] attrValsCareer=new AttributesUniverseValues[2]; 
        attrValsCareer[0]=new AttributesUniverseValues("IT",new String[]{},true);
        attrValsCareer[1]=new AttributesUniverseValues("Math",new String[]{},true);
        
        //Array of attributes values arrays
        Object[] attrVals={attrValsOccupation,attrValsCareer};
        /**
         * END generateAttributesUniverse EXAMPLE DATA
         */
        AttributesManagement.generateAttributesUniverse(attributes,attrVals);
    }
    
    public static void addAttributesToUniverse_example(){
        /**
         * BEGIN addAttributesToUniverse EXAMPLE DATA
         */
        //Add attribute value
        String[][] attributes={{"department","string","faculties_infrastructure"},{"building","string","faculties_infrastructure"}};
        //Department attributes
        AttributesUniverseValues[] attrValsDept=new AttributesUniverseValues[4]; 
        attrValsDept[0]=new AttributesUniverseValues("Laboratory 1",new String[]{},true);
        attrValsDept[1]=new AttributesUniverseValues("Laboratory 2",new String[]{},true);
        attrValsDept[2]=new AttributesUniverseValues("IA",new String[]{},true);
        attrValsDept[3]=new AttributesUniverseValues("Robotics",new String[]{},true);
        //Building attributes
        AttributesUniverseValues[] attrValsBuild=new AttributesUniverseValues[2]; 
        attrValsBuild[0]=new AttributesUniverseValues("Central Building",new String[]{},true);
        attrValsBuild[1]=new AttributesUniverseValues("East Building",new String[]{},true);
        
        //Array of attributes values arrays
        Object[] attrVals={attrValsDept,attrValsBuild};
        /**
         * END addAttributesToUniverse EXAMPLE DATA
         */
        
        AttributesManagement.addAttributesToUniverse(attributes,attrVals);
    }
    
    public static void addAttributeValue_example(){
        /**
         * BEGIN addAttributeValue EXAMPLE DATA
         */
        String attribute="building";
        String[] values={"West Building"};
        String[][] constraints=new String[][]{{},{},{}};
        boolean[] statuses={true};
        /**
         * END addAttributeValue EXAMPLE DATA
         */
        AttributesManagement.addAttributeValue(attribute,values,constraints,statuses);        
    }
    
    public static void modifyAttribute_example(){
        /**
         * BEGIN modifyAttribute EXAMPLE DATA
         */
        String attribute="building";
        String value="West Building";
        String[] constraints=new String[]{}; 
        boolean status=false;
        /**
         * END modifyAttribute EXAMPLE DATA
         */
        AttributesManagement.modifyAttributeValue(attribute,value,constraints,status);
    }
    
    public static void removeAttribute_example(){
        /**
         * BEGIN removeAttribute EXAMPLE DATA
         */
        String attribute="building"; //boolean status=true;
        /**
         * END removeAttribute EXAMPLE DATA
         */
        AttributesManagement.removeAttribute(attribute);
    }
    
    /**
     * USERS' ATTRIBUTES MANAGEMENT
     */
    public static void generateUserAttributes_example() throws JSONException, UnknownHostException{
        /**
         * BEGIN generateUserAttributes EXAMPLE DATA
         */
        UserAttributes[] userSt1_Su=new UserAttributes[2];
        userSt1_Su[0]=new UserAttributes("occupation","student",true);
        userSt1_Su[1]=new UserAttributes("career","IT",true);
        UserAttributes[] userSt2_Su={new UserAttributes("occupation","student",true)};
        UserAttributes[] userSt3_Su={new UserAttributes("occupation","student",true)};
        UserAttributes[] userPr1_Su={new UserAttributes("occupation","professor",true)};
        UserAttributes[] userPr2_Su={new UserAttributes("occupation","professor",true)};
        UserAttributes[] userAs1_Su={new UserAttributes("occupation","assistant",true)};
        
        String[] usersID={"userSt1","userSt2","userSt3","userPr1","userPr2","userAs1"};
        UserAttributes[][] usersAttributes={userSt1_Su,userSt2_Su,userSt3_Su,userPr1_Su,userPr2_Su,userAs1_Su};
        User[] users=UsersManagement.createUsers(usersID,usersAttributes);
        /**
         * END generateUserAttributes EXAMPLE DATA
         */
        
        //Users' attributes assignments
        for(User user:users)
            AttributesManagement.generateUserAttributes(user);
    }
    
    public static void addUserAttributes_example() throws UnknownHostException, JSONException{
        /**
         * BEGIN addUserAttributes EXAMPLE DATA
         */
        String user="userPr1";
        UserAttributes[] userPr1_Su=new UserAttributes[2];
        userPr1_Su[0]=new UserAttributes("career","Math",true);
        userPr1_Su[1]=new UserAttributes("department","Laboratory 1",true);
        /**
         * END addUserAttributes EXAMPLE DATA
         */
        AttributesManagement.addUserAttributes(user,userPr1_Su);
    }
    
    public static void removeUserAttribute_example(){
        /**
         * BEGIN removeUserAttribute EXAMPLE DATA
         */
        String user="userPr1";
        String attribute="department";
        String value="Laboratory 1";
        /**
         * END removeUserAttribute EXAMPLE DATA
         */
        AttributesManagement.removeUserAttribute(user,attribute,value);
    }
    
    public static void displayUserAttributes(String user){
        StructuresDisplay.displayUserAttributes(user,"JSON ");
    }
    
    public static void displayUserActiveAttributes(String user){
        StructuresDisplay.displayUserActiveAttributes(user);
    }
    
    /**
     * ACCESS CONTROL POLICIES MANAGEMENT
     */
    public static void generateFilePolicy_example(){
        /**
         * BEGIN generateFilePolicy EXAMPLE DATA
         */
        //Files specifications
        String[] files=new String[]{"file5.txt"};
        String[] thresholds=new String[]{"2of2","1of2","1of3"};
        String[][] revokedUsers=new String[][]{{}};
        //Available attributes
        String[] occupationValues=new String[]{"student","professor","assistant"};
        String[] careerValues=new String[]{"IT","Math"};
        String[] departmentValues=new String[]{"Laboratory 1","Laboratory 2","IA"};
        String[] buildingValues=new String[]{"Central Building","East Building"};
        
        //Write created policies
        for(int i=0;i<files.length;i++){
            //Populate policy attributes values
            PoliciesAttributesValues[] policyAttributes=new PoliciesAttributesValues[2];
            int index=new Random().nextInt(occupationValues.length);
            policyAttributes[0]=new PoliciesAttributesValues("occupation",occupationValues[index],true);
            index=new Random().nextInt(careerValues.length);
            policyAttributes[1]=new PoliciesAttributesValues("career",careerValues[index],true);
            //Create policy
            FilePolicy policy=new FilePolicy(policyAttributes,thresholds[0]);
        
            PoliciesAttributesValues[] policyAttributes_=new PoliciesAttributesValues[2];
            index=new Random().nextInt(departmentValues.length);
            policyAttributes_[0]=new PoliciesAttributesValues("department",departmentValues[index],true);
            index=new Random().nextInt(buildingValues.length);
            policyAttributes_[1]=new PoliciesAttributesValues("building",buildingValues[index],true);
            //Create policy
            FilePolicy policy_=new FilePolicy(policyAttributes_,thresholds[1]);
            
            
            PoliciesAttributesValues[] policyAttributes_File=new PoliciesAttributesValues[3]; 
            policyAttributes_File[0]=new PoliciesAttributesValues(policy,true);
            policyAttributes_File[1]=new PoliciesAttributesValues(policy_,true);
            index=new Random().nextInt(occupationValues.length);
            policyAttributes_File[2]=new PoliciesAttributesValues("occupation",occupationValues[index],true);
        /**
         * END generateFilePolicy EXAMPLE DATA
         */
            PoliciesManagement.generateFilePolicy(files[i],policyAttributes_File,thresholds[2],revokedUsers[i]);
        }
    }
    
    public static void generateSimpleFilePolicy_example(){
        /**
         * BEGIN generateFilePolicy EXAMPLE DATA
         */
        //Files specifications
        String[] files=new String[]{"file1.txt","file2.txt","file3.txt","file4.txt"};
        String[] thresholds=new String[]{"2of2","1of2","1of2","2of2"};
        String[][] revokedUsers=new String[][]{{},{"userPr2"},{"userSt1"},{"userSt2"}};
        //Available attributes
        String[] occupationValues=new String[]{"student","professor","assistant"};
        String[] careerValues=new String[]{"IT","Math"};
        
        //Write created policies
        for(int i=0;i<files.length;i++){
            //Populate policy attributes values
            PoliciesAttributesValues[] policyAttributes=new PoliciesAttributesValues[2];
            int index=new Random().nextInt(occupationValues.length);
            policyAttributes[0]=new PoliciesAttributesValues("occupation",occupationValues[index],true);
            index=new Random().nextInt(careerValues.length);
            policyAttributes[1]=new PoliciesAttributesValues("career",careerValues[index],true);
        /**
         * END generateFilePolicy EXAMPLE DATA
         */
            PoliciesManagement.generateFilePolicy(files[i],policyAttributes,thresholds[i],revokedUsers[i]);
        }
        displayFilesPolicy();
    }
    
    public static void addAttributesToPolicy_example(){
        /**
         * BEGIN addAttributesToPolicy EXAMPLE DATA
         */
        String file="file2.txt";
        PoliciesAttributesValues[] policyAttributes={new PoliciesAttributesValues("building","Central Building",true)};
        String threshold=""; 
        /**
         * END addAttributesToPolicy EXAMPLE DATA
         */
        PoliciesManagement.addAttributesToPolicy(file,policyAttributes,threshold); //,constraints,revokedUsers);
        StructuresDisplay.displayFilePolicy(file,"JSON ");
    }
    
    public static void removePolicyAttribute_example(){
        /**
         * BEGIN removePolicyAttributes EXAMPLE DATA
         */
        String file="file2.txt";
        String attribute="career";
        String value="IT";
        String threshold="";
        /**
         * END removePolicyAttributes EXAMPLE DATA
         */
        PoliciesManagement.removePolicyAttribute(file,attribute,value,threshold);
        StructuresDisplay.displayFilePolicy(file,"JSON ");
    }
    
    public static void verifyUserAccessibility_example(){
        /**
         * BEGIN verifyUserAccessibility EXAMPLE DATA
         */
        String[] users=new String[]{"userAs1","userPr1","userPr2","userSt1","userSt2","userSt3"};
        String[] files=new String[]{"file5.txt"};
        /**
         * END verifyUserAccessibility EXAMPLE DATA
         */
        
        for(String user:users){
            for(String file:files){
                System.out.println(user+" - "+file);
                System.out.println(Accessibility.verifyUserAccessibility(user,file));
            } System.out.println("");
        }
    }
    
    public static void displayFilesPolicy(){
        String[] files=new String[]{"file1.txt","file2.txt","file3.txt","file4.txt"};
        
        //Show file policy
        for(String file:files)
            StructuresDisplay.displayFilePolicy(file,"JSON ");
    }
    
    public static void displayComplexFilePolicy(){
        String[] files=new String[]{"file0.txt","file1.txt","file2.txt","file3.txt","file4.txt","file5.txt"};
        
        //Show file policy
        for(String file:files)
            StructuresDisplay.displayComplexFilePolicy(file,"JSON");
    }
    
    public static void displayPolicyActiveAttributes(){
        String[] files=new String[]{"file1.txt","file2.txt","file3.txt","file4.txt"};
        
        //Show file policy
        for(String file:files)
            StructuresDisplay.displayPolicyActiveAttributes(file);
    }
    
    
    public static void executeExample() throws JSONException, UnknownHostException, IOException{
        //Attributes Universe management
        generateAttributesUniverse_example();
        addAttributesToUniverse_example();
        addAttributeValue_example();       
        modifyAttribute_example();        
        removeAttribute_example();
        //Display
        StructuresDisplay.displayActiveAttributesUniverse();
        StructuresDisplay.displayActiveAttributesValues();
        
        //Access control policies management
        generateFilePolicy_example();
        addAttributesToPolicy_example();
        removePolicyAttribute_example();
        //Display
        displayComplexFilePolicy();
        displayPolicyActiveAttributes();
        
        //User attributes management
        generateUserAttributes_example();
        addUserAttributes_example();
        removeUserAttribute_example();
        //Display
        displayUserAttributes("userPr1");
        displayUserActiveAttributes("userPr1");
        verifyUserAccessibility_example();
    }


    /**
     * CASE STUDY
     */
    
    public static void generateAttributesUniverse_eHealth() throws JSONException, IOException{
        /**
         * BEGIN generateAttributesUniverse EXAMPLE DATA
         */
        //Attributes to be added
        String[][] attributes={{"occupation","string","staff"},{"specialty","string","medical_specialty"},{"sub-specialty","string","medical_sub-specialties"},
            {"department","string","infrastructure"},{"institution","string","infrastructure"}};
        
        //Occupation attributes
        AttributesUniverseValues[] attrValsOccupation=new AttributesUniverseValues[]{new AttributesUniverseValues("physician",new String[]{},true),
            new AttributesUniverseValues("resident",new String[]{},true),new AttributesUniverseValues("nurse",new String[]{},true),
            new AttributesUniverseValues("medical_assistant",new String[]{},true),new AttributesUniverseValues("medical_researcher",new String[]{},true)};
        //Specialty attributes
        AttributesUniverseValues[] attrValsSpecialty=new AttributesUniverseValues[]{new AttributesUniverseValues("radiology",new String[]{},true),
            new AttributesUniverseValues("oncology",new String[]{},true),new AttributesUniverseValues("gynecology",new String[]{},true),
            new AttributesUniverseValues("orthopedics",new String[]{},true),new AttributesUniverseValues("internal_medicine",new String[]{},true),
            new AttributesUniverseValues("pathology",new String[]{},true)};
        //Sub-specialty attributes
        AttributesUniverseValues[] attrValsSubSpecialty=new AttributesUniverseValues[]{new AttributesUniverseValues("nuclear_medicine",new String[]{},true),
            new AttributesUniverseValues("interventional_radiology",new String[]{},true),new AttributesUniverseValues("thoracic_oncology",new String[]{},true),
            new AttributesUniverseValues("breast_oncology",new String[]{},true),new AttributesUniverseValues("musculoskeletal_oncology",new String[]{},true),
            new AttributesUniverseValues("rheumatology",new String[]{},true),new AttributesUniverseValues("anatomical_pathology",new String[]{},true),
            new AttributesUniverseValues("clinical_pathology",new String[]{},true)};
        //Department attributes
        AttributesUniverseValues[] attrValsDepartment=new AttributesUniverseValues[]{new AttributesUniverseValues("medical_imaging",new String[]{},true),
            new AttributesUniverseValues("oncology",new String[]{},true),new AttributesUniverseValues("gynecology",new String[]{},true),
            new AttributesUniverseValues("orthopedics",new String[]{},true),new AttributesUniverseValues("pathology",new String[]{},true),
            new AttributesUniverseValues("clinical_trials",new String[]{},true),new AttributesUniverseValues("emergency_medicine",new String[]{},true)};
        //Institution attributes
        AttributesUniverseValues[] attrValsInstitution=new AttributesUniverseValues[]{new AttributesUniverseValues("INR",new String[]{},true),
            new AttributesUniverseValues("IMSS",new String[]{},true),new AttributesUniverseValues("ISSSTE",new String[]{},true),
            new AttributesUniverseValues("OCA",new String[]{},true),new AttributesUniverseValues("HU",new String[]{},true)};
        
        //Array of attributes values arrays
        Object[] attrVals={attrValsOccupation,attrValsSpecialty,attrValsSubSpecialty,attrValsDepartment,attrValsInstitution};
        /**
         * END generateAttributesUniverse EXAMPLE DATA
         */
        AttributesManagement.generateAttributesUniverse(attributes,attrVals);
    }
    
    public static void generateUserAttributes_eHealth() throws JSONException, UnknownHostException{
        /**
         * BEGIN generateUserAttributes EXAMPLE DATA
         */
        //User 1
        UserAttributes[] userMD1_Su=new UserAttributes[]{new UserAttributes("occupation","physician",true),new UserAttributes("specialty","oncology",true),
            new UserAttributes("sub-specialty","thoracic_oncology",true),new UserAttributes("department","oncology",true),
            new UserAttributes("institution","IMSS",true)};
        //User 2
        UserAttributes[] userMD2_Su=new UserAttributes[]{new UserAttributes("occupation","physician",true),new UserAttributes("specialty","oncology",true),
            new UserAttributes("sub-specialty","musculoskeletal_oncology",true),new UserAttributes("department","orthopedics",true),
            new UserAttributes("institution","ISSSTE",true)};
        //User 3
        UserAttributes[] userMD3_Su=new UserAttributes[]{new UserAttributes("occupation","physician",true),new UserAttributes("specialty","internal_medicine",true),
            new UserAttributes("department","emergency_medicine",true)};
        //User 4
        UserAttributes[] userNU1_Su=new UserAttributes[]{new UserAttributes("occupation","nurse",true),new UserAttributes("specialty","oncology",true),
            new UserAttributes("sub-specialty","thoracic_oncology",true),new UserAttributes("department","oncology",true),
            new UserAttributes("institution","IMSS",true)};
        //User 5
        UserAttributes[] userNU2_Su=new UserAttributes[]{new UserAttributes("occupation","nurse",true),new UserAttributes("specialty","oncology",true),
            new UserAttributes("sub-specialty","breast_oncology",true),new UserAttributes("department","gynecology",true)};
        //User 6
        UserAttributes[] userNU3_Su=new UserAttributes[]{new UserAttributes("occupation","nurse",true),new UserAttributes("specialty","oncology",true),
            new UserAttributes("sub-specialty","breast_oncology",true),new UserAttributes("department","oncology",true)};
        //User 7
        UserAttributes[] userNU4_Su=new UserAttributes[]{new UserAttributes("occupation","nurse",true),new UserAttributes("specialty","pathology",true),
            new UserAttributes("sub-specialty","clinical_pathology",true),new UserAttributes("department","pathology",true),
            new UserAttributes("institution","OCA",true)};
        //User 8
        UserAttributes[] userMR1_Su=new UserAttributes[]{new UserAttributes("occupation","medical_researcher",true),new UserAttributes("specialty","pathology",true),
            new UserAttributes("sub-specialty","anatomical_pathology",true),new UserAttributes("department","clinical_trials",true)};
        //User 9
        UserAttributes[] userMR2_Su=new UserAttributes[]{new UserAttributes("occupation","medical_researcher",true),new UserAttributes("specialty","radiology",true),
            new UserAttributes("sub-specialty","nuclear_medicine",true),new UserAttributes("department","medical_imaging",true)};
        //User 10
        UserAttributes[] userMA1_Su=new UserAttributes[]{new UserAttributes("occupation","medical_assistant",true),new UserAttributes("specialty","radiology",true),
            new UserAttributes("sub-specialty","interventional_radiology",true)};
        
        
        String[] usersID={"userMD1_Su","userMD2_Su","userMD3_Su","userNU1_Su","userNU2_Su","userNU3_Su","userNU4_Su","userMR1_Su","userMR2_Su","userMA1_Su"};
        UserAttributes[][] usersAttributes={userMD1_Su,userMD2_Su,userMD3_Su,userNU1_Su,userNU2_Su,userNU3_Su,userNU4_Su,userMR1_Su,userMR2_Su,userMA1_Su};
        User[] users=UsersManagement.createUsers(usersID,usersAttributes);
        /**
         * END generateUserAttributes EXAMPLE DATA
         */
        
        //Users' attributes assignments
        for(User user:users)
            AttributesManagement.generateUserAttributes(user);
    }
    
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
            PoliciesManagement.generateFilePolicy(files[i],(PoliciesAttributesValues[])policiesVals[i],thresholds[i],revokedUsers);
            StructuresDisplay.displayComplexFilePolicy(files[i],"JSON ");
        }
    }
    
    public static void testABEtranslator(String file,String construction){
        FilePolicy filePolicy=PoliciesManagement.getFilePolicy(file);
        
        if(construction.equals("BSW07")){
            //Get and display access control policy
            String policyStr=PolicyTranslator.getBSW07PolicyAttributesValues(filePolicy,"")+filePolicy.getThreshold();
            System.out.println("BSW07 policy: \n"+policyStr);
        }else{
            //Get and display access control policy
            Object fbf=PolicyTranslator.getW11PolicyAttributesValues(filePolicy,new Object());
            System.out.println("W11 policy: \n"+fbf+"\n");
        }
    }
    
    public static void translatePolicies(){
        String[] files=new String[]{"file1.dcm","file2.dcm","file3.dcm","file4.dcm","file5.dcm","file6.dcm","file7.dcm","file8.dcm","file9.dcm","file10.dcm"};
        String[] constructions={"BSW07","W11"};
        
        for(String file:files){
            System.out.println(file);
            for(String construction:constructions)
                testABEtranslator(file,construction);
        }
    }

    public static void main(String[] args) throws JSONException, IOException, ParseException{
        /**
         * TESTS
         */
       // //Example
       // executeExample();
        
        //Case study
        generateAttributesUniverse_eHealth();
        generateUserAttributes_eHealth();
        generateFilePolicy_eHealth();
        
        translatePolicies();
    }
}
