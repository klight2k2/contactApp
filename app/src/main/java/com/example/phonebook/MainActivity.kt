package com.example.phonebook

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView

class MainActivity : AppCompatActivity(), PhoneViewAdapter.ItemClickListener {
    private  var items= arrayListOf<PhoneModel>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val phonebook = arrayListOf(
            "0812345678",
            "0854321987",
            "0876543210",
            "0832143654",
            "0865432109",
            "0823456789",
            "0843210987",
            "0887654321",
            "0810987654",
            "0845678901",
            "0856789012",
            "0832109876",
            "0865432109",
            "0876543210",
            "0821098765",
            "0845678901",
            "0812345678",
            "0832143654",
            "0865432109",
            "0887654321"
        )
        val names = arrayListOf(
            "John Smith",
            "Emily Johnson",
            "Michael Williams",
            "Sarah Brown",
            "Christopher Davis",
            "Jessica Miller",
            "Matthew Wilson",
            "Olivia Anderson",
            "David Taylor",
            "Sophia Thomas",
            "Daniel Martinez",
            "Isabella Garcia",
            "Andrew Martinez",
            "Mia Robinson",
            "Joseph Lee",
            "Abigail Thompson",
            "William Hall",
            "Ava Young",
            "James Clark",
            "Charlotte Perez"
        )
        repeat(20) {
            val parts = names[it].lowercase().split(" ")
            val firstName = parts.first()
            val lastName = parts.last()
            val domain = "example.com" // Thay đổi tên miền tùy ý

            items.add(
                PhoneModel(
                    names[it],
                    phonebook[it],
                    "$firstName.$lastName@$domain"
                )
            )
        }

//        val adapter = PhoneRVAdapter(items, this)
        val adapter= PhoneViewAdapter(items, this)
        val listView=findViewById<ListView>(R.id.list_view)
        listView.adapter=adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // Open the context menu
           ItemClick(position)
        }

//
//        val recyclerView = findViewById<RecyclerView>(R.id.rcv_phonebook)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = adapter
        registerForContextMenu(listView)

    }

    override fun ItemClick(position: Int) {
        val phonebook = items[position]
        val intent = Intent(this, DetailPhone::class.java)
        intent.putExtra("name", phonebook.name)
        intent.putExtra("email", phonebook.email)
        intent.putExtra("phoneNumber", phonebook.phoneNumber)
        Log.v("TAG", "position $position")
        startActivity(intent)
    }
    // menu item select listener

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(0, v.id, 0, "call")
        menu.add(0, v.id, 0, "send email")
        menu.add(0, v.id, 0, "send sms")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info:AdapterContextMenuInfo= item.getMenuInfo() as AdapterContextMenuInfo;
        super.onContextItemSelected(item)
        val position= info.position
        val phoneNumber=items[position].phoneNumber
        val email=items[position].email
        if (item.title === "call") {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))

            startActivity(intent)
            Log.v("TAG", "call ")
        } else if (item.title === "send email") {
            val intent=Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email)) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                // You can also attach multiple items by passing an ArrayList of Uris
            }
            startActivity(intent)
            Log.v("TAG", "email")
        } else if (item.title === "send sms") {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$phoneNumber"))
            intent.putExtra("sms_body", "Content")
            startActivity(intent)


            Log.v("TAG", "sms $phoneNumber")
        }
        return true
    }



}