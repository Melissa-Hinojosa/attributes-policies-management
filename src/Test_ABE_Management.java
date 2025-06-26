import attributes.UserAttributes;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import policies.FilePolicy;
import src.cpabe.waters.policy.FBF;
import src.entities.DO.DOOperations;
import src.entities.DU.DU;
import src.utilABE.Files;
import system.AttributesManagement;
import system.PoliciesManagement;
import utils.PolicyTranslator;
import utils.StructuresDisplay;

/**
 * Class used for testing attributes and policies management in FABECS (DET-ABE+AES4SeC).
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2025-03-16
*/
public class Test_ABE_Management {
    /**
     * ANSI colors
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    //Constant parameters
    public static int[] secLevel={128,150,160,192,256}; //Security level \lambda
    public static String[] type={"A","F"}; //EC curve type
    public static String[] construction={"BSW07","W11"}; //ABE construction
    public static String language="english"; //Indexing language
    
    /**
     * Get user's attribute set.
     * @param userID
     * @return userAttributes
     */
    public static List<String> getUserActiveAttributes(String userID){
        //UserAttributes structure
        UserAttributes[] attributes=AttributesManagement.getUserAttributes(userID);
        //User's attributes set (S_u)
        List<String> userAttributes = new LinkedList<String>();
        
        //Populate  S_u only with user's active attributes
        for (UserAttributes attribute:attributes)
            if(attribute.getStatus())
                userAttributes.add(attribute.getValue());
        
        return userAttributes;
    }
    
    /**
     * Create user's secret key (CP-ABE.KeyGen).
     * @param user 
     * @param secLevel 
     * @param type 
     * @param construction 
     * @return SK_u
     */
    public static Object createSKu(String user,int secLevel,String type,String construction){
        //Get user's attributes set S_u
        List<String> userAttributes=getUserActiveAttributes(user);
        //Display user's S_u
        System.out.println("\n"+ANSI_BLUE+"User Attributes".toUpperCase()+": \n"+userAttributes+ANSI_RESET);
        
        System.out.println("\n"+ANSI_BLUE+"=== Secret Key Generation ===".toUpperCase()+ANSI_RESET);
        System.out.println("DET-ABE DECRYPTION MODULE: Asking the Trusted Authority the private key for the given set of attributes...");
        if(construction.equals("BSW07")){ //Get ABE {PK,MK} for BSW07 construction
            //CP-ABE.Setup
            src.cpabe.bsw.common.PublicKey PK=new src.cpabe.bsw.common.PublicKey(secLevel,type);
            src.trustedAuth.bsw.MasterKey MK=new src.trustedAuth.bsw.MasterKey(secLevel,type);
            
            //Generate BSW07 user's SK_u
            src.cpabe.bsw.common.ABEPrivateKey prv = src.trustedAuth.bsw.ABETrustedAuthority.ABEkeyGen(PK,MK,userAttributes);
            //Display user's SK_u
            System.out.println("\n"+ANSI_BLUE+"USER SK_u: "+ANSI_RESET); prv.show(); 

            if(prv == null){
               System.out.println("DET-ABE DECRYPTION MODULE: Unable to generate the private key from TA...\nDecryption process failed.");
               System.exit(0);
            }
            return prv;
        } else{ //Get ABE {PK,MK} for W11 construction
            //CP-ABE.Setup
            src.cpabe.waters.g1.PublicKey PK=new src.cpabe.waters.g1.PublicKey(secLevel,type);
            src.trustedAuth.waters11.g1.MasterKey MK=new src.trustedAuth.waters11.g1.MasterKey(secLevel,type);
            
            //Generate W11 user's SK_u
            src.cpabe.waters.g1.ABEPrivateKey prv = src.trustedAuth.waters11.g1.ABETrustedAuthority.keyGen(PK,MK,userAttributes);
            //Display user's SK_u
            System.out.println("\n"+ANSI_BLUE+"USER SK_u: "+ANSI_RESET); prv.show(); 

            if(prv == null){
               System.out.println("DET-ABE DECRYPTION MODULE: Unable to generate the private key from TA...\nDecryption process failed.");
               System.exit(0);
            }
            
            return prv;
        }
    }    
    
