package team.redrock.cheeringvote.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.exception.ValidException;

import team.redrock.cheeringvote.pojo.response.PollResponse;

/**
 * @author 陌花采撷
 */
@Service
@Slf4j
public class AddPollService {

    @Autowired
    CacheService cacheService;

    public PollResponse addAPoll(String openid, String nickname) throws ValidException {
        if(openid.equals("")){
            throw new ValidException("Fail to get openid");
        }

        int poll = 0;
        Voter voter = cacheService.getVoter(openid);
        if(voter==null){
            cacheService.insertVoter(openid,nickname,5,null);
        }else{
            poll = voter.getPolls();
        }


        if(poll<0||poll>5){
            throw new ValidException("Index out of bound");
        }
        if(poll<5){

            cacheService.insertVoter(openid,nickname,poll+1,"");
        }

        voter = cacheService.getVoter(openid);
        poll = voter.getPolls();

        return new PollResponse(200,"success",new Voter(nickname,poll,""));




    }
}
