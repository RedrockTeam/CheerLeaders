package team.redrock.cheeringvote.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 陌花采撷
 */
@Mapper
@Repository
public interface CreateMapper {

    void createCheeringLeader();
    void createUsers();
    void dropCheeringLeader();
    void dropUsers();
}
