package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DeleteCampaniaAsync extends AsyncTask<String, Void, StatusResponse> {

    private int campaniaID;
    private Context context;

    public DeleteCampaniaAsync(int campaniaID, Context context) {
        this.campaniaID = campaniaID;
        this.context = context;
    }

    public DeleteCampaniaAsync(Context context) {
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(TableDB.DeletePreparedStatement(TableDB.CAMPANIA), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, campaniaID);
            int result = preparedStatement.executeUpdate();


            if(result != 0){
                return StatusResponse.SUCCESS;
            }

            return StatusResponse.FAIL;
        }
        catch(Exception e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }
}
