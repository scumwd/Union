package com.example.domain.auth;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;


public class Authorization {
    FirebaseAuth mAuth;
    Boolean result;

    public Boolean execute(String email, String password, Context context){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            result = true;
                        else
                            result = false;
                    }
                });
        return result;
    }
}
