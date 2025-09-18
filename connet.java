package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static javafx.application.Application.launch;
import javafx.event.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.text.*;
public class connet extends Application{
    private TextField accountNumberField;
    private TextField firstName,midleName,lastName;
    private TextField ageField,gender;
    private ToggleGroup genderGroup;
    private TextField addressField;
    private TextField nationalityField;
    private TextField phoneNumberField;
    private TextField initialBalanceField;
    private TextField userName;
    private ComboBox box;
    private PasswordField password;
    private RadioButton maleRadio,femaleRadio;
    private String gender1;
    private double currentbalance;
    public void previousTransaction(String account,double amount) throws ClassNotFoundException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
            Statement  statment=con.createStatement();
            ResultSet rs=statment.executeQuery("select * from customer ");
            while(rs.next()){
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.now();
                String depositDate = date.format(dateTimeFormatter);
                if (rs.getString(1).equals(account)) {
                    FileWriter filewrier=new FileWriter("accounts.txt",true);
                    PrintWriter printwriter=new PrintWriter(filewrier);
                    printwriter.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+amount+","+depositDate);
                    printwriter.close();}}
                   }catch (SQLException ex) {
                    System.out.println("Error"+ ex);        }}
    public void deposit(String account, double Amount, int swap) throws ClassNotFoundException{
                        try{                 
                            Class.forName("com.mysql.jdbc.Driver"); 
                             Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                                 String updateacount="update  customer set intialbalance=intialbalance+? where acountnumber= ?";
                                   PreparedStatement statment1= con.prepareStatement(updateacount);
                                      statment1.setString(2, account);
                                        statment1.setDouble(1, Amount);
                                        int rowAfected=statment1.executeUpdate();
                                        if(swap==1){
                                        if(rowAfected>0){
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("deposit");
                                            alert.setHeaderText("deposit amount");
                                            alert.setContentText("amount deposited successfully");
                                            previousTransaction(account,+Amount);
                                            currentbalance=currentbalance+Amount;
                                            alert.showAndWait();
                                        }
                                        else{
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("deposit");
                                            alert.setHeaderText("deposit amount");
                                            alert.setContentText("amount deposited NOT successfully,PLEASE TRY AGAIN");
                                            alert.showAndWait();
                                        }}
                                        else{
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Transfer");
                                            alert.setHeaderText("Transfer");
                                            alert.setContentText("Transfer amount successfully ");
                                            previousTransaction(account,+Amount);
                                            alert.showAndWait();
                                        }
    }
                                        catch (SQLException |IOException ex) {
                                         Alert alert = new Alert(Alert.AlertType.ERROR);
                                         alert.setTitle("Error");
                                         alert.setHeaderText("Error Depositing or transfer");
                                         alert.setContentText("An error occurred while depositingor transfering ,and errors occured by:"+ex);
                                         alert.showAndWait();
                                            }}
    public void withdraw(String account, double amount, int swap) throws IOException{
    
                        try{                   
                           Class.forName("com.mysql.jdbc.Driver");
                              Connection  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                                  String updateacount="update  customer set intialbalance=intialbalance-? where acountnumber= ?";
                                    PreparedStatement statment1= con.prepareStatement(updateacount);
                                      statment1.setString(2, account);
                                        statment1.setDouble(1, amount);
                                        int rowAfected=statment1.executeUpdate();
                                        if(swap==1){
                                        if(rowAfected>0){
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("withdraw");
                                            alert.setHeaderText("withdraw amount");
                                            alert.setContentText("amount withdrawn successfully");
                                            previousTransaction(account,-amount);
                                            currentbalance=currentbalance-amount;
                                            alert.showAndWait();
                                        }
                                        else{
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("withdraw");
                                            alert.setHeaderText("withdraw amount");
                                            alert.setContentText("amount withdraw NOT successfully,PLEASE TRY AGAIN");
                                            alert.showAndWait();
                                        }}
                                        else{
                                            previousTransaction(account,-amount);
                                            System.out.println("this acount is recived birr");
                                      }}
                                    catch (SQLException|ClassNotFoundException ex) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Error withdrawing");
                                        alert.setContentText("An error occurred while withdrawing ,and errors occured by:"+ex);
                                        alert.showAndWait();
                             }}
    
     
                public int count(String account){
                                  
                                    int count=0;
                                    try {
                                        FileReader fileReader = new FileReader("accounts.txt");
                                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                                        String line1;
                                        while ((line1 = bufferedReader.readLine()) != null) {
                                            String[] parts2 = line1.split(",");
                                            if (parts2[0].equals(account)) {
                                            count++;
                                            }
                                        }                                                                           
                                    bufferedReader.close();
                                     
                                    } catch (IOException ex) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Error Retrieving Account Information");
                                        alert.setContentText("An error occurred while retrieving account information.");
                                        alert.showAndWait();
                                    }
                                        return count;
    
                                 }

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.setHgap(15);
        pane.setVgap(20);
        Text scenetitle = new Text("  LOGIN PAGE ");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        pane.add(scenetitle, 0, 0, 2, 1);
