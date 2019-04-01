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

@Slf4j
@Component
public class Schedule {

    @Autowired
    private RedisTemplate<String ,Integer> pollRedisTemplate;
//    @Resource
//    UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    @Autowired
    CacheService cacheService = SpringContextHolder.getBean(CacheService.class);
    @Resource
    CheerleadersMapper cheerleadersMapper = SpringContextHolder.getBean(CheerleadersMapper.class);
//    @Scheduled(cron = "0 0/5 23 * * *")
//    public void reflashPoll(){
//        System.out.println("定时任务启动");
//        Map<String,Integer> map = new HashMap<>();
//        Cursor<Map.Entry<Object, Object>> cursor = pollRedisTemplate.opsForHash().scan("Voter", ScanOptions.NONE);
//        while(cursor.hasNext()){
//            Map.Entry<Object, Object> entry = cursor.next();
//            map.put((String) entry.getKey(),5);
//            userMapper.insertUser((String)entry.getKey(),"",5);//这里有BUG
//        }
//        try {
//            cursor.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        pollRedisTemplate.opsForHash().putAll("Voter",map);
//    }

    @Scheduled(cron = "0 0/1 23 * * *")
    public void dataPersist(){
        System.out.println("这里进行数据持久化");
        Map<Integer,Integer> map = new HashMap<>();
        Cursor<Map.Entry<Object, Object>> cursor = pollRedisTemplate.opsForHash().scan("Cheerleaders", ScanOptions.NONE);
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
//            switch((int)entry.getKey()){
//                case 1: cheerleadersMapper.insertCheerLeader("telecommunication", (Integer) entry.getValue(),1);
//                    break;
//                case 2: cheerleadersMapper.insertCheerLeader("computing", (Integer) entry.getValue(),2);
//                    break;
//                case 3: cheerleadersMapper.insertCheerLeader("Automation", (Integer) entry.getValue(),3);
//                    break;
//                case 4: cheerleadersMapper.insertCheerLeader("advanced_manufacturing", (Integer) entry.getValue(),4);
//                    break;
//                case 5: cheerleadersMapper.insertCheerLeader("photoelectricity", (Integer) entry.getValue(),5);
//                    break;
//                case 6: cheerleadersMapper.insertCheerLeader("software ", (Integer) entry.getValue(),6);
//                    break;
//                case 7: cheerleadersMapper.insertCheerLeader("bioinformatics", (Integer) entry.getValue(),7);
//                    break;
//                case 8: cheerleadersMapper.insertCheerLeader("science", (Integer) entry.getValue(),8);
//                    break;
//                case 9: cheerleadersMapper.insertCheerLeader("economic_management", (Integer) entry.getValue(),9);
//                    break;
//                case 10: cheerleadersMapper.insertCheerLeader("media_arts", (Integer) entry.getValue(),10);
//                    break;
//                case 11: cheerleadersMapper.insertCheerLeader("foreign_languages", (Integer) entry.getValue(),11);
//                    break;
//                case 12: cheerleadersMapper.insertCheerLeader("international ", (Integer) entry.getValue(),12);
//                    break;
//                case 13: cheerleadersMapper.insertCheerLeader("Cyberspace_security", (Integer) entry.getValue(),13);
//                    break;
//                default: log.error("ZLOG==>get wrong switch param:"+entry.getKey());
//                    break;
//            }
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


    @CachePut(key = "#id")
    public Cheerleader getCheerLeaderByDB(int id){
        Cheerleader cheerleader = cheerleadersMapper.findById(id);
        return cheerleader;
    }


}
