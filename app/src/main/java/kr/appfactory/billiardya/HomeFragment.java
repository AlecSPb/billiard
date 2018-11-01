package kr.appfactory.billiardya;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  {

    private boolean lastItemVisibleFlag = false;    // 리스트 스크롤이 마지막 셀(맨 바닥)로 이동했는지 체크할 변수
    public  ListView driverMovieListView;
    public List<DriverMovie> driverMovieList;
    private DriverMovieListAdapter driveradapter;
    private boolean mLockListView = false;          // 데이터 불러올때 중복안되게 하기위한 변수

    String target = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&maxResults=3&key=AIzaSyBn4fOG4zKOYVbYtcMtGj8gGsVVpTYb68g&safeSearch=strict&type=video&videoSyndicated=true";


    Activity activity;
    private OnFragmentInteractionListener mListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (Activity) getActivity();
    }
    public HomeFragment() {}

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        // progressBar.setVisibility(View.GONE);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle b) {
        super.onActivityCreated(b);


        String drivertarget = target +"&q=골프+드라이버";
        String woodtarget = target +"&q=골프+우드";
        String irontarget = target +"&q=골프+아이언";
        String wedgetarget = target +"&q=골프+웨지";
        String puttertarget = target +"&q=골프+퍼팅";


        driverMovieListView  = getView().findViewById(R.id.driveronepoint);
        driverMovieList = new ArrayList<DriverMovie>();
        driveradapter = new DriverMovieListAdapter(activity, driverMovieList, this);
        new LoadMovieTask(getActivity(), driverMovieList, driverMovieListView, driveradapter, drivertarget , "main").execute();
        //driverMovieListView.setAdapter(driveradapter);

        Log.d("target", ""+woodtarget);
        driverMovieListView  = getView().findViewById(R.id.woodonepoint);
        driverMovieList = new ArrayList<DriverMovie>();
        driveradapter = new DriverMovieListAdapter(activity, driverMovieList, this);
        new LoadMovieTask(getActivity(), driverMovieList, driverMovieListView, driveradapter, woodtarget , "main").execute();
        //driverMovieListView.setAdapter(driveradapter);


        driverMovieListView  = getView().findViewById(R.id.irononepoint);
        driverMovieList = new ArrayList<DriverMovie>();
        driveradapter = new DriverMovieListAdapter(activity, driverMovieList, this);
        new LoadMovieTask(getActivity(), driverMovieList, driverMovieListView, driveradapter, irontarget , "main").execute();


        driverMovieListView  = getView().findViewById(R.id.wedgeonepoint);
        driverMovieList = new ArrayList<DriverMovie>();
        driveradapter = new DriverMovieListAdapter(activity, driverMovieList, this);
        new LoadMovieTask(getActivity(), driverMovieList, driverMovieListView, driveradapter, wedgetarget , "main").execute();


        driverMovieListView  = getView().findViewById(R.id.putteronepoint);
        driverMovieList = new ArrayList<DriverMovie>();
        driveradapter = new DriverMovieListAdapter(activity, driverMovieList, this);
        new LoadMovieTask(getActivity(), driverMovieList, driverMovieListView, driveradapter, puttertarget , "main").execute();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        //new LoadMovieTask(getContext(), driverMovieList).execute();

        View view=inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

        // new LoadMovieTask(getActivity(), driverMovieList, driverMovieListView, driveradapter, target).cancel(true);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


