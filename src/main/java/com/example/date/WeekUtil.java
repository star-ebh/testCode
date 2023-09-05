package com.example.date;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * 周实用程序
 *
 * @author aaron.huang
 * @version 1.0.0
 * @since 2023-07-13
 */
public class WeekUtil {

    public static boolean isMonday() {
        return LocalDate.now().getDayOfWeek().getValue() == DayOfWeek.MONDAY.getValue();
    }

    public static void main(String[] args) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page_size","100");
        params.add("page_token","21111111111");
        URI uri =
                UriComponentsBuilder.fromHttpUrl("http://127.0.0.1" + "/test").queryParams(params).build().encode().toUri();
        System.out.println(uri);
        String htmlString = "[{\"msg\":\"\",\"digitalSignature\":\"Shard Wang\",\"activityName\":\"起草节点\",\"createDate\":\"2023-07-03 17:47:18\",\"actionName\":\"提交\"},{\"msg\":\"同意\",\"digitalSignature\":\"Faness Ren\",\"activityName\":\"物料计划组负责人审批\",\"createDate\":\"2023-07-04 09:58:09\",\"actionName\":\"同意\"},{\"msg\":\"同意\",\"digitalSignature\":\"Helen He\",\"activityName\":\"采购部负责人审批需求\",\"createDate\":\"2023-07-10 10:02:50\",\"actionName\":\"同意\"},{\"msg\":\"抄送:Rock Ma\",\"digitalSignature\":\"系统处理\",\"activityName\":\"抄送财务主管\",\"createDate\":\"2023-07-10 10:02:51\",\"actionName\":\"抄送\"},{\"msg\":\"同意。位元电子（上海）有限公司：采购订单号CGDD008884、物料编码10107-0005-00，价格由原单价USD:0.415调整为单价USD0.440。价格变动的原因是原厂统一涨价。\",\"digitalSignature\":\"Candy Yuan\",\"activityName\":\"应付会计审批\",\"createDate\":\"2023-07-10 11:35:01\",\"actionName\":\"同意\"},{\"msg\":\"同意\",\"digitalSignature\":\"April Wang\",\"activityName\":\"财务总监审批\",\"createDate\":\"2023-07-10 14:41:45\",\"actionName\":\"同意\"},{\"msg\":\"同意\",\"digitalSignature\":\"Kelvin Lau\",\"activityName\":\"CFO审批\",\"createDate\":\"2023-07-10 15:32:43\",\"actionName\":\"同意\"},{\"msg\":\"怎么还是有很多月结不是90天的？另外检查下付款是否能把现有或者未来临近将产生的承兑用上去。<br><br><b><font color=red>发起加签</font></b>\",\"digitalSignature\":\"Mark Qiu\",\"activityName\":\"执行总裁审批\",\"createDate\":\"2023-07-11 19:46:31\",\"actionName\":\"加签\"},{\"msg\":\"电子料供应商很多都打不到月结90天的条件，这块在规划中，短时间内没办法，只能等采购额上去后找时机谈。承兑不用给电子料供应商，基本谈不下来，放在结构那些好谈的供应商比较好<br><b><font color=green> <span userid='mark' >Mark Qiu</span>发起的加签完毕</font></b>\",\"digitalSignature\":\"Helen He\",\"activityName\":\"执行总裁审批\",\"createDate\":\"2023-07-12 11:16:13\",\"actionName\":\"加签完毕\"},{\"msg\":\"同意\",\"digitalSignature\":\"Mark Qiu\",\"activityName\":\"执行总裁审批\",\"createDate\":\"2023-07-13 12:09:01\",\"actionName\":\"同意\"},{\"msg\":\"没有审批能力\",\"digitalSignature\":\"Steven Qiu\",\"activityName\":\"CEO审批\",\"createDate\":\"2023-07-13 13:23:21\",\"actionName\":\"同意\"},{\"msg\":\"抄送:Bella Xie,Jenny Chen\",\"digitalSignature\":\"系统处理\",\"activityName\":\"抄送出纳\",\"createDate\":\"2023-07-13 13:23:23\",\"actionName\":\"抄送\"}]";

        // 使用正则表达式匹HTML标签
        Pattern pattern = Pattern.compile("<.*?>");
        String plainText = pattern.matcher(htmlString).replaceAll("");

        System.out.println("原始HTML字符串: " + htmlString);

        System.out.println("去除HTML标签后的字符串: " + pattern
                .matcher(CharSequenceUtil
                        .removeAllLineBreaks(CharSequenceUtil.blankToDefault(Convert.toStr(htmlString), "")))
                .replaceAll(""));

    }
}
