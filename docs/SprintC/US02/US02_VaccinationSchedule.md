# US 02 - Schedule a vaccination

## 1. Requirements Engineering

### 1.1. User Story Description

_"As a receptionist at one vaccination center, I want to schedule a vaccination."_

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> "Some users (e.g.: older ones) may want to go to a healthcare center to schedule the vaccine appointment with the help of a receptionists at one vaccination center."

> " (...) his/her SNS user number, select the vaccination center, the date, and the time (s)he wants to be vaccinated as well as the type of vaccine to be administered (...)"

**From the client clarifications:**

> **Question:** "How does the system know which vaccine to suggest when the SNS user is scheduling their vaccine? Is the administrator responsible for setting the outbreak vaccine?"
>  
> **Answer:** "The ongoing outbreak should be defined in the system using a configuration file."

> **Question:** "A receptionist has the ability to schedule an appointment in different vaccination centres or only on their own?"
>
> **Answer:** _**Not answered yet**_.


### 1.3. Acceptance Criteria


* **AC1:** All required fields must be filled in.
* **AC2:** The algorithm should check if the SNS User is within the age and time since the last vaccine.

### 1.4. Found out Dependencies

* --

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* SNS number, 
	* date, 
	* time.
* Selected data:
	* Vaccination Center,
	* Type of vaccine.

**Output Data:**

* List of vaccines,
* List of vaccination centers,
* (In)Success of the operation.

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US02_SSD](SSD/US02_SSD.svg)

**Other alternatives might exist.**

### 1.7 Other Relevant Remarks

* --

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US02_MD](DM/US02_DM.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...            | Answer               | Justification (with patterns)                                                                                 |
| :------------- | :----------------------------------------------------- | :------------------- | :------------------------------------------------------------------------------------------------------------ |
| Step 1         | ... interacting with the actor?                        | CreateTaskUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ... coordinating the US?                               | CreateTaskController | Controller                                                                                                    |
|                | ... instantiating a new Task?                          | Organization         | Creator (Rule 1): in the DM Organization has a Task.                                                          |
|                | ... knowing the user using the system?                 | UserSession          | IE: cf. A&A component documentation.                                                                          |
|                | ... knowing to which organization the user belongs to? | Platform             | IE: has registed all Organizations                                                                            |
|                |                                                        | Organization         | IE: knows/has its own Employees                                                                               |
|                |                                                        | Employee             | IE: knows its own data (e.g. email)                                                                           |
| Step 2         |                                                        |                      |                                                                                                               |
| Step 3         | ...saving the inputted data?                           | Task                 | IE: object created in step 1 has its own data.                                                                |
| Step 4         | ...knowing the task categories to show?                | Platform             | IE: Task Categories are defined by the Platform.                                                              |
| Step 5         | ... saving the selected category?                      | Task                 | IE: object created in step 1 is classified in one Category.                                                   |
| Step 6         |                                                        |                      |                                                                                                               |
| Step 7         | ... validating all data (local validation)?            | Task                 | IE: owns its data.                                                                                            |
|                | ... validating all data (global validation)?           | Organization         | IE: knows all its tasks.                                                                                      |
|                | ... saving the created task?                           | Organization         | IE: owns all its tasks.                                                                                       |
| Step 8         | ... informing operation success?                       | CreateTaskUI         | IE: is responsible for user interactions.                                                                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Organization
 * Platform
 * Task

Other software classes (i.e. Pure Fabrication) identified: 

 * ScheduleVaccinationUI  
 * ScheduleVaccinationController


## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US02_SD](SD/US02_SD.svg)


## 3.3. Class Diagram (CD)

**From alternative 1**

![US02_CD](CD/US02_CD.svg)

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