    /**
     * Retrieve access control policy and encrypt file (CP-ABE.Encrypt).
     * @param dicom
     * @param policy
     * @param secLevel
     * @param type
     * @param construction 
     */
    public static void encryptFile(String dicom,String policy,int secLevel,String type,String construction){
        //Retrieve policy as FilePolicy object
        FilePolicy filePolicy=PoliciesManagement.getFilePolicy(policy);
        //Set AES security level \lambda_{AES} based on the ABE security level selected \lambda_{ABE}
        int secLevelAES=((secLevel==150)||(secLevel==160) ? 128 : secLevel);
        //Plaintext file (bytes)
        byte datapln[] = Files.readBytesFromFile(dicom);
        //Encryption path
        String file="data/sink/encrypted/"+construction+"/"+new File(dicom).getName()+".encABE";
        
        if(construction.equals("BSW07")){ //Get ACP for BSW07 construction
            //Get ABE PK for BSW07 construction
            src.cpabe.bsw.common.PublicKey PK=new src.cpabe.bsw.common.PublicKey(secLevel,type);
            
            //Get, translate and display access control policy
            String policyStr=PolicyTranslator.getBSW07PolicyAttributesValues(filePolicy,"")+filePolicy.getThreshold();
            System.out.println("\n"+ANSI_BLUE+"POLICY: \n"+policyStr+ANSI_RESET);
            
            //Encrypt data: files are encrypted with AES, and the AES key is encrypted using ABE
            System.out.println("\n\n"+ANSI_BLUE+"=== ENCRYPTION ==="+ANSI_RESET);
            List list=DOOperations.encrypt(policyStr,secLevelAES,secLevel,type,datapln,1,"file");
            Files.storeObject(list,file,"Encrypted DET-ABE data");
        } else{ //Get ACP for W11 construction
            //Get ABE PK for W11 construction
            src.cpabe.waters.g1.PublicKey PK=new src.cpabe.waters.g1.PublicKey(secLevel,type);
            
            //Get, translate and display access control policy
            FBF fbf=PolicyTranslator.getW11PolicyAttributesValues(filePolicy,new FBF()); //FilePolicy_i:attributes
            System.out.println("\n"+ANSI_BLUE+"POLICY: \n"+fbf+ANSI_RESET);

            //Encrypt data: files are encrypted with AES, and the AES key is encrypted using ABE
            List list=DOOperations.encrypt(fbf,secLevelAES,secLevel,type,datapln,1,"file");
            Files.storeObject(list,file,"Encrypted DET-ABE data");
        }
    }
    
    /**
     * Decrypt file (CP-ABE.Decrypt).
     * @param dicom
     * @param policy
     * @param user
     * @param secLevel
     * @param type
     * @param construction 
     */
    public static void decryptFile(String dicom,String user,int secLevel,String type,String construction){
        //Path of the encrypted file
        String file="data/sink/encrypted/"+construction+"/"+new File(dicom).getName()+".encABE";
        //Decryption path
        String decryptionPath="data/sink/decrypted/"+construction+"/";
        
        //Get user's attribute set S_u
        List<String> userAttributes=getUserActiveAttributes(user);
        //Display user's S_u
        System.out.println("\n"+ANSI_BLUE+"User Attributes".toUpperCase()+": \n"+userAttributes+ANSI_RESET);
        
        try{ //Decrypt data
            DU.decryptFiles(new String[]{file},construction,userAttributes,decryptionPath);
            //Store the path of the decrypted file to display it using the readDICOM MATLAB function
            FileUtils.writeStringToFile(new File("data/decFile.txt"),decryptionPath.split("data/")[1]+new File(dicom).getName()+".encABE.dec");
        } catch(Exception ex){ System.exit(0);}
    }
    
    
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args) throws JSONException, IOException {
        //Security level and construction to be tested (from the secLevel and construction constant arrays)
        int secLevelIndex=0; int constr=1;
        String user="userMD2_Su"; //User identifier for retrieving user's attributes file
        String file="data/source/MR/image-00005.dcm"; //Path of the file to be encrypted
        String filePolicy="policy0.dcm"; //Access policy assigned to the file (from the path 'data/policies/')
        
        //Define attribute universe (\mathbb{U})
        MainCaseStudy.generateAttributesUniverse_eHealth();
        //Display
        StructuresDisplay.displayActiveAttributesUniverse();
        
        if(constr==0){ //CP-ABE tree-based construction
            System.out.println("BSW07 Setting");

            //Encrypt file
            encryptFile(file,filePolicy,secLevel[secLevelIndex],type[1],construction[0]);
            //Create SK_u
            createSKu(user,secLevel[0],type[1],construction[0]);
            //Decrypt file
            decryptFile(file,user,secLevel[secLevelIndex],type[1],construction[0]);
        } else{ //CP-ABE matrix-based construction
            System.out.println("W11 Setting");

            //Encrypt file
            encryptFile(file,filePolicy,secLevel[secLevelIndex],type[1],construction[1]);
            //Create SK_u
            createSKu(user,secLevel[0],type[1],construction[1]);
            //Decrypt file
            decryptFile(file,user,secLevel[secLevelIndex],type[1],construction[1]);
        } 
    }
}
