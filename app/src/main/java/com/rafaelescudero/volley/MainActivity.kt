package com.rafaelescudero.volley

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Request.Method.GET
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.rafaelescudero.volley.modelo.Personas
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import javax.xml.transform.OutputKeys.METHOD

class MainActivity : AppCompatActivity(),RecyclerAdapter.onPersonasClickListener {

    val listPersonas = ArrayList<Personas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        conectarJson()


    }

    fun conectarJson(){  // conecta con una url y devuelve su contenido
        val url="http://iesayala.ddns.net/rafaa/Personas.php"
        val queue= Volley.newRequestQueue(this)
        val stringRequest=StringRequest(Request.Method.GET,url,Response.Listener { response ->
            //conectó correctamente
            val jsonArray= JSONArray(response)


            for (i in 0 until jsonArray.length()){

                val jsonObject= JSONObject(jsonArray.getString(i))

                listPersonas.add(Personas(jsonObject.get("Nombre").toString(),jsonObject.get("Apellidos").toString(),jsonObject.get("Direccion").toString(),jsonObject.get("Telefono").toString().toInt(),jsonObject.get("Foto").toString()))

            }

             setUpRecyclerView(listPersonas)

        },Response.ErrorListener {

            Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()

        })

        queue.add(stringRequest)
    }

    fun setUpRecyclerView(listPersonas:List<Personas>){

        personas_rv.layoutManager= LinearLayoutManager(this)

        personas_rv.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        personas_rv.adapter=RecyclerAdapter(this,listPersonas,this)

    }

    override fun onImageClick(imagen:String,data:String){

        var extras:Bundle = Bundle()

        extras.putString("url",imagen)
        extras.putString("data",data)

        val intent = Intent(this,MainActivity2::class.java)
        intent.putExtras(extras)
        startActivity(intent)

    }

    override fun onItemClick(nombre:String,apellidos:String,direccion:String) {
        Toast.makeText(this," Nombre : $nombre \n Apellidos :  $apellidos \n Direccion :  $direccion",Toast.LENGTH_LONG).show()
    }

    override fun onNumberClick(telefono: Int) {

        val intent = Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:"+telefono.toString()))
        startActivity(intent)

    }

}