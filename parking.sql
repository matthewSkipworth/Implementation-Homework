#Jake Mckenzie and Matthew Skipworth
#Implementation Homework
#August 10 2018

SET FOREIGN_KEY_CHECKS = 0;    
DROP TABLE Lot;
Drop TABLE `Space`;
Drop TABLE Staff;
Drop TABLE StaffSpace;
Drop TABLE SpaceBooking;
DROP TABLE CoveredSpace;
Drop TABLE UncoveredSpace;
SET FOREIGN_KEY_CHECKS = 1;

create table Lot(
	lotName varchar(20),
	location varchar(20), 
    capacity integer, 
    floors integer,
    primary key(lotName)
);

create table `Space`(
	spaceNumber integer,
	spaceType varchar(20),
    pLotName varchar(20), 
    primary key(spaceNumber),
    constraint fk_lotNm foreign key(pLotName) references Lot(lotName)
);

create table Staff(
	staffNumber integer,
	telephoneExt int, 
    vehicleLicenseNumber integer,
    primary key(staffNumber)
);

create table CoveredSpace(
	coveredSpaceNumber integer,
	monthlyRate decimal(10,2),
    primary key(coveredSpaceNumber),
    constraint fk_coveredSpaceNumber foreign key(coveredSpaceNumber)references `Space`(spaceNumber)
);

create table UncoveredSpace(
	uncoveredSpaceNumber integer,
	primary key(uncoveredSpaceNumber),
	constraint fk_uncoveredSpaceNumber foreign key (uncoveredSpaceNumber) references `Space`(spaceNumber)
);

create table StaffSpace(
	staffNum integer,
	pSpaceNumber integer,
    primary key(staffNum),
    constraint fk_staffNum foreign key (staffNum) references Staff(staffNumber),
    constraint fk_pSpaceNumber foreign key (pSpaceNumber) references CoveredSpace(coveredSpaceNumber)
);
                        
create table SpaceBooking(
	BookingId integer, 
    spaceNum integer, 
    staffN integer, 
    visitorLicence integer, 
    dateOfVisit varchar(20), primary key(BookingId),
	constraint fk_spaceNum foreign key(spaceNum) references CoveredSpace(coveredSpaceNumber),
	constraint fk_staffN foreign key (staffN) references Staff(staffNumber)
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


SELECT * 
FROM Space
WHERE spaceNumber IN (
	SELECT spaceNumber 
    FROM `Space`
    WHERE spaceNumber
    NOT IN
		(SELECT pSpaceNumber
		FROM StaffSpace
		UNION ALL
		SELECT spaceNum
		FROM SpaceBooking)
);
