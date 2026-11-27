package com.example.distributedsearch

import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._

@SpringBootTest
@AutoConfigureMockMvc
class SearchApiIntegrationTest {
  @Autowired
  private var mockMvc: MockMvc = _

  @Test
  def serializesScalaCollectionsAndEmitsARequestId(): Unit = {
    mockMvc
      .perform(get("/api/nodes"))
      .andExpect(status().isOk)
      .andExpect(header().exists("X-Request-Id"))
      .andExpect(jsonPath("$", hasSize[Object](6)))
  }
}
