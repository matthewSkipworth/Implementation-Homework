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
	
	public SpaceBooking(Integer BookingId,Integer spaceNumber,
	Integer staffNumber, Integer visitorLicense, String dateOfVisit) {
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
 * method getStaffNumber returns the staff member's Company ID number.
 * @return returns the staff member's Company ID number.
 */
	public Integer getStaffNumber() {
		return staffNumber;
	}
/**
 * method getDateOfVisit returns the visitor's planned date of visit.	
 * @return returns the visitor's planned date of visit.	
 */
	public String getDateOfVisit() {
		return dateOfVisit;
	}
/**
 * metheod setVisitorLicense update's the visitor's vehicle license
 * number.	
 * @param newLicense represents the license number that the visitor's license
 * number will be updated to.
 */
	public void setVisitorLicense(Integer newLicense) {
		this.visitorLicense = newLicense;
	}
/**
 * method setBookingId updates the booking ID number	
 * @param newBookingId represents the updated booking ID number.
 */
	public void setBookingId(Integer newBookingId) {
		this.BookingId = newBookingId;
	}
/**
 * method setSpaceNumber updates the requested visitor space number.	
 * @param newSpaceNumber represents the new space number for the SpaceBooking
 * object to update to.
 */
	public void setSpaceNumber(Integer newSpaceNumber) {
		this.spaceNumber = newSpaceNumber;
	}
/**
 * method setStaffNumber updates the staff member number on the visitor parking
 * request form.	
 * @param newStaffNumber represents the new staff member's number.
 */
	public void setStaffNumber(Integer newStaffNumber) {
		this.staffNumber = newStaffNumber;
	}
/**
 * method setDateOfVisit updates the visitor's planned date of visit.	
 * @param newDateOfVisit represents the new planned visit date.
 */
	public void setDateOfVisit(String newDateOfVisit) {
		this.dateOfVisit = newDateOfVisit;
	}
	
	
}
