package com.example.content_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chapter9.Words;
import com.example.sqlite_databasehelper.MyDatabaseHelper;

public class DictProvider extends ContentProvider {
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int WORDS = 1;
	private static final int WORD = 2;
	private MyDatabaseHelper dbOpenHelper;

	static {
		matcher.addURI(Words.AUTHORITY, "words", WORDS);
		matcher.addURI(Words.AUTHORITY, "word/#", WORD);
	}

	@Override
	public boolean onCreate() {
		dbOpenHelper = new MyDatabaseHelper(this.getContext(), "myDict.db3", null, 1);
		return true;
	}

	@Nullable
	@Override
	public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (matcher.match(uri)) {
			case WORDS:
				return db.query("dict", projection, selection, selectionArgs, null, null, sortOrder);
			case WORD:
				long id = ContentUris.parseId(uri);
				String whereClause = Words.Word._ID + "=" + id;
				if (selection != null && !"".equals(selection)) {
					whereClause += " and " + selection;
				}
				return db.query("dict", projection, whereClause, selectionArgs, null, null, sortOrder);
			default:
				throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	@Nullable
	@Override
	public String getType(@NonNull Uri uri) {
		switch (matcher.match(uri)) {
			case WORDS:
				return "vnd.android.cursor.dir" + "/" + Words.AUTHORITY;
			case WORD:
				return "vnd.android.cursor.item" + "/" + Words.AUTHORITY;
			default:
				throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	@Nullable
	@Override
	public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (matcher.match(uri)) {
			case WORDS:
				long rowId = db.insert("dict", Words.Word._ID, values);
				if (rowId > 0) {
					Uri wordUri = ContentUris.withAppendedId(uri, rowId);
					getContext().getContentResolver().notifyChange(wordUri, null);
					return wordUri;
				}
				break;
			default:
				throw new IllegalArgumentException("未知Uri:" + uri);
		}
		return null;
	}

	@Override
	public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		int num;
		switch (matcher.match(uri)) {
			case WORDS:
				num = db.delete("dict", selection, selectionArgs);
				break;
			case WORD:
				long id = ContentUris.parseId(uri);
				String whereClause = Words.Word._ID + "=" + id;
				if (selection != null && !"".equals(selection)) {
					whereClause += " and " + selection;
				}
				num = db.delete("dict", whereClause, selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("未知Uri:" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}

	@Override
	public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		int num;
		switch (matcher.match(uri)) {
			case WORDS:
				num = db.update("dict", values, selection, selectionArgs);
				break;
			case WORD:
				long id = ContentUris.parseId(uri);
				String whereClause = Words.Word._ID + "=" + id;
				if (selection != null && !"".equals(selection)) {
					whereClause += " and " + selection;
				}
				num = db.update("dict", values, whereClause, selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("未知Uri:" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}
}
