package es.udc.psi.Q23.encajados.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.database.DatabaseReference;

public class FirebaseHelper {

    FirebaseFirestore firebaseFirestore;

    public FirebaseHelper() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void saveUserScore(String userName, int score) {
        UserScore userScore = new UserScore(userName, score);

        // Agregar un nuevo documento con un ID generado automáticamente
        firebaseFirestore.collection("scores")
                .add(userScore)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("_TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("_TAG", "Error adding document", e);
                    }
                });
    }

    public void getTop10Scores() {
        firebaseFirestore.collection("scores")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                UserScore userScore = document.toObject(UserScore.class);
                                Log.d("_TAG", "User: " + userScore.getUsername() + ", Score: " + userScore.getScore());
                                // Aquí puedes manejar cada una de las puntuaciones obtenidas
                            }
                        } else {
                            Log.w("_TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
