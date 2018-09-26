package com.watermelonheart.petmelon.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class Constant {

    public static final int FEMALE = 1;
    public static final int MALE = 2;
    public static final int UNKNOWN = 3;

    public static List<String> genderList = Arrays.asList("Male", "Female", "Unknown");

    public static HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("Female", 1);
        map.put("Male", 2);
        map.put("Unknown", 3);
    }

}
