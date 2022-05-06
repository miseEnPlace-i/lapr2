package app.service;

public class FormatVerifier {

    public static boolean validateCC(String cc) {
        CCFormatVerifier ccVerifier = new CCFormatVerifier();
        return ccVerifier.validate(cc);
    }

    public static boolean validateEmail(String email) {
        if (email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validatePhoneNumber(String number) {
        if (number.matches("\\+[0-9]{3}[0-9]{9}$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateFaxNumber(String number) {
        if (number.matches("\\+[0-9]{1,3}-[0-9]{3}\\-[0-9]{7}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateWebAddress(String web) {
        if (web.matches("^(https:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateSNSNumber(String number) {
        if (number.matches("^\\d{9}$")) {
            return true;
        } else {
            return false;
        }
    }
}
