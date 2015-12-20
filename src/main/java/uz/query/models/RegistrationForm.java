package uz.query.models;

/**
 * Created by Vejon on 18.12.2015.
 */
public class RegistrationForm extends User {

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    private String confirmPassword;

}
