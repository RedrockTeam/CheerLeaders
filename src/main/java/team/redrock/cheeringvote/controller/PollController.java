package team.redrock.cheeringvote.controller;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.bean.WXUser;
import team.redrock.cheeringvote.exception.ValidException;

import team.redrock.cheeringvote.pojo.response.PollResponse;
import team.redrock.cheeringvote.service.PollService;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


/***
 * https://wx.idsbllp.cn/game/api/index.php?redirect=http://cds5rf.natappfree.cc/poll/1
 * https://wx.idsbllp.cn/game/api/index.php?redirect=http://cds5rf.natappfree.cc/test
 * @author 陌花采撷
 */

@RestController
@Slf4j
public class PollController {

    @Autowired
    PollService pollService;
    @GetMapping("/cheering_vote/poll/{target}")
    public PollResponse getPoll(WXUser wxUser, @PathVariable("target") int target) throws ValidException, NoSuchProviderException, NoSuchAlgorithmException {
        if(wxUser==null){
            log.error("ZLOG==>Fail to authorize");
            throw new ValidException("Fail to authorize");
        }
        String openid = wxUser.getOpenid();
        String nickname = wxUser.getNickname();
        if(nickname==null||nickname.equals("")){
            return new PollResponse(-1,"请先关注公众号",null);
        }

        Voter voter = pollService.poll(openid,nickname,target);
        PollResponse pollResponse;
        switch(voter.getPolls()){
            case -1 : pollResponse = new PollResponse(-1,"票数已用完",voter);
            break;
            default: pollResponse = new PollResponse(200,"success",voter);
        }
        return pollResponse;

}
    //本地测试方法

    @PostMapping("/cheering_vote/polltest/{target}/{nickname}")
    public PollResponse pollTest(String openid, @PathVariable("target") int target,@PathVariable("nickname") String nickname) throws ValidException, NoSuchProviderException, NoSuchAlgorithmException {


        Voter voter = null;
        int i;


        for(i=0; i<1000; i++){
            int finalI = i;
//            new Thread(()->{
//                try {
//                    pollService.poll(openid+ finalI +"dsfafasfasfasca","132",target);
//                } catch (ValidException e) {
//                    e.printStackTrace();
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                } catch (NoSuchProviderException e) {
//                    e.printStackTrace();
//                }
//            }).start();
        }
        voter = pollService.poll(openid,"\uD83D\uDE0B",target);


        PollResponse pollResponse;
        switch(voter.getPolls()){
            case -1 : pollResponse = new PollResponse(-1,"票数已用完",voter);
                break;
            default: pollResponse = new PollResponse(200,"success",voter);
        }
        return pollResponse;
}

}
