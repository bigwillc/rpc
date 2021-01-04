package model;

/**
 * @author bigwillc
 */
public class Error {

    private Integer status;
    private String message;

    public Error(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
