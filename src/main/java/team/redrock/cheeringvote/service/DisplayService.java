package team.redrock.cheeringvote.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Cheerleader;
import team.redrock.cheeringvote.exception.ValidException;
import team.redrock.cheeringvote.pojo.response.DisplayResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陌花采撷
 */
@Service
@Slf4j
public class DisplayService {

    @Autowired
    private RedisTemplate<String ,Integer> pollRedisTemplate;

    public DisplayResponse Display() throws ValidException {

        Cheerleader cheerleader = null;
        List<Cheerleader> cheerleaderList = new ArrayList<>();
        int sum = 0;

        Map<Object, Object> cheerleadersMap = new HashMap<>();
        cheerleadersMap = pollRedisTemplate.opsForHash().entries("Cheerleaders");
        if(cheerleadersMap.size()!=26){
            throw new ValidException("Fail to get total cheerleaders ");
    }

        for (Map.Entry<Object,Object> i: cheerleadersMap.entrySet()) {

            switch((int)i.getKey()){
                case 1:
                    cheerleader = new Cheerleader(1,"telecommunication", (Integer) i.getValue(), (Integer) cheerleadersMap.get(101));
                    break;
                case 2: cheerleader = new Cheerleader(2,"computing", (Integer) i.getValue(),(Integer) cheerleadersMap.get(102));
                    break;
                case 3: cheerleader = new Cheerleader(3,"automation", (Integer) i.getValue(),(Integer) cheerleadersMap.get(103));
                    break;
                case 4: cheerleader = new Cheerleader(4,"advanced_manufacturing", (Integer) i.getValue(),(Integer) cheerleadersMap.get(104));
                    break;
                case 5: cheerleader = new Cheerleader(5,"photoelectricity", (Integer) i.getValue(),(Integer) cheerleadersMap.get(105));
                    break;
                case 6: cheerleader = new Cheerleader(6,"software", (Integer) i.getValue(),(Integer) cheerleadersMap.get(106));
                    break;
                case 7: cheerleader = new Cheerleader(7,"bioinformatics", (Integer) i.getValue(),(Integer) cheerleadersMap.get(107));
                    break;
                case 8: cheerleader = new Cheerleader(8,"science", (Integer) i.getValue(),(Integer) cheerleadersMap.get(108));
                    break;
                case 9: cheerleader = new Cheerleader(9,"economic_management", (Integer) i.getValue(),(Integer) cheerleadersMap.get(109));
                    break;
                case 10: cheerleader = new Cheerleader(10,"media_arts", (Integer) i.getValue(),(Integer) cheerleadersMap.get(110));
                    break;
                case 11: cheerleader = new Cheerleader(11,"foreign_languages", (Integer) i.getValue(),(Integer) cheerleadersMap.get(111));
                    break;
                case 12: cheerleader = new Cheerleader(12,"international", (Integer) i.getValue(),(Integer) cheerleadersMap.get(112));
                    break;
                case 13: cheerleader = new Cheerleader(13,"cyberspace_security", (Integer) i.getValue(),(Integer) cheerleadersMap.get(113));
                    break;

                default:
                    break;
            }

            cheerleaderList.add(cheerleader);
            sum += (Integer) i.getValue();
        }

        return new DisplayResponse(200,"Success",sum,cheerleaderList);

    }


}

