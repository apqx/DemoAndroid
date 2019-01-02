package me.apqx.demo.jetpack.navigation.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import me.apqx.demo.LogUtil
import me.apqx.demo.R
import me.apqx.demo.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentLogin.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentLogin.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FragmentLogin : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    var dataBinding: FragmentLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d("onCreate $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d("onDestroy $this")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        val navController = findNavController()
        dataBinding?.btnLogin?.setOnClickListener{
            navController.navigate(R.id.fragment_blue)
        }
        dataBinding?.btnRegister?.setOnClickListener{
            navController.navigate(R.id.fragment_red)

        }
        return dataBinding?.root
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d("onStart $this")
        dataBinding?.textView?.text = arguments?.getString("name")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d("onStop $this")

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
