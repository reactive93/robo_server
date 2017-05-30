package RestControllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import service.DataService
import sftp.*

/**
 * Created by reactive on 21.04.2017.
 */

@RestController
class MainRestController {


    var sftpc:SSHClient? = null


    private var dbservice:DataService?=null

    @Autowired
    fun setDbService(dbservice:DataService)
    {
        this.dbservice = dbservice
    }

    @Autowired
    fun setsftp(sftpc:SSHClient)
    {
        this.sftpc = sftpc
    }

    @RequestMapping("/menu",produces = arrayOf("application/json"))
    fun getMenu(@RequestParam("ip")ip:String,@RequestParam("port")port:String,@RequestParam("login") login:String,@RequestParam("password")password:String ):FileSystem.Folder{


//        sftpc?.ssh("127.0.0.1",25,"test","test1")
        sftpc?.ssh(ip,port.toInt(),login,password)
        sftpc?.closeChannel()
        sftpc?.closeSession()
        return sftpc!!.getRoot()
    }

    @RequestMapping("/menu2",produces = arrayOf("application/json"))
    fun getMenu2():FileSystem.Folder{


//        sftpc?.ssh("127.0.0.1",25,"test","test1")
        if(sftpc?.ip=="")
        {
            val noFolder = FileSystem.Folder()
            noFolder.folders.add(FileSystem.Folder("No connected ",""))
            return noFolder
        }
        sftpc?.ssh(sftpc?.ip!!,sftpc?.port!!,sftpc?.login!!,sftpc?.password!!)
        sftpc?.closeChannel()
        sftpc?.closeSession()
        return sftpc!!.getRoot()
    }

    @RequestMapping("/file",produces = arrayOf("application/json"))
    fun getFile(@RequestParam("url")url:String):Data
    {
        sftpc?.ssh(sftpc?.ip!!,sftpc?.port!!,sftpc?.login!!,sftpc?.password!!)
        val res = sftpc?.download(url)
        val parser = Parser()
        var data = parser.getData(res!!)
        try {
            dbservice?.insert(data)
        }catch (ex:Exception)
        {
            println("DATA BASE Exception on the insert to DB")
            throw Exception()
        }

        return data
    }

}