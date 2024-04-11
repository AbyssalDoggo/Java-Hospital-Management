package data;

import Validation.Util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Hospital {

    private HashMap<String, Nurse> mapNurse = new HashMap<>();
    private HashMap<String, Patient> mapPatient = new HashMap<>();
    private boolean checkava = false;

// Create a nurse
    public void createANurse() {
        String id, confirm;
        do {
            do {
                id = Util.formatString("Enter ID (N[4 numbers]: ", "Wrong format", "^[N|n]\\d{4}$");
                if (mapNurse.containsKey(id) == true) {
                    System.out.println("ID already exists");
                }
            } while (mapNurse.containsKey(id) == true);
            String name = Util.getString("Enter name: ", "Wrong format");
            int age = Util.getInteger("Enter age: ", "Wrong format", 0);
            int choiceGender = Util.getInteger("Choose gender (1.Male | 2.Female): ", "Please choose either 1 or 2", 1, 2);
            String gender;
            if (choiceGender == 1) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            String address = Util.getString("Enter address: ", "Wrong format");
            String phone = Util.formatString("Enter phone number (Require 10 numbers): ", "Wrong format", "^\\d{10}$");
            String department = Util.getString("Enter department: ", "Wrong format");
            String shift = Util.getString("Enter shift: ", "Wrong format");
            double salary = Util.getDouble("Enter salary: ", "Wrong format", 0);
            mapNurse.put(id, new Nurse(id, name, age, gender, address, phone, department, shift, salary));
            System.out.println("Added successfully.");
            checkava = true;
            confirm = Util.formatString("Continue adding? (Y/N): ", "Please choose either Y or N ", "^[N|n|Y|y]$");
        } while (confirm.equalsIgnoreCase("Y"));
    }

//Find a nurse
    public void findANurse() {
        boolean check = false;
        String name = Util.getString("Enter nurse's name: ", "The nurse does not exist");
        System.out.println("+------+----------------+-----+----------+--------------+--------------+------------+-------+---------+");
        System.out.println(String.format("|%4s  |%10s      |%4s |%9s |%12s  |%10s    |%11s |%6s |%8s |", "ID", "NAME", "AGE", "GENDER", "ADDRESS", "PHONE", "DEPARTMENT", "SHIFT", "SALARY"));
        System.out.println("+------+----------------+-----+----------+--------------+--------------+------------+-------+---------+");
        for (Nurse x : mapNurse.values()) {
            if (x.getName().toLowerCase().contains(name.toLowerCase())) {
                x.print();
                check = true;
            }
        }
        if (check == false) {
            System.out.println("Cannot find the nurse");
        }
        System.out.println("+------+----------------+-----+----------+--------------+--------------+------------+-------+---------+");
    }

// Update a nurse
    public void updateANurse() {
        String id, confirm;
        do {
            do {
                id = Util.formatString("Enter ID (N[4 numbers]): ", "Wrong format", "^[N|n]\\d{4}$");
                for (Nurse x : mapNurse.values()) {
                    x.print();
                }
                if (mapNurse.containsKey(id) == false) {
                    System.out.println("The nurse does not exist");
                }
            } while (mapNurse.containsKey(id) == false);
            Nurse a = mapNurse.get(id);
            String newName = Util.updateString("Enter new name: ", a.getName());
            int newAge = Util.updateInteger("Enter new age: ", 0, a.getAge());
            String newGender;
            int choiceGender = Util.getInteger("Choose gender (1.Male | 2.Female): ", "Choose either 1 or 2", 1, 2);
            if (choiceGender == 1) {
                newGender = "Male";
            } else {
                newGender = "Female";
            }
            String newAddress = Util.updateString("Enter new address: ", a.getAddress());
            String newPhone = Util.updateString("Enter new phone number: ", a.getPhone());
            String newDepartment = Util.updateString("Enter new department: ", a.getDepartment());
            String newShift = Util.updateString("Enter new shift: ", a.getShift());
            double newSalary = Util.updateDouble("Enter new salary: ", 0, a.getSalary());

            a.setName(newName);
            a.setAge(newAge);
            a.setGender(newGender);
            a.setAddress(newAddress);
            a.setPhone(newPhone);
            a.setDepartment(newDepartment);
            a.setShift(newShift);
            a.setSalary(newSalary);

            System.out.println("Updated successfully!");
            checkava = true;
            confirm = Util.formatString("Continue updating? (Y|N): ", "Please choose either Y or N ", "^[N|n|Y|y]$");
        } while (confirm.equalsIgnoreCase("Y"));
    }

    // Delete a nurse
    public void deleteANurse() {
        String id, confirm;
        id = Util.formatString("Enter ID (N[4 numbers]: ", "Wrong format", "^[N|n]\\d{4}$");
        if (mapNurse.containsKey(id)) {
            confirm = Util.formatString("Confirm deletion? (Y | N): ", "The nurse does not exist", "^[N|n|Y|y]$");
            if (confirm.equalsIgnoreCase("Y")) {
                boolean check = false;
                for (Patient x : mapPatient.values()) {
                    if (x.getNurseAssigned().toLowerCase().contains(id.toLowerCase())) {
                        check = true;
                    }
                }
                if (check) {
                    System.out.println("This nurse is already occupied");
                } else {
                    mapNurse.remove(id);
                    System.out.println("Delete successfully");
                    checkava = true;
                }
            }
        } else {
            System.out.println("The nurse does not exist");
        }
    }

// Add a patient
    public void addAPatient() {
        String id, confirm;
        do {
            do {
                id = Util.formatString("Enter patient's ID(P[4 numbers]): ", "Wrong format", "^[P|p]\\d{4}$");
                if (mapPatient.containsKey(id) == true) {
                    System.out.println("ID already existed");
                }
            } while (mapPatient.containsKey(id) == true);
            String name = Util.getString("Enter name: ", "Wrong format");
            int age = Util.getInteger("Enter age: ", "Wrong format", 0);
            String gender;
            int choiceGender = Util.getInteger("Choose gender (1.Male | 2.Female): ", "Please choose either 1 or 2", 1, 2);
            if (choiceGender == 1) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            String address = Util.getString("Enter address: ", "Wrong format");
            String phone = Util.formatString("Enter phone number (Require 10 numbers): ", "Wrong format", "^\\d{10}$");
            String diagnosis = Util.getString("Enter diagnosis: ", "Please enter a valid diagnosis");
            String addmissionDate = Util.getDate("Enter the addmission date: ", "Wrong format");
            String dischargeDate = Util.getDate("Enter the discharge date: ", "Wrong format");
            System.out.println("+------+----------------+-----+----------+--------------+--------------+------------+-------+---------+");
            System.out.println(String.format("|%4s  |%10s      |%4s |%9s |%12s  |%10s    |%11s |%6s |%8s |", "ID", "NAME", "AGE", "GENDER", "ADDRESS", "PHONE", "DEPARTMENT", "SHIFT", "SALARY"));
            for (Nurse x : mapNurse.values()) {
                x.print();
            }
            System.out.println("+------+----------------+-----+----------+--------------+--------------+------------+-------+---------+");
            if (mapNurse.size() < 2) {
                System.out.println("There aren't enough nurse");
            } else {
                boolean check1, check2;
                String idNurse1, idNurse2;
                do {
                    idNurse1 = Util.formatString("Enter ID (N[4 numbers]: ", "Wrong format", "^[N|n]\\d{4}$");
                    check1 = checkNurseVacancy(idNurse1);
                    if (check1 == false) {
                        System.out.println("The nurse doesn't exist");
                    }
                } while (check1 == false);
                do {
                    idNurse2 = Util.formatString("Enter ID (N[4 numbers]: ", "Wrong format", "^[N|n]\\d{4}$");
                    check2 = checkNurseVacancy(idNurse2);
                    if (check2 == false) {
                        System.out.println("The nurse doesn't exist");
                    }
                    if (idNurse1.equalsIgnoreCase(idNurse2)) {
                        System.out.println("One patient can't have identical nurses");
                    }
                } while (check2 == false || (idNurse1.equalsIgnoreCase(idNurse2)));
                String nurseAssigned = idNurse1 + "," + idNurse2;

                mapPatient.put(id, new Patient(id, name, age, gender, address, phone, diagnosis, addmissionDate, dischargeDate, nurseAssigned));
                System.out.println("Added successfully");
                checkava = true;
            }
            confirm = Util.formatString("Continue adding? (Y|N): ", "Please choose either Y or N", "^[Y|y|N|y]$");
        } while (confirm.equalsIgnoreCase("Y"));

    }
//
    public boolean checkNurseVacancy(String id) {
        boolean check = false;
        for (Nurse x : mapNurse.values()) {
            if (x.getId().equalsIgnoreCase(id)) {
                check = true;
            }
        }
        if (check == true) {
            int count = 0;
            for (Patient x : mapPatient.values()) {
                if (x.getNurseAssigned().toLowerCase().contains(id)) {
                    count++;
                }
            }
            if (count >= 2) {
                check = false;
            }
        }
        return check;
    }

//
    public long checktime(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long l = sdf.parse(date).getTime();
        return l;
    }

//display patients
    public void displayPatients() throws ParseException {
        boolean check = false;

        if (mapNurse.isEmpty()) {
            System.out.println("There are no nurses");
        } else {
            String startDate = Util.getDate("Enter the addmission date: ", "Wrong format");
            String endDate;
            do {
                endDate = Util.getDate("Enter the discharge date: ", "Wrong format");
            } while (checktime(startDate) >= checktime(endDate));
            System.out.println("LIST OF PATIENTS");
            System.out.printf("Start date: %s\n", startDate);
            System.out.printf("End date: %s\n", endDate);
            for (Patient n : mapPatient.values()) {
                if (checktime(n.getAdmissionDate()) >= checktime(startDate) && checktime(endDate) >= checktime(n.getAdmissionDate())) {
                    n.print();
                    check = true;
                }
            }
            if (!check) {
                System.out.println("There are no patients that fit that period");
            }
        }
    }

//Sort patients
    public void sortPatients() {
        ArrayList<Patient> list = new ArrayList();
        for (Patient x : mapPatient.values()) {
            list.add(x);
        }
        int choice1 = Util.getInteger("Enter choice (1.Sort on ID | 2.Sort on name): ", "Please choose either 1 or 2", 1, 2);
        int choice2;

        System.out.println("List patients: ");
        if (choice1 == 1) {
            System.out.println("[Sorted by ID]");
            choice2 = Util.getInteger("(1.Ascend sort | 2.Descend sort): ", "Please choose either 1 or 2", 1, 2);
            if (choice2 == 1) {
                System.out.println("Sorted: ASC");
                Collections.sort(list, (a, b) -> {
                    return a.getId().compareToIgnoreCase(b.getId());
                });
            } else {
                System.out.println("Sorted: DESC");
                Collections.sort(list, (a, b) -> {
                    return b.getId().compareToIgnoreCase(a.getId());
                });
            }
        } else {
            System.out.println("Sorted by name: ");
            choice2 = Util.getInteger("(1.Ascend sort | 2.Descend sort): ", "Please choose either 1 or 2", 1, 2);
            if (choice2 == 1) {
                System.out.println("Sorted: ASC");
                Collections.sort(list, (a, b) -> {
                    return a.getName().compareToIgnoreCase(b.getName());
                });
            } else {
                System.out.println("Sorted: DESC");
                Collections.sort(list, (a, b) -> {
                    return b.getName().compareToIgnoreCase(a.getName());
                });
            }
        }
        System.out.println("+--+-----+------------+-------+-----------+-------+");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
        System.out.println("+--+-----+------------+-------+-----------+-------+");
    }
// Save data
    public void saveData() {
        boolean checkNurse, checkPatient;
        if (checkava == true) {
            checkNurse = true;
            checkPatient = true;
            try {
                FileOutputStream fos = new FileOutputStream("nurses.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(mapNurse);
                oos.close();
                fos.close();
            } catch (Exception e) {
                System.out.println("Fail to save data");
                checkNurse = false;
            }
            if (checkNurse) {
                System.out.println("Data saved successfully");
            }
            try {
                FileOutputStream fos = new FileOutputStream("patients.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(mapPatient);
                oos.close();
                fos.close();
            } catch (Exception e) {
                System.out.println("Fail to save data");
                checkPatient = false;
            }
            if (checkPatient == true) {
                System.out.println("Data saved successfully");
            }
        } else {
            System.out.println("There's no information to be saved");
        }
    }
// Load data
    public void loadData() {
        mapNurse.clear();
        mapPatient.clear();
        boolean checkNurse = true;
        boolean checkPatient = true;
        try {
            FileInputStream fis = new FileInputStream("nurses.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mapNurse = (HashMap<String, Nurse>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception e) {
            System.out.println("Fail to load data");
            checkNurse = false;
        }
        if (checkNurse == true) {
            System.out.println("Data loaded successfully");
        }
        try {
            FileInputStream fis = new FileInputStream("patients.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mapPatient = (HashMap<String, Patient>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception e) {
            System.out.println("Fail to load data");
            checkPatient = false;
        }
        if (checkPatient) {
            System.out.println("Data loaded successfully");
        }
    }
}
