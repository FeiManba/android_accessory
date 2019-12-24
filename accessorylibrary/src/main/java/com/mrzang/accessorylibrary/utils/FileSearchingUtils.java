package com.mrzang.accessorylibrary.utils;

import android.text.TextUtils;

import com.mrzang.accessorylibrary.FileModel;

import java.io.File;
import java.util.ArrayList;

/**
 * @author : mr.zang
 * description 文件检索 缓存类
 * 1.递归检索
 * 2.非递归检索
 * createDate: 2019-12-19 14:17
 */
public final class FileSearchingUtils {

    private FileSearchingUtils() {
    }

    private static FileSearchingUtils instance;

    public static FileSearchingUtils getInstance() {
        if (instance == null) {
            synchronized (FileSearchingUtils.class) {
                instance = new FileSearchingUtils();
            }
        }
        return instance;
    }

    private ArrayList<FileModel> weChatFileModels = new ArrayList<>();

    private ArrayList<FileModel> qqChatFileModels = new ArrayList<>();

    private ArrayList<FileModel> allChatFileModels = new ArrayList<>();

    public ArrayList<FileModel> getWeChatFileModels() {
        return weChatFileModels;
    }

    public ArrayList<FileModel> getQqChatFileModels() {
        return qqChatFileModels;
    }

    public ArrayList<FileModel> getAllChatFileModels() {
        return allChatFileModels;
    }

    public boolean isWeChatSearching() {
        return weChatFileModels.size() != 0;
    }

    public boolean isQQSearching() {
        return qqChatFileModels.size() != 0;
    }

    public boolean isAllSearching() {
        return allChatFileModels.size() != 0;
    }

    public void delCache() {
        weChatFileModels.clear();
        qqChatFileModels.clear();
        allChatFileModels.clear();
    }

    /**
     * 递归文件检索
     *
     * @param dirFile      文件目录
     * @param searchType   0 weChat 1 QQ 2 All
     * @param searchSuffix 所需检索的文件后缀
     */
    public void recursionFile(File dirFile, int searchType, String... searchSuffix) {

        if (dirFile == null) {
            return;
        }

        if (searchSuffix == null || searchSuffix.length == 0) {
            return;
        }

        //得到某个文件夹下所有的文件
        File[] files = dirFile.listFiles();
        //文件为空
        if (files == null) {
            return;
        }

        //遍历当前文件下的所有文件
        for (File file : files) {
            //如果是文件夹
            if (file.isDirectory()) {
                //则递归(方法自己调用自己)继续遍历该文件夹
                recursionFile(file, searchType, searchSuffix);
            } else { //如果不是文件夹 则是文件
                String name = file.getName();
                if (!TextUtils.isEmpty(name)) {
                    for (String suffix : searchSuffix) {
                        if (name.endsWith(suffix)) {
                            if (searchType == 0) {
                                FileModel model = FileUtil.getFileInfoFromFile(file);
                                weChatFileModels.add(model);
                            }

                            if (searchType == 1) {
                                FileModel model = FileUtil.getFileInfoFromFile(file);
                                qqChatFileModels.add(model);
                            }

                            if (searchType == 2) {
                                allChatFileModels.add(FileUtil.getFileInfoFromFile(file));
                            }
                        }
                    }
                }
            }
        }

    }
}
