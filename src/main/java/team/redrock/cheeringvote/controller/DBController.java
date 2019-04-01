package team.redrock.cheeringvote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team.redrock.cheeringvote.pojo.response.InfoResponse;


import java.util.Map;

@RestController
public class DBController {

    @Autowired
    private RedisTemplate<String ,Integer> pollRedisTemplate;
    @PostMapping("/cheering_vote/init")
    public InfoResponse initDB(String secret,String timestamp,String string){
        //加密逻辑
        Map<Object, Object> originMap =  pollRedisTemplate.opsForHash().entries("Cheerleaders");
        if(originMap==null||originMap.isEmpty()) {
            for(int i=1;i<14;i++)
            {
                originMap.put(i,0);
            }
            pollRedisTemplate.opsForHash().putAll("Cheerleaders",originMap);
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
            pollRedisTemplate.delete("Cheerleaders");

            return new InfoResponse(200,"success");
        }else{
            return new InfoResponse(200,"DB has been deleted");
        }

    }

}
