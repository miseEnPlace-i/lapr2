# Supplementary Specification (FURPS+)

## Functionality

---

| Category       | Description                                                                                                                                                          |
| :------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Certification  | Provide a service to deliver a vaccination certificate.                                                                                                              |
| Registration   | Provides a service to set up the registration of all the people related to the company.                                                                              |
| Sending SMS    | Provide a service to deliver a SMS with informations of the vaccine appointment to the SNS user.                                                                     |
| User status    | Provide a service to check user´s health information.                                                                                                                |
| Analysing      | Provide crucial data for the vaccination process analysis.                                                                                                           |
| Audit          | If the user information is correct, the receptionist acknowledges the system that the user is ready to take the vaccine.                                             |
| Scheduling     | Provide a service to allow SNS users to schedule a vaccine and obtain a vaccination certificate.                                                                     |
| Persistence    | The application will use object serialization to ensure data persistence between two runs of the application.                                                        |
| Authentication | Those who wish to use the application must be authenticated with a password.                                                                                         |
| Communication  | The user may also authorize the DGS to send a SMS message with information about the scheduled appointment.                                                          |
| Reporting      | The JaCoCo plugin will be used to generate the coverage report.                                                                                                      |
| Security       | All user who wish to use the application will be authenticated wit a password holding seven alphanumeric characters, including three capital letters and two digits. |

## Usability

---

| Category                        | Description                                                                                                                                                          |
| :------------------------------ | :------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Accessibility                   | If the user authorizes the sending of the SMS, the application will send an SMS message when the vaccination event is scheduled and registered in the system.        |
| Error prevention                | Testing often the application to make it hard for the user to commit the error.                                                                                      |
| Interface aesthetics and design | The application graphical interface will be simple, intuitive and consistent developed in JavaFX 11.                                                                 |
| Help                            | Service to provide the user help on the application.                                                                                                                 |
| Documentation                   | The application will have a user manual.                                                                                                                             |
| Consistency                     | All user who wish to use the application will be authenticated wit a password holding seven alphanumeric characters, including three capital letters and two digits. |
| User data                       | Only nurses will be allowed to access all user´s health data.                                                                                                        |

## Reliability

---

| Category                | Description                                                                                                                    |
| :---------------------- | :----------------------------------------------------------------------------------------------------------------------------- |
| Accuracy                | The worst-case time complexity analysis of the algorithms should be properly documented in the user manual of the application. |
| Possibility of recovery | The application will use object serialization to ensure data persistence between two runs of the application.                  |

## Performance

---

| Category      | Description                                                                                                                                           |
| :------------ | :---------------------------------------------------------------------------------------------------------------------------------------------------- |
| Response time | The implemented algorithm should be analyzed in terms of its worst-case time complexity, and it should be compared to a benchmark algorithm provided. |

## Supportability

---

| Category        | Description                                                                                                                                                                           |
| :-------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Configurability | The application will support both Portuguese and English languages, at least.                                                                                                         |
| Testability     | The application will implement unit tests for all methods, except for methods that implement Input/Output operations. The unit tests will be implemented using the JUnit 5 framework. |
| Compatibility   | The application will be compatible with any system.                                                                                                                                   |
| Localizability  | The application will be administered by the portuguese General Health Direction (DGS)                                                                                                 |

## +

### Design Constraints

---

| Category                            | Description                                                                                                                                                                                                                            |
| :---------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Programming languages and paradigms | "The application must be developed in Java language...", using an objected-oriented procedure.                                                                                                                                         |
| Mandatory standards/patterns        | "During the system development, the team must (...) adopt recognized coding standards (e.g. CamelCase)..."                                                                                                                               |
| Security restrictions               | "All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits. Only the nurses are allowed to access all user’s health data." |
| Language requirements               | "The application must support, at least, the Portuguese and the English languages."                                                                                                                                                    |

### Implementation Constraints

---

| Category                         | Description                                                                                                                                                                                                                                                                                                                                                                                              |
| :------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Programming languages            | "The application must be developed in Java language. (...) The application graphical interface is to be developed in JavaFX 11"                                                                                                                                                                                                                |
| Development tools and frameworks | "The application must be developed (...) using the IntelliJ IDE or NetBeans. (...) The team must (...) use Javadoc to generate useful documentation for Java code. (...) The development team must implement unit tests for all methods, (...) using the JUnit 5 framework. The JaCoCo plugin should be used to generate the coverage report." |
| File formats                     | "All the images/figures produced during the software development process should be recorded in SVG format."                                                                                                                                                                                                                                    |

### Interface Constraints

---

| Category           | Description                                                                                                                                                                              |
| :----------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| External resources | "After taking the vaccine, any SNS user can request the issuance of the EU COVID Digital Certificate (this feature of the system will be implemented later by the DGS’s IT department)." |

### Physical Constraints

---

Our team did not find any kind of physical requirements in the information provided by the Client.
