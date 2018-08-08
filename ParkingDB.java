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
 * @author Matthew Skipworth and Jake McKenzie
 * @version 6 August 2018
 *
 */

public class ParkingDB {
	private static String userName = "jake314";
	private static String password = "Hersoyds";
	private static String serverName = "cssgate.insttech.washington.edu";
	private static Connection sConnection;
    private List<Lot> lotList;
    private List<Space> spaceList;
    private List<StaffSpace> staffSpaceList;
    private List<Staff> staffList;
    private List<SpaceBooking> bookingList;


	/**
	 * Creates a sql connection to MySQL using the properties for
	 * userid, password and server information.
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		sConnection =  DriverManager
				.getConnection("jdbc:mysql://" + serverName + "/" + userName + "?user=" + userName + "&password=" + password);
	
		//Uncomment For Debugging - System.out.println("Connected to database");
	}


	/**
	 * Adds a new lot to the lot table.
	 * @param lot 
	 * @throws Exception 
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
	 * @param space 
	 * @throws Exception 
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
	 * @param staff 
	 * @throws Exception 
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
			throw new Exception("Unable to add staff member: " + e.getMessage());
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
			throw new Exception("Unable to add Movie: " + e.getMessage());
		} 
		
	}
    /**
     * method getVisitorSpaces returns all records of visitor space booking
     * requests.
     * @return returns all records of visitor space booking
     * requests.
     * @throws Exception
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
		String query = "select staffNumber, telephoneExt, vehicleLicenseNumber"
						+ "from SpaceBooking";
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
}
