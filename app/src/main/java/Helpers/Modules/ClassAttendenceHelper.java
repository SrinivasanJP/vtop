package Helpers.Modules;

public class ClassAttendenceHelper {
    int Android_Programming, Computer_Networks, Operating_System, Ruby_Programming, Soft_Skills, Software_Construction;

    public ClassAttendenceHelper(){}
    public ClassAttendenceHelper(int android_Programming, int computer_Networks, int operating_System, int ruby_Programming, int soft_Skills, int software_Construction) {
        Android_Programming = android_Programming;
        Computer_Networks = computer_Networks;
        Operating_System = operating_System;
        Ruby_Programming = ruby_Programming;
        Soft_Skills = soft_Skills;
        Software_Construction = software_Construction;
    }

    public int getAndroid_Programming() {
        return Android_Programming;
    }

    public void setAndroid_Programming(int android_Programming) {
        Android_Programming = android_Programming;
    }

    public int getComputer_Networks() {
        return Computer_Networks;
    }

    public void setComputer_Networks(int computer_Networks) {
        Computer_Networks = computer_Networks;
    }

    public int getOperating_System() {
        return Operating_System;
    }

    public void setOperating_System(int operating_System) {
        Operating_System = operating_System;
    }

    public int getRuby_Programming() {
        return Ruby_Programming;
    }

    public void setRuby_Programming(int ruby_Programming) {
        Ruby_Programming = ruby_Programming;
    }

    public int getSoft_Skills() {
        return Soft_Skills;
    }

    public void setSoft_Skills(int soft_Skills) {
        Soft_Skills = soft_Skills;
    }

    public int getSoftware_Construction() {
        return Software_Construction;
    }

    public void setSoftware_Construction(int software_Construction) {
        Software_Construction = software_Construction;
    }
}
