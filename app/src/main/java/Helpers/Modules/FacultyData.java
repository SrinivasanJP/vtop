package Helpers.Modules;

public class FacultyData {
    String name,Designation,Cabin,Email;
    public FacultyData(){}
    public FacultyData(String name, String Designation, String Cabin, String Email) {
        this.name = name;
        this.Designation = Designation;
        this.Cabin = Cabin;
        this.Email = Email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getCabin() {
        return Cabin;
    }

    public void setCabin(String Cabin) {
        this.Cabin = Cabin;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
