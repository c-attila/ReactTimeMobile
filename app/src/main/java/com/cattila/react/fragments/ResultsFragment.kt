package com.cattila.react.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.cattila.react.R
import com.cattila.react.adapters.ResultAdapter
import com.cattila.react.data.ResultDatabase
import com.cattila.react.databinding.FragmentResultsBinding
import com.cattila.react.viewmodels.ResultViewModel
import kotlinx.android.synthetic.main.fragment_results.*
import com.cattila.react.data.Result

class ResultsFragment : Fragment() {

    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)

        var adapter = ResultAdapter()

        binding.recyclerView.adapter = adapter

        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)

        resultViewModel.getAllResults().observe(this, Observer<List<Result>> {
            adapter.submitList(it)
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(
            ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                resultViewModel.delete(adapter.getResultAt(viewHolder.adapterPosition))
                Toast.makeText(context, "Result Deleted!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(binding.recyclerView)

        return binding.root
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val db = Room.databaseBuilder(
//            activity!!.applicationContext,
//            ResultDatabase::class.java, "database"
//        ).allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
//            .build()
//
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false)
//
//        binding.resultsButton1.setOnClickListener {
//            findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToMenuFragment())
//        }
//
//        var results = db.resultDao().getTopTen()
//        println(results.toString())
//        binding.textView.text = results.toString()
//
//        return binding.root
//    }

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }

    private lateinit var resultViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        buttonAddResult.setOnClickListener {
//            startActivityForResult(
//                Intent(this, AddEditResultActivity::class.java),
//                ADD_NOTE_REQUEST
//            )
//        }


//        adapter.setOnItemClickListener(object : ResultAdapter.OnItemClickListener {
//            override fun onItemClick(result: Result) {
//                var intent = Intent(baseContext, AddEditResultActivity::class.java)
//                intent.putExtra(AddEditResultActivity.EXTRA_ID, result.id)
//                intent.putExtra(AddEditResultActivity.EXTRA_TITLE, result.title)
//                intent.putExtra(AddEditResultActivity.EXTRA_DESCRIPTION, result.description)
//                intent.putExtra(AddEditResultActivity.EXTRA_PRIORITY, result.priority)
//
//                startActivityForResult(intent, EDIT_NOTE_REQUEST)
//            }
//        })
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        return when (item?.itemId) {
//            R.id.delete_all_results -> {
//                resultViewModel.deleteAllResults()
//                Toast.makeText(this, "All results deleted!", Toast.LENGTH_SHORT).show()
//                true
//            }
//            else -> {
//                super.onOptionsItemSelected(item)
//            }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
//            val newResult = Result(
//                data!!.getStringExtra(AddEditResultActivity.EXTRA_TITLE),
//                data.getStringExtra(AddEditResultActivity.EXTRA_DESCRIPTION),
//                data.getIntExtra(AddEditResultActivity.EXTRA_PRIORITY, 1)
//            )
//            resultViewModel.insert(newResult)
//
//            Toast.makeText(this, "Result saved!", Toast.LENGTH_SHORT).show()
//        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
//            val id = data?.getIntExtra(AddEditResultActivity.EXTRA_ID, -1)
//
//            if (id == -1) {
//                Toast.makeText(this, "Could not update! Error!", Toast.LENGTH_SHORT).show()
//            }
//
//            val updateResult = Result(
//                data!!.getStringExtra(AddEditResultActivity.EXTRA_TITLE),
//                data.getStringExtra(AddEditResultActivity.EXTRA_DESCRIPTION),
//                data.getIntExtra(AddEditResultActivity.EXTRA_PRIORITY, 1)
//            )
//            updateResult.id = data.getIntExtra(AddEditResultActivity.EXTRA_ID, -1)
//            resultViewModel.update(updateResult)
//
//        } else {
//            Toast.makeText(this, "Result not saved!", Toast.LENGTH_SHORT).show()
//        }
}
