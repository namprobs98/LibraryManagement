package Java.LibraryManagement.models;

public class Borrow {
    private String borrow_id;
    private String member_id;
    private String book_id;
    private String borrow_date;
    private String return_date;

    public Borrow(String borrow_id, String member_id, String book_id, String borrow_date, String return_date) {
        this.borrow_id = borrow_id;
        this.member_id = member_id;
        this.book_id = book_id;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }

    public String getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(String borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}
