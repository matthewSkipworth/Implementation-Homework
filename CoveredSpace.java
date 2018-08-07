/**
 * Represents an uncovered space with a space number.
 * @author Jake McKenzie, Matthew Skipworth
 * @version 6 August 2018
 */
public class CoveredSpace {
    private Integer spaceNumber;
    private Double monthlyRate;
	/**
	 * Initialize the covered space parameter.
	 * @param spaceNumber
     * @param monthlyRate
	 * @throws IllegalArgumentException if title or lot type or lot name are null or empty
	 */
    public CoveredSpace(Integer spaceNumber, Double monthlyRate) {
        setSpaceNumber(spaceNumber);
        setMonthlyRate(monthlyRate);
	}
	
	@Override
	public String toString() {
        return "CoveredSpace [spaceNumber=" + spaceNumber +
                "monthlyRate=" + monthlyRate + "]";
	}

	/**
	 * Returns the space number of the covered space.
	 * @return capacity
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
	 * Returns the monthly rate of the covered space.
	 * @return monthlyRate
	 */
	public Double getMonthlyRate() {
		return monthlyRate;
	}
	
	/**
	 * Modifies the monthly rate of the covered space.
	 * @param monthlyRate
	 */
	public void setMonthlyRate(Double monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
}