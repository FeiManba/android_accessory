package com.mrzang.accessorylibrary;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.IntDef;

import com.mrzang.accessorylibrary.utils.DateUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : mr.zang
 * description
 * createDate: 2019-12-19 14:20
 */
public class FileModel implements Comparable<FileModel>, Parcelable {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FileType.FILE_TYPE_IMG, FileType.FILE_TYPE_VIDEO, FileType.FILE_TYPE_OTHER,
            FileType.FILE_TYPE_SHOW_CAMERA, FileType.FILE_TYPE_WORD, FileType.FILE_TYPE_XLS,
            FileType.FILE_TYPE_PPT, FileType.FILE_TYPE_PDF, FileType.FILE_TYPE_ZIP, FileType.FILE_TYPE_MUSIC,
            FileType.FILE_TYPE_TXT, FileType.FILE_TYPE_AAC, FileType.FILE_TYPE_LINK})
    public @interface FileType {
        //0 图片 1 视频 2 其他{添加file} 3 拍照/录制 4 word 5 xls 6 ppt 7 pdf 8 zip 9 music 10 txt 11 aac 12 link
        int FILE_TYPE_IMG = 0;
        int FILE_TYPE_VIDEO = 1;
        int FILE_TYPE_OTHER = 2;
        int FILE_TYPE_SHOW_CAMERA = 3;
        int FILE_TYPE_WORD = 4;
        int FILE_TYPE_XLS = 5;
        int FILE_TYPE_PPT = 6;
        int FILE_TYPE_PDF = 7;
        int FILE_TYPE_ZIP = 8;
        int FILE_TYPE_MUSIC = 9;
        int FILE_TYPE_TXT = 10;
        int FILE_TYPE_AAC = 11;
        int FILE_TYPE_LINK = 12;
    }

    @Override
    public int compareTo(FileModel o) {
        int sortType = getSortType();
        if (sortType == 1) {
            //文件类型排序
            try {
                int fileType = getFileType();
                int fileType1 = o.getFileType();
                if (fileType >= fileType1) {
                    return 1;
                } else return -1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                String shootDate = getShootDate();
                String shootDate1 = o.getShootDate();
                long logTime1 = DateUtils.stringtoDate(shootDate, DateUtils.FORMAT_ONE).getTime();
                long logTime2 = DateUtils.stringtoDate(shootDate1, DateUtils.FORMAT_ONE).getTime();
                if (logTime1 > logTime2) return -1;
                else if (logTime1 < logTime2) return 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    /**
     * 文件Id
     */
    private String fileId;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件长度
     */
    private long fileLength;
    /**
     * 文件类型
     */
    @FileType
    private int fileType;
    /**
     * 缩略图路径
     */
    private String thumbnail;
    /**
     * 是否选中
     */
    private boolean isSelect = false;
    /**
     * 拍摄时间
     */
    private String shootDate;
    /**
     * 维度
     */
    private String latitude;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 是否是本地资源
     */
    private boolean isLocal;

    /**
     * 附加的Tag
     */
    private String Tag;

    //视频缩略图 ->第一帧
    private Bitmap mBitmap;
    /**
     * 如果是视频 视频时间长度
     */
    private long videoTime;
    /**
     * 是否是网络资源
     */
    private boolean isServerSource;
    /**
     * 是否是临时资源
     * 编辑
     * 裁剪
     */
    private boolean isTempFile;

    /**
     * 相册子元素 数量
     */
    private int childFileCount;

    private int width;
    private int height;

    /**
     * 文件修改时间记录
     */
    private String fileLastModifiedTime;

    /**
     * 排序方式 0 时间 1 类型
     */
    private int sortType;

    /**
     * 是否 录音播放
     */
    private boolean isVoicePlay;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getShootDate() {
        return shootDate;
    }

    public void setShootDate(String shootDate) {
        this.shootDate = shootDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public long getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(long videoTime) {
        this.videoTime = videoTime;
    }

    public boolean isServerSource() {
        return isServerSource;
    }

    public void setServerSource(boolean serverSource) {
        isServerSource = serverSource;
    }

    public boolean isTempFile() {
        return isTempFile;
    }

    public void setTempFile(boolean tempFile) {
        isTempFile = tempFile;
    }

    public int getChildFileCount() {
        return childFileCount;
    }

    public void setChildFileCount(int childFileCount) {
        this.childFileCount = childFileCount;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFileLastModifiedTime() {
        return fileLastModifiedTime;
    }

    public void setFileLastModifiedTime(String fileLastModifiedTime) {
        this.fileLastModifiedTime = fileLastModifiedTime;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public boolean isVoicePlay() {
        return isVoicePlay;
    }

    public void setVoicePlay(boolean voicePlay) {
        isVoicePlay = voicePlay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fileId);
        dest.writeString(this.filePath);
        dest.writeString(this.fileName);
        dest.writeLong(this.fileLength);
        dest.writeInt(this.fileType);
        dest.writeString(this.thumbnail);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        dest.writeString(this.shootDate);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeByte(this.isLocal ? (byte) 1 : (byte) 0);
        dest.writeString(this.Tag);
        dest.writeParcelable(this.mBitmap, flags);
        dest.writeLong(this.videoTime);
        dest.writeByte(this.isServerSource ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTempFile ? (byte) 1 : (byte) 0);
        dest.writeInt(this.childFileCount);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.fileLastModifiedTime);
        dest.writeInt(this.sortType);
        dest.writeByte(this.isVoicePlay ? (byte) 1 : (byte) 0);
    }

    public FileModel() {
    }

    protected FileModel(Parcel in) {
        this.fileId = in.readString();
        this.filePath = in.readString();
        this.fileName = in.readString();
        this.fileLength = in.readLong();
        this.fileType = in.readInt();
        this.thumbnail = in.readString();
        this.isSelect = in.readByte() != 0;
        this.shootDate = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.isLocal = in.readByte() != 0;
        this.Tag = in.readString();
        this.mBitmap = in.readParcelable(Bitmap.class.getClassLoader());
        this.videoTime = in.readLong();
        this.isServerSource = in.readByte() != 0;
        this.isTempFile = in.readByte() != 0;
        this.childFileCount = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
        this.fileLastModifiedTime = in.readString();
        this.sortType = in.readInt();
        this.isVoicePlay = in.readByte() != 0;
    }

    public static final Parcelable.Creator<FileModel> CREATOR = new Parcelable.Creator<FileModel>() {
        @Override
        public FileModel createFromParcel(Parcel source) {
            return new FileModel(source);
        }

        @Override
        public FileModel[] newArray(int size) {
            return new FileModel[size];
        }
    };
}
