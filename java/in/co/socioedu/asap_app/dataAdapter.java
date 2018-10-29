package in.co.socioedu.asap_app;


public class dataAdapter {
    private String name;
    private String contact;
    private String regno;
    private String emailId;


    public dataAdapter(String name, String contact, String regno, String emailId) {
        this.name = name;
        this.contact = contact;
        this.regno = regno;
        this.emailId = emailId;

    }
    public dataAdapter(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


}
