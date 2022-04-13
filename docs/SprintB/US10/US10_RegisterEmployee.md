# US 10 - Register an Employee 

## 1. Requirements Engineering


### 1.1. User Story Description


_"As an administrator, I want to register an Employee."_



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	"[...] employees enrolled in the vaccination process are almost the same in each kind of vaccination center". 


>	"[...] receptionists and nurses registered in the application will work in the vaccination process. As the allocation of receptionists and nurses to vaccination centers might be complex, by now, the system might assume that receptionists and nurses can work on any vaccination center."

> 	"Any Administrator uses the application to register [...] center coordinators, receptionists, and nurses enrolled in the vaccination process."



**From the client clarifications:**

> **Question:** Which information should the administrator know about the employees, in order to register them in the system?
>  
> **Answer:** _Still waiting for clarification._



### 1.3. Acceptance Criteria


* **AC1:** All required fiels must be filled in. (?)
* **AC2:** The password created must contain seven alphanumeric characters, including three capital letters and two digits.
* **AC3:** Each user mmust have a single role defined in the system.


### 1.4. Found out Dependencies


* There is a dependency to "USXX Create an user role" since at least an user role must exist to classify the employee being created.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* name
	* email
	* password
	* ...
	
* Selected data:
	* user role 


**Output Data:**

* List of existing user roles
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US10_SSD](ssd/US10_SSD.svg)

**Other alternatives might exist.**

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

| Interaction ID                                       | Question: Which class is responsible for... | Answer   | Justification (with patterns)                 |
| :--------------------------------------------------- | :------------------------------------------ | :------- | :-------------------------------------------- |
| Step 1: asks to register a new empolyee              | ...instantiating a new empolyee             | Company  | (?)                                           |
| Step 2: requests data (name, email, password, etc)   | n/a                                         | n/a      | n/a                                           |
| Step 3: types requested data                         | ...saving the input data?                   | Employee | IE: object created in step 1 has its own data |
| Step 4: shows user roles list and asks to select one |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Organization
 * Platform
 * Task

Other software classes (i.e. Pure Fabrication) identified: 

 * CreateTaskUI  
 * CreateTaskController


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





