package configuration


import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

/**
 * Created by reactive on 22.05.2017.
 */

@Configuration
@EnableMongoRepositories(basePackages = arrayOf<String>("service") )
open class Configure {


    @Bean
    open fun mongoFactory():MongoDbFactory
    {
        return SimpleMongoDbFactory(MongoClient("localhost"),"test")
    }

    @Bean
   open fun mongoTemplate():MongoTemplate
    {
        val template = MongoTemplate(mongoFactory())
        return template
    }

    @Bean
    open fun persistenceExceptionTranslationPostProcessor(): PersistenceExceptionTranslationPostProcessor
    {
        val postProcessor = PersistenceExceptionTranslationPostProcessor()
        return postProcessor
    }

}