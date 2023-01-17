package com.example.milkaggregatorapplication

class User {
    var product: String? = null
    var amount: String? = null
    var name: String? = null
    var mobile: String? = null
    var address: String? = null

    internal constructor() {}
    constructor(
        product: String?,
        amount: String?,
        name: String?,
        mobile: String?,
        address: String?
    ) {
        this.product = product
        this.amount = amount
        this.name = name
        this.mobile = mobile
        this.address = address
    }
}