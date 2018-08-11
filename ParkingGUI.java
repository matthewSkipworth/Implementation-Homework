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
	/**
	 * I don't know what this does but it was included in our assignment so I included it.
	 * The internet does not appear to have a good answer either.
	 * 
	 * I randomly generated my own that followed the format as to make the ID unique.
	 */
	private static final long serialVersionUID = 7295690032713970188L;
	/**
	 * The parking database object.
	 */
	private ParkingDB db;
	private List<CoveredSpace> listCoveredSpace;
	private List<Space> listAvailableSpaces;
	private List<Staff> listStaff;
	private List<Space> listSpace;

	private String[] columnNamesCovered = {"Space Number", "Monthly Rate"};
	private String[] columnNamesStaff = {"Staff Number", "Telephone Extensionm", "License Plate Number"};
	private String[] columnNamesSpace = {"Space Number", "Space Type", "Lot Name"};
	
	private Object[][] data;

	private JTable table;
	
	private JScrollPane scrollPane;
	
	private JLabel[] txfLabelLot = new JLabel[4];
	private JTextField[] txfFieldLot = new JTextField[4];
	private JLabel[] txfLabelSpace = new JLabel[3];
	private JTextField[] txfFieldSpace = new JTextField[3];
	private JLabel[] txfLabelStaff = new JLabel[3];
	private JTextField[] txfFieldStaff = new JTextField[3];
	private JLabel[] txfLabelUpdate = new JLabel[3];
	private JTextField[] txfFieldUpdate = new JTextField[3];
	private JLabel[] txfLabelSpaceBooking = new JLabel[5];
	private JTextField[] txfFieldSpaceBooking = new JTextField[5];
	private JLabel[] txfLabelStaffSpace = new JLabel[2];
	private JTextField[] txfFieldStaffSpace = new JTextField[2];

	private JButton btnAddMovie,btnStaffList, btnSpaceList, btnMakeLot,
					btnMakeStaff,btnUpdateStaff,btnAssignSpot,btnReserveSpot,
					btnAvailableSpaces,btnViewCoveredSpace,btnAdd,btnAddLot,
					btnMakeSpace,btnAddSpace,btnAddStaff, btnChangeStaff,
					btnMakeSpaceBooking,btnAddSpaceBooking,btnMakeStaffSpace,
					btnAddStaffSpace;

	private JPanel 	pnlButtons, pnlContent, pnlStaffList, pnlSpaceList, pnlMakeLot,
					pnlMakeSpace,pnlUpdateStaff,pnlChangedStaff,pnlAssignSpot,
					pnlReserveSpot,pnlCheckSpace,pnlViewCoveredSpace,pnlAddLot,
					pnlAddSpace,pnlAddStaff,pnlChangeStaff,pnlMakeSpaceBooking,
					pnlAddSpaceBooking, pnlMakeStaffSpace, pnlAddStaffSpace;
	
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
		
		btnAddLot = new JButton("Add Lot");
		btnAddLot.addActionListener(this);

		btnAddSpace = new JButton("Add Space");
		btnAddSpace.addActionListener(this);

		btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addActionListener(this);

		btnUpdateStaff = new JButton("Update Staff");
		btnUpdateStaff.addActionListener(this);

		btnAddStaffSpace = new JButton("Assign Staff Spot");
		btnAddStaffSpace.addActionListener(this);

		btnAddSpaceBooking = new JButton("Reserve Visitor Spot");
		btnAddSpaceBooking.addActionListener(this);

		btnAvailableSpaces = new JButton("Available Spaces");
		btnAvailableSpaces.addActionListener(this);
		
		pnlButtons.add(btnStaffList);
		pnlButtons.add(btnSpaceList);
		pnlButtons.add(btnViewCoveredSpace);
		pnlButtons.add(btnAddLot);
		pnlButtons.add(btnAddSpace);
		pnlButtons.add(btnAddStaff);
		pnlButtons.add(btnUpdateStaff);
		pnlButtons.add(btnAddStaffSpace);
		pnlButtons.add(btnAddSpaceBooking);
		pnlButtons.add(btnAvailableSpaces);
		add(pnlButtons, BorderLayout.NORTH);
		
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
		String labelNamesLot[] = { "Enter Lot Name: ", 
		"Enter Location: ",
		"Enter Capacity: ",
		"Enter Floors: "};
		for (int i=0; i<labelNamesLot.length; i++) {
			JPanel panel = new JPanel();
			txfLabelLot[i] = new JLabel(labelNamesLot[i]);
			txfFieldLot[i] = new JTextField(25);
			panel.add(txfLabelLot[i]);
			panel.add(txfFieldLot[i]);
			pnlAddLot.add(panel);
		}
		JPanel panel = new JPanel();
		btnMakeLot = new JButton("Add Lot");
		btnMakeLot.addActionListener(this);
		panel.add(btnMakeLot);
		pnlAddLot.add(panel);
		
		add(pnlContent, BorderLayout.CENTER);
		
		//Add Space Panel
		pnlAddSpace = new JPanel();
		pnlAddSpace.setLayout(new GridLayout(3, 0));
		String labelNamesSpace[] = { "Enter Space Number: ", 
		"Enter Space Type: ",
		"Enter Lot Name: "};
		for (int i=0; i<labelNamesSpace.length; i++) {
			panel = new JPanel();
			txfLabelSpace[i] = new JLabel(labelNamesSpace[i]);
			txfFieldSpace[i] = new JTextField(25);
			panel.add(txfLabelSpace[i]);
			panel.add(txfFieldSpace[i]);
			pnlAddSpace.add(panel);
		}
		panel = new JPanel();
		btnMakeSpace = new JButton("Add Space");
		btnMakeSpace.addActionListener(this);
		panel.add(btnMakeSpace);
		pnlAddSpace.add(panel);
		
		add(pnlContent, BorderLayout.CENTER);

		//Add Space Booking Panel
		pnlAddSpaceBooking = new JPanel();
		pnlAddSpaceBooking.setLayout(new GridLayout(5, 0));
		String labelNamesSpaceBooking[] = { "Enter Booking ID: ", 
		"Enter Space Number: ", "Enter Staff Number: ",
		"Enter Visitor License: ", "Enter Date of Visit: "};
		for (int i=0; i<labelNamesSpaceBooking.length; i++) {
			panel = new JPanel();
			
			txfLabelSpaceBooking[i] = new JLabel(labelNamesSpaceBooking[i]);
			txfFieldSpaceBooking[i] = new JTextField(25);
			panel.add(txfLabelSpaceBooking[i]);
			panel.add(txfFieldSpaceBooking[i]);
			pnlAddSpaceBooking.add(panel);
		}
		panel = new JPanel();
		btnMakeSpaceBooking = new JButton("Reserve Visitor Spot");
		btnMakeSpaceBooking.addActionListener(this);
		panel.add(btnMakeSpaceBooking);
		pnlAddSpaceBooking.add(panel);
		
		add(pnlContent, BorderLayout.CENTER);

		//Add Staff Panel
		pnlAddStaff = new JPanel();
		pnlAddStaff.setLayout(new GridLayout(3, 0));
		String labelNamesStaff[] = { "Enter Staff Number: ", "Enter Telephone Extension: ","Enter License Plate: "};
		for (int i=0; i<labelNamesStaff.length; i++) {
			panel = new JPanel();
			txfLabelStaff[i] = new JLabel(labelNamesStaff[i]);
			txfFieldStaff[i] = new JTextField(25);
			panel.add(txfLabelStaff[i]);
			panel.add(txfFieldStaff[i]);
			pnlAddStaff.add(panel);
		}
		panel = new JPanel();
		btnMakeStaff = new JButton("Add Staff");
		btnMakeStaff.addActionListener(this);
		panel.add(btnMakeStaff);
		pnlAddStaff.add(panel);
		
		add(pnlContent, BorderLayout.CENTER);

		//Add Staff Space Panel
		pnlAddStaffSpace = new JPanel();
		pnlAddStaffSpace.setLayout(new GridLayout(2, 0));
		String labelNamesStaffSpace[] = { "Enter Staff Number: ", 
		"Enter Space Number: "};
		for (int i=0; i<labelNamesStaffSpace.length; i++) {
			panel = new JPanel();
			txfLabelStaffSpace[i] = new JLabel(labelNamesStaffSpace[i]);
			txfFieldStaffSpace[i] = new JTextField(25);
			panel.add(txfLabelStaffSpace[i]);
			panel.add(txfFieldStaffSpace[i]);
			pnlAddStaffSpace.add(panel);
		}
		panel = new JPanel();
		btnMakeStaffSpace = new JButton("Assign Staff Spot");
		btnMakeStaffSpace.addActionListener(this);
		panel.add(btnMakeStaffSpace);
		pnlAddStaffSpace.add(panel);
		
		add(pnlContent, BorderLayout.CENTER);

		//Update Staff Panel
		pnlChangeStaff = new JPanel();
		pnlChangeStaff.setLayout(new GridLayout(3, 0));
		String labelNamesUpdate[] = {"Enter Staff Number: ", "Enter New Telephone Extension: ", "Enter New License Plate: "};
		for (int i=0; i<labelNamesUpdate.length; i++) {
			panel = new JPanel();
			txfLabelUpdate[i] = new JLabel(labelNamesUpdate[i]);
			txfFieldUpdate[i] = new JTextField(25);
			panel.add(txfLabelUpdate[i]);
			panel.add(txfFieldUpdate[i]);
			pnlChangeStaff.add(panel);
		}
		panel = new JPanel();
		btnChangeStaff = new JButton("Update Staff");
		btnChangeStaff.addActionListener(this);
		panel.add(btnChangeStaff);
		pnlChangeStaff.add(panel);
		
		add(pnlContent, BorderLayout.CENTER);
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
			
		} else if (e.getSource() == btnAvailableSpaces) {
			try {
				listAvailableSpaces = db.getAvailableSpace();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			data = new Object[listAvailableSpaces.size()][columnNamesSpace.length];
			for (int i=0; i<listAvailableSpaces.size(); i++) {
				data[i][0] = listAvailableSpaces.get(i).getSpaceNumber();
				data[i][1] = listAvailableSpaces.get(i).getSpaceType();
				data[i][2] = listAvailableSpaces.get(i).getLotName();
			}
			pnlContent.removeAll();
			table = new JTable(data, columnNamesSpace);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();
			
		}  else if (e.getSource() == btnChangeStaff) {
			Staff staff = new Staff(Integer.parseInt(txfFieldUpdate[0].getText()),
			Integer.parseInt(txfFieldUpdate[1].getText()),
			Integer.parseInt(txfFieldUpdate[2].getText()));
			try {
				db.changeStaff(staff);
			} catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Change Successful!");
			
		}  else if (e.getSource() == btnUpdateStaff) {
			pnlContent.removeAll();
			pnlContent.add(pnlChangeStaff);
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

			Lot lot = new Lot(txfFieldLot[0].getText(), txfFieldLot[1].getText(), 
			Integer.parseInt(txfFieldLot[2].getText().trim()), Integer.parseInt(txfFieldLot[3].getText()));
			try {
				db.addLot(lot);
			} catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			
		} else if (e.getSource() == btnAddLot) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddLot);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnMakeSpace) {

			Space space = new Space(Integer.parseInt(txfFieldSpace[0].getText()), txfFieldSpace[1].getText(), 
							txfFieldSpace[2].getText());
			try {
				db.addSpace(space);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			
		} else if (e.getSource() == btnAddSpace) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddSpace);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnMakeSpaceBooking) {
			
			SpaceBooking sb = new SpaceBooking(Integer.parseInt(txfFieldSpaceBooking[0].getText()),
			Integer.parseInt(txfFieldSpaceBooking[1].getText()),
			Integer.parseInt(txfFieldSpaceBooking[2].getText()),
			Integer.parseInt(txfFieldSpaceBooking[3].getText()),
			txfFieldSpaceBooking[4].getText());
			try {
				db.addSpaceBooking(sb);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			
		} else if (e.getSource() == btnAddSpaceBooking) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddSpaceBooking);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnMakeStaffSpace) {
			
			StaffSpace ss = new StaffSpace(Integer.parseInt(txfFieldStaffSpace[0].getText()),
			Integer.parseInt(txfFieldStaffSpace[1].getText()));
			try {
				db.addStaffSpace(ss);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			
		} else if (e.getSource() == btnAddStaffSpace) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddStaffSpace);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnMakeStaff) {
			
			Staff staff = new Staff(Integer.parseInt(txfFieldStaff[0].getText()),
			Integer.parseInt(txfFieldStaff[1].getText()),
			Integer.parseInt(txfFieldStaff[2].getText()));
			try {
				db.addStaff(staff);
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			
		} else if (e.getSource() == btnAddStaff) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddStaff);
			pnlContent.revalidate();
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
        try {
        	  db.updateStaff(row, columnName, data);
		}
		catch(Exception exception) {
			JOptionPane.showMessageDialog(this, exception.getMessage());
			return;
		}
       
		
	}

}
