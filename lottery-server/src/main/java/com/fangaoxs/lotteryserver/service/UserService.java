package com.fangaoxs.lotteryserver.service;

import com.fangaoxs.lotteryserver.vo.VoUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/07/12:43
 * @Description:
 */
@Service
public interface UserService {

    /**
     * 登记用户
     * @param voUser
     * @return
     */
    Boolean registerUser(VoUser voUser);

    /**
     * 移除登记的用户
     * @param id
     * @return
     */
    Boolean removeUser(Integer id);

    /**
     * 查询用户是否存在
     * @param voUser
     * @return
     */
    Boolean isUserExist(VoUser voUser);

    /**
     * 列出所有登记的用户
     * @return
     */
    List<VoUser> selectUserList();

    /**
     * 查询登记用户的数量
     * @return
     */
    Long totalSize();

}
