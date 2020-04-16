package com.cursoandroid.trabalho.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.domain.Foto
import com.cursoandroid.trabalho.view.adapter.FotoAdapter
import com.cursoandroid.trabalho.viewmodel.FotoViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.foto_item.*
import kotlinx.android.synthetic.main.toolbar.toolbar


class MainActivity : AppCompatActivity() {


    private val viewModel by lazy {
        ViewModelProvider(this).get(FotoViewModel::class.java)
    }

    val autenticado = FirebaseAuth.getInstance().uid
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: FotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureRecyclerView()
        val actionBAR = supportActionBar
        setSupportActionBar(toolbar)

        verificaLogado()

        //linearLayoutManager = LinearLayoutManager(this)
        //rv_foto.layoutManager = linearLayoutManager




        //recyclerView = findViewById(R.id.rv_foto)

        bt_foto_dia.setOnClickListener {
            showFoto()
            startActivity(Intent(this,FotoDia::class.java))
        }
        //configureRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun configureRecyclerView(){
        rv_foto.layoutManager = LinearLayoutManager(this)
    }

    private fun showFoto(){
        viewModel.result.observe(this, Observer { fotos ->
            val adapter = FotoAdapter(ArrayList())
            rv_foto.adapter = adapter
        })
        viewModel.getFoto()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sair ->{
                deslogar()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun verificaLogado(){
        if(autenticado == null){
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }else{

        }
    }


    private fun deslogar(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        Toast.makeText(this,"Deslogado com sucesso",Toast.LENGTH_LONG).show()
    }


}
