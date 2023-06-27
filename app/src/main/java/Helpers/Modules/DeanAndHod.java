package Helpers.Modules;

public class DeanAndHod {
    int deanimg,hodimg;

    String deanName, deanDesignation, deanCabin, deanEmail,hodName, hodDesignation,hodCabin,hodEmail;

    public DeanAndHod(){}
    public DeanAndHod(int deanimg, int hodimg, String deanName, String deanDesignation, String deanCabin, String deanEmail, String hodName, String hodDesignation, String hodCabin, String hodEmail) {
        this.deanimg = deanimg;
        this.hodimg = hodimg;
        this.deanName = deanName;
        this.deanDesignation = deanDesignation;
        this.deanCabin = deanCabin;
        this.deanEmail = deanEmail;
        this.hodName = hodName;
        this.hodDesignation = hodDesignation;
        this.hodCabin = hodCabin;
        this.hodEmail = hodEmail;
    }

    public int getDeanimg() {
        return deanimg;
    }

    public void setDeanimg(int deanimg) {
        this.deanimg = deanimg;
    }

    public int getHodimg() {
        return hodimg;
    }

    public void setHodimg(int hodimg) {
        this.hodimg = hodimg;
    }

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }

    public String getDeanDesignation() {
        return deanDesignation;
    }

    public void setDeanDesignation(String deanDesignation) {
        this.deanDesignation = deanDesignation;
    }

    public String getDeanCabin() {
        return deanCabin;
    }

    public void setDeanCabin(String deanCabin) {
        this.deanCabin = deanCabin;
    }

    public String getDeanEmail() {
        return deanEmail;
    }

    public void setDeanEmail(String deanEmail) {
        this.deanEmail = deanEmail;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public String getHodDesignation() {
        return hodDesignation;
    }

    public void setHodDesignation(String hodDesignation) {
        this.hodDesignation = hodDesignation;
    }

    public String getHodCabin() {
        return hodCabin;
    }

    public void setHodCabin(String hodCabin) {
        this.hodCabin = hodCabin;
    }

    public String getHodEmail() {
        return hodEmail;
    }

    public void setHodEmail(String hodEmail) {
        this.hodEmail = hodEmail;
    }
}
