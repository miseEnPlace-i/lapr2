# US 06 - Record Daily Vaccinated

## 1. Requirements Engineering

### 1.1. User Story Description

DGS wants to record daily the total number of people vaccinated in each vaccinated center.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> No Information regarding this US was found on the specifications document.

**From the client clarifications:**

> **Question:** In the acceptance criteria, "the algorithm should run automatically at a time defined in a configuration file and should register a date, the name of the vaccination center and the total number of vaccinated users." How it is supposed to register this information? Should it be recorded in a file (ex: txt,..) or recorded in the system (ex: in a store) ?
>  
> **Answer:** The data should be written to a CSV file (field delimiter should be a semicolon). 
>
> **Question:** Does the report contain the count of vaccinations of the current day (which depending on the time of day can be incomplete) or the day before?
> 
> **Answer:** Should record vaccinations of the current day.
>
> **Question:** I'd like to clarify something, should we implement some sort of message for when the file is saved or warning if there were any errors saving the file using JavaFX or use JavaFX in any other away in US06?
> 
> **Answer:** No. The user story runs automatically without user interaction.
>
> **Question:** Also, is it supposed to have the possibility to change the information on the configuration file? If so, who can do it?
> 
> **Answer:** Yes. Please discuss this question with ESOFT teachers.
> 
> **Question:** "In the acceptance criteria, "the algorithm should run automatically at a time defined in a configuration file and should register a date, the name of the vaccination center and the total number of vaccinated users." How it is supposed to register this information? Should it be recorded in a file (ex: txt,..) or recorded in the system (ex: in a store) ?"
> 
> **Answer:** The data should be written to a CSV file (field delimiter should be a semicolon).


### 1.3. Acceptance Criteria

* **AC1** The algorithm should run automatically at a time defined in a configuration file and should register the date, the name of the vaccination center and the total number of vaccinated users.
  
### 1.4. Found out Dependencies


* There is a dependency to "US01 - Schedule a Vaccine", as users need to schedule a vaccine in order to get it administered.
* There is a dependency to "US03 - Register SNS User" since at leat one sns user needs to be registered in the system.
* There is a dependency to "US04 - Register the Arrival of an SNS User", as receptionists need to register arrivals so that SNS Users can get vaccines administered.
* There is a dependency to "US08 - Record Administration of Vaccine" since at least one vaccine needs to be administered to a sns user.
* There is a dependency to "US09 - Register a Vaccination Center" since at least one vaccination center needs to be registered in the system.
* There is a dependency to "US12 - Register a new Vaccine Type" since at least one vaccine type needs to be registered in the system.
* There is a dependency to "US13 - Register a new Vaccine" since at least one vaccine needs to be registered in the system.

  
### 1.5 Input and Output Data

**Output Data:**

- A CSV file is generated

### 1.6. System Sequence Diagram (SSD)


![US06-SSD](./SSD/US06_SSD.svg)

### 1.7 Other Relevant Remarks



## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US06-MD](./DM/US06_DM.svg)

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for... | Answer | Justification (with patterns) |
| :------------- | :------------------------------------------ | :----- | :---------------------------- |
| Step 1 | ... instantiating ExportDailyVaccinatedTask? | Company | Creator |
|  | ... instantiating Timer? | Company | Creator |
|  | ... scheduling task operation? | Company | IE: knows necessary data for scheduling |
|  | ... call task at time specified? | Timer | Pure Fabrication |
|  | ... export daily vaccinated? | ExportDailyVaccinatedTask | IE: knows data necessary to do it |
|  | ... get list of vaccination centers? | VaccinationCenterStore | IE/HC/LC: knows all vaccine centers |
|  | ... get list of vaccine types? | VaccineTypeStore | IE/HC/LC: knows all vaccine types |
|  | ... get list of today's vaccine administrations? | VaccinationCenter | IE: knows vaccine administration from center |
|  | ... get the vaccine administered? | VaccineAdministration | IE: knows which vaccine was administered |
|  | ...  get the vaccine type of the vaccine administered? | Vaccine | IE: has a vaccine type |
|  | ...  get the description of the vaccine type? | VaccineType | IE: has a description |
|  | ...  get the vaccine name? | Vaccine | IE: has a name |
|  | ... create and write data to file? | FileUtils | Pure Fabrication |




### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

- Company
- VaccinationCenterStore
- VaccineTypeStore
- VaccinationCenter
- VaccineType
- Vaccine
- VaccineAdministration
- ExportDailyVaccinatedTask
- FileUtils

Other software classes (i.e. Pure Fabrication) identified:
- Timer

## 3.2. Sequence Diagram (SD)

![US06-SD](./SD/US06_SD.svg)

## 3.3. Class Diagram (CD)

![US06-CD](./CD/US06_CD.svg)

# 4. Tests


**VaccinationCenter Tests**

**Test 1:** Check that if there are not any vaccine administration registered getVacAdminFromTodayList returns an empty list

    @Test
    public void ensureEmptyListWorks() {
        VaccinationCenter center = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
            "+351912345678", "+351223456799", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");
        List<VaccineAdministration> emptyList = new ArrayList<>();

        assertEquals(emptyList, center.getVacAdminFromTodayList());
    }

**ExportDailyVaccinated Tests**

**Test 2:** Check that if there are not any vaccine administration exportation works

    @Test
    public void ensureTaskExportWorkWithNoVacAdmin() throws IOException {
        String expected = "Center;" + vacType1.getDescription() + ";" + vacType2.getDescription() + "\n" + center1.getName() + ";0;0\n" + center2.getName() + ";0;0\n";
        ExportDailyVaccinatedTask task = new ExportDailyVaccinatedTask("out\\test", ";".charAt(0), vcStore, vtStore);
        task.run();

        
        Calendar today = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Path expectedFilepath = Path.of("out\\test" + format.format(today.getTime()) + ".csv");

        System.out.println(expected);
        System.out.println(Files.readString(expectedFilepath));

        assertEquals(expected, Files.readString(expectedFilepath));
    }


# 5. Construction (Implementation)

**ExportDailyVaccinatedTask**

    @Override
    public void run() {
        HashMap<VaccinationCenter, HashMap> dataMap = new HashMap<VaccinationCenter, HashMap>();

        List<VaccinationCenter> centerLst = vacCenterSt.getListOfVaccinationCenters();
        List<VaccineType> vacTypeLst = vacTypeSt.getListOfVaccineTypes(); 
        
        for (int i = 0; i < centerLst.size(); i++) {
            List<VaccineAdministration> vacAdminList = centerLst.get(i).getVacAdminFromTodayList();

            HashMap<VaccineType, Integer> centerDataMap = new HashMap<VaccineType, Integer>();
            
            for (int j = 0; j < vacAdminList.size(); j++) {
                Vaccine vaccine = vacAdminList.get(j).getVaccine();
                
                VaccineType vacType = vaccine.getVacType();

                centerDataMap.merge(vacType, 1, Integer::sum);                             
            }
            dataMap.put(centerLst.get(i), centerDataMap);
        }

        String content = convertToString(centerLst, vacTypeLst, dataMap);
        Calendar today = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        String filepath = this.filePath + format.format(today.getTime()) + ".csv";
        FileUtils.writeToFile(filepath, content);
    }

**Company**

    public void scheduleDailyVaccinated(String filePath, String time, String separator, int timeInterval) {
   
        String[] scheduleTime = time.split(":");
        Calendar firstTime = Calendar.getInstance();

        firstTime.set(Calendar.SECOND, 0);
        firstTime.set(Calendar.MINUTE, Integer.valueOf(scheduleTime[1]));
        firstTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(scheduleTime[0]));

        if(firstTime.before(Calendar.getInstance())) firstTime.add(Calendar.SECOND, timeInterval);

        ExportDailyVaccinatedTask task = new ExportDailyVaccinatedTask(filePath, separator.charAt(0), this.vaccinationCenterStore, this.vaccineTypeStore);
        
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(task, firstTime.getTime(), timeInterval);
    }

# 6. Integration and Demo

Some properties were added to the config properties.

# 7. Observations

The properties defined in the Sequence Diagram can be changed by the administrator.
