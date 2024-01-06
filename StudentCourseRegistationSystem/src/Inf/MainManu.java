
package Inf;

import DB.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MainManu extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement pst =null;
    ResultSet rs =null;

    
    public MainManu() {
        initComponents();
        
        //database cnnection class eka link kirima
        conn =DBconnect.connect();
        Register.requestFocus();
        
    }
    
    public void log(){
        
        String position = (String) SelectPosiction.getSelectedItem();
        String x="Admin";
        String y="Student";
        
        if(position.equals(x)){
            
            //database data get to variable
        String[][] data = new String [10][2] ;
         
            try {

                String sql = "SELECT * FROM password";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery(); 

                if (rs.next()) {

                    String Uname = rs.getString("UserName");
                    String Pwd = rs.getString("Password");

                    int i=0; 
                           data[i][0]=Uname;
                           data[i][1]=Pwd;
                           i++;

                        while (rs.next()) {
                            Uname = rs.getString("UserName");
                            Pwd = rs.getString("Password");
                            data[i][0] = Uname;
                            data[i][1]= Pwd;
                            i++;
                        }
                } else {
                         System.out.println("No data found.");
                       }

          } catch (Exception e) {

              JOptionPane.showMessageDialog(null, e);
          }
                            
            //password check
            String username   =UserName.getText();
            String password   =Password.getText();
            for(int i=0; i<10; i++){
                String uname =  data[i][0];
                String pwd   =  data[i][1];
                System.out.println(pwd+" "+password);
                if(uname.equals(username) && pwd.equals(password)){
                    Admin AdminObj = new Admin();
                    AdminObj.setVisible(true);
                    this.dispose();
                    break;
               }else{
                Password.setText("");
                Errorlabel.setText("Incorrect Password");
                }          
            }
            Password.setText("");
            Errorlabel.setText("Incorrect Password");
        }
        else if(position.equals(y)){
            
            //database data get to variable
        String[][] data = new String [200][3] ;
    
         
        try {

            String sql = "SELECT * FROM studentdetails";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(); 



            if (rs.next()) {

                String Uname = rs.getString("Name");
                String Course = rs.getString("Course");
                String Pwd   = rs.getString("Password");

                int i=0; 
                       data[i][0]=Uname;
                       data[i][1]=Course;
                       data[i][2]=Pwd;

                       i++;

                    while (rs.next()) {
                        Uname = rs.getString("Name");
                        Course = rs.getString("Course");
                        Pwd = rs.getString("Password");
                        data[i][0]=Uname;
                        data[i][1]=Course;
                        data[i][2]=Pwd;
                        i++;
                    }
            } else {
            System.out.println("No data found.");
            }

        }   catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            }

            //password check
            String username   =UserName.getText();
            String password   =Password.getText();
            for(int i=0; i<10; i++){
             String uname      =  data[i][0];
             String Coursedata =  data[i][1];
             String pwd        =  data[i][2];
              //System.out.println(pwd+" "+password);
             if(uname.equals(username) && pwd.equals(password)){

                 PersonalForm PersonalFormObj = new PersonalForm(uname,Coursedata);
                 PersonalFormObj.setVisible(true);
                 this.dispose();
                 break;
             }else{
                 Errorlabel.setText("Incorrect Password");
                 Password.setText("");
             }

            }
            
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel11 = new UI.Panel1();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        UserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        SelectPosiction = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Register = new javax.swing.JButton();
        Errorlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Welcome");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name");

        UserName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        Password.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        SelectPosiction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SelectPosiction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Your Position", "Student", "Admin" }));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Log");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Register.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Register.setText("Register ");
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });

        Errorlabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Errorlabel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SelectPosiction, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel11Layout.createSequentialGroup()
                                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))))
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(Errorlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(Register, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addGap(50, 50, 50)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(SelectPosiction, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Errorlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Register, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        RegForm RegFormObj = new RegForm();
        RegFormObj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegisterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       log();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       //exit wima
        int chck= JOptionPane.showConfirmDialog(null,"Do You Want To Exit");
        if( chck ==0){
          System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainManu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainManu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainManu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainManu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainManu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Errorlabel;
    private javax.swing.JPasswordField Password;
    private javax.swing.JButton Register;
    private javax.swing.JComboBox<String> SelectPosiction;
    private javax.swing.JTextField UserName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private UI.Panel1 panel11;
    // End of variables declaration//GEN-END:variables
}
