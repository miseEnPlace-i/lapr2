package app.domain.model.store;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import app.domain.model.SNSUser;
import app.domain.shared.Constants;
import app.domain.shared.Gender;
import app.dto.SNSUserDTO;
import app.dto.UserNotificationDTO;
import app.mapper.SNSUserMapper;
import app.mapper.UserNotificationMapper;
import app.service.password.IPasswordGenerator;
import app.service.password.PasswordGeneratorFactory;
import app.service.sender.ISender;
import app.service.sender.SenderFactory;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class SNSUserStore {
  // User List
  private List<SNSUser> snsUsers;

  // Auth Facade
  private AuthFacade authFacade;

  /**
   * Constructor for SNSUserStore.
   */
  public SNSUserStore(AuthFacade authFacade) {
    this.snsUsers = new ArrayList<SNSUser>();
    this.authFacade = authFacade;
  }

  /**
   * Creates an SNS User instance.
   * 
   * @param citizenCard the citizen card of the SNS User.
   * @param snsNumber the SNS Number of the SNS User.
   * @param name the name of the SNS User.
   * @param birthDayStr the birth day as a string of the SNS User.
   * @param gender the SNS User gender
   * @param phoneNumber SNS User phone number
   * @param email SNS User email
   * @param address SNS User address
   * @return SNSUser
   */
  public SNSUser createSNSUser(String citizenCard, String snsNumber, String name, Date birthDay, Gender gender, String phoneNumber, String email,
      String address) {
    SNSUser snsUser = new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);

    return snsUser;
  }


  // creates SNS User instance.
  public SNSUser createSNSUser(SNSUserDTO snsUserDto) {
    SNSUser snsUser = new SNSUser(snsUserDto);

    return snsUser;
  }

  /**
   * Checks if there are duplicates.
   * 
   * @param snsUser
   */
  public void validateSNSUser(SNSUser snsUser) {
    if (snsUser == null) throw new Error("SNS User is null.");

    // get the SNS User email
    String email = snsUser.getEmail();

    // check with AuthFacade if the email is already in use
    boolean existsUser = this.authFacade.existsUser(email);

    if (existsUser) throw new IllegalArgumentException("Email already in use.");

    checkDuplicates(snsUser);
  }

  /**
   * Inserts a SNS User object to the store and creates a system user.
   * 
   * @param snsUser the employee to be inserted.
   */
  public void saveSNSUser(SNSUser snsUser) {
    addSNSUser(snsUser);

    String email = snsUser.getEmail();
    String phoneNumber = snsUser.getPhoneNumber();
    IPasswordGenerator pwdGenerator = PasswordGeneratorFactory.getPasswordGenerator();
    String pwd = pwdGenerator.generatePwd();

    authFacade.addUserWithRole(snsUser.getName(), email, pwd, Constants.ROLE_SNS_USER);

    String message = String.format("A new user has been created.\nEmail: %s\nPassword: %s", email, pwd);
    UserNotificationDTO notificationDto = UserNotificationMapper.toDto(email, phoneNumber, message);

    sendNotification(notificationDto);

  }

  public void addSNSUser(SNSUser snsUser){
    this.snsUsers.add(snsUser);
  }

  private void sendNotification(UserNotificationDTO notificationDto) {
    ISender sender = SenderFactory.getSender();

    try {
      sender.send(notificationDto);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Checks if there are duplicates in the store.
   * 
   * @param snsUser
   */
  public void checkDuplicates(SNSUser snsUser) {
    if (snsUsers.contains(snsUser)) throw new IllegalArgumentException("Duplicate SNS User.");
  }

  /**
   * Finds a SNS User by SNS Number.
   * 
   * @param snsNumber The SNS User Number.
   * @return SNSUser
   */
  public SNSUser findSNSUserByNumber(String snsNumber) {
    for (SNSUser snsUser : snsUsers)
      if (snsUser.getSnsNumber().equals(snsNumber)) return snsUser;

    return null;
  }

  /**
   * Checks if a SNS User exists.
   * 
   * @param snsNumber The SNS User Number.
   * @return SNSUser
   */
  public boolean checkSNSUserExists(String snsNumber) {
    for (SNSUser snsUser : snsUsers)
      if (snsUser.getSnsNumber().equals(snsNumber)) return true;

    return false;
  }

  /**
   * Finds a SNS User by Email.
   * 
   * @param email The SNS User email.
   * @return SNSUser
   */
  public SNSUser findSNSUserByEmail(String email) {
    for (SNSUser snsUser : snsUsers)
      if (snsUser.getEmail().equals(email)) return snsUser;

    return null;
  }

  /**
   * Gets the size of the store.
   * 
   * @return int of number of SNS Users in the store.
   */
  public int size() {
    return snsUsers.size();
  }

  public List<SNSUser> registerListOfUsers(List<String[]> userDataList) throws ParseException {
    List<SNSUser> userList = new ArrayList<SNSUser>();

      for (int i = 0; i < userDataList.size(); i++) {
        try{
          SNSUserDTO userDto = SNSUserMapper.toDto(userDataList.get(i));
    
          SNSUser snsUser = createSNSUser(userDto);
          validateSNSUser(snsUser);
    
          saveSNSUser(snsUser);
    
          userList.add(snsUser);
        }catch(Exception e){
          userList.add(null);
        }
      }
    

    return userList;
  }

}
