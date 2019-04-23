package info.jef.pduploader;

/**
 * Created by Belal on 10/18/2017.
 */

public class Product {
    //private int id;
    //id,code,from1,to1,subject,message
    private String id;
    private String code;
    private String from1;
    private String to1;
    private String subject;
    private String message;

    public Product(String id, String code, String from1, String to1, String subject, String message) {
        //this.id = id;
        this.id = id;
        this.code = code;
        this.from1 = from1;
        this.to1 = to1;
        this.subject = subject;
        this.message=message;
    }



    public String getid() {
        return id;
    }

    public String getcode() {
        return code;
    }

    public String getfrom1() {
        return from1;
    }

    public String getto1() {return to1; }

    public String getsubject() { return subject; }
    public String getmessage() {return message; }
}
