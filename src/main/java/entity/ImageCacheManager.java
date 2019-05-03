package entity;


import utils.Config;
import utils.Log;
import utils.Util;

import java.io.File;

public class ImageCacheManager {

    private final String TAG = ImageCacheManager.class.getSimpleName();

    private String cacheDirPath = Util.getUserHomeDir() + "\\cn.frankliu.ees";
    private File cacheDir;
    private static ImageCacheManager instance;
    private ImageCacheManager(){
        cacheDir = new File(cacheDirPath);
        if(!cacheDir.exists()){
            if(!cacheDir.mkdir()){
                Log.e(TAG, "make image cache dir fail!");
            }
        }
    }

    public static ImageCacheManager getInstance(){
        if(instance == null){
            instance = new ImageCacheManager();
        }
        return instance;
    }

    public File newImageFile(){
        String fileName = cacheDirPath + String.valueOf(System.currentTimeMillis()) + "." + Config.DEFAULT_IMAGE_FORMAT;
        return new File(fileName);
    }
}
