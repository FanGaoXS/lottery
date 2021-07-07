package com.fangaoxs.lotteryserver.service.impl;

import com.fangaoxs.lotteryserver.mapper.UserMapper;
import com.fangaoxs.lotteryserver.pojo.User;
import com.fangaoxs.lotteryserver.service.UserService;
import com.fangaoxs.lotteryserver.vo.VoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/07/13:03
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean registerUser(VoUser voUser) {
        User user = new User();
        user.setName(voUser.getName());
        user.setAvatar(voUser.getAvatar());
        user.setTime(new Date());
        return userMapper.insertOne(user) > 0;
    }

    @Override
    public Boolean removeUser(Integer id) {
        User user = new User();
        user.setId(id);
        return userMapper.deleteOne(user) > 0;
    }

    @Override
    public Boolean isUserExist(VoUser voUser) {
        User user = new User();
        user.setName(voUser.getName());
        return userMapper.selectOne(user) != null;
    }

    @Override
    public List<VoUser> selectUserList() {
        User user = new User();
        List<User> userList = userMapper.selectList(user);
        List<VoUser> voUserList = new ArrayList<>();
        userList.forEach(dbUser -> {
            voUserList.add(new VoUser(dbUser));
        });
        return voUserList;
    }

    @Override
    public Long totalSize() {
        User user = new User();
        return userMapper.count(user);
    }
}
