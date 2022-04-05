# Supplementary Specification (FURPS+)

## Functionality

---

| Category       | Description                                                                                                                                                          |
| :------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Registration   | " Any Administrator uses the application to register centers, SNS users, center coordinators, receptionists, and nurses enrolled in the vaccination process. "       |
| Sending SMS    | " The SNS user may also authorize the DGS to send a SMS message with information about the scheduled appointment "                                                   |
| User status    | " The nurse checks the user info and health conditions in the system and in accordance with the scheduled vaccine type, and the SNS user vaccination history(...) "  |
| Analysing      | " The Center Coordinator wants to monitor the vaccination process, to see statistics and charts, to evaluate the performance of the vaccination process(...). "      |
| Audit          | " If the information is correct, the receptionist acknowledges the system that the user is ready to take the vaccine. "                                           |
| Scheduling     | " (...) to allow SNS users to schedule a vaccine and obtain a vaccination certificate. "                                                                     |
| Persistence    | " The application will use object serialization to ensure data persistence between two runs of the application. "                                                       |
| Authentication | " All those who wish to use the application must be authenticated with a password. "                                                                                  |
| Communication  | " The user may also authorize the DGS to send a SMS message with information about the scheduled appointment. "                                                      |
| Reporting      | " The JaCoCo plugin should be used to generate the coverage report. "                                                                                                  |
| Security       | " All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits. " |

## Usability

---

| Category                        | Description                                                                                                                                                          |
| :------------------------------ | :------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Interface aesthetics and design | " The application graphical interface is to be developed in JavaFX 11. "                                                                                             |
| Documentation                   | " The user manual must be delivered with the application. "                                                                                                          |
| Consistency                     | " All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits. " |
| User data                       | " Only the nurses are allowed to access all user’s health data. "                                                                                                      |

## Reliability

---

| Category                | Description                                                                                                                    |
| :---------------------- | :----------------------------------------------------------------------------------------------------------------------------- |
| Accuracy                | " The worst-case time complexity analysis of the algorithms should be properly documented in the user manual of the application. "|
| Possibility of recovery | " The application should use object serialization to ensure data persistence between two runs of the application. "            |

## Performance

---

| Category      | Description                                                                                                                                           |
| :------------ | :---------------------------------------------------------------------------------------------------------------------------------------------------- |
| Response time | " The implemented algorithm should be analyzed in terms of its worst-case time complexity, and it should be compared to a benchmark algorithm provided. " |

## Supportability

---

| Category        | Description                                                                                                                                                                                                                                             |
| :-------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Configurability | " The application must support, at least, the Portuguese and the English languages. "                                                                                                                                                                       |
| Testability     | " The development team must implement unit tests for all methods, except for methods that implement Input/Output operations. The unit tests should be implemented using the JUnit 5 framework. " |
| Localizability  | " DGS is a state-funded Portuguese healthcare system that wants an application to manage the vaccination process in Portugal (...) "                                                                                                                    |

## +

### Design Constraints

---

| Category                            | Description                                                                                                                                                                                                                            |
| :---------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Programming languages and paradigms | "The application must be developed in Java language...", using an objected-oriented procedure.                                                                                                                                         |
| Mandatory standards/patterns        | "During the system development, the team must (...) adopt recognized coding standards (e.g. CamelCase)..."                                                                                                                             |
| Security restrictions               | "All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits. Only the nurses are allowed to access all user’s health data." |
| Language requirements               | "The application must support, at least, the Portuguese and the English languages."                                                                                                                                                    |

### Implementation Constraints

---

| Category                         | Description                                                                                                                                                                                                                                                                                                                                    |
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
