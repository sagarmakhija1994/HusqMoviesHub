package com.sagarmakhija1994.husqmovieshub.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.sagarmakhija1994.husqmovieshub.R
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


object Constant {
    fun getJsonResponseFromRaw(response: Response<ResponseBody>): JSONObject? {
        return try {
            JSONObject(getStringResponseFromRaw(response))
        } catch (ex: Exception) {
            null
        }
    }

    private fun getStringResponseFromRaw(response: Response<ResponseBody>): String {
        val reader: BufferedReader?
        val sb = StringBuilder()
        try {
            reader = BufferedReader(InputStreamReader(response.body()!!.byteStream()))
            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    sb.append(line)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        var finalText = sb.toString()
        val char1 = finalText.substring(0, 1)
        if (char1.equals("[", ignoreCase = true)) {
            finalText = "{\"array\": $finalText}"
        }
        Log.e("Response", "" + finalText)
        return finalText
    }

    fun generateAuthToken(mobile:String, password:String, pin:String):String{
        val key = "$mobile~~$password~~$pin"
        return (Base64.encodeToString((Base64.encodeToString(key.toByteArray(), Base64.NO_WRAP)).toString().toByteArray(), Base64.NO_WRAP)).toString()
    }

    fun showToast(context: Context, text:String){
        Toast.makeText(context,text,LENGTH_LONG).show()
    }

    private var alertDialog: AlertDialog? = null
    private var dialogBuilder: AlertDialog.Builder? = null
    fun showProgressDialog(activity: Activity?, text: String?, isCancelable: Boolean) {
        dialogBuilder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = activity!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.progress_dialog_layout, null)
        val alertDialogText: TextView = dialogView.findViewById(R.id.alertDialogText)
        alertDialogText.text = text
        dialogBuilder?.setView(dialogView)
        dialogBuilder?.setCancelable(isCancelable)
        alertDialog = dialogBuilder?.create()
        alertDialog?.show()
    }

    fun hideProgressDialog() {
        try {
            alertDialog?.dismiss()
        }catch (e:Exception){}

    }
}