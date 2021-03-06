import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A class that consists of the database operations to insert and update the Movie information.
 * @author  Jake McKenzie and Matthew Skipworth
 * @version 6 August 2018
 *
 */

public class ParkingDB {
	private static String userName = "jake314";
	private static String password = "Hersoyds";
	private static String serverName = "cssgate.insttech.washington.edu";
	private static Connection sConnection;
	/**
	 * List for storing the stack spaces
	 */
    private List<StaffSpace> staffSpaceList;
	/**
	 * List for storing the employees
	 */
	private List<Staff> staffList;
	/**
	 * List for storing the bookings for spaces
	 */
	private List<SpaceBooking> bookingList;
	/**
	 * List for storing the covered spaces
	 */
	private List<CoveredSpace> coveredSpaceList;
	/**
	 * List for storing the uncovered spaces
	 */
	private List<UncoveredSpace> uncoveredSpaceList;
	/**
	 * list for storing the spaces
	 */
	private List<Space> spaceList;
	/**
	 * list for storing the available spaces
	 */
	private List<Space> spaceListAvailable;


	/**
	 * Creates a sql connection to MySQL using the properties for
	 * userid, password and server information.
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		sConnection =  	DriverManager
						.getConnection(
						"jdbc:mysql://" + 
						serverName + 
						"/" + 
						userName + 
						"?user=" + 
						userName + 
						"&password=" + 
						password);
	}


	/**
	 * Adds a new lot to the lot table.
	 * @param lot 
	 * @throws Exception 
	 */
	public void addLot(Lot lot) throws Exception {
		String sql = "INSERT INTO Lot VALUES\n" + "\t(?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setString(1, lot.getLotName());
			preparedStatement.setString(2, lot.getLocation());
			preparedStatement.setInt(3, lot.getCapacity());
			preparedStatement.setInt(4, lot.getFloors());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Lot: " + e.getMessage());
		} 
    }
    /**
	 * Adds a new space to the space table.
	 * @param space 
	 * @throws Exception 
	 */
	public void addSpace(Space space) throws Exception {
		String sql = "INSERT INTO `Space` VALUES\n" + "\t(?, ?, ?); ";

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
	 * Adds a new space to the space table.
	 * @param space 
	 * @throws Exception 
	 */
	public void addSpaceBooking(SpaceBooking sb) throws Exception {
		String sql = "INSERT INTO SpaceBooking VALUES\n" + "\t(?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, sb.getBookingId());
			preparedStatement.setInt(2, sb.getSpaceNumber());
			preparedStatement.setInt(3, sb.getStaffNumber());
			preparedStatement.setInt(4, sb.getVisitorLicense());
			preparedStatement.setString(5, sb.getDateOfVisit());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Space Booking: " + e.getMessage());
		} 
	}

    /**
	 * Modifies a staff member's telephone and license plate number.
	 * @param staff 
	 * @throws Exception 
	 */
	public void changeStaff(Staff staff) throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String sql = "UPDATE Staff"+"\n"+
		"SET telephoneExt = ?"+
		", vehicleLicenseNumber= ?\n"+
		"WHERE staffNumber = ?;";
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getTelephoneExt());
			preparedStatement.setInt(2, staff.getVehicleLicenseNumber());
			preparedStatement.setInt(3, staff.getStaffNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to update Staff: " + e.getMessage());
		} 
	}

	/**
	 * Adds a new staff member to the staff table.
	 * @param staff 
	 * @throws Exception 
	 */
	public void addStaff(Staff staff) throws Exception {
		String sql = "INSERT INTO Staff VALUES\n" + "\t(?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getStaffNumber());
			preparedStatement.setInt(2, staff.getTelephoneExt());
			preparedStatement.setInt(3, staff.getVehicleLicenseNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add staff member: " + e.getMessage());
		} 
	}
	
	/**
	 * Adds a new staff member to the staff table.
	 * @param staffspace
	 * @throws Exception 
	 */
	public void addStaffSpace(StaffSpace staffspace) throws Exception {
		String sql = "INSERT INTO StaffSpace VALUES\n" + "\t(?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			preparedStatement.setInt(1, staffspace.getStaffNumber());
			preparedStatement.setInt(2, staffspace.getSpaceNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to assign a staff space: " + e.getMessage());
		} 
	}


	/**
	 * method updateStaff updates staff member's telephone extension number
	 * and/or vehicle license number.
	 * @param row represents the relation tuple.
	 * @param columnName represents the relation attribute.
	 * @param data represents the new/updated data.
	 * @throws Exception
	 */
    public void updateStaff(int row, String columnName, Object data) throws Exception {
		
		Staff staff = staffList.get(row);
        Integer telephoneExt = staff.getTelephoneExt();
        Integer vehicleLicenseNumber = staff.getVehicleLicenseNumber();
		String sql = "UPDATE Staff SET " + columnName + " = ?  WHERE telephoneExt= ? AND vehicleLicenseNumber = ? ";
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
			throw new Exception("Unable to add an employee: " + e.getMessage());
		} 
		
	}
    /**
     * method getSpaceBookings returns all records of visitor space booking
     * requests.
     * @return returns all records of visitor space booking
     * requests.
     * @throws Exception
     */
    //returns the SpaceBooking object
    // public List<SpaceBooking> getSpaceBooking() throws Exception {
	// 	if (sConnection == null) {
	// 		createConnection();
	// 	}
	// 	Statement stmt = null;
	// 	String query = "SELECT * " + "FROM SpaceBooking";
	// 	bookingList = new ArrayList<SpaceBooking>();
	// 	try {
	// 		stmt = sConnection.createStatement();
	// 		ResultSet rs = stmt.executeQuery(query);
	// 		while (rs.next()) {
	// 			Integer BookingId = rs.getInt("BookingId");
	// 			Integer spaceNumber = rs.getInt("spaceNumber");
	// 			Integer visitorLicense = rs.getInt("visitorLicense");
	// 			String dateOfVisit = rs.getString("dateOfVisit");
	// 			Integer staffNumber = rs.getInt("staffNumber");
				
	// 			SpaceBooking request = new SpaceBooking(visitorLicense, 
	// 					dateOfVisit, spaceNumber, BookingId, staffNumber);
	// 			bookingList.add(request);
	// 		}
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 		throw new Exception("Unable to retrieve list of visitor space "
	// 				+ "booking requests." + e.getMessage());
	// 	} finally {
	// 		if (stmt != null) {
	// 			stmt.close();
	// 		}
	// 	}
	// 	return bookingList;
	// }
    
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
			String sql = "UPDATE StaffSpace SET " + columnName + " = ?  WHERE pSpaceNumber= ? AND staffNum = ? ";
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
		String query = "SELECT * FROM Staff";
		staffList = new ArrayList<Staff>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer staffNumber = rs.getInt("staffNumber");
				Integer telephoneExt = rs.getInt("telephoneExt");
				Integer vehicleLicenseNumber = rs.getInt("vehicleLicenseNumber");
				Staff staff = new Staff(staffNumber, telephoneExt, vehicleLicenseNumber);
				staffList.add(staff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Staff Members: "
					 + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return staffList;
	}
	/**
	 * method getSpace returns the records for spaces.
	 * @return returns the company's space history.
	 * @throws Exception
	 */
	public List<Space> getSpace() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "SELECT * "+"FROM Space";
		
		spaceList = new ArrayList<Space>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer spaceNumber = rs.getInt("spaceNumber");
				String spaceType = rs.getString("spaceType");
				String lotName = rs.getString("pLotName");
				Space space = new Space(spaceNumber, spaceType, lotName);
				spaceList.add(space);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Spaces: "
					 + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return spaceList;
	}
	/**
	 * method getCoveredSpace returns the records for covered spaces.
	 * @return returns the company's covered space history.
	 * @throws Exception
	 */
	public List<CoveredSpace> getCoveredSpace() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "SELECT * FROM CoveredSpace";
		coveredSpaceList = new ArrayList<CoveredSpace>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer spaceNumber = rs.getInt("coveredSpaceNumber");
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
	/**
	 * method getUncoveredSpace returns the records for uncovered spaces.
	 * @return returns the company's uncovered space history.
	 * @throws Exception
	 */
	public List<UncoveredSpace> getUncoveredSpace() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "SELECT * FROM UncoveredSpace";
		uncoveredSpaceList = new ArrayList<UncoveredSpace>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Integer spaceNumber = rs.getInt("uncoveredSpaceNumber");
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
	/**
	 * method getAvailableSpace return only available spaces.
	 * @return returns the company's space availability.
	 * @throws Exception
	 */
	public List<Space> getAvailableSpace() throws Exception {
		if (sConnection == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "SELECT *\n"+ 
		"FROM `Space`\n"+
		"WHERE spaceNumber IN (\n"+
		"	SELECT spaceNumber\n"+ 
		"    FROM `Space`\n"+
		"    WHERE spaceNumber\n"+
		"    NOT IN\n"+
		"		(SELECT pSpaceNumber\n"+
		"		FROM StaffSpace\n"+
		"		UNION ALL\n"+
		"		SELECT spaceNum\n"+
		"		FROM SpaceBooking)\n"+
		");\n";

		
		
		spaceListAvailable = new ArrayList<Space>();
		try {
			stmt = sConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Integer spaceNumber = rs.getInt("spaceNumber");
				String spaceType = rs.getString("spaceType");
				String lotName = rs.getString("pLotName");
				Space space = new Space(spaceNumber, spaceType, lotName);
				spaceListAvailable.add(space);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to retrieve list of Spaces: "
					 + e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return spaceListAvailable;
	}

	/**
<<<<<<< HEAD
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
						"from Lot";
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
=======
	 * Filters the movie list to find the given title. Returns a list with the
	 * movie objects that match the title provided.
	 * @param title
	 * @return list of movies that contain the title.
	 */
	// public List<CoveredSpace> getCoveredSpace(String title) throws Exception {
	// 	List<CoveredSpace> filterList = new ArrayList<CoveredSpace>();
	// 	try {
	// 		list = getCoveredSpace();
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 		throw new Exception("Unable to retrieve covered spaces: " + e.getMessage());
	// 	}
	// 	for (CoveredSpace cs : list) {
	// 		if (cs.getSpaceNumber().toLowerCase().contains(title.toLowerCase())) {
	// 			filterList.add(cs);
	// 		}
	// 	}
	// 	return filterList;
	// }
>>>>>>> jake-branch
}
