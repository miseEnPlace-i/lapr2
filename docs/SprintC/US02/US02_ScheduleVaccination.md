# US 02 - Schedule a vaccination

## 1. Requirements Engineering

### 1.1. User Story Description

_"As a receptionist at one vaccination center, I want to schedule a vaccination."_

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> "Some users (e.g.: older ones) may want to go to a healthcare center to schedule the vaccine appointment with the help of a receptionists at one vaccination center."

> " (...) his/her SNS user number, select the vaccination center, the date, and the time (s)he wants to be vaccinated as well as the type of vaccine to be administered (...)"

> "Then, the application should check the vaccination center capacity for that day/time and, if possible, confirm that the vaccination is scheduled and inform the user that (s)he should be at the selected vaccination center at the scheduled day and time."

> "The SNS user may also authorize the DGS to send an SMS message with information about the scheduled appointment. If the user authorizes the sending of the SMS, the application should send an SMS message when the vaccination event is scheduled and registered in the system."

**From the client clarifications:**

> **Question:** "How does the system know which vaccine to suggest when the SNS user is scheduling their vaccine? Is the administrator responsible for setting the outbreak vaccine?"
>  
> **Answer:** "The ongoing outbreak should be defined in the system using a configuration file."

> **Question:** "A receptionist has the ability to schedule an appointment in different vaccination centres or only on their own?"
>
> **Answer:** "A receptionist (and a user) can schedule a vaccine at any vaccination center."
> 
> **Question:** "When a receptionist schedules a vaccination for an SNS user, should they be presented with a list of available vaccines (brands, that meet acceptance criteria) from which to choose? Or should the application suggest only one?"
>
> **Answer:** "The receptionist do not select the vaccine brand. When the user is at the vaccination center to take the vaccine, the nurse selects the vaccine. In Sprint D we will introduce new USs where the nurse records the administration of a vaccine to a SNS user."


### 1.3. Acceptance Criteria

* **AC1:** SNS Number must be filled in. Should follow the portuguese format (9 digits).
* **AC2:** Date must be filled in. Should follow the portuguese format (dd/mm/yyyy).
* **AC3:** Time must be filled in. Should follow the following format (HH:mm).
* **AC4:** All required fields must be filled in.
* **AC5:** The algorithm should check if the SNS User is within the age and time since the last vaccine.
* **AC6:** A receptionist cannot schedule the same vaccine more than once for the same SNS User.

### 1.4. Found out Dependencies

* There is a dependency to "US03 - Register an SNS User", as the SNS User needs to be registered in the system to be part of an appointment.
* There is a dependency to "US09 - Register a Vaccination Center", as it needs to exist at least one vaccination center registered in the system.
* There is a dependency to "US12 - Specify a Vaccine Type", as the application needs to know which vaccine types are available, so that SNS users and receptionists can schedule a vaccine.
* There is a dependency to "US13 - Specify a Vaccine", because in order to schedule a vaccine, there has to exist, at least, one vaccine registered in the system.

 
### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* SNS number, 
	* date, 
	* time.
* Selected data:
	* Vaccination centers,
	* Type of vaccine.

**Output Data:**

* List of vaccination centers.
* Suggested vaccine type (or the list of all available vaccine types).
* (In)Success of the operation.

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US02_SSD](SSD/US02_SSD.svg)

**Other alternatives might exist.**

### 1.7 Other Relevant Remarks

* Not found.

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US02_MD](DM/US02_DM.svg)

### 2.2. Other Remarks

