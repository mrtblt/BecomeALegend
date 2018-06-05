package projects.etrkk5.employeer.Profiles;


/**
 * Created by EsrefTurkok on 28.04.2018.
 */

public class Ad {
    private String title;
    private String location;
    private String docsRef;

    public Ad(String docsRef, String title, String location){
        this.docsRef = docsRef;
        this.title = title;
        this.location = location;
    }

    public String getDocsRef() {
        return docsRef;
    }

    public void setDocsRef(String docsRef) {
        this.docsRef = docsRef;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
