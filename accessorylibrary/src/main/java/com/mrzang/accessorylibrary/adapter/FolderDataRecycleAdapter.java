package com.mrzang.accessorylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrzang.accessorylibrary.FileModel;
import com.mrzang.accessorylibrary.ItemClickListener;
import com.mrzang.accessorylibrary.R;
import com.mrzang.accessorylibrary.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 使用遍历文件夹的方式
 * Created by yis on 2018/4/17.
 */

public class FolderDataRecycleAdapter extends BaseAdapter<FileModel, FolderDataRecycleAdapter.ViewHolder> {

    private static final String TYPE_ITEM_CHECK = "TYPE_ITEM_CHECK";

    public FolderDataRecycleAdapter(Context context, ArrayList<FileModel> mds) {
        super(context, mds);
    }

    private ItemClickListener onItemClickListener;

    public void setOnItemClickListener(ItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_searching_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.size() > 0) {
            FileModel fileModel = mds.get(position);
            for (Object o : payloads) {
                if(o instanceof String){
                    String type = (String) o;
                    if (TYPE_ITEM_CHECK.equals(type)) {
                        boolean select = fileModel.isSelect();
                        holder.imgCheck.setSelected(select);
                    }
                }
            }
        } else
            super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    protected void onBindDataViewHolder(ViewHolder holder, int position, final FileModel fileModel) {
        holder.tv_content.setText(fileModel.getFileName());
        holder.tv_size.setText(FileUtil.FormetFileSize(fileModel.getFileLength()));
        holder.tv_time.setText(fileModel.getShootDate());
        boolean select = fileModel.isSelect();
        holder.imgCheck.setSelected(select);
        int fileType = fileModel.getFileType();
        switch (fileType) {
            case FileModel.FileType.FILE_TYPE_MUSIC:
                holder.iv_cover.setImageResource(R.drawable.m_icon_music);
                break;
            case FileModel.FileType.FILE_TYPE_WORD:
                holder.iv_cover.setImageResource(R.drawable.m_icon_doc);
                break;
            case FileModel.FileType.FILE_TYPE_XLS:
                holder.iv_cover.setImageResource(R.drawable.m_icon_xls);
                break;
            case FileModel.FileType.FILE_TYPE_PDF:
                holder.iv_cover.setImageResource(R.drawable.m_icon_ppt);
                break;
            case FileModel.FileType.FILE_TYPE_PPT:
                holder.iv_cover.setImageResource(R.drawable.m_icon_pdf);
                break;
            case FileModel.FileType.FILE_TYPE_TXT:
                holder.iv_cover.setImageResource(R.drawable.m_icon_txt);
                break;
            case FileModel.FileType.FILE_TYPE_ZIP:
                holder.iv_cover.setImageResource(R.drawable.m_icon_zip);
                break;
            default:
                holder.iv_cover.setImageResource(R.drawable.shape_loading_file);
                break;
        }
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.itemClickCallBack(fileModel);
                }
            });
        }
    }

    public void changeItemCheck(String fileId, boolean select) {
        if (mds != null && mds.size() > 0) {
            for (int i = 0, mdsSize = mds.size(); i < mdsSize; i++) {
                FileModel f = mds.get(i);
                if (f.getFileId().equals(fileId)) {
                    f.setSelect(select);
                    notifyItemChanged(i, TYPE_ITEM_CHECK);
                    break;
                }
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rlMain;
        TextView tv_content;
        TextView tv_size;
        TextView tv_time;
        ImageView iv_cover;
        ImageView imgCheck;

        ViewHolder(View itemView) {
            super(itemView);
            rlMain = itemView.findViewById(R.id.rl_main);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_size = itemView.findViewById(R.id.tv_size);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_cover = itemView.findViewById(R.id.iv_cover);
            this.imgCheck = itemView.findViewById(R.id.img_check);
        }
    }

}
