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
	 * @param capacity represents the Lot's parking Space capacity.
	 * @param floors represents the Lot's number of floors.
	 * @param location represents the Lot's geographical location.
     * @param lotName represents the Lot's name.
	 * @throws IllegalArgumentException if title or lot type or lot name are
	 *  null or empty
	 */
    public Lot(Integer capacity, Integer floors, String location, 
    		String lotName) {
        setCapacity(capacity);
        setFloors(floors);
		setLocation(location);
		setLotName(lotName);
	}
	/**
	 * toString method returns a String representation of the data contained
	 * in this Lot.
	 * @return returns this Lot's capacity, number of floors, location, and 
	 * lotName.
	 */
	@Override
	public String toString() {
        return "lot [capacity=" + capacity + 
                ", floors=" + floors + ", location=" + 
                location + ", lotName=" + lotName + "]";
	}

	/**
	 * Returns the capacity of the lot.
	 * @return returns this Lot's maximum capacity.
	 */
	public Integer getCapacity() {
		return capacity;
	}
	
	/**
	 * Modifies the capacity of the lot.
	 * @param capacity represents an updated parking space capacity.
	 * @throws IllegalArgumentException if title is null or empty.
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Returns the floors for the the lot.
	 * @return floors. This Lot's number of floors.
	 */
	public Integer getFloors() {
		return floors;
	}
	
	/**
	 * method setFloors updates this Lot's floor count.
	 * @param floors represents the number of floors for this Lot to update to. 
	*/
	public void setFloors(Integer floors) {
		this.floors = floors;
	}
	
	/**
	 * method getLocations 
	 * Returns the location for the lot.
	 * @return location. This Lot's geographical location.
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Sets the location for the lot.
	 * @param location represents this Lot's updated location.
	*/
	public void setLocation(String location) {
		if (location == null || location.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid location.");
		this.location = location;
    }
    /**
	 * Returns the lot name for the lot.
	 * @return lotName. Returns the Lot's name.
	 */
	public String getLotName() {
		return lotName;
	}
	
	/**
	 * Sets the lot name for the lot.
		return lotName
	 * @param lotName represents this Lot's updated name.
	*/
	public void setLotName(String lotName) {
		if (lotName == null || lotName.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid lot name.");
		this.lotName = lotName;
	}
}