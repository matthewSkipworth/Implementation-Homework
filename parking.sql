#Jake Mckenzie and Matthew Skipworth
#Implementation Homework
#August 10 2018

#You told me to not worry about the 20 visitor limit. I would 

SET FOREIGN_KEY_CHECKS = 0;    
DROP TABLE Lot;
Drop TABLE `Space`;
Drop TABLE Staff;
DROP TABLE CoveredSpace;
Drop TABLE UncoveredSpace;
Drop TABLE StaffSpace;
Drop TABLE SpaceBooking;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE Lot(
	lotName VARCHAR(20),
	location VARCHAR(20), 
    capacity INTEGER, 
    floors INTEGER,
    PRIMARY KEY(lotName)
);

CREATE TABLE `Space`(
	spaceNumber INTEGER,
	spaceType VARCHAR(20),
    pLotName VARCHAR(20), 
    PRIMARY KEY(spaceNumber),
    CONSTRAINT fk_lotNm FOREIGN KEY(pLotName) REFERENCES Lot(lotName)
);

CREATE TABLE Staff(
	staffNumber INTEGER,
	telephoneExt int, 
    vehicleLicenseNumber INTEGER,
    PRIMARY KEY(staffNumber)
);

CREATE TABLE CoveredSpace(
	coveredSpaceNumber INTEGER,
	monthlyRate decimal(10,2),
    PRIMARY KEY(coveredSpaceNumber),
    CONSTRAINT fk_coveredSpaceNumber FOREIGN KEY(coveredSpaceNumber)REFERENCES `Space`(spaceNumber)
);

CREATE TABLE UncoveredSpace(
	uncoveredSpaceNumber INTEGER,
	PRIMARY KEY(uncoveredSpaceNumber),
	CONSTRAINT fk_uncoveredSpaceNumber FOREIGN KEY (uncoveredSpaceNumber) REFERENCES `Space`(spaceNumber)
);

CREATE TABLE StaffSpace(
	staffNum INTEGER,
	pSpaceNumber INTEGER,
    PRIMARY KEY(staffNum),
    CONSTRAINT fk_staffNum FOREIGN KEY (staffNum) REFERENCES Staff(staffNumber),
    CONSTRAINT fk_pSpaceNumber FOREIGN KEY (pSpaceNumber) REFERENCES CoveredSpace(coveredSpaceNumber)
);
                        
CREATE TABLE SpaceBooking(
	BookingId INTEGER, 
    spaceNum INTEGER, 
    staffN INTEGER, 
    visitorLicence INTEGER, 
    dateOfVisit VARCHAR(20), PRIMARY KEY(BookingId),
	CONSTRAINT fk_spaceNum FOREIGN KEY(spaceNum) REFERENCES CoveredSpace(coveredSpaceNumber),
	CONSTRAINT fk_staffN FOREIGN KEY (staffN) REFERENCES Staff(staffNumber)
);

insert into Staff values
	(1,1,1),
    (2,2,2);

insert into Lot values
	('a','lot a',25,4),
    ('b','lot b',36,2);
    
insert into `Space` values
    (354,'uncovered','a'),
    (355,'uncovered','a'),
    (356,'uncovered','a'),
    (687,'covered','b'),
    (688,'covered','b');

insert into CoveredSpace values
	(687,2.17),
    (688,3.14);
    

insert into UncoveredSpace values
	(354);
    
insert into StaffSpace values
	(1,687),
    (2,688);
    
insert into SpaceBooking values
	(656,687,1,3884,'tuesday'),
    (897,688,2,232342,'funday');
    
#select * FROM Staff;
#select * FROM `Space`;
#select * FROM CoveredSpace;
#select * FROM Lot;
#select * FROM Staff;
#select * FROM UncoveredSpace;
#select * FROM StaffSpace;
#select * FROM SpaceBooking;


#SELECT * 
#FROM `Space`
#WHERE spaceNumber IN (
#	SELECT spaceNumber 
#    FROM `Space`
#    WHERE spaceNumber
#    NOT IN
#		(SELECT pSpaceNumber
#		FROM StaffSpace
#		UNION ALL
#		SELECT spaceNum
#		FROM SpaceBooking)
#);

#UPDATE Staff
#SET telephoneExt = ?, vehicleLicenseNumber= ?
#WHERE staffNumber = ?;