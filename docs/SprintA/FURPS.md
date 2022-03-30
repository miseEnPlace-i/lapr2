# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

Audit - If the user information is correct, the receptionist acknowledges the system that the user is ready to take the vaccine.

Scheduling - Provide a service to allow SNS users to schedule a vaccine and obtain a vaccination certificate.

Persistence - The application should use object serialization to ensure data persistence between two runs of the application.

Authentication - Those who wish to use the application must be authenticated with a password.

Communication - The user may also authorize the DGS to send a SMS message with
information about the scheduled appointment.

Reporting - The JaCoCo plugin will be used to generate the coverage report.

Security - All user who wish to use the application will be authenticated wit a password holding seven alphanumeric characters, including three capital letters and two digits.

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

Accessibility - If the user authorizes the sending of the SMS, the application will send an SMS message when the vaccination event is scheduled and registered in the system.

Error prevention - Testing often the application to make it hard for the user to commit the error.

Interface aesthetics and design - The application graphical interface will be simple, intuitive and consistent developed in JavaFX 11.

Help - Service to provide the user help on the application.

Documentation - The application will have a user manual.

Consistency - All user who wish to use the application will be authenticated wit a password holding seven alphanumeric characters, including three capital letters and two digits.

User data - Only nurses will be allowed to access all user´s health data.

## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

Accuracy - The worst-case time complexity analysis of the algorithms should be properly documented in the user manual of the application.

Possibility of recovery - The application will use object serialization to ensure data persistence between two runs of the application.

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

Response time - The implemented algorithm should be analyzed in terms of its worst-case time complexity, and it should be compared to a benchmark algorithm provided.  

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

Configurability - The application will support both Portuguese and English languages, at least.

Testability - The application will implement unit tests for all methods, except for methods that
implement Input/Output operations. The unit tests will be implemented using the JUnit 5 framework.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

(fill in here )

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

(fill in here )

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

(fill in here )

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here )
