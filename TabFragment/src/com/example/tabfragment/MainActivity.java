package com.example.tabfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.fragment.MessageFragment;
import com.example.fragment.MusicFragment;
import com.example.fragment.ShareFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private MessageFragment messageFragment;
	private MusicFragment musicFragment;
	private ShareFragment shareFragment;
	private FragmentManager fragmentManager;
	private PagerSlidingTabStrip tabs;
	private DisplayMetrics dm;
	private SlidingMenu menu;
	private ImageButton play_pasue;

	private ImageView player_img;
	private TextView player_name, player_artist;

	private boolean isPlaying;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initLeftMenu();

		fragmentManager = getSupportFragmentManager();

		dm = getResources().getDisplayMetrics();
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager.setAdapter(new MyPagerAdapter(fragmentManager));
		tabs.setViewPager(pager);
		setTabsValue();
		setPlayFunction();
		player_img = (ImageView) findViewById(R.id.album_font);
		player_name = (TextView) findViewById(R.id.song_name);
		player_artist = (TextView) findViewById(R.id.artist);

	}

	private void initLeftMenu() {
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setBehindOffset(500);
		menu.setFadeDegree(0.35f);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setShadowWidth(18);
		menu.setMenu(R.layout.left_menu);
		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		initMenuListeners();
	}

	private void initMenuListeners() {
		LinearLayout login, like, ranking, recent, local, folder, cloud;
		login = (LinearLayout) findViewById(R.id.login);
		like = (LinearLayout) findViewById(R.id.like);
		ranking = (LinearLayout) findViewById(R.id.ranking);
		recent = (LinearLayout) findViewById(R.id.recent);
		local = (LinearLayout) findViewById(R.id.local);
		folder = (LinearLayout) findViewById(R.id.folder);
		cloud = (LinearLayout) findViewById(R.id.cloud);
		login.setOnClickListener(this);
		like.setOnClickListener(this);
		ranking.setOnClickListener(this);
		recent.setOnClickListener(this);
		local.setOnClickListener(this);
		folder.setOnClickListener(this);
		cloud.setOnClickListener(this);
	}

	public void setTabsValue() {
		tabs.setShouldExpand(true);
		tabs.setDividerColor(Color.TRANSPARENT);
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, dm));
		tabs.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, dm));
		tabs.setTextColor(getResources().getColor(R.color.black));
		tabs.setSelectedTextColor(getResources().getColor(R.color.purple));
		tabs.setIndicatorColor(getResources().getColor(R.color.purple));
		tabs.setTabBackground(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] titles = { "–≈œ¢", "“Ù¿÷", "∑÷œÌ" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (messageFragment == null) {
					messageFragment = new MessageFragment();
				}
				return messageFragment;
			case 1:
				if (musicFragment == null) {
					musicFragment = new MusicFragment();
				}
				return musicFragment;
			case 2:
				if (shareFragment == null) {
					shareFragment = new ShareFragment();
				}
				return shareFragment;
			default:
				return null;
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}

		@Override
		public int getCount() {
			return titles.length;
		}

	}

	private void setPlayFunction() {
		play_pasue = (ImageButton) findViewById(R.id.play_pause);

		play_pasue.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isPlaying) {
					play_pasue.setImageResource(R.drawable.play);
				} else {
					play_pasue.setImageResource(R.drawable.pause);
				}
				isPlaying = !isPlaying;
			}
		});

	}

	public void Pause() {
		play_pasue.performClick();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			Toast.makeText(MainActivity.this, "Logining...", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.like:
			Toast.makeText(MainActivity.this, "That was a good choice",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.ranking:
			Toast.makeText(MainActivity.this, "Lastest Ranking...",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.recent:
			Toast.makeText(MainActivity.this, "I just Listen...",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.local:
			Toast.makeText(MainActivity.this, "Importing the list...",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.folder:
			Toast.makeText(MainActivity.this, "Lets see what I got here",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.cloud:
			Toast.makeText(MainActivity.this, "Music on the Cloud...",
					Toast.LENGTH_SHORT).show();
		default:
			break;
		}
		menu.toggle();
	}

	public void setCurrentPlaying(int imgId, String name, String artist) {
		play_pasue.setImageResource(R.drawable.pause);
		isPlaying = true;
		player_img.setImageResource(imgId);
		player_name.setText(name);
		player_artist.setText(artist);
	}
}
