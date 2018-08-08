/**
 * Represents a staff space with a space number and staff number.
 * @author Jake McKenzie, Matthew Skipworth
 * @version 6 August 2018
 */
public class StaffSpace {
    private Integer spaceNumber;
    private Integer staffNumber;
	/**
	 * Initialize the staff space parameter.
	 * @param spaceNumber represents a space ID number assigned to this space.
     * @param staffNumber represents the employee's company ID number.
	 * @throws IllegalArgumentException if title or lot type or lot name are null or empty.
	 */
    public StaffSpace(Integer spaceNumber, Integer monthlyRate) {
        setSpaceNumber(spaceNumber);
        setStaffNumber(staffNumber);
	}
	/**
	 * method toString returns a String representation of this StaffSpace.
	 * @return returns a String representation of this StaffSpace.
	 */
	@Override
	public String toString() {
        return "CoveredSpace [spaceNumber=" + spaceNumber +
                "staffNumber=" + staffNumber + "]";
	}

	/**
	 * method getSpaceNumber returns the space number belonging to this staff
	 * space.
	 * @return returns the space number belonging to this staff space.
	 */
	public Integer getSpaceNumber() {
		return spaceNumber;
	}
	
	/**
	 * Modifies the space number of the covered space.
	 * @param spaceNumber represents the new staffSpace ID number.
	 */
	public void setSpaceNumber(Integer spaceNumber) {
		this.spaceNumber = spaceNumber;
    }
    /**
	 * Returns the staff number of the staff space.
	 * @return staffNumber represents the staff member's ID number
	 * corresponding to this StaffSpace.
	 */
	public Integer getStaffNumber() {
		return staffNumber;
	}
	
	/**
	 * Modifies the staff number of the staff space.
	 * @param staffNumber represents the new staff member's ID number 
	 * corresponding to this StaffSpace.
	 */
	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}
}