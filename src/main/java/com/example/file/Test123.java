package com.example.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Optional;

public class Test123 {
    public static void main(String[] args) {
        String oldPersonDataStr = "{\"personal_profile\":[{\"personal_profile_id\":\"7291850262029223474\",\"personal_profile_type\":{\"enum_name\":\"profile_type_1_105_10502\",\"display\":[{\"lang\":\"zh-CN\",\"value\":\"个人照片\"},{\"lang\":\"en-US\",\"value\":\"Personal certificate photo\"}]},\"files\":[{\"id\":\"6531db3b441c3001cf524065_1ec523fb16fb48949ab6b852a6470425\"}]},{\"personal_profile_id\":\"7291850262029239858\",\"personal_profile_type\":{\"enum_name\":\"profile_type_1_102_10201\",\"display\":[{\"lang\":\"en-US\",\"value\":\"education certificate\"},{\"lang\":\"zh-CN\",\"value\":\"学历证书\"}]},\"files\":[{\"id\":\"6531db3ae50c1011d5a4df54_946aedc42d3340f4b628806eedd872ce\"}]},{\"personal_profile_id\":\"7291850262029256242\",\"personal_profile_type\":{\"enum_name\":\"profile_type_1_102_10205\",\"display\":[{\"lang\":\"en-US\",\"value\":\"Student status certification of xuexin\"},{\"lang\":\"zh-CN\",\"value\":\"学历认证资料\"}]},\"files\":[{\"id\":\"6531db39e50c1011d5a4df53_0d679c7446e94608bcc74d09988c8243\"}]},{\"personal_profile_id\":\"7291980673009583626\",\"personal_profile_type\":{\"enum_name\":\"profile_type_1_104_10403\",\"display\":[{\"lang\":\"zh-CN\",\"value\":\"简历\"},{\"lang\":\"en-US\",\"value\":\"Employment resume\"}]},\"files\":[{\"id\":\"653251d1441c3001cf524947_f3ac67647a134c8ca1cf36f2e7a7e974\"}]},{\"personal_profile_id\":\"7293327826856855049\",\"personal_profile_type\":{\"enum_name\":\"profile_type_option8_7218894214268092417__c\",\"display\":[{\"lang\":\"zh-CN\",\"value\":\"面试评估表\"},{\"lang\":\"en-US\",\"value\":\"\"}]},\"files\":[{\"id\":\"65371ae73f70a22aa292df75_92560f8383714be2a59848191b251c7f\"}]},{\"personal_profile_id\":\"7294281012687046195\",\"personal_profile_type\":{\"enum_name\":\"profile_type_2_201_20101\",\"display\":[{\"lang\":\"en-US\",\"value\":\"Employment agreement\"},{\"lang\":\"zh-CN\",\"value\":\"竞业限制协议\"}]},\"files\":[{\"id\":\"653a7df071379dd06a326aa2_47051824f3404304b139a602fb743e05\"},{\"id\":\"653a7df03c79cb22c680626b_e2c09f4026bd4ca59797ce9ecbef356a\"},{\"id\":\"653a7df166358ec15dcdb129_bf66434d648a491488470f6b29eeabc4\"},{\"id\":\"653a7df271379dd06a326aa3_bcc691e9b92d4d58b826f61d1de88c30\"},{\"id\":\"653a7df216f8552a27baec26_e2d369061c304dc78e15a5be7146f9b6\"}]}]}";
        String personalProfileArrayStr = "[{\"personal_profile_type\":{\"enum_name\":\"profile_type_1_105_105456\"},\"files\":[{\"id\":\"6531db3b441c3001cf524065_1ec523fb16fb48949ab6b852a6470425\"}]},{\"personal_profile_type\":{\"enum_name\":\"profile_type_1_102_1020789\"},\"files\":[{\"id\":\"6531db3ae50c1011d5a4df54_946aedc42d3340f4b628806eedd872ce\"}]},{\"personal_profile_type\":{\"enum_name\":\"profile_type_1_105_10502\"},\"files\":[{\"id\":\"6531db3ae50c1011d5a4df54_946aedc42d3340f4b628806eedd872ce\"}]}]";
        JSONObject oldPersonData = JSON.parseObject(oldPersonDataStr);
        JSONArray personalProfileArray = JSON.parseArray(personalProfileArrayStr);
        JSONArray oldPersonalProfile =
                Optional.of(oldPersonData).map(i -> i.getJSONArray("personal_profile")).orElse(new JSONArray());
        for (int i = 0; i < personalProfileArray.size(); i++) {
            JSONObject newFileData = personalProfileArray.getJSONObject(i);
            String newEnumName = newFileData.getJSONObject("personal_profile_type").getString("enum_name");
            boolean isAdd = true;
            for (int j = 0; j < oldPersonalProfile.size(); j++) {
                JSONObject oldFileData = oldPersonalProfile.getJSONObject(j);
                String oldEnumName = oldFileData.getJSONObject("personal_profile_type").getString("enum_name");
                if (newEnumName.equals(oldEnumName)) {
                    JSONArray oldFiles = oldFileData.getJSONArray("files");
                    JSONArray newFiles = newFileData.getJSONArray("files");
                    oldFiles.addAll(newFiles);
                    isAdd = false;
                    break;
                }
            }
            if (isAdd) {
                oldPersonalProfile.add(newFileData);
            }

        }
        System.out.println(oldPersonalProfile);
    }
}
