package com.base.wedget.imgpic.adapter.inter;

import com.base.wedget.entity.ImageItem;

/**
 * Created by weikailiang on 2020/5/21.
 */

public interface OnItemOrDeleteClickListener extends OnItemClickListener{
    void onDelete(ImageItem item);
}
