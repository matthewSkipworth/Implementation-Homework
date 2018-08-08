/**
 * 
 * @author Matthew Skipworth and Jake McKenzie
 * @version 6 August 2018
 */
public class Staff {
	private Integer staffNumber;
	private Integer telephoneExt;
	private Integer vehicleLicenseNumber;
/**
 * Class constructor.	
 * @param staffNumber represents the staff member's company ID number.
 * @param telephoneExt represents the staff member's telephone extension number.
 * @param vehicleLicenseNumber represents the staff member's vehicle license 
 * (driver's license?) number.
 */
	public Staff(Integer staffNumber, Integer telephoneExt, 
					Integer vehicleLicenseNumber) {
		this.staffNumber = staffNumber;
		this.telephoneExt = telephoneExt;
		this.vehicleLicenseNumber = vehicleLicenseNumber;
	}
/**
 * method getStaffNumber returns the staff member's company ID number.
 * @return returns the staff member's company ID number.
 */
	public Integer getStaffNumber() {
		return staffNumber;
	}
/**
 * method getTelephoneExt returns the staff member's company telephone 
 * extension number.	
 * @return returns the staff member's company telephone 
 */
	public Integer getTelephoneExt() {
		return telephoneExt;
	}
/**
 * method getVehicleLicenseNumber returns the staff member's vehicle license
 * number.	
 * @return returns the staff member's vehicle license number.	
 */
	public Integer getVehicleLicenseNumber() {
		return vehicleLicenseNumber;
	}
/**
 * method setTelephoneExt updates the staff member's company telephone 
 * extension number.	
 * @param newTelephoneExt
 */
	public void setTelephoneExt(Integer newTelephoneExt) {
		telephoneExt = newTelephoneExt;
	}
/**
 * method setVehicleLicenseNumber updates the staff member's vehicle license
 * number.	
 * @param newVehicleLicenseNumber represents the staff member's updated 
 * vehicle license number.
 */
	public void setVehicleLicenseNumber(Integer newVehicleLicenseNumber) {
		vehicleLicenseNumber = newVehicleLicenseNumber;
	}
}
