package com.czjk.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.java.Log;

/**
 * 阿里云短信发送工具类
 *
 * @author Haotian
 * @version 2.0
 * @date 2020/12/29 10:04
 **/
@Log
public class SMSUtils {

    private static final String ACCESSKEYID = "";
    private static final String ACCESSKEYSECRET = "";

    /**
     * 发送短信验证码
     */
    public static final String VALIDATE_CODE = "SMS_181855484";

    /**
     * 体检预约成功通知
     */
    public static final String ORDER_NOTICE = "SMS_181855484";

    /**
     * 发送短信
     *
     * @param templateCode 短信模板ID。请在控制台模板管理页面模板CODE一列查看。
     * @param phoneNumbers 接收短信的手机号码。国内短信：11位手机号码。
     * @param authCode     验证码
     */
    public static void sendShortMessage(String templateCode, String phoneNumbers, String authCode) {
        DefaultProfile profile = DefaultProfile.getProfile( "cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET );
        IAcsClient client = new DefaultAcsClient( profile );
        CommonRequest request = new CommonRequest();
        request.setMethod( MethodType.POST );
        request.setDomain( "dysmsapi.aliyuncs.com" );
        request.setVersion( "2017-05-25" );
        request.setAction( "SendSms" );
        request.putQueryParameter( "RegionId", "cn-hangzhou" );
        request.putQueryParameter( "PhoneNumbers", phoneNumbers );
        //短信签名名称。请在控制台签名管理页面签名名称一列查看。
        request.putQueryParameter( "SignName", "传智健康" );
        request.putQueryParameter( "TemplateCode", templateCode );
        request.putQueryParameter( "TemplateParam", "{\"code\":\"" + authCode + "\"}" );
        try {
            CommonResponse response = client.getCommonResponse( request );
            log.info( response.getData() );
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}