package team.redrock.cheeringvote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team.redrock.cheeringvote.mapper.CheerleadersMapper;
import team.redrock.cheeringvote.mapper.CreateMapper;
import team.redrock.cheeringvote.pojo.response.InfoResponse;


import java.util.Map;

@RestController
public class DBController {

    @Autowired
    private RedisTemplate<String ,Integer> pollRedisTemplate;
    @Autowired
    private CheerleadersMapper cheerleadersMapper;
    @Autowired
    private CreateMapper createMapper;
    @PostMapping("/cheering_vote/init")
    public InfoResponse initDB(){

        Map<Object, Object> originMap =  pollRedisTemplate.opsForHash().entries("Cheerleaders");
        if(originMap==null||originMap.isEmpty()) {
            //本校投票
            for(int i=1;i<14;i++)
            {
                originMap.put(i,0);
            }
            //非本校投票
            for(int i=1;i<14;i++)
            {
                originMap.put(i+100,0);
            }
            pollRedisTemplate.opsForHash().putAll("Cheerleaders",originMap);

            createMapper.dropCheeringLeader();
            createMapper.dropUsers();
            createMapper.createCheeringLeader();
            createMapper.createUsers();
            cheerleadersMapper.insertCheerLeader("telecommunication",0,1);
            cheerleadersMapper.insertCheerLeader("computing",0,2);
            cheerleadersMapper.insertCheerLeader("automation",0,3);
            cheerleadersMapper.insertCheerLeader("advanced_manufacturing",0,4);
            cheerleadersMapper.insertCheerLeader("photoelectricity",0,5);
            cheerleadersMapper.insertCheerLeader("software",0,6);
            cheerleadersMapper.insertCheerLeader("bioinformatics",0,7);
            cheerleadersMapper.insertCheerLeader("science",0,8);
            cheerleadersMapper.insertCheerLeader("economic_management",0,9);
            cheerleadersMapper.insertCheerLeader("media_arts",0,10);
            cheerleadersMapper.insertCheerLeader("foreign_languages",0,11);
            cheerleadersMapper.insertCheerLeader("international",0,12);
            cheerleadersMapper.insertCheerLeader("cyberspace_security",0,13);
            cheerleadersMapper.insertCheerLeader("telecommunication",0,101);
            cheerleadersMapper.insertCheerLeader("computing",0,102);
            cheerleadersMapper.insertCheerLeader("automation",0,103);
            cheerleadersMapper.insertCheerLeader("advanced_manufacturing",0,104);
            cheerleadersMapper.insertCheerLeader("photoelectricity",0,105);
            cheerleadersMapper.insertCheerLeader("software",0,106);
            cheerleadersMapper.insertCheerLeader("bioinformatics",0,107);
            cheerleadersMapper.insertCheerLeader("science",0,108);
            cheerleadersMapper.insertCheerLeader("economic_management",0,109);
            cheerleadersMapper.insertCheerLeader("media_arts",0,110);
            cheerleadersMapper.insertCheerLeader("foreign_languages",0,111);
            cheerleadersMapper.insertCheerLeader("international",0,112);
            cheerleadersMapper.insertCheerLeader("cyberspace_security",0,113);

            return new InfoResponse(200,"success");
        }else{
            return new InfoResponse(200,"DB is exist");
        }


    }


    @PostMapping("/cheering_vote/delete")
    public InfoResponse delDB(String secret,String timestamp,String string){
        //加密

        Map<Object, Object> originMap =  pollRedisTemplate.opsForHash().entries("Cheerleaders");
        if(originMap!=null||!originMap.isEmpty()){
            for(int i =1;i<14;i++){
                cheerleadersMapper.updatePoll(0,i);
                cheerleadersMapper.updatePoll(0,i+100);
            }
            pollRedisTemplate.delete("Cheerleaders");
            createMapper.dropUsers();
            createMapper.dropCheeringLeader();
            return new InfoResponse(200,"success");
        }else{
            return new InfoResponse(200,"DB has been deleted");
        }

    }

}
