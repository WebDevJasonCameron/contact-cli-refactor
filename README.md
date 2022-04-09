# Contacts Command Line Interface (CLI)
Command Line Interface to manage a contact list

## Assignment Objectives:
- [X] Show all your contacts
- [] Add a new contact
- [] Search a contact by their name
- [] Delete an existing contact
- [] Keep data inside a contacts.txt
Bonus...
- [] Format the phone numbers using dashes
- [] Allow formatting phone numbers with different lengths
- [] Warn user when entering duplicate contact... provide a (Y/N) response 
Other Ideas...
- [] Include email
- [] Backup

## Assignment Recommendations:
- The text file shoud contain w=one contact per line
- When the application starts, the contact list should be read from the file. 
- Before the application exits, the contacts file should be rewritten. 

## App structure
1. Contact Text: key, name (first & last), phone, email

2. Contact class: <br/>
   Fields:
   <span style="color:#7ca9f2">
   (key, fName, lName, phone, email) <br/>
   </span>
   Methods:
   <span style="color:#7ca9f2">
   (set, get)<br/>
   </span>
   Description:
   <span style="color:#7ca9f2">
   TEXT
   </span>

3. ContactManager class: <br/>
   Fields:
   <span style="color:#7ca9f2"> 
   (filename, directory, fileData, directoryPath, filePath) <br/>
   </span>
   Methods:
   <span style="color:#7ca9f2">
   (create, read, update, delete, saveFile) <br/> 
   </span> 
   Description:
   <span style="color:#7ca9f2">
   Manages CRUD data between application and data file
   </span>

4. ContactPrograms: <br/>
   Fields:
   <span style="color:#7ca9f2">
   (FIELDS) <br/>
   </span>
   Methods:
   <span style="color:#7ca9f2">
   (readAll, findContact, checkContact, modContact; sortContacts; deleteContact) <br/>
   </span>
   Description:
   <span style="color:#7ca9f2">
    Combines cm crud functions into larger programs the user selects within the ca menus
   </span>
5. ContactApplication: <br/>
   Fields:
   <span style="color:#7ca9f2">
   (FIELDS) <br/>
   </span>
   Methods:
   <span style="color:#7ca9f2">
   (readAll, findContact, checkContact, modContact; sortContacts; deleteContact) <br/>
   </span>
   Description:
   <span style="color:#7ca9f2">
   Provides user with menus, calls program according to user selection, includes overall application run and terminate function
   </span>
6. UI: Input:  <br/>
   Fields:
   <span style="color:#7ca9f2">
   (FIELDS) <br/>
   </span>
   Methods:
   <span style="color:#7ca9f2">
   (getString, getPhone, getEmail, checkName, checkPhone, checkEmail) <br/>
   </span>
   Description:
   <span style="color:#7ca9f2">
   Manages user inputs, checks for errors
   </span>


