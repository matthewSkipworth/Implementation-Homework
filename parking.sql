create table Lot(lotName    varchar(20),
						    location varchar(20), 
                            capacity integer, 
                            floors integer,
                            primary key(lotName)
                            );

create table Space(spaceNumber  integer,
								spaceType varchar(20),
                                lotName varchar(20), 
                                primary key(spaceNumber),
                                foreign key(lotName) references Lot(lotName)
                                );

create table Staff(staffNumber  integer,
							    telephoneExt int, 
                                vehicleLicenseNumber integer,
                                primary key(staffNumber)
                                );

create table StaffSpace(staffNumber integer,
									spaceNumber integer,
                                    primary key(staffNumber, spaceNumber),
                                    foreign key (staffNumber) references Staff(staffNumber),
                                    foreign key (spaceNumber) references Space(spaceNumber)
                                    );
                            
create table SpaceBooking(BookingId integer ,
									spaceNumber integer,
									staffNumber integer, 
									visitorLicence integer, 
									dateOfVisit date,
									primary key(BookingId),
									foreign key(spaceNumber) references Space(spaceNumber),
									foreign key (staffNumber) references Staff(staffNumber)
									);

create table CoveredSpace(spaceNumber   integer,
						monthlyRate decimal(10,2),
                        primary key(spaceNumber));

create table UncoveredSpace(spaceNumber integer,
										primary key(spaceNumber),
										foreign key (spaceNumber) references Space(spaceNumber)
										);
select * from Staff;

insert into CoveredSpace values
	(1,94,1234),
    (2,75,9874);
    
insert into Staff values
	(1,2.17),
    (2,3.14);
    
DROP TABLE CoveredSpace;