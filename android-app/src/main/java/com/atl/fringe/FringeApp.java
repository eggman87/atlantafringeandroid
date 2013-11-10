package com.atl.fringe;

import android.app.Application;
import android.os.Environment;
import com.nostra13.universalimageloader.cache.disc.impl.TotalSizeLimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:26 AM
 */
public class FringeApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                        //.showStubImage(R.drawable.empty)
                        //.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .build();

        File cacheDir;
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(Environment.getDownloadCacheDirectory(), "images");
        } else {
            cacheDir = this.getCacheDir();
        }
        cacheDir.mkdirs();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(6)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .discCache(new TotalSizeLimitedDiscCache(cacheDir,(6 * 1024 * 1024)))
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(config);

    }
}
