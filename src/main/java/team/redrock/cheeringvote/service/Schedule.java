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

    @Scheduled(cron = "0 0 2 * * *")
    public void dataPersist(){
        log.info("to data persistence");
        Map<Integer,Integer> map = new HashMap<>();
        Cursor<Map.Entry<Object, Object>> cursor = pollRedisTemplate.opsForHash().scan("Cheerleaders", ScanOptions.NONE);
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            switch((int)entry.getKey()){
                case 1: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),1);
                    break;
                case 2: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),2);
                    break;
                case 3: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),3);
                    break;
                case 4: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),4);
                    break;
                case 5: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),5);
                    break;
                case 6: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),6);
                    break;
                case 7: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),7);
                    break;
                case 8: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),8);
                    break;
                case 9: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),9);
                    break;
                case 10: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),10);
                    break;
                case 11: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),11);
                    break;
                case 12: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),12);
                    break;
                case 13: cheerleadersMapper.updatePoll( (Integer) entry.getValue(),13);
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
