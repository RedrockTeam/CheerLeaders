package team.redrock.cheeringvote.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.exception.ValidException;

import team.redrock.cheeringvote.pojo.response.PollResponse;

@Service
@Slf4j
public class AddPollService {

    @Autowired
    CacheService cacheService;
    @Autowired
    private RedisTemplate<String,Integer> pollRedisTemplate;

    public PollResponse addAPoll(String openid, String nickname) throws ValidException {
        if(openid.equals("")){
            throw new ValidException("Fail to get openid");
        }
//        if( pollRedisTemplate.opsForHash().get("Voter",openid)==null){
//            pollRedisTemplate.opsForHash().put("Voter",openid,5);
//            log.info("add a new user in function");
//        }
        int poll = 0;
        Voter voter = cacheService.getVoter(openid);
        if(voter==null){
            cacheService.insertVoter(openid,nickname,5,null);
        }else{
            poll = voter.getPolls();
        }
//        int poll = (int) pollRedisTemplate.opsForHash().get("Voter",openid);

        if(poll<0||poll>5){
            throw new ValidException("Index out of bound");
        }
        if(poll<5){
//            pollRedisTemplate.opsForHash().increment("Voter",openid,1);
            cacheService.insertVoter(openid,nickname,poll+1,"");
        }
//        poll = (int) pollRedisTemplate.opsForHash().get("Voter",openid);
        voter = cacheService.getVoter(openid);
        poll = voter.getPolls();

        return new PollResponse(200,"success",new Voter(nickname,poll,""));




    }
}
