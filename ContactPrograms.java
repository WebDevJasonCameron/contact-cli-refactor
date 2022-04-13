import util.Input;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *  CONTACT PROGRAMS
 *
 *  Description: Combines cm crud and file i/o functions into larger programs the user
 *               within the ca menus
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
        System.out.println("Name                     | Phone Number        | Email\n" +
                "<---------------------------------------------->\n");
        contactsManager.printLines();
        System.out.println("<---------------------------------------------->\n");
    }

    // Mod Contact
    public void modContact(){
        String mContact = findContactByBothName(true);
        String changeContact = "";

        if(mContact.length() > 0) {
            int menuChoice = chooseModMenu();
            changeContact = chooseModOption(menuChoice, mContact);
        } else {
            System.out.println("Something broke in modContact()");
        }

        contactsManager.modLine(mContact, changeContact);
        System.out.println("Contact Changed:  " + changeContact);
        System.out.println("\n<---------------------------------------------->\n");

    }

    // Mod Contact <-- HELPERS
    private String modName(String mainLine){
        String fName = input.getString("Enter First Name: ").trim();
        String lName = input.getString("Enter Last Name: ").trim();
        String wholeName = includeSpaces(fName + " " + lName, 25);
        return wholeName + mainLine.substring(mainLine.indexOf("|"));
    }
    private String modPhone(String mainLine){
        String phone = includeSpaces(phoneCheck(input.getString("Enter Phone Number: \n" +
                "--> Do Not Include Dashes <--")), 20);
        int first = mainLine.indexOf("|");
        int end = mainLine.indexOf("|", first + 1);
        return mainLine.substring(0, first) + "| " + phone + mainLine.substring(end);
    }
    private String modEmail(String mainLine){
        String email = input.getString("Enter First Email: ").trim();
        int first = mainLine.indexOf("|");
        int end = mainLine.indexOf("|", first + 1);
        return mainLine.substring(0, end) + "| " + email;
    }
    private String modAll(String mainLine){
        String fName = input.getString("Enter First Name: ").trim();
        String lName = input.getString("Enter Last Name: ").trim();
        String phone = includeSpaces(phoneCheck(input.getString("Enter Phone Number: \n" +
                "--> Do Not Include Dashes <--")), 20);
        String email = input.getString("Enter First Email: ");
        String wholeName = includeSpaces(fName + " " + lName, 25);

        int first = mainLine.indexOf("|");
        int end = mainLine.indexOf("|", first + 1);
        return wholeName + "| " + phone + "| " + email;
    }
    private int chooseModMenu(){
        System.out.println("Warning! Now modifying contact! ");
        return input.getInt(1, 5,"Choose what to edit.\n" +
                "--> Must Select a Number <--\n" +
                "1. Name\n" +
                "2. Phone\n" +
                "3. Email\n" +
                "4. The Entire Contact\n" +
                "5. Exit");
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
            case 5:
                System.out.println("You Chose Not to Edit a Contact.");
                return mainLine;
            default:
                return "";
        }
    }


    // Add
    public void addContact(){
        String fName = input.getString("Enter First Name: ").trim();
        String lName = input.getString("Enter Last Name: ").trim();
        String wholeName = includeSpaces(fName + " " + lName, 25);
        String phone = "";
        String email = "";

        if (checkContactExists(fName, lName)) {
            System.out.println("Would Like to Continue?  ");
            if(input.yesNo()){
                phone = includeSpaces(phoneCheck(input.getString("Enter Phone Number: \n" +
                        "--> Do Not Include Dashes <--")), 20);
                email = input.getString("Enter Email: ");
                contactsManager.addLines(wholeName +
                        "| " + phone + "| " + email);
                System.out.println("Contact Added:  " + wholeName +
                        "| " + phone + "| " + email);
                System.out.println("<---------------------------------------------->\n");
                contactsManager.printLines();
            } else {
                System.out.println("This function has terminated... ");
            }
        } else {
            phone = includeSpaces(phoneCheck(input.getString("Enter Phone Number: \n" +
                    "--> Do Not Include Dashes <--")), 20);
            email = input.getString("Enter Email: ");
            contactsManager.addLines(wholeName +
                    "| " + phone + "| " + email);
            System.out.println("Contact Added:  " + wholeName +
                    "| " + phone + "| " + email);
            System.out.println("<---------------------------------------------->\n");
            contactsManager.printLines();
        }
        System.out.println("\n<---------------------------------------------->\n");
    }

    // Delete Contact
    public void deleteContact(){
        String contactLine = findContactByBothName(true);
        if(contactLine.length() > 0){
            if(input.yesNo("Contact Found.  Confirm Delete Contact: (Y/N)  ")){
                contactsManager.deleteLine(contactLine);
            } else {
                System.out.println("No Contact Was Deleted.");
            }
        } else {
            System.out.println("No Contact Was Deleted.");
        }

    }

    // Find
    public void findContactByFirstName(){
        String name = input.getString("Enter First Name: ").trim();
        for (String line : contactsManager.getFileData()) {
            if (checkFirstName(line.toLowerCase(), name.toLowerCase())){
                System.out.println(line);
            }
        }
    }
    public void findContactByLastName(){
        String name = input.getString("Enter Last Name: ").trim();
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
            if (checkFirstName(line.toLowerCase().trim(), name1.toLowerCase().trim())
                    &&
                    checkLastName(line.toLowerCase().trim(), name2.toLowerCase().trim())){
                System.out.println("Found Contact: " + line);
                System.out.println("--------------------------------------------");
                return line;
            }
        }
        System.out.println(name1 + " " + name2 + " was not found.");
        return findContactByBothName(input.yesNo("Try again?  (Y/N)"));
    }


    // CHECKS & FIXES
    private boolean checkFirstName(String line, String name){
        return name.trim().equals(line.substring(0, line.indexOf(" ")));
    }
    private boolean checkLastName(String line, String name){
        return name.contains(line.substring(line.indexOf(" ") + 1, line.indexOf("|")).trim());
    }
    private boolean checkContactExists(String name1, String name2){
        for (String line : contactsManager.getFileData()) {
            if (checkFirstName(line.toLowerCase(), name1.trim().toLowerCase())
                            &&
                            checkLastName(line.toLowerCase(), name2.trim().toLowerCase())){
                System.out.println("That contact already exists.");
                return true;
            }
        }
        return false;
    }
    // <-- Email check here
    // Phone check
    public String phoneCheck(String phoneDigits){
        if(phoneDigits.length() == 11){
            return phoneElevenDigits(phoneDigits);
        } else if (phoneDigits.length() == 10){
            return phoneTenDigits(phoneDigits);
        } else if (phoneDigits.length() == 7){
            return phoneSevenDigits(phoneDigits);
        } else {
            System.out.println("That is not a valid entry");
            return phoneCheck(input.getString("Enter Phone Number: \n" +
                    "--> Do Not Include Dashes <--"));
        }
    }

    // Digit checks
    private String phoneSevenDigits(String digits){
        return digits.substring(0,3) + "-" + digits.substring(3);
    }
    private String phoneTenDigits(String digits){
        return digits.substring(0,3) + "-" + digits.substring(3, 6) + "-" + digits.substring(6);
    }
    private String phoneElevenDigits(String digits){
        return digits.substring(0,1) + "-" +
                digits.substring(1, 4) + "-" +
                digits.substring(4, 7) + "-" +
                digits.substring(7);
    }

    // Space Checks
    public String includeSpaces(String nameOrPhone, int totalSpaces){
        if((totalSpaces - nameOrPhone.length())  == 0) return nameOrPhone;
        nameOrPhone += " ";
        return includeSpaces(nameOrPhone, totalSpaces);
    }

    // Email Checks




}  //  <-- END
