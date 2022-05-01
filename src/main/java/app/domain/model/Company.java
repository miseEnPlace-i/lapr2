package app.domain.model;

import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class Company {

  private String designation;
  private AuthFacade authFacade;

  private SNSUserStore snsUserStore;
  private EmployeeStore employeeStore;
  private EmployeeRoleStore employeeRoleStore;
  private VaccinationCenterStore vaccinationCenterStore;

  /**
   * Company constructor.
   *
   * @param designation the designation of the company
   */
  public Company(String designation) {
    if (StringUtils.isBlank(designation)) throw new IllegalArgumentException("Designation cannot be blank.");

    this.designation = designation;
    this.authFacade = new AuthFacade();

    this.snsUserStore = new SNSUserStore(this.authFacade);
    this.employeeRoleStore = new EmployeeRoleStore(this.authFacade);
    this.employeeStore = new EmployeeStore(this.authFacade, this.employeeRoleStore);
  }

  /**
   * Gets the designation of the company.
   *
   * @return the designation of the company
   */
  public String getDesignation() {
    return designation;
  }

  /**
   * Gets the AuthFacade.
   *
   * @return the AuthFacade
   */
  public AuthFacade getAuthFacade() {
    return authFacade;
  }

  /**
   * Gets the SNSUserStore.
   *
   * @return the SNSUserStore
   */
  public SNSUserStore getSNSUserStore() {
    return this.snsUserStore;
  }

  /**
   * Gets the EmployeeStore.
   *
   * @return the EmployeeStore
   */
  public EmployeeStore getEmployeeStore() {
    return this.employeeStore;
  }

  /**
   * Gets the EmployeeRoleStore.
   *
   * @return the EmployeeRoleStore
   */
  public EmployeeRoleStore getEmployeeRoleStore() {
    return this.employeeRoleStore;
  }

  public VaccinationCenterStore getVaccinationCenterStore() {
    return this.vaccinationCenterStore;
  }
}
