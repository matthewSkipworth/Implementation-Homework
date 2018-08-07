/**
 * Represents an space booking with a visitor license, date of visit, space number,
 * booking id and staff number.
 * @author Matthew Skipworth and Jake McKenzie
 * @version 6 August 2018
 */

public class SpaceBooking {
	private Integer BookingId, spaceNumber, visitorLicense, staffNumber;
	private String dateOfVisit;
	
/**
 * Class Constructor.
 * @param visitorLicense represents the visitors license number.
 * @param dateOfVisit represents the planned day of visit.
 * @param spaceNumber represents the space number to be booked.
 * @param BookingId represents the ID number for this booking.
 * @param staffNumber represents the staff member's company ID number for the 
 * staff member doing the booking.
 */
	
	public SpaceBooking(Integer visitorLicense, String dateOfVisit, 
						Integer spaceNumber, Integer BookingId, Integer staffNumber) {
		this.visitorLicense = visitorLicense;
		this.dateOfVisit = dateOfVisit;
		this.spaceNumber = spaceNumber;
		this.BookingId = BookingId;
		this.staffNumber = staffNumber;
	}
/**
 * method getVisitorLicense returns this bookings visitor license number.	
 * @return returns this bookings visitor license number.
 */
	public Integer getVisitorLicense() {
		return visitorLicense;
	}
/**
 * method getBookingId returns this booking's booking ID number.	
 * @return returns this booking's booking ID number
 */
	public Integer getBookingId() {
		return BookingId;
	}
/**
 * method getSpaceNumber returns this booking's requested space number.	
 * @return returns this booking's requested space number.
 */
	public Integer getSpaceNumber() {
		return spaceNumber;
	}
/**
 * method getStaffNumber returns this booking's company staff ID number.
 * @return returns this booking's company staff ID number.
 */
	public Integer getStaffNumber() {
		return staffNumber;
	}
/**
 * method getDateOfVisit returns this booking's planned date of visit.	
 * @return returns this booking's planned date of visit.	
 */
	public String getDateOfVisit() {
		return dateOfVisit;
	}
/**
 * method setVisitorLicense updates the visitor's vehicle license number.	
 * @param newLicense represents the new license number for the booking to 
 * update to.
 */
	public void setVisitorLicense(Integer newLicense) {
		this.visitorLicense = newLicense;	
	}
	
	//public void setBookingId(Integer newBookingId) {
	//	this.BookingId = newBookingId;
	//}
/**
 * method setSpaceNumber updates the parking lot space number for this booking
 * to a new space number.	
 * @param newSpaceNumber represents the parking lot space number for which this
 * booking will update to
 * 
 */
	public void setSpaceNumber(Integer newSpaceNumber) {
		this.spaceNumber = newSpaceNumber;
	}
/**
 * method setStaffNumber updates the staff members company ID number to a new
 * staff member's ID number.
 * @param newStaffNumber represents the new staff member's company ID number.
 */
	public void setStaffNumber(Integer newStaffNumber) {
		this.staffNumber = newStaffNumber;
	}
/**
 * method setDateOfVisit updates the planned date of visit.	
 * @param newDateOfVisit represents the updated visit date.
 */
	public void setDateOfVisit(String newDateOfVisit) {
		this.dateOfVisit = newDateOfVisit;
	}
	
	
}

