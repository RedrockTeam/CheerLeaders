package team.redrock.cheeringvote.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import team.redrock.cheeringvote.bean.Voter;

/**
 * @author 陌花采撷
 */
@Mapper
@Repository
@Transactional
public interface UserMapper {

    @Insert("Insert into users (openid,nickname,polls,pollstatus) value(#{openid},#{nickname},#{polls},#{pollstatus}) ON DUPLICATE KEY UPDATE openid=#{openid},polls=#{polls},pollstatus = #{pollstatus}")
    int insertUser(@Param("openid") String openid, @Param("nickname") String nickname,@Param("polls") int poll,@Param("pollstatus") String pollStatus );

    @Update("Update users set polls  = #{polls} where openid = #{openid}")
    int updatePoll(@Param("openid") String openid,@Param("polls") int polls);

    @Select("select nickname,polls,pollstatus from users where openid = #{openid} ")
    Voter findByOpenid(@Param("openid") String openid);

    @Update("Update users set polls = 5, pollstatus = ''")
    void refreshPoll();

}
