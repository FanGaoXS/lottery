package com.fangaoxs.lotteryserver.service.impl;

import com.fangaoxs.lotteryserver.mapper.UserMapper;
import com.fangaoxs.lotteryserver.pojo.Prize;
import com.fangaoxs.lotteryserver.pojo.Record;
import com.fangaoxs.lotteryserver.pojo.User;
import com.fangaoxs.lotteryserver.service.PrizeService;
import com.fangaoxs.lotteryserver.service.RecordService;
import com.fangaoxs.lotteryserver.service.UserService;
import com.fangaoxs.lotteryserver.utils.MD5Utils;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import com.fangaoxs.lotteryserver.vo.VoUser;
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
 * @Date: 2021/07/13/16:29
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecordService recordService;

    @Override
    public VoUser insertOneUserWithPlaceId(VoUser voUser) {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setName(voUser.getName());
        user.setAvatar(voUser.getAvatar());
        user.setMd5(MD5Utils.md5NameAndAvatar(voUser.getName(),voUser.getAvatar())); //直接生成md5
//        user.setPhone();
        user.setTime(new Date());
        user.setPlaceId(voUser.getPlaceId());
        if (userMapper.insertOne(user) > 0){
            return selectOneUserById(user.getId());
        }
        return null;
    }

    @Override
    public Boolean deleteOneUser(Integer id) {
        User user = new User();
        user.setId(id);
        return userMapper.deleteOne(user) > 0;
    }

    @Override
    public Boolean updateOneUser(VoUser voUser) {
        User user = new User();
        user.setId(voUser.getId());
        user.setName(voUser.getName());
        user.setAvatar(voUser.getAvatar());
        return userMapper.updateOne(user)> 0 ;
    }

    @Override
    public VoList<VoUser> selectAllUser() {
        User user = new User();
        VoList<VoUser> voList = new VoList<>();
        voList.setCurrentPage(null);
        voList.setPageSize(null);
        voList.setItems(getItems(user));
        voList.setTotalSize(userMapper.count(user));
        return voList;
    }

    @Override
    public VoList<VoUser> selectListUser(Integer currentPage, Integer pageSize) {
        User user = new User();
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);

        VoList<VoUser> voList = new VoList<>();
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        voList.setItems(getItems(user));
        voList.setTotalSize(userMapper.count(user));

        return voList;
    }

    @Override
    public List<User> selectListUserByPlaceId(Integer placeId) {
        User user = new User();
        user.setPlaceId(placeId);
        return userMapper.selectList(user);
    }

    @Override
    public List<User> selectListUserByPlaceIdNotPrize(Integer placeId) {
        List<Record> recordList = recordService.selectListRecordByPlaceId(placeId);     //该会场已中奖记录
        User user = new User();
        user.setPlaceId(placeId);
        List<User> userList = userMapper.selectList(user);                              //该会场已经登记用户
        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);
            for (int j = 0; j < userList.size(); j++) {
                User dbUser = userList.get(j);
                if (record.getUserId() == dbUser.getId()){
                    userList.remove(j);
                }
            }
        }
        return userList;
    }

    @Override
    public VoList<VoUser> selectListUserByPlaceId(Integer placeId, Integer currentPage, Integer pageSize) {
        User user = new User();
        user.setPlaceId(placeId);
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);
        VoList<VoUser> voList = new VoList<>();
        voList.setItems(getItems(user));
        voList.setTotalSize(userMapper.count(user));
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        return voList;
    }

    @Override
    public VoUser selectOneUserByNameWithPlaceId(String name,Integer placeId) {
        User user = new User();
        user.setName(name);
        user.setPlaceId(placeId);
        try{
            User dbUser = userMapper.selectOne(user);
            return new VoUser(dbUser);
        } catch (NullPointerException e){
            return null;
        }

    }

    @Override
    public VoUser selectOneUserByMd5WithPlaceId(String md5, Integer placeId) {
        User user = new User();
        user.setMd5(md5);
        user.setPlaceId(placeId);
        try{
            User dbUser = userMapper.selectOne(user);
            return new VoUser(dbUser);
        } catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public VoUser selectOneUserById(Integer id) {
        User user = new User();
        user.setId(id);
        try{
            User dbUser = userMapper.selectOne(user);
            return new VoUser(dbUser);
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    private List<VoUser> getItems(User user){
        ArrayList<VoUser> list = new ArrayList<>();
        List<User> userList = userMapper.selectList(user);
        userList.forEach(dbUser->{
            list.add(new VoUser(dbUser));
        });
        return list;
    }

}
