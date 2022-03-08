package com.airwallex.demo

import com.airwallex.grpc.error.Error
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import demo.HelloRpc
import io.grpc.Context
import java.util.concurrent.TimeUnit
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class HelloService : HelloRpc {

    private val logger = LoggerFactory.getLogger(HelloService::class.java)

    override suspend fun greet(request: String): Result<String, Error> {
        logger.info(
            "[service-3] in greet, time remaining: {}",
            Context.current().deadline?.timeRemaining(TimeUnit.MILLISECONDS)
        )

        return Ok(request)
    }
}
