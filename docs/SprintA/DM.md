# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

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
| Nurse                          |      is an       |                      Employee |
| Nurse                          |      emits       |     VaccineDigitalCertificate |
| Nurse                          |      checks      |                UserHealthInfo |
| Nurse                          |      checks      |                 ReadyUserList |
| Nurse                          |     reports      |              AdverseReactions |
| Nurse                          |    registers     |            VaccinationDetails |
| Nurse                          |     manages      |           RecoveringUsersList |
| Nurse                          | forwards user to |                  RecoveryRoom |
| Nurse                          |      checks      |         AdministrationProcess |
| SNS User                       |       has        |                UserHealthInfo |
| SNS User                       |    schedules     |                   Appointment |
| SNS User                       |     requests     |            VaccineCertificate |
| Vaccine                        |       has        |                   VaccineType |
| Vaccine                        |       has        |         AdministrationProcess |
| VaccineCertificate             |       has        |                       Vaccine |
| Receptionist                   |      is an       |                      Employee |
| Receptionist                   |    registers     |                   UserArrival |
| Receptionist                   |     verifies     |               VaccineSchedule |
| Receptionist                   |     manages      |                 UserReadyList |
| Receptionist                   |    schedules     |                   Appointment |
| Receptionist                   | forwards user to |                   WaitingRoom |
| System                         |      sends       | RecoveryPeriodEndNotification |
| System                         |      sends       |            AppointmentDetails |
| System                         |      emits       |            VaccineCertificate |
| SMS                            |       is a       |                  Notification |
| Email                          |       is a       |                  Notification |

## Domain Model

![DM.svg](DM.svg)
