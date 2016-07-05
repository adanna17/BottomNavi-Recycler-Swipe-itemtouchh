package kr.co.mash_up.bottomnavi_recycler.Home;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.mash_up.bottomnavi_recycler.Interface.ItemTouchHelperCallback;
import kr.co.mash_up.bottomnavi_recycler.R;


public class HomeFragment extends Fragment{


    private RecyclerView recyclerView;
    private HomeRecycleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;
    private ItemTouchHelper mItemTouchHelper;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initList(view);
        return view;
    }

    private void initList(View view) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setColorSchemeColors(Color.RED,Color.GREEN, Color.BLUE);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new HomeRecycleAdapter(getActivity(), refreshLayout);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
