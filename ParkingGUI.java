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
	private List<Space> listSpace;

	private String[] columnNamesCovered = {"Space Number", "Monthly Rate"};
	private String[] columnNamesStaff = {"Staff Number", "Telephone Extensionm", "License Plate Number"};
	private String[] columnNamesSpace = {"Space Number", "Space Type", "Lot Name"};
	
	private Object[][] data;

	private JTable table;
	
	private JScrollPane scrollPane;
	
	private JLabel[] txfLabel = new JLabel[5];
	private JTextField[] txfField = new JTextField[5];
	private JButton btnAddMovie;

	private JButton btnStaffList, btnSpaceList, btnMakeLot,btnMakeSpace,
					btnMakeStaff,btnUpdateStaff,btnAssignSpot,btnReserveSpot,
					btnCheckSpace,btnViewCoveredSpace;

	private JPanel 	pnlButtons, pnlContent, pnlStaffList, pnlSpaceList, pnlMakeLot,
					pnlMakeSpace,pnlUpdateStaff,pnlAssignSpot,pnlReserveSpot,pnlCheckSpace
					,pnlViewCoveredSpace,pnlAddLot;
	
	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public ParkingGUI() {
		super("Staff List");
		db = new ParkingDB();
		try {
			listStaff = db.getStaff();
			data = new Object[listStaff.size()][columnNamesStaff.length];
			for (int i=0; i<listStaff.size(); i++) {
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

		// // //Staff list button
		// pnlStaffList = new JPanel();
		// table = new JTable(data, columnNamesStaff);
		// scrollPane = new JScrollPane(table);
		// pnlStaffList.add(scrollPane);
		// table.getModel().addTableModelListener(this);

		//covered space button
		pnlContent = new JPanel();
		table = new JTable(data, columnNamesStaff);
		scrollPane = new JScrollPane(table);
		pnlContent.add(scrollPane);
		table.getModel().addTableModelListener(this);

		add(pnlContent, BorderLayout.CENTER);

		//Add Lot Panel
		pnlAddLot = new JPanel();
		pnlAddLot.setLayout(new GridLayout(4, 0));
		//private Integer capacity,floors; ivate String location, lotName;
		String labelNames[] = {"Enter Capacity: ", "Enter Floors: ", "Enter Location: ", "Enter Lot Name: "};
		for (int i=0; i<labelNames.length; i++) {
			JPanel panel = new JPanel();
			txfLabel[i] = new JLabel(labelNames[i]);
			txfField[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfField[i]);
			pnlAddLot.add(panel);
		}
		JPanel panel = new JPanel();
		btnMakeLot = new JButton("Add Lot");
		btnMakeLot.addActionListener(this);
		panel.add(btnMakeLot);
		pnlAddLot.add(panel);
		
		add(pnlAddLot, BorderLayout.CENTER);		
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
		if (e.getSource() == btnStaffList) {
			
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
			pnlContent.removeAll();
			table = new JTable(data, columnNamesStaff);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnViewCoveredSpace) {
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
			pnlContent.removeAll();
			table = new JTable(data, columnNamesCovered);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnSpaceList) {
			try {
				listSpace = db.getSpace();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			data = new Object[listSpace.size()][columnNamesSpace.length];
			for (int i=0; i<listSpace.size(); i++) {
				data[i][0] = listSpace.get(i).getSpaceNumber();
				data[i][1] = listSpace.get(i).getSpaceType();
				data[i][2] = listSpace.get(i).getLotName();
			}
			pnlContent.removeAll();
			table = new JTable(data, columnNamesSpace);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnMakeLot) {
			
			Lot lot = new Lot(Integer.parseInt(txfField[0].getText()), Integer.parseInt(txfField[1].getText())
					,txfField[2].getText(), txfField[3].getText() );
			try {
				db.addLot(lot);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			for (int i=0; i<txfField.length; i++) {
				txfField[i].setText("");
			}
			
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
        try {
        	//  db.updateMovie(row, columnName, data);;
		}
		catch(Exception exception) {
			JOptionPane.showMessageDialog(this, exception.getMessage());
			return;
		}
       
		
	}

}
