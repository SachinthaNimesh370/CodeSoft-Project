
package Inf;

import DB.DBconnect;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;


public class MathsStudent extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst =null;
    ResultSet rs =null;
   
    
    public MathsStudent() {
        initComponents();
        Tableheader();
        //database cnnection class eka link kirima
        conn =DBconnect.connect();
        
        CreateGrade1.requestFocus();
        TableLoad();
    }
    
    public void Tableheader(){
        MarkTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16));
        MarkTable.getTableHeader().setOpaque(false);
        MarkTable.getTableHeader().setBackground(new Color (153,153,153));
        MarkTable.getTableHeader().setForeground(new Color (0,0,0));
        MarkTable.setRowHeight(25); 
       
    }
   
    public void TableLoad(){
     
        try {

            String sql = "SELECT ID,Name,MathsMark as Maths, MathsResult as Grade,PhysicsMark as Physics,PhysicsResult As Grade,ChemestryMark as Chemestry,ChemestryResult as Grade,AverageMark as Average FROM mathsstudent";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(); 
            MarkTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

          JOptionPane.showMessageDialog(null, e);
        }
    }
    public void Insert(){
        String  ID1          =Id.getText();
        String  Name1        =Name.getText();
        String  Maths1       =Maths.getText();
        String  MathsR1      =MathsResult.getText();
        String  Physics1     =Physics.getText();
        String  PhysicsR1    =PhysicsResult.getText();
        String  Chemestry1   =Chemestry.getText();
        String  ChemestryR1  =ChemestryResult.getText();
        String  Average1     =Average.getText();
        
        System.out.println(ID1+Name1+Maths1+MathsR1+Physics1+PhysicsR1+Chemestry1+ChemestryR1+Average1);
        
        // Data base ekt values set kirim
        try {
            String sql= "INSERT INTO mathsstudent (ID,Name,MathsMark,MathsResult,PhysicsMark,PhysicsResult,ChemestryMark,ChemestryResult,AverageMark) VALUES ('"+ID1+"','"+Name1+"','"+Maths1+"','"+MathsR1+"','"+Physics1+"','"+PhysicsR1+"','"+Chemestry1+"','"+ChemestryR1+"','"+Average1+"') ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Succesfull");
            Clear();
        } catch (Exception e) {  

            JOptionPane.showMessageDialog(null, e);
          }
    }
    
    public void Update(){
             
        // row eke data text feild ekat awata passe  wenas karpu data variable wlt geniima
        String  ID1          =Id.getText();
        String  Name1        =Name.getText();
        String  Maths1       =Maths.getText();
        String  MathsR1      =MathsResult.getText();
        String  Physics1     =Physics.getText();
        String  PhysicsR1    =PhysicsResult.getText();
        String  Chemestry1   =Chemestry.getText();
        String  ChemestryR1  =ChemestryResult.getText();
        String  Average1     =Average.getText();
       

        //variable wala data data base ekt insert kirima
        try {

           
            String sql = "UPDATE mathsstudent SET   Name ='"+Name1+"'  , MathsMark = '"+Maths1+"' , MathsResult = '"+MathsR1+"' , PhysicsMark = '"+Physics1+"' ,PhysicsResult = '"+PhysicsR1+"' , ChemestryMark = '"+Chemestry1+"' , ChemestryResult ='"+ChemestryR1+"' ,  AverageMark ='"+Average1+"'   WHERE ID  ='"+ID1+"' "; 
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
            Clear();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
         
                             
    }
    public void Tabledata(){
        
        //table row eke data variable ekat set kirma
        int r=MarkTable.getSelectedRow();


        String  ID1          =    MarkTable.getValueAt(r, 0).toString();
        String  Name1        =    MarkTable.getValueAt(r, 1).toString();  
        String  Maths1       =    MarkTable.getValueAt(r, 2).toString();
        String  MathsR1      =    MarkTable.getValueAt(r, 3).toString();
        String  Physics1     =    MarkTable.getValueAt(r, 4).toString();
        String  PhysicsR1    =    MarkTable.getValueAt(r, 5).toString();
        String  Chemestry1   =    MarkTable.getValueAt(r, 6).toString();
        String  ChemestryR1  =    MarkTable.getValueAt(r, 7).toString();
        String  Average1     =    MarkTable.getValueAt(r, 8).toString();
        


        // variable wala data text feld ekata zt kirima
        Id.setText(ID1);
        Name.setText(Name1);
        Maths.setText(Maths1);
        MathsResult.setText(MathsR1);
        Physics.setText(Physics1);
        PhysicsResult.setText(PhysicsR1);
        Chemestry.setText(Chemestry1);
        ChemestryResult.setText(ChemestryR1);
        Average.setText(Average1);
                              
    }
    
    public void MathsResult(){
        double maths =Double.parseDouble(Maths.getText());
        if(0 <= maths && maths<35 ){MathsResult.setText("W");}
        else if(maths <55){MathsResult.setText("S");}
        else if(maths <65){MathsResult.setText("C");}
        else if(maths <75){MathsResult.setText("B");}
        else if(maths <101){MathsResult.setText("A");}
        else{JOptionPane.showMessageDialog(null, "Maths Marks Invalid");}   
    }
    public void PhysicsResult(){
        double physics =Double.parseDouble(Physics.getText());
        if(0 <= physics && physics<35 ){PhysicsResult.setText("W");}
        else if(physics <55){PhysicsResult.setText("S");}
        else if(physics <65){PhysicsResult.setText("C");}
        else if(physics <75){PhysicsResult.setText("B");}
        else if(physics <101){PhysicsResult.setText("A");}
        else{JOptionPane.showMessageDialog(null, "Physics Marks Invalid");}   
    }
    public void ChemestryResult(){
        double chemestry =Double.parseDouble(Chemestry.getText());
        if(0 <= chemestry && chemestry<35 ){ChemestryResult.setText("W");}
        else if(chemestry <55){ChemestryResult.setText("S");}
        else if(chemestry <65){ChemestryResult.setText("C");}
        else if(chemestry <75){ChemestryResult.setText("B");}
        else if(chemestry <101){ChemestryResult.setText("A");}
        else{JOptionPane.showMessageDialog(null, "Chemestry Marks Invalid");}   
    }
    public void AverageMark(){
        double maths     =Double.parseDouble(Maths.getText());
        double physics   =Double.parseDouble(Physics.getText());
        double chemestry =Double.parseDouble(Chemestry.getText());
        double average= maths/3 +physics/3 +chemestry/3;
        Average.setText(Double.toString(average));
      
    }
    public void Delete(){
         
        // Delet kirima                      
        int check =JOptionPane.showConfirmDialog(null,"Do You Want To Delete");
                      
        if(check == 0){
                      
            String id = Id.getText();
                                   
                try {

                    String sql = "DELETE FROM mathsstudent WHERE ID ='"+id+"' ";
                    pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Delete Data");
                    Clear();


                } catch (Exception e) {


                    JOptionPane.showMessageDialog(null, e);
                }
        }     
    }
    public void CreateGrade(){
        MathsResult();
        PhysicsResult();
        ChemestryResult();
        AverageMark();
    }
    public void Clear(){
    // variable wala data text feld ekata zt kirima
        Id.setText("");
        Name.setText("");
        Maths.setText("");
        MathsResult.setText("");
        Physics.setText("");
        PhysicsResult.setText("");
        Chemestry.setText("");
        ChemestryResult.setText("");
        Average.setText("");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel11 = new UI.Panel1();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MarkTable = new javax.swing.JTable();
        panel21 = new UI.Panel2();
        jLabel2 = new javax.swing.JLabel();
        Id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Maths = new javax.swing.JTextField();
        MathsResult = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Physics = new javax.swing.JTextField();
        PhysicsResult = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Chemestry = new javax.swing.JTextField();
        ChemestryResult = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Average = new javax.swing.JTextField();
        CreateGrade1 = new javax.swing.JButton();
        CreateGrade2 = new javax.swing.JButton();
        CreateGrade4 = new javax.swing.JButton();
        CreateGrade3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Maths Student");

        MarkTable.setBackground(new java.awt.Color(153, 153, 153));
        MarkTable.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        MarkTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        MarkTable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        MarkTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MarkTableMouseClicked(evt);
            }
        });
        MarkTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MarkTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(MarkTable);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");

        Id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Id.setForeground(new java.awt.Color(153, 153, 153));
        Id.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Id.setText("000");
        Id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                IdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                IdFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Name");

        Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Name.setForeground(new java.awt.Color(153, 153, 153));
        Name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Name.setText("John.S.P.S");
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Maths");

        Maths.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Maths.setForeground(new java.awt.Color(153, 153, 153));
        Maths.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Maths.setText("00.0");
        Maths.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MathsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                MathsFocusLost(evt);
            }
        });
        Maths.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MathsKeyReleased(evt);
            }
        });

        MathsResult.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        MathsResult.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Physics");

        Physics.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Physics.setForeground(new java.awt.Color(153, 153, 153));
        Physics.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Physics.setText("00.0");
        Physics.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PhysicsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhysicsFocusLost(evt);
            }
        });
        Physics.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PhysicsKeyReleased(evt);
            }
        });

        PhysicsResult.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PhysicsResult.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Chemestry");

        Chemestry.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Chemestry.setForeground(new java.awt.Color(153, 153, 153));
        Chemestry.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Chemestry.setText("00.0");
        Chemestry.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ChemestryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ChemestryFocusLost(evt);
            }
        });
        Chemestry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ChemestryKeyReleased(evt);
            }
        });

        ChemestryResult.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ChemestryResult.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Avarage");

        Average.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Average.setForeground(new java.awt.Color(153, 153, 153));
        Average.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        CreateGrade1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CreateGrade1.setText("Insert");
        CreateGrade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGrade1ActionPerformed(evt);
            }
        });

        CreateGrade2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CreateGrade2.setText("Update");
        CreateGrade2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGrade2ActionPerformed(evt);
            }
        });

        CreateGrade4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CreateGrade4.setText("Delete");
        CreateGrade4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGrade4ActionPerformed(evt);
            }
        });

        CreateGrade3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CreateGrade3.setText("Clear");
        CreateGrade3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGrade3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel21Layout = new javax.swing.GroupLayout(panel21);
        panel21.setLayout(panel21Layout);
        panel21Layout.setHorizontalGroup(
            panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel21Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel21Layout.createSequentialGroup()
                        .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel21Layout.createSequentialGroup()
                                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(26, 26, 26))
                            .addGroup(panel21Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(90, 90, 90)))
                        .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Id)
                            .addComponent(Maths, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Physics, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Chemestry, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Name)
                            .addComponent(Average, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ChemestryResult, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PhysicsResult, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MathsResult, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel21Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CreateGrade4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CreateGrade1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CreateGrade2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CreateGrade3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );
        panel21Layout.setVerticalGroup(
            panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel21Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Maths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MathsResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Physics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhysicsResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Chemestry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ChemestryResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Average, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreateGrade1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateGrade2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreateGrade4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateGrade3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(panel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(597, 597, 597))
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(45, Short.MAX_VALUE))
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

    private void MarkTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MarkTableKeyReleased
        Tabledata();
    }//GEN-LAST:event_MarkTableKeyReleased

    private void MarkTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarkTableMouseClicked
        Tabledata();
    }//GEN-LAST:event_MarkTableMouseClicked

    private void CreateGrade4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGrade4ActionPerformed
        Delete();
        TableLoad();
    }//GEN-LAST:event_CreateGrade4ActionPerformed

    private void CreateGrade3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGrade3ActionPerformed
        Clear();
    }//GEN-LAST:event_CreateGrade3ActionPerformed

    private void CreateGrade2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGrade2ActionPerformed
        Update();
        TableLoad();
    }//GEN-LAST:event_CreateGrade2ActionPerformed

    private void CreateGrade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGrade1ActionPerformed
        Insert();
        TableLoad();
    }//GEN-LAST:event_CreateGrade1ActionPerformed

    private void ChemestryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChemestryKeyReleased
        CreateGrade();
    }//GEN-LAST:event_ChemestryKeyReleased

    private void ChemestryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ChemestryFocusLost
        if(Chemestry.getText().equals("")){

            Chemestry.setText("00.0");
            Chemestry.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_ChemestryFocusLost

    private void ChemestryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ChemestryFocusGained
        if(Chemestry.getText().equals("00.0")){

            Chemestry.setText("");
            Chemestry.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_ChemestryFocusGained

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    private void NameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NameFocusLost
        if(Name.getText().equals("")){

            Name.setText("John.S.P.S");
            Name.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_NameFocusLost

    private void NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NameFocusGained
        if(Name.getText().equals("John.S.P.S")){

            Name.setText("");
            Name.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_NameFocusGained

    private void MathsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MathsKeyReleased
        CreateGrade();
    }//GEN-LAST:event_MathsKeyReleased

    private void MathsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MathsFocusLost
        if(Maths.getText().equals("")){

            Maths.setText("00.0");
            Maths.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_MathsFocusLost

    private void MathsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MathsFocusGained
        if(Maths.getText().equals("00.0")){

            Maths.setText("");
            Maths.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_MathsFocusGained

    private void PhysicsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhysicsKeyReleased
        CreateGrade();
    }//GEN-LAST:event_PhysicsKeyReleased

    private void PhysicsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhysicsFocusLost
        if(Physics.getText().equals("")){

            Physics.setText("00.0");
            Physics.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_PhysicsFocusLost

    private void PhysicsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhysicsFocusGained
        if(Physics.getText().equals("00.0")){

            Physics.setText("");
            Physics.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_PhysicsFocusGained

    private void IdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdFocusLost
        if(Id.getText().equals("")){

            Id.setText("000");
            Id.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_IdFocusLost

    private void IdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdFocusGained
        if(Id.getText().equals("000")){

            Id.setText("");
            Id.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_IdFocusGained

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
            java.util.logging.Logger.getLogger(MathsStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MathsStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MathsStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MathsStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MathsStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Average;
    private javax.swing.JTextField Chemestry;
    private javax.swing.JTextField ChemestryResult;
    private javax.swing.JButton CreateGrade1;
    private javax.swing.JButton CreateGrade2;
    private javax.swing.JButton CreateGrade3;
    private javax.swing.JButton CreateGrade4;
    private javax.swing.JTextField Id;
    private javax.swing.JTable MarkTable;
    private javax.swing.JTextField Maths;
    private javax.swing.JTextField MathsResult;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Physics;
    private javax.swing.JTextField PhysicsResult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Panel1 panel11;
    private UI.Panel2 panel21;
    // End of variables declaration//GEN-END:variables
}
