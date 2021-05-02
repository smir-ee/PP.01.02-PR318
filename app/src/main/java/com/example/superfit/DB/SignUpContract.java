package com.example.superfit.DB;

import android.provider.BaseColumns;
import com.example.superfit.SignInActivity;

public final class SignUpContract {
    private SignUpContract(){

    }

    public static final class SignUp implements BaseColumns{
        public static String TABLE_NAME = "registered_users";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_EMAIL = "email";
        public final static String COLUMN_CODE = "code";
    }
}
