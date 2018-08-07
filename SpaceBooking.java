/**
 * 
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
	
	public Integer getStaffNumber() {
		return staffNumber;
	}
	
	public String getDateOfVisit() {
		return dateOfVisit;
	}
	
	public void setVisitorLicense(Integer newLicense) {
		this.visitorLicense = newLicense;
	}
	
	public void setBookingId(Integer newBookingId) {
		this.BookingId = newBookingId;
	}
	
	public void setSpaceNumber(Integer newSpaceNumber) {
		this.spaceNumber = newSpaceNumber;
	}
	
	public void setStaffNumber(Integer newStaffNumber) {
		this.staffNumber = newStaffNumber;
	}
	
	public void setDateOfVisit(String newDateOfVisit) {
		this.dateOfVisit = newDateOfVisit;
	}
	
	
}

