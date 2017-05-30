package service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import sftp.Data

/**
 * Created by reactive on 22.05.2017.
 */

@Repository
interface DataService:MongoRepository<Data,String> {


}