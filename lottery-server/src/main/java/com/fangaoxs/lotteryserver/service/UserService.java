package com.fangaoxs.lotteryserver.service;

import com.fangaoxs.lotteryserver.pojo.User;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/13/12:50
 * @Description:
 */
@Service
public interface UserService {

    VoUser insertOneUserWithPlaceId(VoUser voUser);

    Boolean deleteOneUser(Integer id);

    Boolean updateOneUser(VoUser voUser);

    VoList<VoUser> selectAllUser();

    VoList<VoUser> selectListUser(Integer currentPage,
                                  Integer pageSize);

    List<User> selectListUserByPlaceId(Integer placeId);

    /**
     *  查询某会场已登记但还未中奖的用户集合
     * @param placeId   会场编号
     * @return
     */
    List<User> selectListUserByPlaceIdNotPrize(Integer placeId);

    VoList<VoUser> selectListUserByPlaceId(Integer placeId,
                                           Integer currentPage,
                                           Integer pageSize);

    VoUser selectOneUserByNameWithPlaceId(String name,Integer placeId);

    VoUser selectOneUserByMd5WithPlaceId(String md5,Integer placeId);

    VoUser selectOneUserById(Integer id);

}
