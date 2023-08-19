import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface SkinConsultationManager{      //implementing interface skin consultation manager
        void addDoctor();
        void deleteDoctor();
        void printDoctors();
        void saveDetailsInFile();
        void displayMenu();

    }





public class WestminsterSkinConsultationManager implements SkinConsultationManager {           //creating class WestminsterSkinConsultationManager

    ArrayList<Doctor> doctorDetails = new ArrayList<>();// creating an array list for save doctor details

    ArrayList<String> doctorDetailsProcess = new ArrayList<>(); // creating an array list for store and save details of the doctors

    @Override
    public void addDoctor() {     //creating method addNewDoctor for add new doctors to the system
        int doctorsAmount = doctorDetails.size();   //Add maximum number of 10 doctors only to the system

        if (doctorsAmount > 9) {
            System.out.println("Maximum number of  doctors are already Added");
        } else {
            String doctorMobileNumber;
            int mobileNumber;
            int doctorValidPhone;
            int doctorValidLisence;
            int doctorLisenceNumber;

            while (true) {
                try {
                    Scanner scanner = new Scanner(System.in);    /// create object called scanner

                    while (true) {   ////medical licence number validation
                        System.out.println("Insert the doctor's medical number : ");

                        doctorLisenceNumber = scanner.nextInt();  // save the doctor medical number

                        doctorValidLisence = checkMedicalLisenceNumber(doctorLisenceNumber);

                        if (doctorValidLisence == -1) { //input validation to check  whether
                            // a similar medical number entry has already been made
                            break;
                        } else {
                            System.out.println("This medical lisense number has already been entered");
                        }
                    }

                    ///firstname validation
                    scanner.nextLine();
                    String firstName;
                    while (true) {
                        System.out.println("Enter the doctor's first name : ");
                        firstName = scanner.nextLine();
                        if (firstName.matches("[a-zA-Z]+")) {  // firstname validation to
                            // Checking if the first name contains only English characters
                            break;

                        } else {
                            System.out.println("First name should contain only English characters");
                        }
                    }


                    // lastname validation

                    String lastName;
                    while (true) {
                        System.out.println("Enter the doctor's last name : ");
                        lastName = scanner.nextLine();
                        if (lastName.matches("[a-zA-Z]+")) {    // lastname validation to
                            // Checking if the last name contains only English characters
                            break;
                        } else {
                            System.out.println("last name should contain only English characters");
                        }
                    }

                    //date of birth validation

                    String dateOfBirth;
                    while (true) {
                        try {
                            System.out.println("Enter the doctor's date of birth in this format (dd-mm-yyyy) : ");

                            SimpleDateFormat DateFormat = new SimpleDateFormat("dd-mm-yyyy"); // store the Date

                            dateOfBirth = DateFormat.format(DateFormat.parse(scanner.nextLine()));
                            //to print given format only
                            break;

                        } catch (ParseException e) {
                            System.out.println("your date format is not valid");
                        }
                    }


                    //Mobile number validation
                    while (true) {
                        System.out.print("Enter doctor's mobile number ");  // asked the input from user
                        doctorMobileNumber = scanner.nextLine(); // save the input
                        mobileNumber = Integer.parseInt(doctorMobileNumber);
                        doctorValidPhone = checkDoctorMobileNumber(mobileNumber);
                        if (doctorMobileNumber.length() != 10) {  //mobile number validation to check if the mobile number is valid by consisting 10 digits
                            System.out.println(" Your phone number should have only ten digits.");
                        } else {
                            if (doctorValidPhone == -1) {       //input validation to check if the same mobile number exist
                                break;
                            } else {
                                System.out.println();
                                System.out.println(" A doctor with the same mobile number exists.");
                            }
                        }
                    }


                    //Specialisation

                    System.out.println("Enter the doctor's specialty : ");
                    String specialisation = scanner.nextLine(); // save the Specialisation

                    Doctor information = new Doctor(firstName, lastName, dateOfBirth, mobileNumber, doctorLisenceNumber, specialisation);
                    doctorDetails.add(information); //create object for doctor class
                    System.out.println("Doctor added successfully");
                    break;

                } catch (Exception e) {
                    System.out.println("Invalid data format");
                }
            }
        }
    }

