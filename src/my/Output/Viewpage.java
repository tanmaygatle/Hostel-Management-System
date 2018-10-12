/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.Output;
import my.Input.*;
import javax.swing.JOptionPane;
import my.Input.MySqlConnect;
import java.util.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 *
 * @author tanmay
 */
public class Viewpage extends javax.swing.JFrame {
Connection conn = MySqlConnect.ConnectDB();
PreparedStatement pst = null;
ResultSet rs = null;
PreparedStatement plp = null;
ResultSet rs1 = null;
int state = 1;
PreparedStatement pst1 = null;
ResultSet rs2 = null;
PreparedStatement psp = null;

public Viewpage(String permission) {
        initComponents();
        FillSID();
        FillBlockID();
        FillRoomID1();
        RadioGroup();
        if(permission.equals("B")) {
            Guestuser();
        }
    }
private void Guestuser(){
    jButton1.setVisible(false);
    visitorupdatebutton.setVisible(false);
    updatebutton.setVisible(false);
    deletebutton.setVisible(false);
    jButton3.setVisible(false);
    updatebutton.setVisible(false);
    jButton12.setVisible(false);
    jButton16.setVisible(false);
    jButton6.setVisible(false);
    txtfname.setEditable(false);
    txtmname.setEditable(false);
    txtlname.setEditable(false);
    txtcity.setEditable(false);
    txtphoneno.setEditable(false);
    txtdepartment.setEditable(false);
    txtblockid.setEditable(false);
    txtroomid.setEditable(false);
    jXDatePicker1.setEditable(false);
    visitortable.setEnabled(false);
    nr.setEditable(false);
    nrnotfull.setEditable(false);
    nrfull.setEditable(false);
    exp.setEditable(false);
    ns.setEditable(false);
    
    oc.setEditable(false);
    ca.setEditable(false);
    rss.setEditable(false);
    ftable.setEnabled(false);
    
    c.setEditable(false);
    messname.setEditable(false);
    amc.setEditable(false);
    bt.setEditable(false);
    lt.setEditable(false);
    dt.setEditable(false);
    mtable.setEnabled(false);
}

public void Updatevtable(){
    try{
        
            plp = conn.prepareStatement("select visitor_id,visitor_name,visitor_time_in,visitor_time_out,date_visited from visitor where student_id= ?" );
            plp.setInt(1,Integer.parseInt(txtsid.getSelectedItem().toString()));

            rs1 = plp.executeQuery();

        
            DefaultTableModel model = (DefaultTableModel)visitortable.getModel();
            model.setRowCount(0);
            while (rs1.next())
            {
                model.addRow(new Object[]{rs1.getInt("visitor_id"),rs1.getString("visitor_name"),rs1.getString("date_visited"),rs1.getString("visitor_time_in"),rs1.getString("visitor_time_out")});
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
}
public void Updatemtable() {
    try{
            plp = conn.prepareStatement("select * from mess_employee where block_id like ?" );
            plp.setString(1,txtblockid2.getSelectedItem().toString());
            rs1 = plp.executeQuery();
        
            DefaultTableModel model = (DefaultTableModel)mtable.getModel();
            model.setRowCount(0);
            while (rs1.next())
            {
                model.addRow(new Object[]{rs1.getInt("emp_id"),rs1.getString("emp_name"),rs1.getInt("emp_salary"),rs1.getString("emp_phoneno"),rs1.getString("emp_designation")});
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
}
public void Updateftable() {
    int stud= Integer.parseInt(txtroomid1.getSelectedItem().toString());
    String bl= txtblockid1.getSelectedItem().toString();
    try{
            plp = conn.prepareStatement("select furniture_id,furniture_type from furniture where room_id= ? and block_id like ?" );
            plp.setInt(1,stud);
            plp.setString(2,bl);
            rs2 = plp.executeQuery();
            DefaultTableModel model = (DefaultTableModel)ftable.getModel();
            model.setRowCount(0);
            while (rs2.next())
            {
                model.addRow(new Object[]{rs2.getInt("furniture_id"),rs2.getString("furniture_type")});
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
}
private void RadioGroup(){
    ButtonGroup group = new ButtonGroup();
    group.add(jRadioButton1);
    group.add(jRadioButton2);
    
    jRadioButton2.setSelected(true);
    state = 2;
}
 private void FillRoomID1() {
         txtroomid1.removeAllItems();
         Connection conn2 = MySqlConnect.ConnectDB();
         PreparedStatement pst2 = null;
         ResultSet rs2 = null;

         String sql = "select room_id from room where block_id = 'H-Block'";
         if(txtblockid1.getSelectedItem().equals("F-Block")) {
             sql = "select room_id from room where block_id = 'F-Block'";
         }
         else if(txtblockid1.getSelectedItem().equals("E-Block")) {
             sql = "select room_id from room where block_id = 'E-Block'";
         }
         else if(txtblockid1.getSelectedItem().equals("I-Block")) {
             sql = "select room_id from room where block_id = 'I-Block'";
         }
         
       try{
            pst2 = conn2.prepareStatement(sql);
            rs2 = pst2.executeQuery();
            while(rs2.next()) {
                String roomid;
                roomid = rs2.getString("room_id");
                txtroomid1.addItem(roomid);
            }    
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
    }
private void FillSID() {
         String sql = "select distinct student_id from student";
       try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                int sid = rs.getInt("student_id");
                txtsid.addItem(Integer.toString(sid));
            }    
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
    }
    
private void FillBlockID() {
         String sql = "select distinct block_id from hostel";
       try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                String blockid = rs.getString("block_id");
                txtblockid.addItem(blockid);
                txtblockid1.addItem(blockid);
                txtblockid2.addItem(blockid);
                txtblockid3.addItem(blockid);
            }    
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
    }
   
     private void FillRoomID() {
         txtroomid.removeAllItems();
         Connection conn2 = MySqlConnect.ConnectDB();
         PreparedStatement pst2 = null;
         ResultSet rs2 = null;

         String sql = "select room_id from room where block_id = 'H-Block' and room_status = 'Not Full'";
         String sql1 = "select room_id,block_id from occupants where student_id = ?";   
         if(txtblockid.getSelectedItem().equals("F-Block")) {
             sql = "select room_id from room where block_id = 'F-Block' and room_status = 'Not Full'";
         }
         else if(txtblockid.getSelectedItem().equals("E-Block")) {
             sql = "select room_id from room where block_id = 'E-Block' and room_status = 'Not Full'";
         }
         else if(txtblockid.getSelectedItem().equals("I-Block")) {
             sql = "select room_id from room where block_id = 'I-Block' and room_status = 'Not Full'";
         }
         
       try{
            pst2 = conn2.prepareStatement(sql);
            rs2 = pst2.executeQuery();
            while(rs2.next()) {
                String roomid;
                roomid = rs2.getString("room_id");
                txtroomid.addItem(roomid);
            }    
            
            pst2 = conn2.prepareStatement(sql1);
            pst2.setInt(1,Integer.valueOf(txtsid.getSelectedItem().toString()));
            rs2 = pst2.executeQuery();
            if(rs2.next()) {
                if(txtblockid.getSelectedItem().toString().equals(rs2.getString("block_id"))){
                    txtroomid.addItem(rs2.getString("room_id"));
                }
            }
                
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
    }
      private int getAge(int year, int month, int day) {
    Calendar dob = Calendar.getInstance();
    Calendar today = Calendar.getInstance();
    dob.set(year, month, day);
    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--;
    }
    return age;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtid = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        logoutbutton = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtfname = new javax.swing.JTextField();
        txtmname = new javax.swing.JTextField();
        txtlname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtdepartment = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtcity = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtphoneno = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtblockid = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtroomid = new javax.swing.JComboBox<>();
        jButtonCancel = new javax.swing.JButton();
        updatebutton = new javax.swing.JButton();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        visitortable = new javax.swing.JTable();
        viewbutton = new javax.swing.JButton();
        txtsid = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        deletebutton = new javax.swing.JButton();
        visitorupdatebutton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        updatebutton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nrfull = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        exp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ns = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        wid = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        txtblockid3 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButtonCancel1 = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        nrnotfull = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        nr = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ftable = new javax.swing.JTable();
        oc = new javax.swing.JTextField();
        ca = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        txtblockid1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtroomid1 = new javax.swing.JComboBox<>();
        rss = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jButton7 = new javax.swing.JButton();
        jButtonCancel3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        mtable = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        c = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        amc = new javax.swing.JTextField();
        bt = new javax.swing.JTextField();
        lt = new javax.swing.JTextField();
        dt = new javax.swing.JTextField();
        txtblockid2 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        messname = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButtonCancel2 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();

        txtid.setBackground(java.awt.Color.darkGray);
        txtid.setForeground(new java.awt.Color(0, 191, 255));
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(java.awt.Color.darkGray);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Navigate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 18), new java.awt.Color(0, 191, 255))); // NOI18N
        jPanel3.setForeground(java.awt.Color.gray);

        jButton1.setBackground(java.awt.Color.lightGray);
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton1.setForeground(java.awt.Color.darkGray);
        jButton1.setText("Insert");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(java.awt.Color.lightGray);
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton2.setForeground(java.awt.Color.red);
        jButton2.setText("View");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        logoutbutton.setBackground(java.awt.Color.lightGray);
        logoutbutton.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        logoutbutton.setForeground(java.awt.Color.darkGray);
        logoutbutton.setText("Logout");
        logoutbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(logoutbutton, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutbutton)
                .addContainerGap())
        );

        jTabbedPane2.setFocusable(false);
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(758, 655));

