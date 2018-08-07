/**
 * Represents a Space with a space number, a type of space and a lot name.
 * @author Jake McKenzie, Matthew Skipworth
 *
 */
public class Space {
	private Integer spaceNumber;
	private String spaceType, lotName;
	
	
	/**
	 * Initialize the space parameters.
	 * @param spaceNumber
	 * @param spaceType
	 * @param lotName
	 * @throws IllegalArgumentException if title or space type or lot name are null or empty
	 */
    public Space(Integer spaceNumber, String spaceType, String lotName) {
		setSpaceNumber(spaceNumber);
		setSpaceType(spaceType);
		setLotName(lotName);
	}
	
	@Override
	public String toString() {
        return "Space [spaceNumber=" + spaceNumber + 
                ", spaceType=" + spaceType + ", lotName=" + 
                lotName + "]";
	}

	/**
	 * Returns the space number of the space.
	 * @return space number
	 */
	public Integer getSpaceNumber() {
		return spaceNumber;
	}
	
	/**
	 * Modifies the space number of the space.
	 * @param spaceNumber
	 * @throws IllegalArgumentException if title is null or empty.
	 */
	public void setSpaceNumber(int spaceNumber) {
		this.spaceNumber = spaceNumber;
	}
	
	/**
	 * Returns the lot name for the the space.
	 * @return spaceType
	 */
	public String getSpaceType() {
		return spaceType;
	}
	
	/**
	 * Sets the space year.
		return space type
	 * @param spaceType
	*/
	public void setSpaceType(String spaceType) {
		if (spaceType == null || spaceType.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid space type.");
		this.spaceType = spaceType;
	}
	
	/**
	 * Returns the lot name for the space.
	 * @return lotName
	 */
	public String getLotName() {
		return lotName;
	}
	
	/**
	 * Sets the lot name for the space.
		return lotName
	 * @param lotName
	*/
	public void setLotName(String lotName) {
		if (lotName == null || lotName.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid space lot name.");
		this.lotName = lotName;
	}
	
}