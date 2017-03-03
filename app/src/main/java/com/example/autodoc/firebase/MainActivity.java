package com.example.autodoc.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "com.example";
    @BindView(R.id.textNome)
    EditText txtNome;
    @BindView(R.id.textEmail)
    EditText txtEmail;
    @BindView(R.id.textSenha)
    EditText txtSenha;
    @BindView(R.id.reciclerView)
    RecyclerView recyclerView;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    AdapterReciclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        initializeMauthStateListener();

    }

    private void initializeMauthStateListener(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                }else{
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
       // mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @OnClick(R.id.btnSalvar)
    void save() {
        saveUser();
    }

    @OnClick(R.id.btnListar)
    void listar() {
        listUser();
    }

    void saveUser() {
        Usuario u = new Usuario();
        u.setmNome(txtNome.getText().toString());
        u.setmEmail(txtEmail.getText().toString());
        u.setmSenha(txtSenha.getText().toString());

        String userId = reference.push().getKey();
        reference.child(userId).setValue(u);
    }

    void listUser() {
        reference = database.getReference("Users");
        final List<Usuario> listUser = new ArrayList<>();
        adapter = new AdapterReciclerView(MainActivity.this, listUser);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<Map<String, Usuario>> t = new GenericTypeIndicator<Map<String, Usuario>>() {
                };

                Map<String, Usuario> lista = dataSnapshot.getValue(t);

                for (String key : lista.keySet()) {
                    Log.d(TAG, "onDataChange: " + lista.get(key));
                    listUser.add(lista.get(key));

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        adapter = new AdapterReciclerView(MainActivity.this, listUser);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    @OnClick(R.id.button_criar) void createUser(){
        createUser(txtEmail.getText().toString(),txtSenha.getText().toString());
    }

    void createUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Toast.makeText(MainActivity.this, "Usuario criado "+ task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Falha ",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }



}
