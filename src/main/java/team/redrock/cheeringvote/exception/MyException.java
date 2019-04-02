package team.redrock.cheeringvote.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陌花采撷
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyException extends RuntimeException {

    private int status;
    private String msg;
}
