package com.fangaoxs.lotteryserver.mapper;

import com.fangaoxs.lotteryserver.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/12/13:09
 * @Description:
 */
@Mapper
@Repository
public interface RecordMapper {

    /**
     * 增加一条记录
     *
     * @param record 中奖记录对象
     * @return 增加成功的记录数
     */
    Integer insertOne(Record record);

    /**
     * 批量增加记录
     * @param recordList 中奖记录对象集合
     * @return  增加成功的记录数
     */
    Integer insertBatch(List<Record> recordList);

    /**
     * 删除一条记录
     *
     * @param record 中奖记录对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(Record record);

    /**
     * 修改一条记录
     *
     * @param record 中奖记录对象
     * @return 修改成功的记录数
     */
    Integer updateOne(Record record);

    /**
     * 查询所有集合（可分页，可多条件，可单条件）
     *
     * @param record 中奖记录对象
     * @return record集合
     */
    List<Record> selectList(Record record);

    /**
     * 查询一条record记录（可多条件，可单条件）
     *
     * @param record 中奖记录对象
     * @return 一条中奖记录对象
     */
    Record selectOne(Record record);

    /**
     * 查询记录数（可条件查询）
     *
     * @param record 中奖记录对象
     * @return 记录数
     */
    Long count(Record record);

}
