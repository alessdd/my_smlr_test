package com.gam.smlr.services

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by alexandr on 10.06.17.
 */
class DefaultKeyMapperServiceTest {

    val service: KeyMapperService = DefaultKeyMapperService();


    private val  KEY: String = "aAbBcCdD"
    private val  LINK: String = "http://www.eveonline.com"
    private val  LINK_NEW: String = "http://wow.ru"

    @Test
    fun clientCanAddNewKeyWithLink(){
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }


    @Test
    fun clientCannotAddExistKey(){
        service.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY, LINK_NEW))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun clientCannotTakeKeyIfKeyNotFoundInService(){
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }


}