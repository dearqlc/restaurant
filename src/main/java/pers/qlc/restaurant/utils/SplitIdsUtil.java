package pers.qlc.restaurant.utils;

import java.util.ArrayList;
import java.util.List;

public class SplitIdsUtil {

    public static List<Long> splitStrIds(String strIds) {
        int num = strIds.indexOf(",");
        List<Long> idList = new ArrayList<>();
        if (num == -1) {
            idList.add(Long.parseLong(strIds));
        } else {
            strIds = strIds.substring(0, strIds.length() - 1);
            System.out.println(strIds);
            String[] ids = strIds.split(",");
            for (String id : ids) {
                idList.add(Long.parseLong(id));
            }
        }
        return idList;
    }

    public static List<Integer> splitStrIdsByInt(String strIds) {
        int num = strIds.indexOf(",");
        List<Integer> idList = new ArrayList<>();
        if (num == -1) {
            idList.add(Integer.parseInt(strIds));
        } else {
            strIds = strIds.substring(0, strIds.length() - 1);
            System.out.println(strIds);
            String[] ids = strIds.split(",");
            for (String id : ids) {
                idList.add(Integer.parseInt(id));
            }
        }
        return idList;
    }

}
