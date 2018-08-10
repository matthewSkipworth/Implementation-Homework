/**
 * Represents a lot with a location, a capacity, floors and a lot name.
 * @author Jake McKenzie, Matthew Skipworth
 * @version 6 August 2018
 */
public class Lot {
	private Integer capacity,floors;
	private String location, lotName;
	
	
	/**
	 * Initialize the lot parameters.
	 * @param capacity
	 * @param floors
	 * @param location
     * @param lotName
	 * @throws IllegalArgumentException if title or lot type or lot name are null or empty
	 */
    public Lot(String lotName,String location, Integer capacity, Integer floors) {
		setCapacity(capacity);
        setFloors(floors);
		setLocation(location);
		setLotName(lotName);
	}
	
	@Override
	public String toString() {
        return "lot [capacity=" + capacity + 
                ", floors=" + floors + ", location=" + 
                location + ", lotName=" + lotName + "]";
	}

	/**
	 * Returns the capacity of the lot.
	 * @return capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}
	
	/**
	 * Modifies the capacity of the lot.
	 * @param capacity
	 * @throws IllegalArgumentException if title is null or empty.
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Returns the floors for the the lot.
	 * @return floors
	 */
	public Integer getFloors() {
		return floors;
	}
	
	/**
	 * Sets the floors.
	 *	return floors
	 * @param floors
	*/
	public void setFloors(Integer floors) {
		this.floors = floors;
	}
	
	/**
	 * Returns the location for the lot.
	 * @return location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Sets the location for the lot.
		return location
	 * @param location
	*/
	public void setLocation(String location) {
		if (location == null || location.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid location.");
		this.location = location;
    }
    /**
	 * Returns the lot name for the lot.
	 * @return lotName
	 */
	public String getLotName() {
		return lotName;
	}
	
	/**
	 * Sets the lot name for the lot.
		return lotName
	 * @param lotName
	*/
	public void setLotName(String lotName) {
		if (lotName == null || lotName.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid lot name.");
		this.lotName = lotName;
	}
}