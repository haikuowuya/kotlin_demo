package com.qxb.kotlin.data

/**
 * Created by root on 17-5-31.
 */
data  class  ForumInfoItem(
        val fid:String,
        val name :String,
        val descrip:String,
        val forumadmin:String,
        val topictype:List<TopicTypeItem>,
        val topic:String,
        val tpost:String,
        val article:String,
        val trposts:String,
        val visits:String
);