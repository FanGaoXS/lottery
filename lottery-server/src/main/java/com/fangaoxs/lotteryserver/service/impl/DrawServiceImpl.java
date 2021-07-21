package com.fangaoxs.lotteryserver.service.impl;

import com.fangaoxs.lotteryserver.pojo.Record;
import com.fangaoxs.lotteryserver.pojo.User;
import com.fangaoxs.lotteryserver.service.DrawService;
import com.fangaoxs.lotteryserver.service.PrizeService;
import com.fangaoxs.lotteryserver.service.RecordService;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import com.fangaoxs.lotteryserver.vo.VoUser;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/20/18:01
 * @Description:
 */
@Service
@Transactional
public class DrawServiceImpl implements DrawService {

    @Autowired
    private RecordService recordService;

    @Autowired
    private PrizeService prizeService;

    /**
     *
     * @param userList  抽奖总登记用户集合
     * @param number    抽奖数量
     * @param voPrize   奖项对象
     * @return
     */
    @Override
    public List<VoUser> makeDraw(List<User> userList, int number, VoPrize voPrize) {
        List<User> drawUserList = draw(userList, number); //抽中对象集合
        List<VoUser> drawVoUserList = new ArrayList<>();  //预定义抽中的对象集合
        drawUserList.forEach(user -> drawVoUserList.add(new VoUser(user)));
        try {
            //修改prize表的balance
            prizeService.updateOnePrizeBalance(voPrize.getId(), voPrize.getBalance()-drawUserList.size());
            //record表新增
            List<Record> recordList = userListToRecordList(drawUserList, voPrize.getId());
            recordService.insertBatchRecord(recordList);
        } catch (DataAccessException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            return new ArrayList<>(); //返回空list
        }
        return drawVoUserList;
    }

    /**
     * roll逻辑
     * @param userList  已登记用户集合
     * @param number    抽奖数量
     * @return          中奖对象数组
     */
    private List<User> draw(List<User> userList,int number){
        int size = Math.min(userList.size(),number); //保证返回的数组的大小
        List<User> resultUserList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            User user = userList.get(i);
            resultUserList.add(user);
        }
        return resultUserList;
    }

    /**
     * 将已经中奖的用户集合转为中奖记录集合（便于批量插入进record表）
     * @param userList
     * @param prizeId
     * @return
     */
    private List<Record> userListToRecordList(List<User> userList,Integer prizeId){
        ArrayList<Record> recordList = new ArrayList<>();
        userList.forEach(user -> {
            Record record = new Record();
            record.setUuid(UUID.randomUUID().toString());
            record.setTime(new Date());
            record.setPlaceId(user.getPlaceId());
            record.setUserId(user.getId());
            record.setPrizeId(prizeId);
            recordList.add(record);
        });
        return recordList;
    }
}
