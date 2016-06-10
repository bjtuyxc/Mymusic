package cn.edu.bjtu.xsbb.loader;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Albums;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import cn.edu.bjtu.xsbb.entity.AlbumInfo;


public class AlbumInfoRetrieveLoader extends AsyncTaskLoader<List<AlbumInfo>> {
	private final String TAG = AlbumInfoRetrieveLoader.class.getSimpleName();

	/** 要从MediaStore检索的列 */
	private final String[] mProjection = new String[] { Albums.ALBUM,
			Albums.NUMBER_OF_SONGS, Albums._ID, Albums.ALBUM_ART };

	// 数据库查询相关参数
	private String mSelection = null;
	private String[] mSelectionArgs = null;
	private String mSortOrder = null;

	private ContentResolver mContentResolver = null;

	private List<AlbumInfo> mArtistInfoList = null;

	public AlbumInfoRetrieveLoader(Context context, String selection,
			String[] selectionArgs, String sortOrder) {
		super(context);
		this.mSelection = selection;
		this.mSelectionArgs = selectionArgs;
		if (sortOrder == null) {
			// 默认按歌曲数目倒序排序
			this.mSortOrder = MediaStore.Audio.Artists.NUMBER_OF_TRACKS
					+ " desc";
		}
		this.mSortOrder = sortOrder;
		mContentResolver = context.getContentResolver();
	}

	@Override
	public List<AlbumInfo> loadInBackground() {
		Log.i(TAG, "loadInBackground");
		Cursor cursor = mContentResolver.query(Albums.EXTERNAL_CONTENT_URI,
				mProjection, mSelection, mSelectionArgs, mSortOrder);

		List<AlbumInfo> itemsList = new ArrayList<AlbumInfo>();

		// 将数据库查询结果保存到一个List集合中(存放在RAM)
		if (cursor != null) {
			int index_album_name = cursor.getColumnIndex(Albums.ALBUM);
			int index_album_id = cursor.getColumnIndex(Albums._ID);
			int index_num_of_songs = cursor
					.getColumnIndex(Albums.NUMBER_OF_SONGS);
			int index_art_work = cursor.getColumnIndex(Albums.ALBUM_ART);

			while (cursor.moveToNext()) {
				AlbumInfo item = new AlbumInfo();
				item.setAlbumId(cursor.getInt(index_album_id));
				item.setAlbumName(cursor.getString(index_album_name));
				item.setNumberOfSongs(cursor.getInt(index_num_of_songs));
				item.setArtWork(cursor.getString(index_art_work));
				itemsList.add(item);
			}
			cursor.close();
		}
		// 如果没有扫描到媒体文件，itemsList的size为0，因为上面new过了
		return itemsList;
	}

	@Override
	public void deliverResult(List<AlbumInfo> data) {
		Log.i(TAG, "deliverResult");
		if (isReset()) {
			if (data != null) {
				onReleaseResources(data);
			}
		}
		List<AlbumInfo> oldList = data;
		mArtistInfoList = data;

		if (isStarted()) {
			super.deliverResult(data);
		}

		if (oldList != null) {
			onReleaseResources(oldList);
		}
	}

	protected void onReleaseResources(List<AlbumInfo> data) {
		Log.i(TAG, "onReleaseResources");
	}

	@Override
	protected void onStartLoading() {
		Log.i(TAG, "onStartLoading");
		if (mArtistInfoList != null) {

			deliverResult(mArtistInfoList);
		}

		forceLoad();
	}

	@Override
	protected void onStopLoading() {
		Log.i(TAG, "onStartLoading");
		super.onStopLoading();
		cancelLoad();
	}

	@Override
	public void onCanceled(List<AlbumInfo> data) {
		super.onCanceled(data);
		Log.i(TAG, "onCanceled");
		onReleaseResources(data);
	}

	@Override
	protected void onReset() {
		super.onReset();
		Log.i(TAG, "onReset");
		onStopLoading();
		if (mArtistInfoList != null) {
			onReleaseResources(mArtistInfoList);
			mArtistInfoList = null;
		}
	}

	@Override
	protected void onForceLoad() {
		super.onForceLoad();
	}

}
