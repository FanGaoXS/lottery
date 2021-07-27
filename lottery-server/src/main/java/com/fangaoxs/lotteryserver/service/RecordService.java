package com.fangaoxs.lotteryserver.service;

import com.fangaoxs.lotteryserver.pojo.Record;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/13/12:51
 * @Description:
 */
@Service
public interface RecordService {

    Boolean insertBatchRecord(List<Record> recordList);

    Boolean deleteOneRecord(Integer id);

    VoRecord selectOneRecordById(Integer id);

    VoList<VoRecord> selectListRecordByPlaceId(Integer placeId,Integer currentPage,Integer pageSize);

    VoList<VoRecord> selectListRecordByPrizeId(Integer prizeId,Integer currentPage,Integer pageSize);

    List<Record> selectListRecordByPlaceId(Integer placeId);
}
