package com.fangaoxs.lotteryserver.mapper;

import com.fangaoxs.lotteryserver.pojo.Place;
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
public interface PlaceMapper {

    /**
     * 增加一条记录
     *
     * @param place 会场对象
     * @return 增加成功的记录数
     */
    Integer insertOne(Place place);

    /**
     * 删除一条记录
     *
     * @param place 会场对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(Place place);

    /**
     * 修改一条记录
     *
     * @param place 会场对象
     * @return 修改成功的记录数
     */
    Integer updateOne(Place place);

    /**
     * 查询所有集合（可分页，可多条件，可单条件）
     *
     * @param place 会场对象
     * @return place集合
     */
    List<Place> selectList(Place place);

    /**
     * 查询一条place记录（可多条件，可单条件）
     *
     * @param place 会场对象
     * @return 一条会场对象
     */
    Place selectOne(Place place);

    /**
     * 查询记录数（可条件查询）
     *
     * @param place 会场对象
     * @return 记录数
     */
    Long count(Place place);

}
