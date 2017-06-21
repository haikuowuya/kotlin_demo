package com.qxb.kotlin.data

/**
 * Created by root on 17-6-1.
 */
data class ThreadResult(val threads: ThreadItem,
                        val page:List<String>,
                        val shortcut: Int,
                        val foruminfo: ForumInfoItem,
                        val locationtype:List<LocationTypteItem>
);