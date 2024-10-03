package system;

import attributes.UserAttributes;

/**
 * Class used for users' management.
 * 
 * @author      Melissa Brigitthe Hinojosa-Cabello
 * @version     1.0
 * @since       2022-05-16
*/
public class UsersManagement {
    /**
     * Register system users.
     * @param usersID
     * @param usersAttributes
     * @return 
     */
    public static User[] createUsers(String[] usersID,UserAttributes[][] usersAttributes){
        //Amount of users to be registered
        int usersLength=usersID.length;
        
        User[] users=new User[usersLength];
        for(int i=0;i<usersLength;i++)
            users[i]=new User(usersID[i],usersAttributes[i],true);

        return users;
    }
}
