package com.fangaoxs.lotteryserver.service.impl;

import com.fangaoxs.lotteryserver.mapper.RecordMapper;
import com.fangaoxs.lotteryserver.pojo.Record;
import com.fangaoxs.lotteryserver.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/14/17:31
 * @Description:
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Boolean insertOneRecord(Record record) {
        return null;
    }

    @Override
    public Boolean insertBatchRecord(List<Record> recordList) {
        return recordMapper.insertBatch(recordList)>0;
    }
}
