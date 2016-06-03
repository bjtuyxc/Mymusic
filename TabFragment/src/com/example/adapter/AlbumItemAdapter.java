package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.MusicFragment;
import com.example.tabfragment.R;
import com.example.utils.AlbumItem;

public class AlbumItemAdapter extends BaseAdapter {

	private Context context;
	private List<AlbumItem> list;
	private LayoutInflater mInflater;
	private Handler mHandler;

	public AlbumItemAdapter(Context context, List<AlbumItem> list,
			Handler handler) {
		this.context = context;
		this.list = list;
		this.mHandler = handler;
		mInflater = LayoutInflater.from(context);
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
		AlbumItem item = list.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.album_item_layout, null);
			holder.back = (RelativeLayout) convertView
					.findViewById(R.id.back_layout);
			holder.font = (ImageView) convertView.findViewById(R.id.font_img);
			holder.small_font = (ImageView) convertView
					.findViewById(R.id.small_font_img);
			holder.album_name = (TextView) convertView
					.findViewById(R.id.album_name);
			holder.artist = (TextView) convertView
					.findViewById(R.id.artist_name);
			holder.song_list = (ListView) convertView
					.findViewById(R.id.song_list);
			holder.play_all = (Button) convertView.findViewById(R.id.paly_all);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.font.setImageResource(item.getFront_img());
		holder.back.setBackgroundResource(item.getBack_img());
		holder.small_font.setImageResource(item.getFront_img());
		holder.album_name.setText(item.getAlbum_name());
		holder.artist.setText(item.getArtist());

		if (position % 3 == 0) {
			holder.play_all.setBackgroundResource(R.drawable.play_pink);
		}
		if (position % 3 == 1) {
			holder.play_all.setBackgroundResource(R.drawable.play_blue);
		}
		if (position % 3 == 2) {
			holder.play_all.setBackgroundResource(R.drawable.play_green);
		}
		holder.play_all.setTag(item);
		holder.play_all.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlbumItem item = (AlbumItem) v.getTag();
				// Toast.makeText(context, "Play all-->" + item.getAlbum_name(),
				// Toast.LENGTH_SHORT).show();
				mHandler.sendMessage(mHandler.obtainMessage(
						MusicFragment.PLAY_ALBUM, item));

			}
		});
		SongItemAdapter adapter = new SongItemAdapter(context,
				item.getSong_list(), mHandler);
		holder.song_list.setAdapter(adapter);
		return convertView;
	}

	private class ViewHolder {
		RelativeLayout back;
		ImageView font, small_font;
		TextView album_name, artist;
		ListView song_list;
		Button play_all;
	}
}
