package com.fangaoxs.lotteryserver.service.impl;

import com.fangaoxs.lotteryserver.mapper.UserMapper;
import com.fangaoxs.lotteryserver.pojo.User;
import com.fangaoxs.lotteryserver.service.UserService;
import com.fangaoxs.lotteryserver.vo.VoList;
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

    @Override
    public Boolean insertOneUserWithPlaceId(VoUser voUser) {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setName(voUser.getName());
        user.setAvatar(voUser.getAvatar());
//        user.setPhone();
        user.setTime(new Date());
        user.setPlaceId(voUser.getPlaceId());
        return userMapper.insertOne(user) > 0;
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
    public VoUser selectOneUserById(Integer id) {
        User user = new User();
        user.setId(id);
        try{
            User dbUser = userMapper.selectOne(user);
            return new VoUser(dbUser);
        } catch (NullPointerException e){
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
