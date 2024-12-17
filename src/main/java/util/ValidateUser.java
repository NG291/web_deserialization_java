package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUser {

    // Phương thức kiểm tra username
    public static boolean validateUsername(String username) {
        if (username == null || username.length() < 3 || username.length() > 20) {
            System.out.println("Username phải có độ dài từ 3 đến 20 ký tự.");
            return false;
        }

        // Kiểm tra chỉ có các ký tự chữ cái và số, không chứa ký tự đặc biệt
        String regex = "^[a-zA-Z0-9_]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        if (!matcher.matches()) {
            System.out.println("Username chỉ được chứa chữ cái, số và dấu gạch dưới.");
            return false;
        }

        return true;
    }

    // Phương thức kiểm tra email
    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Email không được để trống.");
            return false;
        }

        // Kiểm tra định dạng email hợp lệ với Regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            System.out.println("Email không hợp lệ.");
            return false;
        }

        return true;
    }

    // Phương thức kiểm tra password
    public static boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            System.out.println("Password phải có ít nhất 8 ký tự.");
            return false;
        }

        // Kiểm tra password có chứa ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            System.out.println("Password phải chứa ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt.");
            return false;
        }

        return true;
    }
    // Phương thức kiểm tra tất cả các trường
    public static boolean validateUser(String username, String email, String password) {
        return validateUsername(username) && validateEmail(email) && validatePassword(password);
    }


}
