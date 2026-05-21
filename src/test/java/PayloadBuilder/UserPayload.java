package PayloadBuilder;

import org.json.simple.JSONObject;

public class UserPayload {

    public static JSONObject userLoginPayload(String email, String password) {
        JSONObject userLogin = new JSONObject(); //instantiate userLogin object of type JSONObject
        userLogin.put("email", email); //putting key-value pairs in the userLogin object
        userLogin.put("password", password);

        return userLogin;
    }
}
