package team.redrock.cheeringvote.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.redrock.cheeringvote.bean.ShowVoter;



/**
 * @author 陌花采撷
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowVoterResponse {
    private int status;
    private String info;
    private ShowVoter showVoter;
}
