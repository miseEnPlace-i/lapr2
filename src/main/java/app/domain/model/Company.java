package app.domain.model;

import java.io.ObjectInputFilter.Config;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.UserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.shared.Constants;
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
public class Company implements Serializable {
  private String designation;
  private String ongoingOutbreakVaccineTypeCode;
  private transient AuthFacade authFacade;
  private EmployeeStore employeeStore;
  private EmployeeRoleStore employeeRoleStore;
  private SNSUserStore snsUserStore;
  private VaccinationCenterStore vaccinationCenterStore;
  private VaccineStore vaccineStore;
  private VaccineTechnologyStore vaccineTechnologyStore;
  private VaccineTypeStore vaccineTypeStore;
  private transient UserSession userSession;
  private UserStore userStore;

  /**
   * Company constructor.
   *
   * @param designation the designation of the company
   */
  public Company(String designation, String ongoingOutbreakVaccineTypeCode) {
    if (StringUtils.isBlank(designation)) throw new IllegalArgumentException("Designation cannot be blank.");

    if (ongoingOutbreakVaccineTypeCode == null) throw new IllegalArgumentException("Ongoing outbreak vaccine type code cannot be null.");

    this.designation = designation;

    this.authFacade = new AuthFacade();
    this.userStore = new UserStore();
    this.employeeRoleStore = new EmployeeRoleStore(this.authFacade);
    this.employeeStore = new EmployeeStore(this.authFacade, this.userStore, this.employeeRoleStore);
    this.snsUserStore = new SNSUserStore(this.authFacade, this.userStore);
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

  public void scheduleExportDailyVaccinated(Date firstDate, long period){
    ExportDailyVaccinatedTask task = new ExportDailyVaccinatedTask("filetest", ";".charAt(0), this.vaccinationCenterStore, this.vaccineTypeStore);
    Timer timer = new Timer();

    Calendar firstTime = Calendar.getInstance();
    firstTime.add(Calendar.DAY_OF_MONTH, 1);
    firstTime.set(Calendar.HOUR, 0);
    firstTime.set(Calendar.MINUTE, 0);
    firstTime.set(Calendar.SECOND, 0);

    timer.scheduleAtFixedRate(task, firstTime.getTime(), 24*60*60);
  }

  public UserStore getUserStore() {
    return this.userStore;
  }
}
