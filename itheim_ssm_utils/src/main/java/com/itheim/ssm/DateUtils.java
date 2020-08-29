package com.itheim.ssm;

import org.apache.taglibs.standard.tag.common.fmt.FormatDateSupport;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>heima_ssm</h3>
 * <p>类型转换</p>
 *
 * @author : Dell
 * @date : 2020-08-15 19:23
 **/
public class DateUtils {
    public static String DateToStr(Date date,String patt){
        SimpleDateFormat format=new SimpleDateFormat(patt);
        String s = format.format(date);
        return s;

    }
}
