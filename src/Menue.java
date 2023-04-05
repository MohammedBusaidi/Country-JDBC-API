import java.util.HashMap;
import java.util.Scanner;

public class Menue {
    Scanner menueSc = new Scanner(System.in);
    JDBC newJDBC = new JDBC();
    APIConsumer newAPI = new APIConsumer();
    public void showMenue() {
        boolean menueLoop = true;
        while (menueLoop) {
            HashMap<Integer, String> menueOptions = new HashMap<>();
            menueOptions.put(1, "Initialize Database");
            menueOptions.put(2, "Backup Database");
            menueOptions.put(3, "Remove Table");
            menueOptions.put(4, "Show all Countries");
            menueOptions.put(5, "fetch Data");
            menueOptions.put(6, "Search by: ");
            menueOptions.put(7, "Dump data into file");
            menueOptions.put(8, "Retrieve data From file");
            menueOptions.put(9, "Exit");

            int choice = 0;
            while (choice != 9) {
                System.out.println("==================UNIVERSITIES DATABASE==================");
                for (int i = 1; i <= 9; i++) {
                    System.out.println(i + ". " + menueOptions.get(i));
                }
                System.out.println("=========================================================");
                System.out.print("Enter your choice: ");
                choice = menueSc.nextInt();
                switch (choice) {
                    case 1:
                        newJDBC.initializeDatabase();
                        break;
                    case 2:
                        newJDBC.backupDatabase();
                        break;
                    case 3:
                        newJDBC.removeTable();
                        break;
                    case 4:
                        break;
                    case 5:
                        boolean fetchLoop = true;
                        while (fetchLoop) {
                            System.out.println("1- By Database");
                            System.out.println("2- By API");
                            int fetchInput = menueSc.nextInt();
                            if (fetchInput == 1) {
                                break;
                            } else if (fetchInput == 2) {
                                break;
                            } else {
                                fetchLoop = false;
                            }
                        }
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        System.out.println("GOOD BYE!");
                        System.exit(0);
                        break;
                }
            }
        }
    }
}
