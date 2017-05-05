package bike.circle.comment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bike.circle.app.R;
import bike.circle.dto.TravelNoteZoom;

/**
 * Created by MrH on 2017/5/5.
 */

public class TravelNoteViewHolder extends RecyclerView.ViewHolder{

    private ImageView mPortrait;
    private TextView mNikeName;
    private TextView mContent;
    private ImageView mImage_one;
    private ImageView mImage_two;
    private ImageView mLike;
    private ImageView mComment;
    private TextView mLikeCount;
    private TextView mCommentCount;

    public TravelNoteViewHolder(View itemView) {
        super(itemView);
        mContent = (TextView) itemView.findViewById(R.id.content);
    }

    public void bindView(TravelNoteZoom travelNoteZoom)
    {
        mContent.setText(travelNoteZoom.getPartContent());
    }

}
