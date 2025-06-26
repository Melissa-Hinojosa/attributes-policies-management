import attributes.AttributesUniverseValues;
import attributes.PoliciesAttributesValues;
import attributes.UserAttributes;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import policies.FilePolicy;
import system.Accessibility;
import system.AttributesManagement;
import system.PoliciesManagement;
import system.User;
import system.UsersManagement;
import utils.StructuresDisplay;

/**
 * Class used for testing.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-03-28
*/
public class MainExample {
    public static String usersAttributesJSON="_attributes.json";
    public static String filePolicyJSON="_policy.json";
    
    /****************************************
     * ATTRIBUTES UNIVERSE MANAGEMENT
     ****************************************/
    /**
     * Generate attribute universe (\mathbb{U})
     * @throws JSONException
     * @throws IOException 
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
        AttributesManagement.generateAttributesUniverse(attributes,attrVals,"");
    }
    
    /**
     * Update attribute universe: add attributes and its values 
     * (new {attributes,values} to the already existing \mathbb{U})
     */
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
    
    /**
     * Update attribute universe: add values to attributes
     * (new values to already existing attributes in \mathbb{U})
     */
    public static void addAttributeValue_example(){
        /**
         * BEGIN addAttributeValue EXAMPLE DATA
         */
        String attribute="building";
        String[] values={"West Building"};
        String[][] constraints=new String[][]{{},{},{}};
        boolean[] statuses={true};
//        //Three-value example
//        String attribute="building";
//        String[] values={"West Building","North Building", "South Building"};
//        String[][] constraints=new String[][]{{"weekdays","currentTime>=08:00:00","currentTime<=20:00:00"},{},{"weekdays","currentTime>=10:00:00","currentTime<=18:00:00"}};
//        boolean[] statuses={true,true,true};
        /**
         * END addAttributeValue EXAMPLE DATA
         */
        AttributesManagement.addAttributeValue(attribute,values,constraints,statuses);        
    }
    
    /**
     * Update attribute universe: modify attribute
     * (update constraints or status for a given value of an already existing attribute in \mathbb{U})
     */
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
    
    /**
     * Update attribute universe: remove attribute
     * (remove an attribute, and its values, from \mathbb{U}, i.e., status=false)
     */
    public static void removeAttribute_example(){
        /**
         * BEGIN removeAttribute EXAMPLE DATA
         */
        String attribute="building";
        /**
         * END removeAttribute EXAMPLE DATA
         */
        AttributesManagement.removeAttribute(attribute);
    }
    
    /****************************************
     * USERS' ATTRIBUTES MANAGEMENT
     ****************************************/
    /**
     * Generate user's attribute sets (\mathbf{S}_u)
     * @throws JSONException
     * @throws UnknownHostException 
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
            AttributesManagement.generateUserAttributes(user,"");
    }
    
    /**
     * Update attribute set: add attributes and its values 
     * (new {attributes,values} to the already existing \mathbf{S}_u)
     */
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
    
    /**
     * Update attribute set: remove attribute
     * (remove an attribute, and its value, from \mathbf{S}_u, i.e., status=false)
     */
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
    
    /**
     * Display the whole user's attribute set (including inactive attributes)
     * @param user 
     */
    public static void displayUserAttributes(String user){
        /**
         * BEGIN displayUserAttributes EXAMPLE DATA
         */
        StructuresDisplay.displayUserAttributes(user,"JSON ");
        /**
         * END displayUserAttributes EXAMPLE DATA
         */        
    }
    
    /**
     * Display user's attribute set (only including active attributes)
     * @param user 
     */
    public static void displayUserActiveAttributes(String user){
        /**
         * BEGIN displayUserActiveAttributes EXAMPLE DATA
         */
        StructuresDisplay.displayUserActiveAttributes(user);
        /**
         * END displayUserActiveAttributes EXAMPLE DATA
         */        
    }
    
