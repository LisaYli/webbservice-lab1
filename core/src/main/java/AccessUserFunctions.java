import dao.UserFunctions;

public class AccessUserFunctions {

public static String addUser(String name){
return UserFunctions.addNewUser(name);
}

public static String getAllUsers(){
    return UserFunctions.getAllUsers();
}



}

