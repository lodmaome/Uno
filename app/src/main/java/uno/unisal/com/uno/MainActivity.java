package uno.unisal.com.uno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nomeUsuario;
    private Button botaoJogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeUsuario = (TextView) findViewById(R.id.textoUsuarioId);
        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            String textoPassado = extra.getString("nome").toString();
            nomeUsuario.setText("Bem vindo, " + textoPassado);
        }

        botaoJogar = (Button) findViewById(R.id.botaoJogarMainId);
        botaoJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JogoActivity.class));
            }
        });
    }
}
