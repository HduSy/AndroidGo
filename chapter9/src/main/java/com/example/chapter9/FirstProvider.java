package com.example.chapter9;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class FirstProvider extends ContentProvider {

	@Override
	public boolean onCreate() {
		System.out.println("===onCreate方法被调用===");
		return true;
	}

	@Nullable
	@Override
	public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
		System.out.println(uri.toString() + "===query方法被调用===");
		System.out.println("where参数为:" + selection);
		return null;
	}

	@Nullable
	@Override
	public String getType(@NonNull Uri uri) {
		return null;
	}

	@Nullable
	@Override
	public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
		System.out.println(uri.toString() + "===insert方法被调用===");
		System.out.println("values参数为:" + values);
		return null;
	}

	@Override
	public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
		System.out.println(uri.toString() + "===delete方法被调用===");
		System.out.println("where参数为:" + selection);
		return 0;
	}

	@Override
	public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
		System.out.println(uri.toString() + "===update方法被调用===");
		System.out.println("where参数为:" + selection);
		return 0;
	}
}
