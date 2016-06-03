package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.MessageAdapter;
import com.example.tabfragment.R;
import com.example.utils.MessageItem;

public class MessageFragment extends Fragment {
	private ListView listView;
	private MessageAdapter adapter;
	private List<MessageItem> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.message_layout,
				container, false);
		listView = (ListView) messageLayout.findViewById(R.id.message_list);
		list = new ArrayList<MessageItem>();
		for (int i = 0; i < 20; i++) {
			MessageItem item = new MessageItem();
			if (i % 3 == 0) {
				item.setImgSrc(R.drawable.p1);
				item.setTitle("John");
				item.setContent("向你推荐了---Set Fire to the Rain");
				item.setTime("昨天");
				list.add(item);
			}
			if (i % 3 == 1) {
				item.setImgSrc(R.drawable.p2);
				item.setTitle("William");
				item.setContent("正在收听---Sing");
				item.setTime("早上11:30");
				list.add(item);
			}
			if (i % 3 == 2) {
				item.setImgSrc(R.drawable.p3);
				item.setTitle("Max");
				item.setContent("收藏了---Counting Stars");
				item.setTime("下午17:04");
				list.add(item);
			}
		}

		adapter = new MessageAdapter(getActivity(), list, listView);
		listView.setAdapter(adapter);

		return messageLayout;
	}

}
