import java.util.Comparator;

public class drLastnameSort implements Comparator<Doctor>{

        public int compare(Doctor o1, Doctor o2){
            return  o1.getLastName().compareTo(o2.getLastName());
        }
    }

    class drFirstNameSort implements Comparator<Doctor>

    {
        public int compare(Doctor o1, Doctor o2)
        {
            return  o1.getFirstName().compareTo(o2.getFirstName());
        }
    }

