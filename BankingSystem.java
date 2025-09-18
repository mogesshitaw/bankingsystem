package com.example.demo2;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.w3c.dom.Text;

import javafx.event.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.*;
import javafx.util.converter.IntegerStringConverter;
public class BankingSystem extends Application{
    private TextField accountNumberField;
    private TextField firstName,midleName,lastName;
    private TextField ageField,gender;
    private ToggleGroup genderGroup;
    private TextField addressField;
    private TextField phoneNumberField;
    private TextField initialBalanceField;
    private TextField userName;
    private PasswordField password,password1,password2;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private ComboBox<String> roleComboBox;
    private ComboBox<String>  usert;
    private String  gender1,acc;
    private int  permission;
    private double currentbalance;
    private Button btn;
    //Image icon=new Image("CBE.jpg");
    @Override
    public void start(Stage primaryStage) {
               primaryStage.setTitle("Banking System");
               loginPage(primaryStage);
           }
    public void loginPage(Stage primaryStage) {
               GridPane pane = new GridPane(); //
               pane.setHgap(15);
               pane.setVgap(20);
               VBox root=new VBox();
               Label green=new Label();
               green.setStyle("-fx-background-color:green;-fx-min-width:200; border:none;");
               Label yellow=new Label();
               yellow.setStyle("-fx-background-color:yellow;-fx-min-width:200; border:none;");
               Label red=new Label();
               red.setStyle("-fx-background-color:red;-fx-min-width:200; border:none;");
               Label ac=new Label("ETHIOPIA");
               ac.setStyle("-fx-font-size:50px;-fx-min-width:200; ");
               root.getChildren().addAll(green,yellow,red);
               Label logo=new Label();
               logo.setStyle("-fx-background-image:url('cbe-logo.png');-fx-background-size:100px;-fx-background-repeat:no-repeat;-fx-min-width:100; border:none;");
               logo.setPrefHeight(80);
               Label cbe=new Label("Commertial Bank of Ethiopia");
               cbe.setStyle("-fx-font-size:30px; -fx-font-weight:bold;");
               pane.add(logo, 0, 1,1,1);
               pane.add(cbe, 1, 1,3,1);
               pane.add(root, 1, 0);
               pane.add(ac, 2, 0);
               Label scenetitle = new Label("Wellcome to Back ");
               scenetitle.setTextFill(Color.WHITE);
               scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
               scenetitle.setStyle("-fx-font-size:20px;-fx-text-fill:black;");
               pane.add(scenetitle, 1, 2, 3, 1);

            usert=new ComboBox<>();
           usert.setStyle("-fx-font-size:20px;-fx-text-fill:black;");
           usert.getItems().addAll("user","admin");
           usert.setPromptText("please select username");
           pane.add(usert, 1, 4);
           usert.setOnAction(ee->{
               String usertype=usert.getValue();
                login(primaryStage,usertype);
           });
           pane.setAlignment(Pos.CENTER);
           pane.setStyle("-fx-border-color: red; -fx-background-image:url('new9.');-fx-background-size:cover;");
           Scene scene = new Scene(pane, 900, 550);
           primaryStage.setTitle("Banking System"); // Set the stage title
           //primaryStage.getIcons().add(icon);
           primaryStage.setScene(scene);
           // Place the scene in the
           primaryStage.show();
      }
    public void CreateUser(Stage primaryStage ,String userTyp,String user,String password5) {
           if(userTyp=="admin"){
           GridPane grid2 = new GridPane();
           Label logo=new Label();
           grid2.setHgap(8);
           grid2.setVgap(5);
           logo.setStyle("-fx-background-image:url('cbe-logo.png');-fx-background-size:100px;-fx-background-repeat:no-repeat;-fx-min-width:100; border:none;");
           logo.setPrefHeight(80);
           Label cbe=new Label("Create new bank user ");
           cbe.setStyle("-fx-font-size:30px; -fx-font-weight:bold;-fx-text-fill:aqua;");
           Label nameLabel = new Label("FirstName:");
           nameLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
           firstName = new TextField();
           Label nameLabel1 = new Label("MidleName:");
           nameLabel1.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
           midleName = new TextField();
           Label nameLabel2 = new Label("lastName:");
           nameLabel2.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
           lastName = new TextField();
           Label ageLabel = new Label("Age:");
           ageLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
           ageField = new TextField();
           Label genderLabel = new Label("Gender:");
           genderLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
           genderGroup = new ToggleGroup();
           maleRadio = new RadioButton("Male");
           maleRadio.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
           femaleRadio = new RadioButton("Female");
           femaleRadio.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
           gender1="";
           maleRadio.setToggleGroup(genderGroup);
           femaleRadio.setToggleGroup(genderGroup);
       Label addressLabel = new Label("Address:");
       addressLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
       addressField = new TextField();
       Label nationalityLabel = new Label("Nationality:");
       nationalityLabel .setStyle("-fx-text-fill:white;-fx-font-size:20px;");
       roleComboBox=new ComboBox<>();
       roleComboBox.getItems().addAll("ETHIOPIA","USA","NIGERIA","EGYPT");

       Label phoneNumberLabel = new Label("Phone Number:");
       phoneNumberLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
       phoneNumberField = new TextField("+251");

       Label passwordLabel = new Label("password");
       passwordLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
       password=new PasswordField();
       Label userTypeLabel = new Label("User Type");
       userTypeLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
       usert=new ComboBox<>();
       usert.getItems().addAll("user","admin");

       Button back=new Button("back");
       back.setStyle("-fx-background-color:red;-fx-font-size:19px;-fx-text-fill:black;-fx-border-radius:12px;-fx-min-width:50px;");
       grid2.add(back, 4, 0);
       grid2.add(logo, 0, 0,1,1);
       grid2.add(cbe, 1, 0,3,1);

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
       grid2.add(roleComboBox, 1, 8);
       grid2.add(phoneNumberLabel, 0, 9);
       grid2.add(phoneNumberField, 1, 9);
       grid2.add(passwordLabel, 0, 10);
       grid2.add(password,1,10);
       grid2.add(userTypeLabel,0,11);
       grid2.add(usert, 1, 11);
       back.setOnAction(e->{loginAccount(primaryStage,userTyp,user,password5);});
       Button createAccountButton = new Button("Create");
       createAccountButton.setStyle("-fx-background-color:orange;-fx-border-color: blue;-fx-font-size:19px;-fx-border-radius:12px;-fx-min-width:100px;");
       grid2.add(createAccountButton, 0, 12);
       Button clear = new Button("Cancel");
       clear.setStyle("-fx-background-color:green;-fx-text-fill: yellow;-fx-font-size:19px;-fx-border-color:black;-fx-border-radius:12px;-fx-min-width:100px;");
       grid2.add(clear, 1, 12);
       createAccountButton.setOnAction(e->{
           String fname = firstName.getText();
           String mname = midleName.getText();
           String lname = lastName.getText();
            String age=ageField.getText();

           if(maleRadio.isSelected()||femaleRadio.isSelected()){
               if(maleRadio.isSelected()){
                   gender1="MALE";
               }
               if(femaleRadio.isSelected()){
                   gender1="FEMALE";
               }}

           String address = addressField.getText().toUpperCase();
           String nationality=roleComboBox.getValue();
           String phoneNumber = phoneNumberField.getText();
           String password1=password.getText();
           String usertype=usert.getValue();

           if(fname.isEmpty()||lname.isEmpty()||mname.isEmpty()||age.isEmpty()|| gender1.isEmpty()|| address.isEmpty()||nationality.isEmpty()||password1.isEmpty()||usertype.isEmpty()){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Warning");
               alert.setHeaderText(null);
               alert.setContentText("Please enter full information");
               alert.showAndWait();
               return;
           }
           int age1 = Integer.parseInt(ageField.getText()); 

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

           if (phoneNumber.length() != 13 || !phoneNumber.startsWith("+251")) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Invalid Phone");
               alert.setContentText("Phone number must start with +251 and be 13 digits long.");
               alert.showAndWait();
               return;
           }
                      UserCreated(fname,mname,lname,age1,gender1,address,nationality,phoneNumber,password1,usertype);
       });
       clear.setOnMouseClicked(handle ->{

           firstName.clear();
           midleName.clear();
           lastName.clear();
           ageField.clear();
           gender1="";
           addressField.clear();
           phoneNumberField.clear();


       });

       grid2.setAlignment(Pos.CENTER);
       grid2.setStyle("-fx-border-color: red; -fx-background-image:url('new8.png');-fx-background-size:cover;");
       Scene scene = new Scene(grid2, 900, 550);
       primaryStage.setTitle("Banking System"); // Set the stage title
       //primaryStage.getIcons().add(icon);
       primaryStage.setScene(scene);
       // Place the scene in the
       primaryStage.show();}
       else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("warning");
               alert.setHeaderText("");
               alert.setContentText("please try again. This username not allowed this page.please check you selected uesr type. ");
               alert.showAndWait();
               return;
       }
      //
   }
    public  void UserCreated(String fname,String mname,String lname,int age,String gender1,String address,String nationality,String phoneNumber,String password,String usertype )
   {        int idno=1;
               try{
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
               Statement   st=conn.createStatement();
               String username="@"+lname;
               ResultSet rss=st.executeQuery("select * from userbankii ");
                   while(rss.next()){
                   if(rss.getInt(1)==idno){
                       idno++;
                   }
                   }
               PreparedStatement statment= conn.prepareStatement("insert into userbankii values(?,?,?,?,?,?,?,?,?,?,?,?)");
               statment.setInt(1,idno);
               statment.setString(2,fname);
               statment.setString(3,mname);
               statment.setString(4,lname);
               statment.setInt(5,age);
               statment.setString(6,gender1);
               statment.setString(7,address);
               statment.setString(8,nationality);
               statment.setString(9,password);
               statment.setString(10,username);
               statment.setString(11,phoneNumber);
               statment.setString(12, usertype);
               statment.executeUpdate();
               conn.close();
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("user created");
               alert.setHeaderText("");
               alert.setContentText("User account created successfully");
               alert.showAndWait();
           }
           catch (ClassNotFoundException | SQLException ex) {

               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Error Creating Account");
               alert.setContentText("An error occurred while creating the user." +ex);
               alert.showAndWait();
               return;
           }
           accountNumberField.clear();
           firstName.clear();
           midleName.clear();
           lastName.clear();
           ageField.clear();
           femaleRadio.isDisabled();
           addressField.clear();
           phoneNumberField.clear();
           initialBalanceField.clear();


           }  
    public  void UserUpdated(int id,String fname,String mname,String lname,int age,String gender1,String address,String nationality,String phoneNumber,String usertype )
   {        int idno=1;
               try{
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
               Statement   st=conn.createStatement();
               
          PreparedStatement statment= conn.prepareStatement("update userbankii set firstName=?,midleName=?,lastName=?, age=?,gender=?,address=?,nationality=?, password=?,userName=?,phonenumber=? where userId=?");
  
               statment.setString(1,fname);
               statment.setString(2,mname);
               statment.setString(3,lname);
               statment.setInt(4,age);
               statment.setString(5,gender1);
               statment.setString(6,address);
               statment.setString(7,nationality);
               statment.setString(8,phoneNumber);
               statment.setString(9, usertype);
                 statment.setInt(10,idno);
               statment.executeUpdate();
               conn.close();
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("user updated");
               alert.setHeaderText("");
               alert.setContentText("User account updated successfully");
               alert.showAndWait();
           }
           catch (ClassNotFoundException | SQLException ex) {

               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Error Creating Account");
               alert.setContentText("An error occurred while creating the user." +ex);
               alert.showAndWait();
               return;
           }
           }  
 public void displayAllUsers(Stage primaryStage) {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "root", "");
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM userbankii");

        // Create TableView and columns
        TableView<User> tableView = new TableView<>();
        tableView.setEditable(true);

        TableColumn<User, Integer> userIdCol = new TableColumn<>("User  ID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<User, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setFirstName(event.getNewValue());
        });

        TableColumn<User, String> middleNameCol = new TableColumn<>("Middle Name");
        middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        middleNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        middleNameCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setMiddleName(event.getNewValue());
        });

        TableColumn<User, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setLastName(event.getNewValue());
        });

        TableColumn<User, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ageCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setAge(event.getNewValue());
        });

        TableColumn<User, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        genderCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genderCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setGender(event.getNewValue());
        });

        TableColumn<User, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setAddress(event.getNewValue());
        });

        TableColumn<User, String> nationalityCol = new TableColumn<>("Nationality");
        nationalityCol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        nationalityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nationalityCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setNationality(event.getNewValue());
        });

        TableColumn<User, String> userNameCol = new TableColumn<>("Username");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        userNameCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setUserName(event.getNewValue());
        });

        TableColumn<User, String> phoneNumberCol = new TableColumn<>("Phone Number");
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumberCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setPhoneNumber(event.getNewValue());
        });

        TableColumn<User, String> userTypeCol = new TableColumn<>("User  Type");
        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        userTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        userTypeCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setUserType(event.getNewValue());
        });

        // Add "Save" Button Column
        TableColumn<User, Void> saveCol = new TableColumn<>("Action");
        saveCol.setCellFactory(col -> new TableCell<User, Void>() {
            private final Button saveButton = new Button("Save");

            {
                saveButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    saveUserData(conn, user);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(saveButton);
                }
            }
        });

        // Add columns to the table
        tableView.getColumns().addAll(userIdCol, firstNameCol, middleNameCol, lastNameCol, ageCol, genderCol,
                addressCol, nationalityCol, userNameCol, phoneNumberCol, userTypeCol, saveCol);

        // Populate the table with data
        ObservableList<User> users = FXCollections.observableArrayList();
        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getInt("userid"),
                    resultSet.getString("firstName"),
                    resultSet.getString("midleName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("age"),
                    resultSet.getString("gender"),
                    resultSet.getString("address"),
                    resultSet.getString("nationality"),
                    resultSet.getString("userName"),
                    resultSet.getString("phonenumber"),
                    resultSet.getString("userType")
            ));
        }
        tableView.setItems(users);

        // Layout
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);
        primaryStage.setTitle("User  List");
        primaryStage.setScene(scene);
        primaryStage.show();

    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Database Error");
        alert.setContentText("An error occurred while retrieving user information: " + e.getMessage());
        alert.showAndWait();
    }
}

