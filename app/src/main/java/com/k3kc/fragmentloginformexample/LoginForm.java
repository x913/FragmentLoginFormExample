package com.k3kc.fragmentloginformexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginForm extends Fragment {

    public interface ILoginFormMessageListener {
        public void OnCredentials(boolean isSuccessfullyAuthorized, List<LoginModel> logins);
    }


    private MainActivity mMainActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_form, container, false);
        Button loginButton = view.findViewById(R.id.login_button);

        final EditText login = view.findViewById(R.id.login);
        final EditText password = view.findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtLogin = login.getText().toString();
                String txtPassword = password.getText().toString();
                if(TextUtils.isEmpty(txtLogin) || TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(getContext(), R.string.credentials_required, Toast.LENGTH_LONG).show();
                    if(TextUtils.isEmpty(txtLogin))
                        login.requestFocus();
                    else if(TextUtils.isEmpty(txtPassword))
                        password.requestFocus();

                    return;
                } else {

                    ILogin loginApiInterface = ApiClient.getApiClient().create(ILogin.class);
                    Call<List<LoginModel>> call = loginApiInterface.getLoginResult();
                    call.enqueue(new Callback<List<LoginModel>>() {
                        @Override
                        public void onResponse(Call<List<LoginModel>> call, Response<List<LoginModel>> response) {
                            List<LoginModel> logins = response.body();
                            boolean isAuthorized = false;
                            for(LoginModel loginModel: logins) {
                                if(txtLogin.equalsIgnoreCase(loginModel.getEmail())) {
                                    isAuthorized = true;
                                    break;
                                }
                                //Log.d("AAA", loginModel.getName() + " " + loginModel.getEmail());
                            }
                            mMainActivity.OnCredentials(isAuthorized, logins);
                        }

                        @Override
                        public void onFailure(Call<List<LoginModel>> call, Throwable t) {
                            Log.d("AAA", t.getMessage());
                        }
                    });
                }
            }
        });
        return view;
    }
}
