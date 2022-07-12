package view.tm;

public class StudentTm {
    private String  StudentID;
    private String  Studentname;
    private String  email;
    private String  contact;
    private String Address;
    private String nic;

    public Student() {
    }

    public Student(String studentID, String studentname, String email, String contact, String address, String nic) {
        setStudentID(studentID);
        setStudentname(studentname);
        this.setEmail(email);
        this.setContact(contact);
        setAddress(address);
        this.setNic(nic);
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getStudentname() {
        return Studentname;
    }

    public void setStudentname(String studentname) {
        Studentname = studentname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentID='" + StudentID + '\'' +
                ", Studentname='" + Studentname + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", Address='" + Address + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
