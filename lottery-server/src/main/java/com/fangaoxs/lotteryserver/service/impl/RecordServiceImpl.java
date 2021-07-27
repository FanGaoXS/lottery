package com.fangaoxs.lotteryserver.service.impl;

import com.fangaoxs.lotteryserver.mapper.RecordMapper;
import com.fangaoxs.lotteryserver.pojo.Record;
import com.fangaoxs.lotteryserver.service.RecordService;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Boolean insertBatchRecord(List<Record> recordList) {
        return recordMapper.insertBatch(recordList)>0;
    }

    @Override
    public Boolean deleteOneRecord(Integer id) {
        Record record = new Record();
        record.setId(id);
        return recordMapper.deleteOne(record)>0;
    }

    @Override
    public VoRecord selectOneRecordById(Integer id) {
        Record record = new Record();
        record.setId(id);
        try {
            Record dbRecord = recordMapper.selectOne(record);
            System.out.println("dbRecord = " + dbRecord);
            return new VoRecord(dbRecord);
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VoList<VoRecord> selectListRecordByPlaceId(Integer placeId,
                                                      Integer currentPage,
                                                      Integer pageSize) {
        Record record = new Record();
        record.setPlaceId(placeId);
        record.setCurrentPage(currentPage);
        record.setPageSize(pageSize);

        VoList<VoRecord> voList = new VoList<>();
        voList.setPageSize(pageSize);
        voList.setCurrentPage(currentPage);
        voList.setTotalSize(recordMapper.count(record));
        voList.setItems(getItems(record));
        return voList;
    }

    @Override
    public VoList<VoRecord> selectListRecordByPrizeId(Integer prizeId,
                                                      Integer currentPage,
                                                      Integer pageSize) {
        Record record = new Record();
        record.setPrizeId(prizeId);
        record.setCurrentPage(currentPage);
        record.setPageSize(pageSize);

        VoList<VoRecord> voList = new VoList<>();
        voList.setPageSize(pageSize);
        voList.setCurrentPage(currentPage);
        voList.setTotalSize(recordMapper.count(record));
        voList.setItems(getItems(record));
        return voList;
    }

    @Override
    public List<Record> selectListRecordByPlaceId(Integer placeId) {
        Record record = new Record();
        record.setPlaceId(placeId);
        return recordMapper.selectList(record);
    }

    private List<VoRecord> getItems(Record record){
        List<VoRecord> items = new ArrayList<>();
        List<Record> recordList = recordMapper.selectList(record);
        recordList.forEach(dbRecord->{
            items.add(new VoRecord(dbRecord));
        });
        return items;
    }

}
