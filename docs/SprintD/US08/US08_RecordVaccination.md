# US 08 - Record the Administration of a Vaccine

## 1. Requirements Engineering


### 1.1. User Story Description


_"As a nurse, I want to record the administration of a vaccine to an SNS user. At the end of the recovery period, the user should receive an SMS message informing the SNS user that he can leave the vaccination center."_


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	"After giving the vaccine to the user, each nurse registers the event in the system, more precisely, registers the vaccine type (e.g.: Covid-19), vaccine name/brand (e.g.: Astra Zeneca, Moderna, Pfizer), and the lot number used."

>	"Afterwards, the nurse sends the user to a recovery room, to stay there for a given recovery period (e.g.: 30 minutes). If there are no problems, after the given recovery period, the user should leave the vaccination center."

>	"The system should be able to notify (e.g.: SMS or email) the user that his/her recovery period has ended."

**From the client clarifications:**

> **Question:** Lorem ipsum
>
> **Answer:** _"Lorem ipsum"_

> **Question:** Lorem ipsum
>
> **Answer:** _"Lorem ipsum"_

### 1.3. Acceptance Criteria


* **AC1:** The nurse should select a vaccine and the administered dose number.


### 1.4. Found out Dependencies


* There is a dependency to "US01 - Schedule a Vaccine", as users need to schedule a vaccine in order to get it administered.
* There is a dependency to "US03 - Register an SNS User", as nurses can only administer a vaccine to users that are registered in the system.
* There is a dependency to "US04 - Register the Arrival of an SNS User", as receptionists need to register arrivals so that SNS Users can get vaccines administered.
* There is a dependency to "US09 - Register a Vaccination Center", as a nurse administers a vaccine in vaccination centers.
* There is a dependency to "US10 - Register an Employee", as nurses have to be registered in the system in order to administrate vaccines.
* There is a dependency to "US12 - Specify a Vaccine Type", because in order to administer vaccines, there has to exist, at least, one vaccine type.
* There is a dependency to "US13 - Specify a Vaccine", because in order to administer vaccines, there has to exist, at least, one vaccine.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* SNS user number
	* lote number
	* dose number

* Selected data:
	* vaccine

**Output Data:**

* List of vaccines that have the vaccine type of the most recent user arrival
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)
 
**Alternative 1**

![US08_SSD](SSD/US08_SSD.svg)

Other alternatives might exist.

### 1.7 Other Relevant Remarks

n/a


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US08_DM](DM/US08_DM.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                                                   | Question: Which class is responsible for... | Answer                   | Justification (with patterns)                                                 |
| :------------------------------------------------------------------------------- | :------------------------------------------ | :----------------------- | :---------------------------------------------------------------------------- |
| Step 1: asks to schedule a vaccine                                               | ...instantiate a new Appointment            | AppointmentScheduleList  | Creator: it contains all appointment objects                                  |
| Step 2: shows suggested vaccine type and asks to accept it                       | ...knowing the vaccine type to show?        | Company                  | IE: knows the suggested vaccine type defined in the configuration file        |
| Step 3: accepts the suggested vaccine type                                       | ...saving the vaccine type?                 | Appointment              | IE: object created in step 1 has one vaccine type                             |
| Step 4: shows a list of all vaccine types and asks to select one                 | ...knowing the vaccine types to show?       | VaccineTypeStore         | IE: knows all vaccine types                                                   |
| Step 5: selects a vaccine type                                                   | ...saving the selected vaccine type?        | Appointment              | IE: object created in step 1 has one vaccine type                             |
| Step 6: shows a list of all available vaccination centers and asks to select one | ...knowing the vaccination centers to show? | VaccinationCenterStore   | IE: knows all vaccination centers                                             |
| Step 7: selects a vaccination center                                             | ...saving the selected vaccination center?  | Appointment              | IE: object created in step 1 has one vaccination center                       |
| Step 8: requests data (date, time)                                               | n/a                                         | n/a                      | n/a                                                                           |
| Step 9: types requested data                                                     | ...informing operation success?             | Appointment              | IE: object created in step 1 has date and time                                |
| Step 10: asks permission to send sms about the scheduled vaccine                 | n/a                                         | n/a                      | n/a                                                                           |
| Step 11: accepts or declines the request                                         | ...saving the selected option?              | Appointment              | IE: object created in step 1 has the information about the sending of the sms |
| Step 12: shows all data and requests confirmation                                | ...validating the data introduced?          | AppointmentScheduledList | IE: knows all information needed to validate the appointment                  |
| Step 13: confirms the data                                                       | ...saving the created appointment?          | AppointmentScheduledList | IE: holds all appointment objects                                             |
| Step 14: informs operation success                                               | ...informing operation success?             | ScheduleVaccineUI        | IE: responsible for user interaction                                          |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Appointment
 * AppointmentScheduleList
 * VaccinationCenterStore
 * VaccineTypeStore
 * Company

Other software classes (i.e. Pure Fabrication) identified: 

 * ScheduleVaccineUI
 * ScheduleVaccineController

## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US08_SD](SD/US08_SD.svg)

## 3.3. Class Diagram (CD)

**From alternative 1**

![US08_CD](CD/US08_CD.svg)

# 4. Tests

## Instantiate objects with null values

**Test 1:** Check that it is not possible to create an instance of the Appointment class with null values. 

	@Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        Appointment instance = new Appointment(null, null, null, null, null);
    }

# 5. Construction (Implementation)

## Class ScheduleVaccineController

    public void createAppointment(Date date, String time, VaccinationCenterListDTO centerDto, VaccineTypeDTO vaccineTypeDto, boolean sms) {
        VaccinationCenter center = vaccinationCenterStore.getVaccinationCenterWithEmail(centerDto.getEmail());
        this.appointmentSchedule = center.getAppointmentList();

	    String email = App.getInstance().getCurrentUserSession().getUserId().getEmail();
        SNSUser snsUser = snsUserStore.findSNSUserByEmail(email);

        try {
            Calendar dateAndTime = CalendarUtils.parseDateTime(date, time);

            this.appointment = appointmentSchedule.createAppointment(snsUser, dateAndTime, vaccineTypeDto, sms);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Date or time invalid.");
        }

        appointmentSchedule.validateAppointment(this.appointment);
  	}

## Class AppointmentScheduleList

    public Appointment createAppointment(SNSUser snsUser, Calendar date, VaccineTypeDTO vaccineTypeDto, boolean sms) {
        VaccineType vaccineType = VaccineTypeMapper.toModel(vaccineTypeDto);

        Appointment appointment = new Appointment(snsUser, date, this.vaccinationCenter, vaccineType, sms);

        return appointment;
    }

# 6. Integration and Demo 

A new option ("Schedule a Vaccine") was added to the SNS user menu.

For demo purposes, there are multiple objects of all classes created in the previous steps being created when the application is bootstrapped.

# 7. Observations

There are validations needed for this US that are not implemented in the system yet. Therefore, those validations are not implemented yet.
