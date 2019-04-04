package team.redrock.cheeringvote.service;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.exception.ValidException;
import team.redrock.cheeringvote.utils.HttpUtil;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


/**
 * @author 陌花采撷
 */
@Service
@Slf4j
@CacheConfig(cacheNames="Voter")
public class PollService {

    @Value("${getStuInfoUrl}")
    private String url;

    @Autowired
    private RedisTemplate<String,Integer> pollRedisTemplate;
    @Autowired
    CacheService cacheService;

    public Voter poll(String openid, String nickname, int target) throws ValidException, NoSuchProviderException, NoSuchAlgorithmException {

        long s = System.currentTimeMillis();

        if(openid.equals("")){
            throw new ValidException("Fail to get openid");
        }
         int poll = 0;
        String cheerStatus = null;

        int collage_id = 0;
        HttpUtil httpUtil = new HttpUtil();
        System.out.println("网络请求接口时间"+(System.currentTimeMillis()-s));
        s= System.currentTimeMillis();
        JSONObject data;
        synchronized (this){
            data = httpUtil.httpRequestToString(url+openid,"GET",null);
        }
        System.out.println("同步时间"+(System.currentTimeMillis()-s));
        s= System.currentTimeMillis();

            Voter voter = cacheService.getVoter(openid);
            if(voter==null){
                cacheService.insertVoter(openid,nickname,5,"");
                poll = 5;
                cheerStatus = String.valueOf(target);
                log.info("add a new user in function");
            }else{
                poll = voter.getPolls();
                if(voter.getTarget()==null||voter.getTarget().equals("")){
                    cheerStatus = String.valueOf(target);
                }else{
                    cheerStatus = voter.getTarget()+":"+String.valueOf(target);
                }
            }

        System.out.println(System.currentTimeMillis()-s);
            s = System.currentTimeMillis();
                  if(poll<0||poll>5){
                throw new ValidException("Index out of bound");
            }
            if(poll >0){
                String collage = null;
                collage = data.getString("collage");
                String head = collage.substring(0, 1);
                switch (head) {
                    case "通":
                        collage_id = 1;
                        break;
                    case "计":
                        collage_id = 2;
                        break;
                    case "自":
                        collage_id = 3;
                        break;
                    case "先":
                        collage_id = 4;
                        break;
                    case "光":
                        collage_id = 5;
                        break;
                    case "软":
                        collage_id = 6;
                        break;
                    case "生":
                        collage_id = 7;
                        break;
                    case "理":
                        collage_id = 8;
                        break;
                    case "经":
                        collage_id = 9;
                        break;
                    case "传":
                        collage_id = 10;
                        break;
                    case "外":
                        collage_id = 11;
                        break;
                    case "国":
                        if (head.equals("国际学院")) {
                            collage_id = 12;
                        } else {
                            collage_id = 5;
                        }
                        break;
                    case "信":
                        collage_id = 13;
                        break;
                    case "马":
                        collage_id = 14;
                        break;
                    case "体":
                        collage_id = 15;
                        break;
                    case "现":
                        collage_id = 9;
                        break;
                    case "网":
                        collage_id = 13;
                        break;
                    default:
                        log.error(collage);
                        collage_id = 0;
                }

                System.out.println("核心处理时间"+(System.currentTimeMillis()-s));
                s= System.currentTimeMillis();
                synchronized (this){
                if(collage_id==target){
                    pollRedisTemplate.opsForHash().increment("Cheerleaders",target,1);
                }else{
                    pollRedisTemplate.opsForHash().increment("Cheerleaders",target+100,1);
                }
                    cacheService.insertVoter(openid,nickname,poll-1,cheerStatus);
                }
                System.out.println("第二次同步时间"+(System.currentTimeMillis()-s));

        }else{


            return new Voter(nickname,-1,String.valueOf(target));
        }
        return new Voter(nickname,poll-1,String.valueOf(target));
    }

}
