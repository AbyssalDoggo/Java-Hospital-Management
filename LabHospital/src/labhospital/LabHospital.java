package labhospital;

import UI.Menu;
import data.Hospital;
import java.text.ParseException;
import java.util.Scanner;

public class LabHospital {

    public static void main(String[] args) throws ParseException {
        int choice = 0;
        Hospital hospital = new Hospital();

        Menu menu = new Menu("\nHospital's Management");
        Menu mNurse = new Menu("\nNurse's Management.");
        Menu mPatient = new Menu("\nPatient's Management");
        Menu menu5 = new Menu("");

        menu.addOption("1. Nurse's management");
        menu.addOption("2. Patient's management");
        menu.addOption("3. Save data");
        menu.addOption("4. Load data");
        menu.addOption("5. Exit the program");

        mNurse.addOption("1.1 Create a nurse");
        mNurse.addOption("1.2 Find a nurse");
        mNurse.addOption("1.3 Update a nurse");
        mNurse.addOption("1.4 Delete a nurse");
        mNurse.addOption("1.5 Return to main menu");

        mPatient.addOption("2.1 Add a patient");
        mPatient.addOption("2.2 Display a patient");
        mPatient.addOption("2.3 Sort the patients list");
        mPatient.addOption("2.4 Return to main menu");

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    int choiceNurse;
                    do {
                        mNurse.printMenu();
                        choiceNurse = mNurse.getChoice();
                        switch (choiceNurse) {
                            case 1:
                                hospital.createANurse();
                                break;
                            case 2:
                                hospital.findANurse();
                                break;
                            case 3:
                                hospital.updateANurse();
                                break;
                            case 4:
                                hospital.deleteANurse();
                                break;
                            case 5:
                                System.out.println("Return to main menu");
                                break;
                        }
                    } while (choiceNurse != 5);
                    break;
                case 2:
                    int choicePatient;
                    do {
                        mPatient.printMenu();
                        choicePatient = mPatient.getChoice();
                        switch (choicePatient) {
                            case 1:
                                hospital.addAPatient();
                                break;
                            case 2:
                                hospital.displayPatients();
                                break;
                            case 3:
                                hospital.sortPatients();
                                break;
                            case 4:
                                System.out.println("Returning to main menu");
                                break;
                        }
                    } while (choicePatient != 4);
                    break;
                case 3:
                    hospital.saveData();
                    break;
                case 4:
                    hospital.loadData();
                    break;
                case 5:
                    choice = 10;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        } while (choice != 10);
        System.out.println("Program ended");
    }
}
