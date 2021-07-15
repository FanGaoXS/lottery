package com.fangaoxs.lotteryserver.service;

import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPlace;
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
public interface PlaceService {

    VoPlace insertOnePlace(VoPlace voPlace);

    Boolean deleteOnePlace(Integer placeId);

    VoPlace updateOnePlace(VoPlace voPlace);

    VoList<VoPlace> selectAllPlace();

    VoList<VoPlace> selectListPlace(Integer currentPage,Integer pageSize);

    VoPlace selectOnePlaceById(Integer placeId);

    VoPlace selectOnePlaceByName(String name);

}
