package kr.co.mash_up.bottomnavi_recycler.Home;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.co.mash_up.bottomnavi_recycler.Interface.ItemTouchHelperViewHolder;
import kr.co.mash_up.bottomnavi_recycler.Interface.MyEventListener;
import kr.co.mash_up.bottomnavi_recycler.R;


public class HomeViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

    TextView mTextView;
    View view;

    private MyEventListener listener;

    public HomeViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;

        mTextView = (TextView) itemView.findViewById(R.id.layout_item_demo_title);
    }

    public void setListener(MyEventListener listener){
        this.listener = listener;
    }

    public void update(final UserData userData){
        mTextView.setText(userData.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(userData);
            }
        });
    }

    @Override
    public void onItemSelected() {
        view.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        view.setBackgroundColor(0);
    }
}
