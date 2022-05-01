# US 09 - Register a vaccination center to respond to a certain pandemic 

## 1. Requirements Engineering

### 1.1. User Story Description

_"As an administrator, I want to register a vaccination center to respond to a certain pandemic."_

### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	"[...] vaccination centers are facilities specifically created to administer vaccines of a single type as
response to an ongoing disease outbreak (e.g.: Covid-19)."

> "Each vaccination center has a Center Coordinator [...]"

>	"[...] vaccination centers are characterized by a name, an address, a phone number, an e-mail address, a
fax number, a website address, opening and closing hours, slot duration (e.g.: 5 minutes) and the
maximum number of vaccines that can be given per slot (e.g.: 10 vaccines per slot)." 


**From the client clarifications:**

> **Question:** Is there any specification about the name, phone number,email address, address, fax number, website address, opening and closing hours, slot duration and maximum number of vaccines?
>  
> **Answer:** During Sprint B I will not introduce attribute rules/formats other than the ones that I already introduced (in this forum or in the project description). Please study the concepts and define appropriate formats for the attributes.


### 1.3. Acceptance Criteria

* **AC1:** Each center mush have one Center Coordinator.
* **AC2:** All required fields must be filled in.
* **AC3:** When creating a new Vaccination Center, validate that there isn´t already one existing with the same references.

### 1.4. Found out Dependencies


* There is a dependency to "US10 - Register an Employee	" since at least one Center Coordinator must be designated to a Vaccination Center for it to be created.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* an name;
	* an address;
	* email address
	* a phone number;
	* a fax number;
	* website address;
	* opening hours;
	* closing hours;
	* slot duration;
	* maximum vaccines per slot. 
* Selected data:
    * center coordinator;
  
**Output Data:**
* List of existing center coordinators;
* (In)Success of the operation.

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US09_SSD](SSD/US09_SSD.svg)

### 1.7 Other Relevant Remarks

* There are similarities between this user story and the US11 regarding the method getEmployeesWithRole() which is used to get center coordinators.

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US09_MD](DM/US09_DM.svg)

### 2.2. Other Remarks

* Not found.

## 3. Design - User Story Realization 

### 3.1. Rationale

#### From the alternative 1

| Interaction ID                                                                                                                                      | Question: Which class is responsible for...     | Answer                 | Justification (with patterns)                              |
| :-------------------------------------------------------------------------------------------------------------------------------------------------- | :---------------------------------------------- | :--------------------- | :--------------------------------------------------------- |
| Step 1: starts creating a new vaccination center                                                                                                    | ... instantiating a new Vaccination center?     | VaccinationCenterStore | Creator: R1/2                                              |
| Step 2: request data(name,address,emailAddress,phoneNumber,faxNumber,websiteAddress,openingHours,closingHours,slotDuration, maximumVaccinesPerSlot) | n/a                                             | n/a                    | n/a                                                        |
| Step 3: types requested data                                                                                                                        | ... saving the inputted data?                   | VaccinationCenter      | IE: object created in step 1 has its own data.             |
| Step 4: shows all center coordinators and asks to select one                                                                                        | ... listing all the center coordinators?        | EmployeeStore          | IE: knows all the existing center coordinators.            |
| Step 5: selects a center coordinator                                                                                                                | n/a                                             | n/a                    | n/a                                                        |
| Step 8: shows all the data and requests a confirmation                                                                                              | ... validating the data introduced?             | VaccinationCenterStore | IE: holds every information about centers.                 |
|                                                                                                                                                     | ... check for duplicates?                       | VaccinationCenterStore | IE: knows its own data.                                    |
| Step 9: confirms the data                                                                                                                           | ... saving the new created Vaccination Center?  | VaccinationCenterStore | IE: holds every information about the vaccination centers. |
| Step 10: informs operation success                                                                                                                  | ... informing that the operation was a success? | UI                     | IE: responsible for user interaction.                      |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * VaccinationCenter
 * VaccinationCenterStore
 * EmployeeStore

Other software classes (i.e. Pure Fabrication) identified: 

 * CreateVaccinationCenterUI  
 * CreateVaccinationCenterController


## 3.2. Sequence Diagram (SD)

### From alternative 1

![US09_SD](SD/US09_SD.svg)

## 3.3. Class Diagram (CD)

![US09_CD](CD/US09_CD.svg)

# 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the VaccinationCenter class with null values. 

	@Test(expected = IllegalArgumentException.class)
        public void ensureNullIsNotAllowed() {
                VaccinationCenter center = new VaccinationCenter(null, null, null, null, null, null, null, null, 0,0, null);
        }
	
**Test 2:** Check that it is not possible to create an instance of the VaccinationCenter class with empty values.

	@Test(expected = IllegalArgumentException.class)
        public void ensureEmptyIsNotAllowed() {

                Employee coordinator = new Employee("", "", "", "", "", "");

                VaccinationCenter center = new VaccinationCenter("", "", "", "", "", "", "", "", 0, 0, coordinator);
        }

**Test 3:** Check that it is not possible to create a Vaccination Center with the email address invalid.
	
	@Test(expected = IllegalArgumentException.class)
        public void ensureEmailIsValid() {
                Employee coordinator = new Employee"Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("Centro", "Rua João Almeida", "vacinacaoportoAgmail.com", "91919191", "+3511234567", "www.centrovacinaoporto.com", "8:00", "19:00", 5, 10, coordinator);
        }

**Test 4:** Check that it is not possible to create a Vaccination Center with the phone number invalid.

		@Test(expected = IllegalArgumentException.class)
        public void ensurePhoneNumberIsValid() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("Centro", "Rua João Almeida", "vacinacaoporto@gmail.com", "91919191", "+3511234567", "www.centrovacinaoporto.com", "8:00", "19:00", 5, 10, coordinator);
        }

**Test 5:** Check that it is not possible to create a Vaccination Center with the slot duration invalid.

		@Test(expected = IllegalArgumentException.class)
        public void ensureSlotDurationIsValid() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com", "+351921010101", "+35111221222222", "www.centrovacinaoporto.com", "8:00", "19:00", "fff", 10, coordinator);
        }
		
**Test 6:** Check that it is not possible to create a Vaccination Center with the maximum vaccines per slot invalid.

		@Test(expected = IllegalArgumentException.class)
        public void ensureMaxVacPerSlotIsValid() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com", "+351921010101", "+35111221222222", "www.centrovacinaoporto.com", "8:00", "19:00", 10, "FF", coordinator);
        }


# 5. Construction (Implementation)


## Class VaccinationCenterController 

		public void createVaccinationCenter(String name, String address, String emailAddress, String phoneNum, String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration, int maxVacSlot, Employee coordinator) {

    			this.center = vacStore.createVaccinationCenter(name, address, emailAddress, phoneNum, faxNum, webAddress, openingHours, closingHours, slotDuration, maxVacSlot, coordinator);

    			vacStore.validateVaccinationCenter(center);
 		 }


## Class VaccinationCenterStore


		public VaccinationCenter createVaccinationCenter(String name, String address, String emailAddress, String phoneNum, String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration, int maxVacSlot,
      													Employee coordinator) {

    		// TO DO
    		try {
    		} catch (Exception e) {
    		}
    		VaccinationCenter center = new VaccinationCenter(name, address, emailAddress, phoneNum, faxNum, webAddress, openingHours, closingHours, slotDuration, maxVacSlot, coordinator);
    		return center;
 		 }



# 6. Integration and Demo 

* (Information not available yet.)

# 7. Observations

 There is class relation between Employee and VaccinationCenter because it needs to exist at least one coordinator in the first place in the code.
