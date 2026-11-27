package com.example.distributedsearch

import java.util.UUID
import jakarta.servlet.FilterChain
import jakarta.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.slf4j.{LoggerFactory, MDC}
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RequestLoggingFilter extends OncePerRequestFilter {
  private val requestLogger = LoggerFactory.getLogger(classOf[RequestLoggingFilter])

  override protected def doFilterInternal(
      request: HttpServletRequest,
      response: HttpServletResponse,
      filterChain: FilterChain
  ): Unit = {
    val requestId = UUID.randomUUID().toString
    val startedAt = System.nanoTime()
    response.setHeader("X-Request-Id", requestId)
    MDC.put("requestId", requestId)
    try {
      filterChain.doFilter(request, response)
    } finally {
      val tookMs = (System.nanoTime() - startedAt) / 1000000L
      requestLogger.info(
        s"requestId=$requestId method=${request.getMethod} path=${request.getRequestURI} " +
          s"status=${response.getStatus} tookMs=$tookMs"
      )
      MDC.remove("requestId")
    }
  }
}
