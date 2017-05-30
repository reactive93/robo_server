/**
 * Created by reactive on 22.03.2017.
 */
package sftp

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import service.DateTimeCreator
import java.time.format.DateTimeFormatter
import java.util.*

@Document(collection="robot1")
open class Data {


        @Id
    var id=this.hashCode()
    var date =DateTimeCreator.getTime()
    var volts= mutableListOf<Int>()
    var amper= mutableListOf<Int>()
    var volt2= mutableListOf<Int>()
    var amper2= mutableListOf<Int>()
    var ambiet_temp= mutableListOf<Int>()
    var press= mutableListOf<Int>()
    var temp1= mutableListOf<Int>()
    var temp2= mutableListOf<Int>()
    var temp3= mutableListOf<Int>()
    var temp4= mutableListOf<Int>()
    var temp5= mutableListOf<Int>()
    var temp6= mutableListOf<Int>()
    var temp7= mutableListOf<Int>()

    var min_temp1=0
    var min_temp2=0
    var min_temp3=0
    var min_temp4=0
    var min_temp5=0
    var min_temp6=0
    var min_temp7=0

    var max_temp1=0
    var max_temp2=0
    var max_temp3=0
    var max_temp4=0
    var max_temp5=0
    var max_temp6=0
    var max_temp7=0

    var avg_volt=0
    var avg_amper=0
    var avg_volt2=0
    var avg_amper2=0
    var avg_ambiet_temp=0
    var avg_press=0
    var avg_temp1=0
    var avg_temp2=0
    var avg_temp3=0
    var avg_temp4=0
    var avg_temp5=0
    var avg_temp6=0
    var avg_temp7=0

    constructor()
    {

    }


}