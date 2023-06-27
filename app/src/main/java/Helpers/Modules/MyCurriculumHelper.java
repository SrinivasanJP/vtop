package Helpers.Modules;

public class MyCurriculumHelper {
    private String pc,pe,uc,ue,bc,ncc,tc;
    public MyCurriculumHelper(){}

    public MyCurriculumHelper(String pc, String pe, String uc, String ue, String bc, String ncc, String tc) {
        this.pc = pc;
        this.pe = pe;
        this.uc = uc;
        this.ue = ue;
        this.bc = bc;
        this.ncc = ncc;
        this.tc = tc;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getUe() {
        return ue;
    }

    public void setUe(String ue) {
        this.ue = ue;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getNcc() {
        return ncc;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }
}
