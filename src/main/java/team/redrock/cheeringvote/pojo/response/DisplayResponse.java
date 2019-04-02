package team.redrock.cheeringvote.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.redrock.cheeringvote.bean.Cheerleader;

import java.util.List;


/**
 * @author 陌花采撷
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayResponse {

    private int status;
    private String info;
    private int sum;
    private List<Cheerleader> cheerleaders;
}
