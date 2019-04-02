package team.redrock.cheeringvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import team.redrock.cheeringvote.bean.Voter;
import team.redrock.cheeringvote.exception.ValidException;
import team.redrock.cheeringvote.mapper.UserMapper;

/**
 * @author 陌花采撷
 */
@Service
public class CacheService {

    @Autowired
    private UserMapper userMapper;

        @Cacheable(value = "Voter:",key = "#openid")
    public Voter getVoter(String openid){
        return userMapper.findByOpenid(openid);
    }

        @CachePut(value = "Voter:",key = "#openid")
    public Voter insertVoter(String openid,String nickname,int polls,String cheerStatus) throws ValidException {
        int status = userMapper.insertUser(openid,nickname,polls,cheerStatus);
        if (status ==1 ||status ==2) {
            return new Voter(nickname,polls,cheerStatus);
        }
        throw new ValidException("fail to get total users");
    }

    @CacheEvict(value = "Voter:",allEntries = true)
    public void refreshVoter(){
            userMapper.refreshPoll();
    }

}
