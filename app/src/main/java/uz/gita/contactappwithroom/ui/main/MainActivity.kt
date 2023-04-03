package uz.gita.contactappwithroom.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.contactappwithroom.adapter.MyAdapter
import uz.gita.contactappwithroom.databinding.ActivityMainBinding
import uz.gita.contactappwithroom.db.AppDatabase
import uz.gita.contactappwithroom.ui.add.AddUserFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dialogFragment = AddUserFragment.newInstance()
    private val appDB = AppDatabase.getInstance()
    private val adapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            adapter.refreshData(appDB.getUserDao().getUsers())
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)

            adapter.setOnItemClickListener {
                Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
            }

            btnAdd.setOnClickListener {
                dialogFragment.show(supportFragmentManager, null)
            }

            dialogFragment.setListener {
                adapter.refreshData(appDB.getUserDao().getUsers())
                recycler.adapter = adapter
            }
        }
    }
}