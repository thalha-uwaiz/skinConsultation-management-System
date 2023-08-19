public class Patient extends Person{            //creating subclass Person

    private int patientUniqueId;

    public int getPatientUniqueId() {
        return patientUniqueId;
    }

    public void setPatientUniqueId(int patientUniqueId) {
        patientUniqueId = patientUniqueId;
    }

    Patient(String firstName, String lastname, String dateOfBirth,           // creating constructor
            int mobileNumber,int patientUniqueId){

        setPatientUniqueId(patientUniqueId);
        setFirstName(firstName);
        setLastName(lastname);
        setDateOfBirth(dateOfBirth);
        setMobileNumber(mobileNumber);

    }
}