    /****************************************
     * ACCESS CONTROL POLICIES MANAGEMENT
     ****************************************/
    /**
     * Generate access control policies (AP): generate a random policy from a 
     * given set of attributes in \mathbb{U} (including nested policies)
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
            PoliciesManagement.generateFilePolicy(files[i],policyAttributes_File,thresholds[2],revokedUsers[i],"");
        }
    }
    
    /**
     * Generate access control policies (AP): generate a random policy from a 
     * given set of attributes in \mathbb{U} (not including nested policies)
     */
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
            PoliciesManagement.generateFilePolicy(files[i],policyAttributes,thresholds[i],revokedUsers[i],"");
        }

        //Display
        displayFilesPolicy(new String[]{"file1.txt","file2.txt","file3.txt","file4.txt"});
    }
    
    /**
     * Update access control policy: add attribute
     * (add an attribute, and its value, to an already existing policy AP)
     */
    public static void addAttributesToPolicy_example(){
        /**
         * BEGIN addAttributesToPolicy EXAMPLE DATA
         */
        String file="file2.txt";
//        //One-attribute example
//        PoliciesAttributesValues[] policyAttributes={new PoliciesAttributesValues("building","Central Building",true)};
        //Two-attribute example
        PoliciesAttributesValues[] policyAttributes=new PoliciesAttributesValues[2];
        policyAttributes[0]=new PoliciesAttributesValues("department","Laboratory 1",true);
        policyAttributes[1]=new PoliciesAttributesValues("building","Central Building",true);
        String threshold="2of4";
        /**
         * END addAttributesToPolicy EXAMPLE DATA
         */
        PoliciesManagement.addAttributesToPolicy(file,policyAttributes,threshold);
        StructuresDisplay.displayFilePolicy(file,"JSON ");
    }
    
    /**
     * Update access control policy: remove attribute
     * (remove an attribute, and its value, from an already existing policy AP)
     */
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
    
    /**
     * Check if a user is allowed to access a given ciphertext
     */
    public static void verifyUserAccessibility_example(){
        /**
         * BEGIN verifyUserAccessibility EXAMPLE DATA
         */
        String[] users=new String[]{"userAs1","userPr1","userPr2","userSt1","userSt2","userSt3"};
        String[] files=new String[]{"file3.txt"};
        /**
         * END verifyUserAccessibility EXAMPLE DATA
         */
        
        //Display user-file accessibility results
        for(String user:users){
            for(String file:files){
                System.out.println(user+" - "+file);
                System.out.println(Accessibility.verifyUserAccessibility(user,file));
            } System.out.println("");
        }
    }
    
    /**
     * Display simple access control policies (not including nested policies)
     * @param files
     */
    public static void displayFilesPolicy(String[] files){
        //Show file policy
        for(String file:files)
            StructuresDisplay.displayFilePolicy(file,"JSON ");
    }
    
    /**
     * Display complex access control policies (including nested policies)
     * @param files
     */
    public static void displayComplexFilePolicy(String[] files){        
        //Show file policy
        for(String file:files)
            StructuresDisplay.displayComplexFilePolicy(file,"JSON");
    }
    
    /**
     * Display simple access control policies (including only active attributes)
     * @param files
     */
    public static void displayPolicyActiveAttributes(String[] files){        
        //Show file policy
        for(String file:files)
            StructuresDisplay.displayPolicyActiveAttributes(file);
    }
    
    /**
     * Main method
     * @param args 
     * @throws org.json.JSONException 
     * @throws java.io.IOException 
     * @throws org.json.simple.parser.ParseException 
     */
    public static void main(String[] args) throws JSONException, IOException, ParseException{
        /**
         * TESTS
         */
        //Attribute universe management
        generateAttributesUniverse_example();
        addAttributesToUniverse_example();
        addAttributeValue_example();       
        modifyAttribute_example();        
        removeAttribute_example();
        //Display attribute universe
        StructuresDisplay.displayActiveAttributesUniverse();
        StructuresDisplay.displayActiveAttributesValues();
        
        //Access control policies management
        generateFilePolicy_example();
        generateSimpleFilePolicy_example();
        addAttributesToPolicy_example();
        removePolicyAttribute_example();
        //Display access control policies
        displayComplexFilePolicy(new String[]{"file0.txt","file1.txt","file2.txt","file3.txt","file4.txt","file5.txt"});
        displayPolicyActiveAttributes(new String[]{"file1.txt","file2.txt","file3.txt","file4.txt"});
        
        //User attributes management
        generateUserAttributes_example();
        addUserAttributes_example();
        removeUserAttribute_example();
        //Display attribute sets
        displayUserAttributes("userPr1");
        displayUserActiveAttributes("userPr1");
        verifyUserAccessibility_example();
    }
}
