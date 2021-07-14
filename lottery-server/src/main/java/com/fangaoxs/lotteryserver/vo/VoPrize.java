package com.fangaoxs.lotteryserver.vo;

import com.fangaoxs.lotteryserver.pojo.Prize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/14/12:32
 * @Description:
 */
@Data
@NoArgsConstructor
public class VoPrize implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String uuid;

    private Integer idx;

    private String name;

    private Integer amount;

    private Integer balance;

    private String description;

    private Date time;

    private Integer placeId;

    private VoPlace place;

    public VoPrize(Prize prize){
        this.id = prize.getId();
        this.uuid = prize.getUuid();
        this.idx = prize.getIdx();
        this.name = prize.getName();
        this.amount = prize.getAmount();
        this.balance = prize.getBalance();
        this.description = prize.getDescription();
        this.time = prize.getTime();
        this.placeId = prize.getPlaceId();
        this.place = new VoPlace(prize.getPlace());
    }

}