        jPanel1.setPreferredSize(new java.awt.Dimension(752, 630));

        jPanel2.setBackground(java.awt.Color.darkGray);
        jPanel2.setForeground(new java.awt.Color(0, 191, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(752, 630));

        jPanel8.setBackground(new java.awt.Color(0, 191, 255));
        jPanel8.setForeground(java.awt.Color.darkGray);

        jLabel7.setBackground(java.awt.Color.white);
        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 35)); // NOI18N
        jLabel7.setForeground(java.awt.Color.darkGray);
        jLabel7.setText("Student View");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 189, 255));
        jLabel8.setText("Student ID:");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 191, 255));
        jLabel9.setText("Name:");

        txtfname.setBackground(java.awt.Color.darkGray);
        txtfname.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtfname.setForeground(new java.awt.Color(0, 191, 255));
        txtfname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtfname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfnameActionPerformed(evt);
            }
        });

        txtmname.setBackground(java.awt.Color.darkGray);
        txtmname.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtmname.setForeground(new java.awt.Color(0, 191, 255));
        txtmname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmname.setBorder(null);
        txtmname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmnameActionPerformed(evt);
            }
        });

        txtlname.setBackground(java.awt.Color.darkGray);
        txtlname.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtlname.setForeground(new java.awt.Color(0, 191, 255));
        txtlname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtlname.setBorder(null);
        txtlname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlnameActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 189, 255));
        jLabel11.setText("Date of Birth:");

        jLabel12.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 191, 255));
        jLabel12.setText("Department:");

        txtdepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Computer ", "IT", "Electrical", "ENTC", "Mechanical", "Civil" }));
        txtdepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdepartmentActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 189, 255));
        jLabel13.setText("City:");

        txtcity.setBackground(java.awt.Color.darkGray);
        txtcity.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtcity.setForeground(new java.awt.Color(0, 191, 255));
        txtcity.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtcity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcityActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 191, 255));
        jLabel14.setText("Phone No:");

        txtphoneno.setBackground(java.awt.Color.darkGray);
        txtphoneno.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtphoneno.setForeground(new java.awt.Color(0, 191, 255));
        txtphoneno.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtphoneno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtphonenoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 189, 255));
        jLabel15.setText("Block ID:");

        txtblockid.setAutoscrolls(true);
        txtblockid.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtblockid.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtblockidItemStateChanged(evt);
            }
        });
        txtblockid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtblockidActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 191, 255));
        jLabel16.setText("Room ID:");

        txtroomid.setAutoscrolls(true);
        txtroomid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtroomidActionPerformed(evt);
            }
        });

        jButtonCancel.setBackground(new java.awt.Color(0, 191, 255));
        jButtonCancel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButtonCancel.setForeground(java.awt.Color.darkGray);
        jButtonCancel.setText("Cancel");
        jButtonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCancelMouseClicked(evt);
            }
        });
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        updatebutton.setBackground(new java.awt.Color(0, 191, 255));
        updatebutton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        updatebutton.setForeground(java.awt.Color.darkGray);
        updatebutton.setText("Update Student");
        updatebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatebuttonMouseClicked(evt);
            }
        });
        updatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebuttonActionPerformed(evt);
            }
        });

        jXDatePicker1.setAutoscrolls(true);

        visitortable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Visitor ID", "Visitor Name", "Date Visited", "Visitor Time In", "Visitor Time Out"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(visitortable);

        viewbutton.setBackground(new java.awt.Color(0, 191, 255));
        viewbutton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        viewbutton.setText("View");
        viewbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewbuttonActionPerformed(evt);
            }
        });

        txtsid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsidActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.lightGray);
        jLabel1.setText("(First Name)");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel2.setForeground(java.awt.Color.lightGray);
        jLabel2.setText("(Middle Name)");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel3.setForeground(java.awt.Color.lightGray);
        jLabel3.setText("(Last Name)");

        deletebutton.setBackground(new java.awt.Color(0, 191, 255));
        deletebutton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        deletebutton.setForeground(java.awt.Color.darkGray);
        deletebutton.setText("Delete");
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });

        visitorupdatebutton.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        visitorupdatebutton.setText("Update Visitor");
        visitorupdatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visitorupdatebuttonActionPerformed(evt);
            }
        });

        updatebutton1.setBackground(new java.awt.Color(0, 191, 255));
        updatebutton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        updatebutton1.setForeground(java.awt.Color.darkGray);
        updatebutton1.setText("Update Fees");
        updatebutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebutton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11)
                                .addComponent(jLabel8)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtroomid, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jXDatePicker1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtdepartment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtcity, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtphoneno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtblockid, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(txtsid, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(viewbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(visitorupdatebutton)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(updatebutton)
                                    .addGap(59, 59, 59)
                                    .addComponent(updatebutton1)
                                    .addGap(67, 67, 67)
                                    .addComponent(deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(txtfname, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtmname)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtlname)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewbutton))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfname, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtlname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtphoneno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtblockid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtroomid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visitorupdatebutton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatebutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Student", jPanel1);

        jPanel6.setBackground(java.awt.Color.darkGray);

        jLabel4.setBackground(java.awt.Color.darkGray);
        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 192, 255));
        jLabel4.setText("Warden ID:");

        jLabel5.setBackground(java.awt.Color.darkGray);
        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 192, 255));
        jLabel5.setText("Number of rooms:");

        nrfull.setEditable(false);
        nrfull.setBackground(java.awt.Color.darkGray);
        nrfull.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        nrfull.setForeground(new java.awt.Color(0, 192, 255));
        nrfull.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nrfull.setText("                               ");
        nrfull.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel6.setBackground(java.awt.Color.darkGray);
        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 192, 255));
        jLabel6.setText("Annual Expenses:");

        exp.setBackground(java.awt.Color.darkGray);
        exp.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        exp.setForeground(new java.awt.Color(0, 192, 255));
        exp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expActionPerformed(evt);
            }
        });

        jLabel10.setBackground(java.awt.Color.darkGray);
        jLabel10.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 192, 255));
        jLabel10.setText("Number of students:");

        ns.setEditable(false);
        ns.setBackground(java.awt.Color.darkGray);
        ns.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        ns.setForeground(new java.awt.Color(0, 192, 255));
        ns.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nsActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 192, 255));
        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton4.setForeground(java.awt.Color.darkGray);
        jButton4.setText("View");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        wid.setBackground(java.awt.Color.darkGray);
        wid.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        wid.setForeground(new java.awt.Color(0, 192, 255));
        wid.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        wid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                widActionPerformed(evt);
            }
        });

        jLabel17.setBackground(java.awt.Color.darkGray);
        jLabel17.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 192, 255));
        jLabel17.setText("Block ID:");

        jRadioButton1.setBackground(java.awt.Color.darkGray);
        jRadioButton1.setForeground(new java.awt.Color(0, 191, 255));
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Warden Name");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(java.awt.Color.darkGray);
        jRadioButton2.setForeground(new java.awt.Color(0, 191, 255));
        jRadioButton2.setText("Block ID");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(java.awt.Color.darkGray);
        jTextField1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 191, 255));
        jTextField1.setText("Search by:");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtblockid3.setAutoscrolls(true);
        txtblockid3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtblockid3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtblockid3ItemStateChanged(evt);
            }
        });
        txtblockid3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtblockid3ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 191, 255));
        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton3.setForeground(java.awt.Color.darkGray);
        jButton3.setText("Update");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonCancel1.setBackground(new java.awt.Color(0, 191, 255));
        jButtonCancel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButtonCancel1.setForeground(java.awt.Color.darkGray);
        jButtonCancel1.setText("Cancel");
        jButtonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCancel1MouseClicked(evt);
            }
        });
        jButtonCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel1ActionPerformed(evt);
            }
        });

        nrnotfull.setEditable(false);
        nrnotfull.setBackground(java.awt.Color.darkGray);
        nrnotfull.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        nrnotfull.setForeground(new java.awt.Color(0, 192, 255));
        nrnotfull.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nrnotfull.setText("                               ");
        nrnotfull.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        nr.setEditable(false);
        nr.setBackground(java.awt.Color.darkGray);
        nr.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        nr.setForeground(new java.awt.Color(0, 192, 255));
        nr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nr.setText("                               ");
        nr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel18.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel18.setForeground(java.awt.Color.lightGray);
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("(Total)");

        jLabel25.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel25.setForeground(java.awt.Color.lightGray);
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("(Full)");

        jLabel26.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jLabel26.setForeground(java.awt.Color.lightGray);
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("(Not Full)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel4)))
                            .addComponent(jLabel10))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                .addComponent(wid, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(exp, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtblockid3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(nr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                        .addComponent(jSeparator20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nrfull, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jSeparator16)
                                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)))
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nrnotfull, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                        .addComponent(jSeparator19, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(ns, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addGap(37, 37, 37)
                        .addComponent(jRadioButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jButtonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtblockid3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nrfull, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(nrnotfull, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(nr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 166, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 191, 255));
        jPanel9.setForeground(java.awt.Color.darkGray);

        jLabel24.setBackground(java.awt.Color.white);
        jLabel24.setFont(new java.awt.Font("Ubuntu", 0, 35)); // NOI18N
        jLabel24.setForeground(java.awt.Color.darkGray);
        jLabel24.setText("Block View");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(334, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Block", jPanel4);

        jPanel15.setBackground(java.awt.Color.darkGray);
        jPanel15.setPreferredSize(new java.awt.Dimension(815, 611));

        jLabel37.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 191, 255));
        jLabel37.setText("Occupants:");

        jLabel40.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 191, 255));
        jLabel40.setText("Capacity:");

        jLabel41.setForeground(new java.awt.Color(0, 191, 255));

        ftable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FurnitureID", "Furniture Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ftable);

        oc.setEditable(false);
        oc.setBackground(java.awt.Color.darkGray);
        oc.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        oc.setForeground(new java.awt.Color(0, 191, 255));
        oc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        ca.setEditable(false);
        ca.setBackground(java.awt.Color.darkGray);
        ca.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        ca.setForeground(new java.awt.Color(0, 191, 255));
        ca.setText(" ");
        ca.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel42.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 192, 255));
        jLabel42.setText("Room status:");

        jButton12.setBackground(new java.awt.Color(0, 191, 255));
        jButton12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton12.setForeground(java.awt.Color.darkGray);
        jButton12.setText("Delete Furniture");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        txtblockid1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtblockid1ItemStateChanged(evt);
            }
        });
        txtblockid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtblockid1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 189, 255));
        jLabel19.setText("Block ID:");

        jLabel20.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 191, 255));
        jLabel20.setText("Room ID:");

        txtroomid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtroomid1ActionPerformed(evt);
            }
        });

        rss.setEditable(false);
        rss.setBackground(java.awt.Color.darkGray);
        rss.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        rss.setForeground(new java.awt.Color(0, 191, 255));
        rss.setText(" ");
        rss.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        rss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rssActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 192, 255));
        jButton7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton7.setForeground(java.awt.Color.darkGray);
        jButton7.setText("View");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButtonCancel3.setBackground(new java.awt.Color(0, 191, 255));
        jButtonCancel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButtonCancel3.setForeground(java.awt.Color.darkGray);
        jButtonCancel3.setText("Cancel");
        jButtonCancel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCancel3MouseClicked(evt);
            }
        });
        jButtonCancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel37)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel40)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtblockid1, 0, 300, Short.MAX_VALUE)
                    .addComponent(txtroomid1, 0, 300, Short.MAX_VALUE)
                    .addComponent(oc)
                    .addComponent(ca)
                    .addComponent(rss, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator6)
                    .addComponent(jSeparator7)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(167, 167, 167)
                .addComponent(jLabel41)
                .addGap(619, 619, 619))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtblockid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtroomid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel39))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(ca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel41))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel40)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rss, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 152, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 191, 255));

        jLabel22.setBackground(java.awt.Color.white);
        jLabel22.setFont(new java.awt.Font("Ubuntu", 0, 35)); // NOI18N
        jLabel22.setForeground(java.awt.Color.darkGray);
        jLabel22.setText("Room View");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 1255, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Room", jPanel11);

        jPanel17.setBackground(java.awt.Color.darkGray);

        mtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Salary", "Phone Number", "Designation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(mtable);

        jLabel45.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 191, 255));
        jLabel45.setText("Warden Name:");

        c.setEditable(false);
        c.setBackground(java.awt.Color.darkGray);
        c.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        c.setForeground(new java.awt.Color(0, 191, 255));
        c.setBorder(null);
        c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(0, 191, 255));
        jButton16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton16.setForeground(java.awt.Color.darkGray);
        jButton16.setText("Update Employee");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 192, 255));
        jLabel46.setText("Annual Expenses:");

        jLabel48.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 192, 255));
        jLabel48.setText("Breakfast Timings:");

        jLabel49.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 192, 255));
        jLabel49.setText("Dinner Timings:");

        jLabel50.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 192, 255));
        jLabel50.setText("Lunch Timings:");

        amc.setBackground(java.awt.Color.darkGray);
        amc.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        amc.setForeground(new java.awt.Color(0, 191, 255));
        amc.setBorder(null);
        amc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amcActionPerformed(evt);
            }
        });

        bt.setBackground(java.awt.Color.darkGray);
        bt.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        bt.setForeground(new java.awt.Color(0, 191, 255));
        bt.setBorder(null);

        lt.setBackground(java.awt.Color.darkGray);
        lt.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lt.setForeground(new java.awt.Color(0, 191, 255));
        lt.setBorder(null);

        dt.setBackground(java.awt.Color.darkGray);
        dt.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        dt.setForeground(new java.awt.Color(0, 191, 255));
        dt.setBorder(null);

        txtblockid2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtblockid2ItemStateChanged(evt);
            }
        });
        txtblockid2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtblockid2ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 189, 255));
        jLabel21.setText("Block ID:");

        jLabel52.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 192, 255));
        jLabel52.setText("Mess Name:");

        messname.setBackground(java.awt.Color.darkGray);
        messname.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        messname.setForeground(new java.awt.Color(0, 191, 255));
        messname.setBorder(null);

        jButton5.setBackground(new java.awt.Color(0, 192, 255));
        jButton5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton5.setForeground(java.awt.Color.darkGray);
        jButton5.setText("View");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 191, 255));
        jButton6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton6.setForeground(java.awt.Color.darkGray);
        jButton6.setText("Update");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButtonCancel2.setBackground(new java.awt.Color(0, 191, 255));
        jButtonCancel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButtonCancel2.setForeground(java.awt.Color.darkGray);
        jButtonCancel2.setText("Cancel");
        jButtonCancel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCancel2MouseClicked(evt);
            }
        });
        jButtonCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                            .addGap(108, 108, 108)
                                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel45)
                                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addComponent(jLabel47)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel46)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(amc)
                                    .addComponent(bt)
                                    .addComponent(lt)
                                    .addComponent(dt)
                                    .addComponent(txtblockid2, 0, 300, Short.MAX_VALUE)
                                    .addComponent(c)
                                    .addComponent(jSeparator9)
                                    .addComponent(jSeparator10)
                                    .addComponent(jSeparator11)
                                    .addComponent(jSeparator12)
                                    .addComponent(jSeparator13)
                                    .addComponent(jSeparator14)))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(jLabel52)
                                .addGap(58, 58, 58)
                                .addComponent(messname, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(177, 177, 177))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(180, 180, 180)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtblockid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel47))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(messname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addGap(4, 4, 4)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 30, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(0, 191, 255));
        jPanel12.setForeground(java.awt.Color.darkGray);

        jLabel23.setBackground(java.awt.Color.white);
        jLabel23.setFont(new java.awt.Font("Ubuntu", 0, 35)); // NOI18N
        jLabel23.setForeground(java.awt.Color.darkGray);
        jLabel23.setText("Mess View");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Mess", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            Insertpage ins = new Insertpage();
            ins.setVisible(true);
            ins.pack();
            ins.setLocationRelativeTo(null);
            ins.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtblockid2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtblockid2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtblockid2ActionPerformed

    private void txtblockid2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtblockid2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtblockid2ItemStateChanged

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        int selrow = mtable.getSelectedRow();
        if(selrow == -1)
        JOptionPane.showMessageDialog(null,"Please select an employee from the table");
        else{
            messempup mupd = new messempup((int)mtable.getValueAt(selrow, 0), this);
            mupd.setVisible(true);
            mupd.pack();
            mupd.setLocationRelativeTo(null);
            mupd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            

        }

    }//GEN-LAST:event_jButton16ActionPerformed

    private void cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cActionPerformed

    private void widActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_widActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(state == 1){
            try{
                pst = conn.prepareStatement("select * from hostel where warden like ? ");

                pst.setString(1,wid.getText());

                rs = pst.executeQuery();

            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
            try{
                if(rs.next()){
                    String b = rs.getString("block_id");
                    ns.setText(Integer.toString(rs.getInt("no_of_students")));
                    exp.setText(Integer.toString(rs.getInt("annual_expenses")));
                    nr.setText(Integer.toString(rs.getInt("no_of_rooms")));
                    txtblockid3.setSelectedItem(b);
                    
                    pst = conn.prepareStatement("select count(room_id) as temp from room where block_id = ? and room_status = 'Full' group by block_id");
                    pst.setString(1,b);
                    rs = pst.executeQuery();
                    if(rs.next()) {
                        nrfull.setText(rs.getString("temp"));
                    }
                    else
                        nrfull.setText("0");
                    
                    pst = conn.prepareStatement("select count(room_id) as temp from room where block_id = ? and room_status = 'Not Full' group by block_id");
                    pst.setString(1,b);
                    rs = pst.executeQuery();
                    if(rs.next()) {
                        nrnotfull.setText(rs.getString("temp"));
                    }
                    else
                        nrnotfull.setText("0");
                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        else if(state == 2){
            try{
                String b = txtblockid3.getSelectedItem().toString();
                pst = conn.prepareStatement("select * from hostel where block_id like ? ");

                pst.setString(1,b);

                rs = pst.executeQuery();

            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

            try{
                if(rs.next()){
                    String b = txtblockid3.getSelectedItem().toString();
                    ns.setText(Integer.toString(rs.getInt("no_of_students")));
                    exp.setText(Integer.toString(rs.getInt("annual_expenses")));
                    nr.setText(Integer.toString(rs.getInt("no_of_rooms")));
                    wid.setText(rs.getString("warden"));

                    pst = conn.prepareStatement("select count(room_id) as temp from room where block_id = ? and room_status = 'Full' group by block_id");
                    pst.setString(1,b);
                    rs = pst.executeQuery();
                    if(rs.next()) {
                        nrfull.setText(rs.getString("temp"));
                    }
                    else
                        nrfull.setText("0");
                    
                    pst = conn.prepareStatement("select count(room_id) as temp from room where block_id = ? and room_status = 'Not Full' group by block_id");
                    pst.setString(1,b);
                    rs = pst.executeQuery();
                    if(rs.next()) {
                        nrnotfull.setText(rs.getString("temp"));
                    }
                    else
                        nrnotfull.setText("0");
                }

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expActionPerformed

    private void visitorupdatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visitorupdatebuttonActionPerformed
        // TODO add your handling code here:
        int selrow = visitortable.getSelectedRow();
        if(selrow == -1)
        JOptionPane.showMessageDialog(null,"Please select a visitor from the table");
        else{
            visitorupdate vupd = new visitorupdate((int)visitortable.getValueAt(selrow, 0), this);
            vupd.setVisible(true);
            vupd.pack();
            vupd.setLocationRelativeTo(null);
            vupd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }//GEN-LAST:event_visitorupdatebuttonActionPerformed

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete student "+ txtsid.getSelectedItem().toString()+" ?")== JOptionPane.YES_OPTION) {
            String sql3 = "delete from student where student_id = ?";
            String sql2 = "update room set room_status = 'Not Full' where (room_id,block_id) in (select room_id,block_id from (select room_id,block_id from room natural join occupants group by room_id,block_id,capacity having count(student_id)<capacity)as temp)";
            String sql1 = " update hostel set no_of_students = no_of_students-1 where block_id in (select block_id from student where student_id = ?)";
            try{
                pst = conn.prepareStatement(sql1);
                pst.setInt(1,Integer.valueOf(txtsid.getSelectedItem().toString()));
                int i = pst.executeUpdate();
                if(i > 0){}
                else{
                    JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
                }

                pst = conn.prepareStatement(sql3);
                pst.setInt(1,Integer.valueOf(txtsid.getSelectedItem().toString()));
                i = pst.executeUpdate();
                if(i > 0){}
                else{
                    JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
                }

                pst = conn.prepareStatement(sql2);
                i = pst.executeUpdate();
                if(i > 0){ this.dispose();
                    JOptionPane.showMessageDialog(null,"Deletion was successful");
                    //main(null);
                }
                else{
                    JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
                }

            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_deletebuttonActionPerformed

    private void txtsidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsidActionPerformed

    private void viewbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewbuttonActionPerformed
        FillRoomID();
        int stud= Integer.parseInt(txtsid.getSelectedItem().toString());
        try{
            pst = conn.prepareStatement("select * from student where student_id= ?" );

            pst.setInt(1,stud);

            rs = pst.executeQuery();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            plp = conn.prepareStatement("select visitor_id,visitor_name,visitor_time_in,visitor_time_out,date_visited from visitor where student_id= ?" );
            plp.setInt(1,stud);

            rs1 = plp.executeQuery();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            if(rs.next()){
                txtfname.setText(rs.getString("student_first_name"));
                txtmname.setText(rs.getString("student_middle_name"));
                txtlname.setText(rs.getString("student_last_name"));
                jXDatePicker1.setDate(rs.getDate("student_dob"));
                txtdepartment.setSelectedItem(rs.getString("department"));
                txtcity.setText(rs.getString("city"));
                txtphoneno.setText(""+rs.getString("phone_no"));
                txtblockid.setSelectedItem(rs.getString("block_id"));
                txtroomid.setSelectedItem(rs.getString("room_id"));
            }
            else{
                JOptionPane.showMessageDialog(null, "NO DATA FOR THIS ID");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
       try{
            DefaultTableModel model = (DefaultTableModel)visitortable.getModel();
            model.setRowCount(0);
            while (rs1.next())
            {
                model.addRow(new Object[]{rs1.getInt("visitor_id"),rs1.getString("visitor_name"),rs1.getString("date_visited"),rs1.getString("visitor_time_in"),rs1.getString("visitor_time_out")});
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_viewbuttonActionPerformed

    private void updatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebuttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updatebuttonActionPerformed

    private void updatebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatebuttonMouseClicked

        String sql1 = "delete from occupants where student_id = ?";
        String sql2 = "update room set room_status = 'Not Full' where block_id = ? and room_id = ?";
        String sql3 = "update student set student_first_name=?,student_middle_name=?,student_last_name=?,student_age=?,student_dob=?,department=?,city=?,phone_no=?, block_id=?, room_id=? where student_id = ?";
        String sql4 = "insert into occupants values (?,?,?)";
        String sql5 = "update room set room_status = 'Full' where ((room_status = 'Not Full') and ((room_id,block_id) in (select room_id,block_id from (select room_id,block_id from room natural join occupants group by room_id,block_id,capacity having count(student_id) = capacity)as temp)));";
        try{
            pst = conn.prepareStatement(sql1);
            pst.setInt(1,Integer.valueOf(txtsid.getSelectedItem().toString()));
            int i = pst.executeUpdate();
            if(i > 0){}
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }

            pst = conn.prepareStatement(sql2);
            pst.setInt(2,Integer.valueOf(txtroomid.getSelectedItem().toString()));
            pst.setString(1,txtblockid.getSelectedItem().toString());
            i = pst.executeUpdate();
            if(i > 0){}
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }

            pst = conn.prepareStatement(sql3);
            pst.setString(1,txtfname.getText());
            pst.setString(2,txtmname.getText());
            pst.setString(3,txtlname.getText());
            pst.setString(6,txtdepartment.getSelectedItem().toString());
            pst.setString(7,txtcity.getText());
            pst.setString(8,txtphoneno.getText());

            java.util.Date oDate = jXDatePicker1.getDate();
            DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String szDate = oDateFormat.format(oDate);
            pst.setString(5,szDate);

            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            pst.setInt(4,getAge(Integer.valueOf(yearFormat.format(oDate)),Integer.valueOf(monthFormat.format(oDate)),Integer.valueOf(dayFormat.format(oDate))));

            pst.setString(9,txtblockid.getSelectedItem().toString());
            pst.setInt(10,Integer.valueOf(txtroomid.getSelectedItem().toString()));
            pst.setInt(11,Integer.valueOf(txtsid.getSelectedItem().toString()));
            i = pst.executeUpdate();
            if(i > 0){}
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }

            pst = conn.prepareStatement(sql4);
            pst.setInt(1,Integer.valueOf(txtroomid.getSelectedItem().toString()));
            pst.setString(2,txtblockid.getSelectedItem().toString());
            pst.setInt(3,Integer.valueOf(txtsid.getSelectedItem().toString()));
            i = pst.executeUpdate();
            if(i > 0) {}
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }

            pst = conn.prepareStatement(sql5);
            i = pst.executeUpdate();
            if(i > 0) {
                JOptionPane.showMessageDialog(null,"Updation was successful");
            }
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_updatebuttonMouseClicked

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelMouseClicked
        Loginpage pg = new Loginpage();
                pg.setVisible(true);
                pg.pack();
                pg.setLocationRelativeTo(null);
                pg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
    }//GEN-LAST:event_jButtonCancelMouseClicked

    private void txtroomidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtroomidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtroomidActionPerformed

    private void txtblockidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtblockidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtblockidActionPerformed

    private void txtblockidItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtblockidItemStateChanged
        FillRoomID();
    }//GEN-LAST:event_txtblockidItemStateChanged

    private void txtphonenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtphonenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtphonenoActionPerformed

    private void txtcityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcityActionPerformed

    private void txtdepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdepartmentActionPerformed

    private void txtlnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlnameActionPerformed

    private void txtmnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmnameActionPerformed

    private void txtfnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfnameActionPerformed

    private void rssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rssActionPerformed

    private void txtroomid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtroomid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtroomid1ActionPerformed

    private void txtblockid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtblockid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtblockid1ActionPerformed

    private void txtblockid1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtblockid1ItemStateChanged
        FillRoomID1();
    }//GEN-LAST:event_txtblockid1ItemStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int selrow = ftable.getSelectedRow();
        if(selrow == -1)
        JOptionPane.showMessageDialog(null,"Please select a furniture entry from the table");
        else{
            if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete furniture "+ (int)ftable.getValueAt(selrow, 0)+" ?")== JOptionPane.YES_OPTION) {
                String sql = "delete from furniture where furniture_id =?";
                try{
                    pst = conn.prepareStatement(sql);

                    pst.setInt(1,(int)ftable.getValueAt(selrow, 0));

                    int i = pst.executeUpdate();
                    if(i > 0){
                        JOptionPane.showMessageDialog(null,"You have successfully deleted a furniture entry");
                        Updateftable();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void amcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amcActionPerformed

    private void nsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nsActionPerformed

    private void txtblockid3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtblockid3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtblockid3ItemStateChanged

    private void txtblockid3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtblockid3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtblockid3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        state = 1;
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        state=2;
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            pst = conn.prepareStatement("update hostel set annual_expenses=?, warden=? where block_id like ? ");
            
            pst.setInt(1,Integer.parseInt(exp.getText()));
          pst.setString(2,wid.getText());
          pst.setString(3,txtblockid3.getSelectedItem().toString());
            int i = pst.executeUpdate();  
             if(i > 0) {
                JOptionPane.showMessageDialog(null,"You have updated a block");
            }
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }
            
           
       }
            catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancel1MouseClicked
          
    }//GEN-LAST:event_jButtonCancel1MouseClicked

    private void jButtonCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel1ActionPerformed
        // TODO add your handling code here:
        Loginpage pg = new Loginpage();
                pg.setVisible(true);
                pg.pack();
                pg.setLocationRelativeTo(null);
                pg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
    }//GEN-LAST:event_jButtonCancel1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
         int stud= Integer.parseInt(txtroomid1.getSelectedItem().toString());
        String bl= txtblockid1.getSelectedItem().toString();
        try{
            pst = conn.prepareStatement("select student_id from occupants where room_id= ? and block_id like ?" );

            pst.setInt(1,stud);
            pst.setString(2,bl);
            rs = pst.executeQuery();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            pst1 = conn.prepareStatement("select capacity, room_status from room where room_id= ? and block_id like ?" );

            pst1.setInt(1,stud);
            pst1.setString(2,bl);
            rs1 = pst1.executeQuery();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            plp = conn.prepareStatement("select furniture_id,furniture_type from furniture where room_id= ? and block_id like ?" );
            plp.setInt(1,stud);
            plp.setString(2,bl);

            rs2 = plp.executeQuery();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            oc.setText("");
            if(rs.next()){
                rs.beforeFirst();
                while(rs.next()){
                    oc.setText(oc.getText()+"  "+rs.getInt("student_id"));
                }
            }
            else
                oc.setText("Empty");
            
            if(rs1.next()){
                ca.setText(Integer.toString(rs1.getInt("capacity")));
                rss.setText(rs1.getString("room_status"));
            }
            else{
                JOptionPane.showMessageDialog(null, "NO DATA FOR THIS ID");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        try{
            DefaultTableModel model = (DefaultTableModel)ftable.getModel();
            model.setRowCount(0);
            while (rs2.next())
            {
                model.addRow(new Object[]{rs2.getInt("furniture_id"),rs2.getString("furniture_type")});
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButtonCancel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancel3MouseClicked

    private void jButtonCancel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel3ActionPerformed
        // TODO add your handling code here:
        Loginpage pg = new Loginpage();
                pg.setVisible(true);
                pg.pack();
                pg.setLocationRelativeTo(null);
                pg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
    }//GEN-LAST:event_jButtonCancel3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String block= txtblockid2.getSelectedItem().toString();
        try{
            plp = conn.prepareStatement("select * from mess_employee where block_id like ?" );

            plp.setString(1,block);

            rs1 = plp.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            psp = conn.prepareStatement("select * from mess where block_id like ?" );

            psp.setString(1,block);

            rs2 = psp.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            pst= conn.prepareStatement("select warden from hostel where block_id like ?" );
            pst.setString(1,block);

            rs = pst.executeQuery();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            if(rs.next()){
                c.setText(rs.getString("warden"));

            }
            else{
                JOptionPane.showMessageDialog(null, "NO DATA FOR THIS ID");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        try{
            if(rs2.next()){
                amc.setText(Integer.toString(rs2.getInt("monthly_avg_expense")));
                bt.setText(rs2.getString("mess_bf_time"));
                lt.setText(rs2.getString("mess_lunch_time"));
                dt.setText(rs2.getString("mess_dinner_time"));
                messname.setText(rs2.getString("mess_name"));
            }
            else{
                JOptionPane.showMessageDialog(null, "NO DATA FOR THIS ID");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        try{
            DefaultTableModel model = (DefaultTableModel)mtable.getModel();
            model.setRowCount(0);
            while (rs1.next())
            {
                model.addRow(new Object[]{rs1.getInt("emp_id"),rs1.getString("emp_name"),rs1.getInt("emp_salary"),rs1.getString("emp_phoneno"),rs1.getString("emp_designation")});
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
                String block= txtblockid2.getSelectedItem().toString();
        try{
            plp = conn.prepareStatement("update mess set monthly_avg_expense=?, mess_bf_time=?, mess_lunch_time=?, mess_dinner_time=?, mess_name=? where block_id like ?" );
            plp.setInt(1,Integer.parseInt(amc.getText()));
            plp.setString(2,bt.getText());
            plp.setString(3,lt.getText());
            plp.setString(4,dt.getText());
            plp.setString(6,block);
            plp.setString(5,messname.getText());
            int i = plp.executeUpdate();
            if(i > 0) {
                JOptionPane.showMessageDialog(null,"You have updated mess details");
            }
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonCancel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancel2MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButtonCancel2MouseClicked

    private void jButtonCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel2ActionPerformed
        // TODO add your handling code here:
        Loginpage pg = new Loginpage();
                pg.setVisible(true);
                pg.pack();
                pg.setLocationRelativeTo(null);
                pg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
    }//GEN-LAST:event_jButtonCancel2ActionPerformed

    private void updatebutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebutton1ActionPerformed
        // TODO add your handling code here:
        Feesupdate fupd = new Feesupdate(Integer.valueOf(txtsid.getSelectedItem().toString()),txtfname.getText(),txtlname.getText());
        fupd.setVisible(true);
        fupd.pack();
        fupd.setLocationRelativeTo(null);
        fupd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_updatebutton1ActionPerformed

    private void logoutbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbuttonActionPerformed
        // TODO add your handling code here:
        try{
            Loginpage lpg = new Loginpage();
            lpg.setVisible(true);
            lpg.pack();
            lpg.setLocationRelativeTo(null);
            lpg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_logoutbuttonActionPerformed

    /**
     * @param args the command line arguments
     */
   // public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Viewpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Viewpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Viewpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Viewpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
     /*   java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Viewpage(perm).setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amc;
    private javax.swing.JTextField bt;
    private javax.swing.JTextField c;
    private javax.swing.JTextField ca;
    private javax.swing.JButton deletebutton;
    private javax.swing.JTextField dt;
    private javax.swing.JTextField exp;
    private javax.swing.JTable ftable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCancel1;
    private javax.swing.JButton jButtonCancel2;
    private javax.swing.JButton jButtonCancel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JButton logoutbutton;
    private javax.swing.JTextField lt;
    private javax.swing.JTextField messname;
    private javax.swing.JTable mtable;
    private javax.swing.JTextField nr;
    private javax.swing.JTextField nrfull;
    private javax.swing.JTextField nrnotfull;
    private javax.swing.JTextField ns;
    private javax.swing.JTextField oc;
    private javax.swing.JTextField rss;
    private javax.swing.JComboBox<String> txtblockid;
    private javax.swing.JComboBox<String> txtblockid1;
    private javax.swing.JComboBox<String> txtblockid2;
    private javax.swing.JComboBox<String> txtblockid3;
    private javax.swing.JTextField txtcity;
    private javax.swing.JComboBox<String> txtdepartment;
    private javax.swing.JTextField txtfname;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtlname;
    private javax.swing.JTextField txtmname;
    private javax.swing.JTextField txtphoneno;
    private javax.swing.JComboBox<String> txtroomid;
    private javax.swing.JComboBox<String> txtroomid1;
    private javax.swing.JComboBox<String> txtsid;
    private javax.swing.JButton updatebutton;
    private javax.swing.JButton updatebutton1;
    private javax.swing.JButton viewbutton;
    private javax.swing.JTable visitortable;
    private javax.swing.JButton visitorupdatebutton;
    private javax.swing.JTextField wid;
    // End of variables declaration//GEN-END:variables
}
