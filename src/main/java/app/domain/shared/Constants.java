package app.domain.shared;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class Constants {
  public static final String ROLE_ADMIN = "ADMINISTRATOR";
  public static final String ROLE_COORDINATOR = "COORDINATOR";
  public static final String ROLE_NURSE = "NURSE";
  public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
  public static final String ROLE_SNS_USER = "SNS_USER";

  public static final String PARAMS_FILENAME = "config.properties";
  public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

  public static enum VaccineTechnology {
    LIVE_ATTENUATED_TECHNOLOGY, INACTIVATED_TECHNOLOGY, SUBUNIT_TECHNOLOGY, TOXOID_TECHNOLOGY, VIRAL_VECTOR_TECHNOLOGY, M_RNA_TECHNOLOGY;
  }

  public static final byte VACCINE_TYPE_CODE_LENGTH = 5;
}
