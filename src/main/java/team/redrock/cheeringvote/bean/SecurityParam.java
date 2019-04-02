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
public class SecurityParam {
    private String string;
    private String timestamp;
    private String secret;
}
