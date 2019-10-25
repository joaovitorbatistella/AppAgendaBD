package ifrs.ibiruba.appagendabd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Tarefa> tarefas;
    ListView listView;
    ArrayAdapter<Tarefa> adapter;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listView);
        getTarefas();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa)parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, tarefa.descricao + " - " + tarefa.data + " - " + tarefa.hora, Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterview, View view, int position, long id) {
                Tarefa tarefa = (Tarefa)adapterview.getItemAtPosition(position);
                TarefaBD bd = new TarefaBD(context);

                if ( bd.deletar(tarefa)){
                    Toast.makeText(context, "Atividade Esxcluída", Toast.LENGTH_LONG).show();
                    onResume();
                } else{
                    Toast.makeText(context, "Erro ao deletar", Toast.LENGTH_LONG).show();
                }

                return true;

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent it = new Intent(MainActivity.this, ActivityCadastro.class);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getTarefas(){
        TarefaBD bd = new TarefaBD(this);

        //bd.salvar(new Tarefa("Estudar", "10/10/2019", "22:00"));
        //bd.salvar(new Tarefa("Artigo", "15/10/2019", "23:00"));
        //bd.salvar(new Tarefa("Prova", "25/10/2019", "10:15"));

        tarefas = new ArrayList<Tarefa>();
        tarefas = bd.buscarTarefa();

        adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);
        listView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getTarefas();
    }
}
