    --
    -- DATABASE banksystem
    --
    -- Table structure for table `userbankii`
        --
        CREATE TABLE userbankii (
        userid INT NOT NULL AUTO_INCREMENT,
        firstName VARCHAR(50) NOT NULL,
        midleName VARCHAR(50)NOT NULL,
        lastName VARCHAR(50)NOT NULL,
        age int(11),
        gender VARCHAR(5) NOT NULL,
        address VARCHAR(15) NOT NULL,
        nationality VARCHAR(15) NOT NULL,
        password VARCHAR(15) NOT NULL,
        userName varchar(100) NOT NULL,
        phonenumber varchar(15)NOT NULL,
        userType varchar(50) NOT NULL,
        PRIMARY KEY(userid)
        );
        --
    -- insert data for table `userbankii`
    --
    INSERT into userbankii(userid,firstName,midleName,lastName,age,gender,address,nationality,password,userName,phoneNumber,userType) values('1','admin','admin','admin',24,'male','debre birhan','ETHIOPIA','7414','admin','+251974149838','admin');
    --
    -- Table structure for table `announcement`
    --
        CREATE TABLE customer (
        accountnumber VARCHAR(13) NOT NULL,
        firstName VARCHAR(50) NOT NULL,
        midleName VARCHAR(50)NOT NULL,
        lastName VARCHAR(50)NOT NULL,
        age int(11),
        gender VARCHAR(5) NOT NULL,
        address VARCHAR(15) NOT NULL,
        nationality VARCHAR(15) NOT NULL,
        phonenumber varchar(15)NOT NULL,
        intialbalance double,
        permission int,
        PRIMARY KEY(accountnumber)
        );
        INSERT into customer(accountnumber,firstName,midleName,lastName,age,gender,address,nationality,phoneNumber,permission) values('1000123456789','Abebe','Kebede','Alemu',24,'male','debre birhan','ETHIOPIA','+251974149838',1);

        
