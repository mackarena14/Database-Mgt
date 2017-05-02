DROP ROLE if exists administrator;
DROP ROLE if exists staff;
DROP ROLE if exists volunteer;
DROP ROLE if exists vet;

create role admin;
grant all on all tables in schema public to admin;
create role staff;
grant all on all tables in schema public to admin;
create role volunteer;
revoke all on all tables in schema public from volunteer;
grant select on all tables in schema public to volunteer;
grant insert on Volunteers, People, Shifts, Walks, Adoptions, Dogs, Cats to volunteer;
grant update on Volunteers, People, Shifts, Walks, Adoptions, Dogs, Cats to volunteer;
create role vet;
revoke all on all tables in schema public from vet;
grant select on Animals, ReasonFor, Shifts, People to vet;
grant update on ReasonFor, Shifts, People to vet;
grant delete on ReasonFor to vet;
grant insert on ReasonFor to vet;

DROP type if exists gender cascade;
CREATE type gender as ENUM ('male', 'female');
DROP type if exists fixed cascade;
CREATE type fixed as ENUM ('yes', 'no');

DROP TABLE if exists People cascade;
DROP TABLE if exists Volunteers cascade;
DROP TABLE if exists Shifts cascade;
DROP TABLE if exists Adopters cascade;
DROP TABLE if exists Staff cascade;
DROP TABLE if exists ZipCode cascade;
DROP TABLE if exists Adoptions cascade;
DROP TABLE if exists Walks cascade;
DROP TABLE if exists CatBreed cascade;
DROP TABLE if exists Cats cascade;
DROP TABLE if exists DogBreed cascade;
DROP TABLE if exists Dogs cascade;
DROP TABLE if exists Birds cascade;
DROP TABLE if exists Other cascade;
DROP TABLE if exists Animals cascade;
DROP TABLE if exists ReasonFor cascade;
DROP TABLE if exists VetVisit cascade;

CREATE TABLE ReasonFor(
    RFID int not null,
    Reason text not null,
    primary key(RFID)
);

CREATE TABLE DogBreed (
	DBID int not null,
	Name text not null,
	primary key (DBID)
);

CREATE TABLE Animals(
AID int not null,
EstimatedAge INT,
Gender gender not null,
Name text not null,
Primary key (AID)
);

CREATE TABLE CatBreed (
	CBID int not null,
	Name text not null,
	primary key (CBID)
);

CREATE TABLE Birds(
AID int not null references Animals(AID),
Breed text not null,
Primary key (AID)
);

CREATE TABLE Cats(
AID int not null references Animals(AID),
CBID int not null references CatBreed(CBID),
Neutered fixed not null,
Primary key(AID)
);

CREATE TABLE Dogs(
	AID  int not null references Animals(AID),
	DBID int not null references DogBreed(DBID),
	Fixed fixed not null,
	primary key (AID)
);

CREATE TABLE Other(
    AID int not null references Animals (AID),
    Neutered fixed not null,
    AnimalType text not null,
    primary key (AID)
);
  
CREATE TABLE VetVisit(
	RFID int not null references ReasonFor(RFID),
    ApptDate date not null,
    AID int not null references Animals(AID),
    primary key (AID,ApptDate,RFID)
);

CREATE TABLE Zipcode(
    Zip int not null,
    City text not null,
    State text not null,
    primary key(Zip)
);

CREATE TABLE People(
    PID int not null,
    Zip int not null references ZipCode(Zip),
    FirstName text not null,
    LastName text not null,
    StreetAddress text not null,
    DOB date not null,
    email text not null,
    phoneNum text not null,
    primary key (PID)
);

CREATE TABLE Staff(
    PID int not null references People(PID),
    Title text not null,
    DateStarted date not null,
    primary key(PID)
);

CREATE TABLE Adopters(
    PID int not null references People(PID),
    primary key(PID)
);

CREATE TABLE Adoptions(
    PID int not null references Adopters(PID),
    AID int not null references Animals(AID),
    Date date not null,
    primary key(PID,AID)
);
                                              
CREATE TABLE Volunteers(
    PID int not null references People(PID),
    DateStarted date not null,
    primary key (PID)
);

CREATE TABLE Shifts(
    SID int not null,
    PID int not null references People(PID),
    TimeIn time not null,
    TimeOut time not null,
    Date date not null,
    primary key(SID)
);

CREATE TABLE Walks(
    PID int not null references Volunteers(PID),
    AID int not null references Dogs(AID),
    Time time not null,
    Date date not null,
    primary key(PID,AID,date,time)
);

-- insert statements
	
