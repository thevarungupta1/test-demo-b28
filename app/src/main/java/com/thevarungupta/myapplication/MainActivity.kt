package com.thevarungupta.myapplication

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_start.setOnClickListener {
            var time = edit_text_time.text.toString()
            var myAsyncTask = MyAsyncTask()
            myAsyncTask.execute(time)
        }

    }

    inner class MyAsyncTask : AsyncTask<String, String, String>(){

        var progressDialog: ProgressDialog? = null
        var result: String = ""

        override fun onPreExecute() {
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog!!.setTitle("Alert")
            progressDialog!!.setMessage("Progress.....")
            progressDialog!!.show()
        }

        override fun doInBackground(vararg params: String?): String {
//            publishProgress("sleeping....")
//            var time = params[0]!!.toLong()
//            Thread.sleep(time)
//            result = "Sleep for ${params[0]} seconds"
//            return result

            for(i in 0 until 5){
                Thread.sleep(1000)
                publishProgress(i.toString())
            }
            result = "Sleep is over"
            return result
        }

        override fun onProgressUpdate(vararg values: String?) {
            text_view_result.text = values[0]
        }

        override fun onPostExecute(result: String?) {
            progressDialog!!.dismiss()
            text_view_result.text = result
        }

    }
}