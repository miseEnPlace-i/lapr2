package app.service;

public class FormatVerifier {

    public static boolean validateCC(String cc) {
        CCFormatVerifier ccVerifier = new CCFormatVerifier();
        return ccVerifier.validate(cc);
    }

    public static boolean validateEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validatePhoneNumber() {
        // TODO
        return false;
    }

    public static boolean validateFaxNumber() {
        // TODO
        return false;
    }

    public static boolean validateWebAddress() {
        // TODO
        return false;
    }

    public static boolean validateSNSNumber() {
        // TODO
        return false;
    }
}
