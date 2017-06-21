package com.qxb.kotlin.data

/**
 * Created by root on 17-6-2.
 */
data class ReplyItem(
        val autor: String,
        val message: String,
        val micon: String,
        val dateline: String
) {
    companion object {
        public val TAG_AUTHOR = "author";
        public val TAG_MESSAGE = "message";
        public val TAG_DATE_LINE = "dateline";
        public val TAG_MICON = "micon";
    }
}