INSERT INTO ZipCode(Zip, City, State)
	values
    	(12601, 'Poughkeepsie','NY'),
        (10001, 'New York City', 'NY'),
        (18092, 'Zionsville', 'PA'),
        (10597, 'Katonah','NY'),
        (11798, 'Wheely Heights','NY'),
        (18094, 'Macungie', 'PA');
        
INSERT INTO People(PID, zip, firstname, lastname, streetaddress, dob, email, phonenum)
	values
    -- staff
        (1,'12601','Mackenzie','OBrien','3399 North Road', '11/17/1996','m@gmail.com','6108230580'),
        (2,'10001', 'Tien','LongName','1234 China Lane', '12/23/1995','minion@labouseur.com','1234567890'),
        (3, '18092','Rachel','Choi', '4359 John Fry Ln','6/9/1997', 'rachelchoi@gmail.com','6108495630'),
        (4, '10597', 'Blaise','Spinelli','6591 Cherry St','4/1/1993','blaisesp@yahoo.com','4843925441'),
        (5, '11798','Mark','Rajovic','100 Pennsylvania Ave', '3/3/1994','mark@marist.edu','1234567890'),
		(6, '18094','Katie','Bartolotta','5 First Ave','9/30/1960','katieb@aol.com','5456567890'),
        (7, '11798','Marcus','Zimmermann','1 Second Ave','4/5/1996','maZ@gmail.com','3455434444'),
        (8, '18094','G','Leaden','2 Second Ave','3/3/1993','gLeaden@gmail.com','2223334444'),
        -- volunteers
        (9, '10001','Lauren','Waide','5423 Schoolhouse lane','2/20/1997','lwaide@yahoo.com','9876543210'),
        (10, '12601','Nicole','Deserpa','57489 Apple St','10/28/1997','nikkid@aol.com','6667879999'),
        (11, '10597','Stephanie','Stone','9 Pie lane','12/1/1995','stephstone@gmail.com','5498275521'),
        (12, '10001','Tadd','Bindas','8 Red Fox St','8/4/1996','taddyb@yahoo.com','7894032879'),
       -- adopters
        (13, '12601','Rachel','Danko','543 Marist Road','9/4/1995','rdanko@gmail.com','48489572365'),
        (14, '10597','Raymond','Mattingly','4897 Mccann St','11/4/1993','rmattingly@gmail.com','5659832748'),
        (15, '11798','Molly','Smith','7894 PoTown Lane','7/7/1992','msmith@aol.com','5642894135'),
        (16, '10001','Lidia','Bayus',' 4238','10/19/1990','lbayus@yahoo.com','7859043287'),
        (17, '12601','Carlos','Moreno','Fulton Ave','5/24/1995','carlosm@gmail.com','8543170937'),
        (18, '10001','Sam','Klamar','5438 Murray St','8/5/1994','samK@yahoo.com','5458905341'),
        (19, '12601','Alan','Labouseur','8794 Hancock Road','6/18/1967','alan@labouseur.com','5789043512'),
        (20, '18092','Taylor','OBrien','8592 Hudson St','5/6/1994','tobrien@gmail.com','6105490054');
         
INSERT INTO Staff (PID, Title, DateStarted)
	values
        (1,'founder','10/5/2016'),
        (2,'head of adoption','10/5/2016'),
        (3,'head of volunteers','10/5/2016'),
        (4,'vet','10/5/2016'),
		(5,'secretary','11/5/2016'),
        (6,'dog trainer','11/11/2016'),
        (7,'secretary','12/5/2016'),
        (8,'Social Media Coordiantor','1/10/2017');
        
INSERT INTO Volunteers(PID, DateStarted)
     values
            (9, '10/20/2016'),
            (10,'11/5/2016'),
            (11,'12/20/2016'),
            (12,'11/5/2016');

INSERT INTO Shifts(SID, PID, TimeIn, TimeOut, Date)
	values
            (1,1,'8:00','17:00','10/5/2016'),
            (2,3,'8:00','16:00','11/6/2016'),
            (3,2,'8:00','18:00','10/25/2016'),
            (4,4,'12:00','20:00','10/10/2016'),
            (5,1,'5:00','14:00','11/15/2016'),
            (6,2,'10:00','18:00','10/20/2016'),
            (7,3,'9:00','15:00','10/30/2016'),
            (8,1,'12:00','21:00','10/30/2016'),
            (9,5,'10:00','14:00','11/6/2016'),
            (10,6,'9:00','15:00','11/15/2016'),
            (11,5,'11:00','16:00','11/20/2016'),
            (12,7,'9:00','14:00','12/6/2016'),
            (13,8,'8:00','14:00','1/12/2017'),
            (14,9,'9:00','16:00','10/25/2016'),
            (15,10,'10:00','17:00','11/15/2016'),
            (16,9,'11:00','21:00','11/6/2016'),
            (17,1,'8:00','21:00','1/11/2017'),
            (18,11,'10:00','13:00','12/24/2016'),
            (19,10,'12:00','14:00','12/28/2016'),
            (20,3,'8:00','17:00','12/23/2016');
           