//textfield and pasword field
        Label lb1=new Label("UserName");
        lb1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        userName=new TextField();
        Label lb2=new Label("password");
        lb2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        password=new PasswordField();


//button
        Button tb=new Button("0k");
        Button btOK = new Button("Login");
        Button btn2=new Button("clear");
        btOK.setStyle("-fx-background-color:orange;-fx-border-color: blue;");
        btn2.setStyle("-fx-background-color:red;-fx-border-color: blue;");
        LoginBtn handle=new LoginBtn();
        btOK.setOnAction(handle);
        btn2.setOnAction(e->{
            userName.setText("");
            password.setText("");

        });

        pane.setAlignment(Pos.CENTER);
        pane.add(lb1,0,1);
        pane.add(userName,1,1);
        pane.add(lb2,0,2);
        pane.add(password,1,2);
        pane.add(btOK,0,3);
        pane.add(btn2,1,3);

        pane.setStyle("-fx-border-color: red; -fx-background-color: aqua;");
        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Bank Management System"); // Set the stage title
        primaryStage.setScene(scene);
// Place the scene in the 
        primaryStage.show();
    }// Display the stage

   
 class LoginBtn implements EventHandler<ActionEvent>{
        private TextField accountnum;
         private String Account,acc ;
            private Connection con;
            private  Statement statment;
            
         private void loginAccount(Stage PrimaryStage){
             String user = userName.getText();
            String password1 = password.getText();
    
          try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                Statement  statment=con.createStatement();
                ResultSet rs=statment.executeQuery("select * from userBank");
                while(rs.next()){
                    if(rs.getString("username").equals(user) && rs.getString("password").equals(password1)){
                    
                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setAlignment(Pos.CENTER);
                    Text scenetitl = new Text("BANK ACCOUNT LOGIN PAGE");
                    scenetitl.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    grid.add(scenetitl, 0, 0, 2, 1);
                    Label acccb=new Label("ACCOUNT NUMBER");
                    acccb.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
                    grid.add(acccb, 0, 1);
                    accountnum=new TextField();
                    accountnum.setPromptText("Entre Account No");
                    grid.add(accountnum, 1, 1);
                    Label creat=new Label("account is not created please click\n here create button");
                    grid.add(creat, 0, 2, 2, 1);
                    Button create=new Button("create ");
                    create.setStyle("-fx-background-color:orange;-fx-border-color: blue;-fx-border-radius:12px;-fx-padding-left: 200px;");
                    grid.add(create, 0, 3);
                    Button log=new Button("login");
                    log.setStyle("-fx-background-color:green;-fx-border-color: blue;-fx-border-radius:12px;-fx-padding-left: 200px;-fx-color:green;");
                    grid.add(log, 1, 3);
                    AccountCreate handle2=new AccountCreate();
             log.setOnAction(eee->{
                     boolean found=false;
                Account = accountnum.getText();
                if(Account.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter account number");
                    alert.showAndWait();
                    return;
                }
                if (Account.length() != 13 || !Account.startsWith("1000")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Account Number");
                    alert.setContentText("Account number must be 13 digits long and start with 1000.");
                    alert.showAndWait();
                    return;
                }
                else {
                    try{
                     Class.forName("com.mysql.jdbc.Driver");
                       Connection   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                         Statement   st=conn.createStatement();
                           ResultSet rss=st.executeQuery("select * from customer ");
                            while(rss.next()){
                              if (rss.getString(1).equals(Account)) {
                                found=true;
                                System.out.println(" fgfdwk"+rss.getString("intialbalance")+ "fgfghg"+rss.getString(10));
                                acc=rss.getString("acountnumber");
                                 currentbalance=rss.getDouble("intialbalance");
                                Stage stage2=new Stage();
                                Stage stage=new Stage();
                                GridPane grid3 = new GridPane();
                                grid3.setHgap(10);
                                grid3.setVgap(10);
                                grid3.setAlignment(Pos.CENTER);
                                grid3.setStyle("-fx-background-color:orange;-fx-border-color: blue;-fx-border-radius:12px;-fx-padding-left: 200px;");
                                Text scenetitle = new Text("Welcome to Banking System");
                                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                                grid3.add(scenetitle, 0, 0, 2, 1);

                    Button depositButton = new Button("Deposit");
                        grid3.add(depositButton, 0,1);
                            depositButton.setOnAction(ee->{
                                TextInputDialog textInputDialog = new TextInputDialog();
                                    textInputDialog.setTitle("Deposit");
                                    textInputDialog.setHeaderText("Enter Amount to Deposit");
                                    Optional<String> result = textInputDialog.showAndWait();
                                    if (result.get().isEmpty()) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("warnning");
                                        alert1.setHeaderText("Invalid Amount");
                                        alert1.setContentText("please enter amount");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    if(!result.get().matches("[0-9.-]+")){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("warning");
                                        alert.setHeaderText("Invalid amount");
                                        alert.setContentText("please only enter  anumber");
                                        alert.showAndWait();
                                        return;
                                    }
                                    double amount = Double.parseDouble(result.get());
                                    if (amount < 100) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("Error");
                                        alert1.setHeaderText("Invalid Amount");
                                        alert1.setContentText("Amount must be at least 100.");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    else{
                                        deposit(acc,amount ,1);
                                    }
                                        
                                });
                    Button withdrawButton = new Button("Withdraw");
                        grid3.add(withdrawButton, 0, 2);
                            withdrawButton.setOnAction(ee->{
                               TextInputDialog textInputDialog = new TextInputDialog();
                                    textInputDialog.setTitle("Withdraw");
                                    textInputDialog.setHeaderText("Enter withdrawal Amount ");
                                    Optional<String> result = textInputDialog.showAndWait();
                                    if (result.get().isEmpty()) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("warnning");
                                        alert1.setHeaderText("");
                                        alert1.setContentText("please enter amount");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    if(!result.get().matches("[0-9.-]+")){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("warning");
                                        alert.setHeaderText("Invalid amount");
                                        alert.setContentText("please only enter  anumber");
                                        alert.showAndWait();
                                        return;
                                    }
                                    double amount = Double.parseDouble(result.get());
                                    if (amount < 100) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("Error");
                                        alert1.setHeaderText("Invalid Amount");
                                        alert1.setContentText("Amount must be at least 100.");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    if (amount > currentbalance-50) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("warnning");
                                        alert1.setHeaderText("");
                                        alert1.setContentText("inseficient balance");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    
                                    else{
                                        withdraw(acc,amount,1);
                                    } 
                                });

                    Button checkBalanceButton = new Button("Check Balance");
                        grid3.add(checkBalanceButton, 0, 3);
                            checkBalanceButton.setOnAction(ee->{
                               try {
                                   Connection conn2= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                                   Statement s=conn2.createStatement();
                                        ResultSet intialb=s.executeQuery("select * from customer");
                                        while (intialb.next()){
                                            if(intialb.getString(1).equals(Account)){
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setTitle("Account Balance");
                                                alert.setHeaderText("Current Balance");
                                                alert.setContentText("Balance: "+intialb.getString(10));
                                                alert.showAndWait();
                                            }}}
                                    catch (SQLException ex) {
                                            System.out.println("Error"+ ex);
                                    }

                                });

                    Button previousTransactionsButton = new Button("Previous Transactions");
                        grid3.add(previousTransactionsButton, 1, 1);
                            previousTransactionsButton.setOnAction( ee->{
                                    boolean found1 = false;
                                    int i=1,count=count(Account);
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    
                                    try {
                                        FileReader fileReader = new FileReader("accounts.txt");
                                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                                       
                                        String line, priv="";
                                        alert1.setTitle("Transaction ");
                                        while ((line = bufferedReader.readLine()) != null){ 
                                            
                                            String[] parts = line.split(",");
                                            if (parts[0].equals(Account)) {
                                                      found1 = true;
                                                if(i>count-5){
                                                    i++;
                                                alert1.setHeaderText("Name :"+parts[1]+" "+parts[2]);
                                                priv += parts[9]+" at "+ parts[10]+"\n";
                                                }
                                                 else{
                                                    i++;
                                               }
                                            }}
                                        if (found1==false) {
                              
                                            
                                            alert1.setHeaderText("");
                                            alert1.setContentText("No previous transaction .");
                                            alert1.showAndWait();
                                        }
                                        else{
                                        alert1.setContentText(priv);
                                        alert1.showAndWait();}
                                                                                bufferedReader.close();
                                    } catch (IOException ex) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Error Retrieving Account Information");
                                        alert.setContentText("An error occurred while retrieving account information.");
                                        alert.showAndWait();
                                    }
                                });
                                Button displayInformationButton = new Button("Display Information");
                                grid3.add(displayInformationButton, 1, 2);
                                displayInformationButton.setOnAction(ee->{
                                    try{
                                    Connection  conn3= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                                        Statement st1=conn3.createStatement();
                                        ResultSet info=st1.executeQuery("select * from customer ");
                                        while(info.next()){
                                            if(info.getString(1).equals(acc)){
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setTitle("Account Information");
                                                alert.setHeaderText("Account Details");
                                                alert.setContentText("Account Number:" + info.getString(1) + "\nName: " + info.getString(2) +" "+info.getString(3)+ " "+info.getString(4)+"\nAge: " + info.getInt(5) + "\nGender: " + info.getString(6) + "\nAddress: " + info.getString(7)+ "\nNationality: " + info.getString(8) + "\nPhone Number: " + info.getString(9) + "\ncurrent Balance: " + info.getDouble(10));
                                                alert.showAndWait();
                                            } }}catch (SQLException ex) {
                                        System.out.println("error"+ ex);
                                    }

                                });


                                Button deleteButton = new Button("logout");
                                grid3.add(deleteButton, 1, 3);
                                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent ee) {
                                        Stage PrimaryStage=new Stage();
                                      loginAccount(PrimaryStage);}
                                });
                                Button Transfere = new Button("Transfere for other");
                                grid3.add(Transfere, 2, 1);
                                Transfere.setOnAction(ee->{
                                    TextInputDialog textInputDialog = new TextInputDialog();
                                    textInputDialog.setTitle("Transfer");
                                    textInputDialog.setHeaderText("Enter Transfer Account Number");
                                    Optional<String> result = textInputDialog.showAndWait();
                                    String accountum1=result.get();
                                    if(result.get().equals(acc)){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("warning");
                                        alert.setHeaderText("");
                                        alert.setContentText("This account is curently used ");
                                        alert.showAndWait();
                                        return;
                                    }
                                    if (result.get().isEmpty()) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("warnning");
                                        alert1.setHeaderText("");
                                        alert1.setContentText("please enter account number");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    if(!result.get().matches("[0-9]+"))
                                    {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("warning");
                                        alert.setHeaderText("Invalid  account number");
                                        alert.setContentText("please  enter only anumber ");
                                        alert.showAndWait();
                                        return;
                                    }
                                    if(!result.get().startsWith("1000")){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("warning");
                                        alert.setHeaderText("Invalid  account number");
                                        alert.setContentText("please  enter account number starts with 1000");
                                        alert.showAndWait();
                                        return;
                                    }
                                    if(result.get().length() !=13){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("warning");
                                        alert.setHeaderText("Invalid  account number");
                                        alert.setContentText("please  enter only 13 digit");
                                        alert.showAndWait();
                                        return;
                                    }
                                    boolean found1=false;
                                    try{
                                    Connection conn4= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                                    String updateacount="select accountnumber from customer";
                                    Statement statment1= conn4.createStatement();
                                    
                                    ResultSet rs2=statment1.executeQuery("select * from customer ");
                                    while(rs2.next()){
                                        if (rs2.getString(1).equals(result.get())){
                                            found1=true;
                                            
                                        }}
                                    
                                    
                                    }catch (SQLException ex) {
                                        System.out.println("Error"+ ex);
                                    }   
                                    if(found1==false){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("warnning");
                                        alert.setHeaderText("");
                                        alert.setContentText("Not found this account");
                                        alert.showAndWait();
                                        return;
                                    }
                                    TextInputDialog textInputDialog1 = new TextInputDialog();
                                    textInputDialog1.setTitle("Deposit");
                                    textInputDialog1.setHeaderText("Enter Amount to Deposit");
                                    Optional<String> result1 = textInputDialog1.showAndWait();
                                    if (result1.get().isEmpty()) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("warnning");
                                        alert1.setHeaderText("");
                                        alert1.setContentText("please enter amount");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    if(!result1.get().matches("[0-9.-]+")){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("warning");
                                        alert.setHeaderText("Invalid amount");
                                        alert.setContentText("please only enter  anumber");
                                        alert.showAndWait();
                                        return;
                                    }
                                    double amount = Double.parseDouble(result1.get());
                                    if (amount < 100) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("Error");
                                        alert1.setHeaderText("");
                                        alert1.setContentText("Amount must be at least 100.");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    if (amount > currentbalance-50) {
                                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("warnning");
                                        alert1.setHeaderText("");
                                        alert1.setContentText("inseficient balance");
                                        alert1.showAndWait();
                                        return;
                                    }
                                    deposit(accountum1,amount,0);
                                    withdraw(acc,amount,0); 
                                });
                                Scene scene=new Scene(grid3,500,300);
                                stage2.setScene(scene);
                                stage2.setTitle("Banking system");
                                stage2.show();
                                accountnum.clear();
                                break;
                            }}
                        if(found==false){
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("warnning");
                                            alert.setHeaderText("");
                                            alert.setContentText("NOT FOUND THIS ACOUNT");    
                                            alert.showAndWait();
                                            return;
                                        }
                        
                        
                        con.close();
                    }

                    catch (ClassNotFoundException | SQLException ex) {
                        System.out.println("cought error "+ ex);
                    }}});
                    create.setOnAction(handle2);
                    Scene scen = new Scene(grid, 400, 400);
                    PrimaryStage.setScene(scen); // Place the scene in the stage
                    PrimaryStage.show();
                }
                    else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("please check username and password");
                    alert.showAndWait();
                    return;
                    }
                }
            }catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            userName.clear();
            password.clear();
          
      }
        
        @Override
        public void handle(ActionEvent e) {
        
            Stage PrimaryStage=new Stage();
            String user = userName.getText();
            String password1 = password.getText();

            if(user.isEmpty() || password1.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your username");
                alert.showAndWait();
                return;
            }
            if(!user.matches("[a-zA-z0-9_$]+")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your correct user name");
                alert.showAndWait();
                return;
            }
        else {
          loginAccount(PrimaryStage);
         }
        }

        private void withdraw(String acc, double amount, int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void deposit(String acc, double amount, int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
 }

        class AccountCreate implements EventHandler<ActionEvent>{
            @Override
            public void handle(ActionEvent e) {
                Stage stage=new Stage();
                GridPane grid2 = new GridPane();
                grid2.setHgap(10);
                grid2.setVgap(10);
                Text scenetitle = new Text("Welcome to Banking System");
                scenetitle.setStyle("-fx-Text-fill:red;");
                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                grid2.add(scenetitle, 0, 0, 2, 1);
                Label accountNumberLabel = new Label("Account Number:");
                accountNumberField = new TextField();
                accountNumberField.setPromptText("1000-XXXXXXXXXXXX");

                Label nameLabel = new Label("FirstName:");
                firstName = new TextField();
                Label nameLabel1 = new Label("MidleName:");
                midleName = new TextField();
                Label nameLabel2 = new Label("lastName:");
                lastName = new TextField();
                Label ageLabel = new Label("Age:");
                ageField = new TextField();

                Label genderLabel = new Label("Gender:");
                genderGroup = new ToggleGroup();
                maleRadio = new RadioButton("Male");
                femaleRadio = new RadioButton("Female");
                gender1="";
                maleRadio.setToggleGroup(genderGroup);
                femaleRadio.setToggleGroup(genderGroup);

                Label addressLabel = new Label("Address:");
                addressField = new TextField();
             
                box=new ComboBox();
                
                Label nationalityLabel = new Label("Nationality:");
                nationalityField = new TextField();

                Label phoneNumberLabel = new Label("Phone Number:");
                phoneNumberField = new TextField("+251");

                Label initialBalanceLabel = new Label("Initial Balance:");
                initialBalanceField = new TextField();
                
                grid2.setAlignment(Pos.CENTER);

                grid2.add(accountNumberLabel, 0, 1);
                grid2.add(accountNumberField, 1, 1);
                grid2.add(nameLabel, 0, 2);
                grid2.add(firstName, 1, 2);
                grid2.add(nameLabel1, 0, 3);
                grid2.add(midleName, 1, 3);
                grid2.add(nameLabel2, 0, 4);
                grid2.add(lastName, 1, 4);

                grid2.add(ageLabel, 0, 5);
                grid2.add(ageField, 1, 5);
                grid2.add(genderLabel, 0, 6);
                grid2.add(maleRadio, 1, 6);
                grid2.add(femaleRadio, 2, 6);
                grid2.add(addressLabel, 0, 7);
                grid2.add(addressField, 1, 7);
                grid2.add(nationalityLabel, 0, 8);
                grid2.add(nationalityField, 1, 8);
                grid2.add(phoneNumberLabel, 0, 9);
                grid2.add(phoneNumberField, 1, 9);
                grid2.add(initialBalanceLabel, 0, 10);
                grid2.add(initialBalanceField, 1, 10);
                Button createAccountButton = new Button("Sign in");
                createAccountButton.setStyle("-fx-background-color:orange;-fx-border-color: blue;-fx-border-radius:12px;-fx-padding-left: 200px;");
                grid2.add(createAccountButton, 0, 11);
                AccountCreatd hanlde3=new AccountCreatd();
                createAccountButton.setOnAction(hanlde3);
                Button clear = new Button("clear");
                Button back=new Button("Back");
                grid2.setStyle("-fx-background-image:url('images4.jfif');");
                clear.setStyle("-fx-background-color:green;-fx-border-color: blue;-fx-border-radius:12px;-fx-padding-left: 200px;");
                grid2.add(clear, 1, 11);
                clear.setOnMouseClicked(handle ->{
                    accountNumberField.clear();
                    firstName.clear();
                    midleName.clear();
                    lastName.clear();
                    ageField.clear();
                    maleRadio.toBack();
                    femaleRadio.isDisable();
                    addressField.clear();
                    nationalityField.clear();
                    phoneNumberField.clear();
                    initialBalanceField.clear();
                });
                 grid2.add(back,3,11);
                back.setOnAction(new LoginBtn());
               
                Scene scene = new Scene(grid2, 600, 500);
                stage.setTitle("Bank Management System");
                stage.setScene(scene);
                stage.show();}
        }
        class AccountCreatd implements EventHandler<ActionEvent>{
            @Override
            public void handle(ActionEvent e) {
                Boolean found=true;
                String accountNumber = accountNumberField.getText();
                String fname = firstName.getText();
                String mname = midleName.getText();
                String lname = lastName.getText();
                int age1 = Integer.parseInt(ageField.getText());
                if(maleRadio.isSelected()||femaleRadio.isSelected()){
                if(maleRadio.isSelected()){
                gender1="MALE";
                 }
                if(femaleRadio.isSelected()){
                gender1="FEMALE";
                }}
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select customer gender");
                    alert.showAndWait();
                    return;  
                }
                String address = addressField.getText().toUpperCase();
                String nationality = nationalityField.getText().toUpperCase();
                String phoneNumber = phoneNumberField.getText();
        
                double initialBalance = Double.parseDouble(initialBalanceField.getText());
                if(accountNumber.isEmpty()||fname.isEmpty()||lname.isEmpty()||mname.isEmpty()|| gender1.isEmpty()|| address.isEmpty()||nationality.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter full information");
                    alert.showAndWait();
                    return;
                }
                if (accountNumber.length() != 13 || !accountNumber.startsWith("1000")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Account Number");
                    alert.setContentText("Account number must be 13 digits long and start with 1000.");
                    alert.showAndWait();
                    return;
                }
                if(!accountNumber.matches("[0-9]+")){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Account Number");
                    alert.setContentText("Account number only allowed digit.");
                    alert.showAndWait();
                    return;
                }
                if(!fname.matches("[A-Z]+")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("warning");
                    alert.setHeaderText("Invalid name");
                    alert.setContentText("please only enter alphabet and first name must be uppercase");
                    alert.showAndWait();
                    return;
                }
                                
                if(!mname.matches("[A-Z]+")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("warning");
                    alert.setHeaderText("Invalid name");
                    alert.setContentText("please only enter alphabet and midle name must be uppercase.");
                    alert.showAndWait();
                    return;
                }
                if(!lname.matches("[A-Z]+")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("warning");
                    alert.setHeaderText("Invalid name");
                    alert.setContentText("please only enter alphabet and last name must be uppercase.");
                    alert.showAndWait();
                    return;
                }
               
                if (age1 < 18 || age1 > 100) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("Age must be between 18 and 100.");
                    alert.showAndWait();
                    return;
                }
                 if(gender1.isEmpty()){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("please select customer gender");
                    alert.showAndWait();
                    return;
                  
                 }
                if(!nationality.matches("[A-Z]+")){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("warning");
                    alert.setHeaderText("Invalid nationality ");
                    alert.setContentText("please only enter alphabet and  must be uppercase");
                    alert.showAndWait();
                    return;  
                }
                if (initialBalance < 50) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Initial Balance");
                    alert.setContentText("Initial balance must be at least 50.");
                    alert.showAndWait();
                    return;
                }
                if (phoneNumber.length() != 13 || !phoneNumber.startsWith("+251")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Phone");
                    alert.setContentText("Phone number must start with +251 and be 13 digits long.");
                    alert.showAndWait();
                    return;
                }
                else {
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");                      
                        PreparedStatement statment= conn.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?)");
                        statment.setString(1,accountNumber);
                        statment.setString(2,fname);
                        statment.setString(3,mname);
                        statment.setString(4,lname);
                        statment.setInt(5,age1);
                        statment.setString(6,gender1);
                        statment.setString(7,address);
                        statment.setString(8,nationality);
                        statment.setString(9,phoneNumber);
                        statment.setDouble(10,initialBalance);
                         statment.executeUpdate();
                        conn.close();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("ACCOUNT");
                        alert.setHeaderText("Creating Account");
                        alert.setContentText("account created successfully");
                        alert.showAndWait();
                    }
                    catch (ClassNotFoundException | SQLException ex) {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error Creating Account");
                        alert.setContentText("An error occurred while creating the account." +ex);
                        alert.showAndWait();
                    }}
                accountNumberField.clear();
                firstName.clear();
                midleName.clear();
                lastName.clear();
                ageField.clear();
                femaleRadio.isDisabled();
                addressField.clear();
                nationalityField.clear();
                phoneNumberField.clear();
                initialBalanceField.clear();
            }}
    

    public static void main(String[] args) {
        launch(args);
    }

}

