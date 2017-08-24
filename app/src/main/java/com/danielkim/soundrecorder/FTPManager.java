package com.danielkim.soundrecorder;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by HungShiung on 2017/8/1.
 */

public class FTPManager extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private File mFile;
    private ProgressDialog dialog;

    public FTPManager(Context context, File file) {
        this.mContext = context;
        this.mFile = file;
        dialog = new ProgressDialog(mContext);
        dialog.setTitle("檔案上傳");
        dialog.setMessage("請稍後");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = "上傳成功!!!";
        FTPClient ftpClient = new FTPClient();
        FileInputStream inputStream = null;
        String ip = "1.34.132.90";
        String username = "msn9110";
        String password = "hhs#9110";
        String remoteDir = "/MyRecordings/";
        try {
            ftpClient.setConnectTimeout(60* 1000);
            ftpClient.connect(InetAddress.getByName(ip));
            ftpClient.login(username, password);
            ftpClient.changeWorkingDirectory(remoteDir);
            inputStream = new FileInputStream(mFile);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile(mFile.getName(), inputStream);
        } catch (Exception ex) {
            result = "連線異常";
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                result = "關閉連線異常";
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
    }
}
