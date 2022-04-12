# Supplementary Specification (FURPS+)

## Functionality

---

| Requirement    | Description                                                                                                                                                                                                                                |
| :------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Analyzing      | "The Center Coordinator wants to monitor the vaccination process, to see statistics and charts, to evaluate the performance of the vaccination process(...)."                                                                              |
| Authentication | "All those who wish to use the application must be authenticated with a password."                                                                                                                                                         |
| Communication  | "The user may also authorize the DGS to send a SMS message with information about the scheduled appointment."                                                                                                                              |
| Localization   | "The application must support, at least, the Portuguese and the English languages."                                                                                                                                                        |
| Persistence    | "The application will use object serialization to ensure data persistence between two runs of the application."                                                                                                                            |
| Registration   | "Any Administrator uses the application to register centers, SNS users, center coordinators, receptionists, and nurses enrolled in the vaccination process."                                                                               |
| Reporting      | "The Center Coordinator wants to monitor the vaccination process,(...) generate reports and analyze data from other centers"/"The JaCoCo plugin should be used to generate the coverage report"                                            |
| Scheduling     | "(...) to allow SNS users to schedule a vaccine and obtain a vaccination certificate."                                                                                                                                                     |
| Security       | "All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits." / "Only the nurses are allowed to access all user’s health data." |
| Sending SMS    | "The SNS user may also authorize the DGS to send a SMS message with information about the scheduled appointment."                                                                                                                          |
| User status    | "The nurse checks the user info and health conditions in the system and in accordance with the scheduled vaccine type, and the SNS user vaccination history(...)."                                                                         |

## Usability

---

| Requirement                     | Description                                                            |
| :------------------------------ | :--------------------------------------------------------------------- |
| Documentation                   | "The user manual must be delivered with the application."              |
| Interface aesthetics and design | "The application graphical interface is to be developed in JavaFX 11." |

## Reliability

---

| Requirement             | Description                                                                                                       |
| :---------------------- | :---------------------------------------------------------------------------------------------------------------- |
| Possibility of recovery | "The application should use object serialization to ensure data persistence between two runs of the application." |

## Performance

---

| Requirement   | Description                                                                                                                      |
| :------------ | :------------------------------------------------------------------------------------------------------------------------------- |
| Response time | "The worst-case time complexity analysis of the algorithms should be properly documented in the user manual of the application." |

## Supportability

---

| Requirement    | Description                                                                                                                                                                                    |
| :------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Localizability | "The application must support, at least, the Portuguese and the English languages."                                                                                                            |
| Testability    | "The development team must implement unit tests for all methods, except for methods that implement Input/Output operations. The unit tests should be implemented using the JUnit 5 framework." |

## +

### Design Constraints

---

| Requirement                         | Description                                                                                                                                                                                                                                |
| :---------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Language requirements               | "The application must support, at least, the Portuguese and the English languages."                                                                                                                                                        |
| Mandatory standards/patterns        | "During the system development, the team must (...) adopt recognized coding standards (e.g. CamelCase)..."                                                                                                                                 |
| Programming languages and paradigms | "The application must be developed in Java language...", using an objected-oriented procedure.                                                                                                                                             |
| Security restrictions               | "All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits." / "Only the nurses are allowed to access all user’s health data." |

### Implementation Constraints

---

| Requirement                      | Description                                                                                                                                                                                                                                                                                                                                    |
| :------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Development tools and frameworks | "The application must be developed (...) using the IntelliJ IDE or NetBeans. (...) The team must (...) use Javadoc to generate useful documentation for Java code. (...) The development team must implement unit tests for all methods, (...) using the JUnit 5 framework. The JaCoCo plugin should be used to generate the coverage report." |
| File formats                     | "All the images/figures produced during the software development process should be recorded in SVG format."                                                                                                                                                                                                                                    |
| Programming languages            | "The application must be developed in Java language. (...) The application graphical interface is to be developed in JavaFX 11"                                                                                                                                                                                                                |

### Interface Constraints

---

| Requirement        | Description                                                                                                                                                                              |
| :----------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| External resources | "After taking the vaccine, any SNS user can request the issuance of the EU COVID Digital Certificate (this feature of the system will be implemented later by the DGS’s IT department)." |

### Physical Constraints

---

Our team did not find any kind of physical requirements in the information provided by the Client.
