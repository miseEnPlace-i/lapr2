# US 14 - Load users from a file

## 1. Requirements Engineering


### 1.1. User Story Description


As an administrator, I want to load a set of users from a CSV file.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> A SNS User is a person who is registered in the system.

> A SNS User must have an email, a password, a name, a birthday as well as a SNS number.

> Any Administrator uses the application to register SNS users.

**From the client clarifications:**

> **Question:** What would be the sequence of parameters to be read on the CSV?
>  
> **Answer:** Name, Sex, Birth Date, Address, Phone Number, E-mail, SNS User Number and Citizen Card Number.

### 1.3. Acceptance Criteria


* **AC1:** The application must support importing two types of CSV
files: a) one type must have a header, column separation is done using “;”
character; b) the other type does not have a header, column separation is done
using “,” character. 
* **AC2:** The file path name field must filled in.
* **AC3:** The file path name must correspond to an existing and valid CSV file.


### 1.4. Found out Dependencies


* No dependencies were found.

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* File path name

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![SSD_US14](SSD/US14_SSD.svg)

**Other alternatives might exist.**

### 1.7 Other Relevant Remarks

n/a

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US14_DM](DM/US14_MD.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1  		 |	... interacting with the actor? | UploadUserFromFileUI   |  Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.           |
| 			  		 |	... coordinating the US? | RegisterSNSUserController | Controller                             |
| Step 2  		 |							 |             |                              |
| Step 3  		 |	...saving the inputted data? | SVGReader  | IE: owns the data.  |
|  		 |	...validate SVG path? | SVGReader  | IE: owns the data.  |
|  		 |	...validate SVG file and check is type? | SVGReader  | IE: is responsible for the SVG.  |
|  		 |	...instantiating SNSUser? | SNSUserStore  | Creator: SNSUserStore has a SNSUser.  |
|  		 |	...validating SNSUser data?(locally) | SNSUser  | IE: owns the data.  |
|  		 |	...validating SNSUser data?(globally) | SNSUserStore  | IE: knows every SNSUsers registered.  |
|  		 |	...saving created SNSUser | SNSUserStore  | IE: owns every SNSUsers.  |
| Step 4  		 |	... informing operation success?| UploadUserFromFileUI  | IE: is responsible for user interactions.  | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * SNSUserStore
 * SNSUser
 * SVGReader

Other software classes (i.e. Pure Fabrication) identified: 

 * UploadUserFromFileUI	  
 * RegisterSNSUserController


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





