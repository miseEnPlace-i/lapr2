package app.domain.model;

import org.apache.commons.lang3.StringUtils;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.service.sender.ISender;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

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
  private String ongoingOutbreakVaccineTypeCode;
  private AuthFacade authFacade;
  private EmployeeStore employeeStore;
  private EmployeeRoleStore employeeRoleStore;
  private SNSUserStore snsUserStore;
  private VaccinationCenterStore vaccinationCenterStore;
  private VaccineStore vaccineStore;
  private VaccineTechnologyStore vaccineTechnologyStore;
  private VaccineTypeStore vaccineTypeStore;
  private UserSession userSession;
  private ISender sender;

  /**
   * Company constructor.
   *
   * @param designation the designation of the company
   */
  public Company(String designation, String ongoingOutbreakVaccineTypeCode, String senderName) {
    if (StringUtils.isBlank(designation))
      throw new IllegalArgumentException("Designation cannot be blank.");

    if (ongoingOutbreakVaccineTypeCode == null)
      throw new IllegalArgumentException("Ongoing outbreak vaccine type code cannot be null.");

    this.designation = designation;

    this.authFacade = new AuthFacade();

    try {
      Class<?> sender = Class.forName(senderName);

      this.sender = (ISender) sender.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

    this.employeeRoleStore = new EmployeeRoleStore(this.authFacade);
    this.employeeStore = new EmployeeStore(this.authFacade, this.employeeRoleStore, this.sender);
    this.snsUserStore = new SNSUserStore(this.authFacade, this.sender);
    this.vaccinationCenterStore = new VaccinationCenterStore();
    this.vaccineStore = new VaccineStore();
    this.vaccineTechnologyStore = new VaccineTechnologyStore();
    this.vaccineTypeStore = new VaccineTypeStore(vaccineTechnologyStore);
    this.userSession = new UserSession();

    this.ongoingOutbreakVaccineTypeCode = ongoingOutbreakVaccineTypeCode;
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
   * Gets the Sender.
   * 
   * @return ISender.
   */
  public ISender getSender() {
    return this.sender;
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

  public VaccineStore getVaccineStore() {
    return this.vaccineStore;
  }

  public VaccineTypeStore getVaccineTypeStore() {
    return this.vaccineTypeStore;
  }

  public VaccineTechnologyStore getVaccineTechnologyStore() {
    return this.vaccineTechnologyStore;
  }

  public String getOngoingOutbreakVaccineTypeCode() {
    return this.ongoingOutbreakVaccineTypeCode;
  }

  public UserSession getUserSession() {
    return this.userSession;
  }
}
