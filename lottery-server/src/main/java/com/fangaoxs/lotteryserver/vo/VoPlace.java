package com.fangaoxs.lotteryserver.vo;

import com.fangaoxs.lotteryserver.pojo.Place;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/13/12:52
 * @Description:
 */
@Data
@NoArgsConstructor
public class VoPlace implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String uuid;

    private String name;

    private String address;

//    private Date time;

    public VoPlace(Place place){
        this.id = place.getId();
        this.uuid = place.getUuid();
        this.name = place.getName();
        this.address = place.getAddress();
//        this.time = place.getTime();
    }

}
