package com.example.fragment;

import com.example.tabfragment.R;
import com.example.tabfragment.R.layout;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShareFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View shareView = inflater.inflate(R.layout.share_layout, container,
				false);
		return shareView;
	}

}
