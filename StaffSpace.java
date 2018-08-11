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
	 * @param spaceNumber
     * @param staffNumber
	 * @throws IllegalArgumentException if title or lot type or lot name are null or empty
	 */
    public StaffSpace(Integer staffNumber, Integer spaceNumber) {
        setSpaceNumber(spaceNumber);
        setStaffNumber(staffNumber);
	}
	
	@Override
	public String toString() {
        return "CoveredSpace [spaceNumber=" + spaceNumber +
                "staffNumber=" + staffNumber + "]";
	}

	/**
	 * Returns the space number of the covered space.
	 * @return spaceNumber
	 */
	public Integer getSpaceNumber() {
		return spaceNumber;
	}
	
	/**
	 * Modifies the space number of the covered space.
	 * @param spaceNumber
	 */
	public void setSpaceNumber(Integer spaceNumber) {
		this.spaceNumber = spaceNumber;
    }
    /**
	 * Returns the staff number of the staff space.
	 * @return staffNumber
	 */
	public Integer getStaffNumber() {
		return staffNumber;
	}
	
	/**
	 * Modifies the staff number of the staff space.
	 * @param staffNumber
	 */
	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}
}