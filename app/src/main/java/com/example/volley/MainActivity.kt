package com.example.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val sringUrl="https://toplearn.com/courses"
    val jsonUrl="https://barnamenevisan.info/api/courses/getactivecourses"
    val jsonObjectUrl="https://jsonplaceholder.typicode.com/posts/1"

    var RequestVolley:RequestQueue?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RequestVolley=Volley.newRequestQueue(this)
       //  getString()
       //getJsonArray()
      //   getjsonObject()
/*****************************************************/
        val array = JSONArray()
        var listStusent = ArrayList<String>()
        listStusent.add("zahra")
        listStusent.add("sara")
        listStusent.add("mana")

        for (i in 0 until listStusent.size) {
            val obj = JSONObject()
            try {

                obj.put("StudentName",  listStusent[i])
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            array.put(obj)
        }

        val jsonArrayRequest=JsonArrayRequest(
            Request.Method.GET,
            jsonUrl,
            array,
            {
                    response:JSONArray->
                try{
                    for (item in 0 until response.length()-1){
                        val courseObject=response.getJSONObject(item)
                        val courseTitle=courseObject.getString("title")
                        Log.d("response",courseTitle.toString())
                    }

                }catch (e:JSONException){
                    e.printStackTrace()
                }
            },
            { error: VolleyError? -> Log.d("response", error.toString())}

        )
        RequestVolley!!.add(jsonArrayRequest)
    }

/***********************************************************/
/*
{
"title": "دوره فشرده آموزش زبان PHP از مقدماتی تا پیشرفته به همراه معماری MVC ",
"startDate": "2022-03-26T00:00:00",
"teacher": "حسن خسروجردی",
"link": "https://barnamenevisan.info/c/31M",
"departmentName": "اصلی",
"state": "تهران",
"city": "تهران",
"imageAddress": "https://barnamenevisan.info/Images/Course/Origin/80_71_phpmvc.png",
"thumbnailImageAddress": "https://barnamenevisan.info/Images/Course/Thumb/80_71_phpmvc.png",
"departmentUrl": "https://barnamenevisan.info/Department/1/About/اصلی"
}
 */
    fun getjsonObject(){
        val jsonObjectReq=JsonObjectRequest(
            Request.Method.GET,
            jsonObjectUrl,
            { response: JSONObject ->
                try {
                    val data=response.getString("id")
                    Log.d("getjsonObject====>",data.toString())

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error: VolleyError? ->
                try{

                }catch(e:JSONException){
                    e.printStackTrace()
                }
            }

        )

        RequestVolley!!.add(jsonObjectReq)
    }
/*******************************************************/

/*
[
{
"title": "دوره فشرده آموزش زبان PHP از مقدماتی تا پیشرفته به همراه معماری MVC ",
"startDate": "2022-03-26T00:00:00",
"teacher": "حسن خسروجردی",
"link": "https://barnamenevisan.info/c/31M",
"departmentName": "اصلی",
"state": "تهران",
"city": "تهران",
"imageAddress": "https://barnamenevisan.info/Images/Course/Origin/80_71_phpmvc.png",
"thumbnailImageAddress": "https://barnamenevisan.info/Images/Course/Thumb/80_71_phpmvc.png",
"departmentUrl": "https://barnamenevisan.info/Department/1/About/اصلی"
},
{
"title": "دوره فشرده آموزش سی شارپ ( مقدماتی تا پیشرفته ) نوبت دوم",
"startDate": "2022-03-26T00:00:00",
"teacher": "محمد اردوخانی",
"link": "https://barnamenevisan.info/c/82m",
"departmentName": "اصلی",
"state": "تهران",
"city": "تهران",
"imageAddress": "https://barnamenevisan.info/Images/Course/Origin/85_70_csharp.png",
"thumbnailImageAddress": "https://barnamenevisan.info/Images/Course/Thumb/85_70_csharp.png",
"departmentUrl": "https://barnamenevisan.info/Department/1/About/اصلی"
}
]
 */
     fun getJsonArray(){
       val jsonArrayRequest=JsonArrayRequest(
         Request.Method.GET,
         jsonUrl,
           {
                   response:JSONArray->
               try{
                   for (item in 0 until response.length()-1){
                       val courseObject=response.getJSONObject(item)
                       val courseTitle=courseObject.getString("title")
                       Log.d("response",courseTitle.toString())
                   }

               }catch (e:JSONException){
                   e.printStackTrace()
               }
           },
           { error: VolleyError? -> Log.d("response", error.toString())}

       )
         RequestVolley!!.add(jsonArrayRequest)
    }
/********************************************************/

     fun getString() {
        val stringRequest=StringRequest(
            Request.Method.POST,
            sringUrl,
            { response: String ?->
                if (response != null) {
                    Log.d("response",response.toString()) }
            },
            { error: VolleyError? -> Log.d("response", error.toString()) }
        )
        RequestVolley!!.add(stringRequest)
    }
/*********************************************************/

}