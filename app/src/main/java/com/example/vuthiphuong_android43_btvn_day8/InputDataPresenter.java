package com.example.vuthiphuong_android43_btvn_day8;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
public class InputDataPresenter {
    IInputData iInputData;

    public InputDataPresenter(IInputData iInputData) {
        this.iInputData = iInputData;
    }

    public  void onInput(String title, String description, String time, String date, String type, String tags, String weeks){
        if (title.equals("") || description.equals("") || time.equals("") || date.equals("") || type.equals("") || tags.equals("null") || weeks.equals("null"))
            iInputData.onInputDataError("Nhập dữ liệu không thành công");
        else
            iInputData.onInputDataSucsetful("Nhập dữ liệu thành công");
    }
}

