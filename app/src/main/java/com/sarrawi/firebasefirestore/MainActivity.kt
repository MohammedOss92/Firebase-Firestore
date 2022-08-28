package com.sarrawi.firebasefirestore

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("gfgfgfgfg")

        Log.d("LOGS_FILTER", "onCreate: ")

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()

        myAdapter = MyAdapter(this,userArrayList)

        recyclerView.adapter = myAdapter

        EventChangeListener()
        recyclerView.adapter = myAdapter
    }

    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()
//        db.collection("Users")//.orderBy("age",Query.Direction.ASCENDING)
        db.collection("Users").get()
//            .addSnapshotListener(object : EventListener<QuerySnapshot>{
//            override fun onEvent(
//                value: QuerySnapshot?,
//                error: FirebaseFirestoreException?) {
//
//                    for (dc : DocumentChange in value?.documentChanges!!){
//                        Log.e("FireStore error "
//                            , "شش")
//                        if (dc.type == DocumentChange.Type.ADDED){
//                            userArrayList.add(dc.document
//                                .toObject(User::class.java))
//                            Log.e("FireStore error ", "ssss")
//                        }
//                    }
//
//                    myAdapter.notifyDataSetChanged()
//                    setTitle("nbre: "+userArrayList.size)
//            }
//
//        })

            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("LOGS_FILTER", "Listener is successful")
                    for (doc in it.result) {
                        Log.d("LOGS_FILTER", "EventChangeListener: ${doc.id}")
                    }
                }else {
                    Log.d("LOGS_FILTER", "Listener is NOT successful")
                }
            }




            .addOnSuccessListener(object : OnSuccessListener<QuerySnapshot>{


                override fun onSuccess(value: QuerySnapshot?) {
                    Log.d("LOGS_FILTER", "onSuccess: ${value?.documentChanges?.size}")
                    for (dc : DocumentChange in value?.documentChanges!!){
                        Log.e("LOGS_FILTER"
                            , "شش")
                        if (dc.type == DocumentChange.Type.ADDED){
                            userArrayList.add(dc.document
                                .toObject(User::class.java))
                            Log.e("FireStore error ", "ssss")
                            Log.e("LOGS_FILTER", "ssss")
                        }

                    }

                    myAdapter.notifyDataSetChanged()
                    setTitle("nbre: "+userArrayList.size)
                }
            })

    }



}