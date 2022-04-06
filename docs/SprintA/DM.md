# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes

### _Conceptual Class Category List_

**Business Transactions**

- VaccinationProcess

**Transaction Line Items**

- VaccineType

**Product/Service related to a Transaction or Transaction Line Item**

- Vaccine; VaccinationCertificate

**Transaction Records**

- VaccineAdministration; VaccinationScheduling; RegisterVaccine; RegisterNurse; RegisterReceptionist; CertificateIssuance; UserArrival; AdverseReaction;

**Roles of People or Organizations**

- Nurse; Coordinator; Receptionist; Administrator;

**Places**

- CommunityMassVaccinationCenter; HealthCareCenter; WaitingRoom; RecoveryRoom;

**Noteworthy Events**

- RegisterSNSUserArrival; VaccinationScheduling; ScheduleConfirmation; VaccinationAppointment; VaccinationAdministration; UserWaitingInRecoveryRoom; LeaveVaccinationCenter; CertificateIssuance; CheckUserMedicalHistory

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

- DGS; SMSSender;

**Records of finance, work, contracts, legamatters**

- Statistics; Charts; Report; VaccinationCertificate; SMSAuthorization

**Financial Instruments**

- ***

**Documents mentioned/used to perform some work**

- MedicalHistory

## **Rationale to identify associations between conceptual classes**

| Concept (A)                    |   Association    |                   Concept (B) |
| :----------------------------- | :--------------: | ----------------------------: |
| Administrator                  |    configures    |                   VaccineType |
| Administrator                  |    configures    |                       Vaccine |
| Administrator                  |     manages      |             VaccinationCenter |
| Administrator                  |     manages      |                      Employee |
| Administrator                  |    registers     |                       SNSUser |
| Administrator                  |    registers     |             VaccinationCenter |
| Administrator                  |    registers     |                      Employee |
| CenterCoordinator              |    generates     |                  CenterReport |
| CenterCoordinator              |     analyses     |                  CenterReport |
| VaccinationCenter              |       has        |             CenterCoordinator |
| VaccinationCenter              |   administers    |                      Vaccines |
| CommunityMassVaccinationCenter |       is a       |             VaccinationCenter |
| CommunityMassVaccinationCenter |       has        |             CenterCoordinator |
| CommunityMassVaccinationCenter |       has        |                  CenterReport |
| HealthcareCenter               |       is a       |             VaccinationCenter |
| HealthcareCenter               |  is associated   |                           ARS |
| HealthcareCenter               |  is associated   |                          ACES |
| Appointment                    |  is associated   |                       SNSUser |
| Appointment                    |  has a specific  |                       Vaccine |
| Nurse                          |       is a       |                      Employee |
| Nurse                          |      emits       |     VaccineDigitalCertificate |
| Nurse                          |      checks      |                UserHealthInfo |
| Nurse                          |      checks      |                 ReadyUserList |
| Nurse                          |     reports      |              AdverseReactions |
| Nurse                          |    registers     |            VaccinationDetails |
| Nurse                          |     manages      |           RecoveringUsersList |
| Nurse                          | forwards user to |                  RecoveryRoom |
| Nurse                          |      checks      |       VaccinationInstructions |
| SNS User                       |       has        |                UserHealthInfo |
| SNS User                       |    schedules     |                       Vaccine |
| SNS User                       |     requests     |     VaccineDigitalCertificate |
| Vaccine                        |       has        |                   VaccineType |
| Vaccine                        |       has        |                AgeGroupDosage |
| TimeIntervalDoses              |       has        |                   VaccineType |
| VaccinationCertificate         |       has        |                       Vaccine |
| Receptionist                   |       is a       |                      Employee |
| Receptionist                   |    registers     |                   UserArrival |
| Receptionist                   |     verifies     |               VaccineSchedule |
| Receptionist                   |     manages      |                 UserReadyList |
| Receptionist                   |    schedules     |                       Vaccine |
| Receptionist                   | forwards user to |                   WaitingRoom |
| System                         |      sends       | RecoveryPeriodEndNotification |
| System                         |      sends       |         SMSVaccineAppointment |
| System                         |      emmits      |         VaccinationCertifcate |

## Domain Model

![DM.svg](DM.svg)
