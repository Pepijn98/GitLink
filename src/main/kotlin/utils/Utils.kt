package dev.vdbroek.utils

import dev.kord.common.entity.Snowflake

val Long.snowflake
    get() = Snowflake(this)

val String.snowflake
    get() = Snowflake(this)
