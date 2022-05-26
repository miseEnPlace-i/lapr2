package app.domain.model.store;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import app.domain.model.SNSUser;
import app.domain.model.dto.SNSUserDTO;
import app.domain.model.dto.SNSUserRegisterInfoDTO;
import app.domain.shared.Constants;
import app.mappers.SNSUserMapper;
import app.mappers.SNSUserRegisterInfoMapper;
import app.service.PasswordGenerator;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.mappers.UserMapper;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

/**
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
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
  public SNSUser createSNSUser(String citizenCard, String snsNumber, String name, Date birthDay,
      char gender, String phoneNumber, String email, String address) {
    SNSUser snsUser =
        new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);

    return snsUser;
  }


  //creates SNS User instance.
  public SNSUser createSNSUser(SNSUserDTO snsUserDto) {
    SNSUser snsUser =
        new SNSUser(snsUserDto);

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
   * Inserts a SNS User object to the list and adds a User to the AuthFacade.
   * 
   * @param user
   */
  public SNSUserRegisterInfoDTO saveSNSUser(SNSUser snsUser) {
    SNSUserRegisterInfoDTO userRegisterInfoDTO = SNSUserRegisterInfoMapper.toDto(snsUser);

    authFacade.addUserWithRole(userRegisterInfoDTO.getName(), userRegisterInfoDTO.getEmail(), userRegisterInfoDTO.getPass(), userRegisterInfoDTO.getRole());

    addSNSUser(snsUser);

    return userRegisterInfoDTO;

    // TODO: send password email
    // EmailSender emailSender = new EmailSender();
    // emailSender.sendPasswordEmail(email, pwdStr);
  }

  //adds SNS User
  public void addSNSUser(SNSUser snsUser){
    snsUsers.add(snsUser);
  }

  /**
   * Checks if there are duplicates in the store.
   * 
   * @param snsUser
   */
  public void checkDuplicates(SNSUser snsUser) {
    if (snsUsers.contains(snsUser)) {
      throw new IllegalArgumentException("Duplicate SNS User.");
    }
  }

  /**
   * Finds a SNS User by SNS Number.
   * 
   * @param snsNumber The SNS User Number.
   * @return SNSUser
   */
  public SNSUser findSNSUserByNumber(String snsNumber) {
    for (SNSUser snsUser : snsUsers) {
      if (snsUser.getSnsNumber().equals(snsNumber)) {
        return snsUser;
      }
    }

    return null;
  }

  /**
   * Checks if a SNS User exists.
   * 
   * @param snsNumber The SNS User Number.
   * @return SNSUser
   */
  public boolean checkSNSUserExists(String snsNumber) {
    for (SNSUser snsUser : snsUsers) {
      if (snsUser.getSnsNumber().equals(snsNumber)) {
        return true;
      }
    }

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

  public List<SNSUserRegisterInfoDTO> registerListOfUsers(List<String[]> userDataList) throws ParseException{
    List<SNSUserRegisterInfoDTO> userRegisterInfoList = new ArrayList<>();

    for (int i = 0; i < userDataList.size(); i++) {
      SNSUserDTO userDto = SNSUserMapper.toDto(userDataList.get(i));

      SNSUser snsUser = createSNSUser(userDto);

      validateSNSUser(snsUser);

      userRegisterInfoList.add(saveSNSUser(snsUser));;
    }
    return userRegisterInfoList;
  }

}
