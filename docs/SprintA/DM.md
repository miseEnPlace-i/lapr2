# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes

To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development".

### _Conceptual Class Category List_

**Business Transactions**

- a

**Transaction Line Items**

- VaccineType

**Product/Service related to a Transaction or Transaction Line Item**

- Vaccine

**Transaction Records**

- VaccineAdministration; VaccinationScheduling; RegisterVaccine; RegisterNurse; RegisterReceptionist; CertificateIssuance;

**Roles of People or Organizations**

- Nurse; Coordinator; Receptionist; Administrator; DGSAdministrator; SNSUser;

**Places**

- CommunityMassVaccinationCenter; HealthCareCenter; WaitingRoom;

**Noteworthy Events**

- RegisterSNSUserArrival; VaccinationScheduling; ScheduleConfirmation; VaccinationAdministration; UserWaitingInRecoveryRoom; LeaveVaccinationCenter; CertificateIssuance; CheckUserMedicalHistory

**Physical Objects**

- Vaccine

**Descriptions of Things**

- VaccineType; OngoingOutbreak

**Catalogs**

- Vaccines

**Containers**

- ScheduledUsersList; WaitingUsersList; ReadyUsersList; RecoveringUsersList

**Elements of Containers**

- SNSUser

**Organizations**

- DGS; ARS; AGES

**Other External/Collaborating Systems**

- DGS; SMSSender

**Records of finance, work, contracts, legamatters**

- Statistics; Charts; Report; VaccinationCertificate; EUCovidCertificate; SMSAuthorization

**Financial Instruments**

- ***

**Documents mentioned/used to perform some work**

- MedicalHistory

## **Rationale to identify associations between conceptual classes**

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations:

- **_Vaccine_** has a **_VaccineType_**
- **_HealthCareCenter_** belongs to a **_ARS_**
- **_HealthCareCenter_** belongs to a **_ACES_**
- **_CommunityMassVaccinationCenter_** is associated with an **_OngoingOutbreak_**
- **_CommunityMassVaccinationCenter_** has some **_VaccineTypes_** assigned
- **_SNSUser_** owns a **_MedicalHistory_**
- Each **_CommunityMassVaccinationCenter_** has a **_CenterCoordinator_**
- **_A_** ... **_B_**
- **_A_** ... **_B_**
- **_A_** ... **_B_**
- **_A_** ... **_B_**
- **_A_** ... **_B_**
- **_A_** ... **_B_**

| Concept (A)                    |  Association  |                    Concept (B) |
| ------------------------------ | :-----------: | -----------------------------: |
| CenterAdministrator            |  configures   |                    VaccineType |
| CenterAdministrator            |  configures   |                        Vaccine |
| CenterAdministrator            |    manages    | CommunityMassVaccinationCenter |
| CenterAdministrator            |    manages    |                      Employees |
| CommunityMassVaccinationCenter |  administers  |                       Vaccines |
| CommunityMassVaccinationCenter |      has      |              CenterCoordinator |
| DGS                            |     sends     |          SMSVaccineAppointment |
| DGSAdministrator               |   registers   |                        SNSUser |
| DGSAdministrator               |   registers   | CommunityMassVaccinationCenter |
| DGSAdministrator               |   registers   |              CenterCoordinator |
| DGSAdministrator               |   registers   |                  Receptionists |
| DGSAdministrator               |   registers   |                         Nurses |
| HealthcareCenter               |  administers  |                       Vaccines |
| HealthcareCenter               | is associated |                            ARS |
| HealthcareCenter               | is associated |                           ACES |
| Nurse                          |     emits     |         VaccinationCertificate |
| Nurse                          |     check     |                 UserHealthInfo |
| Nurse                          |     check     |                    ListSNSUser |
| Nurse                          |    reports    |               AdverseReactions |
| Nurse                          |   registers   |             VaccinationDetails |
| SNS User                       |   schedule    |                        Vaccine |
| SNS User                       |   requests    |   EUCOVID-19DigitalCertificate |
| VaccineType                    |      has      |                        Vaccine |
| Lot number                     |      has      |                        Vaccine |
| Receptionist                   |   registers   |                   UserArrivals |
| Receptionist                   |   verifies    |                VaccineSchedule |
| Receptionist                   |   confirms    |                      UserReady |
| ...                            |      ...      |                            ... |
| ...                            |      ...      |                            ... |

## Domain Model

**Do NOT forget to identify concepts attributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![DM.svg](DM.svg)
