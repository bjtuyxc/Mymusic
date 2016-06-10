package cn.edu.bjtu.xsbb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.bjtu.xsbb.util.Constant;
import cn.edu.bjtu.xsbb.mymusic.R;

public class FrameAlbumFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.frame_for_nested_fragment,
				container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Bundle args = new Bundle();
		args.putString(Constant.PARENT, this.getClass().getSimpleName());

		getChildFragmentManager()
				.beginTransaction()
				.replace(
						R.id.frame_for_nested_fragment,
						Fragment.instantiate(getActivity(),
								AlbumBrowserFragment.class.getName(), args))
				.commit();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

	}
}
