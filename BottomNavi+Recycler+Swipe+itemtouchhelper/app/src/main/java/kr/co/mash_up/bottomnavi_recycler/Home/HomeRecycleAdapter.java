package kr.co.mash_up.bottomnavi_recycler.Home;

import android.content.Context;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import kr.co.mash_up.bottomnavi_recycler.Interface.ItemTouchHelperAdapter;
import kr.co.mash_up.bottomnavi_recycler.Interface.MyEventListener;
import kr.co.mash_up.bottomnavi_recycler.R;

public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter{

    private Context mContext;
    private List<UserData> mDataset;
    private SwipeRefreshLayout refreshlayout;

    public HomeRecycleAdapter(Context context, SwipeRefreshLayout refreshlayout){
        this.mContext = context;
        this.refreshlayout = refreshlayout;
        this.mDataset = new ArrayList<>();
        setData();
    }

    public void setData(){
        mDataset.clear();
        for (int i = 1; i <= 20; i++) {
            mDataset.add(new UserData(String.valueOf(i)));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_list_item, parent, false);
        return  new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof HomeViewHolder) {
            ((HomeViewHolder) holder).update(mDataset.get(position));
            ((HomeViewHolder) holder).setListener(new MyEventListener() {
                @Override
                public void onItemClick(UserData data) {
                    Toast.makeText(mContext, data.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refresh();
                }
            });
        }
    }

    private void refresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataset.add(0,new UserData("hello"));
                HomeRecycleAdapter.this.notifyDataSetChanged();
                Toast.makeText(mContext, "refresh!!", Toast.LENGTH_SHORT).show();
                refreshlayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onItemDismiss(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }
}
