package com.igexin.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;



public class GetuiMsgReceiver extends BroadcastReceiver {
	private final static String TAG = "GetuiMsgReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		switch (bundle.getInt(PushConsts.CMD_ACTION)) {
		case PushConsts.GET_MSG_DATA:
			// 获取透传（payload）数据
			byte[] payload = bundle.getByteArray("payload");
			if (payload != null) {
				String data = new String(payload);
				Log.d(TAG, "Got Payload:" + data);
				// TODO:接收处理透传（payload）数据
				if(GetuiSDK.getInstance().mGetuiListener!=null)
					GetuiSDK.getInstance().mGetuiListener.processMessage(data);
			}
			break;
		case PushConsts.GET_CLIENTID:
			// 获取ClientID(CID)
			String cid = bundle.getString("clientid");
			Log.d(TAG, "Got ClientID:" + cid);
			if(GetuiSDK.getInstance().mGetuiListener!=null)
				GetuiSDK.getInstance().mGetuiListener.resClientid(cid);
			// TODO:
			/*
			 * 第三方应用需要将ClientID 上传到第三方服务器，并且将当前用户帐号和 ClientID
			 * 进行关联，以便以后通过用户帐号查找ClientID 进行消息推送 有些情况下ClientID
			 * 可能会发生变化，为保证获取最新的ClientID，请应用程序 在每次获取ClientID 广播后，都能进行一次关联绑定
			 */
			break;
		case PushConsts.THIRDPART_FEEDBACK:
			// sendMessage接口调用回执
			String appid = bundle.getString("appid");
			String taskid = bundle.getString("taskid");
			String actionid = bundle.getString("actionid");
			String result = bundle.getString("result");
			long timestamp = bundle.getLong("timestamp");

			Log.d(TAG, "appid:" + appid);
			Log.d(TAG, "taskid:" + taskid);
			Log.d(TAG, "actionid:" + actionid);
			Log.d(TAG, "result:" + result);
			Log.d(TAG, "timestamp:" + timestamp);
			break;
		default:
			break;
		}
	}

}
