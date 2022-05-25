package app.ui.console;

import java.text.ParseException;
import app.controller.App;
import app.controller.RegisterSNSUserArrivalController;
import app.domain.model.VaccinationCenter;
import app.domain.shared.FieldToValidate;
import app.exception.AppointmentNotFoundException;
import app.ui.console.utils.Utils;

/**
 * Register SNS User Arrival View
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalUI extends RegisterUI<RegisterSNSUserArrivalController> {
    public RegisterSNSUserArrivalUI(VaccinationCenter center) {
        super(new RegisterSNSUserArrivalController(App.getInstance().getCompany(), center));
    }

    @Override
    public void insertData() throws IllegalArgumentException, ParseException, AppointmentNotFoundException {
        System.out.println("\nRegister SNS User Arrival UI:");

        String snsNumber = Utils.readLineFromConsoleWithValidation("SNS Number (xxxxxxxxx): ",
                FieldToValidate.SNS_NUMBER);

        // try {
            // verify if the user exists
            super.ctrl.findSNSUser(snsNumber);

            // find the user appointment
            super.ctrl.findSNSUserAppointment();
        // } catch (IllegalArgumentException | AppointmentNotFoundException e) {
        //     System.out.println(e.getMessage());
        //     return;
        // }

        // create the arrival
        super.ctrl.create();
    }
}
