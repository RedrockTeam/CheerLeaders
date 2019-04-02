package team.redrock.cheeringvote.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.redrock.cheeringvote.bean.Voter;

import java.util.Map;

/**
 * @author 陌花采撷
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PollResponse {
    private int status;
    private String info;
   private Voter voter;
}
