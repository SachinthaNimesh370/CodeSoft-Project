
package Inf;

import DB.DBconnect;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class CourseDetails extends javax.swing.JFrame {
    
   Connection conn = null;
   PreparedStatement pst =null;
   ResultSet rs =null;

   
    public CourseDetails() {
        initComponents();
        
        //database cnnection class eka link kirima
        conn =DBconnect.connect();
        TableLoad();
        Tableheader();
    }
    public void Tableheader(){
        CourseTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16));
        CourseTable.getTableHeader().setOpaque(false);
        CourseTable.getTableHeader().setBackground(new Color (153,153,153));
        CourseTable.getTableHeader().setForeground(new Color (0,0,0));
        CourseTable.setRowHeight(25);    
    }
      
    public void TableLoad(){
         
        try {

          String sql = "SELECT * FROM coursedetails";
          pst = conn.prepareStatement(sql);
          rs = pst.executeQuery(); 
          CourseTable.setModel(DbUtils.resultSetToTableModel(rs));


      } catch (Exception e) {

          JOptionPane.showMessageDialog(null, e);
      }
    }
    
    public void Insert(){
    
        // row eke data text feild ekat awata passe  wenas karpu data variable wlt geniima
        String ID          = Id.getText();
        String Course1     = Course.getText();
        String Duration1   = Duration.getText();
        String Lectures1   = Lectures.getText();
        String StartDate1  = StartDate.getText();
        String Capacity1   = Capacity.getText();
        String RegStudent1 = RegStudent.getText();
        String Available1  = Available.getText();
        
        
        
        // Data base ekt values set kirim
            
        try {
                  String sql= "INSERT INTO coursedetails (ID,Course,Duration,Lecture,StartDate,Capacity,RegStudent,Available) VALUES ('"+ID+"','"+Course1+"','"+Duration1+"','"+Lectures1+"','"+StartDate1+"','"+Capacity1+"','"+RegStudent1+"','"+Available1+"') ";
                  pst= conn.prepareStatement(sql);
                  pst.execute();
                  JOptionPane.showMessageDialog(null,"Update Succesfull");
        } catch (Exception e) {  

                  JOptionPane.showMessageDialog(null, e);
          }
           
    }
    
    public void Update(){
             
        // row eke data text feild ekat awata passe  wenas karpu data variable wlt geniima
        String ID          = Id.getText();
        String Course1     = Course.getText();
        String Duration1   = Duration.getText();
        String Lectures1   = Lectures.getText();
        String StartDate1  = StartDate.getText();
        String Capacity1   = Capacity.getText();
        String RegStudent1 = RegStudent.getText();
        String Available1  = Available.getText();
        


        //variable wala data data base ekt insert kirima

        try {

            String sql = "UPDATE coursedetails SET  Course ='"+Course1+"' , Duration = '"+Duration1+"' , Lecture = '"+Lectures1+"' , StartDate = '"+StartDate1+"' , Capacity = '"+Capacity1+"' ,RegStudent = '"+RegStudent1+"' , Available = '"+Available1+"'   WHERE ID  ='"+ID+"' "; 
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
         
                             
    }
    
    public void Delete(){
         
        // Delet kirima                      
        int check =JOptionPane.showConfirmDialog(null,"Do You Want To Delete");
                      
        if(check == 0){
                      
            String id = Id.getText();
                                   
                try {

                    String sql = "DELETE FROM coursedetails WHERE ID ='"+id+"' ";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Delete Data");


                } catch (Exception e) {


                    JOptionPane.showMessageDialog(null, e);
                }
        }     
    }
    
    
    public void Tabledata(){
        
        //table row eke data variable ekat set kirma
        int r=CourseTable.getSelectedRow();
        
        String  ID            =    CourseTable.getValueAt(r, 0).toString();
        String  Course1       =    CourseTable.getValueAt(r, 1).toString();  
        String  Duration1     =    CourseTable.getValueAt(r, 2).toString();
        String  Lectures1     =    CourseTable.getValueAt(r, 3).toString();
        String  StartDate1    =    CourseTable.getValueAt(r, 4).toString();
        String  Capacity1     =    CourseTable.getValueAt(r, 5).toString();
        String  RegStudent1   =    CourseTable.getValueAt(r, 6).toString();
        String  Available1    =    CourseTable.getValueAt(r, 7).toString();
       


        // variable wala data text feld ekata zt kirima
        Id.setText(ID);
        Course.setText(Course1);
        Duration.setText(Duration1);
        Lectures.setText(Lectures1);
        StartDate.setText(StartDate1);
        Capacity.setText(Capacity1);
        RegStudent.setText(RegStudent1);
        Available.setText(Available1);
        
                              
    }
    
    public void clear(){
         
        Id.setText("");
        Course.setText("");
        Duration.setText("");
        Lectures.setText("");
        StartDate.setText("");
        Capacity.setText("");
        RegStudent.setText("");
        Available.setText("");
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel11 = new UI.Panel1();
        jLabel2 = new javax.swing.JLabel();
        Id = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Course = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Duration = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Lectures = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        StartDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Capacity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        RegStudent = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Available = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CourseTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");

        Id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                IdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                IdFocusLost(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Course");

        Course.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Course.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CourseFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CourseFocusLost(evt);
            }
        });
        Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CourseActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Duration");

        Duration.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Duration.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DurationFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                DurationFocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lectures");

        Lectures.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Lectures.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LecturesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                LecturesFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Start Date");

        StartDate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        StartDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                StartDateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                StartDateFocusLost(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Capacity");

        Capacity.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Capacity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CapacityFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CapacityFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Reg Student");

        RegStudent.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        RegStudent.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                RegStudentFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                RegStudentFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Available");

        Available.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Available.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                AvailableFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                AvailableFocusLost(evt);
            }
        });
        Available.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvailableActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Insert");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        CourseTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        CourseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        CourseTable.setRowHeight(35);
        CourseTable.setRowMargin(2);
        CourseTable.setSelectionBackground(new java.awt.Color(51, 153, 255));
        CourseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CourseTableMouseClicked(evt);
            }
        });
        CourseTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CourseTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(CourseTable);

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel11Layout.createSequentialGroup()
                                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel11Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(Available))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel11Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(RegStudent, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Lectures, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Duration, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Course, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Capacity, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(StartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jLabel2))
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Course, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Duration, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Lectures, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(RegStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Available, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdFocusGained
        
    }//GEN-LAST:event_IdFocusGained

    private void IdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdFocusLost
        
    }//GEN-LAST:event_IdFocusLost

    private void DurationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DurationFocusGained
        
    }//GEN-LAST:event_DurationFocusGained

    private void DurationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DurationFocusLost
       
    }//GEN-LAST:event_DurationFocusLost

    private void LecturesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LecturesFocusGained
        
    }//GEN-LAST:event_LecturesFocusGained

    private void LecturesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LecturesFocusLost
        
    }//GEN-LAST:event_LecturesFocusLost

    private void RegStudentFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RegStudentFocusGained
        
    }//GEN-LAST:event_RegStudentFocusGained

    private void RegStudentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RegStudentFocusLost
        
    }//GEN-LAST:event_RegStudentFocusLost

    private void AvailableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AvailableFocusGained
        
    }//GEN-LAST:event_AvailableFocusGained

    private void AvailableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AvailableFocusLost
        
    }//GEN-LAST:event_AvailableFocusLost

    private void AvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvailableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AvailableActionPerformed

    private void CourseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CourseFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_CourseFocusGained

    private void CourseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CourseFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_CourseFocusLost

    private void CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CourseActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clear();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Admin AdminObj = new Admin();
        AdminObj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Update();
        TableLoad();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CapacityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CapacityFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_CapacityFocusGained

    private void CapacityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CapacityFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_CapacityFocusLost

    private void StartDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_StartDateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_StartDateFocusGained

    private void StartDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_StartDateFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_StartDateFocusLost

    private void CourseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CourseTableMouseClicked
       Tabledata();
    }//GEN-LAST:event_CourseTableMouseClicked

    private void CourseTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CourseTableKeyReleased
       Tabledata();
    }//GEN-LAST:event_CourseTableKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Delete();
        TableLoad();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       Insert();
       TableLoad();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(CourseDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CourseDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CourseDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CourseDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Available;
    private javax.swing.JTextField Capacity;
    private javax.swing.JTextField Course;
    private javax.swing.JTable CourseTable;
    private javax.swing.JTextField Duration;
    private javax.swing.JTextField Id;
    private javax.swing.JTextField Lectures;
    private javax.swing.JTextField RegStudent;
    private javax.swing.JTextField StartDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Panel1 panel11;
    // End of variables declaration//GEN-END:variables
}
