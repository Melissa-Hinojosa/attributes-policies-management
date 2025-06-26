import attributes.AttributesUniverseValues;
import attributes.PoliciesAttributesValues;
import attributes.UserAttributes;
import org.json.JSONException;
import java.io.IOException;
import java.net.UnknownHostException;
import system.AttributesManagement;
import system.PoliciesManagement;
import system.User;
import system.UsersManagement;

/**
 * Class used for testing attributes and policies management in FABECS (DET-ABE+AES4SeC).
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2025-05-30
*/
public class MainPerformanceEval {
    /**
     * Convert index to alphabetical strings (A-Z, AA, AB, ...)
     */
    private static String getAlphabeticString(int index) {
        StringBuilder sb = new StringBuilder();
        index++; // Adjust index to 0='A'
        while (index > 0) {
            index--;
            sb.insert(0, (char) ('A' + (index % 26)));
            index /= 26;
        }
        return sb.toString();
    }
    
    /****************************************
     * ATTRIBUTES UNIVERSE MANAGEMENT
     ****************************************/
    /**
     * Generate an attribute universe of n attributes with m values each one
     * @param attributeCount
     * @param valuesPerAttribute
     * @throws JSONException
     * @throws IOException 
     */
    public static void generateAttributeUniverse(int attributeCount, int valuesPerAttribute) throws JSONException, IOException {
        //Attributes to be added to the universe
        String[][] attributes = new String[attributeCount][3];
        //Array of attribute values
        Object[] attrVals = new Object[attributeCount];
        
        // Generate n attributes in the attribute universe
        for (int i = 0; i < attributeCount; i++) {
            String attrName = getAlphabeticString(i);
            String value = getAlphabeticString(i);
            attributes[i] = new String[]{attrName, "string", value};

            // Generate m values per attribute
            AttributesUniverseValues[] valuesArray = new AttributesUniverseValues[valuesPerAttribute];
            for (int j = 0; j < valuesPerAttribute; j++) {
                String val = getAlphabeticString(i * valuesPerAttribute + j);
                valuesArray[j] = new AttributesUniverseValues(val, new String[]{}, true);
            }
            attrVals[i] = valuesArray;
        }
        
        //Store attribute universe
        String note="_U"+attributeCount+"V"+valuesPerAttribute;
        AttributesManagement.generateAttributesUniverse(attributes,attrVals,note);
    }
    
    /**
     * Test method: attribute universe generation
     * @throws JSONException
     * @throws IOException 
     */
    public static void testAttributeUniverse() throws JSONException, IOException{
        //Test settings
//        int[] attributeSizes = {10, 20, 50}; int[] valuesPerAttributeOptions = {5, 10, 15}; //Small-scale universe
//        int[] attributeSizes = {50, 100, 200}; int[] valuesPerAttributeOptions = {5, 10, 15}; //Medium-scale universe
        int[] attributeSizes = {50,100,200}; int[] valuesPerAttributeOptions = {50,50,50}; //Large-scale universe

        //Generate attribute universe
        for (int numAttributes : attributeSizes) {
            for (int numValuesAttributes : valuesPerAttributeOptions) {
                System.out.println("\n\n|U|=" + numAttributes + ", num. values=" + numValuesAttributes);
                for(int j=0;j<100;j++)
                    generateAttributeUniverse(numAttributes, numValuesAttributes);
            }
        }
    }
    
    
    /****************************************
     * USERS' ATTRIBUTES MANAGEMENT
     ****************************************/
    /**
     * Generate an attribute set with n attributes 
     * @param attributeCount
     * @throws JSONException
     * @throws UnknownHostException 
     */
    public static void generateUserAttributes(int attributeCount) throws JSONException, UnknownHostException{
        /**
         * BEGIN generateUserAttributes EXAMPLE DATA
         */
        // Generate n attributes in the attribute set
        UserAttributes[] userAttributes=new UserAttributes[attributeCount]; 
        for (int i = 0; i < attributeCount; i++) {
            String name = getAlphabeticString(i);
            String value = name;
            userAttributes[i] = new UserAttributes(name, value, true);
        }

        //Set user identifier and attributes
        String userID="user1-"+attributeCount+"_Su";
        UserAttributes[][] usersAttributes={userAttributes};
        User[] users=UsersManagement.createUsers(new String[]{userID},usersAttributes);
        /**
         * END generateUserAttributes EXAMPLE DATA
         */
        
        //Store user's attribute set
        String note="_Su"+attributeCount;
        for(User user:users)
            AttributesManagement.generateUserAttributes(user,note);
    }
    
    /**
     * Test method: attribute universe generation
     * @throws JSONException
     * @throws IOException 
     */
    public static void testAttributeSetsGeneration() throws JSONException, IOException{
        //Test settings
//        int[] attributeSizes = {10, 20, 50}; //Small-scale attribute set
        int[] attributeSizes = {50,100,200}; //Medium-scale attribute set
        
        //Generate attribute set
        for (int numAttributes : attributeSizes)
            for(int j=0;j<100;j++)
                generateUserAttributes(numAttributes);        
    }
    
    
    /****************************************
     * ACCESS CONTROL POLICIES MANAGEMENT
     ****************************************/
    /**
     * 
     * @param attributeCount 
     */
    public static void generateFilePolicy(int attributeCount) {
        //Set file identifier
        String file = "file1-"+attributeCount+".txt";

        //Set threshold (n-of=n)
        String threshold = attributeCount + "of" + attributeCount;

        //Set array of revoked users
        String[] revokedUsers = new String[]{};

        // Generate n attributes in the access policy
        PoliciesAttributesValues[] policyAttributes = new PoliciesAttributesValues[attributeCount];
        for (int i = 0; i < attributeCount; i++) {
            String attrName = getAlphabeticString(i);
            String attrValue = attrName;
            policyAttributes[i] = new PoliciesAttributesValues(attrName, attrValue, true);
        }

        //Store access policy
        String note="_AP"+attributeCount;
        PoliciesManagement.generateFilePolicy(file, policyAttributes, threshold, revokedUsers, note);
    }
    
    /**
     * Test method: attribute universe generation
     * @throws JSONException
     * @throws IOException 
     */
    public static void testPoliciesGeneration() throws JSONException, IOException{
        //Test settings
//        int[] attributeSizes = {10, 20, 50}; //Small-scale access policy
        int[] attributeSizes = {50,100,200}; //Medium-scale access policy
        
        //Generate access policy
        for (int numAttributes : attributeSizes)
            for(int j=0;j<100;j++)
                generateFilePolicy(numAttributes);
    }
    
    
    /**
     * Main method
     * @param args 
     * @throws org.json.JSONException 
     * @throws java.io.IOException 
     */
    public static void main(String[] args) throws JSONException, IOException{
        //Execute performance tests
        testAttributeUniverse();
        testAttributeSetsGeneration();
        testPoliciesGeneration();
    }
}
