package app.service;

public class FormatVerifier {

    public static boolean validateCC(String cc) {
        CCFormatVerifier ccVerifier = new CCFormatVerifier();
        return ccVerifier.validate(cc);
    }

    public static boolean validateEmail() {
        // TODO
        return false;
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