    //delete doctor
    @Override
    public void deleteDoctor() {
            try {
                int present =0;
                if (doctorDetails.size() ==0){          //details of the doctors are not entered
                    System.out.println("No doctor information is included for deletion");
                }
                else {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter the medical licence number of the doctor you want to delete");
                    int licenceNumber = scanner.nextInt(); //save the licenceNumber
                    for (Doctor drDelete : doctorDetails){
                        int licenceCheck = drDelete.getMedicalLisenceNumber();
                        if(licenceNumber == licenceCheck){
                            System.out.println();
                            System.out.println("Details of deleted doctors");
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.printf("%-12s %-13s %-14s %-16s %-12s %-12s", "First Name          ", "Last name          ", "Specialisation          ", "Medical Licence Number          ", "Date Of Birth          ", "Mobile Number          ");
                            System.out.println();
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.format("%-20s %-17s %-24s %-32s %-23s %13s", drDelete.getFirstName(), drDelete.getLastName(), drDelete.getSpecialisation(), drDelete.getMedicalLisenceNumber(), drDelete.getDateOfBirth(), drDelete.getMobileNumber());
                            System.out.println();
                            System.out.println();
                            System.out.println("The doctor with this medical licence number has been successfully deleted : "+ drDelete.getMedicalLisenceNumber());
                            doctorDetails.remove(drDelete);  //removing the selected doctor from the array list
                            System.out.println("Current Total number of doctors = " + doctorDetails.size());  //displaying the total number of available doctors now
                            present = 1;
                        }
                    }
                    if (present == 0){
                        System.out.println("A doctor with that medical licence number is not included");
                    }
                }
            } catch (Exception e) {
                System.out.println("Enter a valid medical licence number");
            }
    }

