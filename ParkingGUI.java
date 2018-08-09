import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * A user interface to view the movies, add a new movie and to update an existing movie.
 * The list is a table with all the movie information in it. The TableModelListener listens to
 * any changes to the cells to modify the values for reach movie.
 * 
 * This was compiled in the command line with the following commands:
 * 
 * javac -cp .:parking.jar *.java -Xlint:unchecked
 * 
 * @author Matthew Skipworth and Jake McKenzie
 * @version 6 August 2018
 */
public class ParkingGUI extends JFrame implements ActionListener, TableModelListener {
	private static final long serialVersionUID = 7295690032713970188L;

	private ParkingDB db;
	
	private List<CoveredSpace> listCoveredSpace;
	private List<Staff> listStaff;

	private String[] columnNamesCovered = {"Space Number", "Monthly Rate"};
	private String[] columnNamesStaff = {"Staff Number", "Telephone Extensionm", "License Plate Number"};
	
	private Object[][] data;

	private JTable table;
	
	private JScrollPane scrollPane;
	
	private JLabel[] txfLabel = new JLabel[5];
	private JTextField[] txfField = new JTextField[5];
	private JButton btnAddMovie;

	private JButton btnStaffList, btnSpaceList, btnMakeLot,btnMakeSpace,
					btnMakeStaff,btnUpdateStaff,btnAssignSpot,btnReserveSpot,
					btnCheckSpace,btnViewCoveredSpace;

	private JPanel 	pnlButtons, pnlCoveredSpace, pnlStaffList, pnlSpaceList, pnlMakeLot,
					pnlMakeSpace,pnlUpdateStaff,pnlAssignSpot,pnlReserveSpot,pnlCheckSpace
					,pnlViewCoveredSpace;
	
	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public ParkingGUI() {
		super("Staff List");
		
		db = new ParkingDB();
		try {
			listStaff = db.getStaff();
			//Integer visitorLicense, String dateOfVisit, Integer spaceNumber, Integer BookingId, Integer staffNumber
			data = new Object[listStaff.size()][columnNamesStaff.length];
			for (int i=0; i<listStaff.size(); i++) {
				//private Integer spaceNumber; private Double monthlyRate;
				data[i][0] = listStaff.get(i).getStaffNumber();
				data[i][1] = listStaff.get(i).getTelephoneExt();
				data[i][2] = listStaff.get(i).getVehicleLicenseNumber();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Error: " + e.getMessage());
			return;
		}
		createComponents();
		setVisible(true);
		setSize(1200, 500);
	}

	
    
	/**
	 * Creates panels for Movie list, search, add and adds the corresponding 
	 * components to each panel.
	 */
	private void createComponents() {
		pnlButtons = new JPanel();

		btnStaffList = new JButton("Staff List");
		btnStaffList.addActionListener(this);
		
		btnViewCoveredSpace = new JButton("View Covered Space");
		btnViewCoveredSpace.addActionListener(this);
		
		btnSpaceList = new JButton("Spaces List");
		btnSpaceList.addActionListener(this);
		
		btnMakeLot = new JButton("Add Lot");
		btnMakeLot.addActionListener(this);

		btnMakeSpace = new JButton("Add Space");
		btnMakeSpace.addActionListener(this);

		btnMakeStaff = new JButton("Add Staff");
		btnMakeStaff.addActionListener(this);

		btnUpdateStaff = new JButton("Update Staff");
		btnUpdateStaff.addActionListener(this);

		btnAssignSpot = new JButton("Assign Staff Spot");
		btnAssignSpot.addActionListener(this);

		btnReserveSpot = new JButton("Reserve Visitor Spot");
		btnReserveSpot.addActionListener(this);

		btnCheckSpace = new JButton("Check Spot");
		btnCheckSpace.addActionListener(this);
		
		pnlButtons.add(btnStaffList);
		pnlButtons.add(btnSpaceList);
		pnlButtons.add(btnViewCoveredSpace);
		pnlButtons.add(btnMakeLot);
		pnlButtons.add(btnMakeSpace);
		pnlButtons.add(btnMakeStaff);
		pnlButtons.add(btnUpdateStaff);
		pnlButtons.add(btnAssignSpot);
		pnlButtons.add(btnReserveSpot);
		pnlButtons.add(btnCheckSpace);
		add(pnlButtons, BorderLayout.NORTH);
		
		
		// //Staff Panel
		// pnlStaff = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlStaff.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		// //Staff list button
		pnlStaffList = new JPanel();
		table = new JTable(data, columnNamesStaff);
		scrollPane = new JScrollPane(table);
		pnlStaffList.add(scrollPane);
		table.getModel().addTableModelListener(this);

		//covered space button
		pnlCoveredSpace = new JPanel();
		table = new JTable(data, columnNamesCovered);
		scrollPane = new JScrollPane(table);
		pnlCoveredSpace.add(scrollPane);
		table.getModel().addTableModelListener(this);


		add(pnlCoveredSpace, BorderLayout.CENTER);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParkingGUI parkingGUI = new ParkingGUI();
		parkingGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Event handling to change the panels when different tabs are clicked,
	 * add and search buttons are clicked on the corresponding add and search panels.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnViewCoveredSpace) {
			try {
				listCoveredSpace = db.getCoveredSpace();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			data = new Object[listCoveredSpace.size()][columnNamesCovered.length];
			for (int i=0; i<listCoveredSpace.size(); i++) {
				data[i][0] = listCoveredSpace.get(i).getSpaceNumber();
				data[i][1] = listCoveredSpace.get(i).getMonthlyRate();
			}
			pnlCoveredSpace.removeAll();
			table = new JTable(data, columnNamesCovered);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlCoveredSpace.add(scrollPane);
			pnlCoveredSpace.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnStaffList) {
			try {
				listStaff = db.getStaff();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			data = new Object[listStaff.size()][columnNamesStaff.length];
			for (int i=0; i<listStaff.size(); i++) {
				//private Integer staffNumber; private Integer telephoneExt; private Integer vehicleLicenseNumber;
				data[i][0] = listStaff.get(i).getStaffNumber();
				data[i][1] = listStaff.get(i).getTelephoneExt();
				data[i][2] = listStaff.get(i).getVehicleLicenseNumber();
			}
			pnlCoveredSpace.removeAll();
			table = new JTable(data, columnNamesStaff);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlCoveredSpace.add(scrollPane);
			pnlCoveredSpace.revalidate();
			this.repaint();
		}


	}

	/**
	 * Event handling for any cell being changed in the table.
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        // try {
        // 	 db.updateMovie(row, columnName, data);;
		// }
		// catch(Exception exception) {
		// 	JOptionPane.showMessageDialog(this, exception.getMessage());
		// 	return;
		// }
       
		
	}

}
