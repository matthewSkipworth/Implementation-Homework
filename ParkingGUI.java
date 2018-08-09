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

	private String[] columnNamesCoveredSpace = {"Space Number", "Monthly Rate"};
	private String[] columnNamesStaff = {"Staff Number", "Telephone Extensionm", "License Plate Number"};
	
	private Object[][] dataCoveredSpace;
	private JTable tableCoveredSpace;
	private JScrollPane scrollPaneCoveredSpace;
	private JLabel[] txfLabel = new JLabel[5];
	private JTextField[] txfField = new JTextField[5];
	private JButton btnAddMovie;

	private JButton btnStaffList, btnSpaceList, btnMakeLot,btnMakeSpace,
					btnMakeStaff,btnUpdateStaff,btnAssignSpot,btnReserveSpot,
					btnCheckSpace,btnViewCoveredSpace;

	private JPanel 	pnlButtons, pnlContentCoveredSpace, pnlContentStaff, pnlStaffList, pnlSpaceList, pnlMakeLot,
					pnlMakeSpace,pnlUpdateStaff,pnlAssignSpot,pnlReserveSpot,pnlCheckSpace
					,pnlViewCoveredSpace;
	
	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public ParkingGUI() {
		super("Covered Spaces");
		
		db = new ParkingDB();
		try {
			listCoveredSpace = db.getCoveredSpace();
			//Integer visitorLicense, String dateOfVisit, Integer spaceNumber, Integer BookingId, Integer staffNumber
			dataCoveredSpace = new Object[listCoveredSpace.size()][columnNamesCoveredSpace.length];
			for (int i=0; i<listCoveredSpace.size(); i++) {
				//private Integer spaceNumber; private Double monthlyRate;
				dataCoveredSpace[i][0] = listCoveredSpace.get(i).getSpaceNumber();
				dataCoveredSpace[i][1] = listCoveredSpace.get(i).getMonthlyRate();
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
		

		// //Staff list button
		// pnlStaffList = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		//covered space button
		pnlContentCoveredSpace = new JPanel();
		tableCoveredSpace = new JTable(dataCoveredSpace, columnNamesCoveredSpace);
		scrollPaneCoveredSpace = new JScrollPane(tableCoveredSpace);
		pnlContentCoveredSpace.add(scrollPaneCoveredSpace);
		tableCoveredSpace.getModel().addTableModelListener(this);

		//covered staff button
		// pnlContentStaff = new JPanel();
		// table = new JTable(data, columnNamesStaff);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);
		
		// //parking space list button
		// pnlSpaceList = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		// //make lot button
		// pnlMakeLot = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		// //make lot button
		// pnlMakeLot = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		// //make parking space button
		// pnlMakeSpace = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		// //update parking space button
		// pnlUpdateStaff = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		// //assign staff parking space button
		// pnlAssignSpot = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		// //reserve visitor parking space button
		// pnlReserveSpot = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		// //check to see if parking space button
		// pnlCheckSpace = new JPanel();
		// table = new JTable(data, columnNames);
		// scrollPane = new JScrollPane(table);
		// pnlContent.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		add(pnlContentCoveredSpace, BorderLayout.CENTER);
		
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

		// private JButton btnStaffList, btnSpaceList, btnMakeLot,btnMakeSpace,
		// 			btnMakeStaff,btnUpdateStaff,btnAssignSpot,btnReserveSpot,
		// 			btnCheckSpace;

		// pnlContentCoveredSpace = new JPanel();
		// tableCoveredSpace = new JTable(dataCoveredSpace, columnNamesCoveredSpace);
		// scrollPaneCoveredSpace = new JScrollPane(tableCoveredSpace);
		// pnlContentCoveredSpace.add(scrollPaneCoveredSpace);
		// table.getModel().addTableModelListener(this);
		if (e.getSource() == btnViewCoveredSpace) {
			try {
				listCoveredSpace = db.getCoveredSpace();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			dataCoveredSpace = new Object[listCoveredSpace.size()][columnNamesCoveredSpace.length];
			for (int i=0; i<listCoveredSpace.size(); i++) {
				dataCoveredSpace[i][0] = listCoveredSpace.get(i).getSpaceNumber();
				dataCoveredSpace[i][1] = listCoveredSpace.get(i).getMonthlyRate();
			}
			pnlContentCoveredSpace.removeAll();
			tableCoveredSpace = new JTable(dataCoveredSpace, columnNamesCoveredSpace);
			tableCoveredSpace.getModel().addTableModelListener(this);
			scrollPaneCoveredSpace = new JScrollPane(tableCoveredSpace);
			pnlContentCoveredSpace.add(scrollPaneCoveredSpace);
			pnlContentCoveredSpace.revalidate();
			this.repaint();
			
		} 
		// else if (e.getSource() == btnStaffList) {
		// 	pnlContent.removeAll();
		// 	try {
		// 		listStaff = db.getStaff();
		// 	} catch (Exception exception) {
		// 		JOptionPane.showMessageDialog(this, exception.getMessage());
		// 		return;
		// 	}
		// 	data = new Object[listStaff.size()][columnNamesCoveredSpace.length];
		// 	for (int i=0; i<listStaff.size(); i++) {
		// 		//private Integer staffNumber; private Integer telephoneExt; private Integer vehicleLicenseNumber;
		// 		data[i][0] = listStaff.get(i).getStaffNumber();
		// 		data[i][1] = listStaff.get(i).getTelephoneExt();
		// 		data[i][2] = listStaff.get(i).getVehicleLicenseNumber();
		// 	}
		// 	pnlContent.removeAll();
		// 	table = new JTable(data, columnNamesStaff);
		// 	table.getModel().addTableModelListener(this);
		// 	scrollPane = new JScrollPane(table);
		// 	pnlContent.add(scrollPane);
		// 	pnlContent.revalidate();
		// 	this.repaint();
		// }


		// else if (e.getSource() == btnCheckSpace) {
		// 	pnlContent.removeAll();
		// 	pnlContent.add(pnlCheckSpace);
		// 	pnlContent.revalidate();
		// 	this.repaint();
		// } else if (e.getSource() == btnMakeSpace) {
		// 	pnlContent.removeAll();
		// 	pnlContent.add(pnlMakeSpace);
		// 	pnlContent.revalidate();
		// 	this.repaint();
			
		// } 
		// else if (e.getSource() == btnTitleSearch) {
		// 	String title = txfTitle.getText();
		// 	if (title.length() > 0) {
		// 		try {
		// 			list = db.getMovies(title);
		// 		}
		// 		catch(Exception exception) {
		// 			JOptionPane.showMessageDialog(this, exception.getMessage());
		// 			return;
		// 		}
		// 		data = new Object[list.size()][columnNames.length];
		// 		for (int i=0; i<list.size(); i++) {
		// 			data[i][0] = list.get(i).getTitle();
		// 			data[i][1] = list.get(i).getYear();
		// 			data[i][2] = list.get(i).getLength();
		// 			data[i][3] = list.get(i).getGenre();
		// 			data[i][4] = list.get(i).getStudioName();
		// 		}
		// 		pnlContent.removeAll();
		// 		table = new JTable(data, columnNames);
		// 		table.getModel().addTableModelListener(this);
		// 		scrollPane = new JScrollPane(table);
		// 		pnlContent.add(scrollPane);
		// 		pnlContent.revalidate();
		// 		this.repaint();
		// 	}
		// } else if (e.getSource() == btnAddMovie) {
		// 	Movie movie = new Movie(txfField[0].getText(), Integer.parseInt(txfField[1].getText())
		// 			,Integer.parseInt(txfField[2].getText()), txfField[3].getText(), txfField[4].getText() );
		// 	try {
		// 		db.addMovie(movie);
		// 	} catch(Exception exception) {
		// 		JOptionPane.showMessageDialog(this, exception.getMessage());
		// 		return;
		// 	}
		// 	JOptionPane.showMessageDialog(null, "Added Successfully!");
		// 	for (int i=0; i<txfField.length; i++) {
		// 		txfField[i].setText("");
		// 	}
		// }
		
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
