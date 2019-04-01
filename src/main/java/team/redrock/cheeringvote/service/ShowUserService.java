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

        String[] statuses = cheerStatus.split(":");
        List<Cheer_Status> cheerStatusList = new ArrayList<>();
        for (int i = 1;i<14;i++) {
            Cheer_Status cheer_status = new Cheer_Status(i,0);
            cheerStatusList.add(cheer_status);
        }
        for (String s: statuses) {
            Cheer_Status cheer_status = null;
            switch (s){
                case "1" :  cheer_status = new Cheer_Status(1,0);
                     break;
                case "2" : cheer_status = new Cheer_Status(2,0);
                    break;
                case "3" : cheer_status = new Cheer_Status(3,0);
                    break;
                case "4" : cheer_status = new Cheer_Status(4,0);
                    break;
                case "5" : cheer_status = new Cheer_Status(5,0);
                    break;
                case "6" : cheer_status = new Cheer_Status(6,0);
                    break;
                case "7" : cheer_status = new Cheer_Status(7,0);
                    break;
                case "8" : cheer_status = new Cheer_Status(8,0);
                    break;
                case "9" : cheer_status = new Cheer_Status(9,0);
                    break;
                case "10" : cheer_status = new Cheer_Status(10,0);
                    break;
                case "11" : cheer_status = new Cheer_Status(11,0);
                    break;
                case "12" : cheer_status = new Cheer_Status(12,0);
                    break;
                case "13" : cheer_status = new Cheer_Status(13,0);
                    break;
                default:
                    log.error("创建状态失败");
                    throw new ValidException("Index out of bound");
            }
            if(Integer.parseInt(s)==cheer_status.getCode()){
                cheer_status.setStatus(1);
            }
            cheerStatusList.add(cheer_status);
        }
//        int poll = (int) pollRedisTemplate.opsForHash().get("Voter",openid);


        return new ShowVoterResponse(200,"success",new ShowVoter(nickname,poll,cheerStatusList));
    }

}
