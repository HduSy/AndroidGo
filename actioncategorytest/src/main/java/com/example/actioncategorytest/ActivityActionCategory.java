package com.example.actioncategorytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;

public class ActivityActionCategory extends AppCompatActivity {
	private static final int PICK_CONTACT = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_category);
		Button btn = findViewById(R.id.btn);
		btn.setOnClickListener(v -> {
			requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 0x133);
		});
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == 0x133) {
			if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_PICK);
				intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
				startActivityForResult(intent, PICK_CONTACT);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case PICK_CONTACT:
				if (requestCode == Activity.RESULT_OK) {
					Uri contactData = data.getData();
					CursorLoader cursorLoader = new CursorLoader(this, contactData, null, null, null, null);
					Cursor cursor = cursorLoader.loadInBackground();
					if (cursor != null && cursor.moveToFirst()) {
						String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
						String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
						String phoneNumber = "此联系人暂未输入电话号码";
						Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
						if (phones != null && phones.moveToFirst()) {
							phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						}
						if (phones != null) {
							phones.close();
						}
						TextView show = findViewById(R.id.show);
						show.setText(name);
						TextView phone = findViewById(R.id.phone);
						phone.setText(phoneNumber);
					}
					if (cursor != null) {
						cursor.close();
					}
				}
		}
	}
}
