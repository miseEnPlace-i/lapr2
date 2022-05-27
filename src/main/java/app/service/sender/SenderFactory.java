package app.service.sender;

import java.util.Properties;
import app.domain.shared.Constants;
import app.service.PropertiesUtils;

public class SenderFactory {

  private SenderFactory() {}

  public static ISender getSender() {
    Properties props = PropertiesUtils.getProperties();

    String senderName = props.getProperty(Constants.PARAMS_SENDER);

    try {
      Class<?> senderClass = Class.forName(senderName);

      ISender sender = (ISender) senderClass.getDeclaredConstructor().newInstance();

      return sender;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return null;
    }
  }
}
