package com.example.fragment;

import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

import com.example.adapter.AlbumItemAdapter;
import com.example.tabfragment.MainActivity;
import com.example.tabfragment.R;
import com.example.tabfragment.R.layout;
import com.example.utils.AlbumItem;
import com.example.utils.SongItem;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MusicFragment extends Fragment {
	private ListView album_list_view;

	public static final int PLAY_SONG = 1;
	public static final int PLAY_ALBUM = 2;
	public static final int PAUSE = 0;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			int event = msg.what;
			switch (event) {
			case PLAY_ALBUM:
				AlbumItem album = (AlbumItem) msg.obj;
				((MainActivity) getActivity()).setCurrentPlaying(
						album.getFront_img(), album.getSong_list().get(0)
								.getName(), album.getArtist());
				break;
			case PLAY_SONG:
				SongItem song = (SongItem) msg.obj;
				((MainActivity) getActivity()).setCurrentPlaying(
						song.getFont_img(), song.getName(), song.getArtist());
				break;
			case PAUSE:
				((MainActivity) getActivity()).Pause();
			default:
				break;
			}
		}

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View musicLayout = inflater.inflate(R.layout.music_layout, container,
				false);
		album_list_view = (ListView) musicLayout.findViewById(R.id.album_list);
		String[] songName = { "Rolling in the Deep", "Rumour Has It",
				"Turing Table", "Don't Your Remember", "Set Fire to the Rain",
				"He Won't Go", "Take It All", "I'll be Waiting",
				"One and Only", "Lovesong", "Someone like You" };
		String[] songLength = { "3:49", "3:43", "4:10", "4:03", "4:01", "4:37",
				"3:48", "4:01", "5:48", "5:16", "4:47" };

		List<SongItem> song_list = new ArrayList<SongItem>();

		for (int i = 0; i < songName.length; i++) {
			SongItem item = new SongItem(i + 1, songName[i], songLength[i],
					"Adele", "21", R.drawable.font);
			song_list.add(item);
		}

		AlbumItem album = new AlbumItem(R.drawable.font,R.drawable.back, "21", "Adele",
				song_list);

		String[] songName1 = { "Counting Stars", "If I Lose Myself",
				"Feel Again", "What You Wanted", "I Lived", "Light It Up",
				"Can't Stop", "Au Revoir", "Burning Bridges",
				"Something I Need", "Preacher", "Don't Look Down" };
		String[] songLength1 = { "4:17", "4:01", "3:05", "4:01", "3:55",
				"4.10", "4:09", "4:50", "4:17", "4:01", "4:08", "1:39" };

		List<SongItem> song_list1 = new ArrayList<SongItem>();
		for (int i = 0; i < songName1.length; i++) {
			SongItem item = new SongItem(i + 1, songName1[i], songLength1[i],
					"OneRepublic", "Native", R.drawable.album1);
			song_list1.add(item);
		}

		AlbumItem album1 = new AlbumItem(R.drawable.album1,R.drawable.back1, "Native",
				"OneRepublic", song_list1);

		String[] songName2 = { "One", "I'm a Mess", "Sing", "Don't", "Nina",
				"Photograph", "Bloodstream", "Tenerife Sea", "Runaway",
				"The Man", "Thinking Out Loud", "Afire Love" };
		String[] songLength2 = { "4:13", "4:06", "3:55", "3:39", "3:43",
				"4:17", "4:59", "4:00", "3:26", "4:09", "4:40", "5:14" };
		List<SongItem> song_list2 = new ArrayList<SongItem>();
		for (int i = 0; i < songName2.length; i++) {
			SongItem item = new SongItem(i + 1, songName2[i], songLength2[i],
					"Ed Sheeran", "X", R.drawable.ablum2);
			song_list2.add(item);
		}

		AlbumItem album2 = new AlbumItem(R.drawable.ablum2,R.drawable.back2, "X", "Ed Sheeran",
				song_list2);

		List<AlbumItem> album_list = new ArrayList<AlbumItem>();
		album_list.add(album);
		album_list.add(album1);
		album_list.add(album2);
		album_list.add(album);
		album_list.add(album1);
		album_list.add(album2);

		AlbumItemAdapter adapter = new AlbumItemAdapter(getActivity(),
				album_list, mHandler);
		album_list_view.setAdapter(adapter);
		return musicLayout;
	}

}
