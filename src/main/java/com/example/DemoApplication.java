package com.example;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.example.entity.Test1;
import com.example.tcp.ClientUtil;
import com.example.tcp.TcpUtil;
import com.example.test.ReflectTest;
import com.example.tiapi.TaskSingleton;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 星星泡面
 */
@Slf4j
@SpringBootApplication
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class DemoApplication implements SpringApplicationRunListener {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        try {
            log.info("启动完成，swagger地址: http://{}:{}/swagger-ui.html",
                    InetAddress.getLocalHost().getHostAddress(),
                    environment.getProperty("server.port"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        reflectTest();
        //ConcurrencyTester tester = ThreadUtil.concurrencyTest(2, () -> {
        //
        //
        //    for (int i = 220000; i < 2200000; i++) {
        //        JSONObject jsonObj = new JSONObject();
        //        jsonObj.put("boxSn", "AGING01-01-" + i);
        //        jsonObj.put("lidarSn", "BN3100013R-0" + i + "B");
        //        String json = jsonObj.toJSONString();
        //        String result2 = HttpRequest.post("http://10.20.3.81:7012/api/aging/lidarAging/save")
        //                .body(json)
        //                .execute().body();
        //        Console.log("{}---->{}",i,result2);
        //    }
        //});
        ////获取总的执行时间，单位毫秒
        //Console.log(tester.getInterval());

        //Map<String, String> respMap = TcpUtil.sendTcpRequest("10.30.61.16", "50001", "S,1,DUT30,Begin,BN6100013UXL-021B,192.168.5.57", "GB18030");
        //System.out.println("响应报文如下");
        //System.out.println(respMap.get("respData"));

        //Console.log(countMac("40-2C-76-80-00-00","40-2C-76-8F-FF-FF"));

    }

    static void reflectTest() throws InvocationTargetException, IllegalAccessException {
        //Method[] methods = ReflectUtil.getMethods(ReflectTest.class);
        //for (int i = 0; i < methods.length; i++) {
        //    Console.log(methods[i].getName() + "");
        //}
        //Console.log(methods);
        Method method = ReflectUtil.getMethod(ReflectTest.class, "getId", String.class, String.class);
        Console.log(method);
        ReflectUtil.setAccessible(method);
        method.invoke(null,"123", "123");
        ////ReflectTest testClass = new ReflectTest();
        //final ReflectTest reflectTest = new ReflectTest();
        for (int i = 0; i < 11; i++) {
            //ReflectUtil.invoke(method, "123", "123");
            //ReflectUtil.invoke(reflectTest, "getId", "123", "123");
            method.invoke(null,"123", "123");


        }

    }

    public static void testHttp() {
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(2000, () -> {
            long delay = RandomUtil.randomLong(100, 1000);
            ThreadUtil.sleep(delay);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("rules", "MMXXYYYYZZZZ");
            jsonObj.put("factory", "LX工厂");
            jsonObj.put("product", "RS-LiDAR-M1P");
            String json = jsonObj.toJSONString();
            String result2 = HttpRequest.post("http://10.30.61.16:7011/dataWarehouse/sn/createInternalSn")
                    .body(json)
                    .header("X-Access-Token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTUxOTA4MDAsInVzZXJuYW1lIjoic2hpeXVlMDIifQ.KXNQk-mGNypRtCwHMOGfqiGTp9Xlf8kgMs5a-Alg1b8")
                    .execute().body();
            Console.log("返回值->{}", result2);
        });
        //获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());
    }

    public static List<DateTime> getDateList() {
        List<DateTime> list = new ArrayList<>();
        DateTime date = DateUtil.date();
        for (int i = 0; i < 12; i++) {
            list.add(DateUtil.offsetMonth(date, i));
        }
        return list;
    }

    private static void testYearMonth() {
        int month = DateUtil.offsetMonth(DateUtil.date(), 8).monthBaseOne();
        System.out.println(month);
        int year1 = DateUtil.offsetMonth(DateUtil.date(), 9).year();
        System.out.println(year1);
        int year2 = DateUtil.offsetMonth(DateUtil.date(), 10).year();
        System.out.println(year2);
    }

    private static void test() {
        // 四月
        DateTime dateTime = DateUtil.offsetMonth(DateUtil.date(), 1);
        Map<String, Integer> deliverMaps = new LinkedHashMap<>(12);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 10).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 10).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 11).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 11).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 0).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 0).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 1).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 1).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 2).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 2).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 3).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 3).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 5).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 5).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 6).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 6).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 7).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 7).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 8).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 8).monthStartFromOne(), 123);
        deliverMaps.put(DateUtil.offsetMonth(DateUtil.date(), 9).year() + "" + DateUtil.offsetMonth(DateUtil.date(), 9).monthStartFromOne(), 123);
        Console.log(deliverMaps);
        Console.log("四月->", DateUtil.offsetMonth(dateTime, 9).year() + "" + DateUtil.offsetMonth(dateTime, 9).monthStartFromOne());

