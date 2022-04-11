import util.Input;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *  CONTACT PROGRAMS
 *
 *  Description: Combines cm crud and file i/o functions into larger programs the user
 *  within the ca menus
 */


public class ContactPrograms {

    // I
    ContactsManager contactsManager = new ContactsManager("contacts.txt", "data");
    Input input = new Input();
    // F

    // CON
    public ContactPrograms() {
    }



    // METHS
    // Read All
    public void readAllContacts(){
        contactsManager.printLines();
    }

    // Mod Contact
    public void modContact(){
        String mContact = findContactByBothName(true);
        String changeContact = "";
        if(mContact.length() > 0) {
            int menuChoice = chooseModMenu(mContact);
            chooseModOption(menuChoice, mContact);
        }


    }

    // Mod Contact <-- HELPERS
    private String modName(String mainLine){
        String fName = input.getString("Enter First Name: ");
        String lName = input.getString("Enter Last Name: ");
        return fName + " " + lName + mainLine.substring(mainLine.indexOf(","));
    }
    private String modPhone(String mainLine){
        String phone = input.getString("Enter First Phone Number: ");
        int first = mainLine.indexOf(",");
        int end = mainLine.indexOf(",", first + 1);
        return mainLine.substring(0, first) + phone + mainLine.substring(end);
    }
    private String modEmail(String mainLine){
        String email = input.getString("Enter First Email: ");
        int first = mainLine.indexOf(",");
        int end = mainLine.indexOf(",", first + 1);
        return mainLine.substring(0, end) + ", " + email;
    }
    private String modAll(String mainLine){
        String fName = input.getString("Enter First Name: ");
        String lName = input.getString("Enter Last Name: ");
        String phone = input.getString("Enter First Phone Number: ");
        String email = input.getString("Enter First Email: ");

        int first = mainLine.indexOf(",");
        int end = mainLine.indexOf(",", first + 1);
        return fName + " " + lName + ", " + phone + ", " + email;
    }

    private int chooseModMenu(String mainLine){
        System.out.println("Warning! Now modifying contact: " + mainLine);
        return input.getInt(1, 4,"Choose which part of the contact you would like to edit.\n" +
                "--> Must Select a Number <--\n" +
                "1. Name\n" +
                "2. Phone\n" +
                "3. Email\n" +
                "4. The Entire Contact");
    }
    private String chooseModOption(int choice, String mainLine){
        switch (choice){
            case 1:
                System.out.println(mainLine);
                return modName(mainLine);
            case 2:
                System.out.println(mainLine);
                return modPhone(mainLine);
            case 3:
                System.out.println(mainLine);
                return modEmail(mainLine);
            case 4:
                System.out.println(mainLine);
                return modAll(mainLine);
            default:
                return "";
        }
    }




    // Add
    public void addContact(){
        String fName = input.getString("Enter First Name: ");
        String lName = input.getString("Enter Last Name: ");
        String phone = "";
        String email = "";

        if (checkContactExists(fName, lName)) {
            System.out.println("Would Like to Continue?  ");
            if(input.yesNo()){
                phone = input.getString("Enter Phone Number: ");
                email = input.getString("Enter Email: ");
                contactsManager.addLines(fName + " " +
                        lName + ", " + phone + ", " + email);
                contactsManager.printLines();
            } else {
                System.out.println("This function has terminated... ");
            }
        } else {
            phone = input.getString("Enter Phone Number: ");
            email = input.getString("Enter Email: ");
            contactsManager.addLines(fName + " " +
                    lName + ", " + phone + ", " + email);
            contactsManager.printLines();
        }
    }

    // Delete Contact
    public void deleteContact(){
        String contactLine = findContactByBothName(true);
        if(contactLine.length() > 0){
            if(input.yesNo("Contact Found.  Confirm Delete Contact: (Y/N)  ")){
                contactsManager.deleteLine(contactLine);
                System.out.println("--------------------------------------------");
            } else {
                System.out.println("No Contact Was Deleted.");
                System.out.println("--------------------------------------------");
            }
        } else {
            System.out.println("No Contact Was Deleted.");
            System.out.println("--------------------------------------------");
        }

    }

    // Find
    public void findContactByFirstName(){
        String name = input.getString("Enter First Name: ");
        for (String line : contactsManager.getFileData()) {
            if (checkFirstName(line.toLowerCase(), name.toLowerCase())){
                System.out.println(line);
            }
        }
    }
    public void findContactByLastName(){
        String name = input.getString("Enter Last Name: ");
        for (String line : contactsManager.getFileData()) {
            if (checkLastName(line.toLowerCase(), name.toLowerCase())){
                System.out.println(line);
            }
        }
    }
    public String findContactByBothName(boolean flag){
        if (!flag) return "";

        String name1 = input.getString("Enter First Name: ");
        String name2 = input.getString("Enter Last Name: ");
        for (String line : contactsManager.getFileData()) {
            if (checkFirstName(line.toLowerCase(), name1.toLowerCase())
                    &&
                    checkLastName(line.toLowerCase(), name2.toLowerCase())){
                System.out.println(line);
                return line;
            }
        }
        System.out.println(name1 + " " + name2 + " was not found.");
        return findContactByBothName(input.yesNo("Try again?  (Y/N)"));
    }


    // CHECKS
    private boolean checkFirstName(String line, String name){
        return name.equals(line.substring(0, line.indexOf(" ")));
    }
    private boolean checkLastName(String line, String name){
        return name.contains(line.substring(line.indexOf(" ") + 1, line.indexOf(",")));
    }
    private boolean checkContactExists(String name1, String name2){
        for (String line : contactsManager.getFileData()) {
            if (checkFirstName(line.toLowerCase(), name1.toLowerCase())
                            &&
                            checkLastName(line.toLowerCase(), name2.toLowerCase())){
                System.out.println("That contact already exists.");
                return true;
            }
        }
        return false;
    }







}
