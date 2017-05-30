package RestControllers

import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.BasicQuery
import org.springframework.data.mongodb.core.query.CriteriaDefinition
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.DataService
import sftp.Data

/**
 * Created by reactive on 23.05.2017.
 */

@RestController
class LocalRestController {

    private var dbservice: DataService?=null
    private var dbtebplate:MongoTemplate?=null

    @Autowired
    fun setDbTemplate(dbtemplate:MongoTemplate)
    {
        this.dbtebplate=dbtemplate
    }

    @Autowired
    fun setDbService(dbservice: DataService)
    {
        this.dbservice = dbservice
    }

    @RequestMapping("/localmenu",produces = arrayOf("application/json"))
    fun getMenu(): MutableList<Data>?
    {
        return dbservice?.findAll()
    }

    @RequestMapping("/localmenu2/{path}")
    fun getFile(@PathVariable("path") path:Int):Data
    {

        val query="{\"_id\":$path}"
        println(query)
        val d = Data()
        val query2=Query()
        val dbobj = BasicDBObject("_id",path)
        val bscQuery=BasicQuery(dbobj)
        return dbtebplate?.findOne(bscQuery,d.javaClass)!!
    }
}