INSERT INTO Adopters(PID)
    values
      		(13),
            (14),
            (15),
            (16),
            (17),
            (18),
            (19),
            (20);
  
INSERT INTO Animals(AID, EstimatedAge, gender, name)
    values
    -- dogs
          
            (1,1,'female','Fiona'),
            (2,3,'male', 'Max'),
            (3,2,'male','Fish'),
            (4,6,'female','Carmel'),
            (5,8,'male','Tank'),
            (6,3,'female','Bella'),
            (7,3,'female','Izzy'),
            (8,2,'male','Rex'),
            (9,5,'male','Buddy'),
       -- cats
            (10,4,'female','Abby'),
            (11,10,'male','Bubba'),
            (12,1,'female','Gracie'),
            (13,1,'male','Frank'),
            (14,7,'female','Rosie'),
            (15,1,'male','Fido'),
            (16,2,'female','Roxy'),
            (17,2,'male','Teddy'),
            (18,3,'female','Daisy'),
        -- birds    
            (19,1,'male','Charlie'),
            (20,1,'female','Molly'),
            (21,1,'female','Lucy'),
            (22,6,'male','Jake'),
        -- other
            (23,3,'female','Sadie'),
            (24,4,'female','Lola'),
            (25,3,'male','Lucky'),
            (26,null,'male','test');
            
             
INSERT INTO DogBreed(DBID,name)
    values
            (1,'mix'),
            (2,'golden retriever'),
            (3, 'pitbull'),
            (4,'labrador retriever'),
            (5,'beagle'),
            (6,'husky'),
            (7,'german sheperd'),
            (8, 'rottweiler'),
            (9,'bulldog'),
            (10,'pug'),
            (11,'chihuahua');                          
                
INSERT INTO Dogs(AID, DBID, Fixed)
    values
            (1,1,'yes'),
            (2,3,'yes'),
            (3,2,'yes'),
            (4,6,'yes'),
            (5,8,'no'),
            (6,10,'yes'),
            (7,11,'yes'),
            (8,9,'no'),
            (9,7,'yes');
                
INSERT INTO CatBreed(CBID,name)
   values
            (1,'mix'),
            (2,'domestic shorthair'),
            (3, 'domestic longhair'),
            (4,'persian'),
            (5,'maine coon'),
            (6,'siamese'),
            (7,'ragdoll'),
            (8, 'burmese');
                  
INSERT INTO Cats(AID, CBID, Neutered)
   values
            (10,4,'yes'),
            (11,7,'yes'),
            (12,1,'no'),
            (13,2,'yes'),
            (14,5,'yes'),
            (15,6,'yes'),
            (16,8,'no'),
            (17,2,'yes'),
            (18,3,'yes');
                
INSERT INTO Birds(AID, Breed)
   values
            (19,'canary'),
            (20,'cockatiel'),
            (21,'parakeet'),
            (22,'grey parrot');
                
INSERT INTO Other(AID, Neutered, AnimalType)
   values
            (23,'no','gerbil'),
            (24,'no','rabbit'),
            (25,'no','teacup pig');

INSERT INTO Adoptions(PID, AID, Date)
   values
            (13,1,'10/20/2016'),
            (14,5,'10/25/2016'),
            (15,7,'10/30/2016'),
            (16,9,'11/6/2016'),
            (17,11,'11/6/2016'),
            (18,13,'11/15/2016'),
            (19,4,'11/20/2016'),
            (20,18,'12/6/2016'),
            (13,23,'12/23/2016'),
            (15,20,'12/24/2016'),
            (18,3,'12/28/2016');
                
INSERT INTO ReasonFor(RFID, Reason)               
   values
            (1, 'heartworm'),
            (2, 'mange'),
            (3, 'new animal'),
            (4, 'bladder infection'),
            (5, 'ticks'),
            (6, 'nail trim'),
            (7, 'neuter'),
            (8, 'euthanization');
                
