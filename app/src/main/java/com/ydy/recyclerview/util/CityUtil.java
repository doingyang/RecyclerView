package com.ydy.recyclerview.util;

import com.ydy.recyclerview.R;
import com.ydy.recyclerview.bean.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CityUtil {

    private static final String[] CITYS = {"福建省", "安徽省", "浙江省", "江苏省"};

    /**
     * 获取城市名
     */
    public static List<City> getCityList() {
        List<City> dataList = new ArrayList<>();
        final String FU_JIAN = CITYS[0];
        final int FU_JIAN_ICON = R.mipmap.city1;
        dataList.add(new City("福州", FU_JIAN, FU_JIAN_ICON));
        dataList.add(new City("厦门", FU_JIAN, FU_JIAN_ICON));
        dataList.add(new City("泉州", FU_JIAN, FU_JIAN_ICON));
        dataList.add(new City("宁德", FU_JIAN, FU_JIAN_ICON));
        dataList.add(new City("漳州", FU_JIAN, FU_JIAN_ICON));
        final String AN_HUI = CITYS[1];
        final int AN_HUI_ICON = R.mipmap.city2;
        dataList.add(new City("合肥", AN_HUI, AN_HUI_ICON));
        dataList.add(new City("芜湖", AN_HUI, AN_HUI_ICON));
        dataList.add(new City("蚌埠", AN_HUI, AN_HUI_ICON));
        final String ZHE_JIANG = CITYS[2];
        final int ZHE_JIANG_ICON = R.mipmap.city3;
        dataList.add(new City("杭州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("宁波", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("温州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("嘉兴", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("绍兴", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("金华", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("湖州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("舟山", ZHE_JIANG, ZHE_JIANG_ICON));
        final String JIANG_SU = CITYS[3];
        final int JIANG_SU_ICOM = R.mipmap.city4;
        dataList.add(new City("南京", JIANG_SU, JIANG_SU_ICOM));
        return dataList;
    }


    /**
     * 获取城市名
     */
    public static List<City> getRandomCityList() {
        List<City> dataList = new ArrayList<>();
        Random random = new Random();
        int provinceSize = random.nextInt(5) + 3;
        for (int i = 0; i < provinceSize; i++) {
            String province = getRandomCityName();
            int citySize = random.nextInt(3) + 1;
            for (int j = 0; j < citySize; j++) {
                dataList.add(new City(province + " : city " + j, province, R.mipmap.city4));
            }
        }
        return dataList;
    }

    private static String getRandomCityName() {
        Random random = new Random();
        return CITYS[random.nextInt(4)];
    }
}
