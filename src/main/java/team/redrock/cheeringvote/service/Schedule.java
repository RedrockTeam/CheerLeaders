package team.redrock.cheeringvote.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.redrock.cheeringvote.bean.Cheerleader;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.config.SpringContextHolder;
import team.redrock.cheeringvote.mapper.CheerleadersMapper;
import team.redrock.cheeringvote.mapper.UserMapper;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 陌花采撷
 */
@Slf4j
@Component
public class Schedule {

    @Autowired
    private RedisTemplate<String ,Integer> pollRedisTemplate;

    @Autowired
    CacheService cacheService = SpringContextHolder.getBean(CacheService.class);
    @Resource
    CheerleadersMapper cheerleadersMapper = SpringContextHolder.getBean(CheerleadersMapper.class);
    @Scheduled(cron = "0 0 0 * * *")
    public void reflashPoll(){
      log.info("start refresh voters");
        cacheService.refreshVoter();

    }

    @Scheduled(cron = "0 0/1 10 * * *")
    public void dataPersist(){
        log.info("to data persistence");

        Cursor<Map.Entry<Object, Object>> cursor = pollRedisTemplate.opsForHash().scan("Cheerleaders", ScanOptions.NONE);
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            switch((int)entry.getKey()){
                case 1: cheerleadersMapper.insertCheerLeader( "telecommunication",(Integer) entry.getValue(),1);
                    break;
                case 2: cheerleadersMapper.insertCheerLeader( "computing",(Integer) entry.getValue(),2);
                    break;
                case 3: cheerleadersMapper.insertCheerLeader("automation", (Integer) entry.getValue(),3);
                    break;
                case 4: cheerleadersMapper.insertCheerLeader("advanced_manufacturing", (Integer) entry.getValue(),4);
                    break;
                case 5: cheerleadersMapper.insertCheerLeader( "photoelectricity",(Integer) entry.getValue(),5);
                    break;
                case 6: cheerleadersMapper.insertCheerLeader( "software",(Integer) entry.getValue(),6);
                    break;
                case 7: cheerleadersMapper.insertCheerLeader("bioinformatics", (Integer) entry.getValue(),7);
                    break;
                case 8: cheerleadersMapper.insertCheerLeader( "science",(Integer) entry.getValue(),8);
                    break;
                case 9: cheerleadersMapper.insertCheerLeader( "economic_management",(Integer) entry.getValue(),9);
                    break;
                case 10: cheerleadersMapper.insertCheerLeader( "media_arts",(Integer) entry.getValue(),10);
                    break;
                case 11: cheerleadersMapper.insertCheerLeader( "foreign_languages",(Integer) entry.getValue(),11);
                    break;
                case 12: cheerleadersMapper.insertCheerLeader( "international",(Integer) entry.getValue(),12);
                    break;
                case 13: cheerleadersMapper.insertCheerLeader( "cyberspace_security",(Integer) entry.getValue(),13);
                    break;
                case 101: cheerleadersMapper.insertCheerLeader("telecommunication", (Integer) entry.getValue(),101);
                    break;
                case 102: cheerleadersMapper.insertCheerLeader("computing", (Integer) entry.getValue(),102);
                    break;
                case 103: cheerleadersMapper.insertCheerLeader( "automation",(Integer) entry.getValue(),103);
                    break;
                case 104: cheerleadersMapper.insertCheerLeader("advanced_manufacturing", (Integer) entry.getValue(),104);
                    break;
                case 105: cheerleadersMapper.insertCheerLeader( "photoelectricity",(Integer) entry.getValue(),105);
                    break;
                case 106: cheerleadersMapper.insertCheerLeader( "software",(Integer) entry.getValue(),106);
                    break;
                case 107: cheerleadersMapper.insertCheerLeader("bioinformatics", (Integer) entry.getValue(),107);
                    break;
                case 108: cheerleadersMapper.insertCheerLeader( "science",(Integer) entry.getValue(),108);
                    break;
                case 109: cheerleadersMapper.insertCheerLeader( "economic_management",(Integer) entry.getValue(),109);
                    break;
                case 110: cheerleadersMapper.insertCheerLeader( "media_arts",(Integer) entry.getValue(),110);
                    break;
                case 111: cheerleadersMapper.insertCheerLeader( "foreign_languages",(Integer) entry.getValue(),111);
                    break;
                case 112: cheerleadersMapper.insertCheerLeader( "international",(Integer) entry.getValue(),112);
                    break;
                case 113: cheerleadersMapper.insertCheerLeader("cyberspace_security", (Integer) entry.getValue(),113);
                    break;
                default: log.error("ZLOG==>get wrong switch param:"+entry.getKey());
                    break;
            }
        }
        try {
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
