package com.cursoandroid.trabalho.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.trabalho.R
import com.cursoandroid.trabalho.domain.Perfil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.cadastro_activity.*

class CadastroActivity : AppCompatActivity() {

    private val mauth = FirebaseAuth.getInstance()
    private var perfil: Perfil ?= null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_activity)

        bt_cadastro.setOnClickListener {
            cadastroUsuario()
        }
    }

    private fun cadastroUsuario() {
        val email = et_email.text.toString()
        val senha = et_senha.text.toString()

        if (!email.isEmpty() && !senha.isEmpty()) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Cadastro feito com sucesso", Toast.LENGTH_LONG).show()
                        abreTelaInicial()
                    }
                }
        } else {
            Toast.makeText(this, "Por favor, preencha os campos", Toast.LENGTH_LONG).show()
            return

        }

        val perfis = database.getReference("perfil")
        val query = perfis.orderByChild("email").equalTo(email)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@CadastroActivity,"Consulta encerrada!",Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
               perfil = snapshot.children.first().getValue(Perfil::class.java)
                if(perfil != null){
                    et_email.setText(perfil?.email)
                    et_nome.setText(perfil?.nome)
                }
            }
        })
    }


    private fun abreTelaInicial(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun salvarDados(){
        perfil = Perfil(
            email = et_email.text.toString(),
            nome = et_nome.text.toString(),
            visualizacoes = null
        )
        val uid = mauth.currentUser?.uid
        
        if(uid != null){
            val perfilUsuario = database.getReference("perfil/$uid")
            perfilUsuario.setValue(perfil)
        }

    }

}
