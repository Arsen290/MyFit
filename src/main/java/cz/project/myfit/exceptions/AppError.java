package cz.project.myfit.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class AppError {
    private String message;
    private int status;
    private Date timestamp;

    public AppError(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Date();
    }
}
