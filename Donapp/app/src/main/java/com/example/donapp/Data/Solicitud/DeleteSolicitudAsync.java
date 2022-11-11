package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteSolicitudAsync extends AsyncTask<String, Void, StatusResponse> {

    private int solicitudID;
    private Context context;

    public DeleteSolicitudAsync(int solicitudID, Context context){
        this.solicitudID = solicitudID;
        this.context = context;
    }

    public DeleteSolicitudAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(TableDB.DeletePreparedStatement(TableDB.SOLICITUD), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, solicitudID);
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
