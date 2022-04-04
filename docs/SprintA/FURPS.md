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

| Category                          | Description                                                                                                                    |
| :-------------------------------- | :----------------------------------------------------------------------------------------------------------------------------- |
| Frequency and severity of failure | The application will ensure that the frequency of the software failure is low                                                  |
| Accuracy                          | The worst-case time complexity analysis of the algorithms should be properly documented in the user manual of the application. |
| Possibility of recovery           | The application will use object serialization to ensure data persistence between two runs of the application.                  |

## Performance

---

| Category      | Description                                                                                                                                           |
| :------------ | :---------------------------------------------------------------------------------------------------------------------------------------------------- |
| Response time | The implemented algorithm should be analyzed in terms of its worst-case time complexity, and it should be compared to a benchmark algorithm provided. |

## Supportability

---

| Category        | Description                                                                                                                                                                                                                                             |
| :-------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Configurability | The application will support both Portuguese and English languages, at least.                                                                                                                                                                           |
| Testability     | The application will implement unit tests for all methods, except for methods that implement Input/Output operations. The unit tests will be implemented using the JUnit 5 framework. The JaCoCo plugin should be used to generate the coverage report. |
| Compatibility   | The application will be compatible with any machine that has a operating system. (macOS,Windows,Linux)                                                                                                                                                  |
| Localizability  | The application will be administered by the portuguese General Health Direction (DGS)                                                                                                                                                                   |

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
