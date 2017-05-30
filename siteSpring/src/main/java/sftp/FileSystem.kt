/**
 * Created by reactive on 11.03.2017.
 */

package sftp

open class FileSystem {

    class File {
        var name = ""
        var path = ""

        constructor(name: String, path: String) {
            this.name = name
            this.path = path
        }
    }
    class Folder {
        var files = ArrayList<File>()
        var folders = ArrayList<Folder>()
        var name=""
        var path=""
        constructor(name:String,path:String)
        {
            this.name=name
            this.path=path
        }
        constructor()
        {

        }
    }

    var folders = ArrayList<Folder>()
}