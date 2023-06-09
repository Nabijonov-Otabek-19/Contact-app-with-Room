package uz.gita.contactappwithroom.ui.add

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import uz.gita.contactappwithroom.databinding.DialogFragmentBinding
import uz.gita.contactappwithroom.db.AppDatabase
import uz.gita.contactappwithroom.entities.UserData

class AddUserFragment : DialogFragment() {

    private var _binding: DialogFragmentBinding? = null
    private val binding get() = _binding!!

    private var appDB = AppDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setWindowParams()
    }

    private fun setWindowParams() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userDao = appDB.getUserDao()

        binding.apply {
            txtTitle.text = "Add to Contact"

            btnSave.setOnClickListener {
                if (edtName.text.toString().isNotEmpty() && edtNumber.text.toString().isNotEmpty()
                ) {

                    if (!(userDao.isExistUser(edtName.text.toString()))) {
                        userDao.insert(
                            UserData(
                                name = edtName.text.toString(),
                                number = edtNumber.text.toString()
                            )
                        )
                        edtName.setText("")
                        edtNumber.setText("")
                        dismiss()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "${edtName.text.toString()} is  already exist",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                } else {
                    Toast.makeText(requireActivity(), "Fill form", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddUserFragment()
    }
}