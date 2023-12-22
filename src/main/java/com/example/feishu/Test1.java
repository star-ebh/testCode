package com.example.feishu;

import com.lark.oapi.Client;
import com.lark.oapi.core.request.RequestOptions;
import com.lark.oapi.core.utils.Jsons;
import com.lark.oapi.service.corehr.v1.model.UploadPersonReq;
import com.lark.oapi.service.corehr.v1.model.UploadPersonReqBody;
import com.lark.oapi.service.corehr.v1.model.UploadPersonResp;

import java.io.File;

public class Test1 {
    public static void main(String[] args) throws Exception {
        // 构建client
        Client client = Client.newBuilder("cli_a313ff91f1b2900e", "WGCR08cmfrQmg9UwhclhAfoZrf64yNMs")
                .build();

        // 创建请求对象
        UploadPersonReq req = UploadPersonReq.newBuilder()
                .uploadPersonReqBody(UploadPersonReqBody.newBuilder()
                        .fileContent(new File("Q:\\Downloads\\简历.pdf"))
                        .fileName("简历.pdf")
                        .build())
                .build();

        // 发起请求
        // 如开启了Sdk的token管理功能，就无需调用 RequestOptions.newBuilder().tenantAccessToken("t-xxx").build()来设置租户token了
        UploadPersonResp resp = client.corehr().person().upload(req);

        // 处理服务端错误
        if (!resp.success()) {
            System.out.println(String.format("code:%s,msg:%s,reqId:%s", resp.getCode(), resp.getMsg(), resp.getRequestId()));
            return;
        }

        // 业务数据处理
        System.out.println(Jsons.DEFAULT.toJson(resp.getData()));
    }
}
