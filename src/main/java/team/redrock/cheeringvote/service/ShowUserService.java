package team.redrock.cheeringvote.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Cheer_Status;
import team.redrock.cheeringvote.bean.ShowVoter;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.exception.ValidException;

import team.redrock.cheeringvote.pojo.response.PollResponse;
import team.redrock.cheeringvote.pojo.response.ShowVoterResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShowUserService {
    @Autowired
    CacheService cacheService;
    @Autowired
    private RedisTemplate<String,Integer> pollRedisTemplate;
    public ShowVoterResponse showPoll(String openid, String nickname) throws ValidException {
        if(openid.equals("")){
            throw new ValidException("Fail to get openid");
        }
//        if(pollRedisTemplate.opsForHash().get("Voter",openid)==null){
//            pollRedisTemplate.opsForHash().put("Voter",openid,5);
//            log.info("add a new user");
//        }
        int poll = 0;
        String cheerStatus = null;
        Voter voter = cacheService.getVoter(openid);
        if(voter==null){
            cacheService.insertVoter(openid,nickname,5,"");
            poll = 5;
        }else{
            poll = voter.getPolls();
            cheerStatus = voter.getTarget();
        }


        List<Cheer_Status> cheerStatusList = new ArrayList<>();
        for (int i = 1;i<14;i++) {
            Cheer_Status cheer_status = new Cheer_Status(i,0);
            cheerStatusList.add(cheer_status);
        }
        if(cheerStatus!=null&&!cheerStatus.equals("")){

            String[] statuses = cheerStatus.split(":");
            for (String s : statuses) {
                switch (s) {
                    case "1":
                        cheerStatusList.get(0).setStatus(1);
                        break;
                    case "2":
                        cheerStatusList.get(1).setStatus(1);
                        break;
                    case "3":
                        cheerStatusList.get(2).setStatus(1);
                        break;
                    case "4":
                        cheerStatusList.get(3).setStatus(1);
                        break;
                    case "5":
                        cheerStatusList.get(4).setStatus(1);
                        break;
                    case "6":
                        cheerStatusList.get(5).setStatus(1);
                        break;
                    case "7":
                        cheerStatusList.get(6).setStatus(1);
                        break;
                    case "8":
                        cheerStatusList.get(7).setStatus(1);
                        break;
                    case "9":
                        cheerStatusList.get(8).setStatus(1);
                        break;
                    case "10":
                        cheerStatusList.get(9).setStatus(1);
                        break;
                    case "11":
                        cheerStatusList.get(10).setStatus(1);
                        break;
                    case "12":
                        cheerStatusList.get(11).setStatus(1);
                        break;
                    case "13":
                        cheerStatusList.get(12).setStatus(1);
                        break;
                    default:
                        break;

                }

            }
        }
//        int poll = (int) pollRedisTemplate.opsForHash().get("Voter",openid);


        return new ShowVoterResponse(200,"success",new ShowVoter(nickname,poll,cheerStatusList));
    }

}
