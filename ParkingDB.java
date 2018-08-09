import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.Properties;

/**
 * A class that consists of the database operations to insert and update the Movie information.
 * @author Matthew Skipworth and Jake McKenzie
 * @version 6 August 2018
 *
 */

public class ParkingDB {
	private static String userName = "mfskipwo";
	private static String password = "OkvofUt6";
	private static String serverName = "cssgate.insttech.washington.edu";
	private static Connection sConnection;
    private List<Lot> lotList;
    //private List<Space> spaceList;
    private List<StaffSpace> staffSpaceList;
    private List<Staff> staffList;
    private List<SpaceBooking> bookingList;
    private List<CoveredSpace> coveredSpaceList;
    private List<UncoveredSpace> uncoveredSpaceList;
    private List<Space> spaceList;
    private List<Space> availableSpaceList;


/**
 * Creates a sql connection to MySQL using the properties for
 * userid, password and server information.
 * @throws SQLException if a connection cannot be made.
 */
	public static void createConnection() throws SQLException {
		sConnection =  DriverManager
				.getConnection("jdbc:mysql://" + serverName + "/" + userName + "?user=" + userName + "&password=" + password);
	
		//Uncomment For Debugging - System.out.println("Connected to database");
	}


/**
 * Adds a new lot to the lot table.
 * @param lot represents the lot to be added.
 * @throws Exception is thrown if the lot cannot be added.
 */
	public void addLot(Lot lot) throws Exception {
		String sql = "insert into Lot values " + "(?, ?, ?, ?, null); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, lot.getCapacity());
			preparedStatement.setInt(2, lot.getFloors());
			preparedStatement.setString(3, lot.getLocation());
			preparedStatement.setString(4, lot.getLotName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Lot: " + e.getMessage());
		} 
    }
/**
 * Adds a new space to the space table.
 * @param space represents the new space being added.
 * @throws Exception is thrown if the space cannot be added.
 */
	public void addSpace(Space space) throws Exception {
		String sql = "insert into Space values " + "(?, ?, ?, null); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, space.getSpaceNumber());
			preparedStatement.setString(2, space.getSpaceType());
			preparedStatement.setString(3, space.getLotName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Space: " + e.getMessage());
		} 
	}
/**
 * Adds a new staff member to the staff table.
 * @param staff represents the new staff member to be added.
 * @throws Exception is thrown if the new staff member cannot be added.
 */
	public void addStaff(Staff staff) throws Exception {
		String sql = "insert into Staff values " + "(?, ?, ?, null); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getStaffNumber());
			preparedStatement.setInt(2, staff.getTelephoneExt());
			preparedStatement.setInt(3, staff.getVehicleLicenseNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add staff member: " 
							+ e.getMessage());
		} 
    }
	/**
	 * method updateStaff updates staff member's telephone extension number
	 * and/or vehicle license number.
	 * @param row represents the relation tuple.
	 * @param columnName represents the relation attribute.
	 * @param data represents the new/updated data.
	 * @throws Exception is thrown if change cannot be made.
	 */
    public void updateStaff(int row, String columnName, Object data) throws Exception {
		
		Staff staff = staffList.get(row);
		//String title = movie.getTitle();
        Integer telephoneExt = staff.getTelephoneExt();
        Integer vehicleLicenseNumber = staff.getVehicleLicenseNumber();
		String sql = "update Staff set " + columnName + " = ?  where telephoneExt= ? and vehicleLicenseNumber = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof Integer)
				preparedStatement.setString(1, (String) data);
			else if (data instanceof Integer)
				preparedStatement.setInt(1, (Integer) data);
			preparedStatement.setInt(2, telephoneExt);
			preparedStatement.setInt(3, vehicleLicenseNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update Staff: " + e.getMessage());
		} 
		
	}
    public void updateCoveredSpace(int row, String columnName, Object data) throws Exception {
    	
    }
    /**
     * method getVisitorSpaces returns all records of visitor space booking
     * requests.
     * @return returns all records of visitor space booking
     * requests.
     * @throws Exception if list retrieval cannot be made.
     */
    //returns the SpaceBooking object
    public List<SpaceBooking> getSpaceBooking() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select BookingId, spaceNumber, visitorLicense, "
						+ "dateOfVisit" + "from SpaceBooking";
		bookingList = new ArrayList<SpaceBooking>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer BookingId = rs.getInt("BookingId");
				Integer spaceNumber = rs.getInt("spaceNumber");
				Integer visitorLicense = rs.getInt("visitorLicense");
				String dateOfVisit = rs.getString("dateOfVisit");
				Integer staffNumber = rs.getInt("staffNumber");
				

			//	public SpaceBooking(Integer visitorLicense, String dateOfVisit, 
			//						Integer spaceNumber, Integer BookingId, Integer staffNumber) {
				SpaceBooking request = new SpaceBooking(visitorLicense, 
						dateOfVisit, spaceNumber, BookingId, staffNumber);
				bookingList.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of visitor space "
					+ "booking requests." + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return bookingList;
	}
    
    /**
	 * Modifies the staffSpace information corresponding to the index in the list.
	 * @param row index of the element in the list
	 * @param columnName attribute to modify
	 * @param data value to supply
	 * @throws Exception 
	 */
	
	public void updateStaffSpace(int row, String columnName, Object data) throws Exception {
			
			StaffSpace staffSpace = staffSpaceList.get(row);
			Integer spaceNumber = staffSpace.getSpaceNumber();
			Integer staffNumber = staffSpace.getStaffNumber();
			String sql = "update StaffSpace set " + columnName + " = ?  where spaceNumber= ? and staffNumber = ? ";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = sConnection.prepareStatement(sql);
				 if (data instanceof Integer)
					preparedStatement.setInt(1, spaceNumber);
					preparedStatement.setInt(2, staffNumber);
					//preparedStatement.setInt(3, year);
					preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Unable to add Movie: " + e.getMessage());
			} 
			
		}
	/**
	 * method getStaff returns the company's staff roster.
	 * @return returns the company's staff roster.
	 * @throws Exception
	 */
	public List<Staff> getStaff() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select staffNumber, telephoneExt, vehicleLicenseNumber "
						+ " from Staff";
		staffList = new ArrayList<Staff>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer staffNumber = rs.getInt("staffNumber");
				Integer telephoneExt = rs.getInt("telephoneExt");
				Integer vehicleLicenseNumber = rs.getInt("vehicleLicenseNumber");
			//	String dateOfVisit = rs.getString("dateOfVisit");
			//	Integer staffNumber = rs.getInt("staffNumber");
				

			//	public SpaceBooking(Integer visitorLicense, String dateOfVisit, 
			//						Integer spaceNumber, Integer BookingId, Integer staffNumber) {
				Staff staffMember = new Staff(staffNumber, telephoneExt, vehicleLicenseNumber);
				staffList.add(staffMember);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Staff Members "
					 + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return staffList;
	}
