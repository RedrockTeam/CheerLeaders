package team.redrock.cheeringvote.service;

import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.exception.ValidException;


/**
 * @author 陌花采撷
 */
@Service
@Slf4j
@CacheConfig(cacheNames="Voter")
public class PollService {

    @Autowired
    private RedisTemplate<String,Integer> pollRedisTemplate;
    @Autowired
    CacheService cacheService;

    public Voter poll(String openid, String nickname, int target) throws ValidException {

        if(openid.equals("")){
            throw new ValidException("Fail to get openid");
        }
    int poll = 0;
        String cheerStatus = null;

            Voter voter = cacheService.getVoter(openid);

            if(voter==null){
                cacheService.insertVoter(openid,nickname,5,"");

                poll = 5;
                log.info("add a new user in function");
            }else{

                poll = voter.getPolls();
                if(voter.getTarget()==null||voter.getTarget().equals("")){
                    cheerStatus = String.valueOf(target);
                }else{
                    cheerStatus = voter.getTarget()+":"+String.valueOf(target);
                }
            }

                  if(poll<0||poll>5){
                throw new ValidException("Index out of bound");
            }
            if(poll >0){
                pollRedisTemplate.opsForHash().increment("Cheerleaders",target,1);

                cacheService.insertVoter(openid,nickname,poll-1,cheerStatus);
        }else{


            return new Voter(nickname,-1,String.valueOf(target));
        }
        return new Voter(nickname,poll-1,String.valueOf(target));
    }

}
