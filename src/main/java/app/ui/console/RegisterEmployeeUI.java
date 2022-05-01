package app.ui.console;

import java.util.List;
import app.controller.RegisterEmployeeController;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * Register Employee View
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */

public class RegisterEmployeeUI implements Runnable {
    private RegisterEmployeeController controller;

    /**
     * Constructor for RegisterEmployeeUI.
     */
    public RegisterEmployeeUI() {
        this.controller = new RegisterEmployeeController();
    }

    public void run() {
        // TODO: implement this method
    }

    /**
     * Displays in the screen all the available employee roles
     * 
     * @param employeeRoles the list of employee roles
     */
    private void displayEmployeeRoles(List<UserRole> employeeRoles) {
        Utils.showList(employeeRoles, "Employee Roles");
    }

    /**
     * Selects an employee role
     * 
     * @param employeeRoles the list of employee roles
     */
    private void selectEmployeeRole(List<UserRole> employeeRoles) {
        // TODO: implement this method
    }

    /**
     * Asks user to input employee data
     */
    private void insertEmployeeData() {
        // TODO: implement this method
    }

    /**
     * Asks user to confirm the employee data
     *
     * @return true if the user confirms, false otherwise
     */
    private boolean confirmData() {
        // TODO : implement this method
        return false;
    }
}
