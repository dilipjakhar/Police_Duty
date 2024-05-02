create database police_duty;

use police_duty;

CREATE TABLE employees (
    belt_no INT ,
    emp_name VARCHAR(50),
    ranks VARCHAR(50),
    mobile VARCHAR(50),
    ps_cd INT NOT NULL,
    employee_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    role ENUM('admin', 'officer') NOT NULL
);



CREATE TABLE duty_assignments (
    duty_assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    duty_start_time datetime NOT NULL,
    duty_end_time datetime NOT NULL,
    employee_id VARCHAR(50) ,
    ps_cd INT NOT NULL,
    duty_id INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (duty_id) REFERENCES duty(duty_id)
);




CREATE TABLE duty (
    duty_id INT AUTO_INCREMENT PRIMARY KEY,
    duty_point varchar(50) NOT NULL,
    duty_desc varchar(250) NOT NULL
    );
    
   
    
    
    INSERT INTO employees (belt_no, emp_name, ranks, mobile, ps_cd, employee_id, password, role) 
VALUES
(1, 'Rajesh Kumar', 'Inspector', '1234567890', 1, 'rjjp1', 'password123', 'admin'),
(2, 'Priya Sharma', 'Sub-Inspector', '9876543210', 1, 'rjjp2', 'password456', 'officer'),
(3, 'Amit Singh', 'Sub-Inspector', '5556667777', 1, 'rjjp3', 'password789', 'officer'),
(4, 'Anjali Gupta', 'Sub-Inspector', '1112223333', 1, 'rjjp4', 'passwordabc', 'officer'),
(5, 'Sandeep Patel', 'Sub-Inspector', '4445556666', 1, 'rjjp5', 'passworddef', 'officer'),
(6, 'Neha Verma', 'Head Constable', '7778889999', 1, 'rjjp6', 'passwordghi', 'officer'),
(7, 'Rahul Yadav', 'Head Constable', '3334445555', 1, 'rjjp7', 'passwordjkl', 'officer'),
(8, 'Pooja Gupta', 'Head Constable', '6667778888', 1, 'rjjp8', 'passwordmno', 'officer'),
(9, 'Sachin Sharma', 'Head Constable', '9990001111', 1, 'rjjp9', 'passwordpqr', 'officer'),
(10, 'Sunita Singh', 'Head Constable', '2223334444', 1, 'rjjp10', 'passwordstu', 'officer'),
(11, 'Vikas Kumar', 'Head Constable', '5556667777', 1, 'rjjp11', 'passwordvwx', 'officer'),
(12, 'Anita Devi', 'Head Constable', '8889990000', 1, 'rjjp12', 'passwordyz1', 'officer'),
(13, 'Ravi Tiwari', 'Head Constable', '1112223333', 1, 'rjjp13', 'password234', 'officer'),
(14, 'Sonam Singh', 'Head Constable', '4445556666', 1, 'rjjp14', 'password567', 'officer'),
(15, 'Rahul Kumar', 'Head Constable', '7778889999', 1, 'rjjp15', 'password890', 'officer'),
(16, 'Aarti Yadav', 'Constable', '3334445555', 1, 'rjjp16', 'passwordabc2', 'officer'),
(17, 'Ajay Sharma', 'Constable', '6667778888', 1, 'rjjp17', 'passworddef2', 'officer'),
(18, 'Anjali Mishra', 'Constable', '9990001111', 1, 'rjjp18', 'passwordghi2', 'officer'),
(19, 'Rakesh Verma', 'Constable', '2223334444', 1, 'rjjp19', 'passwordjkl2', 'officer'),
(20, 'Sapna Singh', 'Constable', '5556667777', 1, 'rjjp20', 'passwordmno2', 'officer'),
(21, 'Rohit Gupta', 'Constable', '8889990000', 1, 'rjjp21', 'passwordpqr2', 'officer'),
(22, 'Kavita Sharma', 'Constable', '1112223333', 1, 'rjjp22', 'passwordstu2', 'officer'),
(23, 'Vivek Kumar', 'Constable', '4445556666', 1, 'rjjp23', 'passwordvwx2', 'officer'),
(24, 'Kajal Singh', 'Constable', '7778889999', 1, 'rjjp24', 'passwordyz3', 'officer'),
(25, 'Alok Tiwari', 'Constable', '3334445555', 1, 'rjjp25', 'password2343', 'officer'),
(26, 'Swati Yadav', 'Constable', '6667778888', 1, 'rjjp26', 'password5673', 'officer'),
(27, 'Dinesh Kumar', 'Constable', '9990001111', 1, 'rjjp27', 'password8903', 'officer'),
(28, 'Anju Sharma', 'Constable', '2223334444', 1, 'rjjp28', 'passwordabc3', 'officer'),
(29, 'Amita Singh', 'Constable', '5556667777', 1, 'rjjp29', 'passworddef3', 'officer'),
(30, 'Deepak Gupta', 'Constable', '8889990000', 1, 'rjjp30', 'passwordghi3', 'officer'),
(31, 'Komal Devi', 'Constable', '1112223333', 1, 'rjjp31', 'passwordjkl3', 'officer'),
(32, 'Vikram Singh', 'Constable', '4445556666', 1, 'rjjp32', 'passwordmno3', 'officer'),
(33, 'Asha Verma', 'Constable', '7778889999', 1, 'rjjp33', 'passwordpqr3', 'officer'),
(34, 'Anil Kumar', 'Constable', '3334445555', 1, 'rjjp34', 'passwordstu3', 'officer'),
(35, 'Shweta Yadav', 'Constable', '6667778888', 1, 'rjjp35', 'passwordvwx3', 'officer'),
(36, 'Vijay Sharma', 'Constable', '9990001111', 1, 'rjjp36', 'passwordyz4', 'officer'),
(37, 'Shikha Gupta', 'Constable', '2223334444', 1, 'rjjp37', 'password2344', 'officer'),
(38, 'Praveen Singh', 'Constable', '5556667777', 1, 'rjjp38', 'password5674', 'officer'),
(39, 'Poonam Tiwari', 'Constable', '8889990000', 1, 'rjjp39', 'password8904', 'officer'),
(40, 'Sagar Yadav', 'Constable', '1112223333', 1, 'rjjp40', 'passwordabc4', 'officer'),
(41, 'Nisha Sharma', 'Constable', '4445556666', 1, 'rjjp41', 'passworddef4', 'officer'),
(42, 'Vikas Verma', 'Constable', '7778889999', 1, 'rjjp42', 'passwordghi4', 'officer'),
(43, 'Preeti Yadav', 'Constable', '3334445555', 1, 'rjjp43', 'passwordjkl4', 'officer'),
(44, 'Amit Kumar', 'Constable', '6667778888', 1, 'rjjp44', 'passwordmno4', 'officer'),
(45, 'Neelam Singh', 'Constable', '9990001111', 1, 'rjjp45', 'passwordpqr4', 'officer'),
(46, 'Vinay Sharma', 'Constable', '2223334444', 1, 'rjjp46', 'passwordstu4', 'officer'),
(47, 'Sneha Gupta', 'Constable', '5556667777', 1, 'rjjp47', 'passwordvwx4', 'officer'),
(48, 'Rajat Singh', 'Constable', '8889990000', 1, 'rjjp48', 'passwordyz5', 'officer'),
(49, 'Anamika Tiwari', 'Constable', '1112223333', 1, 'rjjp49', 'password2345', 'officer'),
(50, 'Arjun Yadav', 'Constable', '4445556666', 1, 'rjjp50', 'password5675', 'officer');

    