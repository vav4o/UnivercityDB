package ha;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewFrame extends JFrame{
	Connection conn=null;
	PreparedStatement state=null;
	ResultSet result;
	int id=-1;

	JPanel panelStudents=new JPanel();
	JPanel panelStudents1=new JPanel();
	JPanel panelStudents2=new JPanel();
	JPanel panelCities=new JPanel();
	JPanel panelCities1=new JPanel();
	JPanel panelCities2=new JPanel();
	JPanel panelCities3=new JPanel();
	JPanel panelMajors=new JPanel();
	JPanel panelMajors1=new JPanel();
	JPanel panelMajors2=new JPanel();
	JPanel panelMajors3=new JPanel();
	JPanel panelSearch =new JPanel();
	JPanel panelSearch1 =new JPanel();
	JPanel panelSearch2 =new JPanel();
	
	JTabbedPane tab=new JTabbedPane();

	JLabel fnameL=new JLabel("First Name:");
	JLabel lnameL=new JLabel("Last Name:");
	JLabel sexL=new JLabel("Sex:");
	JLabel ageL=new JLabel("Age:");
	JLabel cityL=new JLabel("City:");
	JLabel majorL=new JLabel("Major:");

	JTextField fnameField=new JTextField();
	JTextField lnameField=new JTextField();
	String[] item= {"Male","Female"};
	JComboBox<String> sexCombo=new JComboBox<String>(item);
	JTextField ageField=new JTextField();
	JComboBox<String> studentCombo=new JComboBox<String>();

	JComboBox<String> cityCombo=new JComboBox<String>();
	JComboBox<String> majorCombo=new JComboBox<String>();

	JButton addBt=new JButton("Add");
	JButton deleteBt=new JButton("Delete");
	JButton editBt=new JButton("Edit");

	JTable studentTable=new JTable();
	JScrollPane myScroll=new JScrollPane(studentTable);

	JLabel cityLabel=new JLabel("City:");
	JTextField cityTextField=new JTextField();
	JComboBox<String> cityCombofull = new JComboBox<String>();
	JButton addCityBt=new JButton("Add");
	JButton deleteCityBt=new JButton("Delete");
	JButton editCityBt=new JButton("Edit");
	JTable cityTable=new JTable();
	JScrollPane myScrollCity=new JScrollPane(cityTable);

	JLabel majorLabel=new JLabel("Major:");
	JTextField majorTextField=new JTextField();
	JComboBox<String> majorCombofull = new JComboBox<String>();
	JButton addMajorBt=new JButton("Add");
	JButton deleteMajorBt=new JButton("Delete");
	JButton editMajorBt=new JButton("Edit");
	JTable majorTable=new JTable();
	JScrollPane myScrollMajor=new JScrollPane(majorTable);

	JLabel fnameAsk=new JLabel("First Name:");
	JTextField fnameAskTextField=new JTextField();
	JLabel lnameAsk=new JLabel("Last Name:");
	JTextField lnameAskTextField=new JTextField();
	JLabel minAgeAsk=new JLabel("Min Age:");
	JTextField minAgeAskTextField=new JTextField();
	JLabel maxAgeAsk=new JLabel("Max Age:");
	JTextField maxAgeAskTextField=new JTextField();
	JLabel sexAsk = new JLabel("Sex:");
	String[] items = {"","Male","Female"};
	JComboBox<String> sexComboAsk = new JComboBox<String>(items);
	JLabel cityAsk=new JLabel("City:");
	JTextField cityAskTextField=new JTextField();
	JLabel majorAsk=new JLabel("Major:");
	JTextField majorAskTextField=new JTextField();

	JButton searchBt=new JButton("Search");
	JTable searchTable=new JTable();
	JScrollPane myScrollSearch=new JScrollPane(searchTable);

	public NewFrame() {
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		tab.add(panelStudents,"Students");
		tab.add(panelCities,"Cities");
		tab.add(panelMajors,"Majors");
		tab.add(panelSearch,"Search");

		panelSearch.setLayout(new GridLayout(2,1));
		panelSearch.add(panelSearch1);
		panelSearch.add(panelSearch2);

		panelSearch1.setLayout(new GridLayout(7, 2));
		panelSearch1.add(fnameAsk);
		panelSearch1.add(fnameAskTextField);
		panelSearch1.add(lnameAsk);
		panelSearch1.add(lnameAskTextField);
		panelSearch1.add(minAgeAsk);
		panelSearch1.add(minAgeAskTextField);
		panelSearch1.add(maxAgeAsk);
		panelSearch1.add(maxAgeAskTextField);
		panelSearch1.add(sexAsk);
		panelSearch1.add(sexComboAsk);
		panelSearch1.add(cityAsk);
		panelSearch1.add(cityAskTextField);
		panelSearch1.add(majorAsk);
		panelSearch1.add(majorAskTextField);

		panelSearch2.setLayout(new GridLayout(2, 1));
		panelSearch2.add(searchBt);
		searchBt.addActionListener(new NewFrame.NewSearch());
		myScrollSearch.setPreferredSize(new Dimension(350, 150));
		panelSearch2.add(myScrollSearch);

		this.add(tab);
		this.setVisible(true);
		panelStudents.setLayout(new GridLayout(2, 1));
		panelStudents1.setLayout(new GridLayout(7, 2));
		panelStudents1.add(fnameL);
		panelStudents1.add(fnameField);
		panelStudents1.add(lnameL);
		panelStudents1.add(lnameField);
		panelStudents1.add(sexL);
		panelStudents1.add(sexCombo);
		panelStudents1.add(ageL);
		panelStudents1.add(ageField);
		panelStudents1.add(cityL);
		panelStudents1.add(cityCombo);
		panelStudents1.add(majorL);
		panelStudents1.add(majorCombo);

		panelStudents.add(panelStudents1);

		panelStudents2.add(addBt);
		panelStudents2.add(deleteBt);
		panelStudents2.add(editBt);
		panelStudents2.add(studentCombo);
		addBt.addActionListener(new NewFrame.AddStudent());
		deleteBt.addActionListener(new NewFrame.DeleteStudent());
		editBt.addActionListener(new NewFrame.EditStudent());
		myScroll.setPreferredSize(new Dimension(350, 150));
		panelStudents2.add(myScroll);

		panelStudents.add(panelStudents2);

		studentTable.addMouseListener(new NewFrame.MouseAction());

		refreshStudentTable();
		refreshCityCombo();
		refreshMajorCombo();
		refreshStudentCombo();

		panelCities.setLayout(new GridLayout(3, 1));
		panelCities.add(panelCities1);
		panelCities1.setLayout(new GridLayout(1, 2));
		panelCities1.add(cityLabel);
		panelCities1.add(cityTextField);
		panelCities.add(panelCities2);
		panelCities2.setLayout(new GridLayout(2, 2));
		panelCities2.add(addCityBt);
		panelCities2.add(deleteCityBt);
		panelCities2.add(editCityBt);
		addCityBt.addActionListener(new NewFrame.AddCity());
		deleteCityBt.addActionListener(new NewFrame.DeleteCity());
		editCityBt.addActionListener(new NewFrame.EditCity());
		panelCities2.add(cityCombofull);
		panelCities.add(panelCities3);
		myScrollCity.setPreferredSize(new Dimension(350, 150));
		panelCities3.add(myScrollCity);

		refreshCityTable();
		refreshCityCombo();

		panelMajors.setLayout(new GridLayout(3, 1));
		panelMajors.add(panelMajors1);
		panelMajors1.setLayout(new GridLayout(1, 2));
		panelMajors1.add(majorLabel);
		panelMajors1.add(majorTextField);
		panelMajors.add(panelMajors2);
		panelMajors2.setLayout(new GridLayout(2, 2));
		panelMajors2.add(addMajorBt);
		panelMajors2.add(deleteMajorBt);
		panelMajors2.add(editMajorBt);
		addMajorBt.addActionListener(new NewFrame.AddMajor());
		deleteMajorBt.addActionListener(new NewFrame.DeleteMajor());
		editMajorBt.addActionListener(new NewFrame.EditMajor());
		panelMajors2.add(majorCombofull);
		panelMajors.add(panelMajors3);
		myScrollMajor.setPreferredSize(new Dimension(350, 150));
		panelMajors3.add(myScrollMajor);

		refreshMajorTable();
		refreshMajorCombo();
	}
	class NewSearch implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			conn=DBConnection.getConnection();

				String sql = "select fname, lname , age ,sex ,city , major, city_id, major_id from students s join cities c on c.id=s.city_id join majors m on m.id=s.major_id where s.id > 0";
				if (!fnameAskTextField.getText().isEmpty()) {
					sql = sql + " and fname='" + fnameAskTextField.getText() + "'";
				}
				if (!lnameAskTextField.getText().isEmpty()) {
					sql = sql + " and lname='" + lnameAskTextField.getText() + "'";
				}
				if (!minAgeAskTextField.getText().isEmpty()) {
					sql = sql + " and age >= " + minAgeAskTextField.getText();
				}
				if (!maxAgeAskTextField.getText().isEmpty()){
					sql = sql + " and age <= " + maxAgeAskTextField.getText();
				}
				if (sexComboAsk.getSelectedItem()!=""){
					sql = sql + " and sex = '" + sexComboAsk.getSelectedItem() + "'";
				}
				if (!cityAskTextField.getText().isEmpty()){
					sql = sql + " and city >= '" + cityAskTextField.getText() + "'";
				}
				if (!majorAskTextField.getText().isEmpty()){
					sql = sql + " and major >= '" + majorAskTextField.getText() + "'";
				}
			try {
				state=conn.prepareStatement(sql);
				result=state.executeQuery();
				searchTable.setModel(new MyModel(result));
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

	}
	public void refreshStudentTable() {
		conn=DBConnection.getConnection();
		try {
			state=conn.prepareStatement("select fname, lname , age ,sex ,city , major, city_id, major_id from students s join cities c on c.id=s.city_id join majors m on m.id=s.major_id;");
			result=state.executeQuery();
			studentTable.setModel(new MyModel(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void refreshCityTable() {
		conn=DBConnection.getConnection();
		try {
			state=conn.prepareStatement("select id, city from cities;");
			result=state.executeQuery();
			cityTable.setModel(new MyModel(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void refreshMajorTable() {
		conn=DBConnection.getConnection();
		try {
			state=conn.prepareStatement("select id, major from majors;");
			result=state.executeQuery();
			majorTable.setModel(new MyModel(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void refreshStudentCombo() {

		studentCombo.removeAllItems();

		conn=DBConnection.getConnection();
		String sql="select id, fname, lname from students";
		String item="";

		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				item=result.getObject(1).toString()+"."+result.getObject(2).toString()+
						" "+result.getObject(3).toString();
				studentCombo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void refreshCityCombo() {

		cityCombo.removeAllItems();
		cityCombofull.removeAllItems();

		conn=DBConnection.getConnection();
		String sql="select id, city from cities";
		String item="";

		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				item=result.getObject(1).toString()+"."+result.getObject(2).toString();
				cityCombo.addItem(item);
				cityCombofull.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void refreshMajorCombo() {

		majorCombo.removeAllItems();
		majorCombofull.removeAllItems();

		conn=DBConnection.getConnection();
		String sql="select id, major from majors";
		String item="";

		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			while(result.next()) {
				item=result.getObject(1).toString()+"."+result.getObject(2).toString();
				majorCombo.addItem(item);
				majorCombofull.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clearForm() {
		fnameField.setText("");
		lnameField.setText("");
		ageField.setText("");
		cityTextField.setText("");
		majorTextField.setText("");
	}
	class AddStudent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			conn=DBConnection.getConnection();
			String sql="insert into students(fname, lname, age, sex, city_id, major_id) values(?,?,?,?,?,?)";
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, fnameField.getText());
				state.setString(2, lnameField.getText());
				state.setInt(3, Integer.parseInt(ageField.getText()));
				state.setString(4, sexCombo.getSelectedItem().toString());
				state.setString(5, Integer.toString(cityCombo.getSelectedIndex()+1));
				state.setString(6, Integer.toString(majorCombo.getSelectedIndex()+1));

				state.execute();
				refreshStudentTable();
				clearForm();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			refreshStudentCombo();
		}

	}
	class AddCity implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			conn=DBConnection.getConnection();
			String sql="insert into cities(city) values(?)";
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, cityTextField.getText());

				state.execute();
				refreshCityTable();
				clearForm();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			refreshCityCombo();
		}
	}
	class AddMajor implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			conn=DBConnection.getConnection();
			String sql="insert into majors(major) values(?)";
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, majorTextField.getText());

				state.execute();
				refreshMajorTable();
				clearForm();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			refreshMajorCombo();
		}
	}
	class MouseAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			int row=studentTable.getSelectedRow();
			fnameField.setText(studentTable.getValueAt(row, 0).toString());
			lnameField.setText(studentTable.getValueAt(row, 1).toString());
			ageField.setText(studentTable.getValueAt(row, 2).toString());
			if(studentTable.getValueAt(row, 3).toString().equals("Male")) {
				sexCombo.setSelectedIndex(0);
			}
			else {
				sexCombo.setSelectedIndex(1);
			}
			cityCombo.setSelectedIndex(Integer.parseInt(studentTable.getValueAt(row, 6).toString())-1);
			majorCombo.setSelectedIndex(Integer.parseInt(studentTable.getValueAt(row, 7).toString())-1);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	class DeleteStudent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			conn=DBConnection.getConnection();
			String sql="delete from students where id=?";
			try {
				String a = studentCombo.getSelectedItem().toString();
				 a = a.substring(0, a.indexOf("."));
				id = Integer.parseInt(a);
				state=conn.prepareStatement(sql);
				state.setInt(1,id);
				state.execute();
				refreshStudentTable();
				refreshStudentCombo();
				clearForm();
				id=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class DeleteCity implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			conn=DBConnection.getConnection();
			String sql="delete from cities where id=?";
			try {
				String a = cityCombofull.getSelectedItem().toString();
				a = a.substring(0, a.indexOf("."));
				id = Integer.parseInt(a);
				state=conn.prepareStatement(sql);
				state.setInt(1,id);
				state.execute();
				refreshCityTable();
				refreshCityCombo();
				clearForm();
				id=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class DeleteMajor implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			conn=DBConnection.getConnection();
			String sql="delete from majors where id=?";
			try {
				String a = majorCombofull.getSelectedItem().toString();
				a = a.substring(0, a.indexOf("."));
				id = Integer.parseInt(a);
				state=conn.prepareStatement(sql);
				state.setInt(1,id);
				state.execute();
				refreshMajorTable();
				refreshMajorCombo();
				clearForm();
				id=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class EditStudent implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
			String a = studentCombo.getSelectedItem().toString();
			a = a.substring(0, a.indexOf("."));
			id = Integer.parseInt(a);
			if(id>0) {
				String sql="update students set fname=?, lname=?, age=?, sex=?, city_id=?, major_id=? where id=?";

				try {
					state=conn.prepareStatement(sql);

					state.setString(1, fnameField.getText());
					state.setString(2, lnameField.getText());
					state.setInt(3, Integer.parseInt(ageField.getText()));
					state.setString(4, sexCombo.getSelectedItem().toString());
					state.setString(5, Integer.toString(cityCombo.getSelectedIndex()+1));
					state.setString(6, Integer.toString(majorCombo.getSelectedIndex()+1));
					state.setString(7, Integer.toString(id));

					state.execute();
					refreshStudentTable();
					clearForm();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}
	class EditCity implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
			String a = cityCombofull.getSelectedItem().toString();
			a = a.substring(0, a.indexOf("."));
			id = Integer.parseInt(a);
			if(id>0) {
				String sql="update cities set city=? where id=?";
				try {
					state=conn.prepareStatement(sql);

					state.setString(1, cityTextField.getText());
					state.setString(2, Integer.toString(id));

					state.execute();
					refreshCityTable();
					clearForm();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	class EditMajor implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
			String a = majorCombofull.getSelectedItem().toString();
			a = a.substring(0, a.indexOf("."));
			id = Integer.parseInt(a);
			if(id>0) {
				String sql="update majors set major=? where id=?";
				try {
					state=conn.prepareStatement(sql);

					state.setString(1, majorTextField.getText());
					state.setString(2, Integer.toString(id));

					state.execute();
					refreshMajorTable();
					refreshMajorCombo();
					clearForm();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	class RefreshAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			refreshStudentTable();
			clearForm();

		}

	}
}