Not found.

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for... | Answer                        | Justification (with patterns)                                                                                 |
| :------------- | :------------------------------------------ | :---------------------------- | :------------------------------------------------------------------------------------------------------------ |
| Step 1         | ... interacting with the actor?             | ScheduleVaccineReceptionistUI | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ... coordinating the US?                    | ScheduleVaccineController     | Controller                                                                                                    |
|                | ... instantiating a new Appointment?        | Appointment                   | Creator: R1/2                                                                                                 |
| Step 2         | n/a                                         | n/a                           | n/a                                                                                                           |
| Step 3         | ... validate SNS User Number format?        | FieldsToValidate              | IE: knows all data formats.                                                                                   |
| Step 4         | ... knows the suggested vaccine?            | Company                       | IE: knows the ongoing outbreak.                                                                               |
| Step 5         | n/a                                         | n/a                           | n/a                                                                                                           |
| opt Step 6     | ... gets all vaccine types?                 | VaccineTypeStore              | IE: knows all the existing vaccine types.                                                                     |
|                | ... listing all vaccine types?              | VaccineTypeDtoList            | Dto:knows relevant information about vaccine types.                                                           |
| opt Step 7     | n/a                                         | n/a                           | n/a                                                                                                           |
| step 8         | ... gets all available vaccination centers? | VaccinationCenterStore        | IE: knows all available vaccination centers.                                                                  |
|                | ... listing all vaccination centers?        | VaccinationCenterDtoList      | Dto: knows relevant information about vaccination centers.                                                    |
| step 9         | ... saving vaccination center selected?     | Appointment                   | IE:                                                                                                           |
| step 10        | n/a                                         | n/a                           | n/a                                                                                                           |
| step 11        | ... saving date and time selected?          | Appointment                   | IE:                                                                                                           |
|                | ... validating center availability?         | VaccinationCenter             | IE: knows vaccination center appointments.                                                                    |
| Step 12        | n/a                                         | n/a                           | n/a                                                                                                           |
| Step 13        | ... knows the method to notify user?        | SenderFactory                 | Factory: knows the logic to notify user.                                                                      |
|                | ... send the SMS notification?              | ISender                       | IE: knows how to notify user.                                                                                 |
| Step 14        | ... gets relevant information to the user?  | AppointmentDto                | Dto: knows relevant information.                                                                              |
|                | ... check for duplicates appointment?       | AppointmentScheduleList       | IE: holds every information about the schedules.                                                              |
| Step 15        | ... saving the new appointment?             | AppointmentScheduleList       | IE: holds every information about the schedules.                                                              |
| Step 16        | ... informing operation success?            | ScheduleVaccineReceptionistUI | IE: is responsible for user interactions.                                                                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Company
 * Appointment
 * AppointmentScheduleList
 * VaccineTypeStore
 * SNSUserStore
 * ISender
 * SenderFactory
  
Other software classes (i.e. Pure Fabrication) identified: 

 * ScheduleVaccineReceptionistUI  
 * ScheduleVaccineController

## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US02_SD](SD/US02_SD.svg)


## 3.3. Class Diagram (CD)

**From alternative 1**

![US02_CD](CD/US02_CD.svg)

# 4. Tests 

**Test 1:** Check that it is possible to schedule an appointment. 

	@Test
  public void ensureThatIsPossibleToScheduleAppointment() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));
      Calendar nextDay = DateUtils.toCalendar(DateUtils.addDays(sdf.parse("01/01/2022 10:00"), 1));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);

      assertNotNull(appointment);

      appointments.saveAppointment(appointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);
      Appointment[][] emptyList = appointments.getAppointmentScheduleForDay(nextDay);

      assertNotNull(list);
      assertNull(emptyList);
    } catch (ParseException e) {
    }
  }
	

**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureReferenceMeetsAC2() {
		Category cat = new Category(10, "Category 10");
		
		Task instance = new Task("Ab1", "Task Description", "Informal Data", "Technical Data", 3, 3780, cat);
	}


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class ScheduleVaccineController 

  public ScheduleVaccineController(Company company) {
    this.company = company;
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
    this.vaccineTypeStore = company.getVaccineTypeStore();
    this.snsUserStore = company.getSNSUserStore();
    this.vaccineStore = company.getVaccineStore();
  }

	public void createAppointment(String snsNumber, Calendar date, VaccinationCenter center, VaccineType vaccineType, boolean sms) {
    this.appointmentSchedule = center.getAppointmentList();
    SNSUser user = snsUserStore.findSNSUserByNumber(snsNumber);
    this.appointment = appointmentSchedule.create(user, date, center, vaccineType, sms);
  }


## Class Appointment

	public Appointment(SNSUser snsUser, Calendar date, VaccinationCenter center, VaccineType vaccineType, boolean sms) {
    this.snsUser = snsUser;
    this.date = date;
    this.center = center;
    this.vaccineType = vaccineType;
    this.sms = sms;
  }



# 6. Integration and Demo 

* A new option on the Receptionist menu options was added (Schedule a vaccine).


# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain. 

Is there any way to avoid this to happen?





