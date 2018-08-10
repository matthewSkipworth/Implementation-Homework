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

insert into Lot values
	('a','b',1234,456),
    ('v','b',12,342);
    
insert into `Space` values
	(2323,'df','dfsf'),
    (234234,'dfsf','qwe');
    
insert into CoveredSpace values
	(1,2.17),
    (2,3.14);
    
insert into `Space` values
	(1,'Covered','Garage A'),
    (2,'Uncovered','Garage B'),
	(3,'Covered','Garage A');
    
insert into UncoveredSpace values
	(4534),
    (45453788),
    (5466878),
    (34234243);
    
insert into StaffSpace values
	(3543534,345345),
    (6757566,69876579);
    
insert into SpaceBooking values
	(900879,534535,3423,234234,'sdrwer'),
    (900000000879,53400000535,342232113,23344234,'sdrdfsdfr');

SET FOREIGN_KEY_CHECKS = 0;    
DROP TABLE Lot;
Drop TABLE `Space`;
Drop TABLE Staff;
Drop TABLE StaffSpace;
Drop TABLE SpaceBooking;
DROP TABLE CoveredSpace;
Drop TABLE UncoveredSpace;
SET FOREIGN_KEY_CHECKS = 1;