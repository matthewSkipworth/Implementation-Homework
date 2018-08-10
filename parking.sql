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
    dateOfVisit date, primary key(BookingId),
	constraint fk_spaceNum foreign key(spaceNum) references CoveredSpace(coveredSpaceNumber),
	constraint fk_staffN foreign key (staffN) references Staff(staffNumber)
);

create table UncoveredSpace(
	uncoveredSpaceNumber integer,
	primary key(uncoveredSpaceNumber),
	constraint fk_uncoveredSpaceNumber foreign key (uncoveredSpaceNumber) references `Space`(spaceNumber)
);
     
										primary key(spaceNumber),
										foreign key (spaceNumber) references `Space`(spaceNumber)
										);
select * FROM Staff;
select * FROM `Space`;
select * FROM CoveredSpace;
select * FROM Lot;

insert into Staff values
	(1,94,1234),
    (2,75,9874);
    
insert into `Space` values
	(1,'Covered','Garage A'),
    (2,'Uncovered','Garage B');    
    
insert into `Space` values
	(3,'Covered','Garage A');
    
insert into CoveredSpace values
	(1,2.17),
    (2,3.14);

SET FOREIGN_KEY_CHECKS = 0;    
DROP TABLE Lot;
Drop TABLE `Space`;
Drop TABLE Staff;
Drop TABLE StaffSpace;
Drop TABLE SpaceBooking;
DROP TABLE CoveredSpace;
Drop TABLE UncoveredSpace;
SET FOREIGN_KEY_CHECKS = 1;