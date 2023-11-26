# SC2002-Group-Project
## CAMs

### Background
CAMs is a system that allows users to login as either Staff or Student. The different user types will have different menus and options available to them.

#### User Classes
  - Staff:
      - Privileged user: Can see everything; but cannot edit or delete suggestions or enquiries.
      - Top dog: Able to approve of suggestions
      - Camp creator: Only user type that can create, modify and delete camps
      - Performance Report master: Only user type that can generate a performance report

  - Student:
      - Base user: Can see and do little; can join and leave camps and submit queries
      - Rank and file: Unable to see suggestions and generate reports
      - Possible promotion: Has a more privileged user called CommitteeMember
      - <b>WARNING: Current implementation overlaps with CommitteMember</b>

  - CommitteMember:
      - More powerful than most: Has elevated permissions, BUT it's restricted to only one camp
      - Report creator: Able to generate general report
      - Score-chaser: Gains points for contributing to camp
      - Duty calls: Able to provide replies to queries and create suggestions
        
#### Controller class templates
  - ```<Task>Controller```:
      - Contains references to ```<Task>List```
      - Responsible for minimal filtering
   
  - ```<Task>List```:
      - Contains references to a list of ```<Task>```
      - Responsible for retrieving data from the list

  - ```<Task>```:
      - Contains attributes that are needed for operations
      - Has getter and setter methods
      - May reference other classes that help support its operations


#### Helper functions
 -  Printers:
   - ```CampDetailsPrinter```
       - Responsible for printing camp details 
   - ```OptionsPrinter```
       - Responsible for most if not all of the options being listed
       - Alternative prints would be inside controller classes

- Process Managers:
  - ```DataController```
      - Uses an instance of the ```DataManager``` class
      - Responsible for reading and writing data from file
   
  - ```DataManager```
      - Contains the <u>serializer</u> and <u>deserializer</u> logic
      - Responsible for initialising Student and Staff classes
  

  
#### Database files
 - StaffData.csv
   - Primary data source for Staff
    
 - StudentData.csv
   - Primary data source for Students
  
 - StaffBackup.csv
   - Backup data source for Staff
     
 - StudentBackup.csv
   - Backup data source for Student

