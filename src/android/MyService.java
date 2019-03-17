package com.red_folder.phonegap.plugin.backgroundservice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;
// import android.webkit.WebView;
// import android.webkit.WebChromeClient;
import android.view.WindowManager;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;

public class MyService extends BackgroundService {

	private final static String TAG = MyService.class.getSimpleName();

	private String mHelloTo = "Kumaran ";

	@Override
	protected JSONObject doWork() {
		JSONObject result = new JSONObject();

		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String now = df.format(new Date(System.currentTimeMillis()));

			String msg = "Hello " + this.mHelloTo + " - its currently " + now;
			result.put("Message", msg);

			// WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
			// CordovaBackground cordovaBackground = new CordovaBackground(this, wm);
			// String url = "file:///android_asset/www/index.html#/app/realtorhome";
			// cordovaBackground.loadUrl(url);

			// WebView myWebView = (WebView) findViewById("com.juniper.android");
			// Log.d("WebviewObject is : " + myWebView);
			// myWebView.setWebChromeClient(new WebChromeClient() {
			// public void onConsoleMessage(String message, int lineNumber, String sourceID)
			// {
			// Log.d("MyApplication", message + " -- From line " + lineNumber + " of " +
			// sourceID);
			// }
			// });

			Log.d(TAG, msg);
		} catch (JSONException e) {
		}

		return result;
	}

	@Override
	protected JSONObject getConfig() {
		JSONObject result = new JSONObject();

		try {
			result.put("HelloTo", this.mHelloTo);
		} catch (JSONException e) {
		}

		return result;
	}

	@Override
	protected void setConfig(JSONObject config) {
		try {
			if (config.has("HelloTo"))
				this.mHelloTo = config.getString("HelloTo");
		} catch (JSONException e) {
		}

	}

	@Override
	protected JSONObject initialiseLatestResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onTimerEnabled() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onTimerDisabled() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);

		params.gravity = Gravity.TOP | Gravity.START;
		params.x = 0;
		params.y = 0;
		params.width = 0;
		params.height = 0;

		final WebView wv = new WebView(this);

		wv.setWebViewClient(new WebViewClient() {

			@Override
			public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
				Log.d("Error", "loading web view: request: " + request + " error: " + error);
			}

			@Override
			public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

				if (request.getUrl().toString().contains("/endProcess")) {

					windowManager.removeView(wv);

					wv.post(new Runnable() {
						@Override
						public void run() {
							wv.destroy();
						}
					});
					stopSelf();
					return new WebResourceResponse("bgsType", "someEncoding", null);
				} else {
					return null;
				}
			}
		});
		wv.loadUrl("file:///android_asset/www/index.html");
		Log.d(TAG, "IndigoSky loaded");
		windowManager.addView(wv, params);
		return super.onStartCommand(intent, flags, startId);
	}

}
