package com.airwallex.demo

import com.airwallex.grpc.annotations.GrpcClient
import com.airwallex.grpc.error.Error
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.google.protobuf.StringValue
import demo.HelloGrpcKt
import demo.HelloRpc
import io.grpc.Context
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.MILLISECONDS
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class HelloService(
    @GrpcClient private val next: HelloGrpcKt.HelloCoroutineStub
) : HelloRpc {

    private val logger = LoggerFactory.getLogger(HelloService::class.java)

    override suspend fun greet(request: String): Result<String, Error> {
        logger.info(
            "[service-2] start greet, time remaining: {}",
            Context.current().deadline?.timeRemaining(MILLISECONDS)
        )

        delay(3000)

        return try {
            val result = next.greet(StringValue.of(request)).value

            logger.info("[service-2] end greet")

            Ok(result)
        } catch (e: Throwable) {
            logger.error("error occurs", e)
            Error.internal(e)
        }
    }
}
