package com.fangaoxs.lotteryserver.service.impl;

import com.fangaoxs.lotteryserver.mapper.PrizeMapper;
import com.fangaoxs.lotteryserver.pojo.Prize;
import com.fangaoxs.lotteryserver.service.PrizeService;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/14/12:42
 * @Description:
 */
@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    @Override
    public Boolean insertOnePrizeWithPlaceId(VoPrize voPrize) {
        Prize prize = new Prize();
        prize.setUuid(UUID.randomUUID().toString());
        prize.setIdx(0);
        prize.setName(voPrize.getName());
        prize.setAmount(voPrize.getAmount());
        prize.setBalance(voPrize.getAmount());  //初始容量和余额应该一致
        prize.setDescription(voPrize.getDescription());
        prize.setTime(new Date());
        prize.setPlaceId(voPrize.getPlaceId());
        return prizeMapper.insertOne(prize) > 0;
    }

    @Override
    public Boolean deleteOnePrize(Integer id) {
        Prize prize = new Prize();
        prize.setId(id);
        return prizeMapper.deleteOne(prize) > 0;
    }

    @Override
    public Boolean updateOnePrize(VoPrize voPrize) {
        Prize prize = new Prize();
        prize.setId(voPrize.getId());
        prize.setName(voPrize.getName());
        prize.setAmount(voPrize.getAmount());
        prize.setBalance(voPrize.getBalance());
        prize.setDescription(voPrize.getDescription());
        return prizeMapper.updateOne(prize)>0;
    }

    @Override
    public Boolean updateOnePrizeIdx(Integer id, Integer idx) {
        Prize prize = new Prize();
        prize.setId(id);
        prize.setIdx(idx);
        return prizeMapper.updateOne(prize)>0;
    }

    @Override
    public VoList<VoPrize> selectAllPrize() {
        Prize prize = new Prize();
        VoList<VoPrize> voList = new VoList<>();
        voList.setItems(getItems(prize));
        voList.setTotalSize(prizeMapper.count(prize));
        return voList;
    }

    @Override
    public VoList<VoPrize> selectListPrize(Integer currentPage, Integer pageSize) {
        Prize prize = new Prize();
        prize.setPageSize(pageSize);
        prize.setCurrentPage(currentPage);
        VoList<VoPrize> voList = new VoList<>();
        voList.setItems(getItems(prize));
        voList.setTotalSize(prizeMapper.count(prize));
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        return voList;
    }

    @Override
    public VoList<VoPrize> selectListPrizeByPlaceId(Integer placeId, Integer currentPage, Integer pageSize) {
        Prize prize = new Prize();
        prize.setPlaceId(placeId);
        prize.setPageSize(pageSize);
        prize.setCurrentPage(currentPage);
        VoList<VoPrize> voList = new VoList<>();
        voList.setItems(getItems(prize));
        voList.setTotalSize(prizeMapper.count(prize));
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        return voList;
    }

    @Override
    public VoPrize selectOnePrizeById(Integer id) {
        Prize prize = new Prize();
        prize.setId(id);
        try{
            Prize dbPrize = prizeMapper.selectOne(prize);
            return new VoPrize(dbPrize);
        } catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public VoPrize selectOnePrizeByNameWithPlaceId(String name, Integer placeId) {
        Prize prize = new Prize();
        prize.setName(name);
        prize.setPlaceId(placeId);
        try{
            Prize dbPrize = prizeMapper.selectOne(prize);
            return new VoPrize(dbPrize);
        } catch (NullPointerException e){
            return null;
        }
    }

    private List<VoPrize> getItems(Prize prize){
        ArrayList<VoPrize> list = new ArrayList<>();
        List<Prize> prizeList = prizeMapper.selectList(prize);
        prizeList.forEach(dbPrize->{
            list.add(new VoPrize(dbPrize));
        });
        return list;
    }
}
