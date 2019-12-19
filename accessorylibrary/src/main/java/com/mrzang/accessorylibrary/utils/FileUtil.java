package com.mrzang.accessorylibrary.utils;

import android.content.Context;
import android.os.storage.StorageManager;
import com.mrzang.accessorylibrary.FileModel;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class FileUtil {

    /****
     * 计算文件大小
     *
     * @param length
     * @return
     */
    public static String getFileSzie(Long length) {
        if (length >= 1048576) {
            return (length / 1048576) + "MB";
        } else if (length >= 1024) {
            return (length / 1024) + "KB";
        } else if (length < 1024) {
            return length + "B";
        } else {
            return "0KB";
        }
    }

    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 字符串时间戳转时间格式
     *
     * @param timeStamp
     * @return
     */
    public static String getStrTime(String timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        long l = Long.valueOf(timeStamp) * 1000;
        timeString = sdf.format(new Date(l));
        return timeString;
    }

    /**
     * 读取文件的最后修改时间的方法
     */
    public static String getFileLastModifiedTime(File f) {
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }

    /**
     * 获取扩展内存的路径
     *
     * @param mContext
     * @return
     */
    public static String getStoragePath(Context mContext) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean checkSuffix(String fileName, String[] fileSuffix) {
        for (String suffix : fileSuffix) {
            if (fileName != null) {
                if (fileName.toLowerCase().endsWith(suffix)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 文件过滤,将手机中隐藏的文件给过滤掉
     */
    public static File[] fileFilter(File file) {
        File[] files = file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return !pathname.isHidden();
            }
        });
        return files;
    }


    public static FileModel getFileInfoFromFile(File file) {
        FileModel fileModel = new FileModel();
        fileModel.setFileId(UUID.randomUUID().toString());
        fileModel.setFileName(file.getName());
        fileModel.setFilePath(file.getPath());
        fileModel.setFileLength(file.length());
        fileModel.setShootDate(FileUtil.getFileLastModifiedTime(file));
        String fileName = file.getName();
        if (fileName.endsWith(".mp3") || fileName.endsWith(".wav") || fileName.endsWith(".aac")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_MUSIC);
        } else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_WORD);
        } else if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_XLS);
        } else if (fileName.endsWith(".ppt") || fileName.endsWith(".pptx")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_PPT);
        } else if (fileName.endsWith(".pdf") || fileName.endsWith(".pdfx")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_PDF);
        } else if (fileName.endsWith(".txt")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_TXT);
        }
        return fileModel;
    }

    public static void lineFileSuffix(FileModel fileModel, String fileName) {
        if (fileName.endsWith(".mp3") || fileName.endsWith(".wav") || fileName.endsWith(".aac")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_MUSIC);
        } else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_WORD);
        } else if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_XLS);
        } else if (fileName.endsWith(".ppt") || fileName.endsWith(".pptx")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_PPT);
        } else if (fileName.endsWith(".pdf")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_PDF);
        } else if (fileName.endsWith(".txt")) {
            fileModel.setFileType(FileModel.FileType.FILE_TYPE_TXT);
        }
    }
}
