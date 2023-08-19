import java.util.Locale;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {

        WestminsterSkinConsultationManager option  = new WestminsterSkinConsultationManager();
        String select;
        option.displayMenu();
        label :
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter the menu option : ");
            select = scanner.nextLine();
            select = select.toUpperCase();
            switch (select){
                case "1":
                    option.addDoctor();
                    break;
                case "2":
                    option.deleteDoctor();
                    break;
                case "3":
                    option.printDoctors();
                    break;
                case "4":
                    option.saveDetailsInFile();
                    break;
                case "5":
                    option.readSavedDetails();
                    break;
                case "6":
                    option.openGUI();
                    break;
                case "7":
                    System.out.println("Successfully exited the program.");
                    break label;
                default:
                    System.out.println("Invalid input!! please select an input from below.");
                    break;

            }

        }
    }
}
