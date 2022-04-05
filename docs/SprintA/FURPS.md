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

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

| Category                            | Description                                                                                                                                                                                                                                                                                                          |
| ----------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Programming languages and paradigms | The application must be developed in Java, using an objected-oriented procedure.                                                                                                                                                                                                                                     |
| Mandatory standards/patterns        | During the design process, as well as in the development process, the team must adopt recognized code standards, which should be used in all documents created in the project. Those standards must also be used in commit messages, in order to easily locate and manage changes in the source code.                |
| Security restrictions               | When it comes to the security of the app data, the application must require a password for everyone that uses the software, which must contain seven alphanumeric characters, including three capital letters and two digits. Regarding privacy of user’s information, only nurses should access user’s health data. |
| Language requirements               | The app must offer all contents in English and Portuguese.                                                                                                                                                                                                                                                           |

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

| Category                         | Description                                                                                                                                                                                                                                                                                                                                                                                              |
| -------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Programming languages            | For the implementation process, there are constraints about the programming languages and the software that the team can use. Java must be used to develop the application, using JavaFX 11 for the graphical interface.                                                                                                                                                                                 |
| Development tools and frameworks | The Client also requested that the development team must use either IntelliJ IDE or NetBeans IDE for writing and managing the app source code. For code documentation, the Javadoc plugin must be used. The development team should implement unit tests for all methods developed, which must be done using JUnit 5 framework. JaCoCo plugin must also be used, but only for generating test’s reports. |
| File formats                     | If there are any images generated by the application, they must be saved in SVG format. Pictures created during the development process should also use the same format.                                                                                                                                                                                                                                 |

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

| Category           | Description                                                                                                                                                                                                                                                                                                                                                                                                           |
| ------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| External resources | The Client has required that the application should send multiple notifications to the user via SMS or email. These notifications can be sent using external software, such as plugins or libraries. After taking the vaccine, the user can request the European Union COVID digital certificate. This document will be generated by an external source, which is going to be implemented by the DGS’s IT department. |

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

Our team did not find any kind of physical requirements in the information provided by the Client.