//        deliverMaps.entrySet().removeIf(m -> {
//            DateUtil.offsetMonth(DateUtil.date(), 9);
//         return    m.getValue()>30 || m.getKey() == "abc";
//        });
    }

    private static void test2() {
        Map<Integer, String> headMap = new HashMap<>(24);
        headMap.put(0, "22年3月交付");
        headMap.put(1, "22年3月欠料");
        headMap.put(2, "22年4月交付");
        headMap.put(3, "22年4月欠料");
        headMap.put(4, "22年5月交付");
        headMap.put(5, "22年5月欠料");
        headMap.put(6, "22年6月交付");
        headMap.put(7, "22年6月欠料");
        headMap.put(8, "22年7月交付");
        headMap.put(9, "22年7月欠料");
        headMap.put(10, "22年8月交付");
        headMap.put(11, "22年8月欠料");
        headMap.put(12, "22年9月交付");
        headMap.put(13, "22年9月欠料");
        headMap.put(14, "22年10月交付");
        headMap.put(15, "22年10月欠料");
        headMap.put(16, "22年11月交付");
        headMap.put(17, "22年11月欠料");
        headMap.put(18, "22年12月交付");
        headMap.put(19, "22年12月欠料");
        headMap.put(20, "23年1月交付");
        headMap.put(21, "23年1月欠料");
        headMap.put(22, "23年2月交付");
        headMap.put(23, "23年2月欠料");
        log.info("{}", headMap.entrySet().stream().filter(r -> r.getValue().contains("11月交付")).map(Map.Entry::getKey).collect(Collectors.toList()));
        log.info("{}", headMap.get(-1));
//        ArrayList<String> arrayList = new ArrayList<>(headMap.values());
//        log.info("{}", DateUtil.date().year() - 2000 + 1);
//        log.info("{}", arrayList.indexOf(DateUtil.date().year() - 2000 + 1 + "年1月交付"));
//        log.info("{}", arrayList.contains(DateUtil.date().year() - 2000 + "年1月交付") ? arrayList.indexOf(DateUtil.date().year() - 2000 + "1月交付") : arrayList.indexOf(DateUtil.date().year() - 2000 + 1 + "年1月交付"));
    }

    private static void test3() {
        // 产品编码
        String keyOne = "TPS51200AQDRCTQ1";
        String keyTwo = "TPS51200AQDRCRQ1";
        TaskSingleton.TaskDetails taskDetails = new TaskSingleton.TaskDetails()
                .setRequiredQuantity(99999)
                .setOrderQuantity(0);
        // 启动定时任务
        final TaskSingleton taskSingleton = TaskSingleton.getInstance();
        taskSingleton.addTask(keyOne, taskDetails)
                .addTask(keyTwo, taskDetails);
    }

    private static void test4() {
        List<Test1> test1s = new ArrayList<>();
        test1s.add(new Test1("1", "t1"));
        test1s.add(new Test1("2", "t2"));
        test1s.add(new Test1("3", "t3"));
        test1s.add(new Test1("4", "t4"));
        List<Map<String, String>> list = test1s.stream().map(item -> {
            Map<String, String> map = new HashMap<>(3);
            map.put("label", item.getName());
            map.put("text", item.getName());
            map.put("value", item.getId());
            return map;
        }).collect(Collectors.toList());
        Console.log(JSON.parseArray(JSON.toJSONString(list)));
    }

    private static void test5() {
        List<String> list = new ArrayList<>();

        list.add("车间仓");
        list.add("车间仓");
        list.add("车间仓");
        list.add("车间仓");
        list.add("1车间仓");
        System.out.println(hasSame(list));
    }

    private static boolean hasSame(List<? extends Object> list) {
        if (null == list) {
            return false;
        }
        return 1 == new HashSet<Object>(list).size();
    }

    public static long countMac(String macStart, String macEnd) {
        String[] start = macStart.split("-");
        String[] end = macEnd.split("-");
        int x;
        int y;
        int z;
        int a;
        int b;
        int c;
        x = Integer.parseInt(start[3], 16);
        y = Integer.parseInt(start[4], 16);
        z = Integer.parseInt(start[5], 16);
        a = Integer.parseInt(end[3], 16);
        b = Integer.parseInt(end[4], 16);
        c = Integer.parseInt(end[5], 16);
        return (long) (a - x) * 16 * 16 * 16 + (long) (b - y) * 16 * 16 + c - z + 1;
    }

}
