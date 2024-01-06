
package Inf;

import DB.DBconnect;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class RegForm extends javax.swing.JFrame {
   Connection conn = null;
   PreparedStatement pst =null;
   ResultSet rs =null;

    
    public RegForm() {
        initComponents();
        
        
        //database cnnection class eka link kirima
        conn =DBconnect.connect();
        
        searchcombobox();
        Register.requestFocus();
        
    }
    public void Clear(){
         Name.setText("");
        Nic.setText("");
         BirthDay.setText("");
         Sex.setSelectedIndex(0);
        Course.setSelectedIndex(0);
        Email.setText("");
        Telephone.setText("");
         Password.setText("");
    }
    
    
    public void searchcombobox(){
    
        String sql = "select * from coursedetails";
            try {
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();
                while(rs.next()){Course.addItem(rs.getString("Course"));}
            } catch (Exception e) {
            }
    }
    
    public void Insert(){
    
        String Name1       = Name.getText();
        String Ni1         = Nic.getText();
        String BirthDay1   = BirthDay.getText();
        String Sex1        = Sex.getSelectedItem().toString();
        String Course1     = Course.getSelectedItem().toString();
        String Email1      = Email.getText();
        String Telephone1  = Telephone.getText();
        String Password1   = Password.getText();
        
        System.out.println(Sex1+Course1);
        
        // Data base ekt values set kirim
            
        try {
                  String sql= "INSERT INTO studentdetails(Name,Nic,BirthDay,Gender,Course,Email,Telephone,Password) VALUES ('"+Name1+"','"+Ni1+"','"+BirthDay1+"','"+Sex1+"','"+Course1+"','"+Email1+"','"+Telephone1+"','"+Password1+"') ";
                  pst= conn.prepareStatement(sql);
                  pst.execute();
                  JOptionPane.showMessageDialog(null,"Registation Succesfull");
        } catch (Exception e) {  

                  JOptionPane.showMessageDialog(null, e);
          }
           
    }
    public void Available() {
         //database data get to variable
        String[][] data = new String [10][4] ;
         
            try {

                String sql = "SELECT * FROM coursedetails";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery(); 

                if (rs.next()) {

                    String Course     = rs.getString("Course");
                    String Capacity   = rs.getString("Capacity");
                    String RegStudent = rs.getString("RegStudent");
                    String Available   = rs.getString("Available");

                    int i=0; 
                           data[i][0]=Course;
                           data[i][1]=Capacity;
                           data[i][2]=RegStudent;
                           data[i][3]=Available;
                           i++;

                        while (rs.next()) {
                            Course     = rs.getString("Course");
                            Capacity   = rs.getString("Capacity");
                            RegStudent = rs.getString("RegStudent");
                            Available   = rs.getString("Available");
                            data[i][0]=Course;
                            data[i][1]=Capacity;
                            data[i][2]=RegStudent;
                            data[i][3]=Available;
                            i++;
                        }
                } else {
                         System.out.println("No data found.");
                       }

          } catch (Exception e) {

              JOptionPane.showMessageDialog(null, e);
          }
                            
            
            String course   =Course.getSelectedItem().toString();
           
            for(int i=0; i<10; i++){
                String Course =  data[i][0];
               
                System.out.println(course+" "+Course);
                if(Course.equals(course)) {
                    
                        String x=data[i][2];
                    int    RegStudent = Integer.parseInt(x);
                           RegStudent = RegStudent+1;
                    String regStudent = Integer.toString(RegStudent);
                    int   Available   = Integer.parseInt(data[i][3]); 
                          Available   = Available-1;
                    String available  = Integer.toString(Available);
                  
                String sql = "UPDATE coursedetails SET   RegStudent ='"+regStudent+"',Available ='"+available+"'  WHERE Course ='"+Course+"' "; 
                    try {
                        pst= conn.prepareStatement(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(RegForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        pst.execute();
                    } catch (SQLException ex) {
                        Logger.getLogger(RegForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                        
                    
                    break;
               }else{
                
                }          
            }
           
        }
        
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel11 = new UI.Panel1();
        jLabel1 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Nic = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BirthDay = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Sex = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        Course = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        Telephone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Password = new javax.swing.JTextField();
        Register = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Corse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name");

        Name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nic");

        Nic.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Birth Day");

        BirthDay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Gender");

        Sex.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Course");

        Course.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Your Course" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Email");

        Email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Telephone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Telephone");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Password");

        Password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Register.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Register.setText("Register");
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Registation Form");

        Corse.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Corse.setText("Course Details");
        Corse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Corse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel11Layout.createSequentialGroup()
                                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(79, 79, 79)
                                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel11Layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Register))
                                    .addComponent(Sex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Course, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Email)
                                    .addComponent(Telephone)
                                    .addComponent(Password)
                                    .addGroup(panel11Layout.createSequentialGroup()
                                        .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(Nic)
                                    .addComponent(BirthDay))))))
                .addGap(85, 85, 85))
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel9)
                .addGap(58, 58, 58)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Nic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(BirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Sex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Course, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(Register))
                .addGap(18, 18, 18)
                .addComponent(Corse, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
       Insert();
       
       Available();
       
       Clear();
    }//GEN-LAST:event_RegisterActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MainManu MainManuObj = new MainManu();
        MainManuObj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       Clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void CorseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorseActionPerformed
        CorseSummery CorseSummeryObj = new CorseSummery();
        CorseSummeryObj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CorseActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegForm().setVisible(true);
            }
        });
    }
    




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BirthDay;
    private javax.swing.JButton Corse;
    private javax.swing.JComboBox<String> Course;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Nic;
    private javax.swing.JTextField Password;
    private javax.swing.JButton Register;
    private javax.swing.JComboBox<String> Sex;
    private javax.swing.JTextField Telephone;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private UI.Panel1 panel11;
    // End of variables declaration//GEN-END:variables
}
