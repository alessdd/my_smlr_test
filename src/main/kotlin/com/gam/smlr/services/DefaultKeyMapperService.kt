package com.gam.smlr.services

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by alexandr on 10.06.17.
 */
@Component
class DefaultKeyMapperService : KeyMapperService{

    private val map : MutableMap<String, String> = ConcurrentHashMap()

    override fun add(key: String, link: String): KeyMapperService.Add {
        if(map.contains(key)){
            return KeyMapperService.Add.AlreadyExist(key)
        } else {
            map.put(key, link)
            return KeyMapperService.Add.Success(key, link)
        }
    }

    override fun getLink(key: String): KeyMapperService.Get = if(map.contains(key)){
        KeyMapperService.Get.Link(map.get(key)!!)
    } else {
        KeyMapperService.Get.NotFound(key)
    }
}