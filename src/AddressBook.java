import java.util.ArrayList;
import java.util.Scanner;

class AddressBook {
    private static ArrayList<Person> personList = new ArrayList<>();
    public static void main(String[] args) {
        personList.add(new Person("John", "O'Sullivan", "0831835567", "5, Suir house"));
        System.out.println(personList);
        chooseOption();
    }

    private static void chooseOption() {
        Scanner sc = new Scanner(System.in);
        int selection;
        boolean exit = false;
        while (!exit) {
            System.out.println("Menu : ");
            System.out.println("Type any number between 1 and 7 for selection");
            System.out.println("1)Create a new Contact Record");
            System.out.println("2)Remove a Contact Record");
            System.out.println("3)Edit a Contact Record");
            System.out.println("4)Get Specific Contact Record information");
            System.out.println("5)List all Contacts by Firstname");
            System.out.println("6)List all Contacts by Lastname");
            System.out.println("7)Exit");
            selection = sc.nextInt();
            switch (selection) {

                case 1: //ADD A CONTACT
                    sc = new Scanner(System.in);
                    System.out.println("Enter Contact Firstname: ");
                    String firstname = sc.nextLine();
                    System.out.println("Enter Contact Lastname: ");
                    String lastname = sc.nextLine();
                    System.out.println("Enter Contact Phone number: ");
                    String phone = sc.nextLine();
                    System.out.println("Enter Contact Address: ");
                    String address = sc.nextLine();
                    System.out.println("Do you want to save this contact to address book? Type Yes / No");
                    Scanner sc1 = new Scanner(System.in);
                    String choice1 = sc1.next();
                    if (choice1.equalsIgnoreCase("yes")) {
                        personList.add(new Person(firstname, lastname, phone, address));
                        System.out.println("Details saved");
                    } else if (choice1.equalsIgnoreCase("no")) {
                        System.out.println("Details Discarded");
                    }
                    break;

                case 2: // DELETE A CONTACT RECORD
                    personList.sort(new IdSort());
                    displayContactList();
                    System.out.println("PLEASE SELECT CONTACT \"ID\" FROM LIST ABOVE TO REMOVE");
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Please Enter Contact ID to remove");
                    int in = sc2.nextInt();
                    System.out.println("Are you sure you want to remove this contact? (Type Yes / No):");
                    System.out.println(checkArray(in));
                    String choice2 = sc2.next();
                    if (choice2.equalsIgnoreCase("yes")) {
                        removeContact(in);

                    } else {
                        System.out.println("Contact NOT deleted");
                    }
                    exit = backToMenu();
                    break;

                case 3: // EDIT A CONTACT RECORD
                    displayContactList();
                    System.out.println("PLEASE SELECT CONTACT \"ID\" FROM LIST ABOVE TO EDIT");
                    Scanner sc3 = new Scanner(System.in);
                    int choice3 = sc3.nextInt();
                    editContact(choice3);
                    System.out.println("Changes Saved!");
                    exit = backToMenu();
                    break;

                case 4: //GET SPECIFIC CONTACT RECORD INFORMATION
                    personList.sort(new IdSort());
                    displayContactList();
                    System.out.println("PLEASE ENTER CONTACT \"ID\" TO RETRIEVE SPECIFIC DETAILS");
                    Scanner sc4 = new Scanner(System.in);
                    int in3 = sc4.nextInt();
                    System.out.println(getContactInformation(in3));
                    exit = backToMenu();
                    break;

                case 5: // LIST ALL CONTACTS BY FIRST NAME
                    System.out.println("Sorting Contacts by Firstname");
                    personList.sort(new FirstnameSort());
                    displayContactList();
                    exit = backToMenu();
                    break;

                case 6: // LIST ALL CONTACTS BY LAST NAME
                    System.out.println("Sorting Contacts by Lastname");
                    personList.sort(new LastnameSort());
                    displayContactList();
                    exit = backToMenu();
                    break;

                case 7: // EXIT APPLICATION
                    System.out.println("Thank you for using this application! GoodBye");
                    exit = true;
                    break;
            }
        }
    }

    private static void editContact(int choice3) {
        for (Person person : personList) {
            if (person.getId() == choice3) {
                Scanner input = new Scanner(System.in);
                System.out.println("Please enter Contact First Name." + " Current Value : " + person.getFirstname());
                System.out.println("Please leave blank if no changes required");
                String firstname = input.nextLine();
                if (!firstname.equals("")) {
                    person.setFirstname(firstname);
                }

                System.out.println("Please enter Contact Last Name." + " Current Value : " + person.getLastname());
                System.out.println("Please leave blank if no changes required");
                String lastname = input.nextLine();
                if (!lastname.equals("")) {
                    person.setLastname(lastname);
                }

                System.out.println("Please enter Contact Address." + " Current Value : " + person.getAddress());
                System.out.println("Please leave blank if no changes required");
                String address = input.nextLine();
                if (!address.equals("")) {
                    person.setAddress(address);
                }

                System.out.println("Please enter Contact Phone." + " Current Value : " + person.getPhone());
                System.out.println("Please leave blank if no changes required");
                String phone = input.nextLine();
                if (!phone.equals("")) {
                    person.setPhone(phone);
                }
                break;
            }
        }
    }

    private static void displayContactList() {
        System.out.println("ID | FULL NAME");
        for (Person person : personList) {
            System.out.println(person.getId() + "  | " + person.getFirstname() + " " + person.getLastname());
        }
    }

    private static String getContactInformation(int inp) {
        int id = 0;
        for (Person p : personList) {
            if (p.getId() == inp) {
                id = personList.indexOf(p);
            }
        }
        return "ID : " + personList.get(id).getId() + "\n" +
                "First Name : " + personList.get(id).getFirstname() + "\n" +
                "Last Name : " + personList.get(id).getLastname() + "\n" +
                "Address : " + personList.get(id).getAddress() + "\n" +
                "Phone : " + personList.get(id).getPhone();
    }

    private static Person checkArray(int in) {
        int id = 0;
        for (Person p : personList) {
            if (p.getId() == in) {
                id = personList.indexOf(p);
            }
        }
        return personList.get(id);
    }

    private static void removeContact(int in) {
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId() == in) {
                personList.remove(i);
                System.out.println("Contact REMOVED successfully!");
                break;
            }
        }
    }

    private static boolean backToMenu() {
        boolean exit = false;
        System.out.println("Return to Main Menu? Type Yes / No");
        Scanner sc2 = new Scanner(System.in);
        if (sc2.next().equalsIgnoreCase("no"))
            exit = true;
        return exit;
    }
}