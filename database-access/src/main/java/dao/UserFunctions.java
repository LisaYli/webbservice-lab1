package dao;

public class UserFunctions {
    public static String addNewUser(String name){
        DAO.addUser(name);

        return name;
    }

    public static String getAllUsers(){
        return DAO.showAllUsers();
    }
}
