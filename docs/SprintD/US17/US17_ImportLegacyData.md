# US 17 - To import data from a legacy system

## 1. Requirements Engineering

### 1.1. User Story Description

_"As a center coordinator, I want to import data from a legacy system that was used in the past to manage centers. The imported data should be presented to the user sorted by arrival time or by the center leaving time. The name of the SNS user and the vaccine type **Short Description** should also be presented to the user."_

### 1.2. Customer Specifications and Clarifications

<!-- TODO -->

**From the specifications document:**

> 

**From the client clarifications:**

> **Question:** 
>
> **Answer:** 

### 1.3. Acceptance Criteria

-  **AC01**: Two sorting algorithms should be implemented (to be chosen manually by the coordinator), and worst-case time complexity of each algorithm should be documented in the application user manual (in the  annexes) that must be delivered with the application. The center coordinator must be able to choose the file that is to be uploaded.

### 1.4. Found out Dependencies

<!-- TODO -->
<!-- ? Identify here any found out dependency to other US and/or requirements. -->

### 1.5 Input and Output Data

**Input Data:**

-   Typed data:
    - file location

-   Selected data:
    - n/a

**Output Data:**

-   imported data sorted by arrival time or by the center leaving time

### 1.6. System Sequence Diagram (SSD)

![US17-SSD](SSD/US17-SSD.svg)

**Alternative 1**

### 1.7 Other Relevant Remarks

<!-- TODO -->
<!-- ? Use this section to capture other relevant information that is related with this US such as (i) special requirements ; (ii) data and/or technology variations; (iii) how often this US is held. -->

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US17-MD](DM/US17-MD.svg)

### 2.2. Other Remarks

<!-- TODO -->
<!-- ? Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams). -->

## 3. Design - User Story Realization

### 3.1. Rationale

<!-- TODO -->

**The rationale grounds on the SSD interactions and the identified input/output data.**

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for... | Answer | Justification (with patterns) |
| :------------- | :------------------------------------------ | :----- | :---------------------------- |
| Step 1         |                                             |        |                               |
| Step 2         |                                             |        |                               |
| Step 3         |                                             |        |                               |
| Step 4         |                                             |        |                               |
| Step 5         |                                             |        |                               |
| Step 6         |                                             |        |                               |
| Step 7         |                                             |        |                               |
| Step 8         |                                             |        |                               |
| Step 9         |                                             |        |                               |
| Step 10        |                                             |        |                               |

### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

<!-- TODO -->
<!-- - Class1
- Class2
- Class3 -->

Other software classes (i.e. Pure Fabrication) identified:

<!-- TODO -->
- xxxxUI
- xxxxController

## 3.2. Sequence Diagram (SD)

![US17-SD](SD/US17-SD.svg)

## 3.3. Class Diagram (CD)

![USXXX-CD](USXXX-CD.svg)

# 4. Tests

<!-- TODO -->
<!-- ? In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling. -->

<!-- * DO NOT COPY ALL DEVELOPED TESTS HERE -->

**Test 1:** Check that it is not possible to create an instance of the Example class with null values.

    @Test(expected = IllegalArgumentException.class)
    	public void ensureNullIsNotAllowed() {
    	Exemplo instance = new Exemplo(null, null);
    }

<!-- It is also recommended to organize this content by subsections. -->

# 5. Construction (Implementation)

<!-- TODO -->
<!-- ? In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits. -->

<!-- It is also recommended to organize this content by subsections. -->

# 6. Integration and Demo

<!-- TODO -->
<!-- ? In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system. -->

# 7. Observations

<!-- TODO -->
<!-- ? In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work. -->

<!--
The receptionist selects the vaccination center she is currently working on at login time.

![EmployeeLogin_SD](SD/EmployeeLogin_SD.svg)
-->
