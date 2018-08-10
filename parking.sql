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

create table StaffSpace(staffNum integer,
										pSpaceNumber integer,
                                        primary key(staffNum), 
                                        primary key(pSpaceNumber),
                                        constraint fk_staffNum foreign key (staffNum) references Staff(staffNumber),
                                        constraint fk_pSpaceNumber foreign key (pSpaceNumber) references CoveredSpace(coveredSpaceNumber)
                                        );
                            
create table SpaceBooking(BookingId integer, spaceNum integer, staffN integer, visitorLicence integer, dateOfVisit date, primary key(BookingId),
											constraint fk_spaceNum foreign key(spaceNum) references CoveredSpace(coveredSpaceNumber),
											constraint fk_staffN foreign key (staffN) references Staff(staffNumber)
											);

create table CoveredSpace(coveredSpaceNumber integer,
											monthlyRate decimal,
                                            primary key(coveredSpaceNumber),
                                            constraint fk_coveredSpaceNumber foreign key(coveredSpaceNumber)references `Space`(spaceNumber));

create table UncoveredSpace(uncoveredSpaceNumber integer,
												primary key(uncoveredSpaceNumber),
												constraint fk_uncoveredSpaceNumber foreign key (uncoveredSpaceNumber) references `Space`(spaceNumber)
												);                           
                                                
                                               
                                                