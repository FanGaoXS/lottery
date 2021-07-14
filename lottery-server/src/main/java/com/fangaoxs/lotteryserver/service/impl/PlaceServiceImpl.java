package com.fangaoxs.lotteryserver.service.impl;

import com.fangaoxs.lotteryserver.mapper.PlaceMapper;
import com.fangaoxs.lotteryserver.pojo.Place;
import com.fangaoxs.lotteryserver.service.PlaceService;
import com.fangaoxs.lotteryserver.vo.VoList;
import com.fangaoxs.lotteryserver.vo.VoPlace;
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
 * @Date: 2021/07/13/13:05
 * @Description:
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public Boolean insertOnePlace(VoPlace voPlace) {
        Place place = new Place();
        place.setUuid(UUID.randomUUID().toString());
        place.setName(voPlace.getName());
        place.setAddress(voPlace.getAddress());
        place.setTime(new Date());
        return placeMapper.insertOne(place) > 0 ;
    }

    @Override
    public Boolean deleteOnePlace(Integer placeId) {
        Place place = new Place();
        place.setId(placeId);
        return placeMapper.deleteOne(place) > 0;
    }

    @Override
    public Boolean updateOnePlace(VoPlace voPlace) {
        Place place = new Place();
        place.setId(voPlace.getId());
        place.setName(voPlace.getName());
        place.setAddress(voPlace.getAddress());
        return placeMapper.updateOne(place) > 0 ;
    }

    @Override
    public VoList<VoPlace> selectAllPlace() {
        Place place = new Place();
        VoList<VoPlace> voList = new VoList<>();
        voList.setItems(getItems(place));
        voList.setTotalSize(placeMapper.count(place));
        return voList;
    }

    @Override
    public VoList<VoPlace> selectListPlace(Integer currentPage, Integer pageSize) {
        if (currentPage==null || pageSize==null){ //默认页码和每页记录数
            currentPage = 1;
            pageSize = 10;
        }

        Place place = new Place();
        place.setPageSize(pageSize);
        place.setCurrentPage(currentPage);

        VoList<VoPlace> voList = new VoList<>();
        voList.setItems(getItems(place));
        voList.setTotalSize(placeMapper.count(place));
        voList.setPageSize(pageSize);
        voList.setCurrentPage(currentPage);

        return voList;
    }

    @Override
    public VoPlace selectOnePlaceById(Integer placeId) {
        Place place = new Place();
        place.setId(placeId);
        try {
            Place dbPlace = placeMapper.selectOne(place);
            return new VoPlace(dbPlace);
        } catch (NullPointerException e){
            return null;
        }

    }

    @Override
    public VoPlace selectOnePlaceByName(String name) {
        Place place = new Place();
        place.setName(name);
        try {
            Place dbPlace = placeMapper.selectOne(place);
            return new VoPlace(dbPlace);
        } catch (NullPointerException e){
            return null;
        }
    }

    private List<VoPlace> getItems(Place place){
        ArrayList<VoPlace> list = new ArrayList<>();
        List<Place> placeList = placeMapper.selectList(place);
        placeList.forEach(dbPlace -> {
            list.add(new VoPlace(dbPlace));
        });
        return list;
    }

}
