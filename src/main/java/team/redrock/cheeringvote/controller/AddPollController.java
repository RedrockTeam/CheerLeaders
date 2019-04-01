package team.redrock.cheeringvote.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team.redrock.cheeringvote.bean.WXUser;
import team.redrock.cheeringvote.exception.ValidException;

import team.redrock.cheeringvote.pojo.response.PollResponse;
import team.redrock.cheeringvote.service.AddPollService;

@RestController
@Slf4j
public class AddPollController {

    @Autowired
    AddPollService addPollService;
    @GetMapping("/cheering_vote/addpoll")
    public PollResponse addPoll(WXUser wxUser) throws ValidException {
        if(wxUser==null){
            log.error("ZLOG==>Fail to authorize");
            throw new ValidException("Fail to authorize");
        }
        String openid = wxUser.getOpenid();
        String nickname = wxUser.getNickname();
        if(nickname==null||nickname.equals("")){
            return new PollResponse(-1,"请先关注公众号",null);
        }
        PollResponse pollResponse = addPollService.addAPoll(openid,nickname);
        return pollResponse;

    }
    @PostMapping("/cheering_vote/addtest")
    public PollResponse addPolltest(String openid) throws ValidException {

        PollResponse pollResponse = addPollService.addAPoll(openid,"");
        return pollResponse;

    }

}
