package com.mrzang.accessorylibrary;

import android.os.Bundle;

import com.mrzang.accessorylibrary.utils.AccessorySuffixUtils;

import java.util.ArrayList;

/**
 * @author : mr.zang
 * description
 * createDate: 2019-12-24 16:30
 */
public class AccessoryBundle {

    public static class Options {

        private final Bundle mOptionsBundle;

        public Options() {
            this.mOptionsBundle = new Bundle();
        }

        /**
         * 最多选择文件个数
         * 默认最多选择9个文件
         */
        public static final String SEL_FILE_MAX_NUM = "SEL_FILE_MAX_NUM";
        private int selFileMaxNum = 9;

        /**
         * 筛选后缀集合
         * 默认 {@link AccessorySuffixUtils#ALL}
         */
        public static final String SCREENING_SUFFIX = "SCREENING_SUFFIX";
        private String[] suffix = AccessorySuffixUtils.ALL;

        /**
         * 之前选择文件 带入 匹配已选
         */
        public static final String OLD_SEL_FILE = "OLD_SEL_FILE";
        private ArrayList<FileModel> oldFileModels;

        public Options setSelFileMaxNum(int selFileMaxNum) {
            mOptionsBundle.putInt(SEL_FILE_MAX_NUM, selFileMaxNum);
            return this;
        }

        public Options setSuffix(String[] suffix) {
            mOptionsBundle.putStringArray(SCREENING_SUFFIX, suffix);
            return this;
        }

        public Options setSuffixColumn(String... suffix) {
            mOptionsBundle.putStringArray(SCREENING_SUFFIX, suffix);
            return this;
        }

        public Options setOldFileModels(ArrayList<FileModel> oldFileModels) {
            mOptionsBundle.putParcelableArrayList(OLD_SEL_FILE, oldFileModels);
            return this;
        }

    }
}
