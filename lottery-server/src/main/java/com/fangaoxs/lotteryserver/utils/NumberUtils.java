package com.fangaoxs.lotteryserver.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/20/13:31
 * @Description:
 */
public class NumberUtils {

    public static int minIntegerNumber(int[] numbers){
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i]<min) min = numbers[i];
        }
        return min;
    }
}
