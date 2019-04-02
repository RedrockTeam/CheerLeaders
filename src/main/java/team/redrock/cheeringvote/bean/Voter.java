package team.redrock.cheeringvote.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陌花采撷
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voter {
    private String nickname;
    private int polls;
    private String target;
}
