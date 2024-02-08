package contacts;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {

    private String phoneNumber;
    private LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    private static List<Contact> contacts = new ArrayList<>();
    transient Scanner scanner = new Scanner(System.in);

    static boolean isPhoneNumber(String number) {
        String pattern = "^[+]?\\w*[- ]?([ -]?|\\([\\w]+\\)\\s*[\\w]*|\\([\\w]{2,}\\))([ -][\\w]{2,})*$";
        if (number.matches(pattern)) {
            return true;
        }
        return false;
    }

    public Contact(String phoneNumber) {

        this.phoneNumber = phoneNumber;
        creationTime = LocalDateTime.now();
        lastEditTime = LocalDateTime.now();
    }

    public Contact() {
        contacts.add(this);
    }

    abstract public String getName();

    abstract public void getInfo();

    abstract public String getFields();

    abstract public String getFullName();

    abstract public void setField(String field, String value);

    public static List<Contact> getContacts() {
        return contacts;
    }

    public static void showContacts() {
        for (Contact c : contacts) {
            System.out.println(contacts.indexOf(c) + 1 + ". " + c.getFullName());
        }
    }

    public static void remove(int id) {
        contacts.remove(id);
    }

    public static void filteredList(String query) {
        List<Contact> filteredContactList = new ArrayList<>();
        String queryString = ".*" + query + ".*";
        Pattern queryPattern = Pattern.compile(queryString, Pattern.CASE_INSENSITIVE);
        for (Contact c : contacts) {
            Matcher matcher = queryPattern.matcher(c.toString());
            if (matcher.find()) {
                filteredContactList.add(c);
            }
        }
        if (filteredContactList.size() == 1) {
            System.out.println("Found 1 result:");
        } else {
            System.out.println("Found " + filteredContactList.size() + " result:");
        }
        for (Contact c : filteredContactList) {
            System.out.println(contacts.indexOf(c) + 1 + ". " + c.getFullName());
        }
    }

    public static void count() {
        System.out.println("The Phone Book has " + Contact.getContacts().size() + " records.");
    }

    public static void createContact(String type, Scanner scanner) {
        switch (type) {
            case "person":
                Person newPerson = new Person();
                System.out.print("Enter the name: ");
                String nameOfPerson = scanner.nextLine();
                newPerson.setName(nameOfPerson);
                System.out.print("Enter the surname: ");
                String surname = scanner.nextLine();
                newPerson.setSurname(surname);
                System.out.print("Enter the birth date: ");
                String birthDate = scanner.nextLine();
                newPerson.setBirthDate(birthDate);
                System.out.print("Enter the gender (M, F): ");
                String gender = scanner.nextLine();
                newPerson.setGender(gender);
                System.out.print("Enter the number: ");
                String numberOfPerson = scanner.nextLine();
                newPerson.setPhoneNumber(numberOfPerson);
                System.out.println();
                break;
            case "organization":
                Organization newOrganization = new Organization();
                System.out.print("Enter the organization name: ");
                String nameOfOrganization = scanner.nextLine();
                newOrganization.setName(nameOfOrganization);
                System.out.print("Enter the address: ");
                String address = scanner.nextLine();
                newOrganization.setAddress(address);
                System.out.print("Enter the number: ");
                String numberOfOrganization = scanner.nextLine();
                newOrganization.setPhoneNumber(numberOfOrganization);
                System.out.println();
                break;
            default:
                System.out.println("Wrong contact type!");
        }
    }

    public static void recordMenu(Scanner scanner, String actionInList) {
        System.out.print("[record] Enter action (edit, delete, menu): ");
        String recordAction1 = scanner.nextLine();
        int id = Integer.parseInt(actionInList);
        switch (recordAction1) {
            case "edit":
                System.out.print("Select a field (" + Contact.getContacts().get(id - 1).getFields() + "): ");
                String recordField = scanner.nextLine();
                Contact.getContacts().get(id - 1).editField(scanner, recordField);
                break;
            case "delete":
                Contact.remove(id - 1);
                break;
            case "menu":
                break;
            default:
                System.out.println("There is no such action!");
                break;
        }
        System.out.println();
    }

    public static  boolean searchMenu(Scanner scanner){
        System.out.println();
        boolean flag = true;
        boolean f = true;
        do {
            System.out.print("[search] Enter action ([number], back, again): ");
            String actionInSearch = scanner.nextLine();
            switch (actionInSearch) {
                case "back":
                    f = false;
                    break;
                case "again":
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    Contact.filteredList(query);
                    System.out.println();
                    break;
                case "exit":
                    f = false;
                    flag = false;
                    break;
                default:
                    if (actionInSearch.matches("\\d+")) {
                        Contact.filteredList(actionInSearch);
                        System.out.println();
                        Contact.recordMenu(scanner, actionInSearch);
                        System.out.println();
                    } else {
                        Contact.filteredList(actionInSearch);
                        System.out.println();
                    }
                    break;
            }
        } while (f);
        return flag;
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
        scanner = new Scanner(System.in);
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (this.isPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "[no number]";
        }
    }

    public void editField(Scanner scanner, String field) {
        System.out.print("Enter " + field + ": ");
        String newValue = scanner.nextLine();
        this.setField(field, newValue);
        this.setLastEditTime(LocalDateTime.now());
        System.out.println("Saved");
        this.getInfo();
        System.out.println();
    }


}