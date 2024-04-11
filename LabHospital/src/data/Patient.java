
package data;

import java.io.Serializable;

public class Patient extends Person implements Serializable{
    private String diagnosis;
    private String admissionDate;
    private String dischargeDate;
    private String nurseAssigned;

    public Patient() {
    }

    public Patient(String id, String name, int age, String gender, String address, String phone, String diagnosis, String admissionDate, String dischargeDate, String nurseAssigned ) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(String nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }
    public void print(){
        int i = 0;
        i++;
        System.out.printf("|%d |%s| %s | %s | %s | %s |\n",i,super.getId(),admissionDate,super.getName(),super.getPhone(),diagnosis);
    }
}
