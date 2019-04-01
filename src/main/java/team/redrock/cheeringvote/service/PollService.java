package team.redrock.cheeringvote.service;

import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.exception.ValidException;

import team.redrock.cheeringvote.mapper.UserMapper;
import team.redrock.cheeringvote.pojo.response.PollResponse;

import java.util.concurrent.TimeUnit;

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

//        if(pollRedisTemplate.opsForHash().get("Voter",openid)==null){
////            pollRedisTemplate.opsForHash().put("Voter",openid,5);
//            Voter voter = getVoter(openid);
//            if(voter==null){
//                insertVoter(openid,nickname,5);
////                //pollRedisTemplate.opsForHash().put("Voter",openid,5);
//                poll = 5;
//                log.info("add a new user in function");
//            }else{
//             poll = voter.getPolls();
////                pollRedisTemplate.opsForHash().put("Voter",openid,poll);
////                pollRedisTemplate.expire("Voter",30, TimeUnit.SECONDS);
//            }
////            poll = (int) pollRedisTemplate.opsForHash().get("Voter",openid);
//        }


            Voter voter = cacheService.getVoter(openid);

            if(voter==null){
                cacheService.insertVoter(openid,nickname,5,"");

                poll = 5;
                log.info("add a new user in function");
            }else{

                poll = voter.getPolls();
                cheerStatus = voter.getTarget()+":"+String.valueOf(target);
            }


                  if(poll<0||poll>5){
                throw new ValidException("Index out of bound");
            }
            if(poll >0){
                pollRedisTemplate.opsForHash().increment("Cheerleaders",target,1);
//                pollRedisTemplate.opsForHash().put("Voter",openid,poll-1);
                cacheService.insertVoter(openid,nickname,poll-1,cheerStatus);
        }else{


            return new Voter(nickname,-1,String.valueOf(target));
        }
        return new Voter(nickname,poll-1,String.valueOf(target));
    }


////    @Cacheable(value = "Voter",key = "#openid")
//    public Voter getVoter(String openid){
//        Voter voter = userMapper.findByOpenid(openid);
//        System.out.println("调用数据库");
//        return voter;
//    }
//
////    @CachePut(value = "Voter",key = "#openid")
//    public Voter insertVoter(String openid,String nickname,int polls) throws ValidException {
//        int status = userMapper.insertUser(openid,nickname,polls);
//        if (status ==1 ||status ==2) {
//            System.out.println("插入数据库");
//            return new Voter(nickname,polls);
//        }
//        throw new ValidException("插入数据失败");
//
//    }
}
