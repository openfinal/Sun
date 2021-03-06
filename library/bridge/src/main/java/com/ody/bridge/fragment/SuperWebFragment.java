package com.ody.bridge.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

import com.ody.bridge.CallBackFunction;
import com.ody.bridge.Html5EventListener;
import com.ody.bridge.R;
import com.ody.bridge.SuperWebView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuperWebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuperWebFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_URL = "url";

    private String mUrl;
    private SuperWebView mSuperWv;

    public SuperWebFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param url Parameter 1.
     * @return A new instance of fragment SuperWebFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuperWebFragment newInstance(String url) {
        SuperWebFragment fragment = new SuperWebFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_super_web, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mSuperWv = (SuperWebView) view.findViewById(R.id.super_wv);
        mSuperWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
        mSuperWv.setDefaultHtml5EventListener(new Html5EventListener() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (function != null) {
                    function.onCallBack("孙华辉");
                }
            }
        });
        mSuperWv.loadUrl(mUrl);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        mSuperWv.sendEventToHtml5("resume");
        super.onResume();
    }

    @Override
    public void onPause() {
        mSuperWv.sendEventToHtml5("pause");
        super.onPause();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
