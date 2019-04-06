package team.redrock.cheeringvote.service;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Cheer_Status;
import team.redrock.cheeringvote.bean.ShowVoter;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.exception.ValidException;
import team.redrock.cheeringvote.pojo.response.ShowVoterResponse;
import team.redrock.cheeringvote.utils.HttpUtil;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 陌花采撷
 */


@Service
@Slf4j
public class ShowUserService {
    @Autowired
    CacheService cacheService;
    @Value("${getStuInfoUrl}")
    private String url;

    public ShowVoterResponse showPoll(String openid, String nickname) throws ValidException, NoSuchProviderException, NoSuchAlgorithmException {
        if(openid.equals("")){
            throw new ValidException("Fail to get openid");
        }

        int poll = 0;
        String cheerStatus = null;
        Voter voter = cacheService.getVoter(openid);
        if(voter==null){
            cacheService.insertVoter(openid,nickname,5,"");
            poll = 5;
        }else{
            poll = voter.getPolls();
            cheerStatus = voter.getTarget();
        }


        List<Cheer_Status> cheerStatusList = new ArrayList<>();
        for (int i = 1;i<14;i++) {
            Cheer_Status cheer_status = new Cheer_Status(i,0);
            cheerStatusList.add(cheer_status);
        }
        if(cheerStatus!=null&&!cheerStatus.equals("")){

            String[] statuses = cheerStatus.split(":");
            for (String s : statuses) {
                switch (s) {
                    case "1":
                        cheerStatusList.get(0).setStatus(1);
                        break;
                    case "2":
                        cheerStatusList.get(1).setStatus(1);
                        break;
                    case "3":
                        cheerStatusList.get(2).setStatus(1);
                        break;
                    case "4":
                        cheerStatusList.get(3).setStatus(1);
                        break;
                    case "5":
                        cheerStatusList.get(4).setStatus(1);
                        break;
                    case "6":
                        cheerStatusList.get(5).setStatus(1);
                        break;
                    case "7":
                        cheerStatusList.get(6).setStatus(1);
                        break;
                    case "8":
                        cheerStatusList.get(7).setStatus(1);
                        break;
                    case "9":
                        cheerStatusList.get(8).setStatus(1);
                        break;
                    case "10":
                        cheerStatusList.get(9).setStatus(1);
                        break;
                    case "11":
                        cheerStatusList.get(10).setStatus(1);
                        break;
                    case "12":
                        cheerStatusList.get(11).setStatus(1);
                        break;
                    case "13":
                        cheerStatusList.get(12).setStatus(1);
                        break;
                    default:
                        break;

                }

            }
        }
        HttpUtil httpUtil = new HttpUtil();
        String collage = null;
        int collage_id = 0;
        JSONObject jsonObject = httpUtil.httpRequestToString(url+openid,"GET",null);

            collage = jsonObject.getString("collage");

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

        return new ShowVoterResponse(200,"success",new ShowVoter(nickname,collage_id,collage,poll,cheerStatusList));
    }


}
