package utils;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used for processing JSON files.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-03-28
*/
public class ProcessJSON{

    /**
     * Write JSON file
     * @param objJSON JSON contents
     * @param json JSON file to be written
     */
    public static void writeJSON(Object objJSON,String json){
        //Create JSON file
        FileWriter file;
        try{
            file=new FileWriter(json);
            file.write(new GsonBuilder().setPrettyPrinting().create().toJson(objJSON)); //String jsonString=new Gson().toJson(attrU);
            file.close();
        } catch (IOException ex){
            Logger.getLogger(ProcessJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /**
     * Read JSON file
     * @param json JSON file to be read
     * @return JSON contents
     */
    public static String readJSON(String json){
        try{
            return new String(Files.readAllBytes(Paths.get(json)));
        } catch (IOException ex){
            Logger.getLogger(ProcessJSON.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
