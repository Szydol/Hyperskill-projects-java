package contacts;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        String filename = "phonebook.db";
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        do {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String action = scanner.nextLine();
            switch (action) {
                case "add":
                    try {
                        SerializationUtils.serialize(Contact.getContacts(), filename);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.print("Enter the type (person, organization): ");
                    String type = scanner.nextLine();
                    Contact.createContact(type, scanner);
                    break;
                case "list":
                    Contact.showContacts();
                    System.out.println();
                    System.out.print("[list] Enter action ([number], back): ");
                    String actionInList = scanner.nextLine();
                    if (actionInList.equals("back")) {
                        System.out.println();
                        break;
                    }
                    Contact.getContacts().get(Integer.parseInt(actionInList) - 1).getInfo();
                    System.out.println();
                    Contact.recordMenu(scanner, actionInList);
                    break;
                case "search":
                    flag = Contact.searchMenu(scanner);
                    break;
                case "count":
                    Contact.count();
                    System.out.println();
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("Action is not correct!");
                    break;
            }
        } while (flag);
        scanner.close();
    }
}