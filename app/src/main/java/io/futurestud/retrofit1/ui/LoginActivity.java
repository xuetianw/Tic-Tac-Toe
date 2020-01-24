package io.futurestud.retrofit1.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.futurestud.retrofit1.R;
import io.futurestud.retrofit1.api.model.Player;
import io.futurestud.retrofit1.api.proxy.ProxyBuilder;
import io.futurestud.retrofit1.api.proxy.WGServerProxy;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    EditText emailET;
    EditText first_nameET;
    EditText last_nameET;
    EditText pass_wordET;
    Player player;
    private WGServerProxy proxy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = (EditText) findViewById(R.id.login_email_ET);
        first_nameET = (EditText) findViewById(R.id.first_name_ET);
        last_nameET = (EditText) findViewById(R.id.last_name_ET);
        pass_wordET = (EditText) findViewById(R.id.password_ET);

        proxy = ProxyBuilder.getProxy();

        Button register_btn = (Button) findViewById(R.id.register_button);
        Button login_btn = (Button) findViewById(R.id.login_button);

        setup_register_btn(register_btn);
        setup_login_btn(login_btn);
    }

    private void setup_login_btn(Button login_btn) {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String first_name = first_nameET.getText().toString();
                String last_name = last_nameET.getText().toString();
                String password = pass_wordET.getText().toString();
                player = new Player();
                player.setEmail(email);
                player.setFirst_name(first_name);
                player.setLast_name(last_name);
                player.setPassword(password);
                Call<Player> call = proxy.login(player);
                ProxyBuilder.callProxy(getApplicationContext(), call, returnedKey -> response(returnedKey));
            }
        });
    }



    private void setup_register_btn(Button register_btn) {
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String first_name = first_nameET.getText().toString();
                String last_name = last_nameET.getText().toString();
                String password = pass_wordET.getText().toString();
                player = new Player();
                player.setEmail(email);
                player.setFirst_name(first_name);
                player.setLast_name(last_name);
                player.setPassword(password);
                Call<Player> call = proxy.sign_up(player);
                ProxyBuilder.callProxy(getApplicationContext(), call, returnedKey -> response(returnedKey));
            }
        });
    }

    private void response(Player returnedKey) {
        if((returnedKey == null)) {
            Toast.makeText(getApplicationContext(), "player does not exist",
                    Toast.LENGTH_LONG).show();
        } else {
            Intent intent = MainActivity.makeIntent(LoginActivity.this);
            finish();
            startActivity(intent);
        }
    }
}
