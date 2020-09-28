package com.example.adamnagy.offlineviewer;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

public class WebViewTTest extends WebViewClient {

    private Context mContext;

    public WebViewTTest(Context context) {
        mContext = context;
    }

    public WebResourceResponse shouldInterceptRequest (WebView view,
                                                       WebResourceRequest request) {
        try {
            Uri uri = request.getUrl();
            String path = uri.getPath();
            if (path.startsWith("/android_asset/")) {
                try {
                    AssetManager assetManager = mContext.getAssets();
                    String relPath = path.replace("/android_asset/", "").replace("gz", "gz.mp3");
                    InputStream stream = assetManager.open(relPath);
                    return new WebResourceResponse(null, null, stream);
                } catch (IOException ex) {
                    String str = ex.getMessage();
                }
            }
        } catch (Exception ex) { }

        return null;
    }
}
