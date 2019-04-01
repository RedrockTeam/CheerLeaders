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

@Service
@Slf4j
public class DisplayService {
    //1 通信
    //2 计算机
    //3 自动化
    //4 光电
    //5 经管
    //6 外国语
    //7 生物
    //8 传媒
    //9 信安
    //10 体育
    //11 理学院
    //12 软件
    //13 先进
    //14 国际
    //15 现代邮政学院
    //16 国际半导体学院
    @Autowired
    private RedisTemplate<String ,Integer> pollRedisTemplate;

    public DisplayResponse Display() throws ValidException {

        Cheerleader cheerleader = null;
        List<Cheerleader> cheerleaderList = new ArrayList<>();
        int sum = 0;

        Map<Object, Object> cheerleadersMap = new HashMap<>();
        cheerleadersMap = pollRedisTemplate.opsForHash().entries("Cheerleaders");
        if(cheerleadersMap.size()!=13){
            throw new ValidException("Fail to get total cheerleaders ");
        }
        for (Map.Entry<Object,Object> i: cheerleadersMap.entrySet() ) {
            switch((int)i.getKey()){
                case 1:  cheerleader = new Cheerleader(1,"telecommunication", (Integer) i.getValue());
                    break;
                case 2: cheerleader = new Cheerleader(2,"computing", (Integer) i.getValue());
                    break;
                case 3: cheerleader = new Cheerleader(3,"Automation", (Integer) i.getValue());
                    break;
                case 4: cheerleader = new Cheerleader(4,"advanced_manufacturing", (Integer) i.getValue());
                    break;
                case 5: cheerleader = new Cheerleader(5,"photoelectricity", (Integer) i.getValue());
                    break;
                case 6: cheerleader = new Cheerleader(6,"software", (Integer) i.getValue());
                    break;
                case 7: cheerleader = new Cheerleader(7,"bioinformatics", (Integer) i.getValue());
                    break;
                case 8: cheerleader = new Cheerleader(8,"science", (Integer) i.getValue());
                    break;
                case 9: cheerleader = new Cheerleader(9,"economic_management", (Integer) i.getValue());
                    break;
                case 10: cheerleader = new Cheerleader(10,"media_arts", (Integer) i.getValue());
                    break;
                case 11: cheerleader = new Cheerleader(11,"foreign_languages", (Integer) i.getValue());
                    break;
                case 12: cheerleader = new Cheerleader(12,"international", (Integer) i.getValue());
                    break;
                case 13: cheerleader = new Cheerleader(13,"Cyberspace_security", (Integer) i.getValue());
                    break;
                default:  log.error("ZLOG==>get wrong switch param !");
                    break;
            }
            cheerleaderList.add(cheerleader);
            sum += (Integer) i.getValue();
        }

        return new DisplayResponse(200,"Success",sum,cheerleaderList);

    }


}

