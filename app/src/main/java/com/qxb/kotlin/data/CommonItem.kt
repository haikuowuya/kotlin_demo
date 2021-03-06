package com.qxb.kotlin.data

/**
 * Created by root on 17-6-1.
 */
data class CommonItem(val tid: String,
                      val fid: String,
                      val posttableid:String,
                      val typeid: String,
                      val locationid: String,
                      val sortid: String,
                      val readperm: String,
                      val price: String,
                      val author: String,
                      val authorid: String,
                      val subject: String,
                      val userValidate: String,
                      val datelineOrigin: String,
                      val lastpost: String,
                      val lastposter: String,
                      val replies: String,
                      val displayorder: String,
                      val highlight: String,
                      val digest: String,
                      val rate: String,
                      val special: String,
                      val attachment: String,
                      val moderated: String,
                      val closed: String,
                      val stickreply: String,
                      val recommends: String,
                      val recommend_add: String,
                      val heats: String,
                      val recommend_sub: String,
                      val status: String,
                      val isgroup: String,
                      val favtimes: String,
                      val sharetimes: String,
                      val stamp: String,
                      val icon: String,
                      val iconlasttime: String,
                      val cover: String,
                      val replycredit: String,
                      val relatebytag: String,
                      val maxposition: String,
                      val authorpoll: String,
                      val pollpercent: String,
                      val delayDays: String,
                      val warningType: String,
                      val lastposterenc: String,
                      val typehtml: String,
                      val sorthtml: String,
                      val multipage: String,
                      val recommendicon: String,
                      val heatlevel: Int,
                      val moved: Int,
                      val icontid: String,
                      val folder: String,
                      val new: Int,
                      val weeknew: Boolean,
                      val pushedaid: String,
                      val id: String,
                      val postdate: String,
                      val hits: String,
                      val ifupload: String,
                      val type: String,
                      val iconurl: String


)
{
    override fun equals(other: Any?): Boolean {
        if(other is CommonItem)
        {
            return  tid.equals(other.tid) ;
        }
        return super.equals(other)
    }
}