package uno.unisal.com.uno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    //Carta rosa = new Carta();



    private Button botaoLogin;
    private TextView nomeAuxiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botaoLogin = (Button) findViewById(R.id.botaoLoginId);
        nomeAuxiliar = (TextView) findViewById(R.id.textoLoginId);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String auxiliar = nomeAuxiliar.getText().toString();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("nome", auxiliar.toString());

                startActivity(intent);
            }
        });
    }
}
