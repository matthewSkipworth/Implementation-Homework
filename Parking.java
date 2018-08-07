/**
 * Represents a Space with a space number, a type of space and a lot name.
 * @author Jake McKenzie, Matthew Skipworth
 *
 */
public class Space {
	private int spaceNumber;
	private String spaceType, lotName;
	
	
	/**
	 * Initialize the Space parameters.
	 * @param spaceNumber
	 * @param spaceType
	 * @param lotName
	 * @throws IllegalArgumentException if title or space type or lot name are null or empty
     * @throws IllegalArgumentException if space number is not an integer
	 */
    public Space(int spaceNumber, String spaceType, String lotName) {
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
	 * Returns the title of the Space.
	 * @return Space title
	 */
	public String getSpaceNumber() {
		return spaceNumber;
	}
	
	/**
	 * Modifies the spaceNumber of the Space.
	 * @param spaceNumber
	 * @throws IllegalArgumentException if title is null or empty.
	 */
	public void setSpaceNumber(int spaceNumber) {
		if (spaceNumber instanceof Integer) {
            this.spaceNumber = spaceNumber;
        } else {
            throw new IllegalArgumentException("Please supply a valid spaceNumber.");
        }
	}
	
	/**
	 * Returns the lot name for the the Space.
	 * @return spaceType
	 */
	public int getSpaceTypee() {
		return spaceType;
	}
	
	/**
	 * Sets the Space year.
		return spaceType
	 * @param spaceType
	*/
	public void setSpaceType(String spaceType)
	{
		if (spaceType == null || spaceType.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid space type.");
		this.spaceType = spaceType;
	}
	
	/**
	 * Returns the year the Space was made.
	 * @return lotName
	 */
	public String getLotName() {
		return lotName;
	}
	
	/**
	 * Sets the lotName year.
		return lotName
	 * @param lotName
	*/
	public void setLotName(String lotName)
	{
		if (lotName == null || lotName.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid space type.");
		this.lotName = lotName;
	}
	
}