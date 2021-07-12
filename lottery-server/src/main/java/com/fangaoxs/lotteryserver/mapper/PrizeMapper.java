package com.fangaoxs.lotteryserver.mapper;

import com.fangaoxs.lotteryserver.pojo.Prize;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/12/13:08
 * @Description:
 */
@Mapper
@Repository
public interface PrizeMapper {

    /**
     * 增加一条记录
     *
     * @param prize 奖项对象
     * @return 增加成功的记录数
     */
    Integer insertOne(Prize prize);

    /**
     * 删除一条记录
     *
     * @param prize 奖项对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(Prize prize);

    /**
     * 修改一条记录
     *
     * @param prize 奖项对象
     * @return 修改成功的记录数
     */
    Integer updateOne(Prize prize);

    /**
     * 查询所有集合（可分页，可多条件，可单条件）
     *
     * @param prize 奖项对象
     * @return prize集合
     */
    List<Prize> selectList(Prize prize);

    /**
     * 查询一条prize记录（可多条件，可单条件）
     *
     * @param prize 奖项对象
     * @return 一条奖项对象
     */
    Prize selectOne(Prize prize);

    /**
     * 查询记录数（可条件查询）
     *
     * @param prize 奖项对象
     * @return 记录数
     */
    Long count(Prize prize);

}
