
package Inf;

import DB.DBconnect;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class StudentDetails extends javax.swing.JFrame {
    
   Connection conn = null;
   PreparedStatement pst =null;
   ResultSet rs =null;

   
    public StudentDetails() {
        initComponents();
        
        //database cnnection class eka link kirima
        conn =DBconnect.connect();
        TableLoad();
        Tableheader();
        StudentTable.requestFocus();
    }
    
    public void Tableheader(){
        StudentTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16));
        StudentTable.getTableHeader().setOpaque(false);
        StudentTable.getTableHeader().setBackground(new Color (153,153,153));
        StudentTable.getTableHeader().setForeground(new Color (0,0,0));
        StudentTable.setRowHeight(25);    
    }
     public void SearchNameID(){
         
        //search bar

        String srch = SearchBar.getText();

        try {
                String sql = " SELECT * FROM studentdetails where  Name LIKE '%"+srch+"%' or RegNo LIKE '%"+srch+"%' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                StudentTable.setModel(DbUtils.resultSetToTableModel(rs));

         } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
             }
       }
     
     
    public void TableLoad(){
    
         
        try {

          String sql = "SELECT * FROM studentdetails";
          pst = conn.prepareStatement(sql);
          rs = pst.executeQuery(); 
          StudentTable.setModel(DbUtils.resultSetToTableModel(rs));


      } catch (Exception e) {

          JOptionPane.showMessageDialog(null, e);
      }
    }
    
    public void Update(){
             
        // row eke data text feild ekat awata passe  wenas karpu data variable wlt geniima
        String ID          = Id.getText();
        String Name1       = Name.getText();
        String Ni1         = Nic.getText();
        String BirthDay1   = BirthDay.getText();
        String Sex1        = Sex.getText();
        String Course1     = Course.getText();
        String Email1      = Email.getText();
        String Telephone1  = Telephone.getText();
        String Password1   = Password.getText();


        //variable wala data data base ekt insert kirima

        try {

            String sql = "UPDATE studentdetails SET  Name ='"+Name1+"' , Nic = '"+Ni1+"' , BirthDay = '"+BirthDay1+"' , Gender = '"+Sex1+"' , Course = '"+Course1+"' ,Email = '"+ Email1+"' , Telephone = '"+ Telephone1+"' , Password ='"+Password1+"'  WHERE RegNo  ='"+ID+"' "; 
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
                      
            String id       = Id.getText();
            String Course1  = Course.getText();                       
                try {

                    String sql = "DELETE FROM studentdetails WHERE RegNo ='"+id+"' ";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Delete Data");
                    Available(Course1);


                } catch (Exception e) {


                    JOptionPane.showMessageDialog(null, e);
                }
        }     
    }
    
    public void Available(String course) {
         //database data get to variable
        String[][] data = new String [10][4] ;
        String Course1 = course;
         
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
                            
            
            
           
            for(int i=0; i<10; i++){
                String Course =  data[i][0];
               
                System.out.println(Course1+" "+Course);
                if(Course.equals(Course1)) {
                    
                        String x=data[i][2];
                    int    RegStudent = Integer.parseInt(x);
                           RegStudent = RegStudent-1;
                    String regStudent = Integer.toString(RegStudent);
                    int   Available   = Integer.parseInt(data[i][3]); 
                          Available   = Available+1;
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
    
    
    public void Tabledata(){
        
        //table row eke data variable ekat set kirma
        int r=StudentTable.getSelectedRow();


        String  ID            =    StudentTable.getValueAt(r, 0).toString();
        String  Name1         =    StudentTable.getValueAt(r, 1).toString();  
        String  Ni1           =    StudentTable.getValueAt(r, 2).toString();
        String  BirthDay1     =    StudentTable.getValueAt(r, 3).toString();
        String  Sex1          =    StudentTable.getValueAt(r, 4).toString();
        String  Course1       =    StudentTable.getValueAt(r, 5).toString();
        String  Email1        =    StudentTable.getValueAt(r, 6).toString();
        String  Telephone1    =    StudentTable.getValueAt(r, 7).toString();
        String  Password1     =    StudentTable.getValueAt(r, 8).toString();


        // variable wala data text feld ekata zt kirima
        Id.setText(ID);
        Name.setText(Name1);
        Nic.setText(Ni1);
        BirthDay.setText(BirthDay1);
        Sex.setText(Sex1);
        Course.setText(Course1);
        Email.setText(Email1);
        Telephone.setText(Telephone1);
        Password.setText(Password1);
                              
    }
    
    public void clear(){
         
        Id.setText("");
        Name.setText("");
        Nic.setText("");
        BirthDay.setText("");
        Sex.setText("");
        Course.setText("");
        Email.setText("");
        Telephone.setText("");
        Password.setText("");
                                   
         
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel11 = new UI.Panel1();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StudentTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Id = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        Nic = new javax.swing.JTextField();
        BirthDay = new javax.swing.JTextField();
        Sex = new javax.swing.JTextField();
        Course = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        Telephone = new javax.swing.JTextField();
        Password = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        SearchBar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Details");

        StudentTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        StudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        StudentTable.setRowHeight(35);
        StudentTable.setRowMargin(2);
        StudentTable.setSelectionBackground(new java.awt.Color(51, 153, 255));
        StudentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentTableMouseClicked(evt);
            }
        });
        StudentTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                StudentTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(StudentTable);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Reg No");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nic");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Birth Day");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Gender");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Course");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email Addres");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Telephone Number");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Password");

        Id.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Id.setForeground(new java.awt.Color(51, 51, 51));
        Id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                IdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                IdFocusLost(evt);
            }
        });
        Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdActionPerformed(evt);
            }
        });
        Id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                IdKeyReleased(evt);
            }
        });

        Name.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Name.setForeground(new java.awt.Color(51, 51, 51));
        Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NameFocusLost(evt);
            }
        });
        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NameKeyReleased(evt);
            }
        });

        Nic.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Nic.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NicFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NicFocusLost(evt);
            }
        });

        BirthDay.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BirthDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BirthDayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BirthDayFocusLost(evt);
            }
        });

        Sex.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Sex.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SexFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SexFocusLost(evt);
            }
        });

        Course.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Course.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CourseFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CourseFocusLost(evt);
            }
        });

        Email.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmailFocusLost(evt);
            }
        });

        Telephone.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Telephone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TelephoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TelephoneFocusLost(evt);
            }
        });
        Telephone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelephoneActionPerformed(evt);
            }
        });

        Password.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFocusLost(evt);
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

        SearchBar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SearchBar.setForeground(new java.awt.Color(102, 102, 102));
        SearchBar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SearchBar.setText("Search");
        SearchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SearchBarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SearchBarFocusLost(evt);
            }
        });
        SearchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchBarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panel11Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panel11Layout.createSequentialGroup()
                                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Sex, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Course, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                        .addComponent(Email, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Telephone, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                        .addComponent(Nic, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(BirthDay, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(Id, javax.swing.GroupLayout.Alignment.LEADING)))))
                    .addComponent(SearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(519, 519, 519))
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel11Layout.createSequentialGroup()
                        .addComponent(SearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Nic, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(BirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(Sex, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Course, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdFocusGained
       
    }//GEN-LAST:event_IdFocusGained

    private void IdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdFocusLost
         
    }//GEN-LAST:event_IdFocusLost

    private void NicFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NicFocusGained
        
    }//GEN-LAST:event_NicFocusGained

    private void NicFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NicFocusLost
       
    }//GEN-LAST:event_NicFocusLost

    private void BirthDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BirthDayFocusGained
        
    }//GEN-LAST:event_BirthDayFocusGained

    private void BirthDayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BirthDayFocusLost
        
    }//GEN-LAST:event_BirthDayFocusLost

    private void EmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailFocusGained
        
    }//GEN-LAST:event_EmailFocusGained

    private void EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailFocusLost
        
    }//GEN-LAST:event_EmailFocusLost

    private void TelephoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TelephoneFocusGained
        
    }//GEN-LAST:event_TelephoneFocusGained

    private void TelephoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TelephoneFocusLost
        
    }//GEN-LAST:event_TelephoneFocusLost

    private void TelephoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelephoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelephoneActionPerformed

    private void PasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFocusGained
        
    }//GEN-LAST:event_PasswordFocusGained

    private void PasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFocusLost
        
    }//GEN-LAST:event_PasswordFocusLost

    private void NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NameFocusGained
       
    }//GEN-LAST:event_NameFocusGained

    private void NameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NameFocusLost
       
    }//GEN-LAST:event_NameFocusLost

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

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

    private void CourseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CourseFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_CourseFocusGained

    private void CourseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CourseFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_CourseFocusLost

    private void SexFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SexFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_SexFocusGained

    private void SexFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SexFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_SexFocusLost

    private void StudentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentTableMouseClicked
       Tabledata();
    }//GEN-LAST:event_StudentTableMouseClicked

    private void StudentTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StudentTableKeyReleased
       Tabledata();
    }//GEN-LAST:event_StudentTableKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Delete();
        TableLoad();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameKeyReleased
       
    }//GEN-LAST:event_NameKeyReleased

    private void IdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyReleased
      
    }//GEN-LAST:event_IdKeyReleased

    private void IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdActionPerformed

    private void SearchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchBarKeyReleased
        SearchNameID();
    }//GEN-LAST:event_SearchBarKeyReleased

    private void SearchBarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchBarFocusGained
       if(SearchBar.getText().equals("Search")){

        SearchBar.setText("");
        SearchBar.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_SearchBarFocusGained

    private void SearchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchBarFocusLost
       if(SearchBar.getText().equals("")){

        SearchBar.setText("Search");
        SearchBar.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_SearchBarFocusLost

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
            java.util.logging.Logger.getLogger(StudentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BirthDay;
    private javax.swing.JTextField Course;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Id;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Nic;
    private javax.swing.JTextField Password;
    private javax.swing.JTextField SearchBar;
    private javax.swing.JTextField Sex;
    private javax.swing.JTable StudentTable;
    private javax.swing.JTextField Telephone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Panel1 panel11;
    // End of variables declaration//GEN-END:variables
}
