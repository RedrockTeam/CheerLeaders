package team.redrock.cheeringvote.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.redrock.cheeringvote.bean.Cheerleader;

import java.util.List;
import java.util.Map;

/**
 * @author 陌花采撷
 */
@Mapper
@Repository
@Transactional
public interface CheerleadersMapper {

    @Insert("Insert into cheerleader (collage,polls,id) value(#{collage},#{polls},#{id}) ON DUPLICATE KEY UPDATE id = #{id}")
    void insertCheerLeader(@Param("collage") String collage, @Param("polls") int polls,@Param("id") int id);

    @Update("Update cheerleader set polls  = #{polls} where id = #{id}")
    int updatePoll(@Param("polls") int polls,@Param("id") int id);

    @Select("Select * from cheerleader where id = #{id}")
    Cheerleader findById(int id);

}
