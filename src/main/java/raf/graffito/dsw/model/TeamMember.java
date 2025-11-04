package raf.graffito.dsw.model;

public class TeamMember {
    private String name;
    private String index;
    private String imagePath;

    public TeamMember(String name, String index, String imagePath) {
        this.name = name;
        this.index = index;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
