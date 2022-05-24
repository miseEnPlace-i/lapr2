# US 10 - Schedule a Vaccine

## 1. Requirements Engineering


### 1.1. User Story Description


_"As an SNS user, I intend to use the application to schedule a vaccine."_


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	"To take a vaccine, the SNS user should use the application to schedule his/her vaccination."

>	"The user should introduce his/her SNS user number, select the vaccination center, the date, and the time (s)he wants to be vaccinated as well as the type of vaccine to be administered (by default, the system suggests the one related to the ongoing outbreak)."

>	"Then, the application should check the vaccination center capacity for that day/time and, if possible, confirm that the vaccination is scheduled and inform the user that (s)he should be at the selected vaccination center at the scheduled day and time."

> 	"The SNS user may also authorize the DGS to send an SMS message with information about the scheduled appointment. If the user authorizes the sending of the SMS, the application should send an SMS message when the vaccination event is scheduled and registered in the system."

**From the client clarifications:**

> **Question:** How does the system know which vaccine to suggest when the SNS user is scheduling their vaccine? Is the administrator responsible for setting the outbreak vaccine?
>
> **Answer:** _"The ongoing outbreak should be defined in the system using a configuration file."_

> **Question:** Community mass vaccination centers are facilities created specifically to administer a single type of vaccine as a response to an ongoing disease breakdown. When there is no ongoing outbreak, are this centers out of service or is another type of vaccine selected?
>
> **Answer:** _"These centers are closed when there is no outbreak."_

### 1.3. Acceptance Criteria


* **AC1:** All required fields must be filled in. (SNS user Number, ).
* **AC2:** The SNS user number must have 9 digits.
* **AC3:** A SNS user cannot schedule the same vaccine more than once.
* **AC4:** The algorithm should check if the SNS user is within the age and time since the last vaccine.


### 1.4. Found out Dependencies


* There is a dependency to "US03 - Register an SNS User", because SNS users can only use the application if they are registered in the system.
* There is a dependency to "US09 - Register a Vaccination Center", as the application needs to know which vaccination centers are available.
* There is a dependency to "US12 - Specify a Vaccine Type", as the application needs to know which vaccine types are available, so that SNS users (and receptionists) can schedule a vaccine.
* There is a dependency to "US13 - Specify a Vaccine", because in order to schedule a vaccine, there has to exist, at least, one vaccine registered in the system.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* SNS user number
	* date and time of the vaccination

* Selected data:
	* vaccine type
	* vaccination center

**Output Data:**

* Suggested vaccine type (or the list of all available vaccine types)
* List of all available vaccination centers
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)
 
**Alternative 1**

![US10_SSD](SSD/US10_SSD.svg)

Other alternatives might exist.

### 1.7 Other Relevant Remarks

n/a


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US10_DM](DM/US10_DM.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                       | Question: Which class is responsible for...            | Answer             | Justification (with patterns)                           |
| :--------------------------------------------------- | :----------------------------------------------------- | :----------------- | :------------------------------------------------------ |
| Step 1: asks to register a new employee              | ...instantiating a new employee                        | EmployeeStore      | Creator: it contains all employee objects               |
| Step 2: shows user roles list and asks to select one | ...knowing the user roles to show?                     | EmployeeRoleStore  | IE: knows all employee roles                            |
| Step 3: selects a user role                          | ...saving the selected role?                           | Employee           | IE: object created in step 1 is classified in one role. |
| Step 4: requests data (name, email, password, etc)   | n/a                                                    | n/a                | n/a                                                     |
| Step 5: types requested data                         | ...saving the input data?                              | Employee           | IE: object created in step 1 has its own data           |
| Step 6: shows all data and requests confirmation     | ...validating the data introduced (local validation)?  | Employee           | IE: owns its data                                       |
| Step 6: shows all data and requests confirmation     | ...validating the data introduced (global validation)? | EmployeeStore      | IE: knows all its employees                             |
| Step 7: confirms the data                            | ...saving the created employee?                        | EmployeeStore      | IE: holds every information about the employees         |
| Step 8: informs operation success                    | ...informing operation success?                        | RegisterEmployeeUI | IE: responsible for user interaction                    |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Employee
 * EmployeeStore
 * EmployeeRoleStore

Other software classes (i.e. Pure Fabrication) identified: 

 * RegisterEmployeeUI
 * RegisterEmployeeController

## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US10_SD](SD/US10_SD.svg)

## 3.3. Class Diagram (CD)

**From alternative 1**

![US10_CD](CD/US10_CD.svg)

# 4. Tests

## Instantiate objects with null values

**Test 1:** Check that it is not possible to create an instance of the Employee class with null values. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureNullIsNotAllowed() {
		Employee instance = new Employee(null, null, null, null, null, null);
	}

## Instantiate objects with empty values (AC1)

**Test 2:** Check that it is not possible to create an instance of the Employee class with empty values. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureEmptyIsNotAllowed() {
		Employee instance = new Employee("", "", "", "", "", "");
	}

## Instatiate objects with invalid email (AC4)

**Test 3:** Check that it is not possible to create an instance of the Employee class with an invalid email. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidEmail() {
		String invalidEmail = "joana";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", "+351916478865", invalidEmail, "30365258 4 ZZ0", "Nurse");
	}

## Instatiate objects with invalid phone number (AC5)

**Test 4:** Check that it is not possible to create an instance of the Employee class with an invalid phone number, which contains an invalid length. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidPhoneNumberWithValidLength() {
		String invalidPhoneNumber = "+3519164788687897845";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", invalidPhoneNumber, "joanamaria@gmail.com", "30365258 4 ZZ0", "Nurse");
	}

**Test 5:** Check that it is not possible to create an instance of the Employee class with an invalid phone number, which contains letters. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidPhoneNumberWithoutLetters() {
		String invalidPhoneNumber = "+3519164foe";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", invalidPhoneNumber, "joanamaria@gmail.com", "30365258 4 ZZ0", "Nurse");
	}

## Instatiate objects with invalid Citizen Card number (AC6)

**Test 6:** Check that it is not possible to create an instance of the Employee class with an invalid Citizen Card number. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidCCNumber() {
		int invalidCCNumber = "3030398578392200";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", "+351916478865", "joanamaria@gmail.com", invalidCCNumber, "Nurse");
	}

# 5. Construction (Implementation)


## Class RegisterEmployeeController

	public void createEmployee(String name, String address, String phoneNumber, String email, String citizenCardNumber, String roleId) {
		// create an instance of an Employee
		this.employee = store.addEmployee(name, address, phoneNumber, email, citizenCardNumber, roleId);

		// validate the Employee
		store.validateEmployee(employee);
  	}


## Class EmployeeStore


	public Employee addEmployee(String name, String phoneNumber, String email, String address, String citizenCard, String roleId) {
		// create an instance of the Employee
		Employee employee = new Employee(name, phoneNumber, email, address, citizenCard, roleId);

		// return the instance of the Employee
		return employee;
  	}	



# 6. Integration and Demo 

(Information not available yet.)


# 7. Observations

There is no class relation between Employee and User to enforce both to exist in the first place in the code.
The relation between them is made by corresponding e-mail (unique attribute in both).
