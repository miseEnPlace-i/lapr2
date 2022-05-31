package app.ui.console;

import org.apache.commons.lang3.NotImplementedException;

/**
 * 
 * @author
 */
public class CoordinatorUI extends EmployeeWithCenterUI {
    public CoordinatorUI() {
        super();
    }

    @Override
    public void callback() {
        // TODO: for users with the coordinator role we don't want to select a center, we want to find the one he is assigned to

        System.out.println("Coordinator UI not implemented yet.");

        // List<MenuItem> options = new ArrayList<MenuItem>();

        // options.add(new MenuItem("Turn the spaceship engine on", new SomethingUI()));

        // int option = 0;
        // do {
        //     System.out.printf("%Coordinator Vaccination Center: %s", super.getCurrentVaccinationCenter());
        //     option = Utils.showAndSelectIndex(options, "\n\Coordinator Menu:");

        //     if ((option >= 0) && (option < options.size())) options.get(option).run();
        // } while (option != -1);
    }

}
