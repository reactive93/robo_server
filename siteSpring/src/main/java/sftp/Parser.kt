package sftp

//import com.google.gson.Gson
import org.springframework.stereotype.Service
import sftp.Data
import java.io.File
import java.io.FileInputStream

/**
 * Created by reactive on 19.03.2017.
 */
@Service
open class Parser {

    var str1:String = ""
//    val gson = Gson()

    fun readFile()
    {
        val file = File("D:\\files_ftp\\data5.txt")
        val input = FileInputStream(file)
        val buffer = ByteArray(1024)
        var size=0
        while(true)
        {
            size = input.read(buffer)
            if(size==-1)
            {
                break
            }
            str1+=String(buffer,0,size)
        }

    }

    private fun parse(str:String):Data
    {

        var temp=""
        var array = mutableListOf<String>()
        val mas =str.split("\n")
        var data = Data()
        for (i in 0..mas.size-1)
        {
            if(mas[i].isNotEmpty())
            {
                if(mas[i][0]=='T')
                {
                    parsing(mas[i],data,true)
                }
                else
                {
                    parsing(mas[i],data,false)
                }
            }


        }

        return data
    }

    private fun parsing(str:String, data: Data, temps:Boolean)
    {
        var temp=""
        var counter=0

        for (i in str)
        {
            if( (i!='\t') && (i!='\r') && (i!='T') )
            {
                temp+=i
            }

            if( (i=='\t') && (temp==""))
            {

            }
            if (temps!=true) {


                if ((i == '\t') && (counter == 0) && (temp != "")) {
                    data.volts.add(temp.toInt())
                    counter++
                    temp = ""
                }

                if ((i == '\t') && (counter == 1) && (temp != "")) {
                    data.amper.add(temp.toInt())
                    counter++
                    temp = ""
                }

                if ((i == '\t') && (counter == 2) && (temp != "")) {
                    data.volt2.add(temp.toInt())
                    counter++
                    temp = ""
                }

                if ((i == '\t') && (counter == 3) && (temp != "")) {
                    data.amper2.add(temp.toInt())
                    counter++
                    temp = ""
                }

                if ((i == '\t') && (counter == 4) && (temp != "")) {
                    data.ambiet_temp.add(temp.toInt())
                    counter++
                    temp = ""
                }
                if ((i == '\t') && (counter == 5) && (temp != "")) {
                    data.press.add(temp.toInt())
                    counter++
                    temp = ""
                }
            }
            else
            {
                if ((i == '\t') && (counter == 0) && (temp != "")) {
                    data.temp1.add(temp.toInt())
                    counter++
                    temp = ""
                }
                if ((i == '\t') && (counter == 1) && (temp != "")) {
                    data.temp2.add(temp.toInt())
                    counter++
                    temp = ""
                }
                if ((i == '\t') && (counter == 2) && (temp != "")) {
                    data.temp3.add(temp.toInt())
                    counter++
                    temp = ""
                }
                if ((i == '\t') && (counter == 3) && (temp != "")) {
                    data.temp4.add(temp.toInt())
                    counter++
                    temp = ""
                }
                if ((i == '\t') && (counter == 4) && (temp != "")) {
                    data.temp5.add(temp.toInt())
                    counter++
                    temp = ""
                }
                if ((i == '\t') && (counter == 5) && (temp != "")) {
                    data.temp6.add(temp.toInt())
                    counter++
                    temp = ""
                }
                if ( ((i == '\t') ||(i=='\r')) && (counter == 6) && (temp != "")) {
                    data.temp7.add(temp.toInt())
                    counter++
                    temp = ""
                }
            }
        }
    }

//    fun toJsonData(str:String):String
//    {
//        val data=parse(str)
//
//        return gson.toJson(data)
//    }

    fun getData(str:String):Data
    { var data = parse(str)

        var mas = MathExcec.AllEval(data.temp1 as ArrayList<Int>)
        data.min_temp1 = mas[0]
        data.max_temp1 = mas[1]
        data.avg_temp1 = mas[2]
        mas = MathExcec.AllEval(data.temp2 as ArrayList<Int>)
        data.min_temp2 = mas[0]
        data.max_temp2 = mas[1]
        data.avg_temp2 = mas[1]
        mas = MathExcec.AllEval(data.temp3 as ArrayList<Int>)
        data.min_temp3 = mas[0]
        data.max_temp3 = mas[1]
        data.avg_temp3 = mas[2]
        mas = MathExcec.AllEval(data.temp4 as ArrayList<Int>)
        data.min_temp4 = mas[0]
        data.max_temp4 = mas[1]
        data.avg_temp4 = mas[2]
        mas = MathExcec.AllEval(data.temp5 as ArrayList<Int>)
        data.min_temp5 = mas[0]
        data.max_temp5 = mas[1]
        data.avg_temp5 = mas[2]
        mas = MathExcec.AllEval(data.temp6 as ArrayList<Int>)
        data.min_temp6 = mas[0]
        data.max_temp6 = mas[1]
        data.avg_temp6 = mas[2]
        mas = MathExcec.AllEval(data.temp7 as ArrayList<Int>)
        data.min_temp7 = mas[0]
        data.max_temp7 = mas[1]
        data.avg_temp7 = mas[2]

        data.avg_ambiet_temp = MathExcec.AllEval(data.ambiet_temp as ArrayList<Int>)[2]
        data.avg_amper = MathExcec.AllEval(data.amper as ArrayList<Int>)[2]
        data.avg_amper2 = MathExcec.AllEval(data.amper2 as ArrayList<Int>)[2]
        data.avg_volt = MathExcec.AllEval(data.volts as ArrayList<Int>)[2]
        data.avg_volt2 = MathExcec.AllEval(data.volt2 as ArrayList<Int>)[2]
        data.avg_press = MathExcec.AllEval(data.press as ArrayList<Int>)[2]

        return data
    }

}