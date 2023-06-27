package Helpers;

public class RecyclerHelper {
    String moduleName;
    int imageId;

    public RecyclerHelper(String moduleName, int imageId) {
        this.moduleName = moduleName;
        this.imageId = imageId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
