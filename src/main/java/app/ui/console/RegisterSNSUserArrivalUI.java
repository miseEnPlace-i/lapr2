package app.ui.console;

import java.text.ParseException;
import app.controller.App;
import app.controller.RegisterSNSUserArrivalController;
import app.service.FieldsToValidate;
import app.ui.console.utils.Utils;

/**
 * Register SNS User Arrival View
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalUI extends RegisterUI<RegisterSNSUserArrivalController> {
    public RegisterSNSUserArrivalUI() {
        super(new RegisterSNSUserArrivalController(App.getInstance().getCompany()));
    }

    @Override
    public void insertData() throws IllegalArgumentException, ParseException {
        System.out.println("\nRegister SNS User Arrival UI:");

        String snsNumber = Utils.readLineFromConsoleWithValidation("SNS Number (xxxxxxxxx): ",
                FieldsToValidate.SNS_NUMBER);

        // verify if the user exists
        try {
            super.ctrl.findSNSUser(snsNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // select the center
        // System.out.println("\nSelect the center:");
        // if (ctrl.listVaccinationCenters().size() == 0) {
        //     System.out.println("\n\nThere are no vaccination centers in the system.");
        //     return;
        // }

        // Object center = Utils.showAndSelectOne(controller.listVaccinationCenters(),
        //         "\n\nVaccination Centers\n");

        // try {
        //     VaccinationCenterListDTO centerDTO = (VaccinationCenterListDTO) center;
        //     nurseSession.setVaccinationCenter(centerDTO);
        // } catch (ClassCastException e) {
        //     System.out.println("\n\nInvalid selection.");
        // }



        // find the user appointment
        super.ctrl.findSNSUserAppointment(snsNumber);

    }
}
