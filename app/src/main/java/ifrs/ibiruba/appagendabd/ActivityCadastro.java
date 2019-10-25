package ifrs.ibiruba.appagendabd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCadastro extends AppCompatActivity {
    EditText txtDescricao;
    EditText txtData;
    EditText txtHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtDescricao = findViewById(R.id.txtDescricao);
        txtData = findViewById(R.id.txtData);
        txtHora= findViewById(R.id.txtHora);

    }
    public void Inserir(View view){
        String descricao = txtDescricao.getText().toString();
        String data = txtData.getText().toString();
        String hora = txtHora.getText().toString();

        Tarefa tarefa = new Tarefa(descricao, data, hora);

        TarefaBD tarefaBD = new TarefaBD(this);
        tarefaBD.salvar(tarefa);

        Toast.makeText(this, "Activity inserida com sucesso", Toast.LENGTH_SHORT).show();

    }
}
