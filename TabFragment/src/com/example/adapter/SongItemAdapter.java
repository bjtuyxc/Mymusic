package com.example.adapter;

import java.util.List;

import com.example.fragment.MusicFragment;
import com.example.tabfragment.R;
import com.example.utils.SongItem;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SongItemAdapter extends BaseAdapter {
	private List<SongItem> list;
	private Context context;
	private LayoutInflater mInflater;
	private int playing = -1;
	private Handler mHandler;

	public SongItemAdapter(Context context, List<SongItem> list, Handler handler) {
		this.list = list;
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.mHandler = handler;
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
		ViewHolder holder;
		SongItem item = list.get(position);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.song_item_layout, null);
			holder = new ViewHolder();
			holder.rl = (RelativeLayout) convertView
					.findViewById(R.id.item_layout);
			holder.id = (TextView) convertView.findViewById(R.id.song_id);
			holder.name = (TextView) convertView.findViewById(R.id.song_name);
			holder.length = (TextView) convertView.findViewById(R.id.song_time);
			holder.play = (ImageView) convertView.findViewById(R.id.play_btn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (playing == item.getSongId()) {
			holder.id.setTextColor(context.getResources()
					.getColor(R.color.pink));
			holder.name.setTextColor(context.getResources().getColor(
					R.color.pink));
			holder.length.setTextColor(context.getResources().getColor(
					R.color.pink));
			holder.play.setVisibility(View.VISIBLE);
		} else {
			holder.id.setTextColor(context.getResources().getColor(
					R.color.white));
			holder.name.setTextColor(context.getResources().getColor(
					R.color.white));
			holder.length.setTextColor(context.getResources().getColor(
					R.color.white));
			holder.play.setVisibility(View.INVISIBLE);
		}
		holder.rl.setTag(item);
		holder.id.setText(item.getSongId() + ".");
		holder.name.setText(item.getName());
		holder.length.setText(item.getLength());

		holder.rl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SongItem item = (SongItem) v.getTag();
				int id = item.getSongId();
				if (id == playing) {
					playing = -1;
					mHandler.sendMessage(mHandler
							.obtainMessage(MusicFragment.PAUSE));
				} else {
					playing = id;
					mHandler.sendMessage(mHandler.obtainMessage(
							MusicFragment.PLAY_SONG, item));
				}
				notifyDataSetChanged();
			}
		});

		return convertView;
	}

	private class ViewHolder {
		private RelativeLayout rl;
		private TextView name, length, id;
		private ImageView play;

	}
}
