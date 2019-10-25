package ifrs.ibiruba.appagendabd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TarefaSQLHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "dbTarefa";
    private static final int VERSAO_BANCO = 1;

    public static final String TABELA_TAREFA = "tarefa";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_DATA = "data";
    public static final String COLUNA_HORA = "hora";

    public TarefaSQLHelper(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + TABELA_TAREFA + "( " +
                    COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_DESCRICAO + " TEXT NOT NULL, " +
                    COLUNA_DATA + " TEXT NOT NULL, " +
                    COLUNA_HORA + " TEXT NOT NULL)" );
        };

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
