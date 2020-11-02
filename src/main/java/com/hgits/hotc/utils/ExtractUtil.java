/*
package com.hgits.hotc.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
// import com.hgits.hotc.service.LogService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Method;

*/
/**
 * @author panhou
 * @description zip解压工具类
 * @date 2020/02/21
 *//*

@Component
public class ExtractUtil {

    @Autowired
    //private LogService logService;

    */
/**
     * 验证MD5值
     *
     * @param fileName
     * @return
     *//*

    public boolean checkMD5(String fileName) throws IOException {
        boolean flag = true;

        File file = new File(fileName);
        FileInputStream fis = null;
        for (File listFile : file.listFiles()) {
            if (listFile.isFile()) {
                String[] split = listFile.getName().split("_");
                String fileMD5 = split[6];
                fis = new FileInputStream(listFile);
                String md5 = DigestUtils.md5Hex(fis).toUpperCase();
                fis.close();
                if (!md5.equals(fileMD5)) {
                    return false;
                }
            }
        }
        return flag;
    }



    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }

    */
/**
     * 首字母转小写
     *
     * @param s
     * @return
     *//*

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }



    public PageInfo<Log> log(Integer sumType,
                          Integer pageNum,
                          Integer pageSize) {
        Page<Log> page = new Page<>(pageNum, pageSize);
        Log log = new Log();
        log.setSumType(sumType);
        return  logService.listByPage(log, page);

    }

}
*/
