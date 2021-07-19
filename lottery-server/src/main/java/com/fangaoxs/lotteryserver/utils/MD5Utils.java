package com.fangaoxs.lotteryserver.utils;

import org.springframework.util.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/19/14:51
 * @Description:
 */
public class MD5Utils {

    public static String md5NameAndAvatar(String name,String avatar){
        byte[] md5Byte = (name +"|"+ avatar).getBytes();
        return DigestUtils.md5DigestAsHex(md5Byte);
    }

}
