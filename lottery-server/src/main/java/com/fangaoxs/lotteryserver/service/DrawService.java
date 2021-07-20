package com.fangaoxs.lotteryserver.service;

import com.fangaoxs.lotteryserver.pojo.User;
import com.fangaoxs.lotteryserver.vo.VoPrize;
import com.fangaoxs.lotteryserver.vo.VoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/20/18:00
 * @Description:
 */
@Service
public interface DrawService {

    List<VoUser> makeDraw(List<User> userList, int number, VoPrize voPrize);

}