/**
 * method getLot returns the Lot relation.	
 * @return returns the lot relation
 * @throws Exception
 */
	public List<Lot> getLot() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select capacity, floors, location, lotName" + 
						" from Lot";
		lotList = new ArrayList<Lot>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer capacity = rs.getInt("capacity");
				Integer floors = rs.getInt("floors");
				String location = rs.getString("location");
				String lotName = rs.getString("lotName");
			
				Lot newLot = new Lot(capacity, floors, location, lotName);
				lotList.add(newLot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Parking Lots."
								+ e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return lotList;
	}
	public List<CoveredSpace> getCoveredSpace() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select spaceNumber, monthlyRate" 
						+ " from CoveredSpace";
		coveredSpaceList = new ArrayList<CoveredSpace>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer spaceNumber = rs.getInt("spaceNumber");
				Double monthlyRate = rs.getDouble("monthlyRate");
				CoveredSpace newCoveredSpace = new CoveredSpace(spaceNumber, 
																monthlyRate);
				coveredSpaceList.add(newCoveredSpace);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to recover the list of "
					+ "covered spaces");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return coveredSpaceList;
	}
	public List<UncoveredSpace> getUncoveredSpace() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select spaceNumber" 
						+ " from UncoveredSpace";
		uncoveredSpaceList = new ArrayList<UncoveredSpace>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer spaceNumber = rs.getInt("spaceNumber");
				//Double monthlyRate = rs.getDouble("monthlyRate");
				UncoveredSpace newUncoveredSpace = new UncoveredSpace(spaceNumber);
				uncoveredSpaceList.add(newUncoveredSpace);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to recover the list of "
					+ "uncovered spaces");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return uncoveredSpaceList;
	}
	public List<Space> getSpace() throws Exception{ 
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select spaceNumber, spaceType, lot Name from Space";
		spaceList = new ArrayList<Space>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer spaceNumber = rs.getInt("spaceNumber");
				String spaceType = rs.getString("spaceType");
				String lotName = rs.getString("lotName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve space.");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return spaceList;
		
		
	}
	public List <Space> getAvailableSpaces() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "SELECT *\n"
					+ "FROM Space\n"
					+ "WHERE spaceNumber IN (SELECT spaceNumber\n"
						+ "FROM Space\n"
						+ "MINUS\n"
						+ "SELECT spaceNumber\n"
						+ "FROM StaffSpace\n"
						+ "UNION\n"
						+ "SELECT spaceNumber\n"
						+ "FROM SpaceBooking)";
		availableSpaceList = new ArrayList<Space>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer spaceNumber = rs.getInt("spaceNumber");
				String spaceType = rs.getString("spaceType");
				String lotName = rs.getString("lotName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve spaces.");
		} finally {
			if (stmt !=null) {
				stmt.close();
			}
		}
		return availableSpaceList;
	}
}
