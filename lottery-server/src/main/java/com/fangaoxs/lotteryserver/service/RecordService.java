package com.fangaoxs.lotteryserver.service;

import com.fangaoxs.lotteryserver.pojo.Record;
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

    Boolean insertOneRecord(Record record);

    Boolean insertBatchRecord(List<Record> recordList);

}
