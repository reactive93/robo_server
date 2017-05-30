package Controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by reactive on 22.04.2017.
 */

@Controller
class MainController {

    @RequestMapping("/index")
    fun index():String
    {
        return "menu1"
    }

    @RequestMapping("/local")
    fun local():String
    {
        return "menu_local"
    }
}