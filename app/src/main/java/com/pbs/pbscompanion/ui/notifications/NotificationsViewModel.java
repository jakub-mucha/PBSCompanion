package com.pbs.pbscompanion.ui.notifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pbs.pbscompanion.DAO;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public NotificationsViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue(cursor.getString(1));
    }

    public LiveData<String> getText() {
        return mText;
    }
}