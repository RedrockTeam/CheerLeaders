package team.redrock.cheeringvote.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author 陌花采撷
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cheerleader {
    private int code;
    private String collage;
    private int polls;
}
