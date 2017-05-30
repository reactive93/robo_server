package service

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by reactive on 23.05.2017.
 */


class DateTimeCreator {



    companion object {
        @JvmStatic
        fun getTime():String
        {

            val dateFormat = SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
            val date = Date()
            return dateFormat.format(date)
        }
    }
}