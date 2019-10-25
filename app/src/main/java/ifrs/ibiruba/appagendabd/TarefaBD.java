package ifrs.ibiruba.appagendabd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TarefaBD {
    private TarefaSQLHelper helper;

    public TarefaBD(Context context){
        helper = new TarefaSQLHelper(context);
    }

    private long inserir(Tarefa tarefa){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TarefaSQLHelper.COLUNA_DESCRICAO, tarefa.descricao);
        cv.put(TarefaSQLHelper.COLUNA_DATA, tarefa.data);
        cv.put(TarefaSQLHelper.COLUNA_HORA, tarefa.hora);

        long id = db.insert(TarefaSQLHelper.TABELA_TAREFA, null, cv);
        if (id != -1){
            tarefa.id = id;
        }
        db.close();
        return id;
    }

    public void salvar(Tarefa tarefa){
        if (tarefa.id == 0){
            inserir(tarefa);
        }else{
            //aqui vai o método para atualizar a tarefa
        }
    }

    public boolean deletar(Tarefa tarefa){
        SQLiteDatabase db = helper.getWritableDatabase();

        int retorno = db.delete(TarefaSQLHelper.TABELA_TAREFA, TarefaSQLHelper.COLUNA_ID + " = ?", new String[] {String.valueOf(tarefa.id)});
        db.close();
        if (retorno != -1){
            return true;
        } else {
            return false;
        }
    }
    public List<Tarefa> buscarTarefa(){
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM " + TarefaSQLHelper.TABELA_TAREFA;
        String[] argumentos = null;
        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(TarefaSQLHelper.COLUNA_ID));
            String descricao = cursor.getString(cursor.getColumnIndex(TarefaSQLHelper.COLUNA_DESCRICAO));
            String data = cursor.getString(cursor.getColumnIndex(TarefaSQLHelper.COLUNA_DATA));
            String hora = cursor.getString(cursor.getColumnIndex(TarefaSQLHelper.COLUNA_HORA));
            Tarefa tarefa = new Tarefa(id, descricao, data, hora);
            tarefas.add(tarefa);
        }
        cursor.close();
        db.close();
        return tarefas;
    }

}
