package com.example.utils;

import com.example.tabfragment.R;
import com.example.tabfragment.R.drawable;
import com.example.tabfragment.R.string;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class PlusActionProvider extends ActionProvider {
	private Context context;

	public PlusActionProvider(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View onCreateActionView() {
		return null;
	}

	@Override
	public boolean hasSubMenu() {
		return true;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		subMenu.clear();
		subMenu.add(context.getString(R.string.upload))
				.setIcon(R.drawable.upload)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						return false;
					}
				});
		subMenu.add(context.getString(R.string.holder))
				.setIcon(R.drawable.place_holder)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						return false;
					}
				});
		subMenu.add(context.getString(R.string.open)).setIcon(R.drawable.open)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						return false;
					}
				});
		subMenu.add(context.getString(R.string.delete))
				.setIcon(R.drawable.delete)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						return false;
					}
				});
		subMenu.add(context.getString(R.string.setting))
				.setIcon(R.drawable.setting)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						return false;
					}
				});
	}
}
