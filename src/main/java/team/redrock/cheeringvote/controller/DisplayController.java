package team.redrock.cheeringvote.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import team.redrock.cheeringvote.bean.SecurityParam;
import team.redrock.cheeringvote.bean.WXUser;
import team.redrock.cheeringvote.exception.ValidException;
import team.redrock.cheeringvote.pojo.response.DisplayResponse;

import team.redrock.cheeringvote.pojo.response.PollResponse;
import team.redrock.cheeringvote.pojo.response.ShowVoterResponse;
import team.redrock.cheeringvote.service.DisplayService;
import team.redrock.cheeringvote.service.ShowUserService;
import team.redrock.cheeringvote.utils.SHAUtil;

@RestController
@Slf4j
public class DisplayController {
    @Autowired
    DisplayService displayService;
    @Autowired
    ShowUserService showUserService;
    @PostMapping("/cheering_vote/display")
    public DisplayResponse displayPolls(@RequestBody SecurityParam securityParam) throws ValidException {

        if(securityParam==null){
          throw new ValidException("Fail to authorize");
        }
        if(!SHAUtil.encode(securityParam.getString()+securityParam.getTimestamp()+securityParam.getSecret()).equals("e455586928569fe47a7917c4774c2eb78688e933")){
            throw new ValidException("Fail to authorize");
        }
        DisplayResponse displayResponse = displayService.Display();
        return  displayResponse;
    }

    @GetMapping("/cheering_vote/showUserPoll")
    public ShowVoterResponse disppalyUser(WXUser wxUser) throws ValidException {

        if(wxUser==null){
            log.error("ZLOG==>Fail to authorize");
            throw new ValidException("Fail to authorize");
        }
        String openid = wxUser.getOpenid();
        String nickname = wxUser.getNickname();


        if(nickname==null||nickname.equals("")){
            return new ShowVoterResponse(-1,"请先关注公众号",null);
        }

        ShowVoterResponse pollResponse = showUserService.showPoll(openid,nickname);
        return pollResponse;
    }


    @PostMapping("/cheering_vote/displaytest")
    public ShowVoterResponse pollTest(String openid, String nickname) throws ValidException {
        ShowVoterResponse pollResponse = showUserService.showPoll(openid,nickname);
        return pollResponse;
    }
}
