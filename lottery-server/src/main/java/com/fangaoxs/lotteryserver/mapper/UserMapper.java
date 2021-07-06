package com.fangaoxs.lotteryserver.mapper;

import com.fangaoxs.lotteryserver.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/06/18:20
 * @Description:
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 增加一条记录
     *
     * @param user 用户对象
     * @return 增加成功的记录数
     */
    Integer insertOne(User user);

    /**
     * 删除一条记录
     *
     * @param user 用户对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(User user);

    /**
     * 修改一条记录
     *
     * @param user 用户对象
     * @return 修改成功的记录数
     */
    Integer updateOne(User user);

    /**
     * 查询所有集合（可分页，可多条件，可单条件）
     *
     * @param user 用户对象
     * @return user集合
     */
    List<User> selectList(User user);

    /**
     * 查询一条user记录（可多条件，可单条件）
     *
     * @param user 用户对象
     * @return 一条用户对象
     */
    User selectOne(User user);

    /**
     * 查询记录数（可条件查询）
     *
     * @param user 用户对象
     * @return 记录数
     */
    Long count(User user);

}
