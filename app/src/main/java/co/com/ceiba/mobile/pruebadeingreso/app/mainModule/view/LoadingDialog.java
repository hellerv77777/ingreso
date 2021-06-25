package co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view;

import android.app.Dialog;
import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class LoadingDialog {

    private Dialog dialog;

    public LoadingDialog(Context context) {

        dialog= new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading_dialog);


        dialog.create();
        dialog.show();
    }


    public void dismiss(){
        dialog.dismiss();
    }
}
