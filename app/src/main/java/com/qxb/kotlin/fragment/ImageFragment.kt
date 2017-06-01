package com.qxb.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.qxb.kotlin.R
import com.qxb.kotlin.base.BaseFragment

/**
 * Created by root on 17-5-31.
 */
class ImageFragment: BaseFragment() {
    lateinit var  mImageView: ImageView;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view  = inflater!!.inflate(R.layout.fragment_image,container,false) ;
        mImageView = view.findViewById(R.id.image_icon) as ImageView;
        return  view;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var resId = arguments.getInt(ARG_IMAGE_RES_ID);
        mImageView.setImageResource(resId);
        mImageView.setOnClickListener { Toast.makeText(activity,""+resId, Toast.LENGTH_SHORT).show() }
    }

    companion object{
        private  val ARG_IMAGE_RES_ID="arg_image_res_id";
        fun newInstance(resId:Int): ImageFragment
        {
            val imageFragment: ImageFragment = ImageFragment();
            val bundle : Bundle = Bundle();
            bundle.putInt(ARG_IMAGE_RES_ID,resId);
            imageFragment.arguments = bundle;
            return  imageFragment;
        }
    }
}