private void saveUserData(Connection conn, User user) {
    try {
        String updateQuery = "UPDATE userbankii SET firstName = ?, midleName = ?, lastName = ?, age = ?, gender = ?, " +
                "address = ?, nationality = ?, userName = ?, phonenumber = ?, userType = ? WHERE userid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getMiddleName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setInt(4, user.getAge());
        preparedStatement.setString(5, user.getGender());
        preparedStatement.setString(6, user.getAddress());
        preparedStatement.setString(7, user.getNationality());
        preparedStatement.setString(8, user.getUserName());
        preparedStatement.setString(9, user.getPhoneNumber());
        preparedStatement.setString(10, user.getUserType());
        preparedStatement.setInt(11, user.getUserId());

        preparedStatement.executeUpdate();
        conn.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("User  Data Saved");
        alert.setContentText("Data for user id " + user.getUserId() + " has been saved successfully!");
        alert.showAndWait();
    } catch (SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Database Error");
        alert.setContentText("Failed to save user data: " + e.getMessage());
        alert.showAndWait();
    }
}
   
    public void login(Stage primaryStage,String userType) {

        GridPane pane = new GridPane();
        pane.setHgap(15);
        pane.setVgap(20);
        VBox root=new VBox();
        Label green=new Label();
        green.setStyle("-fx-background-color:green;-fx-min-width:200; border:none;");
        Label yellow=new Label();
        yellow.setStyle("-fx-background-color:yellow;-fx-min-width:200; border:none;");
        Label red=new Label();
        red.setStyle("-fx-background-color:red;-fx-min-width:200; border:none;");
        Label ac=new Label("ETHIOPIA");
        ac.setStyle("-fx-font-size:50px;-fx-min-width:200; ");
        root.getChildren().addAll(green,yellow,red);
        Label logo=new Label();
        logo.setStyle("-fx-background-image:url('cbe-logo.png');-fx-background-size:100px;-fx-background-repeat:no-repeat;-fx-min-width:100; border:none;");
        logo.setPrefHeight(80);
        Label cbe=new Label("Commertial Bank of Ethiopia");
        cbe.setStyle("-fx-font-size:30px; -fx-font-weight:bold;");
        Button back=new Button("←");
        back.setStyle("-fx-font-size:20px;-fx-text-fill:black;-fx-font-weight:bold;-fx-background-color:white;-fx-border-radius:180px;-fx-border-color:black;");
        back.setPrefHeight(1);
        back.setOnAction(e->{ start(primaryStage);}); 
        
        pane.add(back, 0, 0);
        pane.add(logo, 0, 1,1,1);
        pane.add(cbe, 1, 1,3,1);
        pane.add(root, 1, 0);
        pane.add(ac, 2, 0);
        
        Label scenetitle = new Label(" LOGIN PAGE ");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setStyle("-fx-font-size:20px;-fx-text-fill:black;");
        
        pane.add(scenetitle, 1, 2, 3, 1);
        //textfield and pasword field
        Label lb1=new Label("UserName");
        lb1.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        lb1.setStyle("-fx-background:transparent;-fx-text-fill:blue;");
        userName=new TextField();
        userName.setPrefHeight(20);
        userName.setPromptText("Enter user name");
        Label lb2=new Label("password");
        lb2.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        lb2.setStyle("-fx-background:transparent;-fx-font-weight:bold;-fx-text-fill:blue;");
        password=new PasswordField();
        password.setPrefHeight(20);
        password.setPromptText("Enter password");
        //button

        Button btOK = new Button("Login");
        Button btn2=new Button("Cancel");
        btOK.setStyle("-fx-background-color:orange;-fx-border-color:black;-fx-border-radius:12px;-fx-min-width:100;");
        btOK.setPrefHeight(40);
        btn2.setStyle("-fx-background-color:red; -fx-text-fill:white;-fx-border-color:black;-fx-border-radius:12px;-fx-min-width:100px;");
        btn2.setPrefHeight(40);
        btOK.setOnAction(e->{
            String user= userName.getText();
          String password5 = password.getText();
            loginAccount(primaryStage,userType,user,password5);});
        btn2.setOnAction(e->{
            userName.setText("");
            password.setText("");

        });

        pane.setAlignment(Pos.CENTER);
        pane.add(lb1,1,3);
        pane.add(userName,2,3);
        pane.add(lb2,1,4);
        pane.add(password,2,4);
        pane.add(btOK,1,5);
        pane.add(btn2,2,5);
        pane.setStyle("-fx-border-color: red; -fx-background-image:url('new9.');-fx-background-size:cover;");
        Scene scene = new Scene(pane, 900, 550);
        primaryStage.setTitle("Banking System"); // Set the stage title
        //primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        // Place the scene in the
        primaryStage.show();
    }    // Display the stage
    private void loginAccount(Stage PrimaryStage ,String userType,String user,String password5){
        boolean found=false;
        
        TextField accountnum;
        if(user.isEmpty() || password5.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your username and password");
            alert.showAndWait();
            return;
        }
        if(!user.matches("[a-zA-z0-9_$@]+")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your correct userName");
            alert.showAndWait();
            return;
        }
        else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                Statement  statment=con.createStatement();
                ResultSet rs=statment.executeQuery("select * from userbankii");
                while(rs.next()){
                    if(rs.getString("userName").equals(user) && rs.getString("password").equals(password5)&&rs.getString("userType").equals(userType)){
                        found=true;
                        GridPane grid = new GridPane();
                        grid.setHgap(10);
                        grid.setVgap(10);
                        grid.setAlignment(Pos.CENTER);

        
                        VBox root=new VBox();
                        Label green=new Label();
                        green.setStyle("-fx-background-color:green;-fx-min-width:200; border:none;");
                        Label yellow=new Label();
                        yellow.setStyle("-fx-background-color:yellow;-fx-min-width:200; border:none;");
                        Label red=new Label();
                        red.setStyle("-fx-background-color:red;-fx-min-width:200; border:none;");
                    
                        root.getChildren().addAll(green,yellow,red);
                        root.setAlignment(Pos.CENTER);
                      
                        Label logo=new Label();
                        logo.setStyle("-fx-background-image:url('cbe-logo.png');-fx-background-size:100px;-fx-background-repeat:no-repeat;-fx-min-width:100; border:none;");
                        logo.setPrefHeight(90);
                        Label cbe=new Label("Commertial Bank of Ethiopia");
                        cbe.setStyle("-fx-font-size:30px; -fx-font-weight:bold;");
                        grid.add(logo, 0, 1);
                        grid.add(cbe, 1, 1,3,1);
                        grid.add(root, 0, 0,5,1);
                        
                        Button back=new Button("←");
                        back.setStyle("-fx-font-size:20px;-fx-text-fill:black;-fx-font-weight:bold;-fx-background-color:white;-fx-border-radius:180px;-fx-border-color:black;");
                        back.setOnAction(e->{login(PrimaryStage,userType);});
                        grid.add(back, 0, 0);
                        
                        Button costom=new Button("login for customer");
                        costom.setStyle("-fx-border-color: black;-fx-text-fill:blue;-fx-font-size:19px;-fx-min-width:100;");
                        grid.add(costom, 1, 4,2,1);
                        costom.setOnAction(e->{customerLogin(PrimaryStage,userType,user,password5);});
                        
                        Button create=new Button("Create new customer account ");
                        create.setStyle("-fx-text-fill:blue;-fx-border-color: black;-fx-min-width:100;-fx-font-size:19px;");
                        grid.add(create, 1, 6,4,1);
                        create.setOnAction(e->{AccountCreate(PrimaryStage,userType,user,password5);});
                        
                        int idno=rs.getInt("userId");
                        Button change=new Button("Change password and user name");
                        change.setStyle("-fx-text-fill:blue;-fx-border-color: black;-fx-min-width:100;-fx-font-size:19px;");
                        change.setOnAction(e->{
                          chaneusernamepass(PrimaryStage,userType,idno,user,password5);
                        });
                        
                        Button createuser=new Button("Create new user");
                        createuser.setStyle("-fx-font-size:20px;-fx-text-fill:blue;-fx-min-width:100px;-fx-border-color:black;");
                        if(userType=="admin"){
                        grid.add(createuser,1,10,2,1);
                        grid.add(change,1,9,2,1);
                        Button displayUsersButton = new Button("Display All Users");
                        displayUsersButton.setOnAction(e -> displayAllUsers(PrimaryStage));
                        displayUsersButton.setStyle("-fx-font-size:20px;-fx-text-fill:blue;-fx-min-width:100px;-fx-border-color:black");
                        grid.add(displayUsersButton, 1,8); // Adjust the position as needed
                        }
                        else{                          
                        grid.add(change,1,8); 
                        }
                        createuser.setOnAction(e->{ 
                        CreateUser(PrimaryStage,userType,user,password5);
                        });

                        grid.setStyle("-fx-background-image:url('new2.jpg');-fx-background-size:cover;");
                        Scene scene = new Scene(grid, 800, 550);
                       // PrimaryStage.getIcons().add(icon);
                        PrimaryStage.setTitle("Banking System"); // Set the stage title
                        PrimaryStage.setScene(scene);
                        PrimaryStage.show();
                    }//close if close
                }//close while loop
                if(found==false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("please try again .This user name and password are not allowed");
                    alert.showAndWait();
                    return;
                }
                con.close();
            }//close try part
            catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }//close login else part
    }//close loginAccount method
    public void chaneusernamepass(Stage primaryStage ,String userType,int idno , String user,String password5){
    GridPane pane = new GridPane();
   
        pane.setHgap(15);
        pane.setVgap(20);
        VBox root=new VBox();
        Label green=new Label();
        green.setStyle("-fx-background-color:green;-fx-min-width:200; border:none;");
        Label yellow=new Label();
        yellow.setStyle("-fx-background-color:yellow;-fx-min-width:200; border:none;");
        Label red=new Label();
        red.setStyle("-fx-background-color:red;-fx-min-width:200; border:none;");
        Label ac=new Label("ETHIOPIA");
        ac.setStyle("-fx-font-size:50px;-fx-min-width:200; ");
        root.getChildren().addAll(green,yellow,red);
        Label logo=new Label();
        logo.setStyle("-fx-background-image:url('cbe-logo.png');-fx-background-size:100px;-fx-background-repeat:no-repeat;-fx-min-width:100; border:none;");
        logo.setPrefHeight(80);
        Label cbe=new Label("Commertial Bank of Ethiopia");
        cbe.setStyle("-fx-font-size:30px; -fx-font-weight:bold;");
        Button back=new Button("←");
        back.setStyle("-fx-font-size:20px;-fx-text-fill:black;-fx-font-weight:bold;-fx-background-color:white;-fx-border-radius:180px;-fx-border-color:black;");
        back.setPrefHeight(1);
        back.setOnAction(e->{  
            loginAccount(primaryStage,userType,user,password5);
        }); 
     
        pane.add(back, 0, 0);
        pane.add(logo, 0, 1,1,1);
        pane.add(cbe, 1, 1,3,1);
        pane.add(root, 1, 0);
        pane.add(ac, 2, 0);
   
        Label scenetitle = new Label(" change password and useraname page ");
        scenetitle.setTextFill(Color.WHITE);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setStyle("-fx-font-size:20px;-fx-text-fill:black;");
        
        pane.add(scenetitle, 1, 2, 3, 1);
        //textfield and pasword field
        Label lb1=new Label("UserName");
        lb1.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        lb1.setStyle("-fx-background:transparent;-fx-text-fill:blue;");
        userName=new TextField();
        userName.setPrefHeight(20);
        userName.setPromptText("Enter new username");
        Label lb2=new Label("password");
        lb2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        lb2.setStyle("-fx-background:transparent;-fx-font-weight:bold;-fx-text-fill:blue;");
        password=new PasswordField();
        password.setPrefHeight(20);
        password.setPromptText("Enter old password");
        
        Label lb3=new Label("password");
        lb2.setStyle("-fx-background:transparent;-fx-font-weight:bold;-fx-text-fill:blue;");
        password1=new PasswordField();
        password1.setPrefHeight(20);
        password1.setPromptText("Enter new password");
        
        Label lb4=new Label("password");
        lb2.setStyle("-fx-background:transparent;-fx-font-weight:bold;-fx-text-fill:blue;");
        password2=new PasswordField();
        password2.setPrefHeight(20);
        password2.setPromptText("Enter confirm password");
        //button
        Button tb=new Button("0k");
        Button btOK = new Button("Login");
        Button btn2=new Button("Cancel");
        btOK.setStyle("-fx-background-color:orange;-fx-border-color:black;-fx-border-radius:12px;-fx-min-width:100;");
        btOK.setPrefHeight(40);
        btn2.setStyle("-fx-background-color:red; -fx-text-fill:white;-fx-border-color:black;-fx-border-radius:12px;-fx-min-width:100px;");
        btn2.setPrefHeight(40);
        btOK.setOnAction(e->{
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
            Statement   st=conn.createStatement();
            String username=userName.getText();
            String passO=password.getText();
            String passN=password1.getText();
            String passC=password2.getText();
            boolean found=false;
            ResultSet rss=st.executeQuery("select * from userbankii ");
            PreparedStatement statment= conn.prepareStatement("update userbankii set password=?,userName=? where userId=?");
                while(rss.next()){
                if(rss.getString("password").equals(passO)){
                    found=true;
                    if(passN.equals(passC)){
                    statment.setString(1,passN);
                    statment.setString(2,username);
                    statment.setInt(3, idno);
                    statment.executeUpdate();
                   
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("password and user name change");
                    alert.setHeaderText("");
                    alert.setContentText("Username and password changed successfully");
                    alert.showAndWait();
                    login(primaryStage,userType);
                    }
                    else{
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("WARNING");
                    alert.setHeaderText("");
                    alert.setContentText("new password and confirm password are not mutch");
                    alert.showAndWait(); 
                    return;
                    }
                }
                
                }
                if(found==false){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("WARNING");
                    alert.setHeaderText("");
                    alert.setContentText("please check username and password");
                    alert.showAndWait(); 
                    return;
                    } 
               
             conn.close();
            }
            
        catch (ClassNotFoundException | SQLException ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Creating Account");
            alert.setContentText("An error occurred while creating the user." +ex);
            alert.showAndWait();
            return;
        }
            
        });
        btn2.setOnAction(e->{
            userName.setText("");
            password.setText("");
            password1.setText("");
            password2.setText("");
        });

        pane.setAlignment(Pos.CENTER);
        pane.add(lb1,1,3);
        pane.add(userName,2,3);
        pane.add(lb2,1,4);
        pane.add(password,2,4);
        pane.add(password1,2,5);
        pane.add(password2,2,6);
        pane.add(btOK,1,7);
        pane.add(btn2,2,7);
        pane.setStyle("-fx-border-color: red; -fx-background-image:url('new9.');-fx-background-size:cover;");
        Scene scene = new Scene(pane, 900, 550);
        primaryStage.setTitle("Banking System"); // Set the stage title
        //primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        // Place the scene in the
        primaryStage.show();
    
}
    public void customerLogin(Stage primaryStage,String userType,String user,String password5){
        GridPane grid = new GridPane();
                        grid.setHgap(5);
                        grid.setVgap(5);
                        grid.setAlignment(Pos.CENTER);
                        Label customeLabel=new Label("Customer sign in page");
                        grid.add(customeLabel,0,0,2,1);
                        customeLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
                        
                        Label accountLabel=new Label("Account Number");
                        accountLabel.setStyle("-fx-font-size:25px; -fx-min-width:100px;");
                        accountLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
                        grid.add(accountLabel, 0, 1);
                        
                        accountNumberField = new TextField();
                        accountNumberField.setPromptText("Entre Account Number");
                        accountNumberField.setStyle("-fx-min-width:150px;");
                        grid.add(accountNumberField, 1,1);
                        
                        Button customLogin=new Button("Sign in");
                        customLogin.setStyle("-fx-background-color:orange;-fx-border-color: black;-fx-text-fill:black;-fx-border-radius:12px;-fx-font-size:19px;-fx-min-width:100;");
                        grid.add(customLogin, 0, 2);
                        customLogin.setOnAction(e->{AccountLogged(primaryStage,userType,user,password5);});
                        Button cancel=new Button("Cancel");
                        grid.add(cancel, 1, 2);
                        cancel.setStyle("-fx-background-color:red;-fx-font-size:19px;-fx-border-color:black;-fx-border-radius:12px;");
                        cancel.setOnAction(e->{
                           accountNumberField.clear();
                        });
                        
                        Scene scene = new Scene(grid, 400, 400);
                        primaryStage.setTitle("Banking System"); // Set the stage title
                        primaryStage.setScene(scene);
                        primaryStage.show();
     
    }
    private void AccountCreate(Stage stage ,String userType,String user,String password5) {
          
        GridPane grid2 = new GridPane();
        grid2.setHgap(10);
        grid2.setVgap(10);
        Label scenetitle = new Label("Welcome to Banking System");
        scenetitle.setStyle("-fx-Text-fill:aqua; -fx-font-size:40px;");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid2.add(scenetitle, 0, 0, 2, 1);
        Label accountNumberLabel = new Label("Account Number:");
        accountNumberLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        accountNumberField = new TextField();
        accountNumberField.setPromptText("1000-XXXXXXXXXXXX");
       accountNumberField.setStyle("-fx-background:transparent");

        Label nameLabel = new Label("FirstName:");
        nameLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        firstName = new TextField();
        Label nameLabel1 = new Label("MidleName:");
        nameLabel1.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        midleName = new TextField();
        Label nameLabel2 = new Label("lastName:");
        nameLabel2.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        lastName = new TextField();
        Label ageLabel = new Label("Age:");
        ageLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        ageField = new TextField();
        Label genderLabel = new Label("Gender:");
        genderLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        genderGroup = new ToggleGroup();
        maleRadio = new RadioButton("Male");
        maleRadio.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        femaleRadio = new RadioButton("Female");
        femaleRadio.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        gender1="";
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);

        Label addressLabel = new Label("Address:");
        addressLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        addressField = new TextField();
        Label nationalityLabel = new Label("Nationality:");
        nationalityLabel .setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        roleComboBox=new ComboBox<>();
        roleComboBox.getItems().addAll("ETHIOPIA","USA","NIGERIA","EGYPT");

        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
        phoneNumberField = new TextField("+251");

        Label initialBalanceLabel = new Label("Initial Balance:");
        initialBalanceLabel.setStyle("-fx-text-fill:white;-fx-font-size:20px;");
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
        grid2.add(roleComboBox, 1, 8);
        grid2.add(phoneNumberLabel, 0, 9);
        grid2.add(phoneNumberField, 1, 9);
        grid2.add(initialBalanceLabel, 0, 10);
        grid2.add(initialBalanceField, 1, 10);


        Button createAccountButton = new Button("Sign in");
        createAccountButton.setStyle("-fx-background-color:orange;-fx-border-color: blue;-fx-font-size:19px;-fx-border-radius:12px;-fx-min-width:100px;");
        grid2.add(createAccountButton, 0, 11);
        createAccountButton.setOnAction(e->{
            String accountNumber = accountNumberField.getText();
            String fname = firstName.getText();
            String mname = midleName.getText();
            String lname = lastName.getText();
             String age=ageField.getText();
            
            if(maleRadio.isSelected()||femaleRadio.isSelected()){
                if(maleRadio.isSelected()){
                    gender1="MALE";
                }
                if(femaleRadio.isSelected()){
                    gender1="FEMALE";
                }}
            
            String address = addressField.getText().toUpperCase();
            String nationality=roleComboBox.getValue();

            String phoneNumber = phoneNumberField.getText();
           String initialb=initialBalanceField.getText();
            
            
            if(accountNumber.isEmpty()||fname.isEmpty()||lname.isEmpty()||mname.isEmpty()||age.isEmpty()|| gender1.isEmpty()|| address.isEmpty()||nationality.isEmpty()||initialb.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter full information");
                alert.showAndWait();
                return;
            }
            int age1 = Integer.parseInt(ageField.getText()); 
            double initialBalance = Double.parseDouble(initialBalanceField.getText());
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

            AccountCreated(accountNumber,fname,mname,lname,age1,gender1,address,nationality,phoneNumber,initialBalance);});

        Button clear = new Button("clear");
        Button back=new Button("Back");
        grid2.setStyle("-fx-background-image:url('new8.png');-fx-background-size:cover;");
        clear.setStyle("-fx-background-color:green;-fx-text-fill: yellow;-fx-font-size:19px;-fx-border-color:black;-fx-border-radius:12px;-fx-min-width:100px;");
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
            phoneNumberField.clear();
            initialBalanceField.clear();
        });
        back.setStyle("-fx-background-color:red;-fx-text-fill:black;-fx-border-color:black;-fx-border-radius:12px;-fx-min-width:50px;-fx-font-size:15px");
        grid2.add(back,3,11);
        back.setOnAction(e->{loginAccount(stage,userType,user,password5);});
        Scene scene = new Scene(grid2, 850, 600);
        //stage.getIcons().add(icon);
        stage.setTitle("Banking System");
        stage.setScene(scene);
        stage.show();
    }//close accout create method
    public  void AccountCreated(String accountNumber,String fname,String mname,String lname,int age,String gender1,String address,String nationality,String phoneNumber,double initialBalance){
        Boolean found=true;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
            PreparedStatement statment= conn.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?,?)");
            statment.setString(1,accountNumber);
            statment.setString(2,fname);
            statment.setString(3,mname);
            statment.setString(4,lname);
            statment.setInt(5,age);
            statment.setString(6,gender1);
            statment.setString(7,address);
            statment.setString(8,nationality);
            statment.setString(9,phoneNumber);
            statment.setDouble(10,initialBalance);
            statment.setInt(11, 1);
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
            return;
        }
        accountNumberField.clear();
        firstName.clear();
        midleName.clear();
        lastName.clear();
        ageField.clear();
        femaleRadio.isDisabled();
        addressField.clear();
        phoneNumberField.clear();
        initialBalanceField.clear();

    }
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
        try{                   Class.forName("com.mysql.jdbc.Driver");
            Connection    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
            String updateacount="update  customer set intialbalance=intialbalance+? where accountnumber= ?";
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
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

        try{                      Class.forName("com.mysql.jdbc.Driver");
            Connection  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
            String updateacount="update  customer set intialbalance=intialbalance-? where accountnumber= ?";
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
    private void AccountLogged(Stage stage,String userType ,String user, String password5){
        String Account=accountNumberField.getText();
        boolean found=false;
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
                        permission=rss.getInt("permission");
                        found=true;
                        acc=rss.getString("accountnumber");
                        currentbalance=rss.getDouble("intialbalance");
                        if(permission==0){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("");
                                alert.setContentText("This account number is restricted.");
                                alert.showAndWait();
                            }
                        VBox root=new VBox();
                        Label green=new Label();
                        green.setStyle("-fx-background-color:green;-fx-min-width:200; border:none;");
                        Label yellow=new Label();
                        yellow.setStyle("-fx-background-color:yellow;-fx-min-width:200; border:none;");
                        Label red=new Label();
                        red.setStyle("-fx-background-color:red;-fx-min-width:200; border:none;");
                        Label ac=new Label("ETHIOPIA");
                        ac.setStyle("-fx-font-size:50px;-fx-min-width:200;-fx-font-weight:bold; ");
                        root.getChildren().addAll(green,yellow,red);
                        GridPane grid3 = new GridPane();
                        grid3.setHgap(20);
                        grid3.setVgap(15);
                        grid3.setAlignment(Pos.CENTER);
                        grid3.setStyle("-fx-background-image:url('new1.jpg');-fx-background-size:cover;-x-fx-border-color: blue;-fx-border-radius:12px;");
                        Label scenetitle = new Label("Welcome to Banking System");
                        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        scenetitle.setStyle("-fx-font-size:45px;-fx-text-fill:black;-fx-font-weight:bold;");
                        grid3.add(root,0,0,2,1);
                        grid3.add(ac, 1, 0);
                        grid3.add(scenetitle, 0, 1, 2, 1);

                        Button depositButton = new Button("Deposit");
                        grid3.add(depositButton, 0,2);
                        depositButton.setPrefHeight(50);
                        depositButton.setStyle("-fx-background-color:white;-fx-min-width:200;-fx-font-size:20px;");
                        depositButton.setOnAction(ee->{
                            if(permission==0){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("");
                                alert.setContentText("This account number is restricted and couldn't deposit");
                                alert.showAndWait();
                                return;
                            }
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
                                try {
                                    deposit(acc,amount ,1);
                                } catch (ClassNotFoundException ex) {
                                    System.out.println(ex);
                                }
                            }

                        });

                        Button withdrawButton = new Button("Withdraw");
                        grid3.add(withdrawButton, 0, 3);
                        withdrawButton.setPrefHeight(50);
                        withdrawButton.setStyle("-fx-background-color:white;-fx-min-width:200;-fx-font-size:20px;");
                        withdrawButton.setOnAction(ee->{
                            if(permission==0){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("");
                                alert.setContentText("This account number is restricted and couldn't withdrawn balance");
                                alert.showAndWait();
                                return;
                            }
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
                                try {
                                    withdraw(acc,amount,1);
                                } catch (IOException ex) {
                                    System.out.println( ex);
                                }
                            }
                        });

                        Button checkBalanceButton = new Button("Check Balance");
                        grid3.add(checkBalanceButton, 0, 4);
                        checkBalanceButton.setPrefHeight(50);
                        checkBalanceButton.setStyle("-fx-background-color:white;-fx-min-width:200;-fx-font-size:20px;");
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
                        grid3.add(previousTransactionsButton, 1, 2);
                        previousTransactionsButton.setPrefHeight(50);
                        previousTransactionsButton.setStyle("-fx-background-color:white;-fx-min-width:200;-fx-font-size:20px;");
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
                        grid3.add(displayInformationButton, 1, 3);
                        displayInformationButton.setPrefHeight(50);
                        displayInformationButton.setStyle("-fx-background-color:white;-fx-min-width:220;-fx-font-size:20px;");
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

                        Button Transfere = new Button("Transfere for other");
                        grid3.add(Transfere, 1, 4);
                        Transfere.setPrefHeight(50);
                        Transfere.setStyle("-fx-background-color:white;-fx-min-width:220;-fx-font-size:20px;");
                        Transfere.setOnAction(e->{
                            if(permission==0){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("");
                                alert.setContentText("This account number is restricted and couldn't transfer");
                                alert.showAndWait();
                                return;
                            }
                            try{
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
                            }catch (ClassNotFoundException |IOException  ex) {
                                System.out.println(ex);
                            }
                        });

                        Button logoutButton = new Button("logout");
                        grid3.add(logoutButton, 2, 7);
                        logoutButton.setStyle("-fx-background-color:blue;-fx-text-fill:white;-fx-min-width:20;-fx-font-size:20px");
                        logoutButton.setOnAction(e->{loginAccount(stage,userType,user,password5);});


                        Button ActivatedButton=new Button("Activate");
                        if(permission==0){
                         ActivatedButton.setStyle("-fx-background-color:red;-fx-text-fill:black;-fx-min-width:20;-fx-font-size:20px;");
                        }
                        else{
                        ActivatedButton.setStyle("-fx-background-color:green;-fx-text-fill:black;-fx-min-width:20;-fx-font-size:20px;");}
                        //ActivetedButton.setPrefHeight()
                        grid3.add(ActivatedButton,0,7);
                        ActivatedButton.setOnAction(e->{
                            try{
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                                String updateacount="update  customer set permission=? where accountnumber=?";
                                PreparedStatement statment1= con.prepareStatement(updateacount);
                                statment1.setInt(1, 1);
                                statment1.setString(2,Account);
                                int rowAffected=statment1.executeUpdate();
                                if(rowAffected>0 && permission==1){
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    alert1.setTitle("warnning");
                                    alert1.setHeaderText("");
                                    alert1.setContentText("Account is activated.");
                                    alert1.showAndWait();
                                }
                                else if(rowAffected>0){
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    alert1.setTitle("warnning");
                                    alert1.setHeaderText("");
                                    alert1.setContentText("Account is activating......");
                                    alert1.showAndWait();
                                    loginAccount(stage,userType,user,password5);
                                }



                            }
                            catch (SQLException |ClassNotFoundException ex) {
                                System.out.println("error"+ ex);
                            }
                        });

                        Button De_ActivatedButton=new Button("De_Activate");
                        if(permission==0){
                            Label afterdeactive=new Label("X");
                            grid3.add(afterdeactive, 2, 2,1,3);
                            afterdeactive.setPrefHeight(80);
                            afterdeactive.setStyle("-fx-text-fill:red;-fx-font-size:100px; -fx-min-width:100px;");
                            De_ActivatedButton.setStyle("-fx-background-color:green;-fx-text-fill:black; -fx-font-size:20px;-fx-min-width:20;");
                        }
                        else{
                            De_ActivatedButton.setStyle("-fx-background-color:red;-fx-text-fill:black; -fx-font-size:20px;-fx-min-width:20;");
                        }
                        grid3.add(De_ActivatedButton,1,7);
                        De_ActivatedButton.setOnAction(e->{
                            try{
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","");
                                String updateacount="update  customer set permision=? where accountnumber=?";
                                PreparedStatement statment1= con.prepareStatement(updateacount);
                                statment1.setInt(1, 0);
                                statment1.setString(2,Account);
                                int rowAffected=statment1.executeUpdate();
                                if(rowAffected>0){
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    alert1.setTitle("warnning");
                                    alert1.setHeaderText("");
                                    alert1.setContentText("Account Deactivated sucessfully.");
                                    alert1.showAndWait();
                                }
                                if(permission==1){
                                    loginAccount(stage,userType,user,password5);}

                            }
                            catch (SQLException |ClassNotFoundException ex) {
                                System.out.println("error"+ ex);
                            }
                        });

                        Scene scene=new Scene(grid3,850,550);
                        //stage.getIcons().add(icon);
                        stage.setScene(scene);
                        stage.setTitle("Banking system");
                        stage.show();
                    }//closed if close in while loop

                }//closed while loop of in ths method
                if(found==false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("This account number is not found");
                    alert.showAndWait();
                    return;
                }

            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("cought error "+ ex);}
        }//close else part of logged in
        accountNumberField.clear();
    }
    public static void main(String[] args) {
        launch(args);
    }
} 