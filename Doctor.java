public class Doctor extends Person{

    private int medicalLisenceNumber;
    private String specialisation;

    public int getMedicalLisenceNumber() {
        return medicalLisenceNumber;
    }

    public void setMedicalLisenceNumber(int medicalLisenseNumber) {
        this.medicalLisenceNumber = medicalLisenseNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    Doctor(String firstName, String lastname, String dateOfBirth,           // creating constructor
           int mobileNumber, int medicalLisenseNumber, String specialisation){
        setFirstName(firstName);
        setLastName(lastname);
        setDateOfBirth(dateOfBirth);
        setMobileNumber(mobileNumber);
        setMedicalLisenceNumber(medicalLisenseNumber);
        setSpecialisation(specialisation);

    }
}
