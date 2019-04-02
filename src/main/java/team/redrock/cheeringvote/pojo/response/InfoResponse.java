package team.redrock.cheeringvote.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author 陌花采撷
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoResponse {
    private int status;
    private String info;
}