INSERT INTO VetVisit(RFID,ApptDate,AID)
   values
            (1,'10/26/2016',1),
            (1,'10/30/2016',2),
            (1,'11/5/2016',3),
            (1,'11/17/2016',4),
            (1,'11/21/2016',5),
            (1,'11/27/2016',6),
            (1,'12/4/2016',7),
            (1,'12/8/2016',8),
            (1,'12/20/2016',9),
            (2,'10/26/2016',4),
            (3,'10/30/2016',1),
            (3,'10/30/2016',2),
            (3,'10/30/2016',3),
            (3,'11/5/2016',4), 
            (3,'11/5/2016',5), 
            (3,'11/5/2016',6),
            (3,'11/5/2016',7),
            (3,'11/14/2016',8),
            (3,'11/14/2016',9),  
            (3,'11/14/2016',10),
            (3,'11/21/2016',11),
            (3,'11/21/2016',12),
            (3,'11/21/2016',13),
            (3,'11/21/2016',14),
            (3,'12/5/2016',15),
            (3,'12/5/2016',16),
            (3,'12/5/2016',17),
            (3,'12/5/2016',18),
            (3,'12/20/2016',19),
            (3,'12/20/2016',20),
            (3,'12/20/2016',21),
            (3,'12/28/2016',22),
            (5,'11/6/2016',4);
                   
INSERT INTO Walks(PID,AID,time, date)
   values
            (9,1,'9:00','10/26/2016'),
            (10,2,'10:00','10/30/2016'),
            (10,3,'11:00','11/5/2016'),
            (11,4,'9:00','11/5/2016'),
			(12,5,'11:00','11/11/2016'),
            (10,3,'10:00','11/12/2016'),
            (12,7,'17:00','11/21/2016'),
            (9,9,'16:00','11/22/2016'),
            (10,8,'15:00','12/5/2016'),
            (12,2,'16:00','12/12/2016'),
            (11,3,'14:00','12/28/2016'),
            (12,6,'13:00','1/5/2017');
  
               
DROP VIEW IF EXISTS currentAvailable;
	CREATE VIEW currentAvailable as
    	select name, animals.aid
                from animals
                where animals.aid not in(select animals.AID
       											from Animals
        										inner join Adoptions on Animals.AID=Adoptions.AID
                                              ) and EstimatedAge is not null
       			 ORDER BY Animals.AID ASC;
   -- select * from currentAvailable             
                
DROP VIEW IF EXISTS mostWalks;
	CREATE VIEW mostWalks as
    	select FirstName , count(FirstName) as numWalks
        from People
        	inner join Volunteers on People.PID=Volunteers.PID
            inner join Walks on People.PID=Walks.PID
        group by FirstName    
        ORDER BY count(FirstName) DESC
        LIMIT 2;
                
-- select * from mostWalks   
 
  -- report 1              
select distinct people.firstName as Owner_FirstName, people.LastName as Owner_LastName, animals.Name as PetName, animals.EstimatedAge, adoptions.date
	from adoptions
    	inner join people on people.pid=adoptions.pid
        inner join animals on adoptions.aid=animals.aid;
        
 
CREATE OR REPLACE FUNCTION euthanized()
RETURNS TRIGGER AS	
$$
	BEGIN 
        UPDATE Animals
        SET estimatedAge=null
        where NEW.RFID=8 and NEW.aid=Animals.aid;
        RETURN NEW;
    END;
$$
language plpgsql;

CREATE TRIGGER euthanize
AFTER INSERT ON VetVisit
FOR EACH ROW
EXECUTE PROCEDURE euthanized();
    
-- check stored procedure and trigger 
insert into vetVisit(RFID, ApptDate,AID)
values
 (8,'11/7/2016',10);
 
CREATE OR REPLACE FUNCTION oldEnough(VolunteerID int)
RETURNS INTERVAL AS
$$
DECLARE
	birthday date := (SELECT people.dob
                      from people
                      inner join volunteers on people.pid=volunteers.pid
                      where volunteers.pid=VolunteerID
                      );
     BEGIN
        RETURN age(birthday);
     END;
$$
 language plpgsql;

-- select oldEnough(9)

CREATE OR REPLACE FUNCTION calculateTotalHours(date, refcursor) 
RETURNS refcursor as
$$ 
	DECLARE
    	dayOf date :=$1;
    	resultset refcursor:=$2;
	BEGIN  
    	open resultset for
        	select shifts.pid, sum(timeout-timein) as TotalHours,timein, timeout , people.firstName
            from shifts
            	inner join people on shifts.pid=people.pid
             where shifts.date=dayOf
            group by shifts.pid,people.firstName,timein,timeout
            order by pid ASC;
  		return resultset;
    END;
$$
language plpgsql;

select calculateTotalHours('2016-11-6','results');
fetch all from results;


