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
 * @author mmuppa
 *
 */

public class ParkingDB {
	private static String userName = "jake314";
	private static String password = "Hersoyds";
	private static String serverName = "cssgate.insttech.washington.edu";
	private static Connection sConnection;
    private List<Lot> lotList;
    private List<Space> spaceList;


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
    public void updateStaff(int row, String columnName, Object data) throws Exception {
		
		Staff staff = list.get(row);
		//String title = movie.getTitle();
        Integer telephoneExt = staff.getTelephoneExt();
        Integer vehicleLicenseNumber = staff.getVehicleLicenseNumber();
        int year = movie.getYear();
		String sql = "update Movies set " + columnName + " = ?  where title= ? and year = ? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = sConnection.prepareStatement(sql);
			if (data instanceof String)
				preparedStatement.setString(1, (String) data);
			else if (data instanceof Integer)
				preparedStatement.setInt(1, (Integer) data);
			preparedStatement.setString(2, title);
			preparedStatement.setInt(3, year);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to add Movie: " + e.getMessage());
		} 
		
	}
}
