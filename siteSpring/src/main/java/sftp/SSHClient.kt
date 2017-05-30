package sftp

//import com.google.gson.Gson
import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.io.File
import java.io.OutputStreamWriter
import java.util.*
import javax.ejb.Stateful

/**
 * Created by reactive on 12.03.2017.
 */

@Component("session")
open class SSHClient {

    private var sftp: ChannelSftp? = null
//    private var gson = Gson()
    private var session:Session?=null
    private var channel: Channel?=null
    private val root = FileSystem.Folder()

    fun getRoot():FileSystem.Folder
    {
        return root
    }
    var login:String=""
    var password:String=""
    var ip:String=""
    var port:Int=0


    fun ssh(address: String, port: Int,login:String,pass:String) {
        this.login=login
        this.password =pass
        this.ip=address
        this.port=port
        this.root.files.clear()
        this.root.folders.clear()
        val ssh = JSch()
        session = ssh.getSession(login, address, port)
        session!!.setPassword(pass)
        session!!.setConfig("StrictHostKeyChecking", "no")
        session!!.connect()
        channel = session!!.openChannel("sftp")
        channel?.connect()
        sftp = channel as ChannelSftp
        val list = sftp?.ls("/") as? Vector<ChannelSftp.LsEntry>
        var result = ""
        fillFileSystem2(sftp!!,"",root)
//        channel.disconnect()
//        session.disconnect()

    }
    private var prevFolder:FileSystem.Folder?=null
    private var currentFolder:FileSystem.Folder? = null


    @Deprecated("Old method and dont working")
    fun fillFileSystem(channelSftp: ChannelSftp, path: String, fileSystem: FileSystem) {

        val list = channelSftp.ls(path) as Vector<ChannelSftp.LsEntry>
        var curr=""
        for (item in list) {

            if (!(item.filename[item.filename.length-1]=='.'))
            {

                if (item.attrs.isDir) {

                    val pathFolder = prevFolder?.path+"/" + item.filename
                    prev+="/"
                    curr = prev+path
                    val nameFolder = item.filename
                    val folder = FileSystem.Folder(nameFolder,pathFolder)
                    prevFolder!!.folders.add(folder)
                    prevFolder=folder
                    fillFileSystem(channelSftp, pathFolder, fileSystem)
                } else {

                    if (prevFolder==null)
                    {
                        val pathFolder = "/"
                        val nameFolder = "main"
                        prevFolder = FileSystem.Folder(nameFolder,pathFolder)
                        fileSystem.folders.add(prevFolder!!)
                    }
                    val pathFolder = prevFolder?.path+"/" + item.filename
                    val nameFolder = item.filename
                    val file=FileSystem.File(nameFolder,pathFolder)
                    prevFolder!!.files.add(file)
                }

            }

        }

    }

    private var prev=""
    fun fillFileSystem2(channelSftp: ChannelSftp, path: String, folder:FileSystem.Folder) {

        prev+="/"
        var curr=prev+path

        val list = channelSftp.ls(curr) as Vector<ChannelSftp.LsEntry>

        for (item in list) {

            if (!(item.filename[item.filename.length-1]=='.'))
            {

                if (item.attrs.isDir) {


                    prev+=path
                    val nameFolder = item.filename
                    val n_folder =FileSystem.Folder(nameFolder,curr)
                    folder.folders.add(n_folder)
                    fillFileSystem2(channelSftp,nameFolder , n_folder)
                } else {
                    val name = item.filename
                    folder.files.add(FileSystem.File(name,curr+"/"+name))
                }

            }

        }
    prev=""
    }

    fun download(url:String):String
    {

        val url1="/"+url
        var res=""
        val buffer = ByteArray(1024)
        var size=0
        var inputstream=sftp!!.get(url1)
        while (true)
        {
            size = inputstream.read(buffer)
            if(size==-1)
            {
                break
            }

            res+=String(buffer,0,size)
        }
//        println(res)
        return res
    }

    fun connect(address: String, port: Int,login:String,pass:String)
    {
        this.login=login
        this.password =pass
        val ssh = JSch()
        session = ssh.getSession(login, address, port)
        session!!.setPassword(pass)
        session!!.setConfig("StrictHostKeyChecking", "no")
        session!!.connect()
        channel = session!!.openChannel("sftp")
        channel?.connect()
        sftp = channel as ChannelSftp
    }

    fun closeChannel()
    {
        this.channel!!.disconnect()
    }

    fun closeSession()
    {
        this.session!!.disconnect()
    }

//    fun toJson():String
//    {
//        return gson.toJson(root)
//    }

}