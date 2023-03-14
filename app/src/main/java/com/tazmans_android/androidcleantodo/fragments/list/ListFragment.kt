package com.tazmans_android.androidcleantodo.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tazmans_android.androidcleantodo.R
import com.tazmans_android.androidcleantodo.R.*
import com.tazmans_android.androidcleantodo.data.viewModel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout.fragment_list, container, false)

        val recycleView = view.recycleView
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireActivity())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Set menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all) {
            confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }
}