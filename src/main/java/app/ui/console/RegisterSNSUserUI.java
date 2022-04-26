package app.ui.console;

import app.controller.RegisterSNSUserController;

/**
 * Register SNS User View
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */

public class RegisterSNSUserUI implements Runnable {
    private RegisterSNSUserController ctrl;

    public RegisterSNSUserUI() {
        ctrl = new RegisterSNSUserController();
    }

    public void run() {

    }
}
