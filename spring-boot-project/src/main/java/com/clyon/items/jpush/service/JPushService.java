package com.clyon.items.jpush.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clyon.util.StringUtil;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 第三方通知推送
 * 
 * @ClassName: PushNotification
 * @Description: 通知推送工具类
 * @author: caoxuyang
 * @date: 2018年9月18日 下午4:20:48
 */
@Configuration
@Service
public class JPushService {
	
	private static final Logger log = LoggerFactory.getLogger(JPushService.class);

	/*
	 * private static String masterSecret = "70587d172cac572a7204894e";
	 * 
	 * private static String appKey = "edf1b4a4c7d564f037245cfe";
	 */
	
	@Value("${jpush.masterSecret}")
	private String masterSecret;

	@Value("${jpush.appKey}")
	private String appKey;


	/**
	 * 推送通知给所有用户
	 * 
	 * @param alert 通知内容
	 * @param title 通知标题
	 * @return PushResult
	 */
	@Transactional(rollbackFor = Exception.class)
	public PushPayload buildPushObject_all_all_alert(String title, String alert) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder().addExtra("type", "infomation")
								.setAlert(alert).setTitle(title).build())
						.addPlatformNotification(IosNotification.newBuilder().addExtra("type", "infomation")
								.setAlert(IosAlert.newBuilder().setTitleAndBody(title, null, alert)).build())
						.build())
				.build();
	}

	/**
	 * 极光推送给别名用户
	 * 
	 * @param alias 用户别名
	 * @param alert 通知内容
	 * @param title 通知标题
	 * @return PushResult
	 */
	@Transactional(rollbackFor = Exception.class)
	public PushResult pushToAlias(String alias, String title, String alertMsg) {
		
		if (StringUtil.isBlank(title)) {
			title = "码上服农";
		}

		ClientConfig clientConfig = ClientConfig.getInstance();
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
		PushPayload payload = buildPushObject_android_ios_alias_alert(alias, title, alertMsg);

		try {
			return jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			log.error("Connection error. Should retry later. ", e);
			return null;
		} catch (APIRequestException e) {
			log.error("Error response from JPush server. Should review and fix it. ", e);
			log.info("HTTP Status: " + e.getStatus());
			log.info("Error Code: " + e.getErrorCode());
			log.info("Error Message: " + e.getErrorMessage());
			log.info("Msg ID: " + e.getMsgId());
			return null;
		}
	}
	
	/**
	 * 极光推送给所有用户有标题
	 * 
	 * @param alertMsg 通知内容
	 * @param title 通知标题
	 * @return PushResult
	 */
	@Transactional(rollbackFor = Exception.class)
	public PushResult pushToAll(String title, String alertMsg) {
		System.out.println("masterSecret : "+masterSecret+"appKey : "+  appKey);		
		if (StringUtil.isBlank(title)) {
			title = "码上服农";
		}

		ClientConfig clientConfig = ClientConfig.getInstance();
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
		PushPayload payload = buildPushObject_all_all_alert(alertMsg, title);

		try {
			return jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			log.error("Connection error. Should retry later. ", e);
			return null;
		} catch (APIRequestException e) {
			log.error("Error response from JPush server. Should review and fix it. ", e);
			log.info("HTTP Status: " + e.getStatus());
			log.info("Error Code: " + e.getErrorCode());
			log.info("Error Message: " + e.getErrorMessage());
			log.info("Msg ID: " + e.getMsgId());
			return null;
		}
	}
	
	public PushPayload buildPushObject_android_ios_alias_alert(String alias, String title, String alert) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder().addExtra("type", "infomation")
								.setAlert(alert).setTitle(title).build())
						.addPlatformNotification(IosNotification.newBuilder().addExtra("type", "infomation")
								.setAlert(IosAlert.newBuilder().setTitleAndBody(title, null, alert)).build())
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(false)// true-推送生产环境 false-推送开发环境（测试使用参数）
						.setTimeToLive(90)// 消息在JPush服务器的失效时间（测试使用参数）
						.build())
				.build();
	}

}