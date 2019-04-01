package team.redrock.cheeringvote.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowVoter {
        private String nickname;
        private int polls;
        private List<Cheer_Status> cheer_status;
}

