package com.lx.attendance.utils;

import com.lx.attendance.model.domain.HolidayDO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.lx.attendance.utils.logControl.logPrint;

public class HolidayUtil {
    /**
     * 通过post方式访问外部接口
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url, String param) {
        String result = "";
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity;
            entity = new StringEntity(param, "UTF-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity2 = response.getEntity();
            result = EntityUtils.toString(entity2, "utf-8");
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    /**
     * 通过get方式访问外部接口
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        String result = "";
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity2 = response.getEntity();
            result = EntityUtils.toString(entity2, "utf-8");
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    /**
     * 获取本年度的节假日
     *
     * @return
     */
    public static List<HolidayDO> holidaySeason() {
        try {
            //java提供的日期类
            Calendar cal = Calendar.getInstance();
            //获取当前系统年份（用于API不提供年份，我们做拼接使用）
            String year = String.valueOf(cal.get(Calendar.YEAR));
            int yearAfter = Integer.valueOf(year)+1;
            // 调用开源API接口获取系统年份的节假日信息（API地址：http://timor.tech/api/holiday）
            /*  获取value (由于外部接口传来的数据是：
                    类似{code：0，holiday{"01-01":{holiday:true,wage:3,name:元旦}}})这样的数据 需要拆分出来*/
            String resultStr = sendGet("http://timor.tech/api/holiday/year/"+yearAfter);
            // 将获取的字符串转换成JSON格式
            JSONObject json = new JSONObject(resultStr);
            //定义一个年月日格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //获取转换的json中key为holiday的数据存储起来
            JSONObject holiday = json.getJSONObject("holiday");
            // 使用迭代器遍历
            Iterator iterator = holiday.keys();
            //定义一个节假日实体类
            List<HolidayDO> holidayDOList = new ArrayList<HolidayDO>();
            // api 获取的数据 如果code为0表示成功，-1表示失败
            if (json.get("code").equals(0)) {// 如果是成功代码 遍历节假日数据
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();//获取key名
                    String valueStr = holiday.getString(key);
                    //第二重拆分 {"01-01":{name:元旦,wage:3,holiday:true}}
                    JSONObject valObject = new JSONObject(valueStr);
                    // 将获取到的数据存储到实体类中
                    HolidayDO holidayDO = new HolidayDO();
                    String dateStr = year + "-" + key;
                    Date date = simpleDateFormat.parse(dateStr);
                    holidayDO.setDate(date);
                    holidayDO.setName(valObject.getString("name"));
                    holidayDO.setWage(valObject.getInt("wage"));
                    holidayDO.setYear(year);
                    holidayDOList.add(holidayDO);//存入实体类集合
                }
            }
            return holidayDOList;
        } catch (Exception e) {
            logPrint(HolidayUtil.class,null,e.getMessage());
            return null;
        }
    }
}
