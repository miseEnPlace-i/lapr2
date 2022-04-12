# US 12 - Specify a new Vaccine Type

## 1. Requirements Engineering

### 1.1. User Story Description

_"As an Administrator, I intend to specify a new vaccine type."_

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> “[…] the community mass vaccination centers are facilities specifically created to administer vaccines of a single type […]”

> “[…] healthcare center […] can administer any type of vaccines (e.g.: Covid-19, Dengue, Tetanus, smallpox).”

> “[…] for each type of vaccine, it might exist several vaccines […]”

**From the client clarifications:**

> **Question:** ?

> **Answer:** ?

### 1.3. Acceptance Criteria

- **ACX:** ...

### 1.4. Found out Dependencies

- No dependencies where found.

### 1.5 Input and Output Data

**Input Data:**

- Typed data:
  - ...
- Selected data:
  - none

**Output Data:**

- (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US12_SSD](SSD/US12_SSD.svg)

**Other alternatives might exist.**

### 1.7 Other Relevant Remarks

- Not found

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US12_DM](DM/US12_DM.svg)

### 2.2. Other Remarks

Not found.

## 3. Design - User Story Realization

### 3.1. Rationale

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

### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

- Organization
- Platform
- Task

Other software classes (i.e. Pure Fabrication) identified:

- CreateTaskUI
- CreateTaskController

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

_It is also recommended to organize this content by subsections._

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

- A new option on the Employee menu options was added.

- Some demo purposes some tasks are bootstrapped while system starts.

# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain.

Is there any way to avoid this to happen?
