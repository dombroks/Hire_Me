package com.dom_broks.hireme.utils

import com.dom_broks.hireme.model.PortfolioItem

interface DataHolder{
    fun hold(list :Resource< List<PortfolioItem>>)
}