    @Override
    public void printDoctors() { ///menu of doctors and information about them
        lastNameSort(); //Sorting doctors by their last name
        try {
            if (doctorDetails.size() == 0) { ///check the details of the doctors
                System.out.println("No doctors are included");
            }
            else {
                System.out.println("deleted doctor's information");
                System.out.println();
                System.out.println("INFORMATION ABOUT THE DOCTORS IN THE SYSTEM");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-12s %-13s %-14s %-16s %-12s %-12s","First Name          ", "Surname          ", "Specialisation          ", "Medical Licence Number          ", "Date Of Birth          ", "Mobile Number          ");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                for (Doctor drList : doctorDetails) {
                    System.out.format("%-20s %-17s %-24s %-32s %-23s %13s", drList.getFirstName(), drList.getLastName(), drList.getSpecialisation(), drList.getMedicalLisenceNumber(), drList.getDateOfBirth(), drList.getMobileNumber());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Please Enter correct data format");
        }
    }

    @Override
    public void saveDetailsInFile() {
        try {
            File data = new File("DoctorDetails.txt");
            if (data.createNewFile()) { ///creating file called DoctorDetails
                System.out.println("File created: " + data.getName());
            }
            doctorDetailsProcess.clear();
            FileWriter writer = new FileWriter("DoctorDetails.txt");
            for (Doctor drSave : doctorDetails) {  //enter the all data in the array list of the file
                int count = 0;
                doctorDetailsProcess.add(count, drSave.getFirstName() + "\n" + drSave.getLastName() + "\n" +
                        drSave.getDateOfBirth() + "\n" + drSave.getMobileNumber() + "\n" +
                        drSave.getMedicalLisenceNumber() + "\n" + drSave.getSpecialisation() + "\n"); } //save the details in file
                for (String writeInfo : doctorDetailsProcess) {
                    writer.write(writeInfo);  //writing all the information to the text file DoctorInformation.txt
                }
                writer.close();
                doctorDetailsProcess.clear();
                System.out.println("Successfully stored the program data.");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("Invalid data format");
        }
    }

    public void readSavedDetails() { ///read the saved details in text file
        try {
            File doctorDetails = new File("DoctorDetails.txt");
            Scanner reader = new Scanner(doctorDetails);
            if (reader.hasNextLine()) {
                int lineCount;
                String doctorDetailsFile;
                FileReader readFile = new FileReader("DoctorDetails.txt");
                BufferedReader readBuffer = new BufferedReader(readFile); //creating object bufferreader class
                int count = 0;
                while (reader.hasNextLine()) {
                    reader.nextLine();
                    count++;
                }
                //declaring the loop variables
                int count1 = count / 6;
                int p = 1;
                int q = 6;
                int g;
                int s = 0;
                doctorDetailsProcess.clear();
                this.doctorDetails.clear();
                for (g = 0; g <= count1 - 1; g++) {
                    for (lineCount = 1; lineCount < count + 1; lineCount++) {
                        if (lineCount >= p && lineCount <= q) {
                            doctorDetailsFile = readBuffer.readLine();
                            doctorDetailsProcess.add(doctorDetailsFile);
                        }
                    }
                    //reading the information in the file to the system
                    p = p + 6;
                    q = q + 6;
                    Doctor drRead = new Doctor(doctorDetailsProcess.get(s), doctorDetailsProcess.get(s + 1), doctorDetailsProcess.get(s + 2),
                            Integer.parseInt(doctorDetailsProcess.get(s + 3)), Integer.parseInt(doctorDetailsProcess.get(s + 4)),
                            doctorDetailsProcess.get(s + 5));
                    this.doctorDetails.add(drRead);
                    s = s + 6;
                }
                System.out.println("your information successfully read and saved");
            } else {
                System.out.println("The file does not contain any data");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(" Invalid data format.");
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("------------please Enter the Number------------");
        System.out.println("Add a new Doctor -> 1 : ");
        System.out.println("Delete a doctor -> 2 : ");
        System.out.println("Print the list of doctor -> 3");
        System.out.println("save in a file -> 4 : ");
        System.out.println("Read information in the file -> 5 : ");
        System.out.println("Open GUI -> 6 : ");
        System.out.println("Exit program -> 7 : ");

    }


    public int checkDoctorMobileNumber(int phoneNO) {     //checking the doctor mobile number whether it exist already
        int number = -1;
        for (Doctor drPhone : doctorDetails) {
            int check = drPhone.getMobileNumber();
            if (check == (phoneNO)) {
                number = doctorDetails.indexOf(drPhone);
                break;
            }
        }
        return number;
    }

    //checking the doctor medical licence number whether it exist already
    public int checkMedicalLisenceNumber(int medicalLicenceNO) {

        int number = -1;
        for (Doctor drLicence : doctorDetails) {
            int licenseNO = drLicence.getMedicalLisenceNumber();
            if (licenseNO == medicalLicenceNO) {
                number = doctorDetails.indexOf(drLicence);
                break;
            }
        }
        return number;
    }

    //sorting alphabetically
    public void lastNameSort() {
        drLastnameSort lnSort = new drLastnameSort();
        doctorDetails.sort(lnSort);
    }

    public void firstNameSort() {
        drFirstNameSort fnSort = new drFirstNameSort();
        doctorDetails.sort(fnSort);
    }//implementing graphical user interface
    public void openGUI(){
        // TODO Auto-generated method stub
        GUI gui = new GUI();
        gui.frame.setVisible(true);
    }

    class GUI extends JFrame implements ActionListener {

        JFrame frame;
        JButton button1,buttonQuit;
        JLabel label1;
        JTable table1;
        public GUI()
        {
            initialize();
        }
        public void initialize() {
            // TODO Auto-generated method stub
            frame = new JFrame("Skin Consultation Centre");
            frame.setBounds(50,50,1200,600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(null);
            label1 = new JLabel("Skin Consultation Centre GUI");
            label1.setBounds(450,10,400,25);
            label1.setFont(new Font("Calibre",Font.PLAIN,30));
            frame.getContentPane().add(label1);
            button1 = new JButton("Sort Alphabetically");
            button1.addActionListener(this);
            button1.setBounds(30,60,145,39);
            button1.setFocusPainted(false);
            frame.getContentPane().add(button1);
            buttonQuit = new JButton("Exit Program");
            buttonQuit.addActionListener(this);
            buttonQuit.setBounds(1040,60,113,39);
            frame.getContentPane().add(buttonQuit);
            table1 = new JTable();
            table1.setBounds(30,160,1120,300);
            frame.getContentPane().add(table1);
            tableDisplay();
        }
        void tableDisplay (){

            List<Object[]> tableList = new ArrayList<>();
            tableList.add(new Object[]{"Medical Licence Number", "First Name", "Surname","Date Of Birth","Mobile Number","Specialization"});
            for (Doctor tableVal : doctorDetails) {
                tableList.add(new Object[]{
                        tableVal.getMedicalLisenceNumber(),
                        tableVal.getFirstName(),
                        tableVal.getLastName(),
                        tableVal.getDateOfBirth(),
                        tableVal.getMobileNumber(),
                        tableVal.getSpecialisation()
                });
            }
            table1.setModel(new DefaultTableModel(tableList.toArray(new Object[][] {}),
                    new String[] {"Medical Licence Number", "  First Name", "  Surname","  Date Of Birth","  Mobile Number","  Specialization"}));
        }
        public void actionPerformed(ActionEvent e) {

            // TODO Auto-generated method stub
            String action = ((JButton) e.getSource()).getActionCommand();
            if(action.equals("Sort Alphabetically")) //sorting the alphabetically
            {
                firstNameSort(); //called method firstNameSort
                tableDisplay();
            }
            if(action.equals("Exit Program"))
            {
                System.out.println();
                System.out.println("Successfully exited the program.");
                System.exit(0);
            }
        }
    }
}














