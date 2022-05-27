package app.service.password;

import java.util.Properties;
import app.domain.shared.Constants;
import app.service.PropertiesUtils;

public class PasswordGeneratorFactory {

  private PasswordGeneratorFactory() {}

  public static IPasswordGenerator getPasswordGenerator() {
    Properties props = PropertiesUtils.getProperties();
    boolean isTest = props.getProperty(Constants.PARAMS_TEST_ENVIRONMENT).equals("true") ? true : false;

    String generatorName = isTest ? "app.service.password.TestGenerator" : "app.service.password.PasswordGenerator";

    try {
      Class<?> generatorClass = Class.forName(generatorName);

      IPasswordGenerator generator = (IPasswordGenerator) generatorClass.getDeclaredConstructor().newInstance();

      return generator;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return null;
    }
  }
}
