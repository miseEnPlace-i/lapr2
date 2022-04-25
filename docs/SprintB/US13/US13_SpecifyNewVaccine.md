# US 12 - Specify a new vaccine and its administration process

## 1. Requirements Engineering


### 1.1. User Story Description


_"As an Administrator, I intend to specify a new vaccine and its administation process."_



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	An Administrator is responsible for properly configuring and managing the core information (e.g.:
type of vaccines, vaccines, vaccination centers, employees) required for this application [...]

>   [...] several vaccines might exist, each one demanding a distinct administration process.

>   The vaccine administration process comprises (i) one or more age groups (e.g.: 5 to 12 years old, 13 to 18 years
old, greater than 18 years old), and (ii) per age group, the doses to be administered (e.g.: 1, 2, 3), the
vaccine dosage (e.g.: 30 ml), and the time interval regarding the previously administered dose.

>    [...]  between doses (e.g.: between the 1st and 2nd doses) the
dosage to be administered might vary as well as the time interval elapsing between two consecutive
doses (e.g.: between the 1st and 2nd doses 21 days might be required, while between the 2nd and the
3rd doses 6 months might be required).

>   [...] for each type of vaccine, several vaccines might exist,


**From the client clarifications:**

TODO

> **Question:** 
>   As to the interval between doses, what time format are we to use?
> **Answer:** 
	Number of days.

-

> **Question:** 
>  
> **Answer:** 


### 1.3. Acceptance Criteria

* **AC1:** All required fiedls must be filled in.
* **AC2:** The age group intervals limits, the dosage, the time between doses and the number of doses must be positive.
* **AC3:** When creating a vaccine with an already existing designation, the system must reject such operation and the user must have the chance to modify the typed designation.


### 1.4. Found out Dependencies


* There is a dependency to "US12 - Specify a new Vaccine Type" since at least a vaccine type must exist to classify new vaccine being created.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
  - Vaccine designation
  - Vaccine Code
  - Age group interval limits
  - Dosage
  - Number of doses
  - Time interval between doses
* Selected data:
  - Vaccine type


**Output Data:**

- (In)Success of the operation


### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US13_SSD](SSD/US13_SSD.svg)


**Other alternatives might exist.**

### 1.7 Other Relevant Remarks

* Not found

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US13_MD](DM/US13_DM.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1 		 |	... displaying the screen to the user? | CreateVaccineUI   |  Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.           |
|	| ... coordinating the US	| CreateVaccineController	| Pure Fabrication: Controller 	|
| Step 2 		 |	... request data?(designation, number of different age groups) | CreateVaccineUI | IE: Responsible for user interactions.                            |
| Step 3  		 |	... instantiating a new vaccine? | Company   | IE: Knows all vaccines.   |
| 			  	 |  ... validating data?(locally)  | Vaccine  | IE: Owns the data.  |
| 			  	 |  ... validating data?(globally)  | Companny  | IE: Knows all data (e.g. make sure it doesnt have the same name as other vaccine).  |
| Step 4		 |	... list all vaccine types?	| Company	| IE: Knows all vaccine types. |
| Step 5		 |  ... save the selected vaccine type?	| Vaccine | IE: Object created in step 3 is classified in one vaccine type. |
| Step 6		 |  ... inform the  current state of the process | CreateVaccineUI | Pure Fabrication: Responsible for user interactions. |
| step 7 ||||
| Step 8 		 |	... request data?(min age, max age, number of dosage) | CreateVaccineUI | Pure Fabrication: Responsible for user interactions.                |
| Step 9		 |	... instantiating a new adminsitration process	| Company	| Creator: Knows all administration process. |
|				 |	... validating data? |	AdminsitrationProcess	| IE: Owns the data. |
| Step 10		 |  ... inform the  current state of the process | CreateVaccineUI | Pure Fabrication: Same as step 6.	|
| step 11 ||||
| Step 12 		 |	... request data?(dosage, time to next dosage) | CreateVaccineUI | Pure Fabrication: Responsible for user interactions.                  |
| Step 13		 |	... instantiating a new dose information	| Company	| Creator: Knows all doses information. |
|				 |	... validating data? |	DoseInformation	| IE: Owns the data. |
| Step 14		 |  ... inform the  current state of the process | CreateVaccineUI | Pure Fabrication: Same as step 6.	|
| Step 15		 |  ... show all the data and request confirmation?	|	CreateVaccineUI	|  Pure Fabrication: Responsible for user interactions.	|
| Step 16 ||||
| Step 17		 |  ... informing operation sucess?	|	CreateVaccineUI	|  Pure Fabrication: Responsible for user interactions.	|




### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Company
 * Vaccine
 * VaccineType
 * AdministrationProcess
 * DoseInformation

Other software classes (i.e. Pure Fabrication) identified: 

 * CreateVaccineUI
 * CreateVaccineController  



## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US006_SD](US006_SD.svg)

**Alternative 2**

![US006_SD](US006_SD_v2.svg)

## 3.3. Class Diagram (CD)

**From alternative 1**

![US006_CD](US006_CD.svg)

# 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Task class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Task instance = new Task(null, null, null, null, null, null, null);
	}
	

**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureReferenceMeetsAC2() {
		Category cat = new Category(10, "Category 10");
		
		Task instance = new Task("Ab1", "Task Description", "Informal Data", "Technical Data", 3, 3780, cat);
	}


*It is also recommended to organize this content by subsections.* 

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





