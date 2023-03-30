package com.github.marciel404

import com.github.marciel404.utils.Configs.configData
import com.github.marciel404.utils.loader

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        loader.client(configData("configData.json", arrayOf("token")))
    }


}