package com.igexin.push;

import android.content.Context;

import com.igexin.sdk.PushManager;


public class GetuiSDK {
	private static GetuiSDK instance;
	protected GetuiListener mGetuiListener;
	
	private GetuiSDK() {
	};

	public static GetuiSDK getInstance() {
		if (instance == null) {
			instance = new GetuiSDK();
		}
		return instance;
	}

	public void initialize(Context context) {
		PushManager.getInstance().initialize(context);
	}
	
	public void setGetuiListener(GetuiListener listener){
		this.mGetuiListener = listener;
	}
	
	public interface GetuiListener{
		public void resClientid(String clientid);
		
		public void processMessage(String Message);
	}
}
