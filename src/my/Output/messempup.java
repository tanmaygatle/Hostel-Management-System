package my.Output;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import my.Input.MySqlConnect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kartik
 */
public class messempup extends javax.swing.JFrame {
     Connection conn = MySqlConnect.ConnectDB();
    PreparedStatement pst = null;
    ResultSet rs = null;
    my.Output.Viewpage v = null;
    
    public messempup(int empid,my.Output.Viewpage v) {
        initComponents();
        txtempid.setText(Integer.toString(empid));
        FillEverything();
        this.v = v;
    }
private void FillEverything(){
        try{
            String sql1 = "select emp_name,emp_salary,emp_phoneno,emp_designation from mess_employee where emp_id = ?";
            pst = conn.prepareStatement(sql1);
            pst.setInt(1,Integer.valueOf(txtempid.getText()));
            rs = pst.executeQuery();
      
            if(rs.next()){
                txtempname.setText(rs.getString("emp_name"));
                txtsalary.setText(Integer.toString(rs.getInt("emp_salary")));
                txtempphoneno.setText(rs.getString("emp_phoneno"));
                txtempdes.setSelectedItem(rs.getString("emp_designation"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtempname = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtsalary = new javax.swing.JTextField();
        txtempphoneno = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtempdes = new javax.swing.JComboBox<>();
        jButtonSubmit2 = new javax.swing.JButton();
        jButtonCancel2 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtempid = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel13.setBackground(java.awt.Color.darkGray);
        jPanel13.setForeground(new java.awt.Color(0, 191, 255));

        jPanel14.setBackground(new java.awt.Color(0, 191, 255));
        jPanel14.setForeground(java.awt.Color.darkGray);

        jLabel23.setBackground(java.awt.Color.white);
        jLabel23.setFont(new java.awt.Font("Ubuntu", 0, 35)); // NOI18N
        jLabel23.setForeground(java.awt.Color.darkGray);
        jLabel23.setText("Mess Employee Update");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtempname.setBackground(java.awt.Color.darkGray);
        txtempname.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        txtempname.setForeground(new java.awt.Color(0, 191, 255));
        txtempname.setBorder(null);
        txtempname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtempnameActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 191, 255));
        jLabel22.setText("Employee Name:");

        jLabel24.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 189, 255));
        jLabel24.setText("Salary:");

        txtsalary.setBackground(java.awt.Color.darkGray);
        txtsalary.setForeground(new java.awt.Color(0, 191, 255));
        txtsalary.setBorder(null);
        txtsalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsalaryActionPerformed(evt);
            }
        });

        txtempphoneno.setBackground(java.awt.Color.darkGray);
        txtempphoneno.setForeground(new java.awt.Color(0, 191, 255));
        txtempphoneno.setBorder(null);
        txtempphoneno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtempphonenoActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 189, 255));
        jLabel25.setText("Phone No:");

        jLabel27.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 191, 255));
        jLabel27.setText("Employee Designation:");

        txtempdes.setBackground(java.awt.Color.darkGray);
        txtempdes.setForeground(new java.awt.Color(0, 191, 255));
        txtempdes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mess Manager", "Cook", "Technician", "Laundryman", "Sweeper" }));
        txtempdes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtempdesActionPerformed(evt);
            }
        });

        jButtonSubmit2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButtonSubmit2.setForeground(java.awt.Color.darkGray);
        jButtonSubmit2.setText("Update");
        jButtonSubmit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSubmit2MouseClicked(evt);
            }
        });
        jButtonSubmit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmit2ActionPerformed(evt);
            }
        });

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

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton2.setForeground(java.awt.Color.darkGray);
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 192, 255));
        jLabel1.setText("Employee ID:");

        txtempid.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtempid.setForeground(new java.awt.Color(0, 191, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jButtonCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jButtonSubmit2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel1)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel27)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtempphoneno, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                .addComponent(txtsalary)
                                .addComponent(txtempname)
                                .addComponent(txtempid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1)
                                .addComponent(jSeparator2)
                                .addComponent(jSeparator3))
                            .addComponent(txtempdes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtempid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtempname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtempphoneno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtempdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(113, 113, 113)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSubmit2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtempnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtempnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempnameActionPerformed

    private void txtsalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsalaryActionPerformed

    private void txtempphonenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtempphonenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempphonenoActionPerformed

    private void txtempdesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtempdesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempdesActionPerformed

    private void jButtonSubmit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSubmit2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSubmit2MouseClicked

    private void jButtonSubmit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmit2ActionPerformed
        
        // TODO add your handling code here:
        int d =   Integer.parseInt(txtempid.getText());
        String sql = "update mess_employee set emp_name=?,emp_salary=?,emp_phoneno=?,emp_designation=? where emp_id= ?";
        try{
            pst = conn.prepareStatement(sql);
            
            pst.setInt(5,d);
            pst.setString(1,txtempname.getText());
            pst.setInt(2,Integer.valueOf(txtsalary.getText()));
            pst.setString(3,txtempphoneno.getText());
           
            pst.setString(4,txtempdes.getSelectedItem().toString());
            
            int i = pst.executeUpdate();
            if(i > 0){
                JOptionPane.showMessageDialog(null,"You have successfully updated a mess employee");
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
         
       
    }//GEN-LAST:event_jButtonSubmit2ActionPerformed

    private void jButtonCancel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancel2MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonCancel2MouseClicked

    private void jButtonCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCancel2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete employee "+ txtempid.getText()+" ?")== JOptionPane.YES_OPTION) {
           String sql = "delete from mess_employee where emp_id = ?";
   int d =   Integer.parseInt(txtempid.getText());
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1,d);
            int i = pst.executeUpdate();
            if(i > 0) {
                JOptionPane.showMessageDialog(null,"You have deleted a mess employee");
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
          }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        v.Updatemtable();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    //public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(messempup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(messempup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(messempup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(messempup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
      /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new messempup().setVisible(true);
            }
        });
    }*/
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCancel2;
    private javax.swing.JButton jButtonSubmit2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JComboBox<String> txtempdes;
    private javax.swing.JLabel txtempid;
    private javax.swing.JTextField txtempname;
    private javax.swing.JTextField txtempphoneno;
    private javax.swing.JTextField txtsalary;
    // End of variables declaration//GEN-END:variables
}
