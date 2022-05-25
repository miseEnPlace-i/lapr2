package app.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import app.domain.shared.Constants;

/**
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class PropertiesUtils {

  public static Properties getProperties() {
    Properties props = new Properties();

    // Add default properties and values
    props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");

    // Read configured values
    try {
      InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
      props.load(in);
      in.close();
    } catch (IOException ex) {

    }
    return props;
  }

}
