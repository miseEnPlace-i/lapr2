# US 10 - Register an Employee 

## 1. Requirements Engineering


### 1.1. User Story Description


_"As an administrator, I want to register an Employee."_


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	"[...] employees enrolled in the vaccination process are almost the same in each kind of vaccination center". 

>	"Each vaccination center has a Center Coordinator [...]"

>	"[...] receptionists and nurses registered in the application will work in the vaccination process. As the allocation of receptionists and nurses to vaccination centers might be complex, by now, the system might assume that receptionists and nurses can work on any vaccination center."

> 	"Any Administrator uses the application to register [...] center coordinators, receptionists, and nurses enrolled in the vaccination process."

**From the client clarifications:**

> **Question:** Which information should the administrator know about the employees, in order to register them in the system?
>  
> **Answer:** _"Every Employee has only one role (Coordinator, Receptionist, Nurse). Employee attributes: Id (automatic), name, address, phone number, e-mail and Citizen Card number. All attributes are mandatory."_

> **Question:** How should the password be generated? Can the administrator choose the password?

> **Answer:** _"The password should be randomly generated."_


### 1.3. Acceptance Criteria


* **AC1:** All required fields must be filled in. (All attributes of the employee are required).
* **AC2:** The password created must contain seven alphanumeric characters, including three capital letters and two digits.
* **AC3:** Each user must have a single role defined in the system.
* **AC4:** The email inserted must be valid, verified using a regular expression.
* **AC5:** The phone number inserted must be valid, containing only numbers, and the length must be exactly 9 characters.
* **AC6:** The Citizen Card number inserted must be valid, containing only numbers, and the length must be exactly 8 characters.


### 1.4. Found out Dependencies


* There is a dependency to "USXX - Create an user role" since at least one user role must exist to classify the employee being created.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* name
	* adress
	* phone number
	* email
	* Citizen Card number

* Selected data:
	* user role 

**Output Data:**

* List of existing user roles
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US10_SSD](ssd/US10_SSD.svg)

Other alternatives might exist, such as asking the user role before inserting the employee's data.

### 1.7 Other Relevant Remarks

n/a


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US10_DM](US10_DM.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID                                       | Question: Which class is responsible for...            | Answer           | Justification (with patterns)                           |
| :--------------------------------------------------- | :----------------------------------------------------- | :--------------- | :------------------------------------------------------ |
| Step 1: asks to register a new employee              | ...instantiating a new employee                        | Company          | --                                                      |
| Step 2: requests data (name, email, password, etc)   | n/a                                                    | n/a              | n/a                                                     |
| Step 3: types requested data                         | ...saving the input data?                              | Employee         | IE: object created in step 1 has its own data           |
| Step 4: shows user roles list and asks to select one | ...knowing the user roles to show?                     | Company          | IE: user roles are defined by the company               |
| Step 5: selects a user role                          | ...saving the selected role?                           | Employee         | IE: object created in step 1 is classified in one role. |
| Step 6: shows all data and requests confirmation     | ...validating the data introduced (local validation)?  | Employee         | IE: owns its data                                       |
| Step 6: shows all data and requests confirmation     | ...validating the data introduced (global validation)? | Company          | IE: knows all its employees                             |
| Step 7: confirms the data                            | ...saving the created employee?                        | Company          | IE: holds every information about the employees         |
| Step 8: informs operation success                    | ...informing operation success?                        | CreateEmployeeUI | IE: responsible for user interaction                    |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Employee
 * Company

Other software classes (i.e. Pure Fabrication) identified: 

 * CreateEmployeeUI
 * CreateEmployeeController

## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US10_SD](US10_SD.svg)

## 3.3. Class Diagram (CD)

**From alternative 1**

![US10_CD](US10_CD.svg)

# 4. Tests

## Instantiate objects with null values

**Test 1:** Check that it is not possible to create an instance of the Employee class with null values. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureNullIsNotAllowed() {
		Employee instance = new Employee(null, null, null, null, null, null, null);
	}

## Instantiate objects with empty values (AC1)

**Test 2:** Check that it is not possible to create an instance of the Employee class with empty values. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureEmptyIsNotAllowed() {
		Employee instance = new Employee("", "", "", "", "", "", "");
	}

## Instatiate objects with invalid password (AC2)

**Test 3:** Check that it is not possible to create an instance of the Employee class with an invalid password, which has a valid length, but invalid characters. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidPasswordWithValidChars() {
		String invalidPassword = "joana12";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", "+351916478865", "joanamaria@gmail.com", 30356786, invalidPassword, "Nurse");
	}

**Test 4:** Check that it is not possible to create an instance of the Employee class with an invalid password, which has a valid characters, but invalid length. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidPasswordWithValidLength() {
		String invalidPassword = "JOAna123455667";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", "+351916478865", "joanamaria@gmail.com", 30356786, invalidPassword, "Nurse");
	}

## Instatiate objects with invalid email (AC4)

**Test 5:** Check that it is not possible to create an instance of the Employee class with an invalid email. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidEmail() {
		String invalidEmail = "joana";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", "+351916478865", invalidEmail, 30356786, "JOAna12", "Nurse");
	}

## Instatiate objects with invalid phone number (AC5)

**Test 6:** Check that it is not possible to create an instance of the Employee class with an invalid phone number, which contains an invalid length. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidPhoneNumberWithValidLength() {
		String invalidPhoneNumber = "+3519164788687897845";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", invalidPhoneNumber, "joanamaria@gmail.com", 30356786, "JOAna12", "Nurse");
	}

**Test 7:** Check that it is not possible to create an instance of the Employee class with an invalid phone number, which contains letters. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidPhoneNumberWithoutLetters() {
		String invalidPhoneNumber = "+3519164foe";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", invalidPhoneNumber, "joanamaria@gmail.com", 30356786, "JOAna12", "Nurse");
	}

## Instatiate objects with invalid Citizen Card number (AC6)

**Test 8:** Check that it is not possible to create an instance of the Employee class with an invalid Citizen Card number. 

	@Test(expected = IllegalArgumentException.class)
	public void ensureValidCCNumber() {
		int invalidCCNumber = "3030398578392200";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", "+351916478865", "joanamaria@gmail.com", invalidCCNumber, "JOAna12", "Nurse");
	}

# 5. Construction (Implementation)


## Class CreateTaskController 

		public boolean createTask(String ref, String designation, String informalDesc, 
			String technicalDesc, Integer duration, Double cost, Integer catId)() {
		
			Category cat = this.platform.getCategoryById(catId);
			
			Organization org;
			// ... (omitted)
			
			this.task = org.createTask(ref, designation, informalDesc, technicalDesc, duration, cost, cat);
			
			return (this.task != null);
		}


## Class Organization


		public Task createTask(String ref, String designation, String informalDesc, 
			String technicalDesc, Integer duration, Double cost, Category cat)() {
		
	
			Task task = new Task(ref, designation, informalDesc, technicalDesc, duration, cost, cat);
			if (this.validateTask(task))
				return task;
			return null;
		}



# 6. Integration and Demo 

* A new option on the Employee menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain. 

Is there any way to avoid this to happen?





