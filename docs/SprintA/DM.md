# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes

To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development".

### _Conceptual Class Category List_

**Business Transactions**

- ***

  **Transaction Line Items**

- Vaccination Type

  **Product/Service related to a Transaction or Transaction Line Item**

- Vaccine

  **Transaction Records**

- Vaccine administration; Vaccination scheduling; Register Vaccine; Register Nurse; Register Receptionist; Certificate Issuance;

  **Roles of People or Organizations**

- Nurse; Coordinator; Receptionist; Administrator; DGS Administrator; SNS User;

  **Places**

- Community Mass Vaccination Center; Health Care Center; Waiting room;

  **Noteworthy Events**

- Register Arrival; Schedule Confirmation; Vaccination Scheduling; Vaccination Administration; User waiting in recovery room; Leave vaccination center; Certificate Issuance; Check User's medical history

  **Physical Objects**

- Vaccine

  **Descriptions of Things**

- Vaccination Type

  **Catalogs**

- ***

  **Containers**

- ***

  **Elements of Containers**

- ***

  **Organizations**

- DGS; ARS; AGES

  **Other External/Collaborating Systems**

- DGS; SMS

  **Records of finance, work, contracts, legal matters**

- Statistics; Charts; Report; Vaccination Certificate; EU Covid Certificate; SMS Authorization

  **Financial Instruments**

- ***

  **Documents mentioned/used to perform some work**

- User's Medical History

##**Rationale to identify associations between conceptual classes**###

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations:

- **_A_** is physically or logically part of **_B_**
- **_A_** is physically or logically contained in/on **_B_**
- **_A_** is a description for **_B_**
- **_A_** known/logged/recorded/reported/captured in **_B_**
- **_A_** uses or manages or owns **_B_**
- **_A_** is related with a transaction (item) of **_B_**
- etc.

| Concept (A) | Association | Concept (B) |
| ----------- | :---------: | ----------: |
| C1          |    verb1    |          C2 |
| ...         |     ...     |         ... |

## Domain Model

**Do NOT forget to identify concepts atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![DM.svg](DM.svg)
