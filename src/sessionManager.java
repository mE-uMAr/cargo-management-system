public class sessionManager {
    private static String username;

    public static void setUsername(String username){
        sessionManager.username = username;
    }
    public static String getUsername(){
        return username;
    }
}
