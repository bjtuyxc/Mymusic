package com.example.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tabfragment.R;
import com.example.utils.CycleImageView;
import com.example.utils.MessageItem;

public class MessageAdapter extends BaseAdapter {

	private Activity mActivity;
	private List<MessageItem> list;
	private ListView listView;

	public MessageAdapter(Activity activity, List<MessageItem> list,
			ListView listView) {
		this.mActivity = activity;
		this.list = list;
		this.listView = listView;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MessageViewHolder holder;
		MessageItem item = list.get(position);
		if (convertView == null) {
			convertView = mActivity.getLayoutInflater().inflate(
					R.layout.message_item_layout, null);
			holder = new MessageViewHolder();
			holder.cv = (CycleImageView) convertView
					.findViewById(R.id.cycle_img);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			convertView.setTag(holder);
		} else {
			holder = (MessageViewHolder) convertView.getTag();
		}
		holder.cv.setImageResource(item.getImgSrc());
		holder.cv.setDrawType(CycleImageView.TYPE_CYCLE);
		holder.title.setText(item.getTitle());
		holder.content.setText(item.getContent());
		holder.time.setText(item.getTime());

		convertView.setTag(holder);
		return convertView;
	}

	public class MessageViewHolder {
		CycleImageView cv;
		TextView title, content, time;
	}

}
