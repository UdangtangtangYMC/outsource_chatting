package com.lodong.android.neighborcommunication.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

public class AppDataDelete {
    private static final String TAG = AppDataDelete.class.getSimpleName();
    public static void clearApplicationData(Context context) {
        File cache = context.getCacheDir();
        //Data까지 지우길 원하므로 캐시폴더의 부모폴더 까지 호출한다.
        File appDir = new File(cache.getParent());
        //부모폴더가 존재하는지 여부 확인
        if(appDir.exists()){
            //부모폴더가 존재한다면 부모폴더의 자식 파일리스트를 호출
            String[] children = appDir.list();
            //부모폴더의 자식 파일리스트를 반복문으로 호출해 삭제함수를 호출한다.
            for(String child : children){
                Log.d(TAG, child);
                if(!TextUtils.equals(child, "lib")){
                    deleteCache(new File(appDir, child));
                }
            }
        }
    }
    public static boolean deleteCache(File dir){
        //File이 Null 인지 Null이 아니라면 폴더인지 체크
        if(dir != null && dir.isDirectory()){
            //Null도 아니고 폴더도 아니라면 폴더안 파일 리스트를 호출
            String[] children = dir.list();
            //파일 리스트를 반복문으로 호출
            for(String child : children){
                //파일 리스트중 폴더가 존재할 수 있기 때문에 재귀호출
                boolean isSuccess = deleteCache(new File(dir, child));
                if(!isSuccess){
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
