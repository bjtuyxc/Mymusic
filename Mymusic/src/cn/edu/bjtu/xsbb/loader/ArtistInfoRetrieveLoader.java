package cn.edu.bjtu.xsbb.loader;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import cn.edu.bjtu.xsbb.entity.ArtistInfo;

public class ArtistInfoRetrieveLoader extends AsyncTaskLoader<List<ArtistInfo>> {
	private final String TAG = ArtistInfoRetrieveLoader.class.getSimpleName();

	/** 要从MediaStore检索的列 */
	private final String[] mProjection = new String[] {
			MediaStore.Audio.Artists.ARTIST,
			MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
			MediaStore.Audio.Artists.NUMBER_OF_ALBUMS };

	// 数据库查询相关参数
	private String mSelection = null;
	private String[] mSelectionArgs = null;
	private String mSortOrder = null;

	private ContentResolver mContentResolver = null;

	private List<ArtistInfo> mArtistInfoList = null;

	public ArtistInfoRetrieveLoader(Context context, String selection,
			String[] selectionArgs, String sortOrder) {
		super(context);
		this.mSelection = selection;
		this.mSelectionArgs = selectionArgs;
		if (sortOrder == null) {
			// 默认按艺术家名称排序
			this.mSortOrder = MediaStore.Audio.Artists.NUMBER_OF_TRACKS
					+ " desc";
		}
		this.mSortOrder = sortOrder;
		mContentResolver = context.getContentResolver();
	}

	@Override
	public List<ArtistInfo> loadInBackground() {
		Log.i(TAG, "loadInBackground");
		Cursor cursor = mContentResolver.query(
				MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, mProjection,
				mSelection, mSelectionArgs, mSortOrder);

		List<ArtistInfo> itemsList = new ArrayList<ArtistInfo>();

		// 将数据库查询结果保存到一个List集合中(存放在RAM)
		if (cursor != null) {
			int index_artist = cursor
					.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
			int index_number_of_tracks = cursor
					.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS);
			int index_number_of_albums = cursor
					.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS);
			while (cursor.moveToNext()) {
				ArtistInfo item = new ArtistInfo();
				item.setArtistName(cursor.getString(index_artist));
				item.setNumberOfTracks(cursor.getInt(index_number_of_tracks));
				item.setNumberOfAlbums(cursor.getInt(index_number_of_albums));
				itemsList.add(item);
			}
			cursor.close();
		}
		// 如果没有扫描到媒体文件，itemsList的size为0，因为上面new过了
		return itemsList;
	}

	@Override
	public void deliverResult(List<ArtistInfo> data) {
		Log.i(TAG, "deliverResult");
		if (isReset()) {
			if (data != null) {
				onReleaseResources(data);
			}
		}
		List<ArtistInfo> oldList = data;
		mArtistInfoList = data;

		if (isStarted()) {
			super.deliverResult(data);
		}
		if (oldList != null) {
			onReleaseResources(oldList);
		}
	}

	protected void onReleaseResources(List<ArtistInfo> data) {
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
	public void onCanceled(List<ArtistInfo> data) {
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
		// TODO Auto-generated method stub
		super.onForceLoad();
	}

}
