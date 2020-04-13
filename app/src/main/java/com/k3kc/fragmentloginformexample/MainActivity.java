package com.k3kc.fragmentloginformexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoginForm.ILoginFormMessageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new LoginForm())
                .commit();
    }

    @Override
    public void OnCredentials(boolean isSuccessfullyAuthorized, List<LoginModel> logins) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(isSuccessfullyAuthorized) {
            Log.d("AAA", "IS AUTHORIZED");

            Bundle bundle = new Bundle();
            String serializedLogins = new Gson().toJson(logins);
            bundle.putString("logins", serializedLogins);
            ContactsFragment contactsFragment = new ContactsFragment();
            contactsFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, contactsFragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new AuthorizatiohFailedFragment())
                    .commit();
        }
    }
}
