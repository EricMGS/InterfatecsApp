package ericmgs.interfatecsapp.auxiliar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ericmgs.interfatecsapp.models.Coach;
import ericmgs.interfatecsapp.models.Equipe;
import ericmgs.interfatecsapp.models.Participante;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DBPATH = "data/data/ericmgs.interfatecsapp/databases/";
    private static String DBNAME = "bdEquipes";
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private boolean checkDatabase() {
        SQLiteDatabase db = null;
        try {
            String path = DBPATH + DBNAME;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
        } catch (SQLiteException e) {

        }
        return db != null;
    }

    private void createDataBase() throws Exception {
        boolean exists = checkDatabase();
        if (!exists) {
            this.getReadableDatabase();
            try{
                copyDatabase();
            } catch(IOException e) {
                throw new Error("Não foi possível copiar o arquivo");
            }
        }
    }

    private void copyDatabase() throws IOException {
        String dbPath = DBPATH + DBNAME;
        OutputStream dbStream = new FileOutputStream(dbPath);
        InputStream dbInputStream = context.getAssets().open(DBNAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = dbInputStream.read(buffer)) > 0) {
            dbStream.write(buffer, 0, length);
        }
        dbInputStream.close();
        dbStream.flush();
        dbStream.close();
    }

    public SQLiteDatabase getDatabase() {
        try {
            createDataBase();
            String path = DBPATH + DBNAME;
            return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            return getWritableDatabase();
        }
    }

    public void insertCoach(String nome, String cidade, String rg) {
        String insertQuery = "INSERT INTO tblCoach VALUES(\"" + nome + "\",\"" + cidade + "\",\"" + rg + "\")";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(insertQuery);
        db.close();
    }

    public void insertParticipante(String nome, String ra, String cidade, String equipe, int reserva) {
        String insertQuery = "INSERT INTO tblParticipante VALUES(\"" + nome + "\",\"" + ra + "\",\"" + cidade + "\",\"" + equipe + "\"," + reserva +")";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(insertQuery);
        db.close();
    }

    public void insertEquipe(String nome, String cidade, String integrante1, String integrante2, String integrante3,
                             String reserva, String coach, int cafeComLeite, int primeiraParticipacao, String senha, int colocacao) {
        String insertQuery = "INSERT INTO tblEquipe VALUES(\"" + nome + "\",\"" + cidade + "\",\"" + integrante1 + "\",\"" + integrante2 + "\",\"" +
                integrante3 + "\",\"" + reserva + "\",\"" + coach + "\"," + cafeComLeite + "," + primeiraParticipacao + ",\"" + senha + "\"," +
                colocacao + ")";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(insertQuery);
        db.close();
    }

    public Coach getCoach(String rg) {
        Coach coach = new Coach();
        String selectQuery = "SELECT * FROM tblEquipe WHERE rg = " + rg + ";";
        SQLiteDatabase db = this.getDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            coach.setNome(cursor.getString(0));
            coach.setCidade(cursor.getString(1));
            coach.setRg(cursor.getString(2));
        }
        cursor.close();
        db.close();
        return coach;
    }

    public List<Equipe> getEquipes() {
        List <Equipe> equipes = new ArrayList<Equipe>();
        String selectQuery = "SELECT * FROM tblEquipe";
        SQLiteDatabase db = this.getDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Equipe eq = new Equipe();
                eq.setNome(cursor.getString(0));
                eq.setCidade(cursor.getString(1));
                Participante participante = new Participante();
                Cursor cr;
                //Participante 1
                participante.setName(cursor.getString(2));
                participante.setCidade(eq.getCidade());
                participante.setEquipe(eq.getNome());
                cr = db.rawQuery("SELECT ra FROM tblParticipante WHERE nome = \"" + participante.getName() + "\"", null);
                cr.moveToFirst();
                participante.setRa(cr.getString(0));
                participante.setReserva(false);
                eq.setIntegrante1(participante);
                //Participante 2
                participante.setName(cursor.getString(3));
                participante.setCidade(eq.getCidade());
                participante.setEquipe(eq.getNome());
                cr = db.rawQuery("SELECT ra FROM tblParticipante WHERE nome = \"" + participante.getName() + "\"", null);
                cr.moveToFirst();
                participante.setRa(cr.getString(0));
                participante.setReserva(false);
                eq.setIntegrante2(participante);
                //Participante 3
                participante.setName(cursor.getString(4));
                participante.setCidade(eq.getCidade());
                participante.setEquipe(eq.getNome());
                cr = db.rawQuery("SELECT ra FROM tblParticipante WHERE nome = \"" + participante.getName() + "\"", null);
                cr.moveToFirst();
                participante.setRa(cr.getString(0));
                participante.setReserva(false);
                eq.setIntegrante3(participante);
                //Reserva
                participante.setName(cursor.getString(5));
                participante.setCidade(eq.getCidade());
                participante.setEquipe(eq.getNome());
                cr = db.rawQuery("SELECT ra FROM tblParticipante WHERE nome = \"" + participante.getName() + "\"", null);
                cr.moveToFirst();
                participante.setRa(cr.getString(0));
                participante.setReserva(true);
                eq.setReserva(participante);
                //Coach
                Coach ch = new Coach();
                ch.setNome(cursor.getString(6));
                ch.setCidade(eq.getCidade());
                cr = db.rawQuery("SELECT rg FROM tblCoach WHERE nome = \"" + ch.getNome() + "\"", null);
                cr.moveToFirst();
                ch.setRg(cr.getString(0));
                eq.setCoach(ch);

                eq.setCafeComLeite(cursor.getInt(7) == 0 ? false : true);
                eq.setPrimeiraParticipacao(cursor.getInt(8) == 0 ? false : true);

                equipes.add(eq);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return equipes;
    }

    public void deleteCoach(String rg) {
        String deleteQuery = "DELETE FROM tblCoach WHERE rg=\"" + rg + "\"";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(deleteQuery);
        db.close();
    }

    public void deleteParticipante(String ra) {
        String deleteQuery = "DELETE FROM tblParticipante WHERE ra=\"" + ra + "\"";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(deleteQuery);
        db.close();
    }

    public void deleteEquipe(String nome) {
        String deleteQuery = "DELETE FROM tblEquipe WHERE nome=\"" + nome + "\"";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(deleteQuery);
        db.close();
    }

    public void updateParticipante(String raAntigo, String nome, String ra, String cidade, String equipe, int reserva) {
        String updateQuery = "UPDATE tblParticipante SET nome=\"" + nome + "\", ra=\"" + ra + "\", cidade=\"" + cidade + "\", equipe=\"" +
                equipe + "\", reserva=" + reserva + " WHERE ra=\"" + raAntigo + "\"";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(updateQuery);
        db.close();
    }

    public void updateCoach(String rgAntigo, String nome, String cidade, String rg) {
        String updateQuery = "UPDATE tblCoach SET nome=\"" + nome + "\", cidade=\"" + cidade + "\", rg=\"" + rg + "\" WHERE rg=\"" +
                rgAntigo + "\"";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(updateQuery);
        db.close();
    }

    public void updateEquipe(String nomeAntigo, String nome, String cidade, String integrante1, String integrante2, String integrante3,
                             String reserva, String coach, int cafeComLeite, int primeiraParticipacao, String senha, int colocacao) {
        String updateQuery = "UPDATE tblEquipe SET nome=\"" + nome + "\", cidade=\"" + cidade + "\",integrante1=\"" + integrante1 +
                "\",integrante2=\"" + integrante2 + "\",integrante3=\"" + integrante3 + "\",reserva=\"" + reserva + "\",coach=\"" + coach +
                "\",cafeComLeite=" + cafeComLeite + ",primeiraParticipacao=" + primeiraParticipacao + ",senha=\"" + senha + "\",colocacao=" +
                colocacao + " WHERE nome=\"" + nomeAntigo + "\"";
        SQLiteDatabase db = this.getDatabase();
        db.execSQL(updateQuery);
        db.close();
    }

    public boolean participanteExists(String ra) {
        SQLiteDatabase db = this.getDatabase();
        String selectQuery = "SELECT * FROM tblParticipante WHERE ra=\""+ ra + "\"";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public boolean coachExists(String rg) {
        SQLiteDatabase db = this.getDatabase();
        String selectQuery = "SELECT * FROM tblCoach WHERE rg=\""+ rg + "\"";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

    public boolean equipeExists(String nome) {
        SQLiteDatabase db = this.getDatabase();
        String selectQuery = "SELECT * FROM tblEquipe WHERE nome=\""+ nome + "\"";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }

}
