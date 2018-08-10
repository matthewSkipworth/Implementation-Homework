create table Lot(lotName varchar(20),
						location varchar(20), 
                        capacity integer, 
                        floors integer,
                        primary key(lotName)
                        );

create table `Space`(spaceNumber integer,
								spaceType varchar(20),
                                pLotName varchar(20), 
                                primary key(spaceNumber),
                                constraint fk_lotNm foreign key(pLotName) references Lot(lotName)
                                );
                                

create table Staff(staffNumber integer,
							telephoneExt int, 
                            vehicleLicenseNumber integer,
                            primary key(staffNumber)
                            );
                            
create table CoveredSpace(coveredSpaceNumber integer,
	monthlyRate decimal(10,2),
	primary key(coveredSpaceNumber),
    constraint fk_coveredSpaceNumber foreign key(coveredSpaceNumber)references `Space`(spaceNumber));


create table StaffSpace(staffNum integer,
	pSpaceNumber integer,
	primary key(staffNum), 
	# pSpaceNumber),
	constraint fk_staffNum foreign key (staffNum) references Staff(staffNumber),
	constraint fk_pSpaceNumber foreign key (pSpaceNumber) references CoveredSpace(coveredSpaceNumber)
);
                            
create table SpaceBooking(BookingId integer, spaceNum integer, staffN integer, visitorLicence integer, dateOfVisit date, primary key(BookingId),
											constraint fk_spaceNum foreign key(spaceNum) references CoveredSpace(coveredSpaceNumber),
											constraint fk_staffN foreign key (staffN) references Staff(staffNumber)
											);


create table UncoveredSpace(uncoveredSpaceNumber integer,
												primary key(uncoveredSpaceNumber),
												constraint fk_uncoveredSpaceNumber foreign key (uncoveredSpaceNumber) references `Space`(spaceNumber)
												);                           
                                                
                                               
insert into Lot values('Tioga', 'Tacoma',300,12);   
insert into Lot values('Snoqualmie', 'Puyallup',400,15);   

insert into Space values(12,'uncovered','Tioga');
insert into Space values(13,'covered','Tioga');
insert into Space values(15, 'covered', 'Tioga');

insert into CoveredSpace values(12, 4.55);
insert into CoveredSpace values(13, 5.60);
insert into CoveredSpace values(15, 7.75);



select * 
from Space
where spaceNumber in (

	select spaceNumber 
    from `Space`
    where spaceNumber
    not in
    
		(select pSpaceNumber
		from StaffSpace
    
		union all
    
		select spaceNum
		from SpaceBooking)
    
);

select pSpaceNumber
		from StaffSpace
    
		union
    
		select spaceNum
		from SpaceBooking;


SET FOREIGN_KEY_CHECKS = 0;    
DROP TABLE Lot;
Drop TABLE `Space`;
Drop TABLE Staff;
Drop TABLE StaffSpace;
Drop TABLE SpaceBooking;
DROP TABLE CoveredSpace;
Drop TABLE UncoveredSpace;
SET FOREIGN_KEY_CHECKS = 1;