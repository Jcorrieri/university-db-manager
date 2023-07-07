# DB_PROJ_G2

This is a class project utilizing the JDBC API and an Oracle database.

## ERR Diagram

![ERR Diagram](misc/err-diagram.jpg?raw=true "ERR Diagram")

## Relational Schema

![Relational Schema](misc/relational-schema.jpg?raw=true "Relational Schema")

## Contributions

### Jacomo Corrieri:
* Implemented the GUI components and linked them to the Main class
* Implemented the Logger class
* Implemented the main method (connecting to the database using JDBC and instantiating the GUI)
* Implemented the following methods in the Main class:
  * queryGrade(), queryStudent(), queryCourses(), querySections(), getQualityHours(), addPerson(), addStudentToDatabase(), and addCourse().
* Created and added the following tables to the database:
  * PERSON, DEPARTMENT, and MAJORS_IN
* Contributed to the relational schema as well as the ERR diagram
* Contributed to the conceptual layout of the GUI

### Zeke Geiger:
* Implemented the following methods in the Main class:
  * addInstructor(), addSection(), addStudentToSection(), and addGrade()
* Created and added the following tables to the database:
  *  STUDENT, SECTION, and ASSIGNED_TO
* Contributed to the relational schema as well as the ERR diagram
* Contributed to the conceptual layout of the GUI

### Benjamin Cruz: 
* Implemented the following methods in the Main class:
  * addDepartment()
* Created and added the following tables to the database:
  *  COURSE, INSTRUCTOR, and MINORS_IN
* Contributed to the relational schema as well as the ERR diagram
* Contributed to the conceptual layout of the GUI

## SQL Source Code

```sql
CREATE TABLE PERSON 
	(	Nnumber CHAR(9) NOT NULL,
		Ssn CHAR(9) NOT NULL,
		Phone CHAR(10),
		F_name VARCHAR(50),
		M_init CHAR(1),
		L_name VARCHAR(50),
		Address VARCHAR(75),
		CONSTRAINT PPK
			PRIMARY KEY (Nnumber),
		CONSTAINT PSK
			UNIQUE (Ssn) );

CREATE TABLE DEPARTMENT
    (   Dept_code VARCHAR(4) NOT NULL,
        Name VARCHAR(50) NOT NULL,
        Office_phone CHAR(10),
        Office_num INT,
        College VARCHAR(50),
        CONSTRAINT DEPTPK
            PRIMARY KEY (Dept_code),
        CONSTRAINT DEPTSK
            UNIQUE (Name) );
			
CREATE TABLE INSTRUCTOR
	(	Assoc_depart VARCHAR(4)NOT NULL,
		N_number CHAR(9)NOT NULL,
		Age INTEGER,
		Office_num VARCHAR(4),
		CONSTRAINT INSTRUCTORPK
			PRIMARY KEY (N_number),
		CONSTRAINT INSTRUCTORPERFK
			FOREIGN KEY (N_number) REFERENCES PERSON(Nnumber),
		CONSTRAINT INSTRUCTORDEPFK
			FOREIGN KEY (Assoc_depart) REFERENCES DEPARTMENT(Dept_code) );

CREATE TABLE STUDENT 
	(	Nnumber CHAR(9) NOT NULL,
		Birthdate DATE,
		Sex CHAR(1),
		Perm_phone CHAR(10),
		Perm_state CHAR(2),
		Perm_city VARCHAR(20),
		Perm_zip INT,
		Class_level VARCHAR(10),
		Degree_program VARCHAR(10),
		CONSTRAINT STDPK
			PRIMARY KEY (Nnumber),
		CONSTRAINT STDFK
			FOREIGN KEY (Nnumber) REFERENCES PERSON(Nnumber) );

CREATE TABLE COURSE
    (   Course_name CHAR(50) NOT NULL,
        Course_Desc CHAR(200),
        Course_num VARCHAR(7) NOT NULL,
        Sem_hours INT NOT NULL CHECK(Sem_hours > 0 AND Sem_hours < 7),
        Course_lvl INT NOT NULL,
        Offering_dept VARCHAR(4) NOT NULL,
        CONSTRAINT COURSEPK
            PRIMARY KEY (Course_num),
        CONSTRAINT COURSEFK
            FOREIGN KEY (Offering_dept) REFERENCES DEPARTMENT(Dept_code) );

CREATE TABLE SECTION
	(	Section_num INT NOT NULL,
		Semester VARCHAR(10),
		Year INT,
		Course_num VARCHAR(7) NOT NULL,
		Instructor CHAR(9),
		CONSTRAINT SECPK
			PRIMARY KEY (Section_num, Semester, Year, Course_num),
		CONSTRAINT SECCRFK
			FOREIGN KEY (Course_num) REFERENCES COURSE(Course_num),
		CONSTRAINT SECINSTFK
			FOREIGN KEY (Instructor) REFERENCES PERSON(N_number) );

CREATE TABLE MAJORS_IN
    (   Nnumber CHAR(9) NOT NULL,
        Dept_code VARCHAR(4) NOT NULL,
        CONSTRAINT MAJORSPK
            PRIMARY KEY (Nnumber, Dept_code),
        CONSTRAINT MJRPERSFK
            FOREIGN KEY (Nnumber) REFERENCES PERSON(Nnumber),
        CONSTRAINT MJRDEPTFK
            FOREIGN KEY (Dept_code) REFERENCES DEPARTMENT(Dept_code) );
			
CREATE TABLE MINORS_IN
	(	Nnumber CHAR(9) NOT NULL,
		Dept_code VARCHAR(4) NOT NULL,
		CONSTRAINT MINORS_INNPK
            PRIMARY KEY (Nnumber, Dept_code),
        CONSTRAINT MINORS_INNNUMFK
            FOREIGN KEY (Nnumber) REFERENCES PERSON(Nnumber),
        CONSTRAINT MINORS_INDEPFK
            FOREIGN KEY (Dept_code) REFERENCES DEPARTMENT(Dept_code) );
			
CREATE TABLE ASSIGNED_TO
	(	Nnumber CHAR(9),
		Course_num VARCHAR(7),
		Section_num INT,
		Semester VARCHAR(10),
		Year INT,
		Grade VARCHAR(2),
		CONSTRAINT ATPK
			PRIMARY KEY(Section_num,Semester,Year,Course_num,Nnumber),
		CONSTRAINT ATNNFK
			FOREIGN KEY(Nnumber) REFERENCES STUDENT(Nnumber),
		CONSTRAINT ATSNFK
			FOREIGN KEY(Section_num, Semester, Year, Course_num) REFERENCES SECTION(Section_num, Semester, Year, Course_num) );
```
