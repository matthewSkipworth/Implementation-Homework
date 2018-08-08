/**
 * Represents an uncovered space with a space number.
 * @author Jake McKenzie, Matthew Skipworth
 * @version 6 August 2018
 */
public class UncoveredSpace {
	private Integer spaceNumber;
	
	
	/**
	 * Initialize the uncovered space parameter.
	 * @param spaceNumber
	 * @throws IllegalArgumentException if title or lot type or lot name are null or empty
	 */
    public UncoveredSpace(Integer spaceNumber) {
        setSpaceNumber(spaceNumber);
	}
	
	@Override
	public String toString() {
        return "UncoveredSpace [spaceNumber=" + spaceNumber +"]";
	}

	/**
	 * Returns the sapce number of the uncovered space.
	 * @return spaceNumber
	 */
	public Integer getSpaceNumber() {
		return spaceNumber;
	}
	
	/**
	 * Modifies the space number of the uncovered space.
	 * @param spaceNumber
	 */
	public void setSpaceNumber(Integer spaceNumber) {
		this.spaceNumber = spaceNumber;
	}
}