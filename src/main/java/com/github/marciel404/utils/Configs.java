package com.github.marciel404.utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;

public class Configs {
    public static String configData(String path, String[] item){

        String config;
        try {
            config = FileUtils.readFileToString(new File(path), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String result = "";

        try {
            if (item.length == 1) {

                result = String.valueOf(
                        new JSONObject(
                                config
                        )
                                .get(
                                        item[0]
                                )
                );

            }
            if (item.length == 2) {

                JSONObject j = new JSONObject(
                        config
                );
                JSONObject o = new JSONObject(
                        j.get(
                                item[0]
                        ).toString()
                );
                result = String.valueOf(
                        o.get(
                                item[1]
                        )
                );

            } else {

                JSONObject i1 = new JSONObject(
                        config
                );
                JSONObject i2 = new JSONObject(
                        i1.get(
                                item[0]
                        ).toString()
                );
                JSONObject i3 = new JSONObject(
                        i2.get(
                                item[1]
                        ).toString()
                );
                result = String.valueOf(
                        i3.get(
                                item[2]
                        )
                );
            }
        } catch ( Exception ignored){

        }

        return result;
    }

}
