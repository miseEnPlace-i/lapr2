# OO Analysis

## Rationale to identify domain conceptual classes

### _Conceptual Class Category List_

**Business Transactions**

- VaccinationScheduling

**Product/Service related to a Transaction or Transaction Line Item**

- Vaccine; VaccineType; VaccinationCertificate

**Transaction Records**

- VaccinationCertificate

**Roles of People or Organizations**

- Nurse; Center Coordinator; Receptionist; Administrator

**Places**

- CommunityMassVaccinationCenter; HealthCareCenter; WaitingRoom; RecoveryRoom

**Noteworthy Events**

- RegisterSNSUserArrival; VaccinationScheduling; ScheduleConfirmation; VaccinationAppointment; LeaveVaccinationCenter; CertificateIssuance; CheckUserMedicalHistory

**Physical Objects**

- Vaccine

**Descriptions of Things**

- VaccineType; OngoingOutbreak; VaccineAdministrationProcess

**Catalogs**

- Vaccines

**Containers**

- ScheduledUsersList; WaitingUsersList; ReadyUsersList; RecoveringUsersList

**Elements of Containers**

- SNSUser

**Organizations**

- DGS; ARS; AGES

**Other External/Collaborating Systems**

- DGS; SMSSender; EmailSender

**Records of finance, work, contracts, legamatters**

- Statistics; Charts; Report; VaccinationCertificate; SMSAuthorization

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
| Appointment                    |  is associated   |                       SNSUser |
| Appointment                    |  has a specific  |                       Vaccine |
| CenterCoordinator              |    generates     |                  CenterReport |
| CenterCoordinator              |     analyses     |                  CenterReport |
| CommunityMassVaccinationCenter |       is a       |             VaccinationCenter |
| CommunityMassVaccinationCenter |       has        |             CenterCoordinator |
| CommunityMassVaccinationCenter |       has        |                  CenterReport |
| Email                          |       is a       |                  Notification |
| HealthcareCenter               |       is a       |             VaccinationCenter |
| HealthcareCenter               |  is associated   |                           ARS |
| HealthcareCenter               |  is associated   |                          ACES |
| Nurse                          |      is an       |                      Employee |
| Nurse                          |      emits       |     VaccineDigitalCertificate |
| Nurse                          |      checks      |                UserHealthInfo |
| Nurse                          |      checks      |                 ReadyUserList |
| Nurse                          |     reports      |              AdverseReactions |
| Nurse                          |    registers     |            VaccinationDetails |
| Nurse                          |     manages      |           RecoveringUsersList |
| Nurse                          | forwards user to |                  RecoveryRoom |
| Nurse                          |      checks      |         AdministrationProcess |
| Receptionist                   |      is an       |                      Employee |
| Receptionist                   |    registers     |                   UserArrival |
| Receptionist                   |     verifies     |               VaccineSchedule |
| Receptionist                   |     manages      |                 UserReadyList |
| Receptionist                   |    schedules     |                   Appointment |
| Receptionist                   | forwards user to |                   WaitingRoom |
| SMS                            |       is a       |                  Notification |
| SNS User                       |       has        |                UserHealthInfo |
| SNS User                       |    schedules     |                   Appointment |
| SNS User                       |     requests     |            VaccineCertificate |
| System                         |      sends       | RecoveryPeriodEndNotification |
| System                         |      sends       |            AppointmentDetails |
| System                         |      emits       |            VaccineCertificate |
| Vaccine                        |       has        |                   VaccineType |
| Vaccine                        |       has        |         AdministrationProcess |
| VaccineCertificate             |       has        |                       Vaccine |
| VaccinationCenter              |       has        |             CenterCoordinator |
| VaccinationCenter              |   administers    |                      Vaccines |

## Domain Model

![DM.svg](DM.svg)
