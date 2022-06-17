package app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import app.domain.model.Address;
import app.domain.model.AdminProcess;
import app.domain.model.Company;
import app.domain.model.DoseInfo;
import app.domain.model.Employee;
import app.domain.model.MyUserRole;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.UserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.shared.Constants;
import app.domain.shared.Gender;
import app.service.PropertiesUtils;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
  private Company company;
  private transient AuthFacade authFacade;
  private EmployeeStore employeeStore;
  private EmployeeRoleStore employeeRoleStore;
  private VaccineTechnologyStore vaccineTechnologyStore;
  private VaccinationCenterStore vaccinationCenterStore;
  private VaccineTypeStore vacTypeStore;
  private SNSUserStore snsUserStore;
  private VaccineStore vaccineStore;
  private UserStore userStore;

  private App() {
    loadCompany();

    if (this.company == null) restoreCompany();

    this.authFacade = this.company.getAuthFacade();

    this.employeeStore = this.company.getEmployeeStore();
    this.employeeRoleStore = this.company.getEmployeeRoleStore();
    this.vaccineTechnologyStore = this.company.getVaccineTechnologyStore();
    this.vaccinationCenterStore = this.company.getVaccinationCenterStore();
    this.vacTypeStore = this.company.getVaccineTypeStore();
    this.snsUserStore = this.company.getSNSUserStore();
    this.vaccineStore = this.company.getVaccineStore();
    this.userStore = this.company.getUserStore();

    registerInAuthFacade(employeeRoleStore, userStore);

    if (isFirstRun()) {
      bootstrap();
      writeCompany(this.company, Constants.DATA_FILE_PATH);
    }
  }

  private void registerInAuthFacade(EmployeeRoleStore employeeRoleStore, UserStore userStore) {
    List<MyUserRole> employeeRoles = employeeRoleStore.getRoles();
    for (MyUserRole role : employeeRoles)
      this.authFacade.addUserRole(role.getId(), role.getDescription());

    this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
    this.authFacade.addUserRole(Constants.ROLE_SNS_USER, Constants.ROLE_SNS_USER);

    userStore.loadUsersToAuthFacade(this.authFacade);
  }

  private void loadCompany() {
    File dataFile = new File(Constants.DATA_FILE_PATH);

    if (!isFirstRun()) this.company = readExistingCompany(dataFile);
    else this.company = createNewCompany();
  }

  private boolean isFirstRun() {
    Properties props = PropertiesUtils.getProperties();
    String auto = props.getProperty(Constants.PARAMS_AUTO_DESERIALIZE);
    return !(auto != null && auto.equals("true")) || !new File(Constants.DATA_FILE_PATH).exists();
  }

  private Company readExistingCompany(File file) {
    Company comp = null;

    try {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
      try {
        comp = (Company) ois.readObject();
      } catch (ClassNotFoundException ex) {
        System.out.println("Class not found");
      }
      finally {
        ois.close();
      }
    } catch (IOException e) {
      System.out.println("File not found" + e.getMessage());
    }

    return comp;
  }

  public boolean saveCurrentCompany() {
    try {
      writeCompany(this.company, Constants.DATA_FILE_PATH);
      return true;
    } catch (Exception e) {
      System.out.println("Error saving company");
      return false;
    }
  }

  public void restoreCompany() {
    File file = new File(Constants.DATA_FILE_PATH);
    if (!file.delete()) System.out.println("Error restoring company");
    else System.out.println("Successfully restored company!");
  }

  private void writeCompany(Company comp, String filePath) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
      try {
        oos.writeObject(comp);

      } catch (IOException ex) {
        System.out.println("Error writing to file");
        ex.printStackTrace();
        throw new RuntimeException(ex);
      }
      finally {
        oos.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Company createNewCompany() {
    Company comp = null;

    Properties props = PropertiesUtils.getProperties();
    comp = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION), props.getProperty(Constants.PARAMS_ONGOING_OUTBREAK_VACCINE_TYPE_CODE));

    return comp;
  }

  public Company getCompany() {
    return this.company;
  }

  public UserSession getCurrentUserSession() {
    return this.authFacade.getCurrentUserSession();
  }

  public boolean doLogin(String email, String pwd) {
    return this.authFacade.doLogin(email, pwd).isLoggedIn();
  }

  public void doLogout() {
    this.authFacade.doLogout();
  }

  private void bootstrap() {
    // Added Receptionist user role & a test user with Receptionist role for testing
    // purposes
    this.employeeRoleStore.addEmployeeRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
    this.employeeRoleStore.addEmployeeRole(Constants.ROLE_NURSE, Constants.ROLE_NURSE);
    this.employeeRoleStore.addEmployeeRole(Constants.ROLE_COORDINATOR, Constants.ROLE_COORDINATOR);

    this.vaccineTechnologyStore.addVaccineTechnology("LIVE_ATTENUATED_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("INACTIVATED_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("SUBUNIT_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("TOXOID_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("VIRAL_VECTOR_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("M_RNA_TECHNOLOGY");

    // ********** IMPORTANT **********
    // PLEASE DO NOT DELETE ANY INSTANTIATION OF OBJECTS ON THIS METHOD.
    // THIS SAVES DEVELOPERS A LOT OF WORK.
    // THANK YOU.
    // *******************************

    this.authFacade.addUserWithRole("Test Administrator", "admin@user.com", "123456", Constants.ROLE_ADMIN);
    this.userStore.addUser("Test Administrator", "admin@user.com", "123456", Constants.ROLE_ADMIN);

    Calendar date = Calendar.getInstance();
    date.add(Calendar.YEAR, -18);

    SNSUser user = this.snsUserStore.createSNSUser("00000000", "123456789", "name", date.getTime(), Gender.MALE, "+351212345678", "s@user.com",
        new Address("street", 1, "4444-333", "city"));
    this.snsUserStore.saveSNSUser(user);

    Employee e = this.employeeStore.createEmployee("Name", "+351916919169", "r@user.com", new Address("street", 1, "1-11", "city"), "12345678",
        Constants.ROLE_RECEPTIONIST);
    this.employeeStore.saveEmployee(e);
    Employee e2 = this.employeeStore.createEmployee("Name2", "+351916919269", "c@user.com", new Address("street", 1, "1-11", "city"), "15542404",
        Constants.ROLE_COORDINATOR);
    this.employeeStore.saveEmployee(e2);
    Employee e21 = this.employeeStore.createEmployee("Name21", "+351916919269", "c2@user.com", new Address("street", 1, "1-11", "city"), "15542404",
        Constants.ROLE_COORDINATOR);
    this.employeeStore.saveEmployee(e21);

    Employee e3 =
        this.employeeStore.createEmployee("Name2", "+351916919269", "n@user.com", new Address("street", 1, "1-11", "city"), "00000000", Constants.ROLE_NURSE);
    this.employeeStore.saveEmployee(e3);

    Employee e4 = this.employeeStore.createEmployee("Name2", "+351916919269", "teste@coor.pt", new Address("street", 1, "1-11", "city"), "00000000",
        Constants.ROLE_COORDINATOR);
    this.employeeStore.saveEmployee(e4);

    VaccineType vacType = this.vacTypeStore.addVaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
    this.vacTypeStore.saveVaccineType(vacType);

    VaccineType vacType2 = this.vacTypeStore.addVaccineType("00010", "Monkeypox", "VIRAL_VECTOR_TECHNOLOGY");
    this.vacTypeStore.saveVaccineType(vacType2);

    Vaccine vaccine = this.vaccineStore.createVaccine("BioNTech, Pfizer vaccine", "00001", "Pfizer, BioNTech", vacType);
    AdminProcess adminProcess1 = new AdminProcess(1, 20, 1);
    AdminProcess adminProcess2 = new AdminProcess(21, 89, 2);
    DoseInfo doseInfo1 = new DoseInfo(11, 0);
    DoseInfo doseInfo2 = new DoseInfo(22, 30);

    adminProcess1.addDoseInfo(doseInfo1);

    adminProcess2.addDoseInfo(doseInfo1);
    adminProcess2.addDoseInfo(doseInfo2);

    vaccine.addAdminProc(adminProcess1);
    vaccine.addAdminProc(adminProcess2);
    this.vaccineStore.saveVaccine(vaccine);

    Vaccine vaccine2 = this.vaccineStore.createVaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType2);
    vaccine2.addAdminProc(adminProcess1);
    this.vaccineStore.saveVaccine(vaccine2);

    // to facilitate US 17 (import legacy data)
    Vaccine vaccine3 = this.vaccineStore.createVaccine("Spikevax", "00003", "PTVaccines", vacType);
    vaccine3.addAdminProc(adminProcess1);
    vaccine3.addAdminProc(adminProcess2);
    this.vaccineStore.saveVaccine(vaccine3);

    VaccinationCenter vc = this.vaccinationCenterStore.createCommunityMassCenter("Centro Vacinação de Teste", new Address("street", 1, "11-11", "city"),
        "test@gmail.com", "+351212345678", "+351212345679", "http://www.test.com", "08:00", "20:00", 1, 500, e2, vacType);
    this.vaccinationCenterStore.saveVaccinationCenter(vc);

    VaccinationCenter vc2 = this.vaccinationCenterStore.createHealthCareCenter("Unidade de Saúde de Teste", new Address("street", 1, "11-11", "city"),
        "test2@gmail.com", "+351219876543", "+351219876543", "https://teste.com", "20:00", "21:00", 7, 5, e21, "AGES", "AGS");
    this.vaccinationCenterStore.saveVaccinationCenter(vc2);
  }

  // Extracted from
  // https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
  private static App singleton = null;

  public static App getInstance() {
    if (singleton == null) {
      synchronized (App.class) {
        singleton = new App();
      }
    }
    return singleton;
  }
}
