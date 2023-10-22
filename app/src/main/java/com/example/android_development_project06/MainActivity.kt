package com.example.android_development_project06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var pokeList: MutableList<String>
    private lateinit var pokeNameList: MutableList<String>
    private lateinit var pokeOrderList: MutableList<Int>

    private lateinit var recyclerViewPokemon : RecyclerView


    private val maxPokemon = 36


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewPokemon = findViewById<RecyclerView>(R.id.PokemonList)
        pokeList = mutableListOf<String>() // list of pokemon url
        pokeNameList = mutableListOf<String>()
        pokeOrderList = mutableListOf<Int>()


        getPokemon(1)

    }
    private fun getPokemon(startingPokemon : Int) {
        val client = AsyncHttpClient()
        val pokemonID = startingPokemon
        val apiUrl = "https://pokeapi.co/api/v2/pokemon/$pokemonID/"

        client[apiUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON,) {

                val pokeImg = json.jsonObject.getJSONObject("sprites").getString("front_default")
                val name = json.jsonObject.getString("name")
                val order = json.jsonObject.getInt("id")


                pokeList.add(pokeImg)
                pokeNameList.add(name)
                pokeOrderList.add(order)





                val PokemonAdapter = PokeAdpater(pokeList,pokeNameList,pokeOrderList)
                recyclerViewPokemon.adapter = PokemonAdapter
                PokemonAdapter.notifyDataSetChanged()

                recyclerViewPokemon.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerViewPokemon.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))

                if(pokeList.size < maxPokemon){
                    getPokemon(pokemonID+1)
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?

            ) {
                Log.d("Pokemon Error", errorResponse)
            }
        }]

